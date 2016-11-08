package SEPPORT.sepport;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections4.map.PassiveExpiringMap;

public class CookieAuth {
	
	private static PassiveExpiringMap<String, Integer> cookies = new PassiveExpiringMap<String, Integer>(240,TimeUnit.MINUTES);

	//Checkt ob Cookie vorhanden
	public static boolean checkAuth(String cookie){
		return Collections.synchronizedMap(getCookies()).containsKey(cookie);
	}
	
	//Gibt die UserId für übergebenes Cookie aus
	public static int getUserId(String cookie){
		if(Collections.synchronizedMap(getCookies()).get(cookie)==null){
			return -1;
		}
		return Collections.synchronizedMap(getCookies()).get(cookie);
	}
	
	//Checkt ob Nutzer bereits ein Cookie besitzt
	public static boolean checkIfAlreadyAuth(int userId){
		return Collections.synchronizedMap(getCookies()).containsValue(userId);
	}
	
	//Legt ein neues Cookie mit UserId in Hashmap an
	public static void insertAuth(int userId){
		Collections.synchronizedMap(getCookies()).put(createCookie(), userId);
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
