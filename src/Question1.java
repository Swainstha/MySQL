import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.Random;
import org.bson.Document;


public class Question1 {
    public static void main(String[] args) {
        MongoClient c = new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> numbers = db.getCollection("numbers");

        Random rand = new Random();
        final int len = 10000;
        int first, second, third;

        for (int i = 0; i < len; i++) {

            first = rand.nextInt(10000) + 3000;
            second = rand.nextInt(10000) + 3000;
            third = rand.nextInt(10000) + 3000;
            Document number = new Document("first_number", first).append("second_number", second).
                    append("third_number", third);
            numbers.insertOne(number);
        }

    }
}