package staff.manager;

import org.decagon.staff.manager.Manager;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ManagerTest {

    Manager manager = new Manager();

    @Test
    public void managerListTest() throws IOException {

        manager.declareManager();

        List<Manager> actualManagerList = manager.getTheManager();
        assertFalse(actualManagerList.isEmpty());

        String managerName = actualManagerList.get(0).getName();

        assertEquals(1, actualManagerList.size());
        assertEquals("Adamu John", managerName);

    }

}
