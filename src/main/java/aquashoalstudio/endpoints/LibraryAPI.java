package aquashoalstudio.endpoints;

import aquashoalstudio.models.*;
import aquashoalstudio.utils.ApiResponse;
import aquashoalstudio.utils.ConfigReader; // Assuming you put ConfigReader here
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class LibraryAPI {

    // 1. Dynamic Base URI from config.properties
    private static final String BASE_URI = ConfigReader.getProperty("base.uri");
    private final RequestSpecification requestSpec;

    public LibraryAPI() {
        this.requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    public ApiResponse<AddBookResponse> addBook(AddBookRequest request) {
        // 2. Dynamic endpoint path
        Response response = given().spec(requestSpec).body(request)
                .when().post(ConfigReader.getProperty("endpoint.add"));
        return new ApiResponse<>(response.getStatusCode(), response.as(AddBookResponse.class));
    }

    public ApiResponse<List<BookByIDResponse>> getBookByID(String id) {
        // 3. Dynamic endpoint path
        Response response = given().spec(requestSpec).queryParam("ID", id)
                .when().get(ConfigReader.getProperty("endpoint.get"));

        List<BookByIDResponse> body = Arrays.asList(response.as(BookByIDResponse[].class));
        return new ApiResponse<>(response.getStatusCode(), body);
    }

    public ApiResponse<List<BookByAuthorResponse>> getBooksByAuthor(String authorName) {
        Response response = given().spec(requestSpec).queryParam("AuthorName", authorName)
                .when().get(ConfigReader.getProperty("endpoint.get"));

        List<BookByAuthorResponse> body = Arrays.asList(response.as(BookByAuthorResponse[].class));
        return new ApiResponse<>(response.getStatusCode(), body);
    }

    public ApiResponse<DeleteBookResponse> deleteBook(DeleteBookRequest request) {
        // 4. Dynamic endpoint path
        Response response = given().spec(requestSpec).body(request)
                .when().post(ConfigReader.getProperty("endpoint.delete"));
        return new ApiResponse<>(response.getStatusCode(), response.as(DeleteBookResponse.class));
    }
}