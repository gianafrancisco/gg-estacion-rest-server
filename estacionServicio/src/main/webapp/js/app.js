angular.module('MainWindow', ['ngMaterial'])
.controller('MainWindowCtrl', function($scope) {

	$scope.tabs = [
		new Tab('Surtidores',false,"/surtidor.html"),
		new Tab('Productos',false,"/producto.html"),
		new Tab('Clientes',false,"/empresa.html"),
		new Tab('Vehiculos',false,"/vehiculo.html"),
		new Tab('Usuarios',false,"/usuario.html"),
		new Tab('Reportes',true,"/reporte.html")
	];

});