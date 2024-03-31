package junittest;

import junittest.utils.ConsoleOutputCapturer;
import org.TravelPackageManager.models.TravelPackageImpl;
import org.TravelPackageManager.travelpackages.ManaliTravelPackage;
import org.TravelPackageManager.utils.TravelPackageEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TravelPackageEngineTest {

    TravelPackageImpl travelPackage;
    TravelPackageEngine travelPackageEngine;

    ConsoleOutputCapturer console;

    @Before
    public void init(){
        travelPackage = new ManaliTravelPackage();
        travelPackageEngine = new TravelPackageEngine();
        console = new ConsoleOutputCapturer();
    }


    @Test
    public void givenInvalidInput_whenChooseOptionsInPackage_thenThrowError() throws IOException, InstantiationException, IllegalAccessException {
        console.start();
        travelPackageEngine.chooseOptionsInPackage(10);
        String actual = console.stop();
        String expected = "Invalid input!\n";
        Assert.assertEquals(expected,actual);
    }

}
