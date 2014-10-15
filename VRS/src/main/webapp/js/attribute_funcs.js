/**
 * Created by Сергей on 14.10.14.
 */
//Common functions for working with attributes.

//this function adds or sets GET parameter and refreshes the page
//first param - name of param
//second param - value of param
function setAttr(prmName,val){
    var res = '';
    var d = location.href.split("#")[0].split("?");
    var base = d[0];
    var query = d[1];
    if(query) {
        var params = query.split("&");
        for(var i = 0; i < params.length; i++) {
            var keyval = params[i].split("=");
            if(keyval[0] != prmName) {
                res += params[i] + '&';
            }
        }
    }
    res += prmName + '=' + val;
    window.location.href = base + '?' + res;
    return true;
}

//this function adds or sets GET !parameters!
//first param - array of names of params
//second param - array values of params
//Warning be careful with length of arrays. The shortest will be used
function setSeveralAttr(prmNames, vals){
    var res = '';
    var d = location.href.split("#")[0].split("?");
    var base = d[0];
    var query = d[1];
    if(query) {
        var params = query.split("&");
        for(var i = 0; i < params.length && i < vals.length; i++) {
            var keyval = params[i].split("=");
            var isParamPresents = false;
            for(var j = 0; j < prmNames.length; j++){
                if(keyval[0] == prmNames[j]){
                    isParamPresents = true;
                    break;
                }
            }
            if(!isParamPresents){
                res += params[i] + '&';
            }
        }
    }
    for(i = 0; i < prmNames.length && i < vals.length; i++){
        res += prmNames[i] + '=' + vals[i];
        if(i != prmNames.length - 1 && i != vals.length - 1){
            res+='&';
        }
    }
    window.location.href = base + '?' + res;
    return true;
}