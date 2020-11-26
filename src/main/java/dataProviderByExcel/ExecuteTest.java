package dataProviderByExcel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExecuteTest {

    @Test
    void ExcelTest() throws IOException
    {

        List<Object> lista = RahulDataDrivenExcel.ExcelDataGenerator("TC1", "C:\\Users\\piotr\\unittestingjava\\src\\main\\java\\dataProviderByExcel\\Zeszyt1.xlsx");
        System.out.println(lista);

        System.out.println(lista.get(3));


    }
    }





