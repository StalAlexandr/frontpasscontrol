

var app = angular.module('myApp', ['angularMoment']);


app.controller('myCtrl', function ($scope, $http) {

    $scope.personList = [];
    $scope.currentPerson={};

    $scope.passList = [];
    $scope.currentPass={};

    $scope.lessonsList = [];

    $scope.newPass = {};

    $scope.currentDate =  new Date();
    $scope.nextMonthDate =  moment($scope.currentDate).add(1, 'M');

    $scope.newPass.launchDate = moment($scope.currentDate).format("DD/MM/YYYY");
    $scope.newPass.terminateDate = moment($scope.nextMonthDate).format("DD/MM/YYYY");

    $scope.dtlesson = moment($scope.currentDate).format("DD/MM/YYYY");

    $scope.searchString = '';


    $scope.currentPass;

    $scope.courses = [
        { id: 1, name: 'Латина' },
        { id: 2, name: 'Базовый' },
        { id: 3, name: 'Танго' }
    ];

    $scope.init = function () { };

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


    $scope.findByCard = function () {
        $http.get('http://localhost:8080/persons/select/?cardNumber=' + $scope.searchString).then(function (response) {
            if (!response.data.id) {
                alert('Карточка с номером ' +  $scope.searchString + ' не найдена');
                return;
            }
            $scope.personList[0] = response.data;
            $scope.currentPerson =  $scope.personList[0];
            $scope.findPasses();
        });
    };

    $scope.findByName = function () {
        $http.get('http://localhost:8080/persons/selectByName/' + $scope.searchString).then(function (response) {
            if (!response.data.size<1) {
                alert('Человек по фамилии ' +  $scope.searchString + ' не найден');
                return;
            }
            $scope.personList = response.data;
            $scope.currentPerson =  $scope.personList[0];
            $scope.findPasses();


        });
    };

    $scope.findPasses = function () {

        $scope.passList = [];
        $scope.currentPass = {};

        if ($scope.currentPerson) {
            $http.get('http://localhost:8080/persons/' + $scope.currentPerson.id + '/passes').then(function (response) {
                $scope.passList = response.data;
                $scope.currentPass = $scope.passList[0];
                $scope.findLessons();

            });
        }
    };

    $scope.findLessons = function () {

        $scope.lessonsList = [];

        if ($scope.currentPass) {
            $http.get('http://localhost:8080/passes/' + $scope.currentPass.id + '/lessons').then(function (response) {
                $scope.lessonsList = response.data;
            });
        }
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
        $scope.findPasses();
    };


    $scope.onSelectPass = function(event) {
        $scope.currentPass = $scope.currentPerson.passes[$(event.target).attr("id")];
        $scope.findLessons();
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

    $scope.addPass = function() {


        var headers = new Headers();
        headers.append('Content-Type', 'application/json');
        $http.put('http://localhost:8080/persons/'+$scope.currentPerson.id+"/pass", JSON.stringify($scope.newPass ), {headers: headers}).then(
            function(response) {
                $scope.findPasses();
            },
            function(data) {
                alert("Не удалось сохранить абонимент");
            });

    };

});

