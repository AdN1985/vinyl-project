angular.module('app.viewController',['ui.bootstrap']).controller('VinylViewController', function($scope, $stateParams, Vinyl) {
	$scope.vinyl = Vinyl.get({ id: $stateParams.id }); //Get a single vinyl.Issues a GET to /vinyls/:id
});

//Function REMOVE GENRE
function removeGenre($scope) {	    	
	  var objSpanGenre1 = document.getElementById("spanGenre1");  
	  var objSpanGenre2 = document.getElementById("spanGenre2");
	  var objDivGenres  = document.getElementById("divGenres");
	  
	  objDivGenres.style.visibility = "hidden";
	  
	  $scope.vinyl.genre1 = null;	  
	  $scope.vinyl.genre2 = null;
	  
	  objSpanGenre1.innerText = null;
	  objSpanGenre2.innerText = null;
	  
	 
};

//Function ADD GENRE
function addGenre(obj, $scope){
	  
	  var objDivGenres  = document.getElementById("divGenres");
	  var objSpanGenre1 = document.getElementById("spanGenre1");  
	  var objSpanGenre2 = document.getElementById("spanGenre2");
	  
	  objDivGenres.style.visibility = "visible";
	  
	  //Look if genre exist
	  if(objSpanGenre1.innerText == ''){
		  $scope.vinyl.genre1 = obj.genre;
		  objSpanGenre1.innerText = obj.genre.name;	
		  return;
	  }	  	  
	  
	  if(objSpanGenre2.innerText == '' && objSpanGenre1.innerText != null && (obj.genre.name != objSpanGenre1.innerText)){
		  $scope.vinyl.genre2= obj.genre;
		  objSpanGenre2.innerText = obj.genre.name;
	  }			 
};