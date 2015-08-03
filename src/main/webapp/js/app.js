var app = angular.module('MainWindow', ['ngMaterial','ngRoute','ui.bootstrap']).
    config(function($routeProvider, $httpProvider) {

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
            }).when('/login', {
                 templateUrl: 'login.html',
                 controller: 'MainWindowCtrl'
            }).when('/reporte', {
                               templateUrl: 'reporte.html',
                               controller: 'ReporteCtrl'
                          })
            .otherwise({
                 redirectTo: '/login'
               });
           $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
           }
);

app.controller('MainWindowCtrl', function($rootScope, $scope, $http, $location, $route) {

	$scope.tabs = [
		new Tab('Surtidores',false,"surtidor"),
		new Tab('Productos',false,"producto"),
		new Tab('Clientes',false,"empresa"),
		new Tab('Vehiculos',false,"vehiculo"),
		new Tab('Usuarios',false,"usuario"),
		new Tab('Reportes',false,"reporte")
	];

	$scope.openTab = function(tab){
	    $location.path(tab.url);
	};

var authenticate = function(credentials, callback) {
				var headers = credentials ? {
					authorization : "Basic "
							+ btoa(credentials.username + ":"
									+ credentials.password)
				} : {};
				$http.get('/user', {
					headers : headers
				}).success(function(data) {
					if (data.name) {
						$rootScope.authenticated = true;
					} else {
						$rootScope.authenticated = false;
					}
					callback && callback($rootScope.authenticated);
				}).error(function() {
					$rootScope.authenticated = false;
					callback && callback(false);
				});

			}
			$scope.verificarEstado =  function(authenticated) {
                if (authenticated) {
                    $location.path("/surtidor");
                    $scope.error = false;
                    $rootScope.authenticated = true;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                }
            }
            $scope.verificarEstadoInicial =  function(authenticated) {
                if (authenticated) {
                    $location.path("/surtidor");
                    $rootScope.authenticated = true;
                } else {
                    $location.path("/login");
                    $rootScope.authenticated = false;
                }
            }


			authenticate($scope.credentials,$scope.verificarEstadoInicial);

			$scope.credentials = {};
			$scope.login = function() {
				authenticate($scope.credentials,$scope.verificarEstado)
			};

			$scope.logout = function() {
				$http.post('/logout', {}).success(function() {
					$rootScope.authenticated = false;
					$location.path("/login");
				}).error(function(data) {
					$rootScope.authenticated = false;
				});
			}


})
.controller('SurtidorCtrl',SurtidorCtrl).controller('ProductoCtrl',ProductoCtrl)
.controller('VehiculoCtrl',VehiculoCtrl).controller('EmpresaCtrl',EmpresaCtrl)
.controller('UsuarioCtrl',UsuarioCtrl).controller('ReporteCtrl',ReporteCtrl);