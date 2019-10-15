package apim;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import weather_zuul.LiveResult;

public class WEATHER_3DAY {
    String host;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("nginx");
    }
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\apim\\WEATHER_3DAY.csv");
    }
    @Test(testName = "心知三天接口",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String longitude,String latitude,String expectcode,String headerskey,String headersvalue) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = host+"/en-weather/api/v1/forecast/daily/3day?longitude="+longitude+"&latitude="
                +latitude;
        //调用接口
        OkHttp okhttp = new OkHttp();
        HttpGet httpGet = new HttpGet();
        httpGet.addHeader("apim-accessKey","test");
        okhttp.setExpectcode(expectcode);
        System.out.println("result:" + okhttp.getUrl(url,headerskey,headersvalue));
        LiveResult liveResult=new LiveResult();
        System.out.println();
    }
}
