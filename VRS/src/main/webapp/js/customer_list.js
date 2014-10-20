/**
 * Created by Sergey on 13.10.14.
 */
var allFilterLabel = $("#all-filter-label");
var withFilmsFilterLabel = $("#with-films-filter-label");

var activeClass = "active";
var availableValueName = "withFilms";
var allValueName = "all";

var commandName = "customerList"

var filterParamName = "filter";
if (getUrlParameter(filterParamName) == availableValueName) {
    withFilmsFilterLabel.toggleClass(activeClass);
    allFilterLabel.toggleClass(activeClass);
}

//todo delete this func
function sendGetRequest(filmID, elemnt){
    var temp = elemnt.innerHTML;
    $.ajax({
        type: "GET",
        dataType: "text",
        data: { 'command' : commandName, 'cart': filmID},
        async: true,
        url: window.location.pathname,
        success: function (data) {
            elemnt.onclick = 0;
            elemnt.innerHTML = 'added';
        },
        error: function (request, textStatus, errorThrown) {
            console.log(request.responseText);
            console.log(textStatus);
            console.log(errorThrown);
        },
        beforeSend : function(){
            elemnt.innerHTML = '<i class="fa fa-spinner fa-spin"></i>';
        }
    });
}

allFilterLabel.on("click", function () {
    setAttr(filterParamName, allValueName);
});
withFilmsFilterLabel.on("click", function () {
    setAttr(filterParamName, availableValueName);
});
function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
    return null;
}
