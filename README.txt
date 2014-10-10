Для запуска приложения необходимо:
1) в консоли перейти в каталог VRS
2) выполнить команду mvn clean
3) mvn package
4) WIN:
	target\bin\webapp
UNIX:
	sh target/bin/webapp
5)в браузере переходим на страницу:
	localhost:8080/


!! Если вам нужен war-файл(деплоится на сервер локальный):
 mvn compile war:war
Файл появится в директории target.

Для тестирования список админов:пароль
superadmin@vrs.com : admin
admin1@vrs.com : admin
admin2@vrs.com : admin

