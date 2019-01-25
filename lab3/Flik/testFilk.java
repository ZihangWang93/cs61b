import org.junit.Test;
import static org.junit.Assert.*;

public class testFilk {
    @Test
    public void testEqual(){
        int a = 128;
        int b = 128;
        int c = 200;
        Boolean expAEqualToB = true;
        Boolean expAEqualToC = false;
        assertTrue(expAEqualToB == Flik.isSameNumber(a, b));
        assertTrue(expAEqualToC == Flik.isSameNumber(a, c));
    }

}
