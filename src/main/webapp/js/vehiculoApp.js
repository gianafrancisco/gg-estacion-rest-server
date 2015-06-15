//angular.module('VehiculoApp', ['ngMaterial'])
//.controller('VehiculoCtrl', VehiculoCtrl);

function VehiculoCtrl($scope,$http,$log) {

	$scope.vehiculos = [];
	$scope.empresas = [];
/*
	$scope.empresas = [
		new Empresa('remiseria','La Falda','Juan Pérez','12345','15','60'),
		new Empresa('municipalidad','Valle Hermoso','Ana Gómez','2222222','25','30'),
		new Empresa('intendencia','La Granja','3333333','Luis Rodríguez','5','120'),
		new Empresa('concesionaria','La Falda','María López','4444444','10','70'),
	];

	$scope.empresas[0].empresaId = 1;
	$scope.empresas[1].empresaId = 2;
	$scope.empresas[2].empresaId = 3;
	$scope.empresas[3].empresaId = 4;


	$scope.vehiculos = [
		new Vehiculo('221','xxx','fiat','rojo',1,'Palio','111122223333'),
		new Vehiculo('222','xxx','fiat','rojo',2,'Palio','111122223333'),
		new Vehiculo('223','xxx','fiat','rojo',3,'Palio','111122223333'),
		new Vehiculo('224','xxx','fiat','rojo',4,'Palio','111122223333'),
	];
*/

	$scope.listadoEmpresa= function (index){
		$http.get('/listadoEmpresa').success(function(data, status, headers, config) {
			$scope.empresas=data;
  		});
	};

	$scope.listadoVehiculo= function (index){
		$http.get('/listadoVehiculo').success(function(data, status, headers, config) {
			//console.log("got data "+data);
			$scope.vehiculos=data;
			$scope.selected=$scope.vehiculos[index];
			$scope.selecionarVehiculo($scope.selectedIndex);
			var i=0;
			for(i=0;i<$scope.vehiculos.length;i++){
				console.log("vehiculoId: "+$scope.vehiculos[i].vehiculoId);
			}
  		});
	};

	$scope.selecionarVehiculo = function(index){
		$scope.selectedIndex=index;
		$scope.selected=$scope.vehiculos[$scope.selectedIndex];
		$scope.empresaSeleccionada = $scope.selected.empresaId;
		console.log("vehiculoId: "+$scope.selected.vehiculoId);
		//$scope.empresaSeleccionada = $scope.buscarEmpresa($scope.selected.empresaId);
	};
/*
	$scope.buscarEmpresa = function(empresaId){
		var i=0
		for(;i<$scope.empresas.length;i++){
			if($scope.empresas[i].empresaId === empresaId){
				console.log($scope.empresas[i].nombre);
				return $scope.empresas[i];
			}
		}
		return $scope.empresas[0].empresaId;
	}
*/
	$scope.selectedIndex=0;
	$scope.listadoEmpresa(0);
	$scope.listadoVehiculo($scope.selectedIndex);

	$scope.add = function(){
		$scope.vehiculos[$scope.vehiculos.length] = new Vehiculo('MMM111','','','','','');
		$scope.selecionarVehiculo($scope.vehiculos.length-1);
	};

	$scope.save = function(){
		/*
			Aca va una peticion Rest
		*/
		console.log("Old id: "+$scope.selected.empresaId+", "+$scope.selected.dominio);
		$scope.selected.empresaId = $scope.empresaSeleccionada;
		console.log("New id: "+$scope.selected.empresaId+", "+$scope.selected.dominio);

		$http.put('/agregarVehiculo',$scope.selected).success(function(data, status, headers, config) {
			$scope.listadoVehiculo($scope.selectedIndex);
  		});
	};

	$scope.delete = function(){
		if($scope.vehiculos.length > 0){
			$http.put('/borrarVehiculo',$scope.selected).success(function(data, status, headers, config) {
				//$scope.usuarios.splice($scope.selectedIndex,1);
				if($scope.selectedIndex >= $scope.vehiculos.length-1){
					$scope.selectedIndex = $scope.vehiculos.length-1;
				}
				$scope.listadoVehiculo($scope.selectedIndex);
  			});
		}
	};

	$scope.connect = function(){
        var socket = new SockJS('/wsclave');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            //setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/wsclave/getclave', function(data){
                $log.debug(data.body);
                $scope.selected.tarjetaId = data.body;

            });
        });
	};

	$scope.disconnect = function () {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        //setConnected(false);
        console.log("Disconnected");
    };
    $scope.connect();
}