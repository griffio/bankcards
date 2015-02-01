package griffio.example.bankcard.web;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class CardResourceTest extends JerseyTest {

    @Override
    protected URI getBaseUri() {
        return UriBuilder.fromUri(super.getBaseUri()).path("/").build();
    }

    @Override
    protected Application configure() {
        try {
            return new Application();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void configureClient(ClientConfig config) {
        config.register(MultiPartFeature.class);
    }

    @Test
    public void csv_upload() {
//        WebTarget target = target().path("upload");
//        FormDataMultiPart mp = new FormDataMultiPart();
//        FormDataBodyPart p = new FormDataBodyPart(FormDataContentDisposition.name("csv").build(), "Bank1,1234-1234-1234-1234,Jan-2020");
//        mp.bodyPart(p);
//        String s = target.request().post(Entity.entity(mp, MediaType.MULTIPART_FORM_DATA_TYPE), String.class);
    }


}
