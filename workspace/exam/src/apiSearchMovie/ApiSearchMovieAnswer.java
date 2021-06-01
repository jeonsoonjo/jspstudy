package apiSearchMovie;
	
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ApiSearchMovieAnswer {
	
	/*
	 * 영화검색 애플리케이션
	 * 
	 * 9a5xHErBtTfKYNwPF9s0
	 * _w_IffuewE
	 * 
	*/

    public static void main(String[] args) {
        String clientId = "9a5xHErBtTfKYNwPF9s0"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "_w_IffuewE"; //애플리케이션 클라이언트 시크릿값"
        Scanner sc = new Scanner(System.in);
        System.out.println("영화 관련 검색어를 입력하세요");
        String query = sc.next();
        
        URL url = null;
        HttpURLConnection con = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
           String text = URLEncoder.encode(query, "UTF-8");
           String apiURL = "https://openapi.naver.com/v1/seㅋarch/movie?query=" + text; 
           
           Map<String, String> requestHeaders = new HashMap<>();
           requestHeaders.put("X-Naver-Client-Id", clientId);
           requestHeaders.put("X-Naver-Client-Secret", clientSecret);
           url = new URL(apiURL);
           con = (HttpURLConnection)url.openConnection();
           con.setRequestMethod("GET");
           for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
               con.setRequestProperty(header.getKey(), header.getValue());
           }

           br = new BufferedReader(new InputStreamReader(con.getInputStream()));
           StringBuilder responseBody = new StringBuilder();
           String line;
           while((line = br.readLine()) != null) {
        	   responseBody.append(line);
           }
           String result = responseBody.toString();
           bw = new BufferedWriter(new FileWriter("searh_result2.txt"));
           
	       JSONParser parser = new JSONParser();
	       JSONObject obj = (JSONObject)parser.parse(result);
	       JSONArray arr = (JSONArray)obj.get("items");
	       for(int i=0; i<arr.size(); i++) {
	       	JSONObject obj2 = (JSONObject)arr.get(i);
	       	String title = (String)obj2.get("title");
	       	bw.write(title.replaceAll("<.*?>", ""));
	       	bw.newLine();
	       }
	       System.out.println("search_result2.txt 파일에 결과가 저장되었습니다");
	        
        } catch (Exception e) {
        	try {
        		bw = new BufferedWriter(new FileWriter("search_error.txt"));
        		bw.write(new SimpleDateFormat("yyyy-MM-dd a h:mm:ss").format(new Date()));
        		System.out.println("search_error.txt 파일에 에러 로그가 저장되었습니다");
        	} catch (Exception e2) {
        		
        	}
        } finally {
			try {
				if(con!=null) {con.disconnect();}
				if(br!=null) {br.close();}
				if(bw!=null) {bw.close();}
			} catch (Exception e) {
				
			}
		}
        		
      }

}
