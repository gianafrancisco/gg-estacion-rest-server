function ReporteCtrl($scope,$http) {
  $scope.formats = ['yyyy-MM-dd','dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[0];
  $scope.format2 = $scope.formats[0];
  $scope.totalItems=0;
  $scope.currentPage = 1;
  $scope.ipp = 100;
  $scope.maxSize = 10;
  $scope.fechaDesde = new Date();
  $scope.fechaHasta = new Date();

  $scope.openDesde = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.desdeOpened = true;
  };

  $scope.openHasta = function($event) {
      $event.preventDefault();
      $event.stopPropagation();
      $scope.hastaOpened = true;
  };

  $scope.dateOptions = {
      formatYear: 'yy',
      startingDay: 1
  };

  $scope.fnError = function (data, status, headers, config) {
        $scope.flagErrores = true;
        $scope.mensajeError = "Error inesperado en el servidor";
        $log.error($scope.mensajeError);
    };

  $scope.buscar = function () {
    if($scope.fechaDesde != undefined && $scope.fechaHasta != undefined){
          $scope.fechaDesde.setUTCMinutes(0);
          $scope.fechaDesde.setUTCSeconds(0);
          $scope.fechaDesde.setUTCHours(0);
          $scope.fechaDesde.setUTCMilliseconds(0);

          $scope.fechaHasta.setUTCMinutes(59);
          $scope.fechaHasta.setUTCSeconds(59);
          $scope.fechaHasta.setUTCHours(23);
          $scope.fechaHasta.setUTCMilliseconds(999);

          var filtro = {
            desde: $scope.fechaDesde.toISOString(),
            hasta: $scope.fechaHasta.toISOString(),
            filtro: ""
          };
          var pagination = "?ipp="+$scope.ipp+"&p="+$scope.currentPage;
          $http.post("/registro/listado"+pagination,filtro).success(function (data, status, headers, config) {
              $scope.items = data.items;
              $scope.totalItems = data.totalItems;
          }).error($scope.fnError);
      }
  };

}