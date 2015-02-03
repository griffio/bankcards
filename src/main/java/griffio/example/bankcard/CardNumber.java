package griffio.example.bankcard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;

import static com.google.common.base.CharMatcher.DIGIT;

/**
 * AutoValue (https://github.com/google/auto/tree/master/value)
 * An immutable value type to represent the card number.
 *
 * Credit Card number validation preconditions
 * toString() implementation should mask card number
 */
@AutoValue
public abstract class CardNumber implements Comparable<CardNumber> {

    CardNumber() {
    }

    public abstract String digits();

    public String maskedDigits() {
        String fourDigits = digits().substring(0, 4);
        return fourDigits.concat(DIGIT.replaceFrom(digits().substring(4), 'x'));
    }

    public static boolean checkDigits(String digits) {
        String onlyDigits = DIGIT.retainFrom(digits);
        return onlyDigits.length() == 15 || onlyDigits.length() == 16;
    }

    @JsonCreator
    public static CardNumber create(String digits) {
        Preconditions.checkArgument(checkDigits(digits), "invalid card digits");
        return new AutoValue_CardNumber(digits);
    }

    @Override
    public int compareTo(@Nonnull CardNumber other) {
        return this.digits().compareTo(other.digits());
    }
}
