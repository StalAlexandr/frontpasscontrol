

var app = angular.module('myApp', ['angularMoment']);


app.controller('myCtrl', function ($scope, $http) {

    $scope.personList = [];

    $scope.currentPerson={};
    $scope.currentPass={};


    $scope.currentDate =  new Date();
    $scope.nextMonthDate =  moment($scope.currentDate).add(1, 'M');

    $scope.dtstartpass = moment($scope.currentDate).format("DD/MM/YYYY");  //new Date().toISOString().slice(0, 10).replace(/-/g, '/');

    $scope.dtlesson = moment($scope.currentDate).format("DD/MM/YYYY");

    $scope.dtendpass = moment($scope.nextMonthDate).format("DD/MM/YYYY");

    $scope.searchString = '';



    $scope.search = function () {

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
    };

    $scope.newPerson = function() {
        $scope.currentPerson = {};
    };

    $scope.savePerson = function() {
        if ($scope.currentPerson.id) {
            $scope.updatePerson();
        } else {
            $scope.addPerson();
        }

    };

    $scope.updatePerson = function() {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        $http.put('http://localhost:8080/persons/', JSON.stringify($scope.currentPerson ), {headers: headers}).then(
            function(response) {
            },
            function(data) {
                alert("Не удалось сохранить. Скорее всего карточка с таким номером уже есть в системе");
            });
    };

    $scope.addPerson = function(){

        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        $scope.currentPerson.id='';
        $http.post('http://localhost:8080/persons/', JSON.stringify($scope.currentPerson ), {headers: headers}).then(
            function(response) {
            },
            function(data) {
                alert("Не удалось сохранить. Скорее всего карточка с таким номером уже есть в системе");
            });

    };

    $scope.onSelectPerson = function(event){
        $scope.currentPerson= $scope.personList[$(event.target).attr("id")];
    };


    $scope.onSelectPass = function(event) {
        $scope.currentPass = $scope.currentPerson.passes[$(event.target).attr("id")];
    };

    $scope.validPerson = function() {
       return (($scope.currentPerson.cardNumber));
    };

    $scope.validateRmLesson = function() {
        return true;
    };

    $scope.validateRmPass = function() {
        return true;
    }
});

