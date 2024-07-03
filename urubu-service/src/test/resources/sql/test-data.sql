
INSERT INTO user_details (id, active, locale, refresh_token) VALUES ('1', b'1', 'en', 'refresh');
INSERT INTO users (id, user_details_id, email, hash_pass, name) VALUES ('1', '1', 'mockuser@bank.com', 'd7b4928b2e1dbf14c95fe438cb5d3d9d', 'Mock User');

INSERT INTO accounts (id, balance, user_id, account_identifier) VALUES ('1', '0', '1', 'account_vdmhMSHCZhOOj8uR2TMb');
