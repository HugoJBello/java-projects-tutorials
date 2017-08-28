INSERT INTO users(username,password,enabled)
VALUES ('hjbello','1234', true);


INSERT INTO user_roles (userid, role)
VALUES (1, 'ROLE_USER');
INSERT INTO user_roles (userid, role)
VALUES (1, 'ROLE_ADMIN');