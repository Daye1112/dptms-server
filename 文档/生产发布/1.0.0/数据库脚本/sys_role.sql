/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/20 00:23:18                            */
/*==============================================================*/


/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   bigint not null auto_increment,
   role_name            varchar(32),
   role_code            varchar(32),
   is_admin             tinyint,
   org_id               bigint,
   remark               varchar(256),
   isvalid              tinyint,
   create_time          datetime,
   create_by            bigint,
   update_time          datetime,
   update_by            bigint,
   primary key (id)
);

alter table sys_role comment '角色表';

alter table sys_role modify column id bigint auto_increment comment 'id';

alter table sys_role modify column role_name varchar(32) comment '角色名称';

alter table sys_role modify column role_code varchar(32) comment '角色编号';

alter table sys_role modify column is_admin tinyint comment '是否管理员 0：否 1：是';

alter table sys_role modify column org_id bigint comment '组织id';

alter table sys_role modify column remark varchar(256) comment '备注';

alter table sys_role modify column isvalid tinyint comment '是否有效';

alter table sys_role modify column create_time datetime comment '创建时间';

alter table sys_role modify column create_by bigint comment '创建者';

alter table sys_role modify column update_time datetime comment '更新时间';

alter table sys_role modify column update_by bigint comment '更新者';

