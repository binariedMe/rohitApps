app.controller('logInController',
    ['$scope', '$location', 'angularPostService','$window','$rootScope',
        function($scope, $location, angularPostService, $window, $rootScope){
            $scope.containerHeight = $window.innerHeight;
            $rootScope.headerText = "Welcome to Rohit App World!";
            $rootScope.backToServerChoice = false;
            if(localStorage) {
                var emailATLS = localStorage.getItem("email");
                var passATLS = localStorage.getItem("password");

                if (emailATLS && passATLS) {
                    var data = "user=" + emailATLS + "&password=" + passATLS;
                    var url = "/log_in";

                    angularPostService.serve(data, url).then(function(userData){
                        console.log(userData);
                        if(userData && userData !=='false') {
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
                $scope.logIn = function(formData){
                    if(formData.email && formData.password){
                        data = "user=" + formData.email + "&password=" + formData.password;
                        url = "/log_in";
                        angularPostService.serve(data, url).then(function(status){
                            if(status && status !== 'false'){
                                localStorage.setItem("email", formData.email);
                                localStorage.setItem("password", formData.password);
                                $location.url("chooseApp");
                            }
                            else {
                                alert("Either Username or password is incorrect");
                            }
                        });
                    }
                    $scope.submitted = true;
                }

            }
            else {
                alert("Your browser does not seem to support the latest technologies used in our site. \n" +
                "Please update your browser in order to continue to the site. \n" +
                "Thanks :-)");
            }

        }]);