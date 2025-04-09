package userManagement;
import org.testng.*;


public class DataProvider{
    @Dataprovier(name="test data")
    public Object[String][String] testData
    {
        return new Object[][]{
                {"1","John"}
                {"2","Jane"}
        }
    }
}
