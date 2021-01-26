import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testIsSameNumber(){
        assertEquals(true, Flik.isSameNumber(5, 5));
        assertEquals(false, Flik.isSameNumber(5, 6));
        assertEquals(false, Flik.isSameNumber(-5, 5));
        assertEquals(true, Flik.isSameNumber(-5, -5));
        assertTrue(Flik.isSameNumber(250,250));
    }
}
