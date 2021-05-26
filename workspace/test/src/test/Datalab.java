package test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Datalab {
	/*
	 * 데이터랩 애플리케이션
	 * 
	 * 4jebcZ9PENMz7fliCkoR
	 * A_VCuHzhc0
	 * 
	 */
    public static void main(String[] args) {
        String clientId = "4jebcZ9PENMz7fliCkoR"; // 애플리케이션 클라이언트 아이디
        String clientSecret = "A_VCuHzhc0"; // 애플리케이션 클라이언트 시크릿

        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

        String requestBody = "{\"startDate\":\"2017-01-01\"," +
                "\"endDate\":\"2017-04-30\"," +
                "\"timeUnit\":\"month\"," +
                "\"keywordGroups\":[{\"groupName\":\"한글\"," + "\"keywords\":[\"한글\",\"korean\"]}," +
                "{\"groupName\":\"영어\"," + "\"keywords\":[\"영어\",\"english\"]}]," +
                "\"device\":\"pc\"," +
                "\"ages\":[\"1\",\"2\"]," +
                "\"gender\":\"f\"}";
        
        // 네이버가 응답한 JSON
        String responseBody = post(apiUrl, requestHeaders, requestBody);
        // System.out.println(responseBody);
        
        // key값 빼기
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
	       	 obj = (JSONObject)parser.parse(requestBody);
	       	 String startDate = obj.get("startDate").toString();
	       	 String endDate = obj.get("endDate").toString();
	       	 String timeUnit = obj.get("timeUnit").toString();
	       	 String device = obj.get("device").toString();
	       	 JSONArray ages = (JSONArray)obj.get("ages");
	       	 for(int i=0; i<ages.size(); i++) {
	       		 JSONObject obj2 = (JSONObject)ages.get(i);
	       	 }
	    } catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println(obj.get("startDate"));
        System.out.println(obj.get("endDate"));
        System.out.println(obj.get("timeUnit"));
        System.out.println(obj.get("device"));
        System.out.println(obj.get("ages"));
        
        // https://taein0910.github.io/blog/2019-07-11/java-crawaling/
        // https://coldmater.tistory.com/124
        // https://wowon.tistory.com/122
        // https://velog.io/@garam0410/Java-OPEN-API-%ED%8C%8C%EC%8B%B1%ED%95%98%EA%B8%B0-JSON
        // https://zoomkoding.github.io/codingtest/java/2019/09/20/REST-JSON.html
        // https://sidepower.tistory.com/267?category=899155
        // https://itrh.tistory.com/76
        
        FileOutputStream fos=null;
		try {
			fos=new FileOutputStream("test.txt");
			String str1 = responseBody;
			fos.write(str1.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos!=null) fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        System.out.println(fos + " 파일이 생성되었습니다.");
        
        
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}