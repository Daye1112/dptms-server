/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/20 00:23:18                            */
/*==============================================================*/


/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   id                   bigint not null auto_increment,
   per_name             varchar(64),
   per_code             varchar(64),
   per_group            varchar(64),
   per_url              varchar(256),
   org_id               bigint,
   remark               varchar(256),
   isvalid              tinyint,
   create_time          datetime,
   create_by            bigint,
   update_time          datetime,
   update_by            bigint,
   primary key (id)
);

alter table sys_permission comment '权限表';

alter table sys_permission modify column id bigint auto_increment comment 'id';

alter table sys_permission modify column per_name varchar(64) comment '权限名';

alter table sys_permission modify column per_code varchar(64) comment '权限码';

alter table sys_permission modify column per_group varchar(64) comment '权限组';

alter table sys_permission modify column per_url varchar(256) comment '权限url';

alter table sys_permission modify column org_id bigint comment '组织id';

alter table sys_permission modify column remark varchar(256) comment '备注';

alter table sys_permission modify column isvalid tinyint comment '是否有效';

alter table sys_permission modify column create_time datetime comment '创建时间';

alter table sys_permission modify column create_by bigint comment '创建者';

alter table sys_permission modify column update_time datetime comment '更新时间';

alter table sys_permission modify column update_by bigint comment '更新者';

