Issues --------------------------------------------------------------------------------------------------- 
Invocation of init method failed; nested exception is java.lang.NoSuchFieldError: FSYNCED
Check the pom.xml for correct configuration
Add the dependency
<dependency>
	<groupId>org.springframework.data</groupId>
	<artifactId>spring-data-mongodb</artifactId>
</dependency>

The following method did not exist:
'void com.mongodb.client.internal.MongoClientDelegate.<init>(com.mongodb.connection.Cluster, org.bson.codecs.configuration.CodecRegistry, java.util.List, java.lang.Object, com.mongodb.client.internal.Crypt)'

Remove the following

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

