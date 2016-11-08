package SEPPORT.sepport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Controller
public class Server {

	@RequestMapping(value = "/dashboard")
	ResponseEntity<String> dashboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/html");
		if(checkForCookie(request)==false){
			headers.add("Refresh","0; url=http://localhost:8080/login");
			return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
		}
		
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("index.html"));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dashboard/css")
	ResponseEntity<String> homeCss(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/html");
		if(checkForCookie(request)==false){
			headers.add("Refresh","0; url=http://localhost:8080/login");
			return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
		}
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("/css/index.css"));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dashboard/js")
	ResponseEntity<String> homeJs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/html");
		if(checkForCookie(request)==false){
			headers.add("Refresh","0; url=http://localhost:8080/login");
			return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
		}
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("/js/index.js"));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret,headers, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/login")
	ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/html");
		
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("login.html"));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret, headers, HttpStatus.OK);
	}
	
	@PostMapping(value = "/login")
	ResponseEntity<String> loginPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/html");
		String user = request.getParameter("Username");
		String pass = request.getParameter("password");
		//int userId = DBAcces.authUser(user,pass);
		//if(userId==Null){
		//	headers.add("Refresh","0; url=http://localhost:8080/login");
		//}
		
		//if(CookieAuth.checkIfAlreadyAuth(userId)==false){
		//	CookieAuth.insertAuth(userId, CookieAuth.createCookie());
		//	headers.add("Refresh","0; url=http://localhost:8080/dashboard");
		//	return new ResponseEntity<String>(headers, HttpStatus.OK);
		//}
		//if(CookieAuth.checkIfAlreadyAuth(userId)==false){
		//	headers.add("Refresh","0; url=http://localhost:8080/dashboard");
		//	return new ResponseEntity<String>(headers, HttpStatus.OK);
		//}
		return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
	}
	
	public boolean checkForCookie(HttpServletRequest req){
		String cookie = req.getHeader("Cookie");
		if(cookie==null||CookieAuth.checkAuth(cookie)==false){
			return false;
		}
		return true;
	}
}
