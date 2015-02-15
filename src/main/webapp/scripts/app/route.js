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
            templateUrl: 'views/register.html',
            controller: 'registerController'
        })
        .when('/login', {
            templateUrl: 'views/log_in.html',
            controller: 'logInController'
        })
        .when('/user', {
            templateUrl: 'views/user.html',
            controller: 'userController',
            resolve : {
                userData : userController.getLiveSession
            }
        })
        .when('/chooseApp',{
            templateUrl: 'views/chooseApp.html',
            controller: 'chooseAppCtrl'
        })
        .otherwise({
            templateUrl : 'views/404.html'
        });
});