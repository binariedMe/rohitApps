# Kafka connection layer
This service layer is for producers and consumers to connect to kafka server.

 1. Prerequisite :
 You must have java version 8 installed and available from console/command prompt.
 
 2. How to run the project :
 You can run the application using command 
 

    >java -jar path/to/application-jar
    
 3. Access policy :
A user/system needs to be authorized to either produce or consume message for a specific topic.

 4. Configuration changes :
 You can change properties by editing the file config.properties lying inside the folder "properties"
1. username=password
2. username_topics=list-of-topics-separated-by-comma
3. username_roles=list-of-roles-separated-by-comma
4. secretkey=a-key-to-encrypt-user-credential
5. topic_servers=list-of-servers-for-a-topic-separated-by-comma-in-the-format-host:ip

 5. Rest endpoint exposed :
1. url : "/user/login", method : post, body : {username : username , password : password}, headers : {content-type : application/json}, response : {token : token}
2. url : "/api/produce/{topic}" , method : post, body : message , content-type : application/json, headers : {content-type : application/json, common : {Authorization : "Bearer " + token}}, response : true/false
 

