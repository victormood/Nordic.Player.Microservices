'use strict';
 
angular.module('Home')
 
.controller('HomeController',
    ['$scope', '$rootScope', '$location', 'AccountsService', 
    function ($scope, $rootScope, $location, AccountsService) {
    	
    	 $scope.getAccountInfo = function () {
             $scope.dataLoading = true;
             
             AccountsService.getAccountByNumber($scope.accountNo, function(response) {
                 if(response.success) {
                	 if(response.data) {
	                     $scope.results = JSON.stringify(response.data);
	                 } else {
	                	 $scope.results = "Account was not found !";
	                 }
                 
                     $scope.dataLoading = false;
                 } else {
                	 $location.path('/login');
                 }
             });
         };
      
    }]);