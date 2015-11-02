package CurrencyConverter;

/**
 * Created by Remco on 21-10-2015.
 */
public enum Conversions {
    DOLLAR_TO_EURO("Dollars to Euros"),
    EURO_TO_DOLLAR("Euros to Dollars");

    private final String TYPE;

    private Conversions(String type){
        this.TYPE = type;
    }

    public String getType() {
        return TYPE;
    }
}
