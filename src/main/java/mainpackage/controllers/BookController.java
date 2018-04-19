package mainpackage.controllers;

import java.util.UUID;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;

import io.dropwizard.hibernate.UnitOfWork;
import mainpackage.dao.BookDAO;
import mainpackage.db.Book;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

  private final BookDAO bookDAO;

  public BookController(Jdbi database) {
    bookDAO = database.onDemand(BookDAO.class);
  }

  @GET
  @UnitOfWork
  public Response index() {
    return Response.ok(bookDAO.get()).build();
  }

  @POST
  public Response addBook(Book book) {
    bookDAO.post(book.getTitle(), book.getGenre(), book.getYearPublished(), book.getPages());
    return Response.ok(book).build();
  }

  @Path("{id}")
  @PUT
  public Response updateBook(@PathParam("id") UUID bookID, Book body) {
    Book bookInDB = bookDAO.get(bookID);
    Book temp = new Book();

    if (bookInDB != null) {
      temp.setTitle(body.getTitle() != null ? body.getTitle() : bookInDB.getTitle());
      temp.setGenre(body.getGenre() != null ? body.getGenre() : bookInDB.getGenre());
      temp.setYearPublished(body.getYearPublished() != 0 ? body.getYearPublished() : bookInDB.getYearPublished());
      temp.setPages(body.getPages() != 0 ? body.getPages() : bookInDB.getPages());
      temp.setID(bookInDB.getID());
      bookDAO.updateBook(temp.getID(), temp.getTitle(), temp.getGenre(), temp.getYearPublished(), temp.getPages());
      return Response.ok(String.format("\"book %s updated\"", bookID)).build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity(String.format("\"there is no book with id %s\"", bookID))
          .build();
    }

  }

  @DELETE
  @Path("{id}")
  public Response removeBook(@PathParam("id") UUID id) {
    bookDAO.delete(id);
    return Response.ok(String.format("\"book removed\"")).build();
  }

}