package sequence.FactDataController;
import base.CSVData;
import base.GetDemo;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;

public class range {
    @DataProvider(name = "ApiData")
    public Iterator<Object[]> Numbers() throws IOException{
        return (Iterator<Object[]>)new CSVData( "sequence\\FactDataController\\","range.csv");
    }
    @Test(testName = "接口测试",dataProvider = "ApiData",timeOut =10000)
    public void getTest(Map<String, String> data) {
        //遍历Excel中的数据
        String caseID = String.format( data.get( "caseID" ) );
        String masterId = String.format( data.get( "masterId" ) );
        String metrics = String.format( data.get( "metrics" ) );
        String statistics = String.format( data.get( "statistics" ) );
        String factDataSource = String.format( data.get( "factDataSource" ) );
        String expectcode = String.format(data.get("expectcode"));
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = "http://10.27.21.141:8080/api/fact/masterId/"+masterId+"/metrics/"+metrics+"/statistics/"+statistics+"/range?factDataSource="+factDataSource;
        //调用接口
        GetDemo getdemo = new GetDemo();
        getdemo.setExpectcode(expectcode);
        //输出返回结果
        System.out.println("StatusCode:"+getdemo.getUrl(url));


    }
}