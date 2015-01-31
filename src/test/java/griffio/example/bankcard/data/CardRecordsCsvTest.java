package griffio.example.bankcard.data;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.truth.Truth;
import griffio.example.bankcard.CardRecord;
import org.junit.Before;
import org.junit.Test;

public class CardRecordsCsvTest {

    private String csv1;
    private String csv2;
    private ObjectReader csvReader;

    @Before
    public void setUp() throws Exception {
        csv1 = TestFixtureResource.fixture("griffio.example.bankcard.data/data1.csv");
        csv2 = TestFixtureResource.fixture("griffio.example.bankcard.data/data2.csv");
        CsvSchema schema = CsvSchema.builder().addColumn("bank").addColumn("cardNumber").addColumn("expiry").build();
        CsvMapper mapper = new CsvMapper();
        csvReader = mapper.reader(CardRecord.class).with(schema);
    }

    @Test
    public void load_plain_csv_into_recordset() throws Exception {
        MappingIterator<CardRecord> row = csvReader.readValues(csv1);

        CardRecordsSet cardRecordsSet = new CardRecordsSet();

        while (row.hasNext()) {
            cardRecordsSet.add(row.nextValue());
        }

        Truth.ASSERT.that(cardRecordsSet).hasSize(3);

    }

    @Test
    public void load_quoted_csv_into_recordset() throws Exception {
        MappingIterator<CardRecord> row = csvReader.readValues(csv2);

        CardRecordsSet cardRecordsSet = new CardRecordsSet();

        while (row.hasNext()) {
            cardRecordsSet.add(row.nextValue());
        }

        Truth.ASSERT.that(cardRecordsSet).hasSize(3);

    }
}
