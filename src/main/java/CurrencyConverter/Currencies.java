package CurrencyConverter;

/**
 * Created by Remco on 26-10-2015.
 */
public enum Currencies {
    EURO("Euro","<i class=\"fa fa-eur\"/>"),
    DOLLAR("Dollar", "<i class=\"fa fa-usd\"/>"),
    YEN("Yen", "<i class=\"fa fa-jpy\"/>"),
    POUND("Pound", "<i class=\"fa fa-gbp\"/>"),
    ZBD("Zimbabwe Dollar", "<i class=\"fa fa-usd\"/>");

    private final String CURRENCY;
    private final String ICON;

    private Currencies(String currency, String iconString){
        this.CURRENCY = currency;
        this.ICON = iconString;
    }

    public String getCURRENCY() {
        return CURRENCY;
    }

    @Override
    public String toString() {
        return CURRENCY;
    }

    public String getIcon(){
        return ICON;
    }
}
