package griffio.example.bankcard.web;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import griffio.example.bankcard.data.CardRecordsSet;
import griffio.example.bankcard.data.CardRecordsSetCsvMapper;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.io.IOException;

@ApplicationPath("/")
public class Application extends ResourceConfig {

    private static final CardRecordsSet cardRecordsSet = new CardRecordsSet();

    private static ResourceConfig createResourceConfig() {
        return new ResourceConfig()
                .register(new CardResource(cardRecordsSet))
                .register(MultiPartFeature.class);
    }

    public Application() throws IOException {
        super(createResourceConfig());
        CardRecordsSetCsvMapper cardRecordsSetCsvMapper = new CardRecordsSetCsvMapper(cardRecordsSet);
        cardRecordsSetCsvMapper.load(csv());
    }

    private static String csv() throws IOException {
        return Resources.toString(Resources.getResource("data.csv"), Charsets.UTF_8).trim();
    }

}
