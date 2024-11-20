window.addEventListener('DOMContentLoaded', function () {
    var successMessage = document.getElementById('success');
    var alertDiv = successMessage.closest('.alert-add-to-cart');

    if (successMessage) {
        setTimeout(function () {
            alertDiv.style.display = 'none';
        }, 5000);
    }
});