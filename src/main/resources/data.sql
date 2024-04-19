--- Insertar datos en la tabla categorias
INSERT INTO events(name, create_at, is_present) VALUES ('Tango Techno Night', CURRENT_TIMESTAMP, TRUE);
INSERT INTO events(name, create_at, is_present) VALUES ('Electro Asado', CURRENT_TIMESTAMP,TRUE);
INSERT INTO events(name, create_at, is_present) VALUES ('Palermo Neon Party', CURRENT_TIMESTAMP,TRUE);
INSERT INTO events(name, create_at, is_present) VALUES ('Puerto Madero Deep House Cruise', CURRENT_TIMESTAMP,TRUE);
INSERT INTO events(name, create_at, is_present) VALUES ('Recoleta Underground Experience', CURRENT_TIMESTAMP,TRUE);
INSERT INTO events(name, create_at, is_present) VALUES ('Eclectic Electronic Showcase', CURRENT_TIMESTAMP,TRUE);
--- Insertar datos en la tabla user
INSERT INTO users(email, password, create_at, is_present) VALUES ( 'test@test.com', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO users(email, password, create_at, is_present) VALUES ( 'test2@test.com', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO users(email, password, create_at, is_present) VALUES ( 'test3@test.com', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO users(email, password, create_at, is_present) VALUES ( 'test4@test.com', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO users(email, password, create_at, is_present) VALUES ('test5@test.com', '123456', CURRENT_TIMESTAMP,TRUE);
--- Insertar datos en la tabla profiles
INSERT INTO profiles( user_id, name, lastname, document, create_at, is_present) VALUES ( 1, 'Test', 'Test', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO profiles( user_id, name, lastname, document, create_at, is_present) VALUES ( 2, 'Test2', 'Test2', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO profiles( user_id, name, lastname, document, create_at, is_present) VALUES ( 3, 'Test3', 'Test3', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO profiles( user_id, name, lastname, document, create_at, is_present) VALUES ( 4, 'Test4', 'Test4', '123456', CURRENT_TIMESTAMP,TRUE);
INSERT INTO profiles( user_id, name, lastname, document, create_at, is_present) VALUES ( 5, 'Test5', 'Test5', '123456', CURRENT_TIMESTAMP,TRUE);
--- Insertar datos en la tabla transactions
INSERT INTO transactions(profile_buyer_id, profile_seller_id, state_transaction, create_at, is_present) VALUES ( 1, 2, 'PENDING', CURRENT_TIMESTAMP,TRUE);
INSERT INTO transactions(profile_buyer_id, profile_seller_id, state_transaction, create_at, is_present) VALUES ( 2, 3, 'PENDING', CURRENT_TIMESTAMP,TRUE);
INSERT INTO transactions(profile_buyer_id, profile_seller_id, state_transaction, create_at, is_present) VALUES ( 3, 4, 'PENDING', CURRENT_TIMESTAMP,TRUE);
INSERT INTO transactions(profile_buyer_id, profile_seller_id, state_transaction, create_at, is_present) VALUES ( 4, 5, 'PENDING', CURRENT_TIMESTAMP,TRUE);
INSERT INTO transactions(profile_buyer_id, profile_seller_id, state_transaction, create_at, is_present) VALUES ( 2, 5, 'PENDING', CURRENT_TIMESTAMP,TRUE);
--- Insertar datos en la tabla tickets
INSERT INTO tickets( meta, create_at, transaction_id, profile_id, event_id, is_present, price) VALUES ( 'Meta 1', CURRENT_TIMESTAMP, 1, 1, 1,TRUE, 10000.00);
INSERT INTO tickets( meta, create_at, transaction_id, profile_id, event_id, is_present, price) VALUES ( 'Meta 2', CURRENT_TIMESTAMP, 2, 2, 1,TRUE, 10000.00);
INSERT INTO tickets( meta, create_at, transaction_id, profile_id, event_id, is_present, price) VALUES ( 'Meta 3', CURRENT_TIMESTAMP, 3, 3, 1,TRUE, 10000.00);
INSERT INTO tickets( meta, create_at, transaction_id, profile_id, event_id, is_present, price) VALUES ( 'Meta 4', CURRENT_TIMESTAMP, 4, 4, 2 ,TRUE, 10000.00);
INSERT INTO tickets( meta, create_at, transaction_id, profile_id, event_id, is_present, price) VALUES ( 'Meta 5', CURRENT_TIMESTAMP, 5, 2, 3,TRUE, 10000.00);