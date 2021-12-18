CREATE TABLE file_center
(
  id             BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  file_name      varchar(256) COMMENT '文件名称',
  file_type      TINYINT COMMENT '文件类型 1：文件 2：文件夹',
  file_parent_id BIGINT COMMENT '父文件夹id，0表示根目录',
  file_path      varchar(512) COMMENT '目录id路径',
  file_order     int COMMENT '排序',
  file_id        BIGINT COMMENT '文件id，关联file_info.id',
  remark         VARCHAR(256) COMMENT '备注',
  isvalid        TINYINT COMMENT '是否有效',
  ctime          DATETIME COMMENT '创建时间',
  creater        BIGINT COMMENT '创建者',
  mtime          DATETIME COMMENT '更新时间',
  updater        BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='文件中心表' ENGINE = InnoDB
                   CHARACTER SET = utf8
                   COLLATE = utf8_general_ci;

