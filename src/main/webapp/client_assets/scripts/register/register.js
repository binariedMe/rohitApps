app.controller('registerController',
    ['$scope', '$location', 'angularPostService', '$http', '_','$window', '$rootScope',
    function($scope, $location, angularPostService, $http, _, $window, $rootScope){
        $scope.containerHeight = $window.innerHeight;
        $rootScope.backToServerChoice = false;
        $rootScope.showLogoutBtn = false;
        if(localStorage) {
            var emailATLS = localStorage.getItem("email");
            var passATLS = localStorage.getItem("password");

            if (emailATLS && passATLS) {
                var data = "user=" + emailATLS + "&password=" + passATLS;
                var url = "/log_in";

                angularPostService.serve(data, url).then(function (userData) {
                    if (userData && userData !== 'false') {
                        $location.url("chooseApp");
                        return;
                    }
                    else {
                        localStorage.removeItem("email");
                        localStorage.removeItem("password");
                    }
                });
            }

            $scope.formData = {};
            $scope.checkDuplicate = function(email){
                var data = "userName="+email;
                var url = "/isUserNameAvailable";
                angularPostService.serve( data , url).then(function(status){
                   if(status && status!== 'false'){
                       $scope.duplicateEmail = false;
                   }
                    else {$scope.duplicateEmail = true;
                   }
                });
            };

            $scope.registerMe = function(formData){
                if(formData.email && formData.name && formData.password && formData.rePass && formData.agreement){
                    if(formData.password != formData.rePass) {
                        alert("Password and Repeat Password field do not match...");
                        return;
                    }
                    else if(!$scope.duplicateEmail && formData.password == formData.rePass){
                        var data = "user=" + formData.email + "&password=" + formData.password + "&name="+ formData.name;
                        var url = "/register";
                        angularPostService.serve(data, url).then(function(status){
                            if(status && status !== 'false'){
                                localStorage.setItem("email", formData.email);
                                localStorage.setItem("password", formData.password);
                                $location.url("chooseApp")
                            }
                            else {
                                alert("Registration Unsuccessful!!!");
                            }
                        });
                    }
                }

                $scope.submitted = true;
            }

        }
        else {
            alert("Your browser does not seem to support the latest technologies used in our site. \n" +
            "Please update your browser in order to continue to the site. \n" +
            "Thanks :-)");
        }

    }
]);
