(function () {
    angular
        .module('app-front', ['ngRoute','ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
        //    .when('/edit_product/:productId', {
            .when('/edit_product', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartProductController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/stock', {
                templateUrl: 'stock/stock.html',
                controller: 'stockController'
            })
            .when('/statistic', {
                templateUrl: 'statistic/statistic.html',
                controller: 'statisticController'
            })
            .when('/order_pay', {
                            templateUrl: 'order_pay/order_pay.html',
                            controller: 'orderPayController'
            })
            .when('/orders', {
                            templateUrl: 'orders/orders.html',
                            controller: 'ordersController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {//если в локальном хранилище есть юзер, то он восстанавливается при входе
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }
})();

angular.module('app-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {


//var aaa = document.getElementById("userName");

   $scope.tryToAuth = function () {

        $http.post('http://localhost:5555/logic/api/v1/auth', $scope.user)
            .then(function successCallback(response) {

                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token,
                    role: response.data.role};

                  //  aaa.value = $scope.user.username;
                    $scope.user.username = null;
                    $scope.user.password = null;

                    if (response.data.role == "[ROLE_USER]") {$location.path('store');}
                }
            }, function errorCallback(response) {
             alert(response.data.messages);
            });
    };

    $scope.tryToLogout = function () {

        $scope.clearUser();
      //  aaa.value = '';
        $location.path('welcome');
    };

    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
         if ($localStorage.webMarketUser) {
                //   if ($localStorage.webMarketUser.role =="[ROLE_USER]") {
                       return true;
                //   }
                    }
                       return false;
    };
     $rootScope.isAdminLoggedIn = function () {
     if ($localStorage.webMarketUser) {
         //   if ($localStorage.webMarketUser.role =="[ROLE_ADMIN]") {
                return true;
        //    }
             }
                return false;

        };
     $rootScope.isLoggedIn = function () {
          if ($localStorage.webMarketUser) {
               return true;
           } else {
               return false;
           }
     };
});
