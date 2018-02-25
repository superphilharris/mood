var app = angular.module('moodApp', []);

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
    $scope.current_view = "TRY_TOMORROW"// "ADD_NEW_MOOD";

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
            $scope.showAverage();
        });
    }
});


app.filter('newlines', function() {
    return function(text) {
        return text.split(/\n/g);
    };
})

