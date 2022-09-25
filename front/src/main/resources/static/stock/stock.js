angular.module('app-front').controller('stockController', function ($rootScope, $scope, $http, $location) {
    const contextPath = 'http://localhost:5555/logic/api/v1/';

var objSel = document.getElementById("selectProduct");
var objSel1 = document.getElementById("selectManufacturer");
var objFile = document.getElementById("inputFile");
var objReport = document.getElementById("report");
var productId;

    $scope.loadReport = function(){
        $http.get(contextPath + 'files/report')
                   .then(function successCallback(response) {
                       alert("Отчет готов!!!");
                       console.log(response.data);
                       console.log(response.data.length);
                       let a = 'http://';
                       for(let i=0; i<response.data.length-1; i++){
                          a += response.data[i] + '/';
                       }
                       a += response.data[response.data.length-1];
                       objReport.setAttribute("href",a);
                   //    objReport.setAttribute("href",response.data);
                       console.log(objReport.getAttribute("href"));

                       objReport.innerHTML = response.data[response.data.length-1];

                   }, function failCallback(response) {
                       console.log(response.data);
                   }
                   );
    }
//    function connect(){
//       var socket = new SockJS('/gs-guide-websocket');
//       stompClient = Stomp.over(socket);
//       stompClient.connect({}, function(frame) {
//       setConnected(true);
//     //  console.log('Connected: ' + frame);
//       stompClient.subscribe('/topic/greetings', function(greeting){
//          showGreeting(JSON.parse(greeting.body).content);
//       });
//       });
//    }
//    function setConnected(connected){
//       $("#connect").prop("disabled", connected);
//       $("#disconnect").prop("disabled", !connected);
//       if(connected){
//          $("#conversation").show();
//       }else{
//          $("#conversation").hide();
//       }
//       $("#greetings").html("");
//    }
//connect();
     $scope.createNewStock = function () {
//     console.log($scope.new_stock);


         if($scope.new_stock == null){
           alert("Заполните форму!!!");
           return;
         }
         if ( objSel.selectedIndex != -1 && $scope.new_stock.amount != null) {
           $scope.new_stock.productTitle = objSel.options[objSel.selectedIndex].text;
           $scope.new_stock.productId = objSel.options[objSel.selectedIndex].value;

         $http.post(contextPath + 'stock', $scope.new_stock)
            .then(function successCallback(response) {
                $scope.new_stock = null;
                alert("Продукт добавлен!!!");

            }, function failCallback(response) {
                alert(response.data.messages);
            }
            );
             }
     }

     $scope.loadProduct = function(selectmanufacturerTitle){
      if ( objSel1.selectedIndex != -1) {
         $scope.selectmanufacturerTitle = objSel1.options[objSel1.selectedIndex].value;
//      }
//     alert(selectmanufacturerTitle);
//     console.log($scope.selectmanufacturerTitle);
//        if(selectmanufacturerTitle != null){

           $http({
                    url: contextPath + 'products/manufacturer',
                    method: 'GET',
                    params: {
                     title: $scope.selectmanufacturerTitle
                    }
           }).then(function(response) {
           console.log(response);
           $scope.product = response.data.productsViewList;
           objSel.options.length=1;
           for (let i = 0; i < response.data.productsViewList.length; i++) {
               objSel.options[i+1] = new Option(response.data.productsViewList[i].title, response.data.productsViewList[i].id);
           }
           });
           }else{
              alert("Выберите поставщика!!!");
           }
           }

      $scope.loadManufacturer = function(){
                      $http({
                         url: contextPath + 'manufacturer',
                         method: 'GET',
                      }).then(function(response) {
                      console.log(response);
                      objSel1.options.length=1;
                      for (let i = 0; i < response.data.manufacturerViews.length; i++) {
                          objSel1.options[i+1] = new Option(response.data.manufacturerViews[i].title,
                          response.data.manufacturerViews[i].title);
                      }
                      });
                      }


      $scope.loadManufacturer();
});