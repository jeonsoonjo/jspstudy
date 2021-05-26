package test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class SearchBlog {

/*
 * 검색(블로그) 애플리케이션
 * 
 * 5XNJ9rsPd6cqNVvzTzhK
 * Oo9Pg0h_GH
 * 
 */
    public static void main(String[] args) {
    	
        String clientId = "5XNJ9rsPd6cqNVvzTzhK"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "Oo9Pg0h_GH"; //애플리케이션 클라이언트 시크릿값"
        
        int display = 5;
        String text = null;
        try {
           text = URLEncoder.encode("그린팩토리", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/blog.json?query=" + text + "&display=" + display + "&";    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        // 네이버가 응답한 JSON데이터
        String responseBody = get(apiURL,requestHeaders);
        
        // System.out.println(responseBody);
        String responseDate = "{\r\n" + 
        		"	\"lastBuildDate\": \"Wed, 26 May 2021 19:36:41 +0900\"\r\n" + 
        		"	,\"total\": 9211,\r\n" + 
        		"	\"start\": 1,\r\n" + 
        		"	\"display\": 10,\r\n" + 
        		"	\"items\": [\r\n" + 
        		"		{ \"title\": \"제네시스 GV70 유리막코팅은 <b>그린팩토리</b>카케어 적극 추천\",\r\n" + 
        		"		  \"link\": \"https:\\/\\/blog.naver.com\\/kwak4893?Redirect=Log&logNo=222257681840\",\r\n" + 
        		"		  \"description\": \"안녕하세요 대전유리막코팅 전문점 <b>그린팩토리</b>카케어 곽영문입니다.\r\n" + 
        		"			        제네시스GV70 유리막코팅... 대표적으로 워터스팟, 스월마크등이 있는데, <b>그린팩토리</b>카케어에서는 유리막코팅 패키지에 이런 제거작업이... \",\r\n" + 
        		"		  \"bloggername\": \"그린팩토리카케어 : 블로그\",\r\n" + 
        		"		  \"bloggerlink\": \"https://blog.naver.com/kwak4893\",\r\n" + 
        		"		  \"postdate\": \"20210226\"},\r\n" + 
        		"\r\n" + 
        		"		{ \"title\": \"제라늄 씨앗 파종, 발아조건  (fr, <b>그린팩토리</b>님)\",\r\n" + 
        		"		  \"link\": \"https:\\/\\/blog.naver.com\\/kke5825?Redirect=Log&logNo=222254719953\",\r\n" + 
        		"		  \"description\": \"때마침 <b>그린팩토리</b>님께서 제라늄 씨앗 나눔을 하셨답니다. 네이버 리빙판에 노출이 되어 기념 나눔을...\r\n" + 
        		"			        제라늄 씨앗 이름은 그린스프링핑크 + 송살구 (1) 클로에 f2(2) 그린베아뜨리체 + 스오바(2) 엔젤크리스탈... \",\r\n" + 
        		"		  \"bloggername\": \"행복 Serenade\",\r\n" + 
        		"		  \"bloggerlink\": \"https://blog.naver.com/kke5825\",\r\n" + 
        		"		  \"postdate\": \"20210224\"},\r\n" + 
        		"		]\r\n" + 
        		"}";
        
        // 파싱
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
        	obj = (JSONObject)parser.parse(responseDate);
        	obj.get("display");
        } catch (Exception e) {
			e.printStackTrace();
		}
		
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream("blog.txt");
			String str1 = responseDate;
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