
function submitCustomerForm(btName){
    if(btName == 'update'){
        document.getElementById("form").submit();
    } else if (btName == 'delete'){
        var command = document.getElementById("customerCommand");
        command.value = "editCustomerRemove";
        document.getElementById("form").submit();
    }
}