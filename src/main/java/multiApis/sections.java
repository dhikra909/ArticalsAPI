package multiApis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class sections {

	private Response response;
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	private String status;
	private String copyright;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String url = "jdbc:mysql://localhost:3306/my_Dataapi";
		String user = "root";
		String pass = "root";
	    HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("https://api.nytimes.com/svc/search/v2/articlesearch.json?q=election&api-key=ikkXtidGaU5HWtgVZXo02yaL4f3iyDe3")).build();
			
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				String uglyJsonString = response.body();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonParser jp = new JsonParser();
				JsonElement je = jp.parse(uglyJsonString);
				String prettyJsonString = gson.toJson(je);
				System.out.println(prettyJsonString);
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
