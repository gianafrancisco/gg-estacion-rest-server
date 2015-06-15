/*
	Clase Vehiculo
	Dominio,
	Dominio Municipal,
	Marca,
	Color,
	Empresa ID,
	Modelo,
	Tarjeta ID

	escritura de las variables: camelCase
*/


var Vehiculo = function (dominio, dominioMunicipal, marca, color, empresaId, modelo, tarjetaId){
	this.vehiculoId = 0;
	this.dominio = dominio;
	this.dominioMunicipal = dominioMunicipal;
	this.marca = marca;
	this.color = color;
	this.empresaId = empresaId;
	this.modelo = modelo;
	this.tarjetaId = tarjetaId;
}