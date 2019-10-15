package offshore_plan;
import base.addconfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.CSVData2;
import base.OkHttp;
public class project_turbines {
    String host;
    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("offshore-plan");
    }
    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\offshore_plan\\project_turbines.csv");
    }
    @Test(testName = "风机列表接口",dataProvider = "ApiData",timeOut =10000)
    public void getTest(String caseID,String projectId,String expectcode) {
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = host+"/project/turbines?projectId="+projectId;
        //调用接口
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        System.out.println("result:" + okhttp.getUrl(url,"null","null"));
    }
}