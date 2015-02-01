package griffio.example.bankcard.data;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import griffio.example.bankcard.CardRecord;

import java.io.IOException;

/**
 * CSV Mapper to load file into RecordSet.
 * Load method returns the count of successful records added.
 */
public class CardRecordsSetCsvMapper {

    private final ObjectReader csvReader;
    private final CardRecordsSet cardRecordsSet;

    public CardRecordsSetCsvMapper(CardRecordsSet cardRecordsSet) {
        this.cardRecordsSet = cardRecordsSet;

        CsvSchema schema = CsvSchema.builder().addColumn("bank").addColumn("cardnumber").addColumn("expiry").build();
        com.fasterxml.jackson.dataformat.csv.CsvMapper mapper = new com.fasterxml.jackson.dataformat.csv.CsvMapper();
        csvReader = mapper.reader(CardRecord.class).with(schema);

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
}
