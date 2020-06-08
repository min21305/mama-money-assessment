package co.za.mm.utils;

public class CurrencyConverter {
    private CurrencyConverter(){}

    public static final Double baseRateZARTOKWS =6.10;
    public static final Double baseRateZARTOMWK =42.50;

    public static String randToKWS(String amount){
        return String.valueOf(Double.valueOf(amount)*baseRateZARTOKWS);
    }

    public static String randToMWK(String amount){
        return String.valueOf(Double.valueOf(amount)*baseRateZARTOMWK);
    }
}
