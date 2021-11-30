CREATE TABLE file_dfs_info
(
  id       BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  file_info_id bigint COMMENT '文件信息id file_info.id',
  file_group varchar(128) COMMENT '存储组',
  file_path varchar(128) COMMENT '存储路径',
  file_size bigint COMMENT '存储大小',
  remark   VARCHAR(256) COMMENT '备注',
  isvalid  TINYINT COMMENT '是否有效',
  ctime    DATETIME COMMENT '创建时间',
  creater  BIGINT COMMENT '创建者',
  mtime    DATETIME COMMENT '更新时间',
  updater  BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='文件存储信息表';

