/*
	Venta
	Atributos:
		vehiculoId
		surtidorId
		puntos
	escritura de las variables: camelCase
*/


var Venta = function (surtidor,vehiculo,usuario, puntos){
	this.registroId = 0;
	this.usuarioId = usuario.usuarioId;
	this.nombreUsuario = usuario.nombreUsuario;
	this.nombre = usuario.nombre;
	this.apellido = usuario.apellido;
	this.permisos = usuario.permisos;
	this.surtidorId = surtidor.surtidorId;
	this.descripcionSurtidor = surtidor.descripcion;
	this.direccionNodo = parseInt(surtidor.direccionNodo);
	this.productoId = 0;
	this.descripcionProducto = "";
	this.puntosCambiados = 0;
	this.empresaId = vehiculo.empresaId;
	this.nombreEmpresa = "";
	this.descuento = 10.5;
	this.vencimiento = 0;
	this.vehiculoId = vehiculo.vehiculoId;
	this.dominio = vehiculo.dominio;
	this.dominioMunicipal = vehiculo.dominioMunicipal;
	this.puntos = puntos;
	this.accion = "VENTA";
}