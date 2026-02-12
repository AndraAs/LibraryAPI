package tests;

import aquashoalstudio.endpoints.LibraryAPI;
import aquashoalstudio.models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import aquashoalstudio.utils.ApiResponse;
import java.util.List;

public class LibraryTests extends BaseTest {

    private LibraryAPI libraryAPI;
    private String bookID;
    private String isbn;
    private String aisle;
    private final String AUTHOR_NAME = "Johny foes";

    @BeforeClass
    public void setup() {
        libraryAPI = new LibraryAPI();
    }

    @Test(priority = 1, description = "Add a new book with dynamic data")
    public void addBookTest() {
        isbn = "andra" + (System.currentTimeMillis() % 100000);
        aisle = String.valueOf((int) (Math.random() * 1000));

        AddBookRequest request = AddBookRequest.builder()
                .name("Dynamic Book " + isbn)
                .isbn(isbn)
                .aisle(aisle)
                .author(AUTHOR_NAME)
                .build();

        ApiResponse<AddBookResponse> apiResponse = libraryAPI.addBook(request);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(apiResponse.getBody().getMsg(), "successfully added");

        bookID = apiResponse.getBody().getID();
        Assert.assertNotNull(bookID);
    }

    @Test(priority = 2, dependsOnMethods = "addBookTest")
    public void getBookByIDTest() {
        ApiResponse<List<BookByIDResponse>> apiResponse = libraryAPI.getBookByID(bookID);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);

        BookByIDResponse book = apiResponse.getBody().getFirst();
        Assert.assertEquals(book.getIsbn(), isbn);
    }

    @Test(priority = 3, dependsOnMethods = "addBookTest")
    public void getBookByAuthorTest() {
        ApiResponse<List<BookByAuthorResponse>> apiResponse = libraryAPI.getBooksByAuthor(AUTHOR_NAME);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);

        List<BookByAuthorResponse> books = apiResponse.getBody();

        // Null-safe stream matching
        boolean found = books.stream().anyMatch(b ->
                isbn.equals(b.getIsbn()) && aisle.equals(b.getAisle())
        );

        Assert.assertTrue(found, "Book not found in author list");
    }

    @Test(priority = 4, dependsOnMethods = "addBookTest")
    public void deleteBookTest() {
        DeleteBookRequest request = DeleteBookRequest.builder().ID(bookID).build();
        ApiResponse<DeleteBookResponse> apiResponse = libraryAPI.deleteBook(request);

        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertTrue(apiResponse.getBody().getMsg().contains("deleted"));
    }
}