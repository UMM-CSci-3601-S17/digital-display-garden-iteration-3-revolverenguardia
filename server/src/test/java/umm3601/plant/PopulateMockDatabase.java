package umm3601.plant;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.*;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.Before;
import umm3601.digitalDisplayGarden.PlantController;

import javax.print.Doc;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class PopulateMockDatabase {

    private final static String databaseName = "data-for-testing-only";

    public PopulateMockDatabase(){

    }

    private PlantController plantController;
    private String begoniaIdString;
    public String hexAlternantheraID;

    public void clearAndPopulateDB() throws IOException {
    }

    public void clearAndPopulateDBAgain() throws IOException {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        MongoCollection plants = db.getCollection("plants");
        MongoCollection beds = db.getCollection("beds");
        db.drop();
        plantController = new PlantController(databaseName);


        MongoCollection config = db.getCollection("config");
        config.insertOne(new Document().append("liveUploadId", "first uploadId"));


        //First Plant Alternanthera
        Document alternanthera = new Document();
        alternanthera.append("_id", new ObjectId("58d1c36efb0cac4e15afd202"));
        alternanthera.append("uploadId", "first uploadId");

        alternanthera.append("commonName", "Alternanthera");
        alternanthera.append("cultivar", "Experimental");
        alternanthera.append("gardenLocation", "10.0");
        alternanthera.append("Comments", "Name change from Purple Prince   14x18 spreader");
        alternanthera.append("HBHangBasketCContainerWWall", "");
        alternanthera.append("id", "16001.0");
        alternanthera.append("source", "PA");
        alternanthera.append("SSeedVVeg", "S");

//        BsonArray visits = new BsonArray();
//        ObjectId visit1 = new ObjectId("58e48858efbd6027e34b4c52");
//        visits.add(new BsonObjectId(visit1));

        Document metadataDoc = new Document();
        metadataDoc.append("pageViews", 0);
        metadataDoc.append("ratings", new BsonArray());
        metadataDoc.append("visits", new BsonArray());

        
        alternanthera.append("metadata", metadataDoc);
        //alternanthera.append("garden", "hello!");
        plants.insertOne(alternanthera );

        //Second Plant Begonia
        Document begonia = new Document();
        begonia.append("_id", new ObjectId("58d1c36efb0cac4e15afd203"));
        begonia.append("uploadId", "first uploadId");

        begonia.append("commonName", "Begonia");
        begonia.append("cultivar", "Megawatt Rose Green Leaf");
        begonia.append("gardenLocation", "10.0");
        begonia.append("Comments", "Grow in same sun or shade area; grow close proximity to each other for comparison");
        begonia.append("HBHangBasketCContainerWWall", "");
        begonia.append("id", "16008.0");
        begonia.append("source", "PA");
        begonia.append("SSeedVVeg", "S");

        Document metadataDoc1 = new Document();
        metadataDoc1.append("pageViews", 0);
        metadataDoc1.append("ratings", new BsonArray());


        begonia.append("metadata", metadataDoc1);
        plants.insertOne(begonia);


        //First Garden Location bed7.0
        Document bed7 = new Document();
        bed7.append("_id", new ObjectId("58d1c36efb0cac4e15afd302"));
        bed7.append("gardenLocation", "7.0");

        Document bedMetadataDoc = new Document();
        bedMetadataDoc.append("pageViews", 0);
        bedMetadataDoc.append("qrScans", 0);
        bedMetadataDoc.append("bedVisits", new BsonArray());
        bedMetadataDoc.append("qrVisits", new BsonArray());


        bed7.append("uploadId", "first uploadId");
        bed7.append("metadata", bedMetadataDoc);
        beds.insertOne(bed7);

        Document bed1S = new Document();
        bed1S.append("_id", new ObjectId("58d1c36efb0cac4e15afd304"));
        bed1S.append("gardenLocation", "1S");

         bedMetadataDoc = new Document();
        bedMetadataDoc.append("pageViews", 10);
        bedMetadataDoc.append("qrScans",0);
        bedMetadataDoc.append("bedVisits", new BsonArray()); //TODO should have 10 {visit : objectId}'s in here but for the test I'm writing rn don't need
        bedMetadataDoc.append("qrVisits", new BsonArray());


        bed1S.append("uploadId", "first uploadId");
        bed1S.append("metadata", bedMetadataDoc);
        beds.insertOne(bed1S);


        //Second Garden Location bed10.0
        Document bed10 = new Document();
        bed10.append("_id", new ObjectId("58d1c36efb0cac4e15afd303"));
        bed10.append("gardenLocation", "10.0");


        Document bedMetadataDoc1 = new Document();
        bedMetadataDoc1.append("pageViews", 0);
        bedMetadataDoc1.append("qrScans", 0);
        bedMetadataDoc1.append("bedVisits", new BsonArray());
        bedMetadataDoc1.append("qrVisits", new BsonArray());

        bed10.append("uploadId", "second uploadId");
        bed10.append("metadata", bedMetadataDoc1);
        beds.insertOne(bed10);

        //Third Plant Dianthus
        Document dianthus = new Document();
        dianthus.append("_id", new ObjectId("58d1c36efb0cac4e15afd204"));
        dianthus.append("uploadId", "second uploadId");

        dianthus.append("commonName", "Dianthus");
        dianthus.append("cultivar", "Jolt™ Pink F1");
        dianthus.append("gardenLocation", "7.0");
        dianthus.append("Comments", "");
        dianthus.append("HBHangBasketCContainerWWall", "");
        dianthus.append("id", "16040.0");
        dianthus.append("source", "AAS");
        dianthus.append("SSeedVVeg", "S");

        Document metadataDoc2 = new Document();
        metadataDoc2.append("pageViews", 0);
        metadataDoc2.append("ratings", new BsonArray());

        dianthus.append("metadata", metadataDoc2);
        plants.insertOne(dianthus);

        //Fourth Plant PlantFour
        Document plantFour = new Document();
        plantFour.append("_id", new ObjectId("58d1c36efb0cac4e15afd278"));
        plantFour.append("uploadId", "second uploadId");

        plantFour.append("commonName", "PlantFour");
        plantFour.append("cultivar", "Some Plant");
        plantFour.append("gardenLocation", "12");
        plantFour.append("id", "16040.0");

        Document metadataDoc3 = new Document();
        metadataDoc3.append("pageViews", 0);
        metadataDoc3.append("ratings", new BsonArray());

        plantFour.append("metadata", metadataDoc3);
        plants.insertOne(plantFour);

        //for testing getCommonNamesJSON (third uploadID)
        //Fifth Plant PlantFive
        Document plantFive = new Document();
        plantFive.append("_id", new ObjectId("58d1c36efb0cac4e15afd280"));
        plantFive.append("uploadId", "third uploadId");

        plantFive.append("commonName", "Tulip");
        plantFive.append("cultivar", "Red");
        plantFive.append("gardenLocation", "20");
        plantFive.append("id", "16080.0");

        Document metadataDoc4 = new Document();
        metadataDoc4.append("pageViews", 0);
        metadataDoc4.append("ratings", new BsonArray());

        plantFive.append("metadata", metadataDoc4);
        plants.insertOne(plantFive);

        //Sixth Plant PlantSix
        Document plantSix = new Document();
        plantSix.append("_id", new ObjectId("58d1c36efb0cac4e15afd281"));
        plantSix.append("uploadId", "third uploadId");

        plantSix.append("commonName", "Tulip");
        plantSix.append("cultivar", "Pink");
        plantSix.append("gardenLocation", "20");
        plantSix.append("id", "16081.0");

        Document metadataDoc5 = new Document();
        metadataDoc5.append("pageViews", 0);
        metadataDoc5.append("ratings", new BsonArray());

        plantSix.append("metadata", metadataDoc5);
        plants.insertOne(plantSix);

        //Seventh Plant PlantSeven
        Document plantSeven = new Document();
        plantSeven.append("_id", new ObjectId("58d1c36efb0cac4e15afd282"));
        plantSeven.append("uploadId", "third uploadId");

        plantSeven.append("commonName", "Rose");
        plantSeven.append("cultivar", "Yellow");
        plantSeven.append("gardenLocation", "20");
        plantSeven.append("id", "16082.0");

        Document metadataDoc6 = new Document();
        metadataDoc6.append("pageViews", 0);
        metadataDoc6.append("ratings", new BsonArray());

        plantSeven.append("metadata", metadataDoc6);
        plants.insertOne(plantSeven);



        /*
        Plants that will include mock pageViews and visits
        */

        //EighthPlant PlantEight
        Document plantEight = new Document();
        plantEight.append("_id", new ObjectId("58d1c36efb0cac4e15afd101"));
        plantEight.append("uploadId", "googleCharts uploadId");

        plantEight.append("commonName", "Marigold");
        plantEight.append("cultivar", "Fireball");
        plantEight.append("gardenLocation", "5.0");
        plantEight.append("id", "16053.0");

        Document metadataDoc8 = new Document();
        metadataDoc8.append("pageViews", 5);


        BsonArray r = new BsonArray();
        BsonDocument rateDoc1 = new BsonDocument();
        rateDoc1.append("like",new BsonBoolean(false));
        rateDoc1.append("ratingOnObjectId",new BsonObjectId(new ObjectId("58f03f9aad21334329e73865")));
        r.add(rateDoc1);
        metadataDoc8.append("ratings", r);

        BsonDocument rateDoc2 = new BsonDocument();
        rateDoc2.append("like",new BsonBoolean(true));
        rateDoc2.append("ratingOnObjectId",new BsonObjectId(new ObjectId("58f03f9aad21334329e73866")));
        r.add(rateDoc2);
        metadataDoc.append("ratings", r);



        BsonArray mockVisits8 = new BsonArray();
        BsonDocument docV8 = new BsonDocument();
        docV8.append("visit",new BsonObjectId(new ObjectId("58d1c36efb0cac4e15afd555")));
        mockVisits8.add(docV8);
        metadataDoc8.append("visits", mockVisits8);

        plantEight.append("metadata", metadataDoc8);
        plants.insertOne(plantEight);

        //Ninth plant
        Document plantNine = new Document();
        plantNine.append("_id", new ObjectId("58d1c36efb0cac4e15afd102"));
        plantNine.append("uploadId", "googleCharts uploadId");

        plantNine.append("commonName", "Coreopsis");
        plantNine.append("cultivar", "Sun Kiss");
        plantNine.append("gardenLocation", "2S");
        plantNine.append("id", "16037.0");

        Document metadataDoc9 = new Document();
        metadataDoc9.append("pageViews", 10);

        BsonArray b = new BsonArray();
        BsonDocument doc = new BsonDocument();
        doc.append("like",new BsonBoolean(false));
        doc.append("ratingOnObjectId",new BsonObjectId(new ObjectId("58f03f9aad21334329e73859")));
        b.add(doc);
        metadataDoc9.append("ratings", b);

        BsonDocument doc1 = new BsonDocument();
        doc1.append("like",new BsonBoolean(true));
        doc1.append("ratingOnObjectId",new BsonObjectId(new ObjectId("58f03f9aad21334329e73860")));
        b.add(doc1);
        metadataDoc9.append("ratings", b);

        BsonDocument doc2 = new BsonDocument();
        doc2.append("like",new BsonBoolean(true));
        doc2.append("ratingOnObjectId",new BsonObjectId(new ObjectId("58f03f9aad21334329e73861")));
        b.add(doc2);
        metadataDoc9.append("ratings", b);


        BsonArray mockVisits = new BsonArray();
        BsonDocument docV1 = new BsonDocument();
        docV1.append("visit",new BsonObjectId(new ObjectId("58f03f9aad21334329e73801")));
        mockVisits.add(docV1);
        metadataDoc9.append("visits", mockVisits);

        BsonDocument docV2 = new BsonDocument();
        docV2.append("visit",new BsonObjectId(new ObjectId("58f03f9aad21334329e73802")));
        mockVisits.add(docV2);
        metadataDoc9.append("visits", mockVisits);

        BsonDocument docV3 = new BsonDocument();
        docV3.append("visit",new BsonObjectId(new ObjectId("58f03f9aad21334329e73803")));
        mockVisits.add(docV3);

        metadataDoc9.append("visits", mockVisits);

        plantNine.append("metadata", metadataDoc9);
        plants.insertOne(plantNine);
    }
}




