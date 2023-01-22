package multiApis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class articles {

	private String status;
	private String copyright;
	private Integer num_results;
	private Results[] results;
	
	
	

	public Results[] getResults() {
		return results;
	}

	public void setResults(Results[] results) {
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

	public Integer getNum_results() {
		return num_results;
	}

	public void setNum_results(Integer num_results) {
		this.num_results = num_results;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String url = "jdbc:mysql://localhost:3306/my_Dataapi";
		String user = "root";
		String pass = "root";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=ikkXtidGaU5HWtgVZXo02yaL4f3iyDe3"))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String uglyJsonString = response.body();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJsonString);
		String prettyJsonString = gson.toJson(je);
		System.out.println(prettyJsonString);
		articles A = gson.fromJson(prettyJsonString, articles.class);
//		articles  A = new Gson().fromJson(prettyJsonString, articles.class);
//		for (articles D : A) {
			String status = A.getStatus();
			String copyright = A.getCopyright();
			Integer num_results = A.getNum_results();
			String results = A.getResults()[0].getSection();
			String results1 = A.getResults()[0].getSource();
			String results2 = A.getResults()[0].getSubsection();
			String results3 = A.getResults()[0].getPublished_date();
			String SQLqueryForInserting = "insert into articles(status,copyright, num_results,result )" + " values('" + status
					+ "' ,'" + copyright + "', '" + num_results + "', '" + results +"', '" + results1 + "', '" +results2+"', '" +results3+"')";

			System.out.println(SQLqueryForInserting);
			Connection con = null;
			try {
				Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				DriverManager.registerDriver(driver);
				con = DriverManager.getConnection(url, user, pass);
				java.sql.Statement st = con.createStatement();
				// Executing query
				int m = st.executeUpdate(SQLqueryForInserting);
				if (m >= 0)
					System.out.println("inserted successfully : " + SQLqueryForInserting);
				else
					System.out.println("insertion failed");
				// Closing the connections
				con.close();
			} catch (Exception ex) {
				// Display message when exceptions occurs
				System.err.println(ex);

			}
//		}

	}
}
