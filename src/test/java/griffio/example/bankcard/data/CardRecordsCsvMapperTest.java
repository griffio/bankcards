package griffio.example.bankcard.data;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;

public class CardRecordsCsvMapperTest {

    Logger log = LoggerFactory.getLogger(CardRecordsCsvMapperTest.class);

    @Test
    public void cardRecords_to_csv() throws IOException {

        String fixture = TestFixtureResource.fixture("griffio.example.bankcard.data/data0.csv");
        CardRecordsSet cardRecords = new CardRecordsSet();
        int recordCount = new CardRecordsSetCsvMapper(cardRecords).load(fixture);

        assertThat(recordCount).isGreaterThan(0);

        CsvSchema schema = CsvSchema.builder().addColumn("bankname").addColumn("cardnumber").addColumn("expiry").build();
        CsvMapper mapper = new CsvMapper();
        String csv = mapper.writer(schema).writeValueAsString(cardRecords.iterator()).trim();

        log.debug(csv);

        assertThat(csv).isEqualTo(TestFixtureResource.fixture("griffio.example.bankcard.data/data-masked.csv"));

    }

}
