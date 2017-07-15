/**
 * Created by shuto on 15.07.2017.
 */
function showEditing(id) {
    document.getElementById("rowMain" + id).setAttribute("hidden", true);
    document.getElementById("rowEdit" + id).removeAttribute("hidden");
}

function hideEditing(id) {
    document.getElementById("rowMain" + id).removeAttribute("hidden");
    document.getElementById("rowEdit" + id).setAttribute("hidden", true);
}