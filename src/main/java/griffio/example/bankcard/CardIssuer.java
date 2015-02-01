package griffio.example.bankcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import javax.annotation.Nonnull;

/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card number.
 */
@AutoValue
public abstract class CardIssuer implements Comparable<CardIssuer> {
    CardIssuer() {
    }

    public abstract String bank();

    @JsonCreator
    public static CardIssuer create(String bank) {
        return new AutoValue_CardIssuer(bank);
    }

    @Override
    public int compareTo(@Nonnull CardIssuer other) {
        return this.bank().compareTo(other.bank());
    }
}
