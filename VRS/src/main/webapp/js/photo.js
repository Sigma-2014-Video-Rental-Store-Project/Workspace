// Sets up selected image file to the 'img' tag with 'cover' id.
//
// @author Denys Shevchenko
// @version 1.0
function setUpCoverRepresentation(input){

    // Gets extension from the full image name.
    var ext = input.files[0]['name'].substring(input.files[0]['name'].lastIndexOf('.') + 1).toLowerCase();

    // If extension is valid for current image processor...
    if (input.files && input.files[0] && (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {
        var reader = new FileReader();
        reader.onload = function (e) {

            // Sets up chosen image as source for specified 'img' tag.
            $('#cover').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
        } else {

            // If some trouble occurs, then sets up default image.
            $('#cover').attr('src', '/customerPhoto/0.jpg');
        }
}