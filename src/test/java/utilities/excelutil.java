package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelutil {
    public String path;

    public excelutil(String path) {
        this.path = path;
    }

    // returns last row index (zero-based) like original implementation
    public int getRowCount(String sheetName) throws IOException {
        try (InputStream is = new FileInputStream(path);
             Workbook wb = WorkbookFactory.create(is)) {
            Sheet ws = wb.getSheet(sheetName);
            if (ws == null) return 0;
            return ws.getLastRowNum();
        } catch (EncryptedDocumentException e) {
            throw new IOException("Failed to open workbook", e);
        }
    }

    // returns number of cells in the given row (lastCellNum)
    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (InputStream is = new FileInputStream(path);
             Workbook wb = WorkbookFactory.create(is)) {
            Sheet ws = wb.getSheet(sheetName);
            if (ws == null) return 0;
            Row row = ws.getRow(rownum);
            if (row == null) return 0;
            return row.getLastCellNum();
        } catch (EncryptedDocumentException e) {
            throw new IOException("Failed to open workbook", e);
        }
    }

    // returns cell value as String; empty string for null cells
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (InputStream is = new FileInputStream(path);
             Workbook wb = WorkbookFactory.create(is)) {
            Sheet ws = wb.getSheet(sheetName);
            if (ws == null) return "";
            Row row = ws.getRow(rownum);
            if (row == null) return "";
            Cell cell = row.getCell(colnum);
            if (cell == null) return "";
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (EncryptedDocumentException e) {
            throw new IOException("Failed to open workbook", e);
        }
    }

    // sets cell value; creates sheet/row/cell if needed
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File file = new File(path);
        // ensure parent dirs exist
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();

        try (InputStream is = new FileInputStream(file)) {
            Workbook wb = WorkbookFactory.create(is);
            Sheet ws = wb.getSheet(sheetName);
            if (ws == null) {
                ws = wb.createSheet(sheetName);
            }
            Row row = ws.getRow(rownum);
            if (row == null) row = ws.createRow(rownum);
            Cell cell = row.getCell(colnum);
            if (cell == null) cell = row.createCell(colnum);
            cell.setCellValue(data);

            // write back to file
            try (FileOutputStream fo = new FileOutputStream(file)) {
                wb.write(fo);
            }
            wb.close();
        } catch (EncryptedDocumentException e) {
            throw new IOException("Failed to open workbook", e);
        }
    }
}
