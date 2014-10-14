/**
 * Created by Sergey on 13.10.14.
 */
var allFilterLabel = $("#all-filter-label");
var availableFilterLabel = $("#available-filter-label");

var activeClass = "active";
var availableValueName = "available";
var allValueName = "all";

var commandAndPageSearch = "?command=fullFilmList&pageIndex=1";
var availableFilterName = "&filter=available";
var allFilterName = "&filter=all";

var filterParamName = "filter";
if (getUrlParameter(filterParamName) == availableValueName) {
    availableFilterLabel.toggleClass(activeClass);
    allFilterLabel.toggleClass(activeClass);
}
allFilterLabel.on("click", function () {
    if (!allFilterLabel.hasClass(activeClass))
        window.location.search = commandAndPageSearch + allFilterName;
});
availableFilterLabel.on("click", function () {
    if (!availableFilterLabel.hasClass(activeClass))
        window.location.search = commandAndPageSearch + availableFilterName;
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
