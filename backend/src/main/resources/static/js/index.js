document.addEventListener('DOMContentLoaded', function() {
    const logoutForm = document.querySelector('.logout-form');

    logoutForm.addEventListener('submit', function(event) {
        const confirmed = confirm('Are you sure you want to logout?');
        if (!confirmed) {
            event.preventDefault();
        }
    });
});