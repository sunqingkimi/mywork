package offshore_plan;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class weather_tide {
    String host;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("offshore-plan");
    }
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\offshore_plan\\weather_tide.csv");
    }
    @Test(testName = "潮汐数据接口",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String projectId,String expectcode) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间
        String startTime = sdf.format(new Date());
        String url = host+"/weather/tide?projectId="+projectId+"&startTime="+startTime;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        System.out.println("result:" + okhttp.getUrl(url,"null","null"));
    }
}
