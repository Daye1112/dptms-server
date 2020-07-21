/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     20/07/20 00:23:18                            */
/*==============================================================*/


/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   bigint not null auto_increment,
   menu_name            varchar(64),
   menu_code            varchar(64),
   menu_type            tinyint,
   menu_parent_id       bigint,
   org_id               bigint,
   remark               varchar(256),
   isvalid              tinyint,
   create_time          datetime,
   create_by            bigint,
   update_time          datetime,
   update_by            bigint,
   primary key (id)
);

alter table sys_menu comment '菜单表';

alter table sys_menu modify column id bigint auto_increment comment 'id';

alter table sys_menu modify column menu_name varchar(64) comment '菜单名称';

alter table sys_menu modify column menu_code varchar(64) comment '菜单编号';

alter table sys_menu modify column menu_type tinyint comment '菜单类型 1：普通页 2：新窗口 3：iframe';

alter table sys_menu modify column menu_parent_id bigint comment '父节点id';

alter table sys_menu modify column org_id bigint comment '组织id';

alter table sys_menu modify column remark varchar(256) comment '备注';

alter table sys_menu modify column isvalid tinyint comment '是否有效';

alter table sys_menu modify column create_time datetime comment '创建时间';

alter table sys_menu modify column create_by bigint comment '创建者';

alter table sys_menu modify column update_time datetime comment '更新时间';

alter table sys_menu modify column update_by bigint comment '更新者';

