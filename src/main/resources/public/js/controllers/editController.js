angular.module('app.editController',['ui.bootstrap']).controller('VinylEditController', function($scope, $state, $stateParams, Vinyl, Genre) {
	
  //GET Genres 
  $scope.genres = Genre.query();  
  $scope.modalShown = false;
  
  //Call function remove genre
  $scope.removeGenre = function() {removeGenre($scope);};
  
  //Call function add genre
  $scope.addGenre = function(obj){addGenre(obj,$scope);};
	
  $scope.toggleModal = function() {	
    $scope.modalShown = !$scope.modalShown;    
  };
	  
  $scope.updateVinyl = function() { //Update the edited vinyl. Issues a PUT to /vinyls/:id
		
	$scope.vinyl.imageSrcFront = document.getElementById("thumbFront").src;		
	$scope.vinyl.imageSrcBack = document.getElementById("thumbBack").src;	
		
    $scope.vinyl.$update(function() {
      $state.go('vinyls'); // on success go back to the list i.e. vinyls state.
    });
  };
     
  $scope.loadVinyl = function() { //Issues a GET request to /vinyls/:id to get a vinyl to update
    $scope.vinyl = Vinyl.get({ id: $stateParams.id });
  };

  $scope.loadVinyl(); // Load a vinyl which can be edited on UI
});