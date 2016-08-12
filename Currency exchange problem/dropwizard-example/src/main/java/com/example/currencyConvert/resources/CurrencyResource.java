package com.example.currencyConvert.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.hibernate.validator.constraints.NotEmpty;

@Path("/covert-currency")
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyResource {

    @Path("/from/{from_currency}/to/{to_currency}/amount/{amount}")
    @GET
    public Response getCovertCurrency(@NotNull @NotEmpty @PathParam("from_currency") String fromCurrency,
                                      @NotEmpty @NotNull @PathParam("to_currency") String toCurrency,
                                      @NotNull @PathParam("amount") Integer amount) {

        DecimalFormat df = new DecimalFormat("#.#######");
        Double converted_amount = Double.valueOf(df.format(getRate(fromCurrency, toCurrency, amount)));

        com.example.currencyConvert.Response.Response response = new com.example.currencyConvert.Response.Response();
        response.setAmount(converted_amount);
        return Response.status(HttpStatus.OK_200).entity(response.toString()).build();

    }

    public double getRate(final String from, final String to, final Integer amount) {
        try {
            URL url = new URL("http://finance.yahoo.com/d/quotes.csv?f=l1&s="+ from + to + "=X");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                return Double.parseDouble(line) * amount;
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
