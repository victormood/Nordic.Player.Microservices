'use strict';
 
angular.module('Home')
 
.factory('MoviesService',
    ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout',
    function (Base64, $http, $cookieStore, $rootScope, $timeout) {
        var service = {};

        service.getMovieList = function (callback) {
        	const headerDict = {
			  'Authorizatione': $http.defaults.headers.common['Authorization']
			}

			const requestOptions = {                                                                                                                                                                                 
			  headers: new Headers(headerDict), 
			};
        	
            $http.get('/movies/list', requestOptions)
                .success(function (data, status, headers, config) {
                	var response = { success: true, data: data};
                    callback(response);
                })
                .error(function (data, status, headers, config) {
               	 	var response = { success: false };
                	callback(response);
                });

        };

        service.getMusicList = function (callback) {
        	const headerDict = {
			  'Authorizatione': $http.defaults.headers.common['Authorization']
			}

			const requestOptions = {                                                                                                                                                                                 
			  headers: new Headers(headerDict), 
			};
        	
            $http.get('/audios/list', requestOptions)
                .success(function (data, status, headers, config) {
                	var response = { success: true, data: data};
                    callback(response);
                })
                .error(function (data, status, headers, config) {
               	 	var response = { success: false };
                	callback(response);
                });

        };
        
        return service;
    }])
;