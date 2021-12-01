CREATE TABLE service_config_profile
(
  id             BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  application_id bigint COMMENT '应用id 关联service_application.id',
  profile_code   varchar(64) COMMENT '环境编号',
  profile_name   varchar(64) COMMENT '环境名称',
  remark         VARCHAR(256) COMMENT '备注',
  isvalid        TINYINT COMMENT '是否有效',
  ctime          DATETIME COMMENT '创建时间',
  creater        BIGINT COMMENT '创建者',
  mtime          DATETIME COMMENT '更新时间',
  updater        BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='配置环境表' ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

