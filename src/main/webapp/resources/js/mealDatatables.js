var ajaxUrl = "ajax/meals/";
var datatableApi;
var filterParams;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();

    $("#dateTimeFilter").submit(function () {
        filterParams = $(this).serialize();
        updateTable();
        return false;
    });
    $("#resetFilter").click(function () {
        filterParams = undefined;
        updateTable();
    });
});

