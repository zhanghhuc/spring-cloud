package com.zssq.util;
/*package com.zssq.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Component
public class MongodbUtil {

	
	
	public MongoClient getMongDb(){
		MongoClient client = null; 
      //  MongoCredential credential = MongoCredential.createCredential("root ", "ydbs", "123456".toCharArray());  

		return  new MongoClient( "172.16.126.195" , 27017 );
      //  ServerAddress addr = new ServerAddress("172.16.126.195", 27017);
        client = new MongoClient(addr,Arrays.asList(credential));  

		 
		return client;
	}
	public static void main(String[] args) {
		MongodbUtil mu=new MongodbUtil();
		MongoClient mongoClient=mu.getMongDb();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("ydbs");
		//MongoCollection<Document> ydbs=mongoDatabase.getCollection("ydbs");
		//System.out.println(ydbs.count());
		  
		 MongoCollection<Document> collection = mongoDatabase.getCollection("ydbs");
		// System.out.println(collection.getNamespace());
		 Document document = new Document("title", "MongoDB").  
		         append("description", "database").  
		         append("likes", 100).  
		         append("by", "Fly");  
		         List<Document> documents = new ArrayList<Document>();  
		         documents.add(document);  
		         collection.insertMany(documents);  
		 
		 
		 collection.insertMany(documents);
		 FindIterable<Document> findIterable = collection.find();  
         MongoCursor<Document> mongoCursor = findIterable.iterator();  
         while(mongoCursor.hasNext()){  
            System.out.println(mongoCursor.next());  
         }  
		 
		 
	      
		ServerAddress serverAddress = new ServerAddress("172.16.126.195", 27017);  
        List<ServerAddress> seeds = new ArrayList<ServerAddress>();  
        seeds.add(serverAddress);  
        MongoCredential credentials = MongoCredential.createMongoCRCredential("root", "ydbs",  
                "123456".toCharArray());  
        List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();  
        credentialsList.add(credentials);  
        
        MongoClient client = new MongoClient(seeds, credentialsList);  
  
        
    	MongoDatabase mongoDatabase = client.getDatabase("dbs");
    	
    	MongoCollection<Document> collection = mongoDatabase.getCollection("ydbs");
    	
    	Document document = new Document("title", "MongoDB").  
    	         append("description", "database").  
    	         append("likes", 100).  
    	         append("by", "Fly");  
    	         List<Document> documents = new ArrayList<Document>();  
    	         documents.add(document);  
    	         collection.insertMany(documents);  
    

	}
	
	
}
*/