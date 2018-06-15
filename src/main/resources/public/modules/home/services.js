'use strict';
 
angular.module('Home')
 
.factory('AccountsService',
    ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout',
    function (Base64, $http, $cookieStore, $rootScope, $timeout) {
        var service = {};

        service.getAccountByNumber = function (accountNo, callback) {
        	const headerDict = {
			  'Authorizatione': $http.defaults.headers.common['Authorization']
			}

			const requestOptions = {                                                                                                                                                                                 
			  headers: new Headers(headerDict), 
			};
        	
            $http.get('http://localhost:2222/accounts/' + accountNo, requestOptions)
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