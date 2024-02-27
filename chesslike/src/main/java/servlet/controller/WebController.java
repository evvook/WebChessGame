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
	
	//����ڷκ��� �Է��� �޾Ƽ� ������ �ϴ� ��ü
	//������ ���� ���� �����ڴ� ����ƽ������ �����ȴ�
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Է��� �Ľ��� �� �ְ� ��ó��
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = request.getReader();
			String line = null;
			while((line = br.readLine())!=null) {
				sb.append(line);
			}
			
			//������ �����͸� ���� ���� ��翡�� �����ϰ� ��� ����
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
