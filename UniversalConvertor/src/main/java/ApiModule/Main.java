package ApiModule;
import FormsModule.CoinsGUI;

/**
 * UniversalConvertor - Transformador por unidades.
 * Proyecto ideado por @AluraLatam, Complementado y Elaborado por @JuanEstebanLG.
 *
 * @author Esteban
 * @version 1.0
 *
 */
public class Main {
    public static void main(String[] args) {

        String[] DIVISAS = {
                "COP", "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK",
                "NZD", "MXN", "SGD", "HKD", "NOK", "KRW", "TRY", "RUB", "INR", "BRL", "GBP"
        };

        new CoinsGUI(DIVISAS, DIVISAS);

    }
}
