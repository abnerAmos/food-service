insert into tb_kitchen (id, name) values (1, 'Brasileira');
insert into tb_kitchen (id, name) values (2, 'Japonesa');

insert into tb_payment (id, description) values (1, 'Cartão de crédito');
insert into tb_payment (id, description) values (2, 'Cartão de débito');
insert into tb_payment (id, description) values (3, 'Dinheiro');

insert into tb_restaurant (id, name, delivery_fee, kitchen_id, payment_id) values (1, 'Comida Boa', 1, 1);
insert into tb_restaurant (id, name, delivery_fee, kitchen_id, payment_id) values (2, 'Só Peixe', 2, 2);
insert into tb_restaurant (id, name, delivery_fee, kitchen_id, payment_id) values (3, 'Japa Food', 2, 3);

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