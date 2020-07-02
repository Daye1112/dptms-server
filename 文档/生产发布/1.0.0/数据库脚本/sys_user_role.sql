/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/03 00:44:41                            */
/*==============================================================*/


/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
CREATE TABLE SYS_USER_ROLE
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   ROLE_ID              BIGINT,
   USER_ID              BIGINT,
   REMARK               VARCHAR(256),
   ISVALID              TINYINT,
   CREATE_TIME          DATETIME,
   CREATE_BY            BIGINT,
   UPDATE_TIME          DATETIME,
   UPDATE_BY            BIGINT,
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_USER_ROLE COMMENT '用户角色表';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN ID BIGINT COMMENT 'id';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN ROLE_ID BIGINT COMMENT '角色id';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN USER_ID BIGINT COMMENT '用户id';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN REMARK VARCHAR(256) COMMENT '备注';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN ISVALID TINYINT COMMENT '是否有效';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN CREATE_TIME DATETIME COMMENT '创建时间';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN CREATE_BY BIGINT COMMENT '创建者';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN UPDATE_TIME DATETIME COMMENT '更新时间';

ALTER TABLE SYS_USER_ROLE MODIFY COLUMN UPDATE_BY BIGINT COMMENT '更新者';

