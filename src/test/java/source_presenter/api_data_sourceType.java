package source_presenter;
import base.addconfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.CSVData2;
import base.OkHttp;
public class api_data_sourceType {
    String host;
    String abc;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("source-presenter");
        abc = Addconfig.prop.getProperty("abc");

    }
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\source_presenter\\api_data_sourceType.csv");
    }
    @Test(testName = "接口测试",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String sourceType,String region,String attribute,String time,String expectcode,String headerskey,String headersvalue) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(abc);
        //从Excel文件中取出参数拼接URL
        String url = host+"/api/data/sourceType/"+sourceType+"/region/"+region+"/attribute/"+attribute+"?time="+time;
//        String url2 = "http://10.27.22.26:8080/source-presenter/api/data/sourceType/"+sourceType+"/region/"+region+"/attribute/"+attribute+"?time="+time;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        String a=okhttp.getUrl(url,headerskey,headersvalue);
//        String b=okhttp.getUrl(url2,headerskey,headersvalue);
        System.out.println("result:" + a);
//        System.out.println("result:" + b);
//
//        Assert.assertEquals(a,b,"不一致");
    }
}
