/*
	Clases Usuario
	Atributos:
		Nombre
		Apellido
		Nombre Usuario
		Clave
		Telefono
		Permisos

	escritura de las variables: camelCase
*/


var Usuario = function (nombre, apellido, nombreUsuario, clave, permisos){
	this.usuarioId = 0;
	this.nombre = nombre;
	this.apellido = apellido;
	this.nombreUsuario = nombreUsuario;
	this.clave = clave;
	//this.telefono = telefono;
	this.permisos = permisos;
}