var userController = app.controller('userController',
    ['$rootScope', '$scope', 'angularPostService', '$http', '$route', '$location', '$timeout', '$window',
        function($rootScope, $scope, angularPostService, $http, $route, $location, $timeout, $window){

            $scope.user = $route.current.locals.userData[0];
            $scope.chatPerson = null;
            $scope.activeTab = 'aboutMe';
            $scope.containerHeight = $window.innerHeight;

            $scope.userDetail = {
                editable: false
            };
            $scope.editDetail = function(){
                $scope.userDetail.editable = true;
            };
            $scope.saveDetail = function(formData){
                $scope.userDetail.editable = false;
            };

            $scope.getUserList = function(){
                var url= '/getUserList';
                var data = "user=" + $scope.user.email;
                angularPostService.serve(data,url).then(function(list){
                    $scope.userList=list;});
            };
            $scope.getFriends = function(){
                var url= '/getFriendList';
                var data = "user=" + $scope.user.email;
                angularPostService.serve(data, url).then(function(list){
                    console.info("friend list: ", list);
                    $scope.friendList = list;
                });
            };

            $scope.addFriend = function(user){
                var url = '/addFriend';
                var data = "user=" + $scope.user.email + "&friend=" + user.userEmail +"&friend_name=" + user.userName;
                angularPostService.serve(data,url).then(function(){
                    $scope.getFriends();
                    $scope.getUserList();
                })
            };
            $scope.removeFriend = function(email){
                if($scope.chatPerson && $scope.chatPerson.userEmail && $scope.chatPerson.userEmail==email)
                    $scope.chatPerson = null;
                var data="user=" + $scope.user.email + "&friend=" + email;
                var url = '/removeFriend';
                angularPostService.serve(data,url).then(function(){
                    $scope.getFriends();
                    $scope.getUserList();
                })
            };

            $scope.addToChat = function(reciever){
                $scope.chatPerson = reciever;
                $scope.activeTab = 'chat';
                $scope.fetchMessage();
            };
            $scope.msgSubmit = function(){
                var data = "user=" + $scope.user.email + "&recepient=" + $scope.chatPerson.userEmail
                    + "&message=" + this.message;
                var url = '/submitMessage';
                angularPostService.serve(data, url).then(function(reply, error){

                    // TODO some error handling to be done

                });
                this.message = '';
            };
            $scope.fetchMessage= function(){
                if($scope.chatPerson){
                    var data = "user=" + $scope.user.email + "&friend=" + $scope.chatPerson.userEmail;
                    var url = '/getMessage';
                    angularPostService.serve(data, url).then(function(response, error){

                        // TODO some error handling to be done

                        $scope.chatMessages = response;
                        var msgDiv = document.getElementsByClassName("msgDiv")[0];
                        msgDiv.scrollTop = msgDiv.scrollHeight;
                        $timeout($scope.fetchMessage, 1000);
                    })
                }
            };

            /*
            The logout implementation as of now just removes the data
            stored in local storage.
             */
            $scope.logOUT = function(){
                localStorage.removeItem("email");
                localStorage.removeItem("password");
                $location.url('/login');
            }

            $scope.getFriends();
            $scope.getUserList();
        }
    ]);

userController.getLiveSession = function($q, angularPostService){
    var defer = $q.defer();

    if(localStorage) {
        var emailATLS = localStorage.getItem("email");
        var passATLS = localStorage.getItem("password");

        if (emailATLS && passATLS) {
            var data = "user=" + emailATLS + "&password=" + passATLS;
            var url = "/log_in";

            angularPostService.serve(data, url).then(function(userData){
                if(userData){
                    defer.resolve(userData);
                }
                else {
                    defer.reject();
                }
            });
        }
        else {
            defer.reject();
        }
    }
    else {
        alert("Your browser does not seem to support the latest technologies used in our site. \n" +
        "Please update your browser in order to continue to the site. \n" +
        "Thanks :-)");
        defer.reject();
    }
    return defer.promise;
};
