var chooseAppCtrl = app.controller('chooseAppCtrl',['$scope','$window','$location','$rootScope',
    function($scope, $window, $location, $rootScope){

        $rootScope.showLogoutBtn = true;
        $rootScope.backToServerChoice = false;

        $scope.gtJava = function(){
            $location.url('/user');
        };

        $scope.rtNode = function(){
            alert("Please be kind to wait till tomorrow...\n" +
                " I am launching Node application by 20th of February, 2015!!!")
            //$window.location.href = 'http://localhost:5000';
        };

    }]);