
angular.module('EmpresaApp', ['ngMaterial'])
.controller('EmpresaCtrl', function($scope,$http) {

	$scope.empresas = [];
	/*
	$scope.empresas = [
		new Empresa('remiseria','La Falda','1111111','Juan Pérez','15','01/01/15'),
		new Empresa('municipalidad','Valle Hermoso','2222222','Ana Gómez','25','25/10/14'),
		new Empresa('intendencia','La Granja','3333333','Luis Rodríguez','5','30/07/13'),
		new Empresa('concesionaria','La Falda','4444444','María López','10','03/02/15'),
	];
	*/

	$scope.listadoEmpresa= function (index){
		$http.get('/listadoEmpresa').success(function(data, status, headers, config) {
			$scope.empresas=data;
			$scope.selected=$scope.empresas[index];
  		});
	};

	$scope.selecionarEmpresa = function(index){
		$scope.selectedIndex=index;		
		$scope.selected=$scope.empresas[$scope.selectedIndex];
	};

	$scope.selectedIndex=0;
	$scope.listadoEmpresa($scope.selectedIndex);
	$scope.selecionarEmpresa($scope.selectedIndex);

	$scope.add = function(){
		$scope.empresas[$scope.empresas.length] = new Empresa('Nuevo Cliente','','','');
		$scope.selecionarEmpresa($scope.empresas.length-1);
	};

	$scope.save = function(){
		/*
			Aca va una peticion Rest
		*/
		$http.put('/agregarEmpresa',$scope.selected).success(function(data, status, headers, config) {
			$scope.listadoEmpresa($scope.selectedIndex);
  		});
	};

	$scope.delete = function(){
		if($scope.empresas.length > 0){
			$http.put('/borrarEmpresa',$scope.selected).success(function(data, status, headers, config) {
				//$scope.usuarios.splice($scope.selectedIndex,1);
				if($scope.selectedIndex >= $scope.empresas.length-1){
					$scope.selectedIndex = $scope.empresas.length-1;
				}
				$scope.listadoEmpresa($scope.selectedIndex);
  			});
		}
	};

});