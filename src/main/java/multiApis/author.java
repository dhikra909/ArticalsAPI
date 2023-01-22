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

public class author {

	private String status;
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
	public boolean isHas_more() {
		return has_more;
	}
	public void setHas_more(boolean has_more) {
		this.has_more = has_more;
	}
	public Integer getNum_results() {
		return num_results;
	}
	public void setNum_results(Integer num_results) {
		this.num_results = num_results;
	}
	private String copyright;
	private boolean has_more;
	private Integer num_results;
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String url = "jdbc:mysql://localhost:3306/my_Dataapi";
		String user = "root";
		String pass = "root";
	    HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=godfather&api-key=ikkXtidGaU5HWtgVZXo02yaL4f3iyDe3")).build();
			
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				String uglyJsonString = response.body();
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonParser jp = new JsonParser();
				JsonElement je = jp.parse(uglyJsonString);
				String prettyJsonString = gson.toJson(je);
				System.out.println(prettyJsonString);
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
