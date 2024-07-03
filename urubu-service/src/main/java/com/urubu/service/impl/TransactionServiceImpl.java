package com.urubu.service.impl;

import com.urubu.core.auth.LoginDto;
import com.urubu.core.exceptions.TransactionException;
import com.urubu.core.utils.QRCodeUtils;
import com.urubu.domain.entity.Account;
import com.urubu.domain.entity.Transaction;
import com.urubu.domain.entity.User;
import com.urubu.domain.ref.PaymentMethod;
import com.urubu.domain.ref.PaymentStatus;
import com.urubu.domain.ref.TransactionType;
import com.urubu.domain.repository.AccountRepository;
import com.urubu.domain.repository.TransactionRepository;
import com.urubu.domain.repository.UserRepository;
import com.urubu.model.CreditCardDto;
import com.urubu.model.CreditCardHolderInfo;
import com.urubu.model.TransactionDto;
import com.urubu.service.TransactionService;
import jakarta.ws.rs.core.Context;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Value("${app.host}")
	private String host;
	private static final String PAYMENT_ENDPOINT = "/v1/payment/";

	@Autowired
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
                                  UserRepository userRepository,
                                  ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
	public TransactionDto deposit(@Context LoginDto login, TransactionDto dto) {
		Account account = accountRepository.findByAccountIdentifier(login.getAccountIdentifier());
		User user = userRepository.findByEmail(login.getEmail());
		validateData(login, user, account, dto);

		Transaction transaction = modelMapper.map(dto, Transaction.class);
		transaction.setUser(user);
		transaction.setTransactionIdentifier(generateTransactionIdentifier());
		transaction.setPaymentStatus(PaymentStatus.PENDING);
		transaction.setTrasactionType(TransactionType.DEPOSIT);

		generateTransactionData(transaction);

		Transaction domain = transactionRepository.saveAndFlush(transaction);
		System.err.println(domain);
		return modelMapper.map(domain, TransactionDto.class);
	}

	private void populatePaymentLink(Transaction transaction) {
		String paymentLink = host + PAYMENT_ENDPOINT + transaction.getTransactionIdentifier();
		transaction.setPaymentLink(paymentLink);
	}

	private void generateTransactionData(Transaction transaction) {
		populatePaymentLink(transaction);

		PaymentMethod paymentMethod = PaymentMethod.fromStr(transaction.getPaymentMethod().name());
		switch (paymentMethod) {
			case BANK_SLIP: populateBankSlipFields(transaction); break;
			case PIX: populatePixFields(transaction); break;
		}

	}

	private void populatePixFields(Transaction transaction) {
		String qrCode = QRCodeUtils.generateQRCode(transaction.getPaymentLink());
		System.err.println(qrCode);
		transaction.setPixQrCode(qrCode);
	}

	private void populateBankSlipFields(Transaction transaction) {
		transaction.setBankSlipBarCode("");
		transaction.setBankSlipNumericLine("");
	}

	private void validateData(LoginDto login, User user, Account account, TransactionDto dto) {
		if (!login.getAccountIdentifier().equalsIgnoreCase(account.getAccountIdentifier())) {
			throw new TransactionException("");
		}

		if (!login.getEmail().equalsIgnoreCase(user.getEmail())) {
			throw new TransactionException("");
		}

		if (dto.getAmount() <= 0.0) {
			throw new TransactionException("");
		}
		
	

		// if (dto.getPaymentMethod().getKey().equals(PaymentMethod.CREDIT_CARD.name())
		// && !validCreditCard(dto.getCreditCard(), dto.getCreditCardHolderInfo())) {
		// throw new TransactionException("");
		// }

	}

	private boolean validCreditCard(CreditCardDto creditCard, CreditCardHolderInfo creditCardHolderInfo) {
		return true;
	}

	@Override
	public TransactionDto withdraw(@Context LoginDto login, TransactionDto dto) {
		return null;
	}

	@Override
	public TransactionDto transfer(@Context LoginDto login, TransactionDto dto, String accountIdentifier) {
		return null;
	}

	@Override
	public void manageTransaction(String transactionIdentifier) {

		Transaction transaction = transactionRepository.findByTransactionIdentifier(transactionIdentifier);
		Account account = transaction.getAccount();
		transaction.setPaymentStatus(PaymentStatus.CONFIRMED);
		transactionRepository.saveAndFlush(transaction);

		switch (transaction.getTrasactionType()) {
			case DEPOSIT:
			case WITHDRAW: processWithdraw(account, transaction); break;
		}

	}

	private void processDeposit(Account account, Transaction transaction) {
		Double actualBalance = account.getBalance();
		account.setBalance(actualBalance + transaction.getAmount());
		accountRepository.saveAndFlush(account);
	}

	private void processWithdraw(Account account, Transaction transaction) {
		Double actualBalance = account.getBalance();
		if(actualBalance < transaction.getAmount()) {
			throw new TransactionException("Err");
		}
		account.setBalance(actualBalance - transaction.getAmount());
		accountRepository.saveAndFlush(account);
	}

	private String generateTransactionIdentifier() {
		return "transaction_" + RandomStringUtils.random(20, true, true);
	}
}

//Transaction(id=null, transactionIdentifier=transaction_6hNy5JbSZ3jOUvjRrHP6,
//			user=User(id=null, name=null, email=mockuser@bank.com, hashPass=null, userDetails=null),
//	account=Account(id=null, accountIdentifier=account_vdmhMSHCZhOOj8uR2TMb, balance=null, user=null),
//	trasactionType=DEPOSIT, paymentMethod=PIX, paymentStatus=PENDING, amount=100.0,
//paymentLink=http://localhost:8070//v1/payment/transaction_6hNy5JbSZ3jOUvjRrHP6, pixQrCode=, bankSlipBarCode=null, bankSlipNumericLine=null)
