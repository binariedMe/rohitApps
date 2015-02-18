var chooseAppCtrl = app.controller('chooseAppCtrl',['$scope','$window','$location','$rootScope',
    function($scope, $window, $location, $rootScope){
        $scope.containerHeight = $window.innerHeight;
        $rootScope.headerText = "Welcome to Rohit App World!";
        $rootScope.backToServerChoice = false;
        $scope.logOUT = function(){
            localStorage.removeItem("email");
            localStorage.removeItem("password");
            $location.url('/login');
        };
        $scope.gtJava = function(){
            $location.url('/user');
        };
        $scope.rtNode = function(){
            alert("Please be kind to wait till tomorrow...\n" +
                " I am launching Node server on 19th of February, 2015!!!")
            //$window.location.href = 'http://localhost:5000';
        }

    }]);