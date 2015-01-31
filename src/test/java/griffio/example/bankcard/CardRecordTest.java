package griffio.example.bankcard;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.truth.Truth.ASSERT;

public class CardRecordTest {

    Logger log = LoggerFactory.getLogger(CardRecordTest.class);

    CardRecord nov2017;
    CardRecord oct2017;
    CardRecord dec2018;
    CardRecord duplicateNov2017;

    @Before
    public void setUp() throws Exception {
        nov2017 = CardRecord.create(CardIssuer.create("HSBC Canada"), CardNumber.create("5601‐2345‐3446‐5678"), CardExpiry.create(YearMonth.parse("2017-11")));
        oct2017 = CardRecord.create(CardIssuer.create("Royal Bank of Canada"), CardNumber.create("4519‐4532‐4524‐2456"), CardExpiry.create(YearMonth.parse("2017-10")));
        dec2018 = CardRecord.create(CardIssuer.create("American Express"), CardNumber.create("3786‐7334‐8965‐345"), CardExpiry.create(YearMonth.parse("2018-12")));
        duplicateNov2017 = CardRecord.create(CardIssuer.create("HSBC Canada"), CardNumber.create("5601‐2345‐3446‐5678"), CardExpiry.create(YearMonth.parse("2017-11")));
    }

    @Test(expected = NullPointerException.class)
    public void shows_that_autovalue_checks_null_arguments_before_construction() {
        CardExpiry.create(null);
        CardIssuer.create(null);
        CardNumber.create(null);
    }

    @Test
    public void sort_card_data_by_expiry_date_in_descending_order() throws Exception {
        Iterable<CardRecord> cardData = new ImmutableSortedSet.Builder<>(CardRecord.expiryOrdering)
                .add(nov2017, oct2017, dec2018, duplicateNov2017).build();
        log.debug(Iterables.toString(cardData));
        ASSERT.that(cardData).containsExactly(dec2018, nov2017, oct2017).inOrder();
    }
}