package offshore_plan;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Project_Windows {
    String host;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("offshore-plan");
    }
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\offshore_plan\\Project_Windows.csv");
    }
    @Test(testName = "窗口期接口",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String turbines,String expectcode) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = host+"/project/windows?turbines="+turbines;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        System.out.println("result:" + okhttp.getUrl(url,"null","null"));
    }
}
