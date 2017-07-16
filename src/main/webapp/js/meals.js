function showEditing(id) {
    document.getElementById("id").value = id;
    document.getElementById("dateTime").value =
        document.getElementById("Id" + id + "dateTime").innerHTML.replace(" ", "T");
    document.getElementById("description").value = document.getElementById("Id" + id + "description").innerHTML;
    document.getElementById("calories").value = document.getElementById("Id" + id + "calories").innerHTML;

    document.getElementById("editingForm").classList.remove("hidden");
}

function hideEditing() {
    document.getElementById("editingForm").classList.add("hidden");
}