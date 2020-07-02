/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/03 00:44:41                            */
/*==============================================================*/


/*==============================================================*/
/* Table: SYS_MENU                                              */
/*==============================================================*/
CREATE TABLE SYS_MENU
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   MENU_NAME            VARCHAR(64),
   MENU_CODE            VARCHAR(64),
   MENU_TYPE            TINYINT,
   MENU_PARENT_ID       BIGINT,
   ORG_ID               BIGINT,
   REMARK               VARCHAR(256),
   ISVALID              TINYINT,
   CREATE_TIME          DATETIME,
   CREATE_BY            BIGINT,
   UPDATE_TIME          DATETIME,
   UPDATE_BY            BIGINT,
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_MENU COMMENT '菜单表';

ALTER TABLE SYS_MENU MODIFY COLUMN ID BIGINT COMMENT 'id';

ALTER TABLE SYS_MENU MODIFY COLUMN MENU_NAME VARCHAR(64) COMMENT '菜单名称';

ALTER TABLE SYS_MENU MODIFY COLUMN MENU_CODE VARCHAR(64) COMMENT '菜单编号';

ALTER TABLE SYS_MENU MODIFY COLUMN MENU_TYPE TINYINT COMMENT '菜单类型 1：普通页 2：新窗口 3：iframe';

ALTER TABLE SYS_MENU MODIFY COLUMN MENU_PARENT_ID BIGINT COMMENT '父节点id';

ALTER TABLE SYS_MENU MODIFY COLUMN ORG_ID BIGINT COMMENT '组织id';

ALTER TABLE SYS_MENU MODIFY COLUMN REMARK VARCHAR(256) COMMENT '备注';

ALTER TABLE SYS_MENU MODIFY COLUMN ISVALID TINYINT COMMENT '是否有效';

ALTER TABLE SYS_MENU MODIFY COLUMN CREATE_TIME DATETIME COMMENT '创建时间';

ALTER TABLE SYS_MENU MODIFY COLUMN CREATE_BY BIGINT COMMENT '创建者';

ALTER TABLE SYS_MENU MODIFY COLUMN UPDATE_TIME DATETIME COMMENT '更新时间';

ALTER TABLE SYS_MENU MODIFY COLUMN UPDATE_BY BIGINT COMMENT '更新者';

