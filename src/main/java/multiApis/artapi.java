package multiApis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class artapi {
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

		int x = A.getResults().length;
		for (int i = 0; i < x; i++) {
			String status = A.getStatus();
			String copyright = A.getCopyright();
			Integer num_results = A.getNum_results();
			String section = A.getResults()[i].getSection();
			String source = A.getResults()[i].getSource();
			String subSection = A.getResults()[i].getSubsection();
			String PublishedDate = A.getResults()[i].getPublished_date();
			String SQLqueryForInserting = "insert into articles(status,copyright, num_results,section,source,subsection,published_dateb)"
					+ " values('" + status + "' ,'" + copyright + "'," + num_results + ", '" + section + "', '"
					+ source + "', '" + subSection + "', '" + PublishedDate + "')";

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

		}
	}

}
