function loadImg() {
    document.getElementById("imgThumbnail").src = "resources/img/" + document.getElementById("txtThumbnail").value;
    document.getElementById("txtLink").value = "resources/img/" + document.getElementById("txtThumbnail").value;
}
