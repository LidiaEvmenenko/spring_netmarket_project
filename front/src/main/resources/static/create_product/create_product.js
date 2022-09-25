angular.module('app-front').controller('createProductController', function ($rootScope, $scope, $http, $location) {
    const contextPath = 'http://localhost:5555/logic/api/v1/';

var objSel = document.getElementById("selectCategory");
var objSel1 = document.getElementById("selectManufacturer");
var objFile = document.getElementById("inputFile");

     $scope.createNewProduct = function () {
         if($scope.new_product == null){
           alert("Заполните форму!!!");
           return;
         }
         if ( objSel.selectedIndex != -1) {
           $scope.new_product.categoryTitle = objSel.options[objSel.selectedIndex].value;
         }
         if ( objSel1.selectedIndex != -1) {
                    $scope.new_product.manufacturerTitle = objSel1.options[objSel1.selectedIndex].value;
                  }
         $http.post(contextPath + 'products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                alert("Продукт создан!!!");

            }, function failCallback(response) {
                alert(response.data.messages);
            }
            );
     }
     $scope.createNewCategory = function(){
        if($scope.new_category == null){
                   alert("Введите название категории!!!");
                   return;
                 }
                 $http.post(contextPath + 'category', $scope.new_category)
                    .then(function successCallback(response) {
                        $scope.new_category = null;
                        alert("Категория создана!!!");
                        $scope.loadCategory();
                    }, function failCallback(response) {
                        alert(response.data.messages);
                    }
                    );
     }
     $scope.createNewManufacturer = function(){
             if($scope.new_manufacturer == null){
                        alert("Введите название поставщика!!!");
                        return;
                      }
                      $http.post(contextPath + 'manufacturer', $scope.new_manufacturer)
                         .then(function successCallback(response) {
                             alert("Поставщик создан!!!");
                             $scope.loadManufacturer();
                             $scope.new_manufacturer = null;
                         }, function failCallback(response) {
                             alert(response.data.messages);
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

      $scope.loadCategory();
      $scope.loadManufacturer();
});