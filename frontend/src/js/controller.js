var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http, $element) {

    $scope.network = {};

    $scope.application={};

    $scope.selectedExtidappli = '';

    $scope.selectedOperation = {};

    $scope.setDefaultColors = function () {
        for (var id in $scope.network.body.nodes){
            var node = $scope.network.body.nodes[id];
            node.setOptions({
                font: {
                    background: '#87CEFA'
                }
            });
        }
    };

    $scope.getApplication = function(){
        $http.get('http://localhost:9000/api/application/'+$scope.selectedExtidappli).then(function (response) {

            if (!response.data.id){
                alert('Заявка не найдена');
                return;
            }

            $scope.application = response.data;

            $scope.setDefaultColors();
            var node = $scope.network.body.nodes[$scope.application.status.code];
            console.log(node);
            node.setOptions({
                font: {
                    background: '#FFA07A'
                }
            });
            $scope.network.body.emitter.emit('_dataChanged')
        });
    }

    $scope.init = function () {

        var nodesList = [];

        var fromList = [];

        $http.get('http://localhost:9000/api/network').then(function (response) {
            var data = response.data;

            for (var i in data) {
                var node = {
                    id: data[i].id,
                    label: data[i].status.name,
                    x: data[i].position.xposition,
                    y: data[i].position.yposition,
                    allowedToMoveX: false,
                    allowedToMoveY: false

                };
                var elementFrom = data[i].from;
                for (var j in elementFrom) {
                    var currentFrom = {
                        arrows: 'to',
                        id: elementFrom[j].id,
                        from: elementFrom[j].from,
                        to: elementFrom[j].to,
                        label: elementFrom[j].operation.code,
                        operation: elementFrom[j].operation
                    };
                    fromList.push(currentFrom);
                }
                nodesList.push(node);
            }

            var nodes = new vis.DataSet(nodesList);
            var edges = new vis.DataSet(fromList);
            var container = document.querySelector('#stdnetwork');
            var data = {
                nodes: nodes,
                edges: edges
            };

            var options = {

                nodes: {
                    shape: 'box',
                    widthConstraint:
                        {minimum: 100, maximum: 100},
                    heightConstraint:
                        {minimum: 60}
                },
                interaction: {dragNodes: false, zoomView: false, dragView: false},  //, dragView: false

                physics: {enabled: false},

            };

           $scope.network = new vis.Network(container, data, options);
            $scope.setDefaultColors();
            $scope.network.on("click", function (properties) {
                var nodeIds = properties.nodes;
                var edgeIds = properties.edges;
                if (edgeIds[0]) {
                    $scope.selectedOperation = edges.get(edgeIds[0]).operation;
                    $scope.$apply();
                    console.log($scope.selectedOperation);
                }
            });
        });
    }
});

