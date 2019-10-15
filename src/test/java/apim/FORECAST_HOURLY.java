package apim;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FORECAST_HOURLY {
    String host;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("nginx");
    }
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\apim\\FORECAST_HOURLY.csv");
    }

    @Test(testName = "预测小时数据",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String weatherelement,String longitude,String latitude,String expectcode,String headerskey,String headersvalue) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = host+"/en-weather/v2/api/forecast/hourly?weatherelement="+weatherelement+"&longitude="+longitude+"&latitude="+latitude+"&metadata=true";
        System.out.println(url);
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        System.out.println("result:" + okhttp.getUrl(url,headerskey,headersvalue));
    }
}