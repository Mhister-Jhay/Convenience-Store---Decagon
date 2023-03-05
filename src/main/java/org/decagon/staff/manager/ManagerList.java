package org.decagon.staff.manager;

import java.io.FileNotFoundException;
import java.io.IOException;

@FunctionalInterface
public interface ManagerList {

    public void declareManager() throws IOException;
}
