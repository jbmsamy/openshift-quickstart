package org.acme.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.sql.DataSource;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/greeting")
public class GreetingResource {

    private static final Logger log = LoggerFactory.getLogger(GreetingResource.class);

    @Inject
    private DataSource dataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String jdbc = "";

        try(Connection con = dataSource.getConnection()) {
          if(con !=null) {
            jdbc = " postgres ok";
            log.info("Database connected successfully!");
          }
        }catch(SQLException e) {
          jdbc = e.getMessage();
          log.info(e.getMessage());
        }
        return "Hello RESTEasy";
    }

}
