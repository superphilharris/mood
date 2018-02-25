#!/bin/sh

mysql_user=root
mysql_pass=root
database_name=mood

# Get the username and password
echo "What is the mysql username? (Default=$mysql_user)"
read user
if [ "$user" != "" ]; then
	mysql_user=$user
fi

echo "What is the mysql password? (Default=$mysql_pass)"
read pass
if [ "$pass" != "" ]; then
	mysql_pass=$pass
fi


# Now lets create the database
echo "Using user=$mysql_user, password=$mysql_pass to create the database"
if `which mysql > /dev/null`; then
	mysql -u$mysql_user -p$mysql_pass -e "create database $database_name"
	mysql -u$mysql_user -p$mysql_pass $database_name < mood.sql
	# Now lets update the application properties file
        sed -i "s/spring\\.datasource\\.username.*/spring.datasource.username=$mysql_user/g" src/main/resources/application.properties
	sed -i "s/spring\\.datasource\\.password.*/spring.datasource.password=$mysql_pass/g" src/main/resources/application.properties
	sed -i "s|spring\\.datasource\\.url.*|spring.datasource.url=jdbc:mysql:\/\/localhost:3306\/$database_name|g" src/main/resources/application.properties 
else 
	echo "You do not have a mysql client installed. Please install"
fi


