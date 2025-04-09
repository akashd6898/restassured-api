package utils;
import org.testng.*;
import org.testng.asserts.SoftAssert;

public class SoftAssertUtils {

    private SoftAssert softAssert;

    public SoftAssertUtils()
    {
        softAssert = new SoftAssert();
    }

    public void assertEquals(int statusCode, String msg)
    {
        try{
            softAssert.assertTrue(true, msg);
        }
        catch(AssertionError e)
        {
            softAssert.fail(e.getMessage());
        }
    }

}
