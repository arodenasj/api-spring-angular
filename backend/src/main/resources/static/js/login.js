function validateForm() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    if (username === '' || password === '') {
        alert('Both fields are required.');
        return false;
    }
    return true;
}

function checkPasswordStrength() {
    const password = document.getElementById('password').value;
    const strengthText = document.getElementById('password-strength-text');
    const strength = getPasswordStrength(password);

    strengthText.textContent = strength.message;
    strengthText.className = 'password-strength ' + strength.class;
}

function getPasswordStrength(password) {
    let strength = { message: 'Weak', class: 'weak' };
    if (password.length >= 8) {
        strength = { message: 'Medium', class: 'medium' };
    }
    if (password.length >= 8 && /[A-Z]/.test(password) && /[0-9]/.test(password) && /[!@#\$%\^&\*]/.test(password)) {
        strength = { message: 'Strong', class: 'strong' };
    }
    return strength;
}