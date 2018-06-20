'use strict';
 
angular.module('Home')
 
.controller('HomeController',
    ['$scope', '$rootScope', '$location', 'MoviesService', 
    function ($scope, $rootScope, $location, MoviesService) {
    	
    	MoviesService.getMovieList(function(response) {
    	    if (response.success) {
    	        if (response.data) {
    	        	$scope.movieList = JSON.stringify(response.data);
    	        } else {
    	            $scope.movieList = "Movie was not found !";
    	        }
    	    } else {
    	    	$scope.movieError = "Nu ai drepturi suficiente !";
    	    }
    	});
    	
    	MoviesService.getMusicList(function(response) {
    	    if (response.success) {
    	        if (response.data) {
    	        	$scope.audioList = JSON.stringify(response.data);
    	        } else {
    	            $scope.audioList = "No music was not found !";
    	        }
    	    } else {
    	    	$scope.musicError = "Nu ai drepturi suficiente !";
    	    }
    	});
      
    }]);