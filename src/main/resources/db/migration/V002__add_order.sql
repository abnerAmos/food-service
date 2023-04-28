alter table tb_restaurant add column complement_address	varchar(255) default null;

create table tb_order (
id					bigint			not null	auto_increment	,
code				varchar(255)	not null					,
subtotal			decimal(19,2)	not null					,
delivery_fee		decimal(19,2)	not null					,
total_value			decimal(19,2)	not null					,
register_date		datetime(6)		not null					,
confirmation_date	datetime(6)		default null				,
delivery_date		datetime(6)		default null				,
cancellation_date	datetime(6)		default null				,
status_order		varchar(12)		not null					,
address				varchar(255)	not null					,
complement_address	varchar(255) 	default null				,
city				varchar(50)		not null					,
district			varchar(50)		not null					,
place_number		varchar(50)		not null					,
postal_code			varchar(10)		not null					,
state				varchar(50)		not null					,
user_id				bigint			not null					,
restaurant_id		bigint			not null					,
type_payment_id		bigint			not null					,
primary key (id)												,
foreign key (user_id) references tb_user (id)					,
foreign key (restaurant_id) references tb_restaurant (id)		,
foreign key (type_payment_id) references tb_type_payment (id)
) engine=InnoDB default charset=utf8mb4;

create table tb_order_item (
id			bigint			not null	auto_increment	,
quantity	int				not null					,
unit_price	decimal(19,2)	not null					,
total_value	decimal(19,2)	not null					,
observation	varchar(255)	default null				,
order_id	bigint			not null					,
product_id	bigint			not null					,
primary key (id)										,
foreign key (order_id) references tb_order (id)			,
foreign key (product_id) references tb_product (id)
) engine=InnoDB default charset=utf8mb4;