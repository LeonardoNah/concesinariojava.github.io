document.addEventListener('DOMContentLoaded', cargarUsuarios);

//--Crear modal
let modificarModal;

document.addEventListener('DOMContentLoaded',function(){
    modificarModal= new bootstrap.Modal(document.getElementById('modificarModal'))
    cargarUsuarios();
    
});

// ----------------------------------------- //
// -- Cargar usuarios -- //
function cargarUsuarios() {

    fetch('/proyectoJava_24112/GestionUsuarioServlet')
        .then(response => response.json())
        .then(usuarios => {
            //console.log(usuarios)
            const tbody = document.querySelector('#usuariosTable tbody');
            tbody.innerHTML = '';
            usuarios.forEach(usuario => {
                tbody.innerHTML += `
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.correo}</td>
                        <td>${usuario.password}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="mostrarModificarModal(${usuario.id})">Modificar</button>
                            <button class="btn btn-danger btn-sm" onclick="eliminarUsuario(${usuario.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
            });
        })
}

//---------------------------------------------
//-- Mostrar  y modificar el model--

function mostrarModificarModal(id){
    fetch(`/proyectoJava_24112/GestionUsuarioServlet?id=${id}`)
            .then(response => response.json())
            .then(usuario =>{
                document.getElementById("userId").value = usuario.id;
                document.getElementById("correo").value = usuario.correo;
                document.getElementById("password").value = usuario.password;
                
                modificarModal.show();
    })
            .catch(error => console.error('Error: ' , error));
}


function guardarModificacion() {
    const usuario = {
        id: document.getElementById('userId').value,
        correo: document.getElementById('correo').value,
        password: document.getElementById('password').value,
    };

    fetch('/proyectoJava_24112/GestionUsuarioServlet', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
        //método en JavaScript que convierte un objeto JavaScript en una cadena JSON.
    })
    .then(response => response.json())
    .then(data => {
        console.log(data.exito);
        if (data.exito) {
            modificarModal.hide();
            cargarUsuarios();
        } else {
            alert('Error al modificar el usuario');
        }
    })
    .catch(error => console.error('Error:', error));
}

// -----------------------------------------------------
// -- Eliminar usuario -- //

function eliminarUsuario(id) {
    //función que muestra un cuadro de diálogo con un mensaje y botones "Aceptar" y "Cancelar"
    //Devuelve true si el usuario hace clic en "Aceptar" y false si hace clic en "Cancelar".
    if (confirm('¿Está seguro de que desea eliminar este usuario?')) {
        fetch(`/proyectoJava_24112/GestionUsuarioServlet?id=${id}`, { method: 'DELETE' })
            .then(response => response.json())
            .then(data => {
                console.log(data.exito);
                if (data.exito) {
                    cargarUsuarios();
                } else {
                    alert('Error al eliminar el usuario');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}