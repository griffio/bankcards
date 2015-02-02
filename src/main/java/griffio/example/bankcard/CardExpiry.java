package griffio.example.bankcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.auto.value.AutoValue;
import com.google.common.collect.Ordering;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.annotation.Nonnull;

/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the expiry date.
 * <p/>
 * Comparable - date property provides descending natural order
 */
@AutoValue
public abstract class CardExpiry implements Comparable<CardExpiry> {

    private static final DateTimeFormatter expiryFormat = DateTimeFormat.forPattern("MMM-yyyy");

    private static final Ordering<LocalDate> descending = Ordering.natural().reverse();

    CardExpiry() {
    }

    public abstract LocalDate date();

    public String toYearMonthFormat() {
        return date().toString(expiryFormat);
    }

    @JsonCreator
    public static CardExpiry create(String yearMonth) {
        return new AutoValue_CardExpiry(YearMonth.parse(yearMonth, expiryFormat).toLocalDate(1));
    }

    @Override
    public int compareTo(@Nonnull CardExpiry other) {
        return descending.compare(this.date(), other.date());
    }
}
