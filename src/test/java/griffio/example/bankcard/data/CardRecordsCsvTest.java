package griffio.example.bankcard.data;

import org.junit.Before;
import org.junit.Test;

public class CardRecordsCsvTest {

    @Before
    public void setUp() throws Exception {
        String csv1 = TestFixtureResource.fixture("griffio.example.bankcard.data/data1.csv");
    }

    @Test
    public void load_plain_csv() throws Exception {
        
    }
}
