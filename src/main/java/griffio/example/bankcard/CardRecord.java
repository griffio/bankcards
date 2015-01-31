package griffio.example.bankcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public static CardRecord create(@JsonProperty("bank") CardIssuer bank, @JsonProperty("cardNumber") CardNumber cardNumber, @JsonProperty("expiry") CardExpiry expiry) {
        return new AutoValue_CardRecord(bank, cardNumber, expiry);
    }

}
