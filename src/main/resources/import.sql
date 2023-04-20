insert into tb_kitchen (id, name) values (1, 'Brasileira');
insert into tb_kitchen (id, name) values (2, 'Japonesa');

insert into tb_type_payment (id, description) values (1, 'Cartão de crédito');
insert into tb_type_payment (id, description) values (2, 'Cartão de débito');
insert into tb_type_payment (id, description) values (3, 'Dinheiro');

insert into tb_restaurant (id, active, date_registrer, name, delivery_fee, open_up, kitchen_id) values (1, true, '2011-01-01 10:10:10', 'Comida Boa', 10, true, 1);
insert into tb_restaurant (id, active, date_registrer, name, delivery_fee, open_up, kitchen_id) values (2, true, '2022-02-02 20:20:20', 'Só Peixe', 20, true, 2);
insert into tb_restaurant (id, active, date_registrer, name, delivery_fee, open_up, kitchen_id) values (3, true, '2023-03-03 23:30:30', 'Japa Food', 10, true, 2);

insert into tb_state (id, name) values (1, 'Minas Gerais');
insert into tb_state (id, name) values (2, 'São Paulo');
insert into tb_state (id, name) values (3, 'Ceará');

insert into tb_city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into tb_city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into tb_city (id, name, state_id) values (3, 'São Paulo', 2);
insert into tb_city (id, name, state_id) values (4, 'Campinas', 2);
insert into tb_city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into tb_permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurant_type_payment (restaurant_id, type_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)