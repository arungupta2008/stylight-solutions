import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner; 

public class LookupURL {
    private BiMap<String, String> biMap = HashBiMap.create();
    public void buildHashmap(String line){
        String parts[] = line.split("  ");
        String firstparts[] = parts[0].split("[/?&]+");
        String secondparts[] = parts[1].split("[/?&]+");
        for(int i=0; i < secondparts.length; i++ ){
            biMap.put(firstparts[i+1], secondparts[i]);
        }
    }
    public String getSpeakingURL(String parameterURL){
        if(parameterURL == "/products"){
            return "/Fashion/";
        }
        String parameterURLParts[] = parameterURL.split("[?&]+");
        String speakingURL = "/";
        String speakingURLEnd = "?";
        for(int i=1; i < parameterURLParts.length; i++ ){
            String biMapValue = biMap.get(parameterURLParts[i]);
            if(biMapValue != null)
                speakingURL += biMapValue + "/";
            else
                speakingURLEnd += parameterURLParts[i] + "&";
        }
        speakingURLEnd = speakingURLEnd.substring(0,speakingURLEnd.length()-1);
        return speakingURL + speakingURLEnd;
    }
    public String getParameterURL(String speakingURL){
        String speakingURLParts[] = speakingURL.split("[/?]+");
        String parameterURL = "/products?";
        String parameterURLEnd = "";
        for(int i=1; i < speakingURLParts.length; i++ ){
            String biMapKey = biMap.inverse().get(speakingURLParts[i]);
            if(biMapKey != null)
                parameterURL += biMapKey + "&";
            else
                parameterURLEnd += speakingURLParts[i];
        }
        if (parameterURLEnd == "")
            parameterURL = parameterURL.substring(0,parameterURL.length()-1);
        return parameterURL + parameterURLEnd;
    }
    public static void main(String[] args) throws Exception {
        LookupURL lookup = new LookupURL();
        Scanner scan = new Scanner(System.in);
        System.out.println("=================== Loading the Dataset ===================");        
        BufferedReader in = new BufferedReader(new FileReader("dataset"));
        String line = "";
        while ((line = in.readLine()) != null) {
            lookup.buildHashmap(line);
        }
        in.close();

        System.out.println("=================== Dataset Initialized =================== ");
        System.out.println("=================== Running Example =================== ");
        System.out.println("Looking for speaking URL for /products?gender=female&tag=1770214");
        System.out.println("Speaking URL Result: " + lookup.getSpeakingURL("/products?gender=female&tag=1770214"));
        System.out.println("Looking for parameterized URL for /Women/Shoes/2dFtco1/");
        System.out.println("Parameterized URL Result: " + lookup.getParameterURL("/Women/Shoes/2dFtco1/"));
        System.out.println("=================== Testing out extra case ===================");
        System.out.println("Looking for speaking URL for /products?gender=female&tag=1770214&foo=bar");
        System.out.println("Speaking URL Result: " + lookup.getSpeakingURL("/products?gender=female&tag=1770214&foo=bar"));
        System.out.println("Looking for parameterized URL for /Women/Shoes/2dFtco1/?foo=bar&lorem=ipsum");
        System.out.println("Parameterized URL Result: " + lookup.getParameterURL("/Women/Shoes/2dFtco1/?foo=bar&lorem=ipsum"));
        System.out.println("=================== Testing out user input ===================");

        while (true) {
           System.out.println("");
           System.out.println("Enter 1 to convert Speaking URL to Parameter URL");
           System.out.println("Enter 2 to convert Parameter URL to Speaking URL");
           System.out.println("Press any other button to quit ");
           System.out.println("Enter your input: ");
           int inputValue = scan.nextInt();
           if(inputValue == 1){
               System.out.println("Enter the Speaking URL (Ex: /Women/Blazer/): ");
               String inputURL = scan.next();
               System.out.println("Parameterized URL Result: " + lookup.getParameterURL(inputURL));
           }
           else if(inputValue == 2){
               System.out.println("Enter the Parameter URL (Ex: /products?gender=female&tag=1770214): ");
               String inputURL = scan.next();
               System.out.println("Speaking URL Result: " + lookup.getSpeakingURL(inputURL));
           }
           else{
               break;
           }
        }
    }
}