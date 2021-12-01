CREATE TABLE sys_role_menu
(
  id      BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  menu_id BIGINT COMMENT '菜单id',
  role_id BIGINT COMMENT '角色id',
  remark  VARCHAR(256) COMMENT '备注',
  isvalid TINYINT COMMENT '是否有效',
  ctime   DATETIME COMMENT '创建时间',
  creater BIGINT COMMENT '创建者',
  mtime   DATETIME COMMENT '更新时间',
  updater BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

ALTER TABLE sys_role_menu
  COMMENT '角色菜单表';

