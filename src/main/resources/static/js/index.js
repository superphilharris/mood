var app = angular.module('moodApp', []);

app.service('moodService', function($http) {
    this.add = function(mood) {
        return $http.post("mood/add", JSON.stringify(mood));
    }

    this.getAllForToday = function() {
        return $http.get("mood/allForToday");
    }
})

app.controller('moodCtrl', function($scope, $http) {
    $scope.new_mood = {
        comment: "",
        feeling: "BIT_MEH"
    };
    $scope.mood_average = {
        average: 2,
        moods: [],
        closestFeeling: "BIT_MEH"
    }
    $scope.has_added_new_mood = false;

    var showAverage = function() {
        $scope.has_added_new_mood = true;
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
            showAverage();
        });
    }
});


app.filter('newlines', function() {
    return function(text) {
        return text.split(/\n/g);
    };
})

