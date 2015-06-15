angular.module('SurtidorApp', ['ngMaterial'])
.controller('SurtidorCtrl', function($scope,$http) {


	$scope.surtidores = [];
	$scope.vehiculo = [];
	$scope.flags = { nuevaVenta: false };
	$scope.puntos = 500;

	/*
	$scope.surtidores = [
		new Surtidor('s1','surtidor 1'),
		new Surtidor('s2','surtidor 2'),
		new Surtidor('s3','surtidor 3'),
		new Surtidor('s4','surtidor 4'),
	];
	*/

	$scope.listadoSurtidor= function (index){
		$http.get('/listadoSurtidor').success(function(data, status, headers, config) {
			$scope.surtidores=data;
			$scope.selected=$scope.surtidores[index];
  		});
	};

	$scope.selecionarSurtidor = function(index){
		$scope.selectedIndex=index;		
		$scope.selected=$scope.surtidores[$scope.selectedIndex];
		//$scope.selected["puntos"] = 500;
	};

	$scope.buscarVehiculo = function (){
		$http.get('/leerTarjeta').success(function(data, status, headers, config) {
			$scope.vehiculo = data[0];
  		});	
	}

	$scope.selectedIndex=0;
	$scope.listadoSurtidor($scope.selectedIndex);
	$scope.selecionarSurtidor($scope.selectedIndex);

	$scope.add = function(){
		$scope.flags.nuevaVenta = true;
	};

	$scope.save = function(){
		if($scope.flags.nuevaVenta){

			var venta = new Venta($scope.selected,$scope.vehiculo,new Usuario("Franciso","Giana","fgiana","","Playero"));
			venta.vehiculoId = $scope.vehiculo.vehiculoId;			
			venta.surtidorId = $scope.selected.surtidorId;
			venta.puntos = $scope.selected.puntos;
			$http.post('/agregarRegistro',venta).success(function(data, status, headers, config) {
				console.log(data);
	  		});
			$scope.flags.nuevaVenta = false;
		}
	};

	$scope.cancelar = function(){
		if($scope.flags.nuevaVenta){
			$scope.flags.nuevaVenta = false;	
		}
	};
});