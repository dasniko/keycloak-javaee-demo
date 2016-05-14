import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
@Path("greeter")
public class GreetingController {

    @Context
    SecurityContext securityContext;

    @GET
    @Path("greet")
    public String getGreeting() {

        AccessToken accessToken = ((KeycloakPrincipal) securityContext.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        return "Hello " + accessToken.getPreferredUsername();
    }
}
