package source_presenter;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
public class Boxes {
    String host;//从配置文件取IP地址
    String zuul;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("source-presenter");
        zuul = Addconfig.prop.getProperty("weather-zuul");
    }
    @DataProvider(name = "ApiData")//定义参数化配置文件
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\source_presenter\\Boxes.csv");
    }
    @Test(testName = "气象数据范围判断是否需要重新解析数据",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String attribute,String boxminLon,String boxmaxLon,String boxminLat,String boxmaxLat,String preBoxminLon,
                        String preBoxmaxLon,String preBoxminLat,String preBoxmaxLat,String expectcode,String expectresponse) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = host+"/api/boxes?attribute="+attribute+"&box.minLon="+boxminLon+"&box.maxLon="+boxmaxLon+"&box.minLat="+boxminLat+"&box.maxLat="+boxmaxLat+
                "&preBox.minLon="+preBoxminLon+"&preBox.maxLon="+preBoxmaxLon+"&preBox.minLat="+preBoxminLat+"&preBox.maxLat="+preBoxmaxLat;
        String url2 = zuul+"/source-presenter/api/boxes?attribute="+attribute+"&box.minLon="+boxminLon+"&box.maxLon="+boxmaxLon+"&box.minLat="+boxminLat+"&box.maxLat="+boxmaxLat+
                "&preBox.minLon="+preBoxminLon+"&preBox.maxLon="+preBoxmaxLon+"&preBox.minLat="+preBoxminLat+"&preBox.maxLat="+preBoxmaxLat;
        System.out.println(url);
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        String response=okhttp.getUrl(url,"null","null");
        System.out.println(response);
        Assert.assertEquals(response,expectresponse,"与预期结果不一致");
    }
}
