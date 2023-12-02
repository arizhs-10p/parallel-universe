package com.parallel.universe.util;

import org.testng.annotations.DataProvider;

public class UtilsReader {
//    public void csvRead() throws Exception {
//        String line = "";
//        String splitBy = ",";
//        try {
//            //parsing a CSV file into BufferedReader class constructor
//            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Administrator\\seleniumWD\\parallel-universe\\src\\test\\resources\\Testdata.csv"));
//            while ((line = br.readLine()) != null)
//            //returns a Boolean value
//            {
//                String[] employee = line.split(splitBy);
//                //use comma as separator
//                System.out.println( employee[0]);
//            }
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }
//    }

    @DataProvider(name = "Authentication")
    public Object[][] Authentication() throws Exception{

        Object[][] testObjArray = ExcelUtils.getTableArray("C:\\Users\\Administrator\\seleniumWD\\parallel-universe\\src\\test\\resources\\Testdata.xlsx","Sheet1");

        return (testObjArray);

    }
}
