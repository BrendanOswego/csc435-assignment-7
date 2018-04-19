package mainpackage.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.core.MediaType;

import mainpackage.dao.AuthorDAO;
import mainpackage.dao.BookAuthorDAO;
import mainpackage.dao.BookDAO;
import mainpackage.db.Author;
import mainpackage.db.Book;
import mainpackage.db.BookAuthor;
import mainpackage.message.LocationMessage;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorController {

  private final AuthorDAO authorDAO;
  private final BookDAO bookDAO;
  private final BookAuthorDAO baDAO;

  public AuthorController(Jdbi database) {
    authorDAO = database.onDemand(AuthorDAO.class);
    bookDAO = database.onDemand(BookDAO.class);
    baDAO = database.onDemand(BookAuthorDAO.class);
  }

  @GET
  public Response index() {
    return Response.ok(authorDAO.get()).build();
  }

  @Path("{id}")
  @GET
  public Response getAuthor(@PathParam("id") UUID id) {
    Author author = authorDAO.get(id);
    if (author != null) {
      return Response.ok(author).build();
    }
    return Response.status(Response.Status.NOT_FOUND).entity(String.format("\"author not found\"")).build();
  }

  @Path("{id}/books")
  @GET
  public Response getBooks(@PathParam("id") UUID id) {
    return Response.ok(baDAO.getBooksByAuthor(id)).build();
  }

  @POST
  public Response addAuthor(Author author) throws URISyntaxException {
    UUID id = authorDAO.post(author.getFirstName(), author.getLastName());
    if (id != null) {
      URI uri = UriBuilder.fromUri("/authors/" + id.toString()).build();
      return Response.created(uri).entity(String.format("\"author %s created\"", id)).build();
    }
    Author authorInDB = authorDAO.get(author.getFirstName(), author.getLastName());
    return Response.status(Response.Status.FOUND)
        .entity(new LocationMessage("author already found", "authors/" + authorInDB.getID())).build();
  }

  @Path("{id}/books")
  @POST
  public Response addBookToAuthor(@PathParam("id") String id, Book book) {
    Author author = authorDAO.get(UUID.fromString(id));
    UUID book_id = bookDAO.post(book.getTitle(), book.getGenre(), book.getYearPublished(), book.getPages());

    if (book_id != null) {
      baDAO.post(book_id, author.getID());
      String location = "authors/" + id.toString() + "/books/" + book_id;
      URI uri = UriBuilder.fromUri(location).build();
      return Response.created(uri).entity("book added").build();
    }
    Book bookInDB = bookDAO.get(book.getTitle(), book.getGenre(), book.getYearPublished(), book.getPages());
    String location = "authors/" + id.toString() + "/books/" + bookInDB.getID();
    URI uri = UriBuilder.fromUri(location).build();
    UUID updated = baDAO.post(bookInDB.getID(), author.getID());
    if (updated != null) {
      return Response.created(uri)
          .entity(new LocationMessage("book " + bookInDB.getID() + " added to author " + id, location)).build();
    }
    return Response.status(Response.Status.FOUND)
        .entity(new LocationMessage("book already added to author " + id, location)).build();
  }

  @Path("{id}")
  @PUT
  public Response updateAuthor(@PathParam("id") UUID id, Author body) {
    Author authorInDB = authorDAO.get(id);
    Author temp = new Author();
    temp.setFirstName(body.getFirstName() != null ? body.getFirstName() : authorInDB.getFirstName());
    temp.setLastName(body.getLastName() != null ? body.getLastName() : authorInDB.getLastName());
    temp.setID(id);
    authorDAO.updateAuthor(id, temp.getFirstName(), temp.getLastName());

    return Response.ok(temp).build();
  }

  @Path("{authorID}/books/{bookID}")
  @PUT
  public Response updateBook(@PathParam("authorID") UUID authorID, @PathParam("bookID") UUID bookID, Book body) {
    BookAuthor ba = baDAO.getBookAuthorByID(bookID, authorID);
    Book bookInDB = bookDAO.get(bookID);

    if (ba != null) {
      Book temp = new Book();
      temp.setTitle(body.getTitle() != null ? body.getTitle() : bookInDB.getTitle());
      temp.setGenre(body.getGenre() != null ? body.getGenre() : bookInDB.getGenre());
      temp.setYearPublished(body.getYearPublished() != 0 ? body.getYearPublished() : bookInDB.getYearPublished());
      temp.setPages((Integer) body.getPages() != 0 ? body.getPages() : bookInDB.getPages());
      temp.setID(bookID);
      bookDAO.updateBook(temp.getID(), temp.getTitle(), temp.getGenre(), temp.getYearPublished(), temp.getPages());

      return Response.ok("\"updated book\"").build();
    }
    return Response.status(Response.Status.NOT_FOUND)
        .entity(String.format("\"author %s does not have book %s\"", authorID, bookID)).build();
  }

  @Path("{id}")
  @DELETE
  public Response removeAuthor(@PathParam("id") UUID id) {
    UUID authorID = authorDAO.delete(id);
    if (authorID == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("\"author not found\"").build();
    }
    return Response.ok(String.format("\"removed author  %s\"", authorID)).build();
  }

  @Path("{authorID}/books/{bookID}")
  @DELETE
  public Response removeBookByAuthor(@PathParam("authorID") UUID authorID, @PathParam("bookID") UUID bookID) {
    UUID baID = baDAO.removeBookByAuthor(bookID, authorID);

    if (baID != null) {
      return Response.ok().entity(String.format("\"book %s removed from author %s\"", bookID, authorID)).build();
    }
    return Response.status(Response.Status.NOT_FOUND)
        .entity(String.format("\"author %s does not have book %s\"", authorID, bookID)).build();
  }

}