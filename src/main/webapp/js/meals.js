var HIDDEN_STR = "hidden";

function showEditing(id) {
    document.getElementById("rowMain" + id).setAttribute(HIDDEN_STR, HIDDEN_STR);
    document.getElementById("rowEdit" + id).removeAttribute(HIDDEN_STR);
}

function hideEditing(id) {
    document.getElementById("rowMain" + id).removeAttribute(HIDDEN_STR);
    document.getElementById("rowEdit" + id).setAttribute(HIDDEN_STR, HIDDEN_STR);
}