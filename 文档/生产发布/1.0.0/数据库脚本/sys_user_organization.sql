/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/20 00:23:18                            */
/*==============================================================*/


/*==============================================================*/
/* Table: sys_user_organization                                 */
/*==============================================================*/
create table sys_user_organization
(
   id                   bigint not null auto_increment,
   org_id               bigint,
   user_id              bigint,
   remark               varchar(256),
   isvalid              tinyint,
   create_time          datetime,
   create_by            bigint,
   update_time          datetime,
   update_by            bigint,
   primary key (id)
);

alter table sys_user_organization comment '用户组织表';

alter table sys_user_organization modify column id bigint auto_increment comment 'id';

alter table sys_user_organization modify column org_id bigint comment '组织id';

alter table sys_user_organization modify column user_id bigint comment '用户id';

alter table sys_user_organization modify column remark varchar(256) comment '备注';

alter table sys_user_organization modify column isvalid tinyint comment '是否有效';

alter table sys_user_organization modify column create_time datetime comment '创建时间';

alter table sys_user_organization modify column create_by bigint comment '创建者';

alter table sys_user_organization modify column update_time datetime comment '更新时间';

alter table sys_user_organization modify column update_by bigint comment '更新者';

