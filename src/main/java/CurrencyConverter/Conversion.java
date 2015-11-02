package CurrencyConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Remco on 26-10-2015.
 */
public class Conversion {

    private double convertedAmount, exchangeRate;
    private Currencies from, too;
    private boolean edited;

    public Conversion(){}

    public Conversion(double exchangeRate, Currencies from, Currencies too) {
        this.convertedAmount = 0;
        this.exchangeRate = exchangeRate;
        this.from = from;
        this.too = too;
        this.edited = false;
    }

    public double getConvertedAmount() {
        BigDecimal bd = new BigDecimal(convertedAmount);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Currencies getFrom() {
        return from;
    }

    public void setFrom(Currencies from) {
        this.from = from;
    }

    public Currencies getToo() {
        return too;
    }

    public void setToo(Currencies too) {
        this.too = too;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }
}
