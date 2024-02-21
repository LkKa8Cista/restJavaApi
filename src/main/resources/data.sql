INSERT INTO Color (id, common_Name, hex_Code) VALUES (1, 'Ocean Blue','#0057FB');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (2, 'AquaMarine','#00FBDD');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (3, 'Vivid Red','#FB2C00');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (4, 'Venta Black','#000000');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (5, 'Pure White','#FFFFFF');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (6, 'Pale Cream','#FEFBB7');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (7, 'Baby Blue','#B7E2FE');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (8, 'Nuclear Green','#03E200');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (9, 'Light Gray','#C2AFAF');
INSERT INTO Color (id, common_Name, hex_Code) VALUES (10, 'Dark Gray','#5A5A5A');
INSERT INTO Car_Brand (id, name, country) VALUES (1,'ford',0);
INSERT INTO Car_Brand (id, name, country) VALUES (2,'nissan',7);
INSERT INTO Car_Brand (id, name, country) VALUES (3,'volkswagen',3);
INSERT INTO Car_Brand (id, name, country) VALUES (4,'renault',4);
INSERT INTO Car_Brand (id, name, country) VALUES (5,'ferrari',5);
INSERT INTO Car_Model (id, name, brand_id) VALUES (1, 'Mustang', 1);
INSERT INTO Car_Model (id, name, brand_id) VALUES (2, 'Focus', 1);
INSERT INTO Car_Model (id, name, brand_id) VALUES (3, 'GTR', 2);
INSERT INTO Car_Model (id, name, brand_id) VALUES (4, 'Leaf', 2);
INSERT INTO Car_Model (id, name, brand_id) VALUES (5, 'Golf', 3);
INSERT INTO Car_Model (id, name, brand_id) VALUES (6, 'Polo', 3);
INSERT INTO Car_Model (id, name, brand_id) VALUES (7, 'Megane', 4);
INSERT INTO Car_Model (id, name, brand_id) VALUES (8, 'Clio', 4);
INSERT INTO Car_Model (id, name, brand_id) VALUES (9, 'LaFerrari', 5);
INSERT INTO Car_Model (id, name, brand_id) VALUES (10, '250 GTO', 5);
INSERT INTO Seller (id, name, email, phone_Number) VALUES (1,'Entry with quality','ewq@gmail.com', 123456789);
INSERT INTO Seller (id, name, email, phone_Number) VALUES (2,'Mid Tier','mt@gmail.com',456789123);
INSERT INTO Seller (id, name, email, phone_Number) VALUES (3,'Rich Ones','ro@gmail.com',789123456);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (123,'aa-11-bb',5,2,0,7,5,2,1,1,2, 100, 150);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (234,'bb-22-cc',5,1,0,3,5,1,2,2,1, 150, 200);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (345,'cc-33-dd',4,1,0,9,3,2,3,3,3, 200, 250);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (456,'dd-44-ee',5,1,1,2,5,2,4,4,2, 250, 300);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (567,'ee-55-ff',5,1,0,2,5,2,5,5,1, 300, 350);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (678,'ff-66-gg',5,1,1,5,5,2,6,6,1, 350, 400);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (789,'gg-77-hh',5,2,0,3,5,1,7,7,1, 400, 450);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (890,'hh-88-ii',5,1,0,2,5,2,8,8,1, 450, 500);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (901,'ii-99-jj',2,0,7,9,2,3,9,9,3, 500, 550);
INSERT INTO Car (vin, license_Plate, seats, traction, fuel, type, doors, status, color_id, model_id, seller_id, price_buy, price_sell)
VALUES (1123,'jj-00-kk',2,0,7,9,2,4,10,10,3, 550, 600);