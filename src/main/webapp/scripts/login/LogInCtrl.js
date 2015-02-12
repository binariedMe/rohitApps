app.controller('logInController',
    ['$scope', '$location', 'angularPostService',
        function($scope, $location, angularPostService){

            if(localStorage) {
                var emailATLS = localStorage.getItem("email");
                var passATLS = localStorage.getItem("password");

                if (emailATLS && passATLS) {
                    var data = "user=" + emailATLS + "&password=" + passATLS;
                    var url = "/log_in";

                    angularPostService.serve(data, url).then(function(userData){
                        if(userData && userData.indexOf('false')==-1) {
                            $location.url("user");
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
                            if(status && status.indexOf('false')==-1){
                                localStorage.setItem("email", formData.email);
                                localStorage.setItem("password", formData.password);
                                $location.url("user");
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