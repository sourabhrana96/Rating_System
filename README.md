# OYE-RICKSHAW Ride Rating System.  
This project is made using java Spring boot and postgres database.  

Following APIs are implemented:    

1. To create a new ride.  
2. To allow driver to rate a passenger after ride completion.  
3. To allow passenger to rate a driver after ride completion.
3. A driver or passenger can get his aggregate ride ratings.  

Given google doc contains all the APIs: https://docs.google.com/document/d/1z5hjIyoM1AxqzFe1egGshU_wcWUj7mR0-t7su6_iSv8/edit  

### Solution Approach:  
1. First we create a ride using driverId and passengerId with status ON_GOING.  
2. If driver or passenger is in another ride i.e if there is any ride exist in which status is ON_GOING for the given driver or passenger then a new ride cannot be created.  
3. After ride is completed , passenger can rate a ride given passenger id, driver id, and rating in range of 1-5 stars if any ride exists between them.  
4. We update the aggregate rating by fetching driver entity/passenger entity for that driver Id/passenger Id and incrementing the rating count by dividing aggregateRating with new increment count.  
5. So after every rating we have aggregate rating stored in our Driver and Passenger tables of our database.  
6. To fetch rating of driver or passenger we use params driverId or passengerId and get the aggregate ratings info for the driver or passenger.

## Database Design:  
This project contains three relations(tables) i.e Driver, Passenger, Ride.  

![Image of Database Schema](https://github.com/sourabhrana96/images/blob/master/rating_system_db_desing.png).  

### Project Setup:
1. Install java 8 and postgres on your system.  

2. Run following commands on terminal to setup postgres databaes:  
           sudo -u postgres psql
    postgres=# create database mydb;  
    postgres=# create user myuser with encrypted password 'mypass';  
    postgres=# grant all privileges on database mydb to myuser;  
    
3. Update application.properties file with your database credentials.  

4. Execute following commands project directory on terminal to run the application:  
    first build : mvn -T 1C clean install -f pom.xml -DskipTests  
    then run : mvn spring-boot:run -Dspring.profiles.active=dev -T 1C
