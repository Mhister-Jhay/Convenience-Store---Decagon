package org.decagon.staff.applicant;

import java.io.FileNotFoundException;
import java.io.IOException;

@FunctionalInterface
public interface ApplicantList {
    public void declareApplicant() throws IOException;
}
