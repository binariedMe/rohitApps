app.controller('appCtrl', ['$rootScope', '$location','$scope','$window',
	function($rootScope, $location, $scope, $window){
		$rootScope.$on("$routeChangeError", function () {
			$location.url("/login");
		});
        $scope.containerHeight = $window.innerHeight;
        /*$scope.loadingViewHeight = $window.innerHeight;
        console.log($scope.loadingViewHeight);*/
	}
]);