import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static String promptForString(String prompt, String defaultResponse) {
    	
        System.out.printf("%s [%s]: ", prompt, defaultResponse);

        try {
        	String input = br.readLine();
            if (input.length() != 0) 
            	return input;
        }
        catch (IOException ex) {}

        return defaultResponse;
    }

    public static int promptForNumber(String prompt, int defaultResponse) {
    	
        System.out.printf("%s [%s]: ", prompt, defaultResponse);

        try {
        	String input = br.readLine();
            if (input.length() != 0) 
            	return Integer.parseInt(input);
        }
        catch (IOException ex) {}

        return defaultResponse;
    }
}
