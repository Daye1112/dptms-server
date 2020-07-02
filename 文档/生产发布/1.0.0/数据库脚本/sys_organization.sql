/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/03 00:44:41                            */
/*==============================================================*/


/*==============================================================*/
/* Table: SYS_ORGANIZATION                                      */
/*==============================================================*/
CREATE TABLE SYS_ORGANIZATION
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   ORG_NAME             VARCHAR(64),
   ORG_CODE             VARCHAR(64),
   REMARK               VARCHAR(256),
   ISVALID              TINYINT,
   CREATE_TIME          DATETIME,
   CREATE_BY            BIGINT,
   UPDATE_TIME          DATETIME,
   UPDATE_BY            BIGINT,
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_ORGANIZATION COMMENT '组织表';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN ID BIGINT COMMENT 'id';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN ORG_NAME VARCHAR(64) COMMENT '组织名称';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN ORG_CODE VARCHAR(64) COMMENT '组织编号';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN REMARK VARCHAR(256) COMMENT '备注';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN ISVALID TINYINT COMMENT '是否有效';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN CREATE_TIME DATETIME COMMENT '创建时间';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN CREATE_BY BIGINT COMMENT '创建者';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN UPDATE_TIME DATETIME COMMENT '更新时间';

ALTER TABLE SYS_ORGANIZATION MODIFY COLUMN UPDATE_BY BIGINT COMMENT '更新者';

