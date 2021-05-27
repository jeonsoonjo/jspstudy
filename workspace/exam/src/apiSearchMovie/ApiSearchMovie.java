package apiSearchMovie;
	
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@SuppressWarnings("resource")
public class ApiSearchMovie {
	/*
	 * 영화검색 애플리케이션
	 * 
	 * 9a5xHErBtTfKYNwPF9s0
	 * _w_IffuewE
	 * 
	*/
	
    public static void main(String[] args) throws ParseException {
        String clientId = "9a5xHErBtTfKYNwPF9s0"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "_w_IffuewE"; //애플리케이션 클라이언트 시크릿값"

        System.out.println("영화 제목을 입력하세요");
		Scanner sc = new Scanner(System.in);
        String genre = sc.next();
        try {
        	genre = URLEncoder.encode("코미디", "UTF-8");
        	String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + genre; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
        	
        	Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", clientId);
            requestHeaders.put("X-Naver-Client-Secret", clientSecret);
            String responseBody = get(apiURL,requestHeaders);
            
            JSONParser parser = new JSONParser();
        	JSONObject obj = (JSONObject)parser.parse(responseBody);
        	JSONArray items = (JSONArray)obj.get("items");
        	for(int i=0; i<items.size(); i++) {
        		JSONObject results = (JSONObject)items.get(i);
        		String title = (String)results.get("title");
        		
	        	StringBuffer sb = new StringBuffer();
	        	sb.append(title);
	        	System.out.println(sb.toString());
        	}

        	FileOutputStream fos=null;
    		try {
    			fos=new FileOutputStream("search_result.txt");
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
        	
        	// System.out.println(responseBody);
        	
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
    }

    
    
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

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
	
	

