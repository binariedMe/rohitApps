var userController = app.controller('userController',['$rootScope','$scope','friendService','$http','$route','$location','$timeout','logInService','$window',
    function($rootScope, $scope, friendService, $http,$route,$location,$timeout,logInService,$window){
        if(localStorage.getItem("email") && localStorage.getItem("password")){
            data = "user=" + localStorage.getItem("email") + "&password=" + localStorage.getItem("password");
            url = "/log_in";
            logInService.serve(data,url).then(function(status){
                if(status && status.indexOf('false')==-1){
                }
                else
                $location.url("login");
            });
        }
        else {
            alert("Please Log In");

            $location.url('login');
        }
        $scope.user = $route.current.locals.userData[0];
        $scope.chatPerson = null;
        $scope.activeTab = 'aboutMe';
        $scope.containerHeight = $window.innerHeight;

        $scope.userDetail={
            editable : false
            };
        $scope.editDetail = function(){
           $scope.userDetail.editable = true;
        };
        $scope.saveDetail = function(formData){
            $scope.userDetail.editable = false;
        };
        $scope.addToChat = function(reciever){
            $scope.chatPerson = reciever;
            $scope.activeTab = 'chat';
            $scope.fetchMessage();
        };
        $scope.msgSubmit= function(){
            var data = "user=" + $scope.user.email + "&recepient=" + $scope.chatPerson.userEmail + "&message=" + this.message;
            var url = '/submitMessage';
            friendService.serve(data,url).then(function(reply){

            });
            this.message = '';
        };
        $scope.fetchMessage= function(){
            if($scope.chatPerson){
                var data = "user=" + $scope.user.email + "&friend=" + $scope.chatPerson.userEmail;
                var url = '/getMessage';
                friendService.serve(data,url).then(function(response){
                    $scope.chatMessages = response;
                    var msgDiv = document.getElementsByClassName("msgDiv")[0];
                    msgDiv.scrollTop = msgDiv.scrollHeight;
                    $timeout($scope.fetchMessage,1000);
                })
            }
        };
        $scope.getFriends = function(){
            var url= '/getFriendList';
            var data = "user=" + $scope.user.email;
            friendService.serve(data,url).then(function(list){
                $scope.friendList=list;});
        };
        $scope.getFriends();
        $scope.getUserList = function(){
            var url= '/getUserList';
            var data = "user=" + $scope.user.email;
            friendService.serve(data,url).then(function(list){
                $scope.userList=list;});
        };
        $scope.getUserList();
        $scope.addFriend = function(user){
            var url = '/addFriend';
            var data = "user=" + $scope.user.email + "&friend=" + user.userEmail +"&friend_name=" + user.userName;
            friendService.serve(data,url).then(function(){
                $scope.getFriends();
                $scope.getUserList();
            })
        };
        $scope.removeFriend = function(email){
            if($scope.chatPerson && $scope.chatPerson.userEmail && $scope.chatPerson.userEmail==email)
                $scope.chatPerson = null;
            var data="user=" + $scope.user.email + "&friend=" + email;
            var url = '/removeFriend';
            friendService.serve(data,url).then(function(){
                $scope.getFriends();
                $scope.getUserList();
            })
        };
        $scope.logOUT = function(){
            localStorage.removeItem("email");
            localStorage.removeItem("password");
            $location.url('/login');
        }


    }]);
userController.loadUser = function($q,logInService){
    var defer = $q.defer();
    if(localStorage.getItem("email") && localStorage.getItem("password")){
        data = "user=" + localStorage.getItem("email") + "&password=" + localStorage.getItem("password");
        url = "/log_in";
        logInService.serve(data,url).then(function(status){
            if(status){
                defer.resolve(status);
            }
            else {
                alert("Please Log In somewhere");
                defer.reject();
            }
        });
    }
    else {
        alert("Please Log In here");
        defer.reject();
    }
    return defer.promise;
};








