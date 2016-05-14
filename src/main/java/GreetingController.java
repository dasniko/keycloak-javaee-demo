import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Path("greeter")
public class GreetingController {

    @GET
    @Path("greet")
    public String getGreeting() {
        return "Hello ";
    }
}
