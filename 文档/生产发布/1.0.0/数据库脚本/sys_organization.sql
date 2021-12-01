CREATE TABLE sys_organization
(
  id       BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  org_name VARCHAR(64) COMMENT '组织名称',
  org_code VARCHAR(64) COMMENT '组织编号',
  remark   VARCHAR(256) COMMENT '备注',
  isvalid  TINYINT COMMENT '是否有效',
  ctime    DATETIME COMMENT '创建时间',
  creater  BIGINT COMMENT '创建者',
  mtime    DATETIME COMMENT '更新时间',
  updater  BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

ALTER TABLE sys_organization
  COMMENT '组织表';
