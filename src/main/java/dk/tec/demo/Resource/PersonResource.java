package dk.tec.demo.Resource;

import dk.tec.demo.Models.Person;
import dk.tec.demo.dataService.PersonDataService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonResource {
    @GET
    @Produces("application/json")
    public List<Person> hello() {
        PersonDataService dataService = new PersonDataService();
        return dataService.getAllPersons();
}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{persId}")
    public Person helloMedParam(@PathParam("persId") int persId) {
        PersonDataService dataService = new PersonDataService();
        return dataService.getPerson(persId);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public int helloPost(Person pers) {
        PersonDataService dataService = new PersonDataService();
        return dataService.addPerson(pers);
    }

    @PUT
    @Produces("text/plain")
    @Path("{persId}")
    public int helloPut(@PathParam("persId") int persId, Person pers) {
        PersonDataService dataService = new PersonDataService();
        return dataService.updatePerson(persId, pers);
    }

    @PATCH
    @Produces("text/plain")
    @Path("{persId}")
    public String helloPatch(@PathParam("persId") int persId) { return "HelloPATCH " + persId;}

    @DELETE
    @Produces("text/plain")
    @Path("{persId}")
    public int helloDelete(@PathParam("persId") int persId) {
        PersonDataService dataService = new PersonDataService();
        return dataService.deletePerson(persId);
    }
}