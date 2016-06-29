package griffio.example.bankcard.data;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class DateTimeFormatTest {

    @Test
    public void expiry_date_format() throws Exception {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MMM-yyyy");
        YearMonth nov2017 = YearMonth.parse("Nov-2017", fmt);
        assertThat(nov2017.toLocalDate(1)).isEqualTo(LocalDate.parse("2017-11-01"));
    }
}
