
import java.util.HashMap;
import java.util.Scanner;

public class Task4 {
    private HashMap<String, Double> exchangeRates;

    public Task4() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD_TO_EUR", 0.84);
        exchangeRates.put("USD_TO_GBP", 0.72);
        exchangeRates.put("USD_TO_INR", 75.0);
        exchangeRates.put("USD_TO_AUD", 1.31);
        exchangeRates.put("INR_TO_USD", 0.013);
        exchangeRates.put("INR_TO_EUR", 0.011);
        exchangeRates.put("INR_TO_JPY", 1.82);
        exchangeRates.put("USD_TO_JPY", 110.92);
        
    }

    public double convertCurrency(String baseCurrency, String targetCurrency, double amount) {
        String key = baseCurrency + "_TO_" + targetCurrency;
        double exchangeRate = exchangeRates.get(key);
        return amount * exchangeRate;
    }

    public static void main(String[] args) {
        Task4 converter = new Task4();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n<--- Welcome To Currency Converter---->");
        System.out.println("\n USD-United State\n EUR-Europe\n GBP-United Kingdom\n INR-India\n AUD-Australia\n JPY-Japan\n");
        System.out.print("Enter the base currency : ");
        String baseCurrency = scanner.next();

        System.out.print("Enter the target currency : ");
        String targetCurrency = scanner.next();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        double convertedAmount = converter.convertCurrency(baseCurrency, targetCurrency, amount);
        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

        scanner.close();
    }
}