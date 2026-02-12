package aquashoalstudio.endpoints;

import aquashoalstudio.models.*;
import aquashoalstudio.utils.ApiResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class LibraryAPI {

    private static final String BASE_URI = "http://216.10.245.166";
    private final RequestSpecification requestSpec;

    public LibraryAPI() {
        this.requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    public ApiResponse<AddBookResponse> addBook(AddBookRequest request) {
        Response response = given().spec(requestSpec).body(request)
                .when().post("/Library/Addbook.php");
        return new ApiResponse<>(response.getStatusCode(), response.as(AddBookResponse.class));
    }

    public ApiResponse<List<BookByIDResponse>> getBookByID(String id) {
        Response response = given().spec(requestSpec).queryParam("ID", id)
                .when().get("/Library/GetBook.php");

        List<BookByIDResponse> body = Arrays.asList(response.as(BookByIDResponse[].class));
        return new ApiResponse<>(response.getStatusCode(), body);
    }

    public ApiResponse<List<BookByAuthorResponse>> getBooksByAuthor(String authorName) {
        Response response = given().spec(requestSpec).queryParam("AuthorName", authorName)
                .when().get("/Library/GetBook.php");

        List<BookByAuthorResponse> body = Arrays.asList(response.as(BookByAuthorResponse[].class));
        return new ApiResponse<>(response.getStatusCode(), body);
    }

    public ApiResponse<DeleteBookResponse> deleteBook(DeleteBookRequest request) {
        Response response = given().spec(requestSpec).body(request)
                .when().post("/Library/DeleteBook.php");
        return new ApiResponse<>(response.getStatusCode(), response.as(DeleteBookResponse.class));
    }
}