package source_presenter;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import net.sf.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

public class getMinAndMax {
    String host;

    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        host = Addconfig.prop.getProperty("source-presenter");

    }

    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\source_presenter\\getMinAndMax.csv");
    }

    @Test(testName = "接口测试", dataProvider = "ApiData")
    public void getTest(String caseID, String time, String expectcode, String weatherelement, String mode, String isneedrun, String hour) {
        if (!Boolean.valueOf(isneedrun))
            return;
        int hours = Integer.parseInt(hour);
        long times = Long.parseLong(time);
        int ms = 3600000;
        Double[] b = new Double[hours * 2];
        //打印caseID
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: " + caseID + ">>>>>>>>>>>>>>>>>>>>>>>");
        //从Excel文件中取出参数拼接URL
        for (int a = 0; a < hours; a++) {
            String url = host + "/api/v1/surface/data?weatherelement=" + weatherelement + "&time=" + (times + ms * a) + "&mode=" + mode;
            //调用接口
            OkHttp okhttp = new OkHttp();
            okhttp.setExpectcode(expectcode);
            String response = okhttp.getUrl(url, "null", "null");
            JSONObject jasonObject = JSONObject.fromObject(response);//把接口返回信息从String类型转换成json格式
            String[] arr = jasonObject.getString("data").replaceAll("[\\[\\]]", "").split(",");
            Double[] ds = new Double[arr.length];
            for (int i = 0; i < arr.length; i++) {
                ds[i] = Double.valueOf(arr[i]);
            }
            b[a * 2] = Collections.min(Arrays.asList(ds));
            b[a * 2 + 1] = Collections.max(Arrays.asList(ds));
        }
        System.out.println(Collections.min(Arrays.asList(b)) + "\n" + Collections.max(Arrays.asList(b)));
    }
}
