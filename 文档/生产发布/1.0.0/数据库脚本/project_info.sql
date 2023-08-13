CREATE TABLE project_info
(
  id       BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  org_id   BIGINT COMMENT '组织id',
  project_name varchar(128) COMMENT '项目名称',
  project_desc varchar(256) COMMENT '项目描述',
  project_app_key varchar(32) COMMENT '项目appKey',
  remark   VARCHAR(256) COMMENT '备注',
  isvalid  TINYINT COMMENT '是否有效',
  ctime    DATETIME COMMENT '创建时间',
  creater  BIGINT COMMENT '创建者',
  mtime    DATETIME COMMENT '更新时间',
  updater  BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='项目信息表' ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

