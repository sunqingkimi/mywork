package base;

import okhttp3.*;
import org.testng.Assert;

public class OkHttp {
    public static String getExpectcode() {
        return expectcode;
    }

    public void setExpectcode(String expectcode) {
        this.expectcode = expectcode;
    }

    private static String expectcode;

    public static String getUrl(String url, String key, String value) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).addHeader(key, value).build();
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            String StatusCode = response.code() + "";
//            System.out.println("response status code -->"+StatusCode);
            Assert.assertEquals(StatusCode, getExpectcode(), result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static String postUrl(String url, String params) {
        try {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            String jsonStr = params;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).post(RequestBody.create(JSON, jsonStr)).build();
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            String StatusCode = response.code() + "";
            Assert.assertEquals(StatusCode, getExpectcode(), result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}