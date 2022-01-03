CREATE TABLE monitor_operate_log
(
  id                BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  username          varchar(64) COMMENT '用户名',
  content           varchar(128) COMMENT '操作内容',
  url               varchar(256) COMMENT '请求地址',
  browser           varchar(64) COMMENT '浏览器',
  ip                varchar(64) COMMENT 'ip',
  ip_address        varchar(256) COMMENT 'ip地址',
  log_level         TINYINT COMMENT '日志级别',
  business_type     TINYINT COMMENT '业务类型',
  exception_content varchar(512) COMMENT '异常内容',
  request_param     text COMMENT '请求参数',
  time_consuming    int COMMENT '接口耗时',
  remark            VARCHAR(256) COMMENT '备注',
  isvalid           TINYINT COMMENT '是否有效',
  ctime             DATETIME COMMENT '创建时间',
  creater           BIGINT COMMENT '创建者',
  mtime             DATETIME COMMENT '更新时间',
  updater           BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

ALTER TABLE monitor_operate_log
  COMMENT '操作日志表';

