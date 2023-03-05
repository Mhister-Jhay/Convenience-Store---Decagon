package product;

import org.decagon.product.beverages.Beverages;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BeverageListTest {

    Beverages beverages = new Beverages();

    @Test
    public void beverageListTest() throws IOException {
        beverages.listOfBeverages();
        ArrayList<Beverages> actualBeverageList = beverages.beverageList;
        assertFalse(actualBeverageList.isEmpty());

        String firstBeverageProduct = actualBeverageList.get(0).name;
        assertEquals(11, actualBeverageList.size());
        assertEquals("Coca Cola", firstBeverageProduct);
    }
}
