/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
var stringWithShorterURLs = getReplacementString($(".tasks-overflow").text());

function getReplacementString(str){
    return str.replace(/(https?\:\/\/[^\s]*)/gi,function(match){
        return match.substring(0,10) + "..."
    });
}});

