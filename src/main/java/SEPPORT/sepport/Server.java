package SEPPORT.sepport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@Controller
public class Server {
	
	@RequestMapping(value = "/{name}.{endung}")
	ResponseEntity<String> resourceStr(@PathVariable("name") String name,@PathVariable("endung") String endung, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();	
		if(checkForCookie(request)==false){
			headers.add("Refresh","0; url=http://localhost:8080/login");
			return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
		}
		headers.add("Content-Type", "text/html");
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("resources\\"+name+"."+endung));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/css/{name}.{endung}")
	ResponseEntity<String> resourceCss(@PathVariable("name") String name,@PathVariable("endung") String endung, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/css");
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("resources\\css\\"+name+"."+endung));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/js/{name}.{endung}")
	ResponseEntity<String> resourceJs(@PathVariable("name") String name,@PathVariable("endung") String endung, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "application/javascript");
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("resources\\js\\"+name+"."+endung));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/img/{name}.{endung}")
	ResponseEntity<byte[]> resourceImg(@PathVariable("name") String name, @PathVariable("endung") String endung, HttpServletRequest request, HttpServletResponse response){
		MultiValueMap<String, String> headers = new HttpHeaders();
		
		if(endung=="jpeg"||endung=="jpg"){
			headers.add("Content-Type", "image/jpeg");
		}else{
			headers.add("Content-Type", "image/"+endung);
		}
		
		File pfad = new File("");
		byte[] fileContent=null;
		File file = new File("resources\\img\\"+name+"."+endung);
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			fileContent = new byte[(int)file.length()];
			fin.read(fileContent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(fileContent,headers,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fonts/{name}.{endung}")
	ResponseEntity<String> resourceFonts(@PathVariable("name") String name,@PathVariable("endung") String endung, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		if(checkForCookie(request)==false){
			headers.add("Refresh","0; url=http://localhost:8080/login");
			return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
		}
		
		if(endung=="svg"){
			headers.add("Content-Type", "image/svg+xml");
		}
		if(endung=="ttf"){
			headers.add("Content-Type", "application/x-font-ttf");
		}
		if(endung=="otf"){
			headers.add("Content-Type", "application/x-font-opentype");
		}
		if(endung=="woff"){
			headers.add("Content-Type", "application/font-woff");
		}
		if(endung=="woff2"){
			headers.add("Content-Type", "application/font-woff2");
		}
		if(endung=="eot"){
			headers.add("Content-Type", "application/vnd.ms-fontobject");
		}
		if(endung=="sfnt"){
			headers.add("Content-Type", "application/font-sfnt");
		}
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("resources\\fonts\\"+name+"."+endung));
		while ((line = reader.readLine()) != null) {
			ret = ret + line;
		}
		reader.close();
		return new ResponseEntity<String>(ret,headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/")
	ResponseEntity<String> dashboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Type", "text/html");
		if(checkForCookie(request)==false){
			headers.add("Refresh","0; url=http://localhost:8080/login");
			return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
		}
		String ret = "";
		String line = "";
		BufferedReader reader = new BufferedReader(new FileReader("resources\\index.html"));
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
		BufferedReader reader = new BufferedReader(new FileReader("resources\\login.html"));
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
		String user = request.getParameter("user");
		String pass = request.getParameter("password");
		System.out.println(pass);
		System.out.println(user);
		if(user.equals("root")&&pass.equals("toor")){
			System.out.println("swag logged in");
			String cookie = CookieAuth.insertAuth(111);
			headers.add("Refresh","0; url=http://localhost:8080/index.html");
			headers.add("Set-Cookie", cookie);
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		}
		headers.add("Refresh","0; url=http://localhost:8080/login");
		return new ResponseEntity<String>(headers, HttpStatus.UNAUTHORIZED);
		
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
	}
	
	public boolean checkForCookie(HttpServletRequest req){
		String cookie = req.getHeader("Cookie");
		System.out.println(cookie);
		if(cookie==null||CookieAuth.checkAuth(cookie)==false){
			return false;
		}
		return true;
	}
}
