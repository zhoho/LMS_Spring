// script.js

document.querySelector('#myForm')
    .addEventListener(
        'submit',
        () => {
            document.querySelector('#myForm button')
                .classList.add('hidden');
        });