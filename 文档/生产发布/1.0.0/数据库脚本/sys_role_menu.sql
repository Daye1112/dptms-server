/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/03 00:44:41                            */
/*==============================================================*/


/*==============================================================*/
/* Table: SYS_ROLE_MENU                                         */
/*==============================================================*/
CREATE TABLE SYS_ROLE_MENU
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   MENU_ID              BIGINT,
   ROLE_ID              BIGINT,
   REMARK               VARCHAR(256),
   ISVALID              TINYINT,
   CREATE_TIME          DATETIME,
   CREATE_BY            BIGINT,
   UPDATE_TIME          DATETIME,
   UPDATE_BY            BIGINT,
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_ROLE_MENU COMMENT '角色菜单表';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN ID BIGINT COMMENT 'id';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN MENU_ID BIGINT COMMENT '菜单id';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN ROLE_ID BIGINT COMMENT '角色id';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN REMARK VARCHAR(256) COMMENT '备注';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN ISVALID TINYINT COMMENT '是否有效';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN CREATE_TIME DATETIME COMMENT '创建时间';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN CREATE_BY BIGINT COMMENT '创建者';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN UPDATE_TIME DATETIME COMMENT '更新时间';

ALTER TABLE SYS_ROLE_MENU MODIFY COLUMN UPDATE_BY BIGINT COMMENT '更新者';

