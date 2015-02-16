var underscore = angular.module('underscore',[]);

underscore.factory('_', function(){
    return window._;
});

var app = angular.module('chat-app',['ngRoute','underscore']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo : '/user'
        })
        .when('/register', {
            templateUrl: 'client_assets/views/register.html',
            controller: 'registerController'
        })
        .when('/login', {
            templateUrl: 'client_assets/views/log_in.html',
            controller: 'logInController'
        })
        .when('/user', {
            templateUrl: 'client_assets/views/user.html',
            controller: 'userController',
            resolve : {
                userData : userController.getLiveSession
            }
        })
        .when('/chooseApp',{
            templateUrl: 'client_assets/views/chooseApp.html',
            controller: 'chooseAppCtrl'
        })
        .otherwise({
            templateUrl : 'client_assets/views/404.html'
        });
});