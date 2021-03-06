var userController = app.controller('userController',
    ['$rootScope', '$scope', 'angularPostService', '$http', '$route', '$location','_', '$timeout', '$window',
        function($rootScope, $scope, angularPostService, $http, $route, $location, _, $timeout, $window){

            $rootScope.headerText = "Java Chat Server";
            $rootScope.backToServerChoice = true;
            $rootScope.showLogoutBtn = true;
            $scope.user = $route.current.locals.userData;
            $scope.profileDetail = angular.copy($scope.user);
            $scope.profileDetail.address = $scope.profileDetail.address ?
                $scope.profileDetail.address : {};

            $scope.profileDetail.address.zipCode = $scope.profileDetail.address.zipCode != 0?$scope.profileDetail.address.zipCode:null;

            $scope.chatPerson = null;
            $scope.activeTab = 'aboutMe';
            $scope.editable = false;
            $scope.showUser = null;

            $scope.addressString = function(addressObject){
                addressObject = _.values(addressObject);
                var addressString = "";
                (_.without((_.values(addressObject)),undefined,"", "undefined", 0, null, "null")).forEach(function(entry){
                    addressString += (entry + ",");
                });
                addressString = addressString.substring(0,addressString.length-1);
                return addressString;
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
                    $scope.friendList = list;
                });
            };

            $scope.addFriend = function(user){
                if($scope.showUser && $scope.showUser.email && $scope.showUser.email==user.email)
                    $scope.showUser = null;
                var url = '/addFriend';
                var data = "user=" + $scope.user.email + "&friend=" + user.email +"&friend_name=" + user.fullName;
                angularPostService.serve(data,url).then(function(){
                    $scope.getFriends();
                    $scope.getUserList();
                })
            };
            $scope.removeFriend = function(email){
                if($scope.chatPerson && $scope.chatPerson.email && $scope.chatPerson.email==email)
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
                $scope.fetchMessage();
            };
            $scope.showUserDetail = function(user){
              $scope.showUser = user;
            };
            $scope.activeMe = function(activeTab){
              $scope.activeTab = activeTab;
            };
            $scope.msgSubmit = function(){
                var data = "user=" + $scope.user.email + "&recepient=" + $scope.chatPerson.email
                    + "&message=" + this.message;
                var url = '/submitMessage';
                angularPostService.serve(data, url).then(function(reply, error){

                    // TODO some error handling to be done

                });
                this.message = '';
            };

            $scope.fetchMessage= function(){
                if($scope.chatPerson){
                    var data = "user=" + $scope.user.email + "&friend=" + $scope.chatPerson.email;
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

            $scope.editProfile = function(){
                $scope.editable = true;
            };
            $scope.cancelProfileEdit = function(){
                console.log("user:",$scope.user);
                console.log("profile:", $scope.profileDetail);
                $scope.profileDetail = angular.copy($scope.user);
                $scope.profileDetail.address = $scope.profileDetail.address ?
                    $scope.profileDetail.address : {};
                $scope.profileDetail.address.zipCode = $scope.profileDetail.address.zipCode != 0?
                    $scope.profileDetail.address.zipCode:null;
                $scope.editable = false;
                console.log("user1:",$scope.user);
                console.log("profile1:", $scope.profileDetail);
            };
            $scope.saveProfile = function(profileDetail){
                if(profileDetail.firstName) {
                    if (!profileDetail.fullName) {
                        profileDetail.fullName = profileDetail.lastName ?
                            profileDetail.firstName + " " + profileDetail.lastName
                            : profileDetail.firstName;
                    }
                    $scope.profileDetail.address.zipCode = $scope.profileDetail.address.zipCode != null?$scope.profileDetail.address.zipCode:0;
                    if($scope.profileDetail.address.zipCode && $scope.profileDetail.address.zipCode < 100000){
                        alert("Invalid zip code");
                        return;
                    }

                    var defaultAddress = {
                        city : "",
                        state : "",
                        country : "",
                        landMark : "",
                        addressLine1 : "",
                        addressLine2 : "",
                        zipCode : ""
                    };
                    var defaultUserData = {
                        email : "",
                        firstName : "",
                        lastName : "",
                        fullName : "",
                        phone : ""
                    };
                    _.defaults(profileDetail.address, defaultAddress);
                    _.defaults(profileDetail,defaultUserData);
                    var data = "user=" + $scope.user.email
                        + "&firstName=" + profileDetail.firstName
                        + "&lastName=" + profileDetail.lastName
                        + "&fullName=" + profileDetail.fullName
                        + "&phone=" + profileDetail.phone
                        + "&addressLine1=" + profileDetail.address.addressLine1
                        + "&addressLine2=" + profileDetail.address.addressLine2
                        + "&city=" + profileDetail.address.city
                        + "&landMark=" + profileDetail.address.landMark
                        + "&state=" + profileDetail.address.state
                        + "&country=" + profileDetail.address.country
                        + "&zipCode=" + profileDetail.address.zipCode;
                    var url = '/updateProfile';
                    angularPostService.serve(data, url).then(function (profileData) {
                        if(profileData){
                            $scope.user = profileData;
                            $scope.profileDetail = angular.copy($scope.user);
                            $scope.profileDetail.address = $scope.profileDetail.address ?
                                $scope.profileDetail.address : {};
                            $scope.profileDetail.address.zipCode = $scope.profileDetail.address.zipCode != 0?
                                $scope.profileDetail.address.zipCode:null;
                            $scope.userAddress = $scope.addressString(profileData.address);
                            $scope.editable = false;
                        }

                    });

                }
            };

            $scope.getFriends();
            $scope.getUserList();
            $scope.userAddress = $scope.addressString($scope.profileDetail.address);
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
                if(userData && userData!== 'false'){
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

var editAboutMeDirective = app.directive('editAboutMe',function(){
    return{
        restrict : 'E',
        templateUrl : 'client_assets/views/editAboutMe.html'
    }
});
var displayAboutMeDirective = app.directive('displayAboutMe', function(){
    return{
        restrict : 'E',
        templateUrl : 'client_assets/views/displayAboutMe.html'
    }
});