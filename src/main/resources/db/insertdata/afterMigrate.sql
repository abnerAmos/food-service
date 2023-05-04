set foreign_key_checks = 0;

delete from tb_kitchen;
delete from tb_type_payment;
delete from tb_restaurant;
delete from tb_product;
delete from tb_permission;
delete from tb_user;
delete from tb_group;
delete from tb_order;
delete from tb_order_item;
delete from at_restaurant_type_payment;
delete from at_user_group;
delete from at_group_permission;

set foreign_key_checks = 1;

alter table tb_kitchen auto_increment = 1;
alter table tb_type_payment auto_increment = 1;
alter table tb_restaurant auto_increment = 1;
alter table tb_product auto_increment = 1;
alter table tb_permission auto_increment = 1;
alter table tb_user auto_increment = 1;
alter table tb_group auto_increment = 1;
alter table tb_order auto_increment = 1;
alter table tb_order_item auto_increment = 1;
alter table at_restaurant_type_payment auto_increment = 1;
alter table at_user_group auto_increment = 1;
alter table at_group_permission auto_increment = 1;

insert into tb_kitchen (id, name) values (1, 'Brasileira');
insert into tb_kitchen (id, name) values (2, 'Japonesa');
insert into tb_kitchen (id, name) values (3, 'Italiana');

insert into tb_type_payment (id, description) values (1, 'Cartão de crédito');
insert into tb_type_payment (id, description) values (2, 'Cartão de débito');
insert into tb_type_payment (id, description) values (3, 'Dinheiro');

insert into tb_restaurant (id, active, address, complement_address, city, district, place_number, postal_code, state, date_register, name, delivery_fee, open_up, kitchen_id)
values (1, true, "Rua Fulano", null, "Amapá", "Jabuticaba", "258", "12345678", "AP", '2011-01-01 10:10:10', 'Comida Brasileira', 10, true, 1);

insert into tb_restaurant (id, active, address, complement_address, city, district, place_number, postal_code, state, date_register, name, delivery_fee, open_up, kitchen_id)
values (2, true, "Rua Beltrano", 'Casa 1', "Rio de Janeiro", "Manga", "369", "87654321", "RJ", '2022-02-02 20:20:20', 'Só Peixe', 20, true, 2);

insert into tb_restaurant (id, active, address, complement_address, city, district, place_number, postal_code, state, date_register, name, delivery_fee, open_up, kitchen_id)
values (3, true, "Rua Sicrano", 'AP 10', "Amazonas", "Abacate", "147", "43218765", "AM", '2023-03-03 23:30:30', 'Massa Nobre', 10, true, 3);

insert into tb_product (id, name, description, price, active, restaurant_id) values (1, 'Feijoada', 'Light', 39.90, true, 1);
insert into tb_product (id, name, description, price, active, restaurant_id) values (2, 'Salmão', 'Cortes finos', 69.90, true, 2);
insert into tb_product (id, name, description, price, active, restaurant_id) values (3, 'Massa', 'Feito a Mão', 49.90, false, 3);

insert into tb_permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tb_user (id, username, email, password, date_register) values (1, "abnerbrasil", "brasil@email.com", "123456", '2011-01-01 10:10:10');
insert into tb_user (id, username, email, password, date_register) values (2, "abnerjapao", "japao@email.com", "789456", '2022-02-02 20:20:20');
insert into tb_user (id, username, email, password, date_register) values (3, "abneritalia", "italia@email.com", "987654", '2023-03-03 13:30:30');

insert into tb_group (id, name) values (1, "Grupo One");
insert into tb_group (id, name) values (2, "Grupo Two");
insert into tb_group (id, name) values (3, "Grupo Three");

insert into tb_order (id, code, subtotal, delivery_fee, total_value, register_date, confirmation_date, delivery_date, cancellation_date,
status_order, address, complement_address, city, district, place_number, postal_code, state, user_id, restaurant_id, type_payment_id)
values (1, 15738, 150.00, 7.50, 157.50, '2023-01-01 10:10:10', '2023-01-01 10:15:10', '2023-01-01 10:20:10', null, 'DELIVERED',
'Rua Fulano', 'Casa 3', 'Amapá', 'Jabuticaba', '678', '25812345', 'AP', 1, 1, 1);

insert into tb_order (id, code, subtotal, delivery_fee, total_value, register_date, confirmation_date, delivery_date, cancellation_date,
status_order, address, complement_address, city, district, place_number, postal_code, state, user_id, restaurant_id, type_payment_id)
values (2, 68517, 75.00, 5.00, 80.00, '2023-01-01 10:10:10', null, null, null, 'CREATED', 'Rua das Flores', 'AP-8', 'São Paulo',
'Itaim', '1122-C', '08429-819', 'SP', 2, 2, 2);

insert into tb_order_item (id, quantity, unit_price, total_value, observation, order_id, product_id)
values (1, 2, 25.00, 50.00, 'sem azeitona', 1, 1);

insert into tb_order_item (id, quantity, unit_price, total_value, observation, order_id, product_id)
values (2, 1, 50.00, 50.00, 'sem azeitona', 2, 2);

insert into at_restaurant_type_payment (restaurant_id, type_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
insert into at_user_group (user_id, group_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
insert into at_group_permission (group_id, permission_id) values (1, 2), (2, 1), (3, 1), (3, 2);