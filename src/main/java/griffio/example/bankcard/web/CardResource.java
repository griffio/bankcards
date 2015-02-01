package griffio.example.bankcard.web;

import com.google.common.collect.Iterables;
import griffio.example.bankcard.CardRecord;
import griffio.example.bankcard.data.CardRecordsSet;
import griffio.example.bankcard.data.CardRecordsSetCsvMapper;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class CardResource {

    Logger log = LoggerFactory.getLogger(CardResource.class);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {

        List<CardRecord> response = new ArrayList<>();

        CardRecordsSet cardRecordsSet = Application.cardRecordsSet;
        for (CardRecord cardRecord : cardRecordsSet) {
            response.add(cardRecord);
        }

        log.debug(Iterables.toString(response));

        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        Response.ResponseBuilder builder = Response.ok(response,MediaType.APPLICATION_JSON_TYPE);
        builder.cacheControl(cc);

        return builder.build();
    }

    @POST
    @Path("/form")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void post(@BeanParam BankCardForm form,
                     @Context HttpServletResponse servletResponse,
                     @Context HttpServletRequest servletRequest) throws IOException {
        boolean updated = Application.cardRecordsSet.add(form.toCardRecord());
        servletResponse.sendRedirect(servletRequest.getContextPath() + "/index.html?updated=" + updated);
    }

    @POST
    @Path("/upload")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void post(@FormDataParam("csv") FormDataContentDisposition csvForm,
                     @FormDataParam("csv") String csv,
                     @Context HttpServletResponse servletResponse,
                     @Context HttpServletRequest servletRequest) throws IOException {

        CardRecordsSetCsvMapper csvMapper = new CardRecordsSetCsvMapper(Application.cardRecordsSet);
        int loaded = csvMapper.load(csv);
        log.debug(csvForm.getFileName());
        servletResponse.sendRedirect(servletRequest.getContextPath() + "/index.html?loaded=" + loaded);
    }

}
