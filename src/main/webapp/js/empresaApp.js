//angular.module('EmpresaApp', ['ngMaterial'])
//.controller('EmpresaCtrl', EmpresaCtrl);

function EmpresaCtrl($scope,$http) {

	$scope.empresas = [];
	$scope.flagNuevo = true;
    $scope.flagGrabar = false;
    $scope.flagBorrar = false;

	$scope.listadoEmpresa= function (index){
		$http.get('/listadoEmpresa').success(function(data, status, headers, config) {
			$scope.empresas=data;
			//$scope.selected=$scope.empresas[index];
  		});
	};

	$scope.selecionarEmpresa = function(id){
		if($scope.flagNuevo){
			$scope.empresas.forEach(function(element,index){
				if(element.empresaId === id){
					$scope.selectedIndex=index;
					return;
				}
			});
			$scope.selected=$scope.empresas[$scope.selectedIndex];
			$scope.flagBorrar = true;
			$scope.flagGrabar = true;
			$scope.flagNuevo = true;
		}
	};

	$scope.selectedIndex=0;
	$scope.listadoEmpresa($scope.selectedIndex);

	$scope.add = function(){
		$scope.empresas[$scope.empresas.length] = new Empresa('Nuevo Cliente','','','');
		$scope.selecionarEmpresa(0);
		$scope.flagBorrar = true;
    	$scope.flagGrabar = true;
    	$scope.flagNuevo = false;
	};

	$scope.save = function(){
		/*
			Aca va una peticion Rest
		*/
		$http.put('/agregarEmpresa',$scope.selected).success(function(data, status, headers, config) {
			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
			$scope.listadoEmpresa(0);
  		});
	};

	$scope.delete = function(){
		if($scope.empresas.length > 0){
			if($scope.selected.empresaId !== 0){
				$http.put('/borrarEmpresa',$scope.selected).success(function(data, status, headers, config) {
					$scope.listadoEmpresa(0);
  				});
  			}else{
  				$scope.listadoEmpresa(0);
			}
			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
		}
	};

}