package griffio.example.bankcard;

import org.junit.Before;
import org.junit.Test;

public class CardIssuerTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void shows_that_null_arguments_before_construction_throw_exception() {
        CardIssuer.create(null);
    }
}