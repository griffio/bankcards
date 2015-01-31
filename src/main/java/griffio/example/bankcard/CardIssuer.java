package griffio.example.bankcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card number.
 */
@AutoValue
public abstract class CardIssuer {
    CardIssuer() {}

    public abstract String bank();
    @JsonCreator
    public static CardIssuer create(String bank) {
        return new AutoValue_CardIssuer(bank);
    }

}
