package weather_zuul;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.CSVData2;
import base.OkHttp;
public class api_v1_forecast_hourly {
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\weather_zuul\\api_v1_forecast_hourly.csv");
    }
    @Test(testName = "接口测试",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String sourceAttributes,String startTime,String longitude,String latitude,String length,String expectcode,String headerskey,String headersvalue) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = "http://10.27.22.26:8080/en-weather/api/v1/forecast/hourly?sourceAttributes="+sourceAttributes+"&startTime="
                +startTime+"&longitude="+longitude+"&latitude="+latitude+"&length="+length;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        System.out.println("result:" + okhttp.getUrl(url,headerskey,headersvalue));
    }
}
