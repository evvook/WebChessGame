package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.gameController.GameControllerContainer;
import service.gameController.GameFlowController;

/**
 * Servlet implementation class MatchingListController
 */
@WebServlet("/MatchingListController")
public class MatchingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchingListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("매칭리스트 컨트롤러 입니다.");
		
		StringBuffer sb = new StringBuffer();
		BufferedReader br = request.getReader();
		String line = null;
		while((line = br.readLine())!=null) {
			sb.append(line);
		}
		
//		Map<String,String> data = new HashMap<String,String>();
		//입력을 json객체 형태로 가공
		Gson gson = new Gson();
//		data = gson.fromJson(sb.toString(),data.getClass());
		
		//리스트로 만들어서 던짐
		Map<String,GameFlowController> controllerContainer = GameControllerContainer.getAllController();
		List<String> battleControllers = new ArrayList<String>();
		for(String key:controllerContainer.keySet()) {
			if("battle".equals(controllerContainer.get(key).getMode())) {
				if(2 > controllerContainer.get(key).getNumOfUser()) {
					battleControllers.add(key);
				}
			}
		}
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("readyToBattle", battleControllers);
		
		String jsonString = gson.toJson(data);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		out.flush();
	}

}
