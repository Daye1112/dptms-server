/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/20 00:23:18                            */
/*==============================================================*/


/*==============================================================*/
/* Table: sys_organization                                      */
/*==============================================================*/
create table sys_organization
(
   id                   bigint not null auto_increment,
   org_name             varchar(64),
   org_code             varchar(64),
   remark               varchar(256),
   isvalid              tinyint,
   create_time          datetime,
   create_by            bigint,
   update_time          datetime,
   update_by            bigint,
   primary key (id)
);

alter table sys_organization comment '组织表';

alter table sys_organization modify column id bigint auto_increment comment 'id';

alter table sys_organization modify column org_name varchar(64) comment '组织名称';

alter table sys_organization modify column org_code varchar(64) comment '组织编号';

alter table sys_organization modify column remark varchar(256) comment '备注';

alter table sys_organization modify column isvalid tinyint comment '是否有效';

alter table sys_organization modify column create_time datetime comment '创建时间';

alter table sys_organization modify column create_by bigint comment '创建者';

alter table sys_organization modify column update_time datetime comment '更新时间';

alter table sys_organization modify column update_by bigint comment '更新者';

