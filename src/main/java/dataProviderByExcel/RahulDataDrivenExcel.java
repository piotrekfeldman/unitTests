package dataProviderByExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class RahulDataDrivenExcel {


//    public static void main(String[] args) throws IOException {
//        ExcelDataGenerator();
//    }


    public static List<Object> ExcelDataGenerator(String testcaseName, String fileInputStreamPath) throws IOException {

       FileInputStream fileInputStream = new FileInputStream(fileInputStreamPath);
       XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        List<Object> lista= new ArrayList<>();
        int sheets = xssfWorkbook.getNumberOfSheets();


        for (int i = 0; i < sheets ; i++) {

            if(xssfWorkbook.getSheetName(i).equalsIgnoreCase("Arkusz1")) {
               XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
                Iterator<Row> rowIterator =xssfSheet.iterator();


                while(rowIterator.hasNext()){
                    Row row =rowIterator.next();
                    if(row.getCell(i).toString().equalsIgnoreCase(testcaseName)){
//                        System.out.println("Sukces");

                        Iterator<Cell> cellIterator = row.cellIterator();

                        while(cellIterator.hasNext()){
                           Cell cell = cellIterator.next();
                           if(cell.getCellType()== CellType.STRING) {
                               String stringCell = cell.getStringCellValue();
                               lista.add(stringCell);
                           }
                           else{
                              String t = NumberToTextConverter.toText(cell.getNumericCellValue());
                               lista.add(t);


                           }
                        }
                    }
                }

            }
        }

//        System.out.println("*********");
//        System.out.println(lista);
//        System.out.println(lista.get(1));
//
//        Set<String> set = new HashSet<>();
//        set.addAll(lista);
//
//        System.out.println("To jest lista set: "+set);

        return lista ;
    }
}
