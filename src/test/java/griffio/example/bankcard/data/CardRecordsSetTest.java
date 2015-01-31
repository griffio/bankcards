package griffio.example.bankcard.data;

import com.google.common.collect.Iterables;
import griffio.example.bankcard.CardExpiry;
import griffio.example.bankcard.CardIssuer;
import griffio.example.bankcard.CardNumber;
import griffio.example.bankcard.CardRecord;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.truth.Truth.ASSERT;

public class CardRecordsSetTest {

    Logger log = LoggerFactory.getLogger(CardRecordsSetTest.class);

    CardRecord nov2017;
    CardRecord oct2017;
    CardRecord dec2018;
    CardRecord duplicateNov2017;

    @Before
    public void setUp() throws Exception {
        nov2017 = CardRecord.create(CardIssuer.create("HSBC Canada"), CardNumber.create("5601‐2345‐3446‐5678"), CardExpiry.create("Nov-2017"));
        oct2017 = CardRecord.create(CardIssuer.create("Royal Bank of Canada"), CardNumber.create("4519‐4532‐4524‐2456"), CardExpiry.create("Oct-2017"));
        dec2018 = CardRecord.create(CardIssuer.create("American Express"), CardNumber.create("3786‐7334‐8965‐345"), CardExpiry.create("Dec-2018"));
        duplicateNov2017 = CardRecord.create(CardIssuer.create("HSBC Canada"), CardNumber.create("5601‐2345‐3446‐5678"), CardExpiry.create("Nov-2017"));
    }

    @Test
    public void sort_card_data_by_expiry_date_in_descending_order() throws Exception {

        CardRecordsSet cardRecordsSet = new CardRecordsSet();
        cardRecordsSet.add(nov2017);
        cardRecordsSet.add(oct2017);
        cardRecordsSet.add(dec2018);
        cardRecordsSet.add(duplicateNov2017);

        log.debug(Iterables.toString(cardRecordsSet));

        ASSERT.that(cardRecordsSet).containsExactly(dec2018, nov2017, oct2017).inOrder();

    }

}