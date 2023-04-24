insert into tb_kitchen (id, name) values (1, 'Brasileira');
insert into tb_kitchen (id, name) values (2, 'Japonesa');
insert into tb_kitchen (id, name) values (3, 'Italiana');

insert into tb_type_payment (id, description) values (1, 'Cartão de crédito');
insert into tb_type_payment (id, description) values (2, 'Cartão de débito');
insert into tb_type_payment (id, description) values (3, 'Dinheiro');

insert into tb_restaurant (id, active, address, city, district, place_number, postal_code, state, date_registrer, name, delivery_fee, open_up, kitchen_id) values (1, true, "Rua Fulano", "Amapá", "Jabuticaba", "258", "12345678", "AP", '2011-01-01 10:10:10', 'Comida Brasileira', 10, true, 1);
insert into tb_restaurant (id, active, address, city, district, place_number, postal_code, state, date_registrer, name, delivery_fee, open_up, kitchen_id) values (2, true, "Rua Beltrano", "Rio de Janeiro", "Manga", "369", "87654321", "RJ", '2022-02-02 20:20:20', 'Só Peixe', 20, true, 2);
insert into tb_restaurant (id, active, address, city, district, place_number, postal_code, state, date_registrer, name, delivery_fee, open_up, kitchen_id) values (3, true, "Rua Sicrano", "Amazonas", "Abacate", "147", "43218765", "AM", '2023-03-03 23:30:30', 'Massa Nobre', 10, true, 3);

insert into tb_product (id, name, description, price, active, restaurant_id) values (1, "Feijoada", "Light", 39.90, true, 1)
insert into tb_product (id, name, description, price, active, restaurant_id) values (2, "Salmão", "Cortes finos", 69.90, true, 2)
insert into tb_product (id, name, description, price, active, restaurant_id) values (3, "Massa", "Feito a Mão", 49.90, false, 3)

insert into tb_permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tb_user (id, username, email, password, date_registrer) values (1, "abnerbrasil", "brasil@email.com", "123456", '2011-01-01 10:10:10')
insert into tb_user (id, username, email, password, date_registrer) values (2, "abnerjapao", "japao@email.com", "789456", '2022-02-02 20:20:20')
insert into tb_user (id, username, email, password, date_registrer) values (3, "abneritalia", "italia@email.com", "987654", '2023-03-03 13:30:30')

insert into tb_group (id, name) values (1, "Grupo One")
insert into tb_group (id, name) values (2, "Grupo Two")
insert into tb_group (id, name) values (3, "Grupo Three")

insert into restaurant_type_payment (restaurant_id, type_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into user_group (user_id, group_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into group_permission (group_id, permission_id) values (1, 2), (2, 1), (3, 1), (3, 2)