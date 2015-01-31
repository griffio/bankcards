package griffio.example.bankcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import static com.google.common.base.CharMatcher.DIGIT;

/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card number.
 */
@AutoValue
public abstract class CardNumber {

    CardNumber() {
    }

    public abstract String digits();

    public String maskedDigits() {
        String fourDigits = digits().substring(0, 4);
        return fourDigits.concat(DIGIT.replaceFrom(digits().substring(4), 'x'));
    }
    @JsonCreator
    public static CardNumber create(String digits) {
        return new AutoValue_CardNumber(digits);
    }
}
