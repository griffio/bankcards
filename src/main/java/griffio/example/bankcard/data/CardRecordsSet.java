package griffio.example.bankcard.data;

import griffio.example.bankcard.CardRecord;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Store a mutable set of records with expiry date ordering.
 * Can be accessed concurrently.
 */
@ThreadSafe
public final class CardRecordsSet implements Iterable<CardRecord> {

    private final Set<CardRecord> cardRecords;

    public CardRecordsSet() {
        this.cardRecords = new ConcurrentSkipListSet<>(CardRecord.expiryOrdering);
    }

    public boolean add(CardRecord cardRecord) {
        return cardRecords.add(cardRecord);
    }

    @Override
    public Iterator<CardRecord> iterator() {
        return cardRecords.iterator();
    }

}
