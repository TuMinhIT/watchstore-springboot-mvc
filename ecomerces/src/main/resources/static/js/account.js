function showSection(sectionId) {
    document.getElementById('account-info').classList.add('hidden');
    document.getElementById('change-password').classList.add('hidden');
    document.getElementById('address-book').classList.add('hidden');
    document.getElementById(sectionId).classList.remove('hidden');
}

document.querySelectorAll('.nav-link').forEach(link => {
    link.addEventListener('click', function (event) {

        document.querySelectorAll('.nav-link').forEach(link => link.classList.remove('selected'));

        this.classList.add('selected');

        const sectionId = this.getAttribute('data-section');
        console.log('Showing section:', sectionId);
    });

});

function validateForm() {
    var addressInput = document.getElementById("new-address").value.trim();
    var errorMessage = document.getElementById("error-message");

    if (addressInput === "") {
        errorMessage.style.display = "block";
        return false;
    } else {
        errorMessage.style.display = "none";
        return true;
    }
}

function validatePasswords() {
    const newPassword = document.getElementById("new-password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const errorMessage = document.getElementById("error");

    if (newPassword !== confirmPassword || newPassword.trim().length <= 0) {
        errorMessage.style.display = "block";
        errorMessage.textContent = "Mật khẩu không khớp";
        return false; // Ngăn form gửi đi
    }
    errorMessage.style.display = "none";
    return true;
}

// Function to hide messages after 5 seconds
function hideMessages() {
    const successMessage = document.getElementById('success-message');
    const errorMessage = document.getElementById('message-error');

    // Hide success message if it exists
    if (successMessage) {
        setTimeout(() => {
            successMessage.style.display = 'none';
        }, 5000);
    }

    // Hide error message if it exists
    if (errorMessage) {
        setTimeout(() => {
            errorMessage.style.display = 'none';
        }, 5000);
    }
}

// Call the function after the DOM is fully loaded
document.addEventListener('DOMContentLoaded', hideMessages);
