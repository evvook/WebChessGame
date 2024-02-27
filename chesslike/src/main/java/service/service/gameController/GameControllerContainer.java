package service.gameController;

import java.util.HashMap;
import java.util.Map;

//������ ���� ���� �����ڸ� �����ϴ� ��ü
public class GameControllerContainer {
	public static Map<String,GameFlowController> controllerContainer = new HashMap<String,GameFlowController>();
	
	public static void putController(String key,GameFlowController controller) {
		System.out.println(key);
		controllerContainer.put(key, controller);
	}
	public static GameFlowController getController(String key) {
		System.out.println(key);
		return controllerContainer.get(key);
	}
	public static boolean isExists(String key) {
		return controllerContainer.containsKey(key);
	}
	public static boolean isExists(GameFlowController value) {
		return controllerContainer.containsValue(value);
	}
	
	public static Map<String,GameFlowController> getAllController(){
		return controllerContainer;
	}
}
