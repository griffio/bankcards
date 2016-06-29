package griffio.example.bankcard;

import com.google.common.base.Preconditions;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.base.CharMatcher.DIGIT;
import static com.google.common.truth.Truth.assertThat;

public class CardNumberTest {

    final String fixture = "1234-1234-1234-1234";

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void shows_that_null_arguments_before_construction_throw_exception() {
        CardNumber.create(null);
    }

    @Test
    public void mask_all_digits() throws Exception {
        String masked = DIGIT.replaceFrom(fixture, "x");
        assertThat(masked).isEqualTo("xxxx-xxxx-xxxx-xxxx");
    }

    @Test
    public void mask_digits_retain_first_four() throws Exception {
        String masked = fixture.substring(0, 4).concat(DIGIT.replaceFrom(fixture.substring(4), 'x'));
        assertThat(masked).isEqualTo("1234-xxxx-xxxx-xxxx");
    }

    @Test
    public void cardnumber_retains_first_four_digits() throws Exception {
        CardNumber cardNumber = CardNumber.create(fixture);
        assertThat(cardNumber.maskedDigits()).isEqualTo("1234-xxxx-xxxx-xxxx");
    }

    @Test
    public void allow_simple_valid_amount_of_digits() throws Exception {
        String digits = DIGIT.retainFrom(fixture);
        assertThat(digits.length() == 15 || digits.length() == 16).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void dont_allow_invalid_amount_of_digits() throws Exception {
        String digits = DIGIT.retainFrom(fixture.substring(3));
        Preconditions.checkArgument(digits.length() == 15 || digits.length() == 16);
    }

    @Test(expected = IllegalArgumentException.class)
    public void card_number_has_invalid_amount_of_digits() throws Exception {
        CardNumber.create(fixture.substring(3));
    }

}