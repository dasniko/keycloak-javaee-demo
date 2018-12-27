package dasniko.bookbox;

import org.keycloak.representations.AccessToken;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Path("books")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    @Inject
    AccessToken accessToken;

    @Context
    HttpServletRequest request;

    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        books.add(new Book(1, "1984", "George Orwell"));
        books.add(new Book(2, "Hitchhiker's Guide to the Galaxy", "Douglas Adams"));
    }

    @GET
    public List<Book> books() {
        return books;
    }

    @GET
    @Path("/{id}")
    public Book book(@PathParam("id") int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    @POST
    public void addBook(Book book) {
        System.out.println("***** " + request.getUserPrincipal().getName() + " has added the book " + book.getTitle());
        int id = 1;
        if (!books.isEmpty()) {
            id = books.get(books.size() - 1).getId() + 1;
        }
        book.setId(id);
        books.add(book);
    }

    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") int id) {
        System.out.println("***** " + accessToken.getPreferredUsername() + " has deleted the book id " + id);
        books = books.stream().filter(book -> book.getId() != id).collect(Collectors.toList());
    }

}
