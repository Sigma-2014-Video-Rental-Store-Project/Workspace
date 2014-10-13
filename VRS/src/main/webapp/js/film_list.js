/**
 * Created by Sergey on 13.10.14.
 */
$("#option1").change(function () {
    if ($(this).attr("checked")) {
        $.get("controller", { command: "fullFilmList", page: "1" });
        alert("Отмечен");// делаем что-то, когда чекбокс включен
    } else {
        alert("Отметка снята");// делаем что-то другое, когда чекбокс выключен
    }
});

function setAllFilms() {
    alert("1");
    $.get("controller", { command: "fullFilmList", page: "1" });
}