package griffio.example.bankcard;

import com.google.auto.value.AutoValue;
import com.google.common.collect.Ordering;

import java.util.Comparator;
/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card record with nested values
 */
@AutoValue
public abstract class CardRecord {

    public static final Comparator<CardRecord> expiryOrdering = new Ordering<CardRecord>() {
        @Override
        public int compare(CardRecord left, CardRecord right) {
            return left.expiry().compareTo(right.expiry());
        }
    };

    CardRecord() {
    }

    public abstract CardIssuer issuer();

    public abstract CardNumber number();

    public abstract CardExpiry expiry();

    public static CardRecord create(CardIssuer bank, CardNumber number, CardExpiry expiry) {
        return new AutoValue_CardRecord(bank, number, expiry);
    }

}
