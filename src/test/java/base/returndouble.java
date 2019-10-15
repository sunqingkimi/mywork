package base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class returndouble {
    /**
     * 入参weather查询URL和接口预期code
     * 结果返回成一个double类型的数组
     */
    public double[] doublearray(String url, String expectcode) {
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode(expectcode);
        String[] response = okhttp.getUrl(url,"null","null").replaceAll("[\\[\\]]", "").split(",");
        double[] response2 = IntStream.range(0, response.length).mapToDouble(i -> Double.parseDouble(response[i])).toArray();
        return response2;
    }

    /**
     * 历史批次时间添加到list中
     */
    public List<String> availableStartTime(String startTime, int batch, String expectcode, String weathertype) {
        List<String> list = new ArrayList();
        for (int reverseIndex = 0; reverseIndex < batch; reverseIndex++) {
            String historystarttimeurl = "http://10.21.6.24:8080/api/availableStartTime/weather/" + weathertype + "/startTime/" + startTime + "?reverseIndex=" + reverseIndex;
            OkHttp okhttp = new OkHttp();
            okhttp.setExpectcode(expectcode);
            list.add(okhttp.getUrl(historystarttimeurl,"null","null"));
        }
        return list;
    }

    /**
     * 拼接数据并返回
     */
//    public Double[] weatherdata(int batch, String last, String date, String weathertype, String sourceAttribute, List<String> list, String expectcode) {
//        int interval = 24 / batch;//计算每个批次间隔的小时数
//        int residue;//时间能整除间隔时，只要再加当前批次
//        int time=0;
//        returndouble Returndouble = new returndouble();
//        double[] HTSGWnow = new double[168];
//        if (last.equals(date + "T00:00:00Z")) {
//            time = 8;
//        } else if (last.equals(date + "T06:00:00Z")) {
//            time = 14;
//        } else if (last.equals(date + "T12:00:00Z")) {
//            time = 20;
//        } else if (last.equals(date + "T18:00:00Z")) {
//            time = 2;
//        }
//        if (time % interval == 0) {
//            residue = 1;
//        } else {
//            residue = 2;
//        }
//        int cishu = time / interval + residue;
//        for (int a = cishu - 2; a >= 0; a--) {
//            String waveurl = "http://10.21.6.24:8080/api/weather/" + weathertype + "/sourceAttribute/" + sourceAttribute + "/startTime/" + list.get(a).replaceAll("\"", "") + "?longitude=121.5&latitude=32.5&interval=PT1H&hot=false";
//            double[] HTSGW0double = Returndouble.doublearray(waveurl, expectcode);
//            int c = 0;
//            if(a==cishu - 2){
//                int b=0;
//                 c=interval-(time % interval);
//            }else {
//                c = 0;
//            }
//            for (int b = 0; b < 168; b++) {
//                HTSGWnow[b] = HTSGW0double[c];
//                c++;
//            }
//        }
//        String waveurl = "http://10.21.6.24:8080/api/weather/" + weathertype + "/sourceAttribute/" + sourceAttribute + "/startTime/" + last + "?longitude=121.5&latitude=32.5&interval=PT1H&hot=false";
//        double[] HTSGW0double = Returndouble.doublearray(waveurl, expectcode);
//        int c = 0;
//        for (int d = time; d < 168; d++) {
//            HTSGWnow[d] = HTSGW0double[c];
//            c++;
//        }
//        return HTSGWnow;
//    }
}
