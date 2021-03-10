CREATE TABLE service_config_release
(
  id              BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  application_id  bigint COMMENT '应用id 关联service_application.id',
  profile_id      bigint COMMENT '环境id 关联service_config_profile.id',
  release_version text COMMENT '发布版本',
  release_type    tinyint COMMENT '发布类型 0：普通发布 1：回滚发布',
  release_status  tinyint COMMENT '发布状态 0：失效 1：激活',
  release_time    datetime COMMENT '发布时间',
  remark          VARCHAR(256) COMMENT '备注',
  isvalid         TINYINT COMMENT '是否有效',
  ctime           DATETIME COMMENT '创建时间',
  creater         BIGINT COMMENT '创建者',
  mtime           DATETIME COMMENT '更新时间',
  updater         BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='配置发布表';

