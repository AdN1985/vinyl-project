angular.module('app.createController', ['ui.bootstrap']).
controller('VinylCreateController', function($scope, $state, $stateParams, Vinyl, Genre) {
	
  //GET Genres 
  $scope.genres = Genre.query();  
  $scope.modalShown = false;
	  
  $scope.vinyl = new Vinyl();  //create new vinyl instance. Properties will be set via ng-model on UI
  
  //Call function remove genre
  $scope.removeGenre = function() {removeGenre($scope);};
  
  //Call function add genre
  $scope.addGenre = function(obj){addGenre(obj,$scope);};
  
  $scope.toggleModal = function() {
    $scope.modalShown = !$scope.modalShown;    
  };
  
  $scope.addVinyl = function() { //create a new vinyl. Issues a POST to /vinyls	  	

	$scope.vinyl.imageSrcFront = document.getElementById("thumbFront").src;		
	$scope.vinyl.imageSrcBack = document.getElementById("thumbBack").src;	
			
    $scope.vinyl.$save(function() {    	  
      $state.go('vinyls'); // on success go back to the list i.e. vinyls state.
    });
  }
 }
 );