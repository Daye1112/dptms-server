CREATE TABLE sys_permission
(
  id        BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  per_name  VARCHAR(64) COMMENT '权限名',
  per_code  VARCHAR(64) COMMENT '权限码',
  per_group VARCHAR(64) COMMENT '权限组',
  per_url   VARCHAR(256) COMMENT '权限url',
  remark    VARCHAR(256) COMMENT '备注',
  isvalid   TINYINT COMMENT '是否有效',
  ctime     DATETIME COMMENT '创建时间',
  creater   BIGINT COMMENT '创建者',
  mtime     DATETIME COMMENT '更新时间',
  updater   BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

ALTER TABLE sys_permission
  COMMENT '权限表';

