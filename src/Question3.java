import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Question3 {
    public static void main(String[] args) {
        MongoClient c =  new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");

        try {
            Document animal = new Document("animal", "monkey");

            animals.insertOne(animal);
            System.out.println(animal);
            animal.remove("animal");
            System.out.println(animal);
            animal.append("animal", "cat");
            System.out.println(animal);
            animals.insertOne(animal);
            //animal.remove("animal");
            //animal.append("animal", "lion");
            //animals.insertOne(animal);
        } catch(MongoWriteException e) {
            System.out.println("Duplicate key error index");
        }
    }
}