var animacion = function (e,c){
               angular.element(e).addClass(c);
               setTimeout(function(){
                    angular.element(e).removeClass(c);
                },1000);
            };

var popup = function(e,msg){

}

angular.module('RegistroVentasApp', ['ngResource'])
        .controller('RegistroVentasController', function ($scope, $http, $log, $resource) {

            $scope.flagSeleccionarUsuario = false;
            $scope.leyendoDatoSurtidor = false;
            $scope.flagInformacion = false;
            $scope.flagErrores = false;
            $scope.flagVehiculo = false;
            $scope.flagResumenVenta = false;
            $scope.flagConsultarPuntos = false;
            $scope.flagMostrarSurtidores = false;
            $scope.flagLectura = false;
            $scope.flagResumenVenta = false;
            $scope.flagValidar = true;
            $scope.flagValidarError=false;
            $scope.flagValidarOK=false;
            $scope.flagInformacionCambioPuntos = false;
            $scope.flagCambioPuntos = false;
            $scope.flagEsperandoLeerLlaveroConsultaPuntos = false;
            $scope.stompClient = null;

            $scope.usuario;
            $scope.usuarios = [];
            $scope.surtidores = [];
            $scope.surtidor;
            $scope.vehiculo;
            $scope.empresa;
            $scope.producto;
            $scope.productoCambiar;
            $scope.mensajeError;
            $scope.puntosAcumulados = 0;


            $scope.cargarUsuarios = function () {
                $http.get("/listadoUsuario").success(function (data, status, headers, config) {
                    $scope.usuarios = data;
                }).error($scope.fnError);
            };

            $scope.leerSurtidores = function () {
                $http.get("/listadoSurtidor").success(function (data, status, headers, config) {
                    $scope.surtidores = data;
                }).error($scope.fnError);
            };

            $scope.leerLlavero = function () {
                $scope.flagVehiculo = true;
                $scope.buscarEmpresa($scope.vehiculo.empresaId);
            };

            $scope.buscarEmpresa = function (id) {
                $http.get("/empresa/" + id).success(function (data, status, headers, config) {
                    $scope.empresa = data;
                }).error($scope.fnError);
            };

            $scope.buscarProducto = function (id) {
                $http.get("/producto/" + id).success(function (data, status, headers, config) {
                    $scope.producto = data;
                }).error($scope.fnError);
            };

            $scope.leerDatosSurtidor = function (index) {
                $scope.flagInformacion = true;
                $scope.surtidor = $scope.surtidores[index];
                $http.get("/surtidor/leer/" + $scope.surtidor.surtidorId).success(function (data, status, headers, config) {
                    $scope.flagInformacion = false;
                    $scope.surtidorLectura = data;
                    $scope.buscarProducto(4);
                }).error($scope.fnError);
            };

            $scope.registrarVenta = function () {

                $scope.mensajeInfo = "Seleccione los datos del surtidor";
                $scope.flagInformacion = false;
                if($scope.producto === undefined){
                    animacion("#datosSurtidor","animated flash");
                    animacion("#listaSurtidores","animated flash");
                    $scope.flagInformacion = true;
                    return;
                }
                if($scope.surtidor === undefined){
                    animacion("#datosSurtidor","animated flash");
                    animacion("#listaSurtidores","animated flash");
                    $scope.flagInformacion = true;
                    return;
                }
                $http.put("/registro/agregar", {usuario: $scope.usuario, surtidor: $scope.surtidor, producto: $scope.producto, lectura: $scope.surtidorLectura, empresa: $scope.empresa, vehiculo: $scope.vehiculo}).success(function (data, status, headers, config) {
                    $scope.fechaProximaCarga = data.fecha;
                    $scope.flagMostrarSurtidores=false;
                    $scope.obtenerPuntosDisponibles();
                }).error($scope.fnError);
            };

            $scope.obtenerPuntosDisponibles = function(){
                $http.get("/registro/puntos/"+$scope.empresa.empresaId).success(function (data, status, headers, config) {
                    $scope.puntosAcumulados = data;
                    $scope.flagResumenVenta = true;
                    $scope.flagLectura = false;
                }).error($scope.fnError);
            }

            $scope.obtenerPuntosDisponiblesCambioPuntos = function(){
                $http.get("/registro/puntos/"+$scope.empresa.empresaId).success(function (data, status, headers, config) {
                    $scope.puntosAcumulados = data;
                    $scope.obtenerProductos();
                }).error($scope.fnError);
            }

            $scope.limpiarObjetos = function(){
                 $scope.usuario = undefined;
                 $scope.surtidor = undefined;
                 $scope.producto = undefined;
                 $scope.surtidorLectura = undefined;
                 $scope.empresa = undefined;
                 $scope.vehiculo = undefined;
            };

            $scope.seleccionarUsuario = function (index) {
                $scope.leerSurtidores();
                $scope.usuario = $scope.usuarios[index];
                $scope.flagSeleccionarUsuario = false;
                $scope.flagMostrarSurtidores = true;
                $scope.flagValidar = false;
                $scope.flagLectura = true;
                $scope.flagCambioPuntos = true;
                $scope.leerLlavero();
            };

            $scope.nuevaVenta = function () {
                $scope.flagSeleccionarUsuario = false;
                $scope.leyendoDatoSurtidor = false;
                $scope.flagInformacion = false;
                $scope.flagErrores = false;
                $scope.flagVehiculo = false;
                $scope.flagResumenVenta = false;
                $scope.flagConsultarPuntos = false;
                $scope.flagMostrarSurtidores = false;
                $scope.flagLectura = false;
                $scope.flagResumenVenta = false;
                $scope.flagValidar = true;
                $scope.flagValidarError=false;
                $scope.flagValidarOK=false;
                $scope.flagInformacionCambioPuntos = false;
                $scope.flagCambioPuntos = false;
                $scope.connect();
                $scope.limpiarObjetos();
            };

            $scope.mostrarSurtidoresDiv = function () {
                return $scope.flagMostrarSurtidores;
            };
            $scope.mostrarLecturaDiv = function () {
                return $scope.flagLectura;
            };
            $scope.mostrarResumenVentaDiv = function(){
                return $scope.flagResumenVenta;
            };
            $scope.showConsultaPuntos = function (){

                $scope.connectCambioPuntos();
                $scope.flagConsultarPuntos = true;
                $scope.flagResumenVenta = false;
                $scope.flagValidar = false;
                $scope.flagMostrarSurtidores = false;
                $scope.flagLectura = false;
                $scope.empresa = undefined;
                $scope.vehiculo = undefined;
                $scope.puntosAcumulados = 0;
                $scope.productos = undefined;
                $scope.flagInformacionCambioPuntos = false;
            };
            $scope.mostrarValidadVehiculo = function(){
                return $scope.flagValidar;
            };
            $scope.mostrarSeleccionarUsuario = function(){
                return $scope.flagSeleccionarUsuario;
            };
            $scope.obtenerProductos = function () {
                $http.get("/producto/listado/"+$scope.vehiculo.empresaId+"/"+$scope.puntosAcumulados).success(function (data, status, headers, config) {
                    $scope.productos = data;
                }).error($scope.fnError);
            };

            $scope.buscarEmpresaCambioPuntos = function (id) {
                $http.get("/empresa/" + id).success(function (data, status, headers, config) {
                    $scope.empresa = data;
                    $scope.obtenerPuntosDisponiblesCambioPuntos();
                }).error($scope.fnError);
            };

            $scope.registrarCambioPuntos = function (index) {
                $scope.producto = $scope.productos[index];
                $http.put("/registro/cambiarPuntos", {surtidor: $scope.surtidores[0], usuario: $scope.usuario, producto: $scope.producto, empresa: $scope.empresa, vehiculo: $scope.vehiculo}).success(function (data, status, headers, config) {
                    $scope.obtenerPuntosDisponiblesCambioPuntos();
                    $scope.flagInformacionCambioPuntos=true;
                    $scope.flagResumenVenta = false;
                }).error($scope.fnError);
            };
            $scope.fnError = function (data, status, headers, config) {
                $scope.flagErrores = true;
                $scope.mensajeError = "Error inesperado en el servidor";
                $log.error($scope.mensajeError);
            };

            $scope.fnError = function (data, status, headers, config) {
                $scope.flagErrores = true;
                $scope.mensajeError = "Error inesperado en el servidor";
                $log.error($scope.mensajeError);
            };

            $scope.connect = function(){
                $scope.disconnect();
                var socket = new SockJS('/wsvehiculo');
                $scope.stompClient = Stomp.over(socket);
                $scope.stompClient.connect({}, function(frame) {
                    //setConnected(true);
                    console.log('Connected: ' + frame);
                    $scope.stompClient.subscribe('/wsvehiculo/getvehiculo', function(data){
                        var o = JSON.parse(data.body);
                        $scope.flagValidarOK = o.activado;
                        $scope.vehiculo = o.vehiculo;
                        $scope.flagValidarError = !$scope.flagValidarOK;
                        $scope.flagSeleccionarUsuario = true;
                        $scope.disconnect();
                        $scope.$apply();
                    });
                });
            };

            $scope.connectCambioPuntos = function(){
                $scope.flagEsperandoLeerLlaveroConsultaPuntos = true;
                $scope.disconnect();
                var socket = new SockJS('/wsvehiculo');
                $scope.stompClient = Stomp.over(socket);
                $scope.stompClient.connect({}, function(frame) {
                    //setConnected(true);
                    console.log('Connected: ' + frame);
                    $scope.stompClient.subscribe('/wsvehiculo/getvehiculo', function(data){
                        var o = JSON.parse(data.body);
                        $scope.vehiculo = o.vehiculo;
                        $scope.buscarEmpresaCambioPuntos($scope.vehiculo.empresaId);
                        $scope.flagInformacionCambioPuntos = false;
                        $scope.flagEsperandoLeerLlaveroConsultaPuntos = false;
                        $scope.disconnect();
                        $scope.$apply();
                    });
                });
            };

            $scope.disconnect = function () {
                if ($scope.stompClient != null) {
                    $scope.stompClient.disconnect();
                }
                console.log("Disconnected");
            };

            $scope.nuevaVenta();
            $scope.cargarUsuarios();




        });


