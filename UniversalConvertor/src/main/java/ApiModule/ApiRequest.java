package ApiModule;

import FormsModule.CoinsGUI;

public class ApiRequest  {
    public static void main(String[] args) {
        String[] DIVISAS = {
                "COP", "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK",
                "NZD", "MXN", "SGD", "HKD", "NOK", "KRW", "TRY", "RUB", "INR", "BRL", "GBP"
        };

        new CoinsGUI(DIVISAS, DIVISAS);

    }
}
