
function submitCustomerForm(btName){
    if(btName == 'update'){
        document.form.submit();
    } else if (btName == 'delete'){
        var command = document.getElementsByName("command");
        command.value = "editCustomerRemove";
        document.form.submit();
    }
}