//angular.module('ProductoApp', ['ngMaterial'])
//.controller('ProductoCtrl', ProductoCtrl);

function ProductoCtrl($scope,$http) {

	$scope.productos = [];
	$scope.flagNuevo = true;
	$scope.flagGrabar = false;
	$scope.flagBorrar = false;

	$scope.listadoProducto= function (index){
		$http.get('/listadoProducto').success(function(data, status, headers, config) {
			$scope.productos=data;
  		});
	};

	$scope.selecionarProducto = function(id){
		if($scope.flagNuevo){
			$scope.productos.forEach(function(element,index){
				if(element.productoId === id){
					$scope.selectedIndex=index;
					return;
				}
			});
			$scope.selected=$scope.productos[$scope.selectedIndex];
			$scope.flagBorrar = true;
			$scope.flagGrabar = true;
			$scope.flagNuevo = true;
		}

	};

	$scope.selectedIndex=0;
	$scope.listadoProducto($scope.selectedIndex);

	$scope.add = function(){
		$scope.productos[$scope.productos.length] = new Producto('Nuevo Producto',0);
		$scope.selecionarProducto(0);
		$scope.flagBorrar = true;
    	$scope.flagGrabar = true;
    	$scope.flagNuevo = false;
	};

	$scope.save = function(){
		/*
			Aca va una peticion Rest
		*/
		$http.put('/agregarProducto',$scope.selected).success(function(data, status, headers, config) {
			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
			$scope.listadoProducto($scope.selectedIndex);
  		});
	};

	$scope.delete = function(){
		if($scope.productos.length > 0){
			if($scope.selected.productoId !== 0){
				$http.put('/borrarProducto',$scope.selected).success(function(data, status, headers, config) {
					$scope.listadoProducto(0);
				});
  			}else{
  				$scope.listadoProducto(0);
  			}
			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
		}
	};

}