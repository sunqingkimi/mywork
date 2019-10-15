package offshore_plan;

import base.CSVData2;
import base.OkHttp;
import base.addconfig;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.returndouble;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class weather_list {
    String offshoreplan;
    String weathergloba;

    @BeforeClass
    public void setUp() {
        addconfig Addconfig;
        Addconfig = new addconfig();
        offshoreplan = Addconfig.prop.getProperty("offshore-plan");
        weathergloba = Addconfig.prop.getProperty("weather-globa");
    }

    @DataProvider(name = "ApiData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\offshore_plan\\weather_list.csv");
    }

    @Test(testName = "天气信息接口", dataProvider = "ApiData")
    public void getTest(String caseID, String turbineId, String expectcode) {
        try {
            String weathertype = "WAVE";
            String sourceAttribute = "HTSGW";
            //打印caseID
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                    + caseID + ">>>>>>>>>>>>>>>>>>>>>>>");
            //从Excel文件中取出参数拼接URL
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间
            String date = sdf.format(new Date());
            System.out.println("窗口期数据日期为：" + sdf.format(new Date()));
            OkHttp okhttp = new OkHttp();
            okhttp.setExpectcode(expectcode);
            String refreshurl = offshoreplan+"/weather/refresh";
            okhttp.getUrl(refreshurl,"null","null");//接口返回信息赋值
            String url = offshoreplan + "/weather/list?turbineId=" + turbineId + "&startTime="
                    + sdf.format(new Date());
            //调用窗口期接口
            okhttp.setExpectcode(expectcode);
            String response = okhttp.getUrl(url, "null", "null");//接口返回信息赋值
            JSONObject jasonObject = JSONObject.fromObject(response);//把接口返回信息从String类型转换成json格式
            JSONObject jsonData = jasonObject.getJSONObject("WAVE");//通过getJSONObject获取WAVE下的数据
            String[] forecastData = jsonData.getString("forecastData").replaceAll("[\\[\\]]", "").split(",");//通过getString读值
            double[] forecastData2 = IntStream.range(0, forecastData.length).mapToDouble(i -> Double.parseDouble(forecastData[i])).toArray();
            String starttimerangeurl = weathergloba + "/api/weather/" + weathertype + "/startTimeRange?longitude=121.5&latitude=32.5";//通过weather接口拿到理论批次时间
            System.out.println(starttimerangeurl);
            okhttp.setExpectcode(expectcode);
            String response2 = okhttp.getUrl(starttimerangeurl, "null", "null");//接口返回信息赋值
            JSONObject jasonObject2 = JSONObject.fromObject(response2);//把接口返回信息从String类型转换成json格式
            String last = jasonObject2.getString("last");//当前最新批次时间
            System.out.println("wave理论的批次时间为：" + last);
            //查询历史批次时间，给后面的if使用,batch为数据源一天的批次数
            int batch = 4;
            List<String> list = new ArrayList();
            returndouble Returndouble = new returndouble();
            list = Returndouble.availableStartTime(last, batch, expectcode, weathertype);
            System.out.println(list);
            //当前批次的wave数据
            String waveurl = weathergloba + "/api/weather/WAVE/sourceAttribute/HTSGW/startTime/" + last + "?longitude=121.5&latitude=32.5&interval=PT1H&hot=false";
            double[] HTSGWdouble = Returndouble.doublearray(waveurl, expectcode);
            System.out.println(waveurl);
            //查找4四个历史批次的wave数据
            String waveurl0 = weathergloba + "/api/weather/WAVE/sourceAttribute/HTSGW/startTime/" + list.get(0).replaceAll("\"", "") + "?longitude=121.5&latitude=32.5&interval=PT1H&hot=false";
            double[] HTSGW0double = Returndouble.doublearray(waveurl0, expectcode);
            String waveurll = weathergloba + "/api/weather/WAVE/sourceAttribute/HTSGW/startTime/" + list.get(1).replaceAll("\"", "") + "?longitude=121.5&latitude=32.5&interval=PT1H&hot=false";
            double[] HTSGW1double = Returndouble.doublearray(waveurll, expectcode);
            String waveurl2 = weathergloba + "/api/weather/WAVE/sourceAttribute/HTSGW/startTime/" + list.get(2).replaceAll("\"", "") + "?longitude=121.5&latitude=32.5&interval=PT1H&hot=false";
            double[] HTSGW2double = Returndouble.doublearray(waveurl2, expectcode);
            String waveurl3 = weathergloba + "/api/weather/WAVE/sourceAttribute/HTSGW/startTime/" + list.get(3).replaceAll("\"", "") + "?longitude=121.5&latitude=32.5&interval=PT1H&hot=false";
            double[] HTSGW3double = Returndouble.doublearray(waveurl3, expectcode);
            double[] HTSGWnow = new double[168];
            System.out.println("查找4四个历史批次的wave数据完成");
            System.out.println("last:" + last);
            if (last.equals(sdf.format(new Date()) + "T00:00:00Z")) {
                HTSGWnow[0] = (HTSGW1double[4]);
                HTSGWnow[1] = (HTSGW1double[5]);
                HTSGWnow[2] = (HTSGW0double[0]);
                HTSGWnow[3] = (HTSGW0double[1]);
                HTSGWnow[4] = (HTSGW0double[2]);
                HTSGWnow[5] = (HTSGW0double[3]);
                HTSGWnow[6] = (HTSGW0double[4]);
                HTSGWnow[7] = (HTSGW0double[5]);
                for (int a = 0; a < 160; a++) {
                    HTSGWnow[a + 8] = (HTSGWdouble[a]);
                }
            } else if (last.equals(sdf.format(new Date()) + "T06:00:00Z")) {
                HTSGWnow[0] = (HTSGW2double[4]);
                HTSGWnow[1] = (HTSGW2double[5]);
                HTSGWnow[2] = (HTSGW1double[0]);
                HTSGWnow[3] = (HTSGW1double[1]);
                HTSGWnow[4] = (HTSGW1double[2]);
                HTSGWnow[5] = (HTSGW1double[3]);
                HTSGWnow[6] = (HTSGW1double[4]);
                HTSGWnow[7] = (HTSGW1double[5]);
                HTSGWnow[8] = (HTSGW0double[0]);
                HTSGWnow[9] = (HTSGW0double[1]);
                HTSGWnow[10] = (HTSGW0double[2]);
                HTSGWnow[11] = (HTSGW0double[3]);
                HTSGWnow[12] = (HTSGW0double[4]);
                HTSGWnow[13] = (HTSGW0double[5]);
                for (int a = 0; a < 154; a++) {
                    HTSGWnow[a + 14] = (HTSGWdouble[a]);
                }
            } else if (last.equals(sdf.format(new Date().getTime() - 1 * 24 * 60 * 60 * 1000) + "T12:00:00Z")) {
                HTSGWnow[0] = (HTSGW3double[4]);
                HTSGWnow[1] = (HTSGW3double[5]);
                HTSGWnow[2] = (HTSGW2double[0]);
                HTSGWnow[3] = (HTSGW2double[1]);
                HTSGWnow[4] = (HTSGW2double[2]);
                HTSGWnow[5] = (HTSGW2double[3]);
                HTSGWnow[6] = (HTSGW2double[4]);
                HTSGWnow[7] = (HTSGW2double[5]);
                HTSGWnow[8] = (HTSGW1double[0]);
                HTSGWnow[9] = (HTSGW1double[1]);
                HTSGWnow[10] = (HTSGW1double[2]);
                HTSGWnow[11] = (HTSGW1double[3]);
                HTSGWnow[12] = (HTSGW1double[4]);
                HTSGWnow[13] = (HTSGW1double[5]);
                HTSGWnow[14] = (HTSGW0double[0]);
                HTSGWnow[15] = (HTSGW0double[1]);
                HTSGWnow[16] = (HTSGW0double[2]);
                HTSGWnow[17] = (HTSGW0double[3]);
                HTSGWnow[18] = (HTSGW0double[4]);
                HTSGWnow[19] = (HTSGW0double[5]);
                for (int a = 0; a < 148; a++) {
                    HTSGWnow[a + 20] = (HTSGWdouble[a]);
                }
            } else if (last.equals(sdf.format(new Date().getTime() - 1 * 24 * 60 * 60 * 1000) + "T18:00:00Z")) {
//                for (int c = 0; c < 168; c++) {
//                    System.out.println(HTSGW0double[c]);
//                }

                HTSGWnow[0] = (HTSGW0double[4]);
                HTSGWnow[1] = (HTSGW0double[5]);

                for (int a = 0; a < 166; a++) {
                    HTSGWnow[a + 2] = (HTSGWdouble[a]);
                }
            }
            System.out.println("准备执行比较");
            for (int a = 0; a < 168; a++) {
                if (Double.compare(HTSGWnow[a], forecastData2[a]) != 0) {
                    System.out.println("下标数为：" + a + "\t" + "weather接口:" + HTSGWnow[a] + "\t" + "窗口期接口:" + forecastData2[a]);
                }
                int b = 0;
                Assert.assertEquals(b, Double.compare(HTSGWnow[a], forecastData2[a]), "Not equals: ");

            }
            System.out.println("结果一致");
        } catch (Exception e) {

        }
    }
}