INSERT INTO ROLES (ROLE_ID, ROLE_NAME) VALUES (1, "SuperAdmin");
INSERT INTO ROLES (ROLE_ID, ROLE_NAME) VALUES (2, "Admin");

INSERT INTO ADMINS (ADMIN_ID, ROLE_ID, ADMIN_EMAIL, PASSWORD_HASH) VALUES (1, 1, "superadmin@vrs.com", );
INSERT INTO ADMINS (ADMIN_ID, ROLE_ID, ADMIN_EMAIL, PASSWORD_HASH) VALUES (2, 2, "admin1@vrs.com", );
INSERT INTO ADMINS (ADMIN_ID, ROLE_ID, ADMIN_EMAIL, PASSWORD_HASH) VALUES (3, 2, "admin2@vrs.com", );

INSERT INTO SEX (SEX_ID, SEX) VALUES (1, "male");
INSERT INTO SEX (SEX_ID, SEX) VALUES (2, "female");
INSERT INTO SEX (SEX_ID, SEX) VALUES (3, "undefined");

INSERT INTO CUSTOMERS (CUSTOMER_ID, LAST_NAME, FIRST_NAME, MIDLE_NAME, CUSTOMER_EMAIL, CUSTOMER_PHONE, SEX_ID, CUSTOMER_PHOTO, BONUS) 
	VALUES (1, "Дуров", "Павел", "Валерьевич", "durov@somemail.com", "+10000000001", 1, "1.jpg", 0.0);
INSERT INTO CUSTOMERS (CUSTOMER_ID, LAST_NAME, FIRST_NAME, MIDLE_NAME, CUSTOMER_EMAIL, CUSTOMER_PHONE, SEX_ID, CUSTOMER_PHOTO, BONUS) 
	VALUES (2, "Медведев", "Дмитрий", "Анатольевич", "medvedev@somemail.com", "+10000000002", 1, "2.jpg", 0.0);
INSERT INTO CUSTOMERS (CUSTOMER_ID, LAST_NAME, FIRST_NAME, MIDLE_NAME, CUSTOMER_EMAIL, CUSTOMER_PHONE, SEX_ID, CUSTOMER_PHOTO, BONUS) 
	VALUES (3, "Кожевникова", "Мария", "Александровна", "kozhevnikova@somemail.com", "+10000000003", 2, "3.jpg", 0.0);
INSERT INTO CUSTOMERS (CUSTOMER_ID, LAST_NAME, FIRST_NAME, MIDLE_NAME, CUSTOMER_EMAIL, CUSTOMER_PHONE, SEX_ID, CUSTOMER_PHOTO, BONUS) 
	VALUES (4, "Трофимова", "Екатерина", "Владимировна", "trofimova@somemail.com", "+10000000004", 2, "4.jpg", 0.0);
INSERT INTO CUSTOMERS (CUSTOMER_ID, LAST_NAME, FIRST_NAME, MIDLE_NAME, CUSTOMER_EMAIL, CUSTOMER_PHONE, SEX_ID, CUSTOMER_PHOTO, BONUS) 
	VALUES (5, "Задорнов", "Михаил", "Николаевич", "zadornov@somemail.com", "+10000000005", 1, "5.jpg", 0.0);
	
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (1, "аниме");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (2, "биография");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (3, "боевик");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (4, "вестерн");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (5, "военный");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (6, "детектив");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (7, "детский");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (8, "для взрослых");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (9, "документальный");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (10, "драма");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (11, "игра");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (12, "история");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (13, "комедия");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (14, "концерт");
INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME) VALUES (15, "короткометражка");

INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(1, "Список Шиндлера", 1993, "Лента рассказывает историю Оскара Шиндлера, члена нацистской партии, преуспевающего фабриканта, спасшего во время Второй мировой войны более тысячи ста евреев.",
	"1.jpg", 3, 200.0, 5.0, 1.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(2, "Побег из Шоушенка", 1994, "Банкир Энди Дюфрейн обвинен в убийстве жены и ее любовника. Оказавшись в тюрьме Шоушенк, он сталкивается беззаконием, царящим по обе стороны решетки.",
	"2.jpg", 10, 300.0, 7.5, 0.5);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(3, "Зеленая миля", 1999, "Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора».",
	"3.jpg", 1, 500.0, 20.0, 2.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(4, "Форрест Гамп", 1994, "От лица главного героя Форреста Гампа, слабоумного безобидного человека с благородным и открытым сердцем, рассказывается история его необыкновенной жизни.",
	"4.jpg", 6, 160.0, 4.0, 0.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(5, "1+1", 2011, "Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники жителя предместья Дрисса, только что освободившегося из тюрьмы.",
	"5.jpg", 12, 320.0, 10.0, 1.0);
	