var app = angular.module('moodApp', ['ngCookies']);

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
    $scope.current_view = ($cookies.get('LAST_FEELING') == null)? "ADD_NEW_MOOD" : "TRY_TOMORROW";

    var getTonight = function() {
        var tonight = new Date();
        tonight.setDate(tonight.getDate() + 1)
        tonight.setHours(0,0,0,0)
        return tonight;
    }
    var setFeelingCookie = function(feeling){
        $cookies.put('LAST_FEELING', feeling, { expires: getTonight() })
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
            setFeelingCookie($scope.new_mood.feeling)
            $scope.showAverage();
        });
    }
});


app.filter('newlines', function() {
    return function(text) {
        return text.split(/\n/g);
    };
})

