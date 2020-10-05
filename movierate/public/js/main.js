//console.log('main.js');

$(function(){
    $('.card').hover(function(){
        $(this).css("background-color",'#e9ecef');
        $(this).find('.tools').css('visibility','visible');
    },function(){
        $(this).css("background-color",'white');
        $(this).find('.tools').css('visibility','hidden');
    })

});