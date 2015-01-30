app.controller('logInController',['$scope','$location','logInService',function($scope,$location,logInService){
    if(localStorage.getItem("email")&& localStorage.getItem("password")){
        data = "user=" + localStorage.getItem("email") + "&password=" + localStorage.getItem("password");
        url = "/log_in";
        logInService.serve(data,url).then(function(status){
            if(status && status.indexOf('false')==-1){
                $location.url("user")
            }
            else alert("Please Log In");
        });
    }
    //localStorage.removeItem("email");
    //localStorage.removeItem("password");
    $scope.formData = {};
    $scope.logIn = function(formData){
        if(formData.email && formData.password){
            data = "user=" + formData.email + "&password=" + formData.password;
            url = "/log_in";
            logInService.serve(data,url).then(function(status){
                if(status && status.indexOf('false')==-1){
                    localStorage.setItem("email", formData.email);
                    localStorage.setItem("password", formData.password);
                    $location.url("user");
                }
                else alert("Either Username or password is incorrect");
            });
        }
        $scope.submitted = true;
    }
}]);





