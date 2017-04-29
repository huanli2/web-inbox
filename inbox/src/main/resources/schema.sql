-- create DATABASE if not EXISTS web_inbox;
-- use web-inbox;
-- '1900-01-01 00:00:00.000'
create table users (
    id int(8) NOT NULL AUTO_INCREMENT comment 'id',
    username varchar(100) not null UNIQUE comment 'username,唯一标识用户',
    password VARCHAR (50) not NULL comment 'sha + salt加密之后的密码',
    salt VARCHAR (20) not null,
    added_time datetime DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table contacts (
    id int(8) NOT NULL AUTO_INCREMENT comment 'id',
    user1_name varchar(100) not null comment '联系人1',
    user2_name VARCHAR (100) not NULL comment '联系人2',
    is_deleted BIT not null DEFAULT 0 comment '是否被删除',
    added_time datetime not null DEFAULT CURRENT_TIMESTAMP,
    deleted_time datetime not NULL DEFAULT '1900-01-01 00:00:00.000',

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


