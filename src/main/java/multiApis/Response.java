package multiApis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Response {

	
	private Docs[] docs;

	public Docs[] getDocs() {
		return docs;
	}

	public void setDocs(Docs[] docs) {
		this.docs = docs;
	}

	
}
