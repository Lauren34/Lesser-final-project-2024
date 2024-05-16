package Lesser.finalproject;

import Lesser.finalproject.Json.CollectionResponse;
import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RijksServiceTest {

    @Test
    public void testPageNumber() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.pageNumber(1, apiKey.get()).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }

    @Test
    public void testSearchCollection() {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.searchCollection("Rembrandt", 1, apiKey.get()).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }

    @Test
    public void testSearchByArtist() {
        // given
        ApiKey apiKey = new ApiKey();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.searchByArtist("Vermeer", 1, apiKey.get()).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }
}
