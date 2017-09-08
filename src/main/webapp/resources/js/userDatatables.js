var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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
                "asc"
            ]
        ]
    });
    makeEditable();

    $(".enable-checkbox").change(function () {
        var checkbox = $(this);
        var tr = checkbox.parent().parent();
        $.ajax({
            type: "POST",
            url: ajaxUrl + tr.attr("id"),
            data: "enabled=" + checkbox.is(':checked'),
            success: function () {
                successNoty("Saved");
                tr.attr("class", checkbox.is(':checked') ? "" : "disabled");
            }
        });
    });
});