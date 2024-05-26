// Capturar Botones

const btnLogin = document.querySelector('#btnLogin');
btnLogin.addEventListener('click',activarFormulario);

function activarFormulario(){
    const mostrarLogin = document.querySelector('.form');

    mostrarLogin.classList.toggle('mostrar');

}

// ------------LOGIN-------------
// obtener el formulario
const formulario = document.querySelector('.form');

// agregar un evento de escucha para cuando se envie el formulario
formulario.addEventListener('submit',evento =>{

    // cancelar envio de datos por defecto
    evento.preventDefault();

    // valiar los campos del formulario
    if (validarFomulario()){
        // si todos los campos son validos, enviar el formulario
        formulario.submit();

    }
});

function validarFomulario(){

    //obtener los valores de los campos
    const email = document.getElementById('exampleInputEmail1').value ;
    const password = document.getElementById('exampleInputPassword1').value ;

    if(email === ''){
        mostrarError('email','Por favor ingrese el mail')
        return false;
    }

    if(password === ''){
        mostrarError('password','Por favor ingrese la contraseña')
        return false;
    }

    // if(!validarEmail(email)){
    //     mostrarError('email','Por favor ingresa un correo electronico valido')
    //     return false
    // }

    return true;
}

// funcion para mostrar mensaje de error
function mostrarError(campo,mensaje){
    const campoError = document.getElementById(`${campo}-error`);
    campoError.innerText = mensaje;
}


// funcion para validar correo electronico utilizando una expresion regular
function validarEmail(email){
    const expresionRegular = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return expresionRegular.test(email);

}

