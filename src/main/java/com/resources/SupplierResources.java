package com.resources;

import com.model.Car;
import com.provider.DataConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/suppliers")
public class SupplierResources {


    @GET
    public String init() {
        return "Hello World!";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Car> getAvailableCars(@QueryParam("xp") String xp, @QueryParam("yp") String yp, @QueryParam("xd") String xd, @QueryParam("yd") String yd, @QueryParam("num") String num) {
        return DataConstructor.getDataConstructor().getSuppliersOffers(Integer.valueOf(xp), Integer.valueOf(yp), Integer.valueOf(xd), Integer.valueOf(yd), Integer.valueOf(num));
    }
}
