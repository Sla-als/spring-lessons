angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/happy';

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.deleteProductById = function (id) {
        $http({
            url: contextPath + '/products',
            method: 'DELETE',
            params: {
                id: id
            }
        }).then(function (response) {
            $scope.fillTable();
        });
    };


    $scope.fillTable();
});

