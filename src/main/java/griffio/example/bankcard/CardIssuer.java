package griffio.example.bankcard;

import com.google.auto.value.AutoValue;
/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card number.
 */
@AutoValue
public abstract class CardIssuer {
    CardIssuer() {}

    public abstract String bank();

    public static CardIssuer create(String bank) {
        return null;
    }

}
