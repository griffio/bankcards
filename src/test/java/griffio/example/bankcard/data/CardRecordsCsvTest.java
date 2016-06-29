package griffio.example.bankcard.data;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import griffio.example.bankcard.CardRecord;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

public class CardRecordsCsvTest {

    private String csv0;
    private String csv1;
    private String csv2;
    private ObjectReader csvReader;

    @Before
    public void setUp() throws Exception {
        csv0 = TestFixtureResource.fixture("griffio.example.bankcard.data/data0.csv");
        csv1 = TestFixtureResource.fixture("griffio.example.bankcard.data/data1.csv");
        csv2 = TestFixtureResource.fixture("griffio.example.bankcard.data/data2.csv");
        CsvSchema schema = CsvSchema.builder().addColumn("bankname").addColumn("cardnumber").addColumn("expiry").build();
        CsvMapper mapper = new CsvMapper();
        csvReader = mapper.readerFor(CardRecord.class).with(schema);
    }

    @Test
    public void load_plain_csv_into_recordset() throws Exception {
        MappingIterator<CardRecord> row = csvReader.readValues(csv1);

        CardRecordsSet cardRecordsSet = new CardRecordsSet();

        while (row.hasNext()) {
            cardRecordsSet.add(row.nextValue());
        }

        assertThat(cardRecordsSet).hasSize(5);

    }

    @Test
    public void load_quoted_csv_into_recordset() throws Exception {
        MappingIterator<CardRecord> row = csvReader.readValues(csv2);

        CardRecordsSet cardRecordsSet = new CardRecordsSet();

        while (row.hasNext()) {
            cardRecordsSet.add(row.nextValue());
        }

        assertThat(cardRecordsSet).hasSize(3);

    }

    @Test
    public void load_csv_into_recordset_mapper() throws IOException {
        CardRecordsSet cardRecordsSet = new CardRecordsSet();
        CardRecordsSetCsvMapper cardRecordsSetCsvMapper = new CardRecordsSetCsvMapper(cardRecordsSet);
        int loaded = cardRecordsSetCsvMapper.load(csv0);
        loaded += cardRecordsSetCsvMapper.load(csv1);
        loaded += cardRecordsSetCsvMapper.load(csv2);
        assertThat(loaded).isEqualTo(5);
    }

}
