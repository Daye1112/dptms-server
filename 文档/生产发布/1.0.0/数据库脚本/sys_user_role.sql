CREATE TABLE sys_user_role
(
  id      BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  role_id BIGINT COMMENT '角色id',
  user_id BIGINT COMMENT '用户id',
  remark  VARCHAR(256) COMMENT '备注',
  isvalid TINYINT COMMENT '是否有效',
  ctime   DATETIME COMMENT '创建时间',
  creater BIGINT COMMENT '创建者',
  mtime   DATETIME COMMENT '更新时间',
  updater BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

ALTER TABLE sys_user_role
  COMMENT '用户角色表';
