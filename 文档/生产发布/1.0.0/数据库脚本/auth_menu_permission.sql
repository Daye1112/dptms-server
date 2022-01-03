CREATE TABLE auth_menu_permission
(
  id      BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  per_id  BIGINT COMMENT '权限id',
  menu_id BIGINT COMMENT '菜单id',
  remark  VARCHAR(256) COMMENT '备注',
  isvalid TINYINT COMMENT '是否有效',
  ctime   DATETIME COMMENT '创建时间',
  creater BIGINT COMMENT '创建者',
  mtime   DATETIME COMMENT '更新时间',
  updater BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

ALTER TABLE auth_menu_permission
  COMMENT '菜单权限表';

