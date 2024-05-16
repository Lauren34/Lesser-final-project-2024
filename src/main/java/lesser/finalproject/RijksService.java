package lesser.finalproject;

import lesser.finalproject.Json.CollectionResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijksService {

    @GET("/api/en/collection")
    Single<CollectionResponse> pageNumber(
            @Query("p")int pageNumber,
            @Query("key") String apikey);


    @GET("/api/en/collection")
    Single<CollectionResponse> searchCollection(
            @Query("q") String query,
            @Query("p") int pageNumber,
            @Query("key") String apiKey
    );


    @GET("/api/en/collection")
    Single<CollectionResponse> searchByArtist(
            @Query("involvedMaker") String artist,
            @Query("p") int pageNumber,
            @Query("key") String apiKey
    );

}
