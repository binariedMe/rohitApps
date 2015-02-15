var chooseAppCtrl = app.controller('chooseAppCtrl',['$scope','$window','$location',
    function($scope, $window, $location){
        $scope.containerHeight = $window.innerHeight;

        $scope.logOUT = function(){
            localStorage.removeItem("email");
            localStorage.removeItem("password");
            $location.url('/login');
        };
        $scope.gtJava = function(){
            $location.url('/user');
        };
        $scope.rtNode = function(){
            $window.location.href = 'http://localhost:5000';
        }

    }]);