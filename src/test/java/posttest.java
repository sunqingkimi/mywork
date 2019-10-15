import base.CSVData;
import base.GetDemo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class posttest {
    @DataProvider(name = "getData")
    public Iterator<Object[]> Numbers() throws IOException {
        return (Iterator<Object[]>) new CSVData("", "posttest.csv");
    }

    @Test(testName = "接口测试", dataProvider = "getData", timeOut = 10000)
    public void getTest(Map<String, String> data) throws Exception {
        InputStreamReader read = new InputStreamReader(new FileInputStream("D:\\FirstAPITest\\src\\test\\java\\testdata\\posttest.xml"), "UTF-8");
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(read);
        String row;
        while ((row = br.readLine()) != null) {
            sb.append(row.trim());
        }
        String params = sb.toString();
        String caseID = String.format(data.get("caseID"));
        String expectcode = String.format(data.get("expectcode"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID + ">>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(params);
        String url = String.format(data.get("url"));
        System.out.println(url);
        GetDemo getdemo = new GetDemo();
        getdemo.setExpectcode(expectcode);
        //输出返回结果
        System.out.println("StatusCode:" + getdemo.doPost(url, params));
    }
}
