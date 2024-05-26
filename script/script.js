// Capturar Botones

const btnLogin = document.querySelector('#btnLogin');
btnLogin.addEventListener('click',activarFormulario);

function activarFormulario(){
    const mostrarLogin = document.querySelector('.form');

    mostrarLogin.classList.toggle('ocultar');

}

// ------------LOGIN-------------
// obtener el formulario
const formulario = document.querySelector('.form');

// agregar un evento de escucha para cuando se envie el formulario
formulario.addEventListener('submit',evento =>{

    // cancelar envio de datos por defecto
    evento.defaultPrevented();

    // valiar los campos del formulario
    if (validarFomulario()){
        // si todos los campos son validos, enviar el formulario
        formulario.submit();

    }


});

// funcion para mostrar mensaje de error
function mostrarError(campo,mensaje){
    const campoError = document.getElementById(`${campo}-error`);
    campoError.innerText = mensaje;
}


// funcion para validar correo electronico