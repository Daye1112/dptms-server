CREATE TABLE sys_menu
(
  id             BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  menu_name      VARCHAR(64) COMMENT '菜单名称',
  menu_code      VARCHAR(64) COMMENT '菜单编号',
  menu_type      TINYINT COMMENT '菜单类型 1：普通页 2：新窗口 3：iframe',
  menu_parent_id BIGINT COMMENT '父节点id',
  remark         VARCHAR(256) COMMENT '备注',
  isvalid        TINYINT COMMENT '是否有效',
  ctime          DATETIME COMMENT '创建时间',
  creater        BIGINT COMMENT '创建者',
  mtime          DATETIME COMMENT '更新时间',
  updater        BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
);

ALTER TABLE sys_menu
  COMMENT '菜单表';
