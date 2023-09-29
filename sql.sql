create database asm_java6;
use asm_java6;
select * from accounts;
Insert into categories(name) values ("Bàn"), ("Đèn"), ("Ghế"), ("Sen đá"), ("Lọ");
Insert into products (name,price,image,available,category_id) values
("Lọ thuỷ tinh", 120, "botlee-1.jpg", 1, 5),
("Lọ Nhựa", 70, "botlee-2.jpg", 1, 5),
("Ghế dài", 60, "chair-1.jpg", 1, 3),
("Ghế ngắn", 80, "chair-2.jpg", 1, 3),
("Đèn", 80, "lamp-1.jpg", 1, 2),
("Sen đá", 80, "plant-1.jpg", 1, 4),
("Sen đá lớn", 80, "plant-2.jpg", 1, 4),
("Bàn", 80, "wood-1.jpg", 1, 1);
GRANT ALL PRIVIlEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;