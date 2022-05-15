INSERT INTO users (user_id,name,email,passwd,joined_date)
VALUES('tmddn645','유승우','tmddn645@naver.com','{noop}Asdfqwer!','2021-08-03');

INSERT INTO users (user_id,name,email,passwd,joined_date)
VALUES('kim312','김병만','kim312@naver.com','{noop}Asdfqwer!','2021-08-03');

INSERT INTO roles (role_name)
VALUES('ROLE_USER');

INSERT INTO roles (role_name)
VALUES('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_name) values ('tmddn645', 'ROLE_USER');
INSERT INTO users_roles (user_id, role_name) values ('tmddn645', 'ROLE_ADMIN');
INSERT INTO users_roles (user_id, role_name) values ('kim312', 'ROLE_USER');
