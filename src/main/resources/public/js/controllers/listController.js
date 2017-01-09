angular.module('app.listController', ['ui.bootstrap']).
 controller('VinylListController', function($scope, $state, popupService, $window, Vinyl, filterFilter) {
		 	 	 
	 // create empty search model (object) to trigger $watch on update	 
	  $scope.search = {};
	  
	  //fetch all vinyls. Issues a GET to /vinyls		
	  $scope.vinyls = Vinyl.query(function() {
		// pagination controls
		$scope.currentPage = 1;
		$scope.totalItems = $scope.vinyls.length;		
		$scope.entryLimit = 8; // items per page
		$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);	
		
		// $watch search to update pagination
		$scope.$watch('search', function (newVal, oldVal) {					
			$scope.filteredVinyls = filterFilter($scope.vinyls, newVal);		
			$scope.totalItems = $scope.filteredVinyls.length;	
			$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
			$scope.currentPage = 1;
		}, true);			  		
  }); 
	  
   
  //Function delete vinyls
  $scope.deleteVinyl = function(vinyl) { // Delete a Vinyl. Issues a DELETE to /vinyls/:id
    if (popupService.showPopup('Really delete '+ vinyl.title + ' ?')) {
    	vinyl.$delete(function() {       
        $scope.vinyls = Vinyl.query();      		        
        $state.go('vinyls');
      });
    }
  }; 
  
   
  $scope.exportVinyls = function(args) {	
	
	  var strHeadersCSV = "Title;Artist;Edited;Genre_1;Genre_2;\n";
	  var strContentCSV = "";
	  
	  for (i = 0; i < $scope.vinyls.length; i++) {
		  v = $scope.vinyls[i];
		  strContentCSV += v.title + ";" + v.artist + ";" + v.edited + ";" + 
		  ((v.genre1 != null) ? (v.genre1.name + ";") : ";") + ((v.genre2 != null) ? (v.genre2.name + ";") : ";");
		  strContentCSV += "\n";		  
	  }
	  
	  strVinylsCSV = strHeadersCSV + strContentCSV;
	  		 
	  var data, filename, link;
      var csv =  strVinylsCSV;
     
      if (csv == null) return;

      filename = args.filename;

      if (!csv.match(/^data:text\/csv/i)) {
          csv = 'data:text/csv;charset=utf-8,' + csv;
      }
      data = encodeURI(csv);

      link = document.createElement('a');
      link.setAttribute('href', data);
      link.setAttribute('download', filename);
      link.click();		  
 };
 
 });