const loginUrlButton = document.getElementById('login-url-btn');

if (loginUrlButton) {
    loginUrlButton.addEventListener('click', event => {
        location.replace('/login');
    });
}