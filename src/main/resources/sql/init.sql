DROP
DATABASE if exists EasyDemoDB_master;
CREATE
DATABASE EasyDemoDB_master;
use
EasyDemoDB_master;

DROP TABLE if exists edm_user;
CREATE TABLE edm_user
(
    uid      INT comment '用户唯一id',
    name     VARCHAR(20) comment '用户名',
    account  VARCHAR(12) NOT NULL UNIQUE comment '用户账号',
    password VARCHAR(20) NOT NULL comment '用户密码',
    deleted  INT default 0,
    PRIMARY KEY (uid)
);
ALTER TABLE edm_user modify uid int AUTO_INCREMENT;
ALTER TABLE edm_user AUTO_INCREMENT=100001;
