// script.js

$(document).ready(function () {
    $("#agregarPregunta").click(function () {
        var topico = {
            titulo: $("#titulo").val(),
            mensaje: $("#mensaje").val()
            // Otros campos si es necesario
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/topicos",
            data: JSON.stringify(topico),
            dataType: "json",
            success: function (response) {
                console.log(response);
                // Manejar la respuesta según tus necesidades
            },
            error: function (error) {
                console.error(error);
                // Manejar el error según tus necesidades
            }
        });
    });
});
