import java.util.Scanner;
/**
 * Pre-processor for text files. Remove blank lines (with or without whitespace on the line)
 * and/or removes single line comments (i.e. //), and/or removes block comments (i.e. everything 
 * between [star forward slash] and [forward slash star].
 * 
 * @author Jason Isaacs
 * @version 1.2
 */
public class CharacterStripper
{
    private boolean insideOfComment = false;
    private final int INCREMENT_PAST_CHARACTERS = 2;
    private final int END_OF_LINE = -1;
   
    /**
     * Removes blank lines (both truly blank and
     * lines containing nothing but whitespace and/or tabs).
     * 
     * @param  currentLine line from text file to be checked if is blank
     * @return     empty string if is a blank line (either kind) or an unaltered string if contains characters
     */
    private String removeBlankLines(String currentLine) {
        char currentCharacter;
        String lineOfText = "";
        
        for (int i = 0; i < currentLine.length(); i++) {
           currentCharacter = currentLine.charAt(i);
           if (!Character.isWhitespace(currentCharacter)){
               lineOfText = currentLine;
               break;
           }
        }
        return lineOfText;
    }
    
    /**
     * Removes line comments from a line of text
     * 
     * @param  currentLine line from text file to be checked
     * @return     empty string if is a blank line (either kind), a string
     * containing all text except characters found in a line comment 
     * or an unaltered string if no comments present on line
     */
    private String removeLineComments(String currentLine) {
        String lineOfText = currentLine;
       
        for (int i = 0; i < currentLine.length() - 1; i++) {
           if (currentLine.charAt(i) == '\"') {
               i = currentLine.indexOf('\"', i + 1);
               if (i == -1)
                  break;
           }
           if (currentLine.charAt(i) == '/' && currentLine.charAt(i + 1) == '/'){
               lineOfText = removeBlankLines(currentLine.substring(0, i));
               break;
           }
        }
        return lineOfText;
    }

    /**
     * Removes block comments from a line of text, tracks whether last line processed 
     * had an unclosed comment. Only works correctly if successive lines given to method
     * are from the same text.
     * 
     * @param  currentLine line from text file to be checked
     * @return     empty string if is a blank line (either kind), a string
     * containing all text except characters found in block comment 
     * or an unaltered string if no comments present on line
     */
    private String removeBlockComments(String currentLine) {
        int index1 = 0;
        int index2 = 0;
        StringBuilder blockCommentsRemoved = new StringBuilder();
        
        for (int i = 0; i < currentLine.length() - 1; i++) {
            if (currentLine.charAt(i) == '\"') {
               i = currentLine.indexOf('\"', i + 1);
               if (i == END_OF_LINE)
                  break;
            }
            if (insideOfComment) {
                if (currentLine.indexOf("*/") == END_OF_LINE) {
                    index1 = currentLine.length();
                    i = index1 - 1;
                } else {
                    index1 = currentLine.indexOf("*/") + INCREMENT_PAST_CHARACTERS;
                    insideOfComment = false;
                }
            }
            if (currentLine.charAt(i) == '/' && currentLine.charAt(i + 1) == '*') {
                index2 = i;
                blockCommentsRemoved.append(currentLine.substring(index1, index2));
                
                if (currentLine.indexOf("*/", index2) == END_OF_LINE) {                    
                    index1 = currentLine.length();
                    insideOfComment = true;
                    break;
                } else {
                    index1 = currentLine.indexOf("*/", index2 + INCREMENT_PAST_CHARACTERS) + INCREMENT_PAST_CHARACTERS;
                    i = index1;
                }
            }
        }//end for loop
        if(!insideOfComment) {
            blockCommentsRemoved.append(currentLine.substring(index1, currentLine.length()));
        }
        return removeBlankLines(blockCommentsRemoved.toString());
    }
    
    /**
     * Removes all blank lines (with and without whitespace characters), single line comments, and
     * block comments.
     * 
     * @params text textfile to have blank lines, line comments, and block comments removed from
     * @return fully processed textfile with blank lines, line comments and block comments removed
     */
    public String preprocessText(String text) {
        Scanner scanText = new Scanner(text);
        String fullyProcessedLine = "";
        String withoutLineComments, withoutBlockComments;
        StringBuilder fullyProcessedText = new StringBuilder();
        boolean firstLineOfText = true;
        while (scanText.hasNextLine()) {
            withoutBlockComments = removeBlockComments(scanText.nextLine());
            withoutLineComments = removeLineComments(withoutBlockComments);
            fullyProcessedLine = removeBlankLines(withoutLineComments);
            
            if (!firstLineOfText && !fullyProcessedLine.equals("")){
                fullyProcessedText.append(System.lineSeparator());
            }
            fullyProcessedText.append(fullyProcessedLine);
            
            if (!fullyProcessedLine.equals("")) {
                firstLineOfText = false;
            }
        }  
        insideOfComment = false;
        return fullyProcessedText.toString(); 
    }    
}