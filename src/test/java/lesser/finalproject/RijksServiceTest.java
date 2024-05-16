package lesser.finalproject;

import lesser.finalproject.json.CollectionResponse;
import com.andrewoid.ApiKey;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RijksServiceTest {

    @Test
    public void pageNumber() {
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
    public void searchCollection() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.searchCollection("Rembrandt", 1, apiKey.get()).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }

    @Test
    public void searchByArtist() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.searchByArtist("Vermeer", 1, apiKey.get()).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects[0]);
    }
}

