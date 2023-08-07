CREATE TABLE USER_DETAIL (
                      ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                      user_name VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      access_to_restricted_policy BOOLEAN NOT NULL,
                      granted_authority VARCHAR(255) NOT NULL
);
