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
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(6, "Игры разума", 2001, "От всемирной известности до греховных глубин — все это познал на своей шкуре Джон Форбс Нэш-младший.",
	"6.jpg", 14, 200.0, 2.0, 0.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(7, "Король Лев", 1994, "У величественного Короля-Льва Муфасы рождается наследник по имени Симба. В детстве малыш становится жертвой интриг своего дяди Шрама, мечтающего о власти.",
	"7.jpg", 8, 100.0, 1.0, 0.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(8, "Леон", 1994, "Профессиональный убийца Леон, знакомится со своей очаровательной соседкой Матильдой, семью которой расстреливают полицейские, замешанные в торговле наркотиками.",
	"8.jpg", 3, 320.0, 5.5, 0.0);	
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(9, "Начало", 2010, "Способности Кобба сделали его ценным игроком в мире промышленного шпионажа, они же превратили его в извечного беглеца и лишили всего, что он когда-либо любил.",
	"9.jpg", 7, 420.0, 6.5, 1.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(10, "Бойцовский клуб", 1999, "Терзаемый хронической бессонницей и отчаянно пытающийся вырваться из скучной жизни, клерк встречает Тайлера Дардена, торговца мылом с извращенной философией.",
	"10.jpg", 4, 370.0, 3.5, 1.5);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(11, "Жизнь прекрасна", 1997, "Во время II Мировой войны в Италии в концлагерь были отправлены евреи, отец и его маленький сын.",
	"11.jpg", 2, 190.0, 2.0, 0.5);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(12, "Иван Васильевич меняет профессию", 1973, "Инженер-изобретатель Тимофеев сконструировал машину времени, которая соединила его квартиру с палатами государя Ивана Грозного.",
	"12.jpg", 1, 500.0, 10.0, 3.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(13, "Достучаться до небес", 1997, "Судьба сводит героев картины в больнице, где врачи выносят им смертный приговор. Счет времени их жизней идет на часы.",
	"13.jpg", 21, 240.0, 3.0, 0.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(14, "Крестный отец", 1972, "Криминальная сага, повествующая о нью-йоркской сицилийской мафиозной семье Корлеоне. Фильм охватывает период 1945-1955 годов.",
	"14.jpg", 21, 240.0, 3.0, 0.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(15, "Престиж", 2006, "Роберт и Альфред — фокусники-иллюзионисты, которые на рубеже XIX и XX веков соперничали друг с другом в Лондоне.",
	"15.jpg", 13, 140.0, 2.0, 0.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(16, "Карты, деньги, два ствола", 1998, "Четверо парней накопили по 25 тысяч фунтов, чтобы один из них мог сыграть в карты с опытным шулером и матерым преступником, известным по кличке Гарри-Топор.",
	"16.jpg", 10, 265.0, 2.7, 1.7);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(17, "Криминальное чтиво", 1994, "Винсент Вега и Джулс Винфилд проводят время в философских беседах в перерыве между разборками своего криминального босса Марселласа Уоллеса.",
	"17.jpg", 4, 280.0, 3, 0.0);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(18, "Операция «Ы» и другие приключения Шурика", 1965, "Фильм состоит из трех новелл, объединенных фигурой главного героя Шурика, попадающего в самые невероятные ситуации.",
	"18.jpg", 7, 380.0, 3.5, 0.5);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(19, "Властелин колец: Возвращение Короля", 2009, "Последняя часть трилогии о Кольце Всевластия и о героях, взявших на себя бремя спасения Средиземья.",
	"19.jpg", 10, 190.0, 2.0, 0.5);
INSERT INTO FILMS (FILM_ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT)
	VALUES(20, "Гладиатор", 2000, "В великой Римской империи не было военачальника, равного генералу Максимусу. ",
	"20.jpg", 2, 450.0, 8.0, 1.5);

INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (2, 1);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (10, 2);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (10, 3);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (10, 4);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (13, 5);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (2, 6);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (7, 7);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (3, 8);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (6, 9);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (3, 10);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (12, 11);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (13, 12);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (10, 13);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (12, 14);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (8, 15);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (3, 16);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (3, 17);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (13, 18);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (12, 19);
INSERT INTO FILM_CATEGORIES (CATEGORY_ID, FILM_ID) VALUES (4, 20);


