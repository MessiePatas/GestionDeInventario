$(document).ready(function () {

    if ($("#logMessage").val() === "Usuario o contraseña incorrecta") {
        /*Swal hace parte del plugin de jquery, el cual es referenciado en templates.fragments en el archivo base.html
         * por tal razon si existe y puede ser invocado. En este caso, Swal llama a la funcion sweetAlert2 para crear mensajes
         * de alerta requeridos para la aplicacion y no generara problemas de referenciacion.
         * 
         * La incidencia "Swal" does not exist. Change its name or declare it so that its usage doesn't result in a "ReferenceError",
         * es un falso positivo*/
        Swal.fire({
            title: 'Oops...',
            text: '¡Los datos ingresados son incorrectos!',
            icon: 'error',
            showConfirmButton: false,
            timer: 3000
        });
    }

});
