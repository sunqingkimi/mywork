package WeatherService.ExternalApiController;

import base.CSVData;
import base.GetDemo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Map;
import java.io.IOException;
import java.util.Iterator;

public class GfsGlobal {
    @DataProvider(name = "ApiData")
    public Iterator<Object[]> Numbers() throws IOException{
        return (Iterator<Object[]>)new CSVData( "WeatherService\\ExternalApiController\\","GfsGlobal.csv" );
    }

    @Test(testName = "接口测试",dataProvider = "ApiData",timeOut =10000)
    public void getTest(Map<String, String> data) {
        //遍历Excel中的数据
        String caseID = String.format( data.get( "caseID" ) );
        String descriptioncsv = String.format( data.get( "description" ) );
        String sourceAttributes = String.format( data.get( "sourceAttributes" ) );
        String startTime = String.format( data.get( "startTime" ) );
        String length = String.format( data.get( "length" ) );
        String longitude = String.format( data.get( "longitude" ) );
        String latitude = String.format( data.get( "latitude" ) );
        String expectcode = String.format(data.get("expectcode"));
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID+">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        //测试环境
        //String url = "http://10.27.21.149:8081/forecast/api/v1/hourly?sourceAttributes="+sourceAttributes+"&startTime="+startTime+"&length="+length+"&longitude="+longitude+"&latitude="+latitude;
        //线上环境
        String url = "http://10.21.6.24:8080/forecast/api/v1/hourly?sourceAttributes="+sourceAttributes+"&startTime="+startTime+"&length="+length+"&longitude="+longitude+"&latitude="+latitude;
        //调用接口
        GetDemo getdemo = new GetDemo();
        getdemo.setExpectcode(expectcode);
        //输出返回结果
        System.out.println("StatusCode:"+getdemo.getUrl(url));


    }
}