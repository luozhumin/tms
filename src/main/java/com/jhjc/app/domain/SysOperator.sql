-- auto Generated on 2018-05-02 11:24:14 
-- DROP TABLE IF EXISTS `sys_operator`; 
CREATE TABLE sys_operator(
    `oid` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'oid',
    `soid` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'soid',
    `code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'code',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `driver` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'driver',
    `password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'password',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'sys_operator';
