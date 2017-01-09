 angular.module('app.uploadController',['ui.bootstrap']).
 controller('VinylUploadController', function($scope, $http, Vinyl, $state) {
		 
  //Function upload vinyls
  $scope.uploadVinyls = function(){
	  	   
	  var fd = new FormData();
      fd.append('file', $scope.myFile);
      
      $http.post('vinyls/upload', fd, {
          transformRequest: angular.identity,
          headers: {'Content-Type': undefined}
      }).then(function successCallback(response) {        	  
          $state.go('uploadVinyl');                   
      }, function errorCallback(response) {    	  
    	  alert("Vinyls NOT uploaded: " + response.data);
      });
	  	
	}   
   });