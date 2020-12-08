CREATE TABLE sys_role
(
  id        BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  org_id    BIGINT COMMENT '组织id 关联sys_organization.id',
  role_name VARCHAR(32) COMMENT '角色名称',
  role_code VARCHAR(32) COMMENT '角色编号',
  is_admin  TINYINT COMMENT '是否管理员 0：否 1：是',
  remark    VARCHAR(256) COMMENT '备注',
  isvalid   TINYINT COMMENT '是否有效',
  ctime     DATETIME COMMENT '创建时间',
  creater   BIGINT COMMENT '创建者',
  mtime     DATETIME COMMENT '更新时间',
  updater   BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
);

ALTER TABLE sys_role
  COMMENT '角色表';

