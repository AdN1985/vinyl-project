angular.module('app.services', []).factory('Vinyl', function($resource) {
  return $resource('/vinyls/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).factory('Genre',function($resource){	
	return $resource('/genres/:id', { id: '@id' }, {
	    update: {
	      method: 'PUT'
	    }
	  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
	   

