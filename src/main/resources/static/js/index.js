var app = angular.module('moodApp', ['ngCookies']);

app.service('moodService', function($http) {
    this.add = function(mood) {
        return $http.post("mood/add", JSON.stringify(mood));
    }

    this.getAllForToday = function() {
        return $http.get("mood/allForToday");
    }
})

app.controller('moodCtrl', function($scope, $http, $cookies) {
    $scope.new_mood = {
        comment: "",
        feeling: "BIT_MEH"
    };
    $scope.mood_average = {
        average: 2,
        moods: [],
        closestFeeling: "BIT_MEH"
    }
    console.log($cookies.get('addedMood'))
    $scope.current_view = "TRY_TOMORROW"// "ADD_NEW_MOOD";

    var addFeelingToCookie = function(new_feeling){
        var expireDate = new Date();
        expireDate.setDate(expireDate.getDate() + 1)
        $cookies.put('addedMood', new_feeling, { 'expires': expireDate });
    }

    $scope.showAverage = function() {
        $scope.current_view = "AVERAGE_MOOD";
        $http.get("mood/averageForToday").error(function(data){
            console.error(JSON.stringify(data))
        }).success(function(response){
            $scope.mood_average = response;
        });
    }
    $scope.addNewMood = function() {
        $http.post("mood/add", $scope.new_mood).error(function(data){
            console.log("ERROR: " + JSON.stringify(data));
        }).success(function(data){
            addFeelingToCookie($scope.new_mood.feeling);
            $scope.showAverage();
        });
    }
});


app.filter('newlines', function() {
    return function(text) {
        return text.split(/\n/g);
    };
})

