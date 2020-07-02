/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/03 00:44:41                            */
/*==============================================================*/


/*==============================================================*/
/* Table: SYS_MENU_PERMISSION                                   */
/*==============================================================*/
CREATE TABLE SYS_MENU_PERMISSION
(
   ID                   BIGINT NOT NULL AUTO_INCREMENT,
   PER_ID               BIGINT,
   MENU_ID              BIGINT,
   REMARK               VARCHAR(256),
   ISVALID              TINYINT,
   CREATE_TIME          DATETIME,
   CREATE_BY            BIGINT,
   UPDATE_TIME          DATETIME,
   UPDATE_BY            BIGINT,
   PRIMARY KEY (ID)
);

ALTER TABLE SYS_MENU_PERMISSION COMMENT '菜单权限表';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN ID BIGINT COMMENT 'id';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN PER_ID BIGINT COMMENT '权限id';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN MENU_ID BIGINT COMMENT '菜单id';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN REMARK VARCHAR(256) COMMENT '备注';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN ISVALID TINYINT COMMENT '是否有效';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN CREATE_TIME DATETIME COMMENT '创建时间';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN CREATE_BY BIGINT COMMENT '创建者';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN UPDATE_TIME DATETIME COMMENT '更新时间';

ALTER TABLE SYS_MENU_PERMISSION MODIFY COLUMN UPDATE_BY BIGINT COMMENT '更新者';

