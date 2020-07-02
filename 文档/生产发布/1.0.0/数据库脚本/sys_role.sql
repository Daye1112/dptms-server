/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/03 00:44:41                            */
/*==============================================================*/


/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
CREATE TABLE SYS_ROLE
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   ROLE_NAME            VARCHAR(32),
   ROLE_CODE            VARCHAR(32),
   IS_ADMIN             TINYINT,
   ORG_ID               BIGINT,
   REMARK               VARCHAR(256),
   ISVALID              TINYINT,
   CREATE_TIME          DATETIME,
   CREATE_BY            BIGINT,
   UPDATE_TIME          DATETIME,
   UPDATE_BY            BIGINT,
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_ROLE COMMENT '角色表';

ALTER TABLE SYS_ROLE MODIFY COLUMN ID BIGINT COMMENT 'id';

ALTER TABLE SYS_ROLE MODIFY COLUMN ROLE_NAME VARCHAR(32) COMMENT '角色名称';

ALTER TABLE SYS_ROLE MODIFY COLUMN ROLE_CODE VARCHAR(32) COMMENT '角色编号';

ALTER TABLE SYS_ROLE MODIFY COLUMN IS_ADMIN TINYINT COMMENT '是否管理员 0：否 1：是';

ALTER TABLE SYS_ROLE MODIFY COLUMN ORG_ID BIGINT COMMENT '组织id';

ALTER TABLE SYS_ROLE MODIFY COLUMN REMARK VARCHAR(256) COMMENT '备注';

ALTER TABLE SYS_ROLE MODIFY COLUMN ISVALID TINYINT COMMENT '是否有效';

ALTER TABLE SYS_ROLE MODIFY COLUMN CREATE_TIME DATETIME COMMENT '创建时间';

ALTER TABLE SYS_ROLE MODIFY COLUMN CREATE_BY BIGINT COMMENT '创建者';

ALTER TABLE SYS_ROLE MODIFY COLUMN UPDATE_TIME DATETIME COMMENT '更新时间';

ALTER TABLE SYS_ROLE MODIFY COLUMN UPDATE_BY BIGINT COMMENT '更新者';

