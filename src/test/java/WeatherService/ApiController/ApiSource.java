//package WeatherService.ApiController;
//
//import base.CSVData;
//import base.ExcelData;
//import base.GetDemo;
//import java.util.Map;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import java.io.IOException;
//import java.util.Iterator;
//import base.CSVData2;
//import base.OkHttp;
//public class ApiSource {
//    @DataProvider(name = "ApiData")
//    public static Object[][] words() throws Exception {
//        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\WeatherService\\ApiController\\ApiSource.csv");
//    }
//    @Test(testName = "接口测试",dataProvider = "ApiData",timeOut =10000)
//    public void getTest(String caseID,String source,String sourceAttribute,String startTime,String longitude,String latitude,String expectcode,String headerskey,String headersvalue) {
//        //打印caseID
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
//                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
//        //从Excel文件中取出参数拼接URL
//        String url = "http://10.27.21.149:8081/api/source/"+source+"/sourceAttribute/"+sourceAttribute+"/startTime/"+startTime+"?longitude="+longitude+"&latitude="+latitude+"&hot=false";
//        //调用接口
//        OkHttp okhttp = new OkHttp();
//        okhttp.setExpectcode(expectcode);
//        System.out.println("result:" + okhttp.getUrl(url,headerskey,headersvalue));
//    }
//}