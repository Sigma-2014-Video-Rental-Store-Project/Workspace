/**
 * Created by Sergey on 13.10.14.
 */
var allFilterLabel = $("#all-filter-label");
var availableFilterLabel = $("#available-filter-label");

var activeClass = "active";
var availableValueName = "available";
var allValueName = "all";

var commandName = "fullFilmList"
var commandAndPageSearch = "?command=fullFilmList&pageIndex=1";
var availableFilterName = "&filter=available";
var allFilterName = "&filter=all";

var filterParamName = "filter";
if (getUrlParameter(filterParamName) == availableValueName) {
    availableFilterLabel.toggleClass(activeClass);
    allFilterLabel.toggleClass(activeClass);
}

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
availableFilterLabel.on("click", function () {
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
