import base.CSVData2;
import base.OkHttp;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class jsontest {
    @DataProvider(name = "searchData")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\create.csv");
    }

    @Test(testName = "接口测试", dataProvider = "searchData", timeOut = 10000)
    public void getTest(String isNeedRun, String caseID, String projectId, String endTime, String powerLimitedTo, String startTime, String sensorId, String operator, String reason, String expectcode) {
        String url = "http://10.27.21.139:8080/api/schedulePlan/";
        Map schedulePlanAttr = new HashMap();
        schedulePlanAttr.put("endTime", endTime);
        schedulePlanAttr.put("powerLimitedTo", powerLimitedTo);
        schedulePlanAttr.put("startTime", startTime);
        Map createSchedulePlans = new HashMap();
        createSchedulePlans.put("schedulePlanAttr", schedulePlanAttr);
        createSchedulePlans.put("projectId", projectId);
        createSchedulePlans.put("sensorId", sensorId);
        List<Map> listmap = new ArrayList<>();
        listmap.add(createSchedulePlans);
        Map schedulePlan = new HashMap();
        schedulePlan.put("createSchedulePlans", listmap);
        schedulePlan.put("operator", operator);
        schedulePlan.put("projectId", projectId);
        schedulePlan.put("reason", reason);
        String json = JSON.toJSONString(schedulePlan);
        System.out.println(json);
//        if (isNeedRun.equals("false"))
//            return;
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
//                + caseID + ">>>>>>>>>>>>>>>>>>>>>>>");
//        OkHttp okhttp = new OkHttp();
//        okhttp.setExpectcode(expectcode);
//        System.out.println("result:" + okhttp.postUrl(url,json));
    }
}
