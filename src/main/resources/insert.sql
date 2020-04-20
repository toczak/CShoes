INSERT INTO color(id, name) VALUES (1, 'Dark');
INSERT INTO color(id, name) VALUES (2, 'Light');
INSERT INTO color(id, name) VALUES (3, 'Multicoloured');
INSERT INTO color(id, name) VALUES (4, 'Unicoloured');

INSERT INTO type(id, name) VALUES (1, 'Sport');
INSERT INTO type(id, name) VALUES (2, 'Elegant');
INSERT INTO type(id, name) VALUES (3, 'Summer');
INSERT INTO type(id, name) VALUES (4, 'Winter');

INSERT INTO gender_group(id, name) VALUES (1, 'Women');
INSERT INTO gender_group(id, name) VALUES (2, 'Men');
INSERT INTO gender_group(id, name) VALUES (3, 'Children');

INSERT INTO manufacturer(id, name) VALUES (1, 'Luxury');
INSERT INTO manufacturer(id, name) VALUES (2, 'Multiple');
INSERT INTO manufacturer(id, name) VALUES (3, 'Prestigious');
INSERT INTO manufacturer(id, name) VALUES (4, 'Popular');

INSERT INTO size(id, size) VALUES (1, 25);
INSERT INTO size(id, size) VALUES (2, 26);
INSERT INTO size(id, size) VALUES (3, 27);
INSERT INTO size(id, size) VALUES (4, 28);
INSERT INTO size(id, size) VALUES (5, 29);
INSERT INTO size(id, size) VALUES (6, 30);
INSERT INTO size(id, size) VALUES (7, 31);
INSERT INTO size(id, size) VALUES (8, 32);
INSERT INTO size(id, size) VALUES (9, 33);
INSERT INTO size(id, size) VALUES (10, 34);
INSERT INTO size(id, size) VALUES (11, 35);
INSERT INTO size(id, size) VALUES (12, 36);
INSERT INTO size(id, size) VALUES (13, 37);
INSERT INTO size(id, size) VALUES (14, 38);
INSERT INTO size(id, size) VALUES (15, 39);
INSERT INTO size(id, size) VALUES (16, 40);
INSERT INTO size(id, size) VALUES (17, 41);
INSERT INTO size(id, size) VALUES (18, 42);
INSERT INTO size(id, size) VALUES (19, 43);
INSERT INTO size(id, size) VALUES (20, 44);
INSERT INTO size(id, size) VALUES (21, 45);

INSERT INTO shoes(id, description, name, price, color_id, group_id, manufacturer_id, size_id, type_id)	VALUES (1, 'Opis Czonwersy BLK1', 'Czonwersy BLK1', 259, 1, 2, 4, 15, 3);
INSERT INTO shoes(id, description, name, price, color_id, group_id, manufacturer_id, size_id, type_id) VALUES (2, 'Opis Czonwersy GRN1', 'Czonwersy GRN1', 139, 1, 1, 4, 12, 3);
INSERT INTO shoes(id, description, name, price, group_id, size_id, manufacturer_id, type_id, color_id)	VALUES (3, 'Opis LIUTO RK3', 'LIUTO RK3', 189, 2, 20, 2, 2, 1);
INSERT INTO shoes(id, description, name, price, group_id, size_id, manufacturer_id, type_id, color_id) VALUES (4, 'Opis Aigrek KRMK3', 'Aigrek KRMK3', 299, 2, 17, 3, 1, 3);
INSERT INTO shoes(id, description, name, price, group_id, size_id, manufacturer_id, type_id, color_id) VALUES (5, 'Opis Mondelum M765', 'Mondelum M765', 329, 1, 9, 2, 3, 2);
