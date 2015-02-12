app.service(
	"angularPostService",
	['$http', '$q', function($http, $q) {

		return({
			serve: serve
		});

		function serve(data, url) {
			var request = $http({
				method: 'post',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				url: url,
				data: data
			});

			return( request.then( handleSuccess, handleError ) );

		}
		function handleError( response ) {
			if (! angular.isObject( response.data ) || ! response.data.message) {
				return( $q.reject( "An unknown error occurred." ) );
			}
			return( $q.reject( response.data.message ) );
		}
		function handleSuccess( response ) {
			return( response.data );
		}
	}]
);