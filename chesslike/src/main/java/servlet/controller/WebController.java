package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.gameController.GameControllerContainer;
import service.gameController.GameFlowController;

@WebServlet("/WebController")
public class WebController extends HttpServlet{
	private static final long serialVersionUID = 7172054946465883267L;
	
	//사용자로부터 입력을 받아서 게임을 하는 객체
	//생성된 게임 진행 통제자는 스태틱영역에 보관된다
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//입력을 파싱할 수 있게 선처리
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = request.getReader();
			String line = null;
			while((line = br.readLine())!=null) {
				sb.append(line);
			}
			
			//가공한 데이터를 게임 진행 담당에게 전달하고 결과 받음
			GameFlowController gameFlowController = null;
			
			HttpSession session = request.getSession();
			String key = session.getId();
			
			StringBuffer input = new StringBuffer();
			input.append(sb.toString().replace("}",""));
			input.append(",");
			input.append("\"mode\":\""+getMode(request)+"\"");
			input.append(",");
			input.append("\"key\":\""+key+"\"");
			input.append("}");
			
			if(GameControllerContainer.isExists(key)) {
				gameFlowController = GameControllerContainer.getController(key);
			}else {
				gameFlowController = new GameFlowController();
				GameControllerContainer.putController(key, gameFlowController);
			}
			
			String jsonString = gameFlowController.run(input.toString());
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);
			out.flush();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
			PrintWriter out = response.getWriter();
			out.println(e.getMessage());
			out.flush();
		}
		
	}
	
	private String getMode(HttpServletRequest requset) {
		String uri = requset.getHeader("referer");
		uri = uri.split("\\?")[0];
		uri = uri.replace(requset.getHeader("origin"),"");
		uri = uri.replace("/chesslike/", "");
		uri = uri.replace(".jsp", "");
		return uri;
	}
}
