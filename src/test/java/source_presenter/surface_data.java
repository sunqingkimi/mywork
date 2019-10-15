package source_presenter;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class surface_data {
    String host;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("source-presenter");

    }
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\source_presenter\\surface_data.csv");
    }
    @Test(testName = "接口测试",dataProvider = "ApiData")
    public void getTest(String caseID,String sourceType,String region,String attribute,String time,String expectcode,String weatherelement,String mode) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
//        String url = host+"/api/data/sourceType/"+sourceType+"/region/"+region+"/attribute/"+attribute+"?time="+time;
        String url2 = host+"/api/v1/surface/data?weatherelement="+weatherelement+"&time="+time+"&mode="+mode;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
//        String a=okhttp.getUrl(url,"null","null");
        String b=okhttp.getUrl(url2,"null","null");
        System.out.println(b);
//        Assert.assertEquals(a,b,"不一致");
    }
}
