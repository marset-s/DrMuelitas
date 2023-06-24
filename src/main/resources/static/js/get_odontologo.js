window.addEventListener('load', function () {

    (function(){
      //con fetch invocamos a la API de odontólogos con el método GET
      //nos devolverá un JSON con una colección de odontólogos
      const url = '/odontologos/';
      const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
         //recorremos la colección de odontólogos del JSON
         for(student of data){
          //por cada odontólogo armaremos una fila de la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos
          //el odontólogo

          var table = document.getElementById("studentTable");
          var studentRow =table.insertRow();
          let tr_id = 'tr_' + student.id;
          studentRow.id = tr_id;


          //por cada odontólogo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
          //dicho boton invocara a la funcion de java script deleteByKey que se encargará
          //de llamar a la API para eliminar al odontólogo
           let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + student.id + '\"' +
                                      ' type="button" onclick="deleteBy('+student.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

           //por cada estudiante creamos un boton que muestra el id y que al hacerle clic invocará
           //a la función de java script findBy que se encargará de buscar al estudiante que queremos
           //modificar y mostrar los datos del mismo en un formulario.
          let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + student.id + '\"' +
                                      ' type="button" onclick="findBy('+student.id+')" class="btn btn-info btn_id">' +
                                      student.id +
                                      '</button>';


          //armamos cada columna de la fila
          //como primer columna pondremos el boton modificar
          //luego los datos del estudiante
          //como ultima columna el boton eliminar
         studentRow.innerHTML = '<td>' + updateButton + '</td>' +
                              '<td class=\"td_first_name\">' + student.name.toUpperCase() + '</td>' +
                              '<td class=\"td_last_name\">' + student.lastname.toUpperCase() + '</td>' +
                              '<td>' + deleteButton + '</td>';

        };

})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/listadoOdontologos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})


})