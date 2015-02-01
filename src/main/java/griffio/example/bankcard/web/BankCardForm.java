package griffio.example.bankcard.web;

import griffio.example.bankcard.CardExpiry;
import griffio.example.bankcard.CardIssuer;
import griffio.example.bankcard.CardNumber;
import griffio.example.bankcard.CardRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.FormParam;

public final class BankCardForm {

    Logger log = LoggerFactory.getLogger(BankCardForm.class);

    private final String bankname;
    private final String cardnumber;
    private final String expiry;

    public BankCardForm(@FormParam("bankname") String bankname, @FormParam("cardnumber") String cardnumber, @FormParam("expiry") String expiry) {

        log.debug("FormParams={},{},{}", bankname, cardnumber, expiry);

        this.bankname = bankname;
        this.cardnumber = cardnumber;
        this.expiry = expiry;
    }

    public CardRecord toCardRecord() {
        return CardRecord.create(
                CardIssuer.create(bankname),
                CardNumber.create(cardnumber),
                CardExpiry.create(expiry));
    }

}
