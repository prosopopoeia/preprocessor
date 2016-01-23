
/**
 * Pre-processor for text files. Remove blank lines (with or without whitespace on the line)
 * and/or removes single line comments (i.e. //), and/or removes block comments (i.e. everything 
 * between [star forward slash] and [forward slash star].
 * 
 * @author Jason Isaacs
 * @version 1.1
 */
public class CharacterStripper
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class CharacterStripper
     */
    public CharacterStripper() {
    }

    /**
     * Removes blank lines (both truly blank and
     * lines containing nothing but whitespace and/or tabs).
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String removeBlankLines(String currentLine) {
        char currentCharacter;
        String lineOfText = null;
        for(int i = 0; i < currentLine.length(); i++) {
           currentCharacter = currentLine.charAt(i);
           if(!Character.isWhitespace(currentCharacter)){
               lineOfText = currentLine;
               break;
           }
        }
        return lineOfText;
    }
    
    public String removeLineComments(String currentLine) {
        char currentCharacter;
        String lineOfText = currentLine;
        boolean hasCharacters = false;
        for(int i = 0; i < currentLine.length(); i++) {
           currentCharacter = currentLine.charAt(i);
           if(currentLine.charAt(i) == '/' && currentLine.charAt(i + 1) == '/'){
               lineOfText = removeBlankLines(currentLine.substring(0, i));
               break;
           }
        }
        return lineOfText;
    }
    
    
    /**
     * Removes Java block comments 
     */
    public String removeBlockComments(String currentLine) {
        
        return null;
    }
    
    /**
     * Removes all blank lines (with and without whitespace characters), single line comments, and
     * block comments.
     */
    public String preprocessText(String text) {
        String noBlankLines = removeBlankLines(text);
        String noBlankLinesOrLineComments = removeLineComments(noBlankLines);
        return removeBlockComments(noBlankLinesOrLineComments);
    }
}
