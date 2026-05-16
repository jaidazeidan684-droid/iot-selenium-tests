package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private static final String FILE_PATH = "src/test/resources/testdata.xlsx";

    public static String getCellValue(int rowNum, int colNum) {
        String value = "";
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("TestData");
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            value = cell.getStringCellValue();
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getEmail() {
        return getCellValue(1, 0);
    }

    public static String getPassword() {
        return getCellValue(1, 1);
    }
}