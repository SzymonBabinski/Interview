# 1. Building
To build this application use following command:

mvn clean package 

# 2. Running
After building the application run following command to start it:

java -jar <JarName.jar>
  
e.g. java -jar library-0.0.1-SNAPSHOT.jar
  
# 3. Testing
To run test use command below:
  
mvn test
  
# 4. Endpoints
| Endpoint | Description  |
| ------------- |:-------------:|
| /lectures/{lectureId}/reservations (POST) | makes reservation of given user(json) on lecture|
| /lectures/{lectureId}/reservations (DELETE)| delete reservation of given user(json) on lecture|
| /schedules (GET) | get schedule of conference |
| /users/lectures/{login} (GET) | get all user login based on his login|
| /users (GET) | get all users|

  
# 5. E.g endpoints

 1. (GET) /users
 2. (GET) /schedules
 3. (GET) /users/lectures/user1
 4. (POST) /lectures/9/reservations json:     "login":"user6",
                                               "email":"user6@gmail.pl"
 5. (DELETE) /lectures/1/reservations json:     "login":"user1",
                                                "email":"user1@gmail.pl"

  
  
  
  

  
  
  
  