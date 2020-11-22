/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/20 00:23:18                            */
/*==============================================================*/


/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   bigint not null auto_increment,
   username             varchar(64),
   password             varchar(128),
   salt                 varchar(128),
   real_name            varchar(64),
   gender               tinyint,
   phone_number         varchar(32),
   email                varchar(64),
   last_login_time      datetime,
   pwd_update_time      datetime,
   file_id              bigint,
   org_id               bigint,
   is_locked            tinyint,
   remark               varchar(256),
   isvalid              tinyint,
   create_time          datetime,
   create_by            bigint,
   update_time          datetime,
   update_by            bigint,
   primary key (id)
);

alter table sys_user comment '用户表';

alter table sys_user modify column id bigint auto_increment comment 'id';

alter table sys_user modify column username varchar(64) comment '用户名';

alter table sys_user modify column password varchar(128) comment '密码';

alter table sys_user modify column salt varchar(128) comment '盐';

alter table sys_user modify column real_name varchar(64) comment '姓名';

alter table sys_user modify column gender tinyint comment '性别 1：男 2：女';

alter table sys_user modify column phone_number varchar(32) comment '联系电话';

alter table sys_user modify column email varchar(64) comment '邮箱';

alter table sys_user modify column last_login_time datetime comment '上次登录时间';

alter table sys_user modify column pwd_update_time datetime comment '密码更新时间';

alter table sys_user modify column file_id bigint comment '头像文件id';

alter table sys_user modify column org_id bigint comment '组织id';

alter table sys_user modify column is_locked tinyint comment '是否被锁定 0：否 1：是';

alter table sys_user modify column remark varchar(256) comment '备注';

alter table sys_user modify column isvalid tinyint comment '是否有效';

alter table sys_user modify column create_time datetime comment '创建时间';

alter table sys_user modify column create_by bigint comment '创建者';

alter table sys_user modify column update_time datetime comment '更新时间';

alter table sys_user modify column update_by bigint comment '更新者';

