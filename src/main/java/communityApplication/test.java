package communityApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

import com.baidu.aip.ocr.AipOcr;


public class test {

    public static final String APP_ID = "16392823";
    public static final String API_KEY = "b1sjSrWdtVL3LwXggevhZWtS";
    public static final String SECRET_KEY = "GeiLdSGOnsSMHptExkgHqgQq44F0o710";
    
    public static void main(String[] args) {
    	getAuth();
    }
    
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "b1sjSrWdtVL3LwXggevhZWtS";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "GeiLdSGOnsSMHptExkgHqgQq44F0o710";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            
            System.out.println(jsonObject.toString(2));
            String access_token = jsonObject.getString("access_token");
            System.out.println(access_token);
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
    
	/*@Test
	public void testPic() throws IOException {
		 // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
       // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
       // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:/Users/mi/Pictures/test.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
        String json =res.toString();
        JSONArray jsonArray = res.getJSONArray("words_result");
        Iterator<Object> it = jsonArray.iterator();
        StringBuilder s = new StringBuilder();
        while (it.hasNext()) {
			JSONObject next = (JSONObject) it.next();
			s.append(next.getString("words"));
		}
        //System.out.println(s.toString());
        HashMap<String, String> options = new HashMap<String, String>();
        
        
        // 参数为本地路径
        String image = "C:/Users/mi/Pictures/test.jpg";
        JSONObject res2 = client.businessCard(image, options);
        System.out.println(res2.toString(2));

        
	}*/
}
