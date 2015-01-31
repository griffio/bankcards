package griffio.example.bankcard;

import com.google.auto.value.AutoValue;
/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card number.
 */
@AutoValue
public abstract class CardNumber {
    CardNumber() {}

    public abstract String digits();

    public String maskedDigits() {
      return digits();
    }

    public static CardNumber create(String digits) {
        return new AutoValue_CardNumber(digits);
    }
}
