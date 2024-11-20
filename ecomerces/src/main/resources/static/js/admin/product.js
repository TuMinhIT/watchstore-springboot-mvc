
    function previewImage(event) {
    const file = event.target.files[0];
    const preview = document.getElementById('Preview');

    if (file) {
    const reader = new FileReader();
    reader.onload = function (e) {
    preview.src = e.target.result;
    preview.style.display = 'block';
};
    reader.readAsDataURL(file);
} else {
    preview.src = "";
    preview.style.display = 'none';
}
}

    document.getElementById('close-btn').addEventListener('click', function () {
    var modal = document.querySelector('.modal-edit');

    // Hide the modal by setting display to 'none'
    modal.style.display = 'none';
});


    function editPreviewImage(event) {
    const fileInput = event.target;
    const file = fileInput.files[0];
    const preview = document.getElementById("editPreview");

    if (file) {
    const reader = new FileReader();
    reader.onload = function(e) {
    preview.src = e.target.result;
};
    reader.readAsDataURL(file);
}
}
