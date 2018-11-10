import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class TestServer {

    public static void main(String[] args) {
        Client client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .build();
        //WS text get
        WebTarget target = client.target("http://localhost:8080/FindCar/resources/").path("suppliers/get");

//                .queryParam("xp", 2)
//                .queryParam("yp", 2)
//                .queryParam("xd", 2)
//                .queryParam("yd", 2)
//                .queryParam("num", 4);
        String res = target.request().get().readEntity(String.class);
        System.out.println(res);
    }
}