CREATE TABLE user (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(50),
  account_id varchar(100),
  token char(36),
  gmt_create bigint(20),
  gmt_modified bigint(20)
) ;