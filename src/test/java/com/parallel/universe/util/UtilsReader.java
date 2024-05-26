package com.parallel.universe.util;

import com.parallel.universe.config.ConfigReader;
import org.testng.annotations.DataProvider;

public class UtilsReader {
    private ConfigReader configReader = new ConfigReader();
    String testDataFilePath = configReader.testDataExcelFile();

    @DataProvider(name = "Authentication")
    public Object[][] Authentication() throws Exception {

        Object[][] testObjArray = ExcelUtils.getTableArray(testDataFilePath, "Api");

        return (testObjArray);

    }

    @DataProvider(name = "Authentication02")
    public Object[][] Authentication02() throws Exception {

        Object[][] testObjArray02 = ExcelUtils.getTableArray(testDataFilePath, "Sheet1");

        return (testObjArray02);

    }
}
