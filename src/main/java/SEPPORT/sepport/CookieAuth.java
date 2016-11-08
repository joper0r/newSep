package SEPPORT.sepport;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections4.map.PassiveExpiringMap;

public class CookieAuth {
	
	private static PassiveExpiringMap<String, Integer> cookies = new PassiveExpiringMap<String, Integer>(240,TimeUnit.MINUTES);

	//Checkt ob Cookie vorhanden
	public static boolean checkAuth(String cookie){
		String cookieL = cookie.substring(9);
		
		return Collections.synchronizedMap(getCookies()).containsKey(cookieL);
	}
	
	//Gibt die UserId für übergebenes Cookie aus
	public static int getUserId(String cookie){
		String cookieL = cookie.substring(9);
		if(Collections.synchronizedMap(getCookies()).get(cookieL)==null){
			return -1;
		}
		return Collections.synchronizedMap(getCookies()).get(cookieL);
	}
	
	//Checkt ob Nutzer bereits ein Cookie besitzt
	public static boolean checkIfAlreadyAuth(int userId){
		return Collections.synchronizedMap(getCookies()).containsValue(userId);
	}
	
	//Legt ein neues Cookie mit UserId in Hashmap an
	public static String insertAuth(int userId){
		String uuid = createCookie();
		Collections.synchronizedMap(getCookies()).put(uuid, userId);
		return "exsession="+uuid;
	}
	
	//Löscht ein vorhandenes Cookie
	public static void deleteAuth(String cookie){
		Collections.synchronizedMap(getCookies()).remove(cookie);
	}
	
	public static PassiveExpiringMap<String, Integer> getCookies() {
		return cookies;
	}
	
	//Erzeugt ein neues zufälliges Cookie TODO!!!!
	public static String createCookie(){
	UUID newCookie = UUID.randomUUID();
		return newCookie.toString();
	}
}
