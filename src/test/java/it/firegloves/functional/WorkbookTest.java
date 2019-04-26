package it.firegloves.functional;

import it.firegloves.MemPOI;
import it.firegloves.builder.MempoiBuilder;
import it.firegloves.domain.MempoiSheet;
import it.firegloves.domain.footer.NumberSumSubFooter;
import it.firegloves.styles.template.ForestStyleTemplate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkbookTest extends FunctionalBaseTest {

    /**********************************************************************************************
     * HSSFWorkbook
     *********************************************************************************************/

    @Test
    public void test_with_HSSFWorkbook() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_HSSFWorkbook.xlsx");

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setWorkbook(new HSSFWorkbook())
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test_with_HSSFWorkbook_and_styles_and_subfooter() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_HSSFWorkbook_and_styles_and_subfooter.xlsx");
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .setStyleTemplate(new ForestStyleTemplate())
                    .setMempoiSubFooter(new NumberSumSubFooter())
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_with_HSSFWorkbook_and_styles_and_subfooter_and_evaluateformulas() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_HSSFWorkbook_and_styles_and_subfooter_and_evaluateformulas.xlsx");
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .setStyleTemplate(new ForestStyleTemplate())
                    .setMempoiSubFooter(new NumberSumSubFooter())
                    .setEvaluateCellFormulas(true)
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test_with_HSSFWorkbook_and_custom_styles() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_HSSFWorkbook_and_custom_styles.xlsx");
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            dateCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/MM/dd"));

            CellStyle datetimeCellStyle = workbook.createCellStyle();
            datetimeCellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            datetimeCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            datetimeCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));

            CellStyle commonDataCellStyle = workbook.createCellStyle();
            commonDataCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            commonDataCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    // custom header cell style
                    .setHeaderCellStyle(headerCellStyle)
                    // no style for number fields
                    .setNumberCellStyle(workbook.createCellStyle())
                    // custom date cell style
                    .setDateCellStyle(dateCellStyle)
                    // custom datetime cell style
                    .setDatetimeCellStyle(datetimeCellStyle)
                    // custom common data cell style
                    .setCommonDataCellStyle(commonDataCellStyle)
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**********************************************************************************************
     * XSSFWorkbook
     *********************************************************************************************/

    @Test
    public void test_with_XSSFWorkbook() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_XSSFWorkbook.xlsx");

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setWorkbook(new XSSFWorkbook())
                    .setFile(fileDest)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test_with_XSSFWorkbook_and_styles_and_subfooter() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_XSSFWorkbook_and_styles_and_subfooter.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .setStyleTemplate(new ForestStyleTemplate())
                    .setMempoiSubFooter(new NumberSumSubFooter())
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_with_XSSFWorkbook_and_styles_and_subfooter_and_evaluateformulas() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_XSSFWorkbook_and_styles_and_subfooter_and_evaluateformulas.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .setStyleTemplate(new ForestStyleTemplate())
                    .setMempoiSubFooter(new NumberSumSubFooter())
                    .setEvaluateCellFormulas(true)
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test_with_XSSFWorkbook_and_custom_styles() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_XSSFWorkbook_and_custom_styles.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();

        try {

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            dateCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/MM/dd"));

            CellStyle datetimeCellStyle = workbook.createCellStyle();
            datetimeCellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            datetimeCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            datetimeCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));

            CellStyle commonDataCellStyle = workbook.createCellStyle();
            commonDataCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            commonDataCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    // custom header cell style
                    .setHeaderCellStyle(headerCellStyle)
                    // no style for number fields
                    .setNumberCellStyle(workbook.createCellStyle())
                    // custom date cell style
                    .setDateCellStyle(dateCellStyle)
                    // custom datetime cell style
                    .setDatetimeCellStyle(datetimeCellStyle)
                    // custom common data cell style
                    .setCommonDataCellStyle(commonDataCellStyle)
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**********************************************************************************************
     * SXSSFWorkbook
     *********************************************************************************************/

    @Test
    public void test_with_SXSSFWorkbook() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_SXSSFWorkbook.xlsx");

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setWorkbook(new SXSSFWorkbook())
                    .setFile(fileDest)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test_with_SXSSFWorkbook_and_styles_and_subfooter() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_SXSSFWorkbook_and_styles_and_subfooter.xlsx");
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .setStyleTemplate(new ForestStyleTemplate())
                    .setMempoiSubFooter(new NumberSumSubFooter())
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_with_SXSSFWorkbook_and_styles_and_subfooter_and_evaluateformulas() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_SXSSFWorkbook_and_styles_and_subfooter_and_evaluateformulas.xlsx");
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        try {

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    .setStyleTemplate(new ForestStyleTemplate())
                    .setMempoiSubFooter(new NumberSumSubFooter())
                    .setEvaluateCellFormulas(true)
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test_with_SXSSFWorkbook_and_custom_styles() {

        File fileDest = new File(this.outReportFolder.getAbsolutePath(), "test_with_SXSSFWorkbook_and_custom_styles.xlsx");
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        try {

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            dateCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/MM/dd"));

            CellStyle datetimeCellStyle = workbook.createCellStyle();
            datetimeCellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
            datetimeCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            datetimeCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));

            CellStyle commonDataCellStyle = workbook.createCellStyle();
            commonDataCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            commonDataCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            MemPOI memPOI = new MempoiBuilder()
                    .setDebug(true)
                    .setWorkbook(workbook)
                    .setFile(fileDest)
                    .setAdjustColumnWidth(true)
                    .addMempoiSheet(new MempoiSheet(prepStmt))
                    // custom header cell style
                    .setHeaderCellStyle(headerCellStyle)
                    // no style for number fields
                    .setNumberCellStyle(workbook.createCellStyle())
                    // custom date cell style
                    .setDateCellStyle(dateCellStyle)
                    // custom datetime cell style
                    .setDatetimeCellStyle(datetimeCellStyle)
                    // custom common data cell style
                    .setCommonDataCellStyle(commonDataCellStyle)
                    .build();

            CompletableFuture<String> fut = memPOI.prepareMempoiReportToFile();
            assertThat("file name len === starting fileDest", fut.get(), equalTo(fileDest.getAbsolutePath()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
