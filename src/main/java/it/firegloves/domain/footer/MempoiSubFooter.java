package it.firegloves.domain.footer;

import it.firegloves.domain.MempoiColumn;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface MempoiSubFooter {

    /**
     * sets the value that each column should have into its sub footer
     * @param workbook the current woorkbook
     * @param mempoiColumnList list of current report MempoiColumn
     * @param subFooterCellStyle the cell style to apply to the current sub footer
     * @param firstDataRowIndex the first data row index (headers and subheaders are not counted)
     * @param lastDataRowIndex the last data row index (footers and subfooters are not counted)
     */
    void setColumnSubFooter(Workbook workbook, List<MempoiColumn> mempoiColumnList, CellStyle subFooterCellStyle, int firstDataRowIndex, int lastDataRowIndex);
}
