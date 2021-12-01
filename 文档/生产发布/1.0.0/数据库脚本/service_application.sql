CREATE TABLE service_application
(
  id       BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  org_id   BIGINT COMMENT '组织id',
  app_code varchar(128) COMMENT '应用编号',
  app_name varchar(64) COMMENT '应用名称',
  app_type tinyint COMMENT '应用类型 1：服务 2：网关',
  remark   VARCHAR(256) COMMENT '备注',
  isvalid  TINYINT COMMENT '是否有效',
  ctime    DATETIME COMMENT '创建时间',
  creater  BIGINT COMMENT '创建者',
  mtime    DATETIME COMMENT '更新时间',
  updater  BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='应用表' ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

