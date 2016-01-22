
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
        String lineOfText = null;
        for(int i = 0; i < currentLine.length(); i++) {
           currentCharacter = currentLine.charAt(i);
           if(currentLine.contains("//")){
               lineOfText = currentLine;
               break;
           }
        }
        return lineOfText;
        //return null;
    }
    
    public String removeBlockComments(String currentLine) {
        
        return null;
    }
}
