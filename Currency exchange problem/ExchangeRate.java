import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner; 

public class ExchangeRate {

    public double getRate(final String from, final String to, final float amount) {
        try {
            URL url = new URL("http://finance.yahoo.com/d/quotes.csv?f=l1&s="+ from + to + "=X");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line.length() > 0) {
                return Double.parseDouble(line) * amount;
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static void main(String args[]) {

        ExchangeRate exchange = new ExchangeRate();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter currency you want to convert from: ");
        String from = scan.next();
        System.out.println("Enter currency you want to convert to: ");
        String to = scan.next();
        System.out.println("Enter amount you want to convert: ");
        float amount = scan.nextFloat();
        System.out.println("Converted Amount: " + exchange.getRate(from, to, amount));
    }
}