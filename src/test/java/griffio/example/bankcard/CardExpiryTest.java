package griffio.example.bankcard;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static com.google.common.truth.Truth.ASSERT;

public class CardExpiryTest {

    Logger log = LoggerFactory.getLogger(CardExpiryTest.class);

    CardExpiry nov2017;
    CardExpiry oct2017;
    CardExpiry dec2018;
    CardExpiry duplicateNov2017;

    @Before
    public void setUp() throws Exception {
        nov2017 = CardExpiry.create("Nov-2017");
        oct2017 = CardExpiry.create("Oct-2017");
        dec2018 = CardExpiry.create("Dec-2018");
        duplicateNov2017 = CardExpiry.create("Nov-2017");
    }

    @Test(expected = NullPointerException.class)
    public void shows_that_null_arguments_before_construction_throw_exception() {
        CardExpiry.create(null);
    }

    @Test
    public void sort_list_by_expiry_date_in_descending_order() throws Exception {
        List<CardExpiry> expiryValues = Lists.newArrayList(nov2017, oct2017, dec2018, duplicateNov2017);
        Collections.sort(expiryValues);
        log.debug(Iterables.toString(expiryValues));
        ASSERT.that(expiryValues).containsAllOf(dec2018, duplicateNov2017, nov2017, oct2017).inOrder();
    }

    @Test
    public void sort_set_by_expiry_date_in_descending_order() throws Exception {
        Iterable<CardExpiry> expiryValues = ImmutableSortedSet.of(nov2017, oct2017, dec2018, duplicateNov2017);
        log.debug(Iterables.toString(expiryValues));
        ASSERT.that(expiryValues).containsExactly(dec2018, nov2017, oct2017).inOrder();
    }

}
