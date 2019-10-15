package sequence.ResultController;
import base.CSVData;
import base.GetDemo;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
public class resultAttribute {
    @DataProvider(name = "ApiData")
    public Iterator<Object[]> Numbers() throws IOException{
        return (Iterator<Object[]>)new CSVData( "sequence\\ResultController\\","resultAttribute.csv");
    }
    @Test(testName = "接口测试",dataProvider = "ApiData",timeOut =10000)
    public void getTest(Map<String, String> data) {
        //遍历Excel中的数据
        String caseID = String.format( data.get( "caseID" ) );
        String subject = String.format( data.get( "subject" ) );
        String masterId = String.format( data.get( "masterId" ) );
        String resultAttribute = String.format( data.get( "resultAttribute" ) );
        String forecastType = String.format( data.get( "forecastType" ) );
        String startTime = String.format( data.get( "startTime" ) );
        String length = String.format( data.get( "length" ) );
        String expectcode = String.format(data.get("expectcode"));
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        String url = "http://10.21.6.33:8080/api/result/subject/"+subject+"/masterId/"+masterId+"/resultAttribute/"+resultAttribute+"?forecastType="+forecastType+"&startTime="+startTime+"&length="+length;
        //调用接口
        GetDemo getdemo = new GetDemo();
        getdemo.setExpectcode(expectcode);
        //输出返回结果
        System.out.println("StatusCode:"+getdemo.getUrl(url));


    }
}
