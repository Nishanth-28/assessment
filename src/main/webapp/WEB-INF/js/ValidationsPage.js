$(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='pagefrm']").validate({
        rules: {
            pageTitle: "required",
            blockSet: "required",
        },
        messages: {
            pageTitle: "Please specify the tile name",
            blockSet: "blokset should not empty"
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function (form) {
            form.submit();
        }
    });
});

$(document).ready(function () {
$("#sub").click(function(){
    var blockSet=$("#blockMap").val();
    var valid = true;
                
   if(blockSet==='{}'){
       alert("Unable to configure the page as there are no blocks available. First create block(s) before creating page.");
       valid = false;
   }else{
     $("#fm").submit(); 
   }
   return valid;
});

});

