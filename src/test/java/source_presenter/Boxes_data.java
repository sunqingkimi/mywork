package source_presenter;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Boxes_data {
    String host;//从配置文件取IP地址
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("source-presenter");
    }
    @DataProvider(name = "ApiData")//定义参数化配置文件
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\source_presenter\\Boxes_data.csv");
    }
    @Test(testName = "气象数据范围判断是否需要重新解析数据",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String attribute,String time,String boxminLon,String boxmaxLon,String boxminLat,String boxmaxLat,String expectcode) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = host+"/api/data?attribute="+attribute+"&time="+time+"&box.minLon="+boxminLon+"&box.maxLon="+boxmaxLon+"&box.minLat="+boxminLat+
                "&box.maxLat="+boxmaxLat;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        System.out.println(okhttp.getUrl(url,"null","null"));
    }
}
