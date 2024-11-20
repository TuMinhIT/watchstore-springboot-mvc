//lấy sự kiện nhấp vao thẻ a
const links = document.querySelectorAll('a');
const currentUrl = window.location.pathname;
links.forEach(link => {
    if (link.href.includes(currentUrl)) {
        link.classList.add('active');
    }
    link.addEventListener('click', function() {
        links.forEach(item => item.classList.remove('active'));
        this.classList.add('active');
    });
});



//điều hướng title
const dashboardTitle = document.getElementById('dashboard-title');
dashboardTitle.addEventListener('click', function() {
    window.location.href = '/admin/home';
});

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
