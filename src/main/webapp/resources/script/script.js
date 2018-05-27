function loadImg() {
    var selectedFile = event.target.files[0];
    var reader = new FileReader();
    var imgtag = document.getElementById("previewimg");
    imgtag.title = selectedFile.name;
    reader.onload = function (event) {
        imgtag.src = event.target.result;
    };
    reader.readAsDataURL(selectedFile);
}