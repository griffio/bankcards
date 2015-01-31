package griffio.example.bankcard;

import com.google.auto.value.AutoValue;
import com.google.common.collect.Ordering;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import javax.annotation.Nonnull;
/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the expiry date.
 *
 * Comparable - date property provides descending natural order
 */
@AutoValue
public abstract class CardExpiry implements Comparable<CardExpiry> {

    private static final Ordering<LocalDate> descending = Ordering.natural().reverse();

    CardExpiry() {
    }

    public abstract LocalDate date();

    public static CardExpiry create(YearMonth yearMonth) {
        return new AutoValue_CardExpiry(yearMonth.toLocalDate(1));
    }

    @Override
    public int compareTo(@Nonnull CardExpiry other) {
        return descending.compare(this.date(), other.date());
    }
}
