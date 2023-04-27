create table tb_kitchen (
id		bigint		not null	auto_increment	,
name	varchar(50)	not null					,
primary key(id)
) engine=InnoDB default charset=utf8mb4;

create table tb_type_payment (
id			bigint		not null	auto_increment	,
description	varchar(20)	not null					,
primary key(id)
) engine=InnoDB default charset=utf8mb4;

create table tb_restaurant (
id				bigint			not null	auto_increment	,
name			varchar(100)	not null					,
open_up			bit				not null					,
active			bit				not null					,
address			varchar(255)	not null					,
city			varchar(50)		not null					,
district		varchar(50)		not null					,
place_number	varchar(50)		not null					,
postal_code		varchar(10)		not null					,
state			varchar(50)		not null					,
date_register	datetime(6)		not null					,
date_update		datetime(6)		default null				,
delivery_fee	decimal(19,2)	default null				,
kitchen_id		bigint			not null					,
primary key(id)												,
foreign key (kitchen_id) references tb_kitchen (id)
) engine=InnoDB default charset=utf8mb4;

create table tb_product (
id				bigint			not null		auto_increment	,
name			varchar(50)		not null						,
description		varchar(255)	default null					,
active			bit				not null						,
price			decimal(19,2)	not null						,
restaurant_id	bigint			not null						,
primary key (id)												,
foreign key (restaurant_id) references tb_restaurant (id)
) engine=InnoDB default charset=utf8mb4;

create table tb_permission (
id			bigint		not null		auto_increment	,
name		varchar(50)	not null						,
description	varchar(50)	not null						,
primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table tb_user (
id				bigint			not null	auto_increment	,
username		varchar(25)		not null					,
email			varchar(255)	not null					,
password		varchar(255)	not null					,
date_register	datetime(6)		not null					,
primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table tb_group (
id		bigint		not null	auto_increment	,
name	varchar(50)	not null					,
primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table at_user_group (
user_id		bigint	not null					,
group_id	bigint	not null					,
foreign key (user_id) references tb_user (id)	,
foreign key (group_id) references tb_group (id)
) engine=InnoDB default charset=utf8mb4;

create table at_group_permission (
group_id		bigint	not null							,
permission_id	bigint	not null							,
foreign key (group_id) references tb_group (id)				,
foreign key (permission_id) references tb_permission (id)
) engine=InnoDB default charset=utf8mb4;

create table at_restaurant_type_payment (
restaurant_id	bigint	not null								,
type_payment_id	bigint	not null								,
foreign key (restaurant_id) references tb_restaurant (id)		,
foreign key (type_payment_id) references tb_type_payment (id)
) engine=InnoDB default charset=utf8mb4;