(function() {
	var app = angular.module('app', ['ui.router', 'navController', 'ngAnimate', 'ui.bootstrap', 'ngResource', 'app.createController',
	                                 'app.editController','app.viewController','app.listController', 'app.uploadController', 'app.services'])
		
	// define for requirejs loaded modules
	define('app', [], function() { return app; });

	// function for dynamic load with requirejs of a javascript module for use with a view
	// in the state definition call add property `resolve: req('/views/ui.js')`
	// or `resolve: req(['/views/ui.js'])`
	// or `resolve: req('views/ui')`
	function req(deps) {
		if (typeof deps === 'string') deps = [deps];
		return {
			deps: function ($q, $rootScope) {
				var deferred = $q.defer();
				require(deps, function() {
					$rootScope.$apply(function () {
						deferred.resolve();
					});
					deferred.resolve();
				});
				return deferred.promise;
			}
		}
	}
	
	
	app.config(function($stateProvider, $urlRouterProvider, $controllerProvider){
		var origController = app.controller
		app.controller = function (name, constructor){
			$controllerProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|tel|file|blob):/);
			$controllerProvider.register(name, constructor);
			return origController.apply(this, arguments);
		}
		
		var viewsPrefix = 'views/';

		// For any unmatched url, send to /
		$urlRouterProvider.otherwise("/")

		$stateProvider
			// you can set this to no template if you just want to use the html in the page
			.state('home', {
					url: "/",
					templateUrl: viewsPrefix + "home.html",
					data: {
						pageTitle: 'Home'
					}
			}).state('vinyls',{
		        url:'/vinyls',
		        templateUrl: viewsPrefix + 'vinyls.html',
		        controller:'VinylListController'
		    }).state('viewVinyl',{
		       url:'/vinyls/:id/view',
		       templateUrl: viewsPrefix + 'vinyls-view.html',
		       controller:'VinylViewController'
		    }).state('newVinyl',{
		        url:'/vinyls/new',
		        templateUrl: viewsPrefix + 'vinyls-add.html',
		        controller:'VinylCreateController'
		    }).state('editVinyl',{
		        url:'/vinyls/:id/edit',
		        templateUrl: viewsPrefix + 'vinyls-edit.html',
		        controller:'VinylEditController'
		    }).state('uploadVinyl',{
		        url:'/vinyls',
		        templateUrl: viewsPrefix + 'vinyls.html',
		        controller:'VinylListController'
		    });
	})
	.directive('updateTitle', ['$rootScope', '$timeout',
		function($rootScope, $timeout) {
			return {
				link: function(scope, element) {
					var listener = function(event, toState) {
						var title = 'Project Name';
						if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle + ' - ' + title;
						$timeout(function() {
							element.text(title);
						}, 0, false);
					};

					$rootScope.$on('$stateChangeSuccess', listener);
				}
			};
		}
	])
	.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);                                                
                  var modelSetter = model.assign;
                  
                  element.bind('change', function(){
                     scope.$apply(function(){                    	                     	                    	 
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            }}])
	.directive('fileRead', [ function () {
	    return {
	        scope: {
	            fileread: '='
	        },
	        link: function (scope, element, attributes) {	        		        
	        	        	
	            element.bind('change', function (changeEvent) {	   
	            		            		            
            		var f = changeEvent.target.files[0];	            		            		            		
            		var reader = new FileReader();         	
            			
	                //Se lanza el evento
	            	reader.onload = (function(theFile) {
	            		
	                 return function(e) {  				                    			                    		                    	
	                    
	                 if(!theFile.name.includes(".csv")){		                    			                    	                   
		                    var thumbFront = document.getElementById("thumbFront");
		                    var thumbBack = document.getElementById("thumbBack");
		                    var thumbFrontAdded = false;
		              		               
		                    //Validamos que no tenga img asociada
		                    if(thumbFront.src.includes('/images/img_not_available.png')){
		                    	thumbFrontAdded = true;
		                    	thumbFront.src =  e.target.result;
		                    }
		                    		      
		                    //Validamos que no tenga img asociada
		                    if(thumbBack.src.includes('/images/img_not_available.png') && !thumbFrontAdded){		                    	
		                    	thumbBack.src =  e.target.result;
		                    }
	                    }		                    	
	                  };
	                })(f);      
	            			  
	                if(f){ 	
	                	scope.fileread = f ? f : 'undefined';
	            		if(f.name.includes(".csv")){		            		
	            			reader.readAsBinaryString(f);
	            		}else{		            			  
	            			reader.readAsDataURL(f);
	            		}		            				            		
	            	}	           	
	            		            	               	              	        
	            });
	        }
	    }
	}])
	.directive('modalDialog', function() {
		  return {
		    restrict: 'E',
		    scope: {
		      show: '='
		    },
		    replace: true, // Replace with the template below
		    transclude: true, // we want to insert custom content inside the directive
		    link: function(scope, element, attrs) {
		      scope.dialogStyle = {};
		      if (attrs.width)
		        scope.dialogStyle.width = attrs.width;
		      if (attrs.height)
		        scope.dialogStyle.height = attrs.height;
		      scope.hideModal = function() {
		        scope.show = false;		    	
		      };
		    },
		    template: '<div class="ng-modal" ng-show="show">'
			    		+ '<div class="ng-modal-dialog" ng-style="dialogStyle">'
			    		+		'<div class="ng-modal-dialog-content" ng-transclude>'			    
			    		+		'</div>'
			     		+  '</div>'
		     		    +'</div>' // See below
		  };
		})	
	.filter('startFrom', function () {
		return function (input, start) {
			if (input) {
				start = +start;
				return input.slice(start);
			}
			return [];
		};
	})
}()
);
