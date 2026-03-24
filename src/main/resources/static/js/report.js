String.prototype.trim=function(){return this.replace(/^\s+|\s+$/g, '');};

var newadApp = angular.module('newadApp', []);

newadApp.controller('EmailCtrl', ['$scope', '$timeout', 'EmailService',
  function ($scope, $timeout, EmailService) {
    $scope.formSubmitable = false;
    var user_email;
    $scope.reportSent = false;
    $scope.reportNeeded = {
      tzym: true, jsym: true, gzdb: true, dxlx: true, zxlx: true
    };

    $scope.checkForm = function() {
      var user_name = $scope.user_name;
      user_email = $scope.user_email;
      var user_phone = $scope.user_phone;

      if(user_name && user_email && user_phone) {
        $scope.formSubmitable = true;
      } else {
        $scope.formSubmitable = false;
      }
    };

    $scope.submit = function() {
      var data = {'email': user_email,
                  'reportNeeded': $scope.reportNeeded};
      EmailService.sendEmail(data)
        .success(function (data) {
          console.log(data);
        });
      $scope.reportSent = true;
      $timeout(function () {
        $scope.reportSent = false;
      }, 4000);
    };

    $scope.change = function(str) {
      switch(str) {
        case 'tzym':
        $scope.reportNeeded.tzym = !$scope.reportNeeded.tzym;
        break;

        case 'jsym':
        $scope.reportNeeded.jsym = !$scope.reportNeeded.jsym;
        break;

        case 'gzdb':
        $scope.reportNeeded.gzdb = !$scope.reportNeeded.gzdb;
        break;

        case 'dxlx':
        $scope.reportNeeded.dxlx = !$scope.reportNeeded.dxlx;
        break;

        case 'zxlx':
        $scope.reportNeeded.zxlx = !$scope.reportNeeded.zxlx;
        break;

        default:
        break;
      }
    };
  }
]);

newadApp.factory('EmailService', function ($http) {
  return {
    sendEmail: function (data) {
      return $http.post('/reports', data);
    }
  };
});