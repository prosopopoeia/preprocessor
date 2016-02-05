
/**
 * Retrieves a file to be processed. Processed means blank lines (both truly blank and
 * lines containing nothing but whitespace or tabs) and comments (block and line) will be removed. 
 * Can only guarantee it works correctly with ANSI encoded text files, though it may work with other encodings.
 * 
 * @author Jason Isaacs 
 * @version 1.0
 */

import java.io.*;
import java.util.Scanner;

public class Preprocessor
{
    /**
     * Takes a filepath and return a string representing the contents of the file found at the filepath
     * 
     * @param  filepath path of desired file
     * @return String of contents of file
     */
    private String getFileContents(File filepath) {
        StringBuilder textFromFile = new StringBuilder();
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = inputStream.readLine()) != null) {
                   textFromFile.append(line);
                   textFromFile.append(System.getProperty("line.separator"));
            }
        } 
        catch (IOException e) {
            System.out.println("An error has occurred, the inputStream will be closed, please make sure you have a file to read from: \n" + e);  
        } finally {
            if (inputStream != null) {
                try {
                     inputStream.close();
                } catch (IOException e){
                    System.out.println("An error has occurred, the inputStream did not close \n" + e);
                }
            }
        }
        return textFromFile.toString();
    }
    
    /**
     * Takes a filepath and a string representing the contents of the file
     * and store them in a file located at filepath
     * 
     * @param  filepath path of desired file
     * @param fileContents string of contents to be stored
     */
    private void storeFile(String filepath, String fileContents) {
        BufferedWriter outputStream = null;
        try {
            outputStream = new BufferedWriter(new FileWriter(filepath));
            outputStream.write(fileContents);
        } catch(IOException e) {
            System.out.println("An error has occurred, the outputStream will be closed: " + e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch(IOException e) {
                    System.out.println("An error has occurred, the outputStream did not close \n" + e);
                }
            }
        }
    }
    
    /**
     * Starts execution of preprocessor.
     */
    public static void main(String[] args) {
        String unProcessedFile, processedFile, outputName;
        Preprocessor preprocessor = new Preprocessor();
        CharacterStripper stripper = new CharacterStripper();
        
        FilenameFilter testFileFilter = new FilenameFilter() {
            public boolean accept(File testDir, String name) {
                String fileName = name;
                if (!name.contains("out") && name.contains("striptest")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        
        File testDirectory = new File("tests");
        File[] testFiles = testDirectory.listFiles(testFileFilter);
        
        for (File testfile : testFiles) {
            unProcessedFile = preprocessor.getFileContents(testfile);            
            processedFile = stripper.preprocessText(unProcessedFile);
            outputName = testfile.getPath();
            preprocessor.storeFile(outputName.replace(".txt", "-out.txt"), processedFile);
        }
    }
}
