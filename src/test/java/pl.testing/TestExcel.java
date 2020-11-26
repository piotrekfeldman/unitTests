package pl.testing;
import dataProviderByExcel.ExcelUtils;
import dataProviderByExcel.RahulDataDrivenExcel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scala.collection.immutable.List;

import java.io.IOException;

public class TestExcel {

    @Test(dataProvider = "Authentication")
    public void DataProviding(String log, String pass) {

        System.out.println("homeLoanLogginForm");
        System.out.println(log);
        System.out.println(pass);

    }



    @DataProvider
    public Object[][] Authentication() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\piotr\\unittestingjava\\src\\main\\java\\dataProviderByExcel\\Zeszyt2.xlsx","Arkusz1");


        return (testObjArray);

    }
}
