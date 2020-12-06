CREATE TABLE sys_user
(
  id              BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  username        VARCHAR(64) COMMENT '用户名',
  password        VARCHAR(128) COMMENT '密码',
  salt            VARCHAR(128) COMMENT '盐',
  real_name       VARCHAR(64) COMMENT '姓名',
  gender          TINYINT COMMENT '性别 1：男 2：女',
  phone_number    VARCHAR(32) COMMENT '联系电话',
  email           VARCHAR(64) COMMENT '邮箱',
  last_login_time DATETIME COMMENT '上次登录时间',
  pwd_update_time DATETIME COMMENT '密码更新时间',
  file_id         BIGINT COMMENT '头像文件id',
  is_locked       TINYINT COMMENT '是否被锁定 0：否 1：是',
  remark          VARCHAR(256) COMMENT '备注',
  isvalid         TINYINT COMMENT '是否有效',
  ctime           DATETIME COMMENT '创建时间',
  creater         BIGINT COMMENT '创建者',
  mtime           DATETIME COMMENT '更新时间',
  updater         BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
);

ALTER TABLE sys_user
  COMMENT '用户表';
