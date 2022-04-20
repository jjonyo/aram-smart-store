CREATE TABLE IF NOT EXISTS `group_code`
(
    `group_code`  varchar(20) NOT NULL,
    `value`       varchar(20) NOT NULL,
    `use_yn`      char(1)     NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`group_code`)
);

CREATE TABLE IF NOT EXISTS `common_code`
(
    `common_code` varchar(20) NOT NULL,
    `group_code`  varchar(20) NOT NULL,
    `value`       varchar(20) NOT NULL,
    `use_yn`      char(1)     NOT NULL,
    `order`       int         NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`group_code`, `common_code`),
    KEY `index_2` (`common_code`)
);


CREATE TABLE IF NOT EXISTS `category`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint(20),
    `store_id`    bigint(20)  NOT NULL,
    `name`        varchar(30) NOT NULL,
    `level`       int         NOT NULL,
    `use_yn`      char(1)     NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    KEY `index_2` (`store_id`),
    KEY `index_3` (`parent_id`)
);

CREATE TABLE IF NOT EXISTS `category_history`
(
    `sequence`    bigint(20)  NOT NULL AUTO_INCREMENT,
    `category_id` bigint(20)  NOT NULL,
    `parent_id`   bigint(20),
    `store_id`    bigint(20)  NOT NULL,
    `name`        varchar(30) NOT NULL,
    `level`       int         NOT NULL,
    `use_yn`      char(1)     NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`category_id`)
);

CREATE TABLE IF NOT EXISTS `product`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `name`        varchar(30) NOT NULL,
    `category_id` bigint(20)  NOT NULL,
    `price`       int         NOT NULL,
    `quantity`    int DEFAULT 0,
    `description` mediumtext,
    `delete_yn`   char(1)     NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    KEY `index_2` (`category_id`)
);

CREATE TABLE IF NOT EXISTS `product_history`
(
    `sequence`    bigint(20)  NOT NULL AUTO_INCREMENT,
    `product_id`  bigint(20)  NOT NULL,
    `name`        varchar(30) NOT NULL,
    `category_id` bigint(20)  NOT NULL,
    `price`       int         NOT NULL,
    `quantity`    int DEFAULT 0,
    `description` mediumtext,
    `delete_yn`   char(1)     NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`product_id`)
);

CREATE TABLE IF NOT EXISTS `product_order`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `product_id`  bigint(20)  NOT NULL,
    `pay_id`      bigint(20)  NOT NULL,
    `price`       int         NOT NULL,
    `quantity`    int DEFAULT 0,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    KEY `index_2` (`product_id`),
    KEY `index_3` (`pay_id`)
);

CREATE TABLE IF NOT EXISTS `product_order_history`
(
    `sequence`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `product_order_id` bigint(20)  NOT NULL,
    `product_id`       bigint(20)  NOT NULL,
    `pay_id`           bigint(20)  NOT NULL,
    `price`            int         NOT NULL,
    `quantity`         int DEFAULT 0,
    `creator_id`       varchar(25) NOT NULL,
    `created_at`       datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`product_order_id`)
);


CREATE TABLE IF NOT EXISTS `pay`
(
    `id`                    bigint(20)  NOT NULL AUTO_INCREMENT,
    `store_id`              bigint(20)  NOT NULL,
    `amount`                int         NOT NULL,
    `receiver_name`         varchar(20) NOT NULL,
    `receiver_address`      text,
    `receiver_phone_number` varchar(20),
    `state`                 varchar(20) NOT NULL,
    `cancel_type`           varchar(20),
    `cancel_reason`         text,
    `creator_id`            varchar(25) NOT NULL,
    `modifier_id`           varchar(25) NOT NULL,
    `created_at`            datetime    NOT NULL,
    `modified_at`           datetime    NOT NULL,
    PRIMARY KEY (`id`),
    KEY `index_2` (`store_id`)
);

CREATE TABLE IF NOT EXISTS `pay_history`
(
    `sequence`              bigint(20)  NOT NULL AUTO_INCREMENT,
    `pay_id`                bigint(20)  NOT NULL,
    `store_id`              bigint(20)  NOT NULL,
    `amount`                int         NOT NULL,
    `receiver_name`         varchar(20) NOT NULL,
    `receiver_address`      text,
    `receiver_phone_number` varchar(20),
    `state`                 varchar(20) NOT NULL,
    `cancel_type`           varchar(20),
    `cancel_reason`         text,
    `creator_id`            varchar(25) NOT NULL,
    `created_at`            datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`pay_id`)
);


CREATE TABLE IF NOT EXISTS `store`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `name`        varchar(60) NOT NULL,
    `description` mediumtext,
    `state`       varchar(20) NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_2` (`name`)
);

CREATE TABLE IF NOT EXISTS `store_history`
(
    `sequence`    bigint(20)  NOT NULL AUTO_INCREMENT,
    `store_id`    bigint(20)  NOT NULL,
    `name`        varchar(60) NOT NULL,
    `description` mediumtext,
    `state`       varchar(20) NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`store_id`)
);

CREATE TABLE IF NOT EXISTS `user`
(
    `id`           bigint(20)  NOT NULL AUTO_INCREMENT,
    `username`     varchar(30) NOT NULL,
    `password`     varchar(30) NOT NULL,
    `name`         varchar(30) NOT NULL,
    `address`      text,
    `phone_number` varchar(20),
    `state`        varchar(20) NOT NULL,
    `creator_id`   varchar(25) NOT NULL,
    `modifier_id`  varchar(25) NOT NULL,
    `created_at`   datetime    NOT NULL,
    `modified_at`  datetime    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_2` (`username`)
);

CREATE TABLE IF NOT EXISTS `user_history`
(
    `sequence`     bigint(20)  NOT NULL AUTO_INCREMENT,
    `user_id`      bigint(20)  NOT NULL,
    `username`     varchar(30) NOT NULL,
    `password`     varchar(30) NOT NULL,
    `name`         varchar(30) NOT NULL,
    `address`      text,
    `phone_number` varchar(20),
    `state`        varchar(20) NOT NULL,
    `creator_id`   varchar(25) NOT NULL,
    `created_at`   datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`user_id`)
);

CREATE TABLE IF NOT EXISTS `store_member`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `store_id`    bigint(20)  NOT NULL,
    `user_id`     bigint(20)  NOT NULL,
    `type`        varchar(20) NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    KEY `index_2` (`store_id`),
    KEY `index_3` (`user_id`)
);

CREATE TABLE IF NOT EXISTS `store_member_history`
(
    `sequence`        bigint(20)  NOT NULL AUTO_INCREMENT,
    `store_member_id` bigint(20)  NOT NULL,
    `store_id`        bigint(20)  NOT NULL,
    `user_id`         bigint(20)  NOT NULL,
    `type`            varchar(20) NOT NULL,
    `log_type`        varchar(20) NOT NULL,
    `creator_id`      varchar(25) NOT NULL,
    `created_at`      datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`store_member_id`)
);

CREATE TABLE IF NOT EXISTS `api_log`
(
    `sequence`       bigint(20)  NOT NULL AUTO_INCREMENT,
    `store_id`       bigint(20)  NOT NULL,
    `full_path`      text        NOT NULL,
    `domain`         text        NOT NULL,
    `path`           text        NOT NULL,
    `request_param`  text,
    `response_param` text,
    `response_code`  tinytext,
    `creator_id`     varchar(25) NOT NULL,
    `created_at`     datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`store_id`)
);

CREATE TABLE IF NOT EXISTS `product_image`
(
    `id`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `file_id`    bigint(20)  NOT NULL,
    `product_id` bigint(20)  NOT NULL,
    `creator_id` varchar(25) NOT NULL,
    `created_at` datetime    NOT NULL,
    PRIMARY KEY (`id`),
    KEY `index_2` (`file_id`),
    KEY `index_3` (`product_id`)
);

CREATE TABLE IF NOT EXISTS `product_image_history`
(
    `sequence`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `product_image_id` bigint(20)  NOT NULL,
    `log_type`         varchar(20) NOT NULL,
    `file_id`          bigint(20)  NOT NULL,
    `product_id`       bigint(20)  NOT NULL,
    `creator_id`       varchar(25) NOT NULL,
    `created_at`       datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`product_image_id`)
);

CREATE TABLE IF NOT EXISTS `file`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `name`        text        NOT NULL,
    `path`        text        NOT NULL,
    `save_name`   text        NOT NULL,
    `extension`   tinytext    NOT NULL,
    `full_name`   text        NOT NULL,
    `state`       varchar(20) NOT NULL,
    `creator_id`  varchar(25) NOT NULL,
    `modifier_id` varchar(25) NOT NULL,
    `created_at`  datetime    NOT NULL,
    `modified_at` datetime    NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `file_history`
(
    `sequence`   bigint(20)  NOT NULL AUTO_INCREMENT,
    `file_id`    bigint(20)  NOT NULL,
    `name`       text        NOT NULL,
    `path`       text        NOT NULL,
    `save_name`  text        NOT NULL,
    `extension`  tinytext    NOT NULL,
    `full_name`  text        NOT NULL,
    `state`      varchar(20) NOT NULL,
    `creator_id` varchar(25) NOT NULL,
    `created_at` datetime    NOT NULL,
    PRIMARY KEY (`sequence`),
    KEY `index_2` (`file_id`)
);

INSERT INTO group_code
VALUES ('YN', 'YN', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('Y', 'YN', 'Y', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('N', 'YN', 'N', 'Y', 2, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO group_code
VALUES ('STORE_MEMBER_TYPE', '스토어 멤버 유형', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('MANAGER', 'STORE_MEMBER_TYPE', '매니저', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO group_code
VALUES ('LOG_TYPE', '로그 유형', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('CREATE', 'LOG_TYPE', '생성', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('MODIFY', 'LOG_TYPE', '수정', 'Y', 2, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('DELETE', 'LOG_TYPE', '삭제', 'Y', 3, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO group_code
VALUES ('STORE_STATE', '스토어 상태', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('OPEN', 'STORE_STATE', '열림', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('CLOSE', 'STORE_STATE', '닫힘', 'Y', 2, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO group_code
VALUES ('USER_STATE', '사용자 상태', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('NORMAL', 'USER_STATE', '정상', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('DELETE', 'USER_STATE', '탈퇴', 'Y', 2, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO group_code
VALUES ('PAY_STATE', '결제 상태', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('PAY', 'PAY_STATE', '결제', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('CANCEL', 'PAY_STATE', '취소', 'Y', 2, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('DELIVERY', 'PAY_STATE', '배달중', 'Y', 3, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO group_code
VALUES ('CANCEL_TYPE', '취소 유형', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('EMPTY', 'CANCEL_TYPE', '재고없음', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('REFUND', 'CANCEL_TYPE', '환불', 'Y', 2, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('ETC', 'CANCEL_TYPE', '기타', 'Y', 3, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO group_code
VALUES ('FILE_STATE', '파일 상태', 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('NORMAL', 'FILE_STATE', '정상', 'Y', 1, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('DELETE_WAIT', 'FILE_STATE', '삭제대기', 'Y', 2, 'SYSTEM', 'SYSTEM', NOW(), NOW());
INSERT INTO common_code
VALUES ('DELETE', 'FILE_STATE', '삭제', 'Y', 3, 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO user (`username`, `password`, `name`, `address`, `phone_number`, `state`, `creator_id`,
                  `modifier_id`, `created_at`, `modified_at`)
VALUES ('test', '1234', '박종혁', '서울', '01012345678', 'NORMAL', 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO store (`name`, `description`, `state`, `creator_id`, `modifier_id`, `created_at`,
                   `modified_at`)
VALUES ('아람몰', '아람몰', 'OPEN', '1', '1', NOW(), NOW());

INSERT INTO category (`store_id`, `name`, `level`, `use_yn`, `creator_id`, `modifier_id`,
                      `created_at`, `modified_at`)
VALUES (0, 'ROOT', 0, 'Y', 'SYSTEM', 'SYSTEM', NOW(), NOW());

INSERT INTO category (`parent_id`, `store_id`, `name`, `level`, `use_yn`, `creator_id`,
                      `modifier_id`, `created_at`, `modified_at`)
VALUES (1, 1, '의류', 1, 'Y', '1', '1', NOW(), NOW());

INSERT INTO category (`parent_id`, `store_id`, `name`, `level`, `use_yn`, `creator_id`,
                      `modifier_id`, `created_at`, `modified_at`)
VALUES (1, 1, '음식', 1, 'Y', '1', '1', NOW(), NOW());
