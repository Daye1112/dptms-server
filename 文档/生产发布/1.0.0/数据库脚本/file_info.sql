CREATE TABLE file_info
(
  id        BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  org_id    BIGINT COMMENT '组织id',
  file_type TINYINT COMMENT '文件类型',
  file_name varchar(256) COMMENT '文件名称',
  file_size bigint COMMENT '文件总大小',
  file_ext  varchar(64) COMMENT '文件类型名',
  remark    VARCHAR(256) COMMENT '备注',
  isvalid   TINYINT COMMENT '是否有效',
  ctime     DATETIME COMMENT '创建时间',
  creater   BIGINT COMMENT '创建者',
  mtime     DATETIME COMMENT '更新时间',
  updater   BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='文件信息表' ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

