package CurrencyConverter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by Remco on 26-10-2015.
 */
@ManagedBean(name = "conversionBean")
@ApplicationScoped
public class ConversionBean {

    @Digits(integer=4, fraction=2)
    @NotNull
    private double input;
    private List<Conversion> data = new LinkedList<>();
    private Conversion selectedConversion;

    public ConversionBean() {
//        //Dollar > X
//        data.add(new Conversion(0.906897865, Currencies.DOLLAR, Currencies.EURO));
//        data.add(new Conversion(121.006776, Currencies.DOLLAR, Currencies.YEN));
//        data.add(new Conversion(0.6522519, Currencies.DOLLAR, Currencies.POUND));
//        //Euro > X
//        data.add(new Conversion(1.10266, Currencies.EURO, Currencies.DOLLAR));
//        data.add(new Conversion(133.289569, Currencies.EURO, Currencies.YEN));
//        data.add(new Conversion(0.718458729, Currencies.EURO, Currencies.POUND));
//        //Yen > X
//        data.add(new Conversion(0.008264, Currencies.YEN, Currencies.DOLLAR));
//        data.add(new Conversion(0.00750246254, Currencies.YEN, Currencies.EURO));
//        data.add(new Conversion(0.0053902097, Currencies.YEN, Currencies.POUND));
//        //Pound > X
//        data.add(new Conversion(1.53315, Currencies.POUND, Currencies.DOLLAR));
//        data.add(new Conversion(1.3918684, Currencies.POUND, Currencies.EURO));
//        data.add(new Conversion(185.521539, Currencies.POUND, Currencies.YEN));

        input = 1;
    }

    public List<Conversion> getData() {
        return data;
    }

    public void setData(List<Conversion> data) {
        this.data = data;
        updateConvertedAmounts(input);
    }

    public String sortDataByExchangeRate(final int dir){
        Collections.sort(data, new Comparator<Conversion>(){
            @Override
            public int compare(Conversion key_1, Conversion key_2){
                return dir * Double.compare(key_1.getExchangeRate(), key_2.getExchangeRate());
            }
        });
        return null;
    }

    public String sortByFromCurrency(final int dir){
        Collections.sort(data, new Comparator<Conversion>() {
            @Override
            public int compare(Conversion key_1, Conversion key_2) {
                if (dir < 0){
                    return key_1.getFrom().toString().compareTo(key_2.getFrom().toString());
                }
                else {
                    return key_2.getFrom().toString().compareTo(key_1.getFrom().toString());
                }
            }
        });
        return null;
    }

    public String sortByConvertedAmount(final int dir){
        Collections.sort(data, new Comparator<Conversion>() {
            @Override
            public int compare(Conversion key_1, Conversion key_2) {
                return dir * Double.compare(key_1.getConvertedAmount(), key_2.getConvertedAmount());
            }
        });
        return null;
    }

    public String sortByTooCurrency(final int dir){
        Collections.sort(data, new Comparator<Conversion>() {
            @Override
            public int compare(Conversion key_1, Conversion key_2) {
                if (dir < 0){
                    return key_1.getToo().toString().compareTo(key_2.getToo().toString());
                }
                else {
                    return key_2.getToo().toString().compareTo(key_1.getToo().toString());
                }
            }
        });
        return null;
    }

    public void deleteConversion(Conversion conversion){
        data.remove(conversion);
    }

    public void editRow(Conversion conversion){
        conversion.setEdited(true);
    }

    public void saveChanges(){
        for (Conversion conversion : data){
            conversion.setEdited(false);
        }
    }

    public void addNewRow(){
        Conversion new_conversion = new Conversion();
        new_conversion.setEdited(true);
        data.add(new_conversion);
    }

    public void updateConvertedAmounts(double input){
        for (Conversion conversion : data){
            conversion.setConvertedAmount(input * conversion.getExchangeRate());
        }
    }

    public void addConversions(List<Conversion> data){
        data = filterDuplicates(data);
        this.data.addAll(data);
        updateConvertedAmounts(input);
    }

    private List<Conversion> filterDuplicates(List<Conversion> data){
        List<Conversion> filteredData = new LinkedList<Conversion>();
        for (Conversion c1 : data){
            boolean exists = false;
            for (Conversion c2 : this.data){
                if (c1.getFrom() == c2.getFrom() && c1.getToo() == c2.getToo()){
                    exists = true;
                    continue;
                }
            }
            if (!exists){
                filteredData.add(c1);
            }
        }
        return filteredData;
    }


    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public void updateConvertedAmounts(AjaxBehaviorEvent e) throws AbortProcessingException {
        updateConvertedAmounts(input);
    }

    public Conversion getSelectedConversion() {
        return selectedConversion;
    }

    public void setSelectedConversion(Conversion selectedConversion) {
        this.selectedConversion = selectedConversion;
    }

    public String convert(Conversion conversion){
        setSelectedConversion(conversion);
        return "convertedPage";
    }
}
