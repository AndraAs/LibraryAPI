package aquashoalstudio.endpoints;

import aquashoalstudio.models.*;
import aquashoalstudio.utils.ApiResponse;
import aquashoalstudio.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class LibraryAPI {

    private final RequestSpecification requestSpec;

    public LibraryAPI() {
        String baseUri = ConfigReader.getProperty("base.uri");
        this.requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .build();
    }

    public ApiResponse<AddBookResponse> addBook(AddBookRequest request) {
        Response response = given().spec(requestSpec).body(request)
                .when().post(ConfigReader.getProperty("endpoint.add"));
        return new ApiResponse<>(response.getStatusCode(), response.as(AddBookResponse.class));
    }

    public ApiResponse<List<BookByIDResponse>> getBookByID(String id) {
        Response response = given()
                .spec(requestSpec)
                .queryParam("ID", id)
                .when()
                .get(ConfigReader.getProperty("endpoint.get"));

        String responseString = response.getBody().asString();

        if (responseString.contains("\"msg\"") || response.getStatusCode() != 200) {
            return new ApiResponse<>(response.getStatusCode(), java.util.Collections.emptyList());
        }

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
        Response response = given().spec(requestSpec).body(request)
                .when().post(ConfigReader.getProperty("endpoint.delete"));
        return new ApiResponse<>(response.getStatusCode(), response.as(DeleteBookResponse.class));
    }
}