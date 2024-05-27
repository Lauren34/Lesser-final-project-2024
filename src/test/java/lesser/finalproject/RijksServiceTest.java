package lesser.finalproject;

import com.andrewoid.ApiKey;
import lesser.finalproject.json.ArtObject;
import lesser.finalproject.json.CollectionResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RijksServiceTest {

    @Test
    public void pageNumber() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.pageNumber(1, keyString).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertTrue(response.artObjects.length > 0);
        ArtObject firstArtObject = response.artObjects[0];
        assertNotNull(firstArtObject);
        assertNotNull(firstArtObject.webImage.url);
    }

    @Test
    public void searchCollection() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.searchCollection("Rembrandt", 1, keyString).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertTrue(response.artObjects.length > 0);
        ArtObject firstArtObject = response.artObjects[0];
        assertNotNull(firstArtObject);
        assertNotNull(firstArtObject.webImage.url);
    }

    @Test
    public void searchByArtist() {
        // given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijksService service = new RijksServiceFactory().getService();

        // when
        CollectionResponse response = service.searchByArtist("Johannes Vermeer", 1, keyString).blockingGet();

        // then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertTrue(response.artObjects.length > 0);
        ArtObject firstArtObject = response.artObjects[0];
        assertNotNull(firstArtObject);
        assertNotNull(firstArtObject.title);
        assertNotNull(firstArtObject.longTitle);
        assertNotNull(firstArtObject.principalOrFirstMaker);
        assertNotNull(firstArtObject.webImage.url);
    }
}
