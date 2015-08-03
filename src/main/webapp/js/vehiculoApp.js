//angular.module('VehiculoApp', ['ngMaterial'])
//.controller('VehiculoCtrl', VehiculoCtrl);

function VehiculoCtrl($scope,$http,$log) {

	$scope.vehiculos = [];
	$scope.empresas = [];
	$scope.stompClient = null;
	$scope.flagNuevo = true;
	$scope.flagGrabar = false;
	$scope.flagBorrar = false;

	$scope.flagLeyendoTarjeta=false;
	$scope.flagLeerTarjeta=false;
	$scope.flagCancelarTarjeta=false;

	$scope.listadoEmpresa= function (index){
		$http.get('/listadoEmpresa').success(function(data, status, headers, config) {
			$scope.empresas=data;
  		});
	};

	$scope.listadoVehiculo= function (index){
		$http.get('/listadoVehiculo').success(function(data, status, headers, config) {
			$scope.vehiculos=data;
  		});
	};

	$scope.selecionarVehiculo = function(id){
			if($scope.flagNuevo){
    			$scope.vehiculos.forEach(function(element,index){
    				if(element.vehiculoId === id){
    					$scope.selectedIndex=index;
    					return;
    				}
    			});

				$scope.selected=$scope.vehiculos[$scope.selectedIndex];
				$scope.empresaSeleccionada = $scope.selected.empresaId;
    			$scope.flagBorrar = true;
    			$scope.flagGrabar = true;
    			$scope.flagNuevo = true;
				$scope.flagLeyendoTarjeta=false;
				$scope.flagLeerTarjeta=true;
				$scope.flagCancelarTarjeta=false;

            }
	};

	$scope.selectedIndex=0;
	$scope.listadoEmpresa(0);
	$scope.listadoVehiculo($scope.selectedIndex);

	$scope.add = function(){
		$scope.vehiculos[$scope.vehiculos.length] = new Vehiculo('MMM111','','','','','');
		$scope.selecionarVehiculo(0);
		$scope.flagBorrar = true;
		$scope.flagGrabar = true;
		$scope.flagNuevo = false;
		$scope.flagLeyendoTarjeta=false;
		$scope.flagLeerTarjeta=true;
		$scope.flagCancelarTarjeta=false;
	};

	$scope.save = function(){
		$scope.selected.empresaId = $scope.empresaSeleccionada;
		$http.put('/agregarVehiculo',$scope.selected).success(function(data, status, headers, config) {
			$scope.listadoVehiculo($scope.selectedIndex);
			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
            $scope.cancelarLectura();
			$scope.flagLeyendoTarjeta=false;
			$scope.flagLeerTarjeta=false;
			$scope.flagCancelarTarjeta=false;
  		});
	};

	$scope.delete = function(){
		if($scope.vehiculos.length > 0){
			if($scope.selected.vehiculoId !== 0){
				$http.put('/borrarVehiculo',$scope.selected).success(function(data, status, headers, config) {
					$scope.listadoVehiculo(0);
				});
			}else{
				$scope.listadoVehiculo(0);
			}
  			$scope.flagBorrar = false;
            $scope.flagGrabar = false;
            $scope.flagNuevo = true;
            $scope.cancelarLectura();
            $scope.flagLeyendoTarjeta=false;
            $scope.flagLeerTarjeta=false;
			$scope.flagCancelarTarjeta=false;
		}
	};

	$scope.connect = function(){
        var socket = new SockJS('/wsclave');
        $scope.stompClient = Stomp.over(socket);
        $scope.stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            $scope.stompClient.subscribe('/wsclave/getclave',$scope.grabarClave);
        });
	};

	$scope.grabarClave = function(data){
		$scope.selected.tarjetaId = data.body;
		$log.debug($scope.selected.tarjetaId);
		$scope.disconnect();
		$log.debug("la clave fue leida con exito");
	}

	$scope.disconnect = function () {
        if ($scope.stompClient != null) {
            $scope.stompClient.disconnect();
        }
        $scope.flagCancelarTarjeta = false;
        $scope.flagLeyendoTarjeta = false;
        $scope.flagLeerTarjeta = true;
        $scope.$apply();
        console.log("Disconnected");
    };

    $scope.leerTarjeta = function(){
    	$scope.flagLeerTarjeta = false;
    	$scope.flagLeyendoTarjeta = true;
    	$scope.flagCancelarTarjeta = true;
    	$log.debug("esperando que ingrese la tarjeta");
    	$scope.connect();
    }
    $scope.cancelarLectura = function(){
    	$scope.disconnect();
    }
}