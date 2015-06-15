angular.module('UsuarioApp', ['ngMaterial'])
.controller('UsuarioCtrl', function($scope,$http) {

	$scope.usuarios = [];


	$scope.listadoUsuario= function (index){
		$http.get('/listadoUsuario').success(function(data, status, headers, config) {
			$scope.usuarios=data;
			$scope.selected=$scope.usuarios[index];
  		});
	};

	$scope.selecionarUsuario = function(index){
		$scope.selectedIndex=index;		
		$scope.selected=$scope.usuarios[$scope.selectedIndex];
	};

	$scope.selectedIndex=0;
	$scope.listadoUsuario($scope.selectedIndex);
	$scope.selecionarUsuario($scope.selectedIndex);

	$scope.add = function(){
		$scope.usuarios[$scope.usuarios.length] = new Usuario('Nuevo Usuario','','','','','playero');
		$scope.selecionarUsuario($scope.usuarios.length-1);
	};

	$scope.save = function(){
		/*
			Aca va una peticion Rest
		*/
		$http.put('/agregarUsuario',$scope.selected).success(function(data, status, headers, config) {
			$scope.listadoUsuario($scope.selectedIndex);
  		});
		
	};

	$scope.delete = function(){
		if($scope.usuarios.length > 0){
			$http.put('/borrarUsuario',$scope.selected).success(function(data, status, headers, config) {
				//$scope.usuarios.splice($scope.selectedIndex,1);
				if($scope.selectedIndex >= $scope.usuarios.length-1){
					$scope.selectedIndex = $scope.usuarios.length-1;
				}
				$scope.listadoUsuario($scope.selectedIndex);
  			});
		}
	};
});