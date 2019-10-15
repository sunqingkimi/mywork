package weather_zuul;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.CSVData2;
import base.OkHttp;
public class ApiCtual {
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\weather_zuul\\ApiCtual.csv");
    }
    @Test(testName = "接口测试",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String masterId,String startTime,String length,String expectcode) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = "http://10.27.22.26:8080/kong-ibm/api/actual?masterId="+masterId+"&startTime="+startTime+"&length="+length;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        System.out.println("result:" + okhttp.getUrl(url,null,null));
    }
}