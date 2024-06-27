package com.urubu.support;

import com.urubu.core.auth.LoginOrigin;
import com.urubu.core.utils.PasswordUtils;
import com.urubu.domain.entity.User;
import com.urubu.domain.entity.UserDetails;
import com.urubu.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urubu.domain.entity.test.support.AbstractTestDbSetup;
import com.urubu.service.config.ServiceConfig;

import java.util.Locale;


@EnableJpaRepositories
@ContextConfiguration(classes = { ServiceConfig.class })
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AbstractTestSupport extends AbstractTestDbSetup {

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected ModelMapper modelMapper;

	@Autowired
	protected UserRepository userRepository;


	public LoginOrigin generateOrigin() {
		return new LoginOrigin("192.168.0.0", "PostmanRuntime/7.36.3", "0:0:0:0:0:0:0:1");
	}

	protected User createUser(String name, String email, String password) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setHashPass(PasswordUtils.encodeMd5(password));

		UserDetails details = new UserDetails(true, new Locale("en"));
		user.setUserDetails(details);

		return userRepository.saveAndFlush(user);
	}
}
