angular.module('SurtidorApp', ['ngMaterial'])
.controller('SurtidorCtrl', function($scope,$http) {


	$scope.surtidores = [];
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
	};

	$scope.selectedIndex=0;
	$scope.listadoSurtidor($scope.selectedIndex);
	$scope.selecionarSurtidor($scope.selectedIndex);

	$scope.add = function(){
		$scope.surtidores[$scope.surtidores.length] = new Surtidor(0,'Surtidor ');
		$scope.selecionarSurtidor($scope.surtidores.length-1);
	};

	$scope.save = function(){
		/*
			Aca va una peticion Rest
		*/
		$http.put('/agregarSurtidor',$scope.selected).success(function(data, status, headers, config) {
			$scope.listadoSurtidor($scope.selectedIndex);
  		});
	};

	$scope.delete = function(){
		if($scope.surtidores.length > 0){
			$http.put('/borrarSurtidor',$scope.selected).success(function(data, status, headers, config) {
				$scope.surtidores.splice($scope.selectedIndex,1);
				if($scope.selectedIndex >= $scope.surtidores.length-1){
					$scope.selectedIndex = $scope.surtidores.length-1;
				}
				$scope.listadoSurtidor($scope.selectedIndex);
  			});
		}
	};
});