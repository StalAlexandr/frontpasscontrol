var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http, $element) {


    $scope.personList = [];

    $scope.currentPerson = {};

    $scope.searchString = '';



    $scope.search = function () {

        $scope.current = 'Ф';
        $scope.currentPerson='';
        $scope.personList = [];

        if (!isNaN( $scope.searchString)){
            $scope.findByCard()
            }
       else {
            $scope.findByName()
        }
    }

    $scope.init = function () {


    };


    $scope.findByCard = function () {
        $http.get('http://localhost:8080/persons/select/?cardNumber=' + $scope.searchString).then(function (response) {
            if (!response.data.id) {
                alert('Карточка с номером ' +  $scope.searchString + ' не найдена');
                return;
            }
            $scope.personList[0] = response.data;
            $scope.currentPerson =  $scope.personList[0];
        });
    }


    $scope.findByName = function () {
        $http.get('http://localhost:8080/persons/selectByName/' + $scope.searchString).then(function (response) {
            if (!response.data.size<1) {
                alert('Человек по фамилии ' +  $scope.searchString + ' не найден');
                return;
            }
            $scope.personList = response.data;
            $scope.currentPerson =  $scope.personList[0];

        });
    }

});

