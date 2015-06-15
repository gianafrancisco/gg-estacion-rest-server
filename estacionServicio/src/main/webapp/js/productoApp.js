
angular.module('ProductoApp', ['ngMaterial'])
.controller('ProductoCtrl', function($scope,$http) {

	$scope.productos = [];
	/*
	$scope.productos = [
		new Producto('remiseria','La Falda','1111111','Juan Pérez','15','01/01/15'),
		new Producto('municipalidad','Valle Hermoso','2222222','Ana Gómez','25','25/10/14'),
		new Producto('intendencia','La Granja','3333333','Luis Rodríguez','5','30/07/13'),
		new Producto('concesionaria','La Falda','4444444','María López','10','03/02/15'),
	];
	*/

	$scope.listadoProducto= function (index){
		$http.get('/listadoProducto').success(function(data, status, headers, config) {
			$scope.productos=data;
			$scope.selected=$scope.productos[index];
  		});
	};

	$scope.selecionarProducto = function(index){
		$scope.selectedIndex=index;		
		$scope.selected=$scope.productos[$scope.selectedIndex];
	};

	$scope.selectedIndex=0;
	$scope.listadoProducto($scope.selectedIndex);
	$scope.selecionarProducto($scope.selectedIndex);

	$scope.add = function(){
		$scope.productos[$scope.productos.length] = new Producto('Nuevo Producto',0);
		$scope.selecionarProducto($scope.productos.length-1);
	};

	$scope.save = function(){
		/*
			Aca va una peticion Rest
		*/
		$http.put('/agregarProducto',$scope.selected).success(function(data, status, headers, config) {
			$scope.listadoProducto($scope.selectedIndex);
  		});
	};

	$scope.delete = function(){
		if($scope.productos.length > 0){
			$http.put('/borrarProducto',$scope.selected).success(function(data, status, headers, config) {
				//$scope.usuarios.splice($scope.selectedIndex,1);
				if($scope.selectedIndex >= $scope.productos.length-1){
					$scope.selectedIndex = $scope.productos.length-1;
				}
				$scope.listadoProducto($scope.selectedIndex);
  			});
		}
	};

});