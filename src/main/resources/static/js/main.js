$(document).ready(function () {
    $('.select2-multi').select2({
        templateResult: selectMulti(this), width: '100%'
    });

    $('.select2-multi-small').select2({
        templateResult: selectMulti(this)
    });

    $('.select2-multi-small-50').select2({
        templateResult: selectMulti(this), width: "50%"
    });

    $('.select2-multi-medium').select2({
        templateResult: selectMulti(this), width: "90%"
    });

    function selectMulti(option) {
        if (option.element && (option.element).hasAttribute('hidden')) {
            return null;
        }
        return option.text;
    }


    var t;

    window.onload = resetTimer;
    window.onmousemove = resetTimer;
    window.onmousedown = resetTimer; // catches touchscreen presses
    window.onclick = resetTimer;     // catches touchpad clicks
    window.onscroll = resetTimer;    // catches scrolling with arrow keys
    window.onkeypress = resetTimer;

    function logout() {
        window.location.reload();
    }

    function resetTimer() {
        clearTimeout(t);
        t = setTimeout(logout, 310000);  // time is in milliseconds
    }



    $(function () {

        $('input[id="daterange"]').daterangepicker({
            timePicker: true,
            timePickerSeconds: true,
            opens: 'left',
            showDropdowns: true,
            minYear: 1901,
            maxDate: new Date(new Date().setFullYear(new Date().getFullYear() + 5)),
            maxSpan: {
                months: 2
            },

            locale: {
                format: 'YYYY/MM/DD HH:mm:ss',
                "applyLabel": "Aplicar",
                "cancelLabel": "Cancelar",
                "fromLabel": "Inicio",
                "toLabel": "Fin",
                "daysOfWeek": [
                    "Do",
                    "Lu",
                    "Ma",
                    "Mi",
                    "Ju",
                    "Vi",
                    "Sa"
                ],
                "monthNames": [
                    "Enero",
                    "Febrero",
                    "Marzo",
                    "Abril",
                    "Mayo",
                    "Junio",
                    "Julio",
                    "Agosto",
                    "Septiembre",
                    "Octubre",
                    "Noviembre",
                    "Diciembre"
                ]
            }
        });
    });

    if ($("#logMessage").val() === "El registro ya existe") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡El registro a ingresar ya existe!',
            icon: "error",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "Ingreso exitoso") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: '¡Registro creado!',
            icon: "success",
            showConfirmButton: false,
            timer: 1300
        });
    } else if ($("#logMessage").val() === "Modificacion exitosa") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: '¡Registro modificado!',
            icon: "success",
            showConfirmButton: false,
            timer: 1300
        });
    } else if ($("#logMessage").val() === "Problemas con servidor") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡Se tuvieron problemas al conectar con el servidor!',
            icon: "error",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "No se encontro informacion") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡No se encontró información del servicio solicitado!',
            icon: "error",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "Archivo vacio") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡El archivo seleccionado está vacío!',
            icon: "error",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "Problemas al procesar el archivo") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡Se tuvieron problemas al procesar el archivo!',
            icon: "error",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "Tipo de archivo no valido") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡El tipo de archivo seleccionado es inválido!',
            icon: "error",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "Eliminado exitoso") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: '¡Registro eliminado!',
            icon: "success",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "No se pudo generar el backup") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡No se pudo generar el backup de la tabla!',
            icon: "error",
            showConfirmButton: false,
            timer: 3000
        });
    } else if ($("#logMessage").val() === "Mas de 2500 registros") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'La lista es demasiado grande',
            text: '¡La lista tiene más de 2500 datos, por lo que solo se muestra la primera parte!',
            icon: "info",
            showConfirmButton: false,
            timer: 10000
        });
    } else if ($("#logMessage").val() === "No existe el buzon indicado") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡No existe una carpeta en el servidor con el nombre de buzón indicado!',
            icon: "error",
            showConfirmButton: false,
            timer: 15000
        });
    } else if (typeof $("#logMessage").val() !== 'undefined' && $("#logMessage").val().toString().startsWith("No la direccion indicada")) {
        var values = $("#logMessage").val().toString().split(";;");
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡La dirección indicada no existe en el servidor ' + values[1] + '!',
            icon: "error",
            showConfirmButton: false,
            timer: 15000
        });
    } else if (typeof $("#logMessage").val() !== 'undefined' && $("#logMessage").val().toString().startsWith("BulkLoad exitoso")) {
        var values = $("#logMessage").val().toString().split(",");
        var send = values[1];
        var succes = values[2];
        var repeated = values[3];
        var failed = values[4];

        if (send === succes) {
            /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
             * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
             * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
             * 
             * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
             * es un falso positivo*/
            Swal.fire({
                title: '¡Registros insertados exitosamente!',
                text: 'Enviados: ' + send + ' | Exitosos: ' + succes + ' | Repetidos: ' + repeated + ' | Fallidos: ' + failed,
                icon: "success",
                showConfirmButton: false,
                timer: 15000
            });
        } else if (succes === '0') {
            /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
             * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
             * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
             * 
             * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
             * es un falso positivo*/
            Swal.fire({
                title: '¡Se tuvieron problemas al insertar los registros!',
                html: "<div><p> Enviados: " + send + " | Exitosos: " + succes + " | Repetidos: " + repeated + " | Fallidos: " + failed +
                        "</p><a id='detailsButton' class='btn'> <i class='fas fa-file-download fa-2x'></i></a></div>",
                icon: "error",
                showConfirmButton: false,
                timer: 40000
            });
        } else {
            /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
             * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
             * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
             * 
             * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
             * es un falso positivo*/
            Swal.fire({
                title: 'No se cargaron todos los registros',
                html: "<div><p> Enviados: " + send + " | Exitosos: " + succes + " | Repetidos: " + repeated + " | Fallidos: " + failed +
                        "</p><a id='detailsButton' class='btn'> <i class='fas fa-file-download fa-2x'></i></a></div>",
                icon: "info",
                showConfirmButton: false,
                timer: 40000
            });
        }

    } else if (typeof $("#logMessage").val() !== 'undefined' && $("#logMessage").val().toString().startsWith("BulkLoad demasiado grande")) {
        var values = $("#logMessage").val().toString().split("#");
        var correos = values[1];
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: '¡La cantidad de los registros es demasiado grande!',
            text: 'Se enviara un correo informando el resultado de la carga masiva a los correos: ' + correos,
            icon: "info",
            showConfirmButton: false,
            timer: 10000
        });
    } else if ($("#logMessage").val() !== null && typeof $("#logMessage").val() !== "undefined") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: '¡Atenci&oacute;n!',
            text: $("#logMessage").val(),
            icon: "info",
            showConfirmButton: false,
            timer: 10000
        });
    } else {
        //Unused
    }

    $("#pageLoader").fadeOut("hide");

    var estate = 1;

    $("#mnuButton").click(function () {

        if (estate === 0) {
            estate++;
            $("#containerLeft").show();
            document.getElementById('containerLeft').style.width = '15%';
            document.getElementById('containerRight').style.width = '85%';
        } else {
            estate--;
            $("#containerLeft").hide();
            document.getElementById('containerRight').style.width = '100%';
            document.getElementById('containerLeft').style.width = '0%';
        }

    });


    $('#homeK').on('click', function () {
        location.href = "/WebPluton/home";
    });


    $(".loadResource").submit(function () {
        $('#pageLoader').show();
    });

    $('.loadResourceMenu').on('click', function () {
        $('#pageLoader').show();
    });
});
