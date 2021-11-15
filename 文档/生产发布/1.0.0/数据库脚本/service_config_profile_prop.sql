CREATE TABLE service_config_profile_prop
(
  id         BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  profile_id bigint COMMENT '环境id 关联service_config_profile.id',
  prop_key   varchar(128) COMMENT '属性键',
  prop_value text COMMENT '属性值',
  prop_desc  varchar(256) COMMENT '属性描述',
  remark     VARCHAR(256) COMMENT '备注',
  isvalid    TINYINT COMMENT '是否有效',
  ctime      DATETIME COMMENT '创建时间',
  creater    BIGINT COMMENT '创建者',
  mtime      DATETIME COMMENT '更新时间',
  updater    BIGINT COMMENT '更新者',
  PRIMARY KEY (id)
) COMMENT ='配置环境属性表';

