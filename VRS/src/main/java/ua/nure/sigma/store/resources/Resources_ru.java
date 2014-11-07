package ua.nure.sigma.store.resources;

import java.util.ListResourceBundle;

/**
 * Created by Maksim Sinkevich on 04.11.2014.
 */
public class Resources_ru extends ListResourceBundle {
    private Object[][] contents = {
        // Locale block.
        { "locale.jspf.language", "Язык" },
        { "locale.jspf.current", "Текущий:" },
        { "locale.jspf.changeOn", "Замена:" },
        { "login.title", "Вход в Video Rental Store"},
        { "login.CongratText", "Вход в систему"},
        { "login.EmailPlaceholder", "Email-адресс"},
        { "login.PassPlaceholder", "Пароль"},
        { "login.remember", "Запомнить"},
        { "login.btn", "Вход"},
        { "login.error", "Неверный логин или пароль"},
        { "filmlist.title", "Фильмы"},
        { "filmlist.allCat", ""},
        { "filmlist.all", "Все"},
        { "filmlist.avail", "В наличии"},
        { "filmlist.addNewFilmbtn", "Добавить новый фильм"},
        { "filmlist.searchPlaceholder", "Ключевое слово"},
        { "filmlist.searchbtn", "Поиск"},
        { "filmlist.colName", "Название"},
        { "filmlist.colCopies", "Осталось копий"},
        { "filmlist.colPrice", "Цена за день"},
        { "filmlist.colAdd", "Добавить в корзину"},
        { "filmlist.colEdit", "Редактировать"},
        { "filmlist.editLink", "редактировать"},
        { "filmlist.addLink", "добавить"},
        { "filmlist.absent", "отсутствует"},
        { "filmlist.added", "добавлен"},
        { "addfilm.name", "Название фильма:"},
        { "addfilm.genre", "Жанр:"},
        { "addfilm.browse", "Открыть..."},
        { "addfilm.copies", "Копий: "},
        { "addfilm.rentPrice", "Цена за день ($):"},
        { "addfilm.year", "Год:"},
        { "addfilm.genPrice", "Общая стоимость ($):"},
        { "addfilm.bonus", "Бонусы:"},
        { "addfilm.cancel", "Отмена"},
        { "addfilm.title", "Добавить новый фильм"},
    };

    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }
}
