var app = angular.module('MainWindow', ['ngMaterial','ngRoute']).
    config(['$routeProvider',
           function($routeProvider) {
             $routeProvider.
               when('/surtidor', {
                 templateUrl: 'surtidor.html',
                 controller: 'SurtidorCtrl'
               }).
              when('/producto', {
                templateUrl: 'producto.html',
                controller: 'ProductoCtrl'
              }).
              when('/vehiculo', {
                templateUrl: 'vehiculo.html',
                controller: 'VehiculoCtrl'
              }).
              when('/usuario', {
                templateUrl: 'usuario.html',
                controller: 'UsuarioCtrl'
              }).
            when('/empresa', {
              templateUrl: 'empresa.html',
              controller: 'EmpresaCtrl'
            }).

               otherwise({
                 redirectTo: '/surtidor'
               });
           }]);

app.controller('MainWindowCtrl', function($scope,$location) {

	$scope.tabs = [
		new Tab('Surtidores',false,"surtidor"),
		new Tab('Productos',false,"producto"),
		new Tab('Clientes',false,"empresa"),
		new Tab('Vehiculos',false,"vehiculo"),
		new Tab('Usuarios',false,"usuario"),
		new Tab('Reportes',true,"reporte")
	];

	$scope.openTab = function(tab){
	    $location.path(tab.url);
	};

})
.controller('SurtidorCtrl',SurtidorCtrl).controller('ProductoCtrl',ProductoCtrl)
.controller('VehiculoCtrl',VehiculoCtrl).controller('EmpresaCtrl',EmpresaCtrl)
.controller('UsuarioCtrl',UsuarioCtrl);
