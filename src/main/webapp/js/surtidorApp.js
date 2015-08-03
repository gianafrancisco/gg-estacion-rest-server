//angular.module('SurtidorApp', ['ngMaterial'])
//.controller('SurtidorCtrl', SurtidorCtrl);

function SurtidorCtrl ($scope,$http) {

	$scope.surtidores = [];
	$scope.flagNuevo = true;
	$scope.flagGrabar = false;
	$scope.flagBorrar = false;

	$scope.listadoSurtidor= function (index){
		$http.get('/listadoSurtidor').success(function(data, status, headers, config) {
			$scope.surtidores=data;
  		});
	};

	$scope.selecionarSurtidor = function(id){
		if($scope.flagNuevo){
			$scope.surtidores.forEach(function(element,index){
				if(element.surtidorId === id){
					$scope.selectedIndex=index;
					return;
				}
			});
			$scope.selected=$scope.surtidores[$scope.selectedIndex];
			$scope.flagBorrar = true;
			$scope.flagGrabar = true;
			$scope.flagNuevo = true;
		}
	};
	$scope.listadoSurtidor($scope.selectedIndex);
	$scope.add = function(){
		$scope.surtidores[$scope.surtidores.length] = new Surtidor(0,'Surtidor ');
		$scope.selecionarSurtidor(0);
		$scope.flagBorrar = true;
		$scope.flagGrabar = true;
		$scope.flagNuevo = false;
	};

	$scope.save = function(){
		$http.put('/agregarSurtidor',$scope.selected).success(function(data, status, headers, config) {
			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
			$scope.listadoSurtidor(0);
  		});
	};

	$scope.delete = function(){
		if($scope.surtidores.length > 0){
			if($scope.selected.surtidorId !== 0){
				$http.put('/borrarSurtidor',$scope.selected).success(function(data, status, headers, config) {
					$scope.listadoSurtidor($scope.selectedIndex);
				});
  			}else{
  				$scope.listadoSurtidor(0);
  			}
  			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
		}
	};
}