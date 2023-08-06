DROP
DATABASE if exists EasyDemoDB;
CREATE
DATABASE EasyDemoDB;
use
EasyDemoDB;

DROP TABLE if exists ed_user;
CREATE TABLE ed_user
(
    uid      INT comment '用户唯一id',
    account  VARCHAR(12) NOT NULL UNIQUE comment '用户账号',
    password VARCHAR(20) NOT NULL comment '用户密码',
    PRIMARY KEY (uid)
);
ALTER TABLE ed_user modify uid int auto_increment;

INSERT INTO ed_user(account, password)
VALUES ('100001', '123456');
INSERT INTO ed_user(account, password)
VALUES ('100002', '123456');
INSERT INTO ed_user(account, password)
VALUES ('100003', '123456');