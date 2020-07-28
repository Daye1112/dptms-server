CREATE TABLE sys_client_detail
(
  id                      bigint NOT NULL AUTO_INCREMENT,
  client_id               varchar(255),
  resource_ids            varchar(255),
  client_secret           varchar(255),
  scope                   varchar(255),
  authorized_grant_types  varchar(255),
  web_server_redirect_uri varchar(255),
  authorities             varchar(255),
  access_token_validity   int(11),
  refresh_token_validity  int(11),
  additional_information  varchar(4096),
  auto_approve            tinyint(4),
  origin_secret           varchar(255),
  remark                  varchar(256),
  isvalid                 tinyint,
  create_time             datetime,
  create_by               bigint,
  update_time             datetime,
  update_by               bigint,
  PRIMARY KEY (id)
);

ALTER TABLE sys_client_detail
  COMMENT '客户端配置表';

ALTER TABLE sys_client_detail
  MODIFY COLUMN id bigint AUTO_INCREMENT COMMENT 'id';

ALTER TABLE sys_client_detail
  MODIFY COLUMN client_id varchar(255) COMMENT '客户端id';

ALTER TABLE sys_client_detail
  MODIFY COLUMN resource_ids varchar(255) COMMENT '资源ids';

ALTER TABLE sys_client_detail
  MODIFY COLUMN client_secret varchar(255) COMMENT '客户端密钥';

ALTER TABLE sys_client_detail
  MODIFY COLUMN scope varchar(255) COMMENT '可访问域';

ALTER TABLE sys_client_detail
  MODIFY COLUMN authorized_grant_types varchar(255) COMMENT '授权类型';

ALTER TABLE sys_client_detail
  MODIFY COLUMN web_server_redirect_uri varchar(255) COMMENT '转发url';

ALTER TABLE sys_client_detail
  MODIFY COLUMN authorities varchar(255) COMMENT '客户端权限';

ALTER TABLE sys_client_detail
  MODIFY COLUMN access_token_validity int(11) COMMENT '访问token有效期';

ALTER TABLE sys_client_detail
  MODIFY COLUMN refresh_token_validity int(11) COMMENT '刷新token有效期';

ALTER TABLE sys_client_detail
  MODIFY COLUMN additional_information varchar(4096) COMMENT '额外信息';

ALTER TABLE sys_client_detail
  MODIFY COLUMN auto_approve tinyint(4) COMMENT '是否自动审批';

ALTER TABLE sys_client_detail
  MODIFY COLUMN origin_secret varchar(255) COMMENT '源密钥';

ALTER TABLE sys_client_detail
  MODIFY COLUMN remark varchar(256) COMMENT '备注';

ALTER TABLE sys_client_detail
  MODIFY COLUMN isvalid tinyint COMMENT '是否有效';

ALTER TABLE sys_client_detail
  MODIFY COLUMN create_time datetime COMMENT '创建时间';

ALTER TABLE sys_client_detail
  MODIFY COLUMN create_by bigint COMMENT '创建者';

ALTER TABLE sys_client_detail
  MODIFY COLUMN update_time datetime COMMENT '更新时间';

ALTER TABLE sys_client_detail
  MODIFY COLUMN update_by bigint COMMENT '更新者';

