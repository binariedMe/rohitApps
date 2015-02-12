app.controller('registerController',['$scope', '$location', 'angularPostService', '$http', '_',
    function($scope, $location, angularPostService, $http, _){

        if(localStorage) {
            var emailATLS = localStorage.getItem("email");
            var passATLS = localStorage.getItem("password");

            if (emailATLS && passATLS) {
                var data = "user=" + emailATLS + "&password=" + passATLS;
                var url = "/log_in";

                angularPostService.serve(data, url).then(function (userData) {
                    if (userData && userData.indexOf('false') == -1) {
                        $location.url("user");
                        return;
                    }
                    else {
                        localStorage.removeItem("email");
                        localStorage.removeItem("password");
                    }
                });
            }

            $http.get('/getUsers').success(function(data){
                $scope.Users = data;
            });
            $scope.formData = {};
            $scope.checkDuplicate = function(email){
                if(_.findWhere($scope.Users,{userEmail:email}))
                    $scope.duplicateEmail = true;
                else
                    $scope.duplicateEmail = false;

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
                            if(status){
                                localStorage.setItem("email", formData.email);
                                localStorage.setItem("password", formData.password);
                                $location.url("user")
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
