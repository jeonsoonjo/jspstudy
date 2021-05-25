package quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz04")
public class Quiz04 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Quiz04() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 처리
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String contents=request.getParameter("contents");
		String date=new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date());
		String ip=request.getRemoteAddr();
		if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			InetAddress inet=InetAddress.getLocalHost();
			ip=inet.getHostAddress();
		}
		/*
			  String ip=request.getHeader("X-Forwarded-For");
			  if(ip==null){
			  	ip=request.getRemoteAddr();
			  }
		*/
	
		// 파일명 생성
		String filename=date.substring(0, 10) + "_" + writer + ".txt";
		
		// 파일 경로 지정
		File file=new File("D:\\spring0303_jsj\\jspstudy", filename);
		
		// 파일 생성 스트림
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		
		// 파일 생성
		bw.write("작성일자: "+ date); bw.newLine(); // 줄바꿈 메소드
		bw.write("작성IP: "+ ip); bw.newLine();
		bw.write("작성자: "+ writer); bw.newLine();
		bw.write("제목: "+ title); bw.newLine();
		bw.write(contents); bw.newLine();
		if(bw!=null) { // 따로 try-catch 작업을 안 하는 이유는, doGet()에서 예외처리를 미리 했기 때문이다
			bw.close(); 			
		}
		
		
		// 응답 처리
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('"+filename+"파일이 생성되었습니다.')");
		out.println("history.back()");
		out.println("</script>");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
