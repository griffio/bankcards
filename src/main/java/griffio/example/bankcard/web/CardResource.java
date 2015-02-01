package griffio.example.bankcard.web;

import com.google.common.collect.Iterables;
import griffio.example.bankcard.CardRecord;
import griffio.example.bankcard.data.CardRecordsSet;
import griffio.example.bankcard.data.CardRecordsSetCsvMapper;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/")
public class CardResource {

    Logger log = LoggerFactory.getLogger(CardResource.class);

    @GET
    @Path("/list")
    public Response list() {
        StringBuilder response = new StringBuilder();

        CardRecordsSet cardRecordsSet = Application.cardRecordsSet;
        for (CardRecord cardRecord : cardRecordsSet) {
            response.append(cardRecord).append("\n");
        }

        return Response.status(200).entity(response.toString()).build();
    }

    @POST
    @Path("/form")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void post(@BeanParam BankCardForm form,
                     @Context HttpServletResponse servletResponse) throws IOException {
        boolean updated = Application.cardRecordsSet.add(form.toCardRecord());
        log.debug(Iterables.toString(Application.cardRecordsSet));
        servletResponse.sendRedirect("/list.html?updated="+updated);
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response post(@FormDataParam("csv") String csv) throws IOException {
        CardRecordsSetCsvMapper csvMapper = new CardRecordsSetCsvMapper(Application.cardRecordsSet);
        int loaded = csvMapper.load(csv);
        log.debug(Iterables.toString(Application.cardRecordsSet));
        return Response.status(200).entity(String.format("csv rows added: %d", loaded)).build();
    }
}
