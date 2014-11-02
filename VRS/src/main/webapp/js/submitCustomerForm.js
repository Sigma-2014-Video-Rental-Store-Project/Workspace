/**
 * Created by vlad on 02.11.14.
 */
function submitCustomerForm(btName){
    if(btName == 'update'){
        document.form.submit();
    } else if (btName == 'delete'){
        var command = document.getElementsByName("command");
        command.value = "editCustomerRemove";
        document.form.submit();
    }
}