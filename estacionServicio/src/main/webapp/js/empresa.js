/*
	Clases Empresa
	Atributos:
		Nombre
		Localidad
		Telefono
		Nombre del contacto
		Descuento

	escritura de las variables: camelCase
*/


var Empresa = function (nombre, localidad, nombreContacto, telefonoContacto, descuento, vencimiento){
	this.empresaId = 0;
	this.nombre = nombre;
	this.localidad = localidad;
	this.nombreContacto = nombreContacto;
	this.telefonoContacto = telefonoContacto;
	this.descuento = descuento;
	this.vencimiento = vencimiento;
}