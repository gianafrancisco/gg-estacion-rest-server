//angular.module('UsuarioApp', ['ngMaterial'])
//.controller('UsuarioCtrl', UsuarioCtrl);

function UsuarioCtrl($scope,$http) {

	$scope.usuarios = [];
	$scope.permisos = [{id: 'Administrador'},{id:'Playero'}];
	$scope.flagNuevo = true;
	$scope.flagGrabar = false;
	$scope.flagBorrar = false;

	$scope.listadoUsuario= function (index){
		$http.get('/listadoUsuario').success(function(data, status, headers, config) {
			$scope.usuarios=data;
  		});
	};

	$scope.selecionarUsuario = function(id){
		if($scope.flagNuevo){
			$scope.usuarios.forEach(function(element,index){
				if(element.usuarioId === id){
					$scope.selectedIndex=index;
					return;
				}
			});

			$scope.selected=$scope.usuarios[$scope.selectedIndex];
			//$scope.permisosSeleccionado = $scope.selected.permisos;
			$scope.flagBorrar = true;
			$scope.flagGrabar = true;
			$scope.flagNuevo = true;
        }
	};

	$scope.selectedIndex=0;
	$scope.listadoUsuario($scope.selectedIndex);

	$scope.add = function(){

		$scope.usuarios[$scope.usuarios.length] = new Usuario('Nuevo Usuario','','','','','Playero');
		$scope.selecionarUsuario(0);
		$scope.flagBorrar = true;
		$scope.flagGrabar = true;
		$scope.flagNuevo = false;
	};

	$scope.save = function(){
		//$scope.selected.permisos = $scope.permisosSeleccionado;
		$http.put('/agregarUsuario',$scope.selected).success(function(data, status, headers, config) {
			$scope.listadoUsuario(0);
			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
  		});

	};

	$scope.delete = function(){
		if($scope.usuarios.length > 0){
			if($scope.selected.usuarioId !== 0){
				$http.put('/borrarUsuario',$scope.selected).success(function(data, status, headers, config) {
					$scope.listadoUsuario(0);
				});
			}else{
				$scope.listadoUsuario(0);
			}
  			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
		}
	};
}