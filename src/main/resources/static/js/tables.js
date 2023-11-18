$(document).ready(function () {
    $("#listTable").DataTable({
        "lengthMenu": [[5, 10, 25, 50], [5, 10, 25, 50]],
        "sScrollX": '100%',
        "columnDefs": [
            {"orderable": false, "targets": -1}
        ],

        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por página.",
            "zeroRecords": "No se encontró registro.",
            "info": "  _START_ de _END_ (_TOTAL_ registros totales).",
            "infoEmpty": "0 de 0 de 0 registros",
            "infoFiltered": "(Encontrado de _MAX_ registros)",
            "search": "Buscar en datos cargados: ",
            "processing": "Procesando la información",
            "paginate": {
                "first": " |< ",
                "previous": "Ant.",
                "next": "Sig.",
                "last": " >| "
            }
        }
    });

});

