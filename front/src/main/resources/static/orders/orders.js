angular.module('app-front').controller('ordersController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/logic/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.goToPay = function (orderId) {
        $location.path('/order_pay/' + orderId);
    }

    $scope.loadOrders();
});