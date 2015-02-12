app.controller('appCtrl', ['$rootScope', '$location',
	function($rootScope, $location){
		$rootScope.$on("$routeChangeError", function () {
			$location.url("/login");
		})
	}
]);