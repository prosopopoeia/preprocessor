

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CharacterStripperTest.
 *
 * @author  Jason Isaacs
 * @version 1.2
 */
public class CharacterStripperTest
{
    private CharacterStripper charStripper;

    /**
     * Default constructor for test class CharacterStripperTest
     */
    public CharacterStripperTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        charStripper = new CharacterStripper();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    @Test
    public void testRemoveBlankLines() {
        assertEquals("not blank", charStripper.removeBlankLines("not blank"));
    }

    @Test
    public void testRemoveBlankLineWithTabs() {
        assertNull(charStripper.removeBlankLines("     \r"));
    }

    @Test
    public void testRemoveBlankLinesWithEmptyLine()
    {
        assertNull(charStripper.removeBlankLines(""));
    }

    @Test
    public void testRemoveBlankLinesWithSpaces() {
        assertNull(charStripper.removeBlankLines("   "));
    }
    
    @Test
    public void testRemoveBlankLinesWithSpacesAndTabsAtEnd() {
        
        assertEquals(" this line should be unaltered       ", charStripper.removeBlankLines(" this line should be unaltered       "));
    }

    @Test
    public void testRemoveLineCommentsOnOwnLine()
    {
        assertNull(charStripper.removeLineComments("//this line will be removed because there is nothing else"));
        assertNull(charStripper.removeLineComments(" //this line will be removed because there is only whitespace"));
    }

    @Test
    public void testRemoveLineCommentsWithCharactersInFront()
    {
        assertEquals("don't remove this text", charStripper.removeBlankLines("don't remove this text//but remove this"));
        assertEquals("dont remove ", charStripper.removeLineComments("dont remove // but do remove this"));
    }

    @Test
    public void testRemoveBlockCommentsOnOneLine()
    {
        assertNull(charStripper.removeBlockComments("/*remove this line*/"));
        assertNull(charStripper.removeBlockComments("  /*this will be removed along with the line */"));
    }
    
    @Test
    public void testRemoveBlockCommentsOnMultipleLines()
    {
        assertNull(charStripper.removeBlockComments("/*remove these lines \r because they are unneeded/*"));
        assertNull(charStripper.removeBlockComments("  /*these comments will be removed along with the lines \n and this\n and this*/"));
    }
    
    @Test
    public void testRemoveBlockCommentsOnManyLinesWithText()
    {
        assertEquals("don't remove this data", charStripper.removeBlockComments("don't remove this data/* but do remove these lines \r because they are unneeded/*"));
        assertEquals("don't remove this  \nthis will not be removed", charStripper.removeBlockComments("don't remove this  /*these comments will be removed along with the lines \n and this\n and this*/" +
                                                    "this will not be removed"));
    }
}







