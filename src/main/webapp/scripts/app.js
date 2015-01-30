var app= angular.module('chat-app',['ngRoute','underscore']);
app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo : '/user'
        })
        .when('/register', {
            templateUrl: 'views/register.html',
            controller: 'registerController'
        }).when('/login', {
            templateUrl: 'views/log_in.html',
            controller: 'logInController'
        }).when('/user', {
            templateUrl: 'views/user.html',
            controller: 'userController',
            resolve : {
                userData : userController.loadUser
            }
        })
        .otherwise({
            templateUrl : 'views/404.html'
        });
});

var underscore = angular.module('underscore',[]);
underscore.factory('_', function(){
    return window._;
});
app.controller('appCtrl',['$rootScope','$scope','$location','logInService','_',
    function($rootScope,$scope,$location,logInService,_){
    $rootScope.$on("$routeChangeError", function () {
        $location.url("/login");
    })
}]);

app.service(
    "friendService",
    ['$http','$q',function( $http, $q ) {
        return({
            serve: serve
        });
        function serve(data,url) {
            var request = $http({
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                url: url,
                data: data
            });

            return( request.then( handleSuccess, handleError ) );

        }
        function handleError( response ) {
            if (
                ! angular.isObject( response.data ) ||
                ! response.data.message
                ) {

                return( $q.reject( "An unknown error occurred." ) );

            }
            return( $q.reject( response.data.message ) );

        }

        function handleSuccess( response ) {

            return( response.data );

        }

    }]
);
app.service(
    "logInService",
    ['$http','$q',function( $http, $q ) {
        return({
            serve: serve
        });
        function serve(data,url) {
            var request = $http({
                method: 'post',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                url: url,
                data: data
            });

            return( request.then( handleSuccess, handleError ) );

        }
        function handleError( response ) {
            if (
                ! angular.isObject( response.data ) ||
                ! response.data.message
                ) {

                return( $q.reject( "An unknown error occurred." ) );

            }
            return( $q.reject( response.data.message ) );

        }

        function handleSuccess( response ) {

            return( response.data );

        }

    }]
);
