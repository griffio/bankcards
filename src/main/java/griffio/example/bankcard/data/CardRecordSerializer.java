package griffio.example.bankcard.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import griffio.example.bankcard.CardRecord;

import java.io.IOException;

/**
 * Custom Json Serializer for CardRecord
 */
public class CardRecordSerializer extends JsonSerializer<CardRecord> {

    @Override
    public void serialize(CardRecord cardRecord, JsonGenerator json, SerializerProvider provider) throws IOException {
        json.writeStartObject();
        json.writeObjectField("bankname", cardRecord.issuer().bank());
        json.writeStringField("cardnumber", cardRecord.number().maskedDigits());
        json.writeObjectField("expiry", cardRecord.expiry().toYearMonthFormat());
        json.writeEndObject();
    }

}