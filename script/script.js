// Capturar Botones

const btnLogin = document.querySelector('#btnLogin');
btnLogin.addEventListener('click',activarFormulario);

function activarFormulario(){
    const mostrarLogin = document.querySelector('.form');

    mostrarLogin.classList.toggle('ocultar');

}