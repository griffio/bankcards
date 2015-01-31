package griffio.example.bankcard;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.base.CharMatcher.DIGIT;
import static com.google.common.truth.Truth.ASSERT;

public class CardNumberTest {

    final String fixture = "1234-1234-1234-1234";

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void shows_that_null_arguments_before_construction_throw_exception() {
        CardExpiry.create(null);
    }

    @Test
    public void mask_all_digits() throws Exception {
        String masked = DIGIT.replaceFrom(fixture, "x");
        ASSERT.that(masked).isEqualTo("xxxx-xxxx-xxxx-xxxx");
    }

    @Test
    public void mask_digits_retain_first_four() throws Exception {
        String masked = fixture.substring(0, 4).concat(DIGIT.replaceFrom(fixture.substring(4), 'x'));
        ASSERT.that(masked).isEqualTo("1234-xxxx-xxxx-xxxx");
    }

    @Test
    public void cardnumber_retains_first_four_digits() throws Exception {
        CardNumber cardNumber = CardNumber.create(fixture);
        ASSERT.that(cardNumber.maskedDigits()).isEqualTo("1234-xxxx-xxxx-xxxx");
    }

}