import base.OkHttp;

public class test2 {
    public static void main(String[] args) {
        String url="https://reqres.in/api/users";
        OkHttp okhttp = new OkHttp();
        okhttp.setExpectcode("200");
        System.out.printf(okhttp.getUrl(url,"null","null"));
    }
}