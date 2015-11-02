package CurrencyConverter;

import javax.faces.context.FacesContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Remco on 26-10-2015.
 */
public class FileParser {

    private List<Conversion> parseString(String file){
        List<Conversion> data = new LinkedList<>();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String fromCurrency = scanner.next();
            String tooCurrency = scanner.next();
            double exchangeRate = scanner.nextDouble();
            data.add(new Conversion(exchangeRate, checkCurrency(fromCurrency), checkCurrency(tooCurrency)));
        }
        return data;
    }

    public void parseStringOverride(String file){
        getBean().setData(parseString(file));
    }

    public void parseStringAdd(String file){
        getBean().addConversions(parseString(file));
    }

    public ConversionBean getBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{conversionBean}", ConversionBean.class);
    }

    private Currencies checkCurrency(String in){
        switch (in){
            case "Dollar":
                return Currencies.DOLLAR;
            case "Euro":
                return Currencies.EURO;
            case "Yen":
                return Currencies.YEN;
            case "Pound":
                return Currencies.POUND;
            case "ZBD":
                return Currencies.ZBD;
            default:
                return null;
        }
    }
}
