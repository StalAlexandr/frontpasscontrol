var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http, $element) {

    $scope.currentPerson={};

    $scope.currentPersonCard = '';



    $scope.getUserByCard = function(){

        $http.get('http://localhost:8080/persons/'+$scope.currentPersonCard).then(function (response) {


            if (!response.data.id){
                alert('Человек не найден');
                return;
            }

            $scope.currentPerson = response.data;

        });
    };

    $scope.init = function () {


    }
});

