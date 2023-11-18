$(document).on('select2:open', () => {
    document.querySelector('.select2-search__field').focus();
});

$(document).ready(function () {

    $("#filterBtn").on("click", function (event) { // Agrega el parámetro event
        
        console.log('Este es el valor inicial de la fecha ' + $("#daterange").val());
    });

});


$(document).on('load', function () {
    $("#pageLoader").fadeOut("slow");
});

