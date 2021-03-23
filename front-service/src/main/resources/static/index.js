(function () {
    'use strict';

    angular
        .module('app', [])
        .config(config)
        .run(run);

    function config($httpProvider) {

    }

    function run() {

    }
})();

angular.module('app', []).controller('indexController', function ($scope, $http) {

    $scope.getProducts = function () {
        $http({
            url: 'http://localhost:5555/api/v1/products',
            method: 'GET'
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };
   $scope.getProducts();
});