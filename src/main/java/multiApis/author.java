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

public class Author {

	private String status;
	private String copyright;
	private boolean has_more;
	private Integer num_results;
	private Results1[] results;
	
	public Results1[] getResults() {
		return results;
	}
	public void setResults(Results1[] results) {
		this.results = results;
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
