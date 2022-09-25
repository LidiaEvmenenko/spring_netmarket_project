angular.module('app-front').controller('editProductController', function ($routeParams, $scope, $http, $location) {
    const contextPath = 'http://localhost:5555/logic/api/v1/';

var objSel = document.getElementById("selectCategory");
var objSel1 = document.getElementById("selectManufacturer");
var objSel2 = document.getElementById("selectProduct");

     $scope.putFormProduct = function () {
        //   $http.get(contextPath + 'products/' + $routeParams.productId)
           $http.get(contextPath + 'products/' + objSel2.options[objSel2.selectedIndex].value)
            .then(function successCallback(response) {
                    $scope.put_product = response.data;
                }, function failCallback(response) {
//                    alert(response.data.messages);
//                    $location.path('store');
                }
            );
     }


      $scope.putProduct = function () {
              if ( objSel.selectedIndex != -1) {
                  $scope.put_product.categoryTitle = objSel.options[objSel.selectedIndex].value;
               }
                if ( objSel1.selectedIndex != -1) {
                                 $scope.put_product.manufacturerTitle = objSel1.options[objSel1.selectedIndex].value;
                              }
                              console.log($scope.put_product);
                    $http.put(contextPath + 'products', $scope.put_product)
              .then(function successCallback(response) {
                      $scope.put_product = null;
                      alert("Продукт успешно обновлен");
                   //   $location.path('store');
                  }, function failCallback(response) {
                    //  alert(response.data.messages);
                  }
              );
                         }
          $scope.loadCategory = function(){
                     $http({
                        url: contextPath + 'category',
                        method: 'GET',
                     }).then(function(response) {
                     objSel.options.length=1;
                     for (let i = 0; i < response.data.categoryViews.length; i++) {
                         objSel.options[i+1] = new Option(response.data.categoryViews[i].title, response.data.categoryViews[i].title);
                     }
                     });
                     }


       $scope.deleteProduct = function (product) {
           $http.delete(contextPath + 'products/'+ product)
                       .then(function successCallback(response) {
                       alert("Продукт удален");
                       $scope.put_product = null;
                         $scope.loadProduct();
                           }, function failCallback(response) {
                                alert(response.data.messages);
                       });
          }
          $scope.loadManufacturer = function(){
                                $http({
                                   url: contextPath + 'manufacturer',
                                   method: 'GET',
                                }).then(function(response) {
                                objSel1.options.length=1;
                                for (let i = 0; i < response.data.manufacturerViews.length; i++) {
                                    objSel1.options[i+1] = new Option(response.data.manufacturerViews[i].title,
                                     response.data.manufacturerViews[i].title);
                                }
                                });
                                }
        $scope.loadProduct = function(){

                  $http.get(contextPath + 'products')
                  .then(function(response) {

                  $scope.product = response.data.productsViewList;
                  objSel2.options.length=1;
                  for (let i = 0; i < response.data.productsViewList.length; i++) {
                      objSel2.options[i+1] = new Option(response.data.productsViewList[i].title, response.data.productsViewList[i].id);
                  }
                  });

                  }
      $scope.loadCategory();
    //  $scope.putFormProduct();
      $scope.loadManufacturer();
      $scope.loadProduct();
});