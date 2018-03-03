## Mood Tracker for Team
This will record the team's mood for the day.
Each person is able to only record one feeling for the day,
and an optional comment.
Once the day expires then the team member can record another feeling.

After the person has recorded their feeling, then they can see the overall team mood.



## Technologies Used
* Spring Boot generated from https://start.spring.io
  * Spring MVC
  * JPA
* MySQL 14.14 Distrib 5.6.33
* AngularJS 1.4.4
* Bootstrap 3.3.7
* Maven 3.5.2
* Java 1.8.0-161


## Installation Instructions
1. Ensure that you have at least the above technologies installed on a linux box (Excluding angular and bootstrap)
2. cd to the root directory folder mood
3. Run ./install.sh, and provide the mysql credentials
4. Run the command `mvn spring-boot:run`
5. Navigate to http://localhost:8080

