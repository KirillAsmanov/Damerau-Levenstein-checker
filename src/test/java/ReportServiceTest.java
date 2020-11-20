import com.company.PersonalData;
import com.company.Report;
import com.company.ReportService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReportServiceTest {

    // ===== createReport() tests =====

    @Test
    public void when_Create_Report_With_Equal_Objects() {
        PersonalData pd1 = new PersonalData("Кирилл",
                "Асманов",
                "Алексеевич");
        PersonalData pd2 = new PersonalData("Кирилл",
                "Асманов",
                "Алексеевич");
        ReportService reportService = new ReportService();

        Report expected = new Report(0);
        Report result = reportService.createReport(pd1, pd2);

        Assert.assertEquals(expected, result);
        assertTrue(result.isValid());
        Assert.assertEquals(0, result.getErrCnt());
    }

    @Test
    public void when_Create_Report_With_One_Error_In_First_Name() {
        PersonalData pd1 = new PersonalData("Кирилл",
                "Асманов",
                "Алексеевич");
        PersonalData pd2 = new PersonalData("Lирилл",
                "Асманов",
                "Алексеевич");
        ReportService reportService = new ReportService();

        Report expected = new Report(1);
        Report result = reportService.createReport(pd1, pd2);

        Assert.assertEquals(expected, result);
        assertTrue(result.isValid());
        Assert.assertEquals(1, result.getErrCnt());
    }

    @Test
    public void when_Create_Report_With_Three_Errors_In_All_Names() {
        PersonalData pd1 = new PersonalData("Кирилл",
                "Асманов",
                "Алексеевич");
        PersonalData pd2 = new PersonalData("КиRилл",
                "Османов",
                "Лаексеевич");
        ReportService reportService = new ReportService();

        Report expected = new Report(3);
        Report result = reportService.createReport(pd1, pd2);

        Assert.assertEquals(expected, result);
        assertFalse(result.isValid());
        Assert.assertEquals(3, result.getErrCnt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_Create_Report_With_Null_Parameters() {
        ReportService reportService = new ReportService();
        reportService.createReport(null, null);
    }


    // ===== foundDifference() tests =====


    @Test
    public void when_Found_Difference_Between_Equals_Strings_And_Get_0() {
        String source = "Horse";
        String target = "Horse";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(0, result);
    }

    @Test
    public void when_Found_Difference_Between_Equals_With_UpperCase_And_Get_0() {
        String source = "Horse";
        String target = "hORSE ";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(0, result);
    }

    @Test
    public void when_Found_Difference_With_NonValid_Symbols_And_Get_0() {
        String source = "Horse3";
        String target = "    %@H^o<;r:>s&&)([e]%+=  3";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(0, result);
    }

    @Test
    public void when_Found_Difference_With_Deleted_Char_And_Get_1() {
        String source = "Horse";
        String target = "orse";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(1, result);
    }

    @Test
    public void when_Found_Difference_With_Replaced_Char_And_Get_1() {
        String source = "Horse";
        String target = "Worse";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(1, result);
    }

    @Test
    public void when_Found_Difference_With_Added_Char_And_Get_1() {
        String source = "Horse";
        String target = "pHorse";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(1, result);
    }

    @Test
    public void when_Found_Difference_With_Swapped_Chars_And_Get_1() {
        String source = "Horse";
        String target = "oHrse";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(1, result);
    }

    @Test
    public void when_Found_Difference_With_Multiply_Errors_And_Get_3() {
        String source = "Horse";
        String target = "aoHrsa";
        ReportService reportService = new ReportService();
        int result = reportService.foundDifference(source, target);

        Assert.assertEquals(3, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_Found_Difference_With_Null_Parameters() {
        ReportService reportService = new ReportService();
        reportService.foundDifference(null, null);
    }

    // ===== removeNonValidSymbols() tests =====

    @Test
    public void when_RemoveNonValid_With_nonValid_Chars_Parameter() {
        ReportService reportService = new ReportService();
        String s = "№@ ., -=\"+-/*;:?()}{[]|!'#$%^&_\\<>~)_([]` 123abzABZбёяБЁЯ";

        String result = reportService.removeNonValidSymbols(s);
        String expected = "123abzABZбёяБЁЯ";

        Assert.assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void when_RemoveNonValid_With_Null_Parameter() {
        ReportService reportService = new ReportService();
        reportService.removeNonValidSymbols(null);
    }
}
