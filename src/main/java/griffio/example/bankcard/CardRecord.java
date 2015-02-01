package griffio.example.bankcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import griffio.example.bankcard.data.CardRecordSerializer;

import java.util.Comparator;

/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card record with nested values.
 */
@AutoValue
@JsonSerialize(using = CardRecordSerializer.class)
public abstract class CardRecord {
    /**
     * Ordering is expiry(descending), issuer(ascending), number(ascending)
     */
    public static final Comparator<CardRecord> ORDERING = new Ordering<CardRecord>() {
        @Override
        public int compare(CardRecord left, CardRecord right) {
            return ComparisonChain.start()
                    .compare(left.expiry(), right.expiry())
                    .compare(left.issuer(), right.issuer())
                    .compare(left.number(), right.number()).result();
        }
    };

    CardRecord() {
    }

    public abstract CardIssuer issuer();

    public abstract CardNumber number();

    public abstract CardExpiry expiry();

    @JsonCreator
    public static CardRecord create(@JsonProperty("bank") CardIssuer bank, @JsonProperty("cardnumber") CardNumber cardnumber, @JsonProperty("expiry") CardExpiry expiry) {
        return new AutoValue_CardRecord(bank, cardnumber, expiry);
    }

}
