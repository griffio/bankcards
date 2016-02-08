package griffio.example.bankcard.data;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import griffio.example.bankcard.CardRecord;

import java.io.IOException;

/**
 * CSV Mapper to load file into RecordSet.
 * Load method returns the count of successful records added.
 */
public class CardRecordsSetCsvMapper {

    private final CsvSchema schema;
    private final ObjectReader csvReader;
    private final CardRecordsSet cardRecordsSet;

    public CardRecordsSetCsvMapper(CardRecordsSet cardRecordsSet) {
        this.cardRecordsSet = cardRecordsSet;

        schema = CsvSchema.builder().addColumn("bankname").addColumn("cardnumber").addColumn("expiry").build();
        CsvMapper mapper = new com.fasterxml.jackson.dataformat.csv.CsvMapper();
        csvReader = mapper.readerFor(CardRecord.class).with(schema);

    }

    public int load(String csv) throws IOException {
        int count = 0;
        MappingIterator<CardRecord> row = csvReader.readValues(csv);
        while (row.hasNext()) {
            if (cardRecordsSet.add(row.nextValue())) {
                count++;
            }
        }
        return count;
    }

    public String toCsv() throws IOException {
        CsvMapper mapper = new CsvMapper();
        return mapper.writer(schema).writeValueAsString(cardRecordsSet.iterator()).trim();
    }
}
