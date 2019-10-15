package base;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


/**
 * GET请求示例
 *
 * @author suqning
 */
public class GetDemo {
    public static String getExpectcode() {
        return expectcode;
    }

    public void setExpectcode(String expectcode) {
        this.expectcode = expectcode;
    }

    private static String expectcode;

    public static int getUrl(String url) {
        try {
            // 1. 创建HttpClient对象
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 2. 创建url对象
            HttpGet httpGet = new HttpGet(url);
            log.println(httpGet);
            // 3. 执行GET请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            log.println("Response:" + EntityUtils.toString(entity));
            String actualcode = response.getStatusLine().getStatusCode() + "";
            Assert.assertEquals(getExpectcode(), actualcode, "Not equals: ");
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            return Integer.parseInt(null);
        }
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) throws Exception {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);// 创建httpPost
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            String charSet = "UTF-8";
            StringEntity entity = new StringEntity(params, charSet);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = null;
            response = httpclient.execute(httpPost);
            log.println(httpPost);
            StatusLine status = response.getStatusLine();
            String state = status.getStatusCode() + "";
            log.println(state);
            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
            Assert.assertEquals(getExpectcode(), state, jsonString);
            return jsonString;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * put请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPut(String url, String params) throws Exception {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpPut httpPut = new HttpPut(url);// 创建httpPost
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-Type", "application/json");
            String charSet = "UTF-8";
            StringEntity entity = new StringEntity(params, charSet);
            httpPut.setEntity(entity);
            CloseableHttpResponse response = null;
            response = httpclient.execute(httpPut);
            StatusLine status = response.getStatusLine();
            String state = status.getStatusCode() + "";
            log.println(state);
            HttpEntity responseEntity = response.getEntity();
            String jsonString = EntityUtils.toString(responseEntity);
            Assert.assertEquals(getExpectcode(), state, jsonString);
            return jsonString;
        } catch (Exception e) {
            return null;
        }
    }
}