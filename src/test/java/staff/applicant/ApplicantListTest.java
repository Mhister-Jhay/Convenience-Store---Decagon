package staff.applicant;

import org.decagon.staff.applicant.Applicant;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ApplicantListTest {

    Applicant applicant = new Applicant();

    @Test
    public void applicantListTest() throws IOException {

        applicant.ListOfApplicant();
        List<Applicant> actualAppliccantList = applicant.getListOfApplicants();
        assertFalse(actualAppliccantList.isEmpty());

        String firstApplicantName = applicant.getListOfApplicants().get(0).getName();

        assertEquals(4, actualAppliccantList.size());
        assertEquals("Emmanuel Chidi", firstApplicantName);
    }
}
