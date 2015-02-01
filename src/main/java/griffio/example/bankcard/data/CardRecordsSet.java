package griffio.example.bankcard.data;

import griffio.example.bankcard.CardRecord;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Store a mutable set of records with descending expiry first ordering.
 * Protects against duplicate data  and can be accessed concurrently.
 */
@ThreadSafe
public final class CardRecordsSet implements Iterable<CardRecord> {

    private final Set<CardRecord> cardRecords;

    public CardRecordsSet() {
        this.cardRecords = new ConcurrentSkipListSet<>(CardRecord.ORDERING);
    }

    public boolean add(CardRecord cardRecord) {
        return cardRecords.add(cardRecord);
    }

    @Override
    public Iterator<CardRecord> iterator() {
        return cardRecords.iterator();
    }

}
