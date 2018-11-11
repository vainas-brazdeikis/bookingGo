package com.bookingGo.resources;

import com.bookingGo.provider.DataConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/suppliers")
public class SupplierResources {


    @GET
    public String init() {
        return "Hello World!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object[] getAvailableCars(@QueryParam("xp") String xp, @QueryParam("yp") String yp, @QueryParam("xd") String xd, @QueryParam("yd") String yd, @QueryParam("num") String num) {
        System.out.println( );
        return DataConstructor.getDataConstructor().getSuppliersOffers(Integer.valueOf(xp), Integer.valueOf(yp), Integer.valueOf(xd), Integer.valueOf(yd), Integer.valueOf(num)).toArray();
    }
}
