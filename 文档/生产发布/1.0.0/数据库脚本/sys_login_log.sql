CREATE TABLE sys_login_log
(
  id         BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  username   varchar(64) COMMENT '用户名',
  os         varchar(64) COMMENT '操作系统',
  browser    varchar(64) COMMENT '浏览器',
  ip         varchar(64) COMMENT 'ip',
  ip_address varchar(256) COMMENT 'ip地址',
  remark     VARCHAR(256) COMMENT '备注',
  isvalid    TINYINT COMMENT '是否有效',
  ctime      DATETIME COMMENT '创建时间',
  creater    BIGINT COMMENT '创建者',
  mtime      DATETIME COMMENT '更新时间',
  updater    BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
);

ALTER TABLE sys_login_log
  COMMENT '登录日志表';

