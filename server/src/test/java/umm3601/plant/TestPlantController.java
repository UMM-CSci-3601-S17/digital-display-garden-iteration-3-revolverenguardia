package umm3601.plant;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
//import sun.text.normalizer.UTF16;
import umm3601.digitalDisplayGarden.Plant;
import umm3601.digitalDisplayGarden.PlantController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TestPlantController {


    private final static String databaseName = "data-for-testing-only";
    private PlantController plantController;

    @Before
    public void populateDB() throws IOException {
        PopulateMockDatabase db = new PopulateMockDatabase();
        db.clearAndPopulateDBAgain();
        plantController = new PlantController(databaseName);
    }

    @Test
    public void filterPlantsByCommonName() throws IOException {
        Plant[] filteredPlants;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("commonName", new String[]{"Alternanthera"});
        String rawPlants = plantController.listPlants(queryParams, "first uploadId");

        System.out.println(rawPlants);

        filteredPlants = gson.fromJson(rawPlants, Plant[].class);
        assertEquals("Incorrect number of plants with commonName Alternanthera", 1, filteredPlants.length);
        assertEquals("Incorrect commonName of plant", "Alternanthera", filteredPlants[0].commonName);
        assertEquals("Incorrect cultivar of plant", "Experimental", filteredPlants[0].cultivar);
//        if(rawPlants.contains(UTF16.valueOf(0x00AE))){
//            rawPlants = rawPlants.replaceAll(UTF16.valueOf(0x00AE), "");
//        }
    }

    @Test
    public void filterPlantsByPlantThatDoesntExist() throws IOException {
        Plant[] filteredPlants;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("commonName", new String[]{"Bob"});
        String rawPlants = plantController.listPlants(queryParams, "second uploadId");

        filteredPlants = gson.fromJson(rawPlants, Plant[].class);
        assertEquals("Incorrect number of plants with commonName Bob", 0, filteredPlants.length);
    }

    @Test
    public void findDataForGardenTen() throws  IOException {
        Plant[] filteredPlants;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("gardenLocation", new String[]{"10.0"});
        String rawPlants = plantController.listPlants(queryParams, "first uploadId");
        filteredPlants = gson.fromJson(rawPlants, Plant[].class);

        assertEquals("Incorrect number of flowers for gardenLocation 10.0", 2, filteredPlants.length);
        assertEquals("Incorrect contents for index 0", "Alternanthera", filteredPlants[0].commonName);
        assertEquals("Incorrect contents for index 1", "Begonia", filteredPlants[1].commonName);
    }

    @Test
    public void gardenOneHundred() throws IOException {
        Plant[] filteredPlants;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("gardenLocation", new String[]{"100.0"});
        String rawPlants = plantController.listPlants(queryParams, "second uploadId");
        filteredPlants = gson.fromJson(rawPlants, Plant[].class);

        assertEquals("Incorrect number of plants for gardenLocation 100", 0, filteredPlants.length);
    }

    @Test
    public void listPlantsFiltersByUploadID() throws IOException{
        Plant[] filteredPlants;
        Gson gson = new Gson();

        Map<String, String[]> queryParams = new HashMap<>();
        String rawPlants = plantController.listPlants(queryParams, "second uploadId");
        filteredPlants = gson.fromJson(rawPlants, Plant[].class);

        assertEquals(2, filteredPlants.length);
    }

    @Test
    public void TestGetPlantsByPlantID(){

        String plantJson = "{ \"_id\" : { \"$oid\" : \"58d1c36efb0cac4e15afd202\" }, \"commonName\" : \"Alternanthera\", \"cultivar\" : \"Experimental\", \"gardenLocation\" : \"10.0\", \"id\" : \"16001.0\" }";
        String plantJson2 = "{ \"_id\" : { \"$oid\" : \"58d1c36efb0cac4e15afd204\" }, \"commonName\" : \"Dianthus\", \"cultivar\" : \"Jolt™ Pink F1\", \"gardenLocation\" : \"7.0\", \"id\" : \"16040.0\" }";
        assertEquals("this should be plant 1",plantJson,plantController.getPlantByPlantID("16001.0","first uploadId"));
        assertEquals("this should be plant 2",plantJson2,plantController.getPlantByPlantID("16040.0","second uploadId"));

        System.out.println(plantController.getPlantByPlantID("16005", "first uploadId").charAt(0));
        //test to see if the plant doesnt appear'
        assertEquals("this plant doesnt exist thus should return \"null\"",plantController.getPlantByPlantID("16005", "first uploadId"), "null");

    }


}
