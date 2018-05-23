function loadImg() {
    var link = document.getElementById("txtThumbnail").value;
    if (link.indexOf("resources/img/") >= 0) {
        document.getElementById("imgThumbnail").src = link;
        document.getElementById("txtLink").value = link;
    } else {
        document.getElementById("imgThumbnail").src = "resources/img/" + link;
        document.getElementById("txtLink").value = "resources/img/" + link;
    }
}
