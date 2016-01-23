import java.io.*;
import java.util.Scanner;
/**
 * Retrieves a file to be processed. Processed means blank lines (both truly blank and
 * lines containing nothing but whitespace or tabs) and comments (block and line) will be removed. 
 * 
 * @author Jason Isaacs 
 * @version 1.0
 */
public class Preprocessor
{
    
    private String file;
    /**
     * Constructor for objects of class Preprocessor
     */
    public Preprocessor(String file) {
       this.file = file;
    }

    public Preprocessor() {
    }
    
    public String getFile(String filepath) {
        StringBuilder textFromFile = new StringBuilder();
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = inputStream.readLine()) != null) {
                   textFromFile.append(line);
            }
        } 
        catch (IOException e) {
            System.out.println("An error has occurred, the inputStream will be closed, please make sure you have a file to read from: \n" + e);  
        } 
        finally {
            if (inputStream != null) {
                try {
                     inputStream.close();
                }
                catch (IOException e){
                    System.out.println("An error has occurred, the inputStream did not close \n" + e);
                }
            }
        }
        return textFromFile.toString();
    }
    
    public void stripFile(String filepath) {
        String unprocessedTextFromFile;
        StringBuilder processedTextFromFile = new StringBuilder();
        CharacterStripper stripper = new CharacterStripper();
        
        unprocessedTextFromFile = getFile(filepath);
        Scanner scanText = new Scanner(unprocessedTextFromFile);
        while (scanText.hasNextLine()) {
            processedTextFromFile.append(scanText.nextLine());
        }
        
    }
    
    public void storeFile(String filepath, String fileContents) {
        BufferedWriter outputStream = null;
        try {
            outputStream = new BufferedWriter(new FileWriter(filepath));
            outputStream.write(fileContents);
        }
        catch(IOException e) {
            System.out.println("An error has occurred, the outputStream will be closed: " + e);
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                }
                catch(IOException e) {
                    System.out.println("An error has occurred, the outputStream did not close \n" + e);
                }
            }
        }
    }
    
}
