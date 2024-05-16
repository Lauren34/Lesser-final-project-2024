package Lesser.finalproject;

import Lesser.finalproject.Json.CollectionResponse;
import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RijksServiceTest {

    @Test
    public void testPageNumber() {
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        CollectionResponse response = service.pageNumber(1, apiKey.get()).blockingGet();

        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }

    @Test
    public void testSearchCollection() {
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        CollectionResponse response = service.searchCollection("Rembrandt", 1, apiKey.get()).blockingGet();

        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }

    @Test
    public void testSearchByArtist() {
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        CollectionResponse response = service.searchByArtist("Vermeer", 1, apiKey.get()).blockingGet();

        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }
}
