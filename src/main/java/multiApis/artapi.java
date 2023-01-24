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

public class Artapi {
	public static void mainar() throws IOException, InterruptedException {
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
		Articles a = gson.fromJson(prettyJsonString, Articles.class);

		int x = a.getResults().length;
		for (int i = 0; i < x; i++) {
			String status = a.getStatus();
			String copyright = a.getCopyright();
			Integer num_results = a.getNum_results();
			String section = a.getResults()[i].getSection();
			String source = a.getResults()[i].getSource();
			String subSection = a.getResults()[i].getSubsection();
			String PublishedDate = a.getResults()[i].getPublished_date();
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
