/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var i = 1;
    
  
 //when the Add Field button is clicked
    $("#add").click(function () {
       
       //Append a new row of code to the "#items" div
        $("#items").append('<tr><td><input name="blockSet" type="text" /></td></tr>');
       
    });

    $("#delete").click(function () {
        $("#items").find('input:text').last().closest('tr').remove();
       
        i--;
       
    });
});/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


