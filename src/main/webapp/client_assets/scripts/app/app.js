app.controller('appCtrl', ['$rootScope', '$location','$scope','$window',
	function($rootScope, $location, $scope, $window){

		$rootScope.$on("$routeChangeError", function () {
			$location.url("/login");
		});

		/*
		 The logout implementation as of now just removes the data
		 stored in local storage.
		 */
		$rootScope.logOUT = function(){
			localStorage.removeItem("email");
			localStorage.removeItem("password");
			$location.url('/login');
		};

		$rootScope.headerText = "Welcome to Rohit Apps World!";
		$rootScope.showLogoutBtn = false;

		$rootScope.containerHeight = $window.innerHeight - 190 - 10;
	}

]);