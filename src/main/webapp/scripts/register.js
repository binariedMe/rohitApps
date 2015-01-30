app.controller('registerController',['$scope','$location','logInService','$http','_',
    function($scope, $location,logInService,$http,_){
        if(localStorage.getItem("email")&& localStorage.getItem("password")){
            data = "user=" + localStorage.getItem("email") + "&password=" + localStorage.getItem("password");
            url = "/log_in";
            logInService.serve(data,url).then(function(status){
                if(status){
                    $location.url("user")
                }
                else alert("Please Log In");
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
                if(formData.password != formData.rePass)
                {
                    alert("Password and Repeat Password field do not match...");
                    return;
                }
                else if(!$scope.duplicateEmail && formData.password == formData.rePass){
                data = "user=" + formData.email + "&password=" + formData.password + "&name="+ formData.name;
                url = "/register";
                logInService.serve(data,url).then(function(status){
                    if(status){
                        localStorage.setItem("email", formData.email);
                        localStorage.setItem("password", formData.password);
                        $location.url("user")
                    }
                    else alert("Registration Unsuccessful");
                });
            }
            }

            $scope.submitted = true;
        }
    }]);
