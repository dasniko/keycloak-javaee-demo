import org.keycloak.representations.AccessToken;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Path("greeter")
public class GreetingController {

    @Inject
    AccessToken accessToken;

    @GET
    @Path("greet")
    public String getGreeting() {
        return "Hello " + accessToken.getPreferredUsername();
    }
}
