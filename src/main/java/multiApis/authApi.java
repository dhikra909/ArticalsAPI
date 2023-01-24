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

public class AuthApi {

	public static void mainath() throws IOException, InterruptedException {
		String url = "jdbc:mysql://localhost:3306/my_Dataapi";
		String user = "root";
		String pass = "root";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
				"https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=godfather&api-key=ikkXtidGaU5HWtgVZXo02yaL4f3iyDe3"))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String uglyJsonString = response.body();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJsonString);
		String prettyJsonString = gson.toJson(je);
		System.out.println(prettyJsonString);

		Author a = gson.fromJson(prettyJsonString, Author.class);
		int x = a.getResults().length;
		for (int i = 0; i < x; i++) {
			String status = a.getStatus();
			String copyright = a.getCopyright();
			boolean has_more = a.isHas_more();
			Integer num_results = a.getNum_results();
			String display_title = a.getResults()[i].getDisplay_title();
			String mpaa_rating = a.getResults()[i].getMpaa_rating();
			String SQLqueryForInserting = "insert into author(status,copyright,has_more,num_results,display_title,mpaa_rating)"
					+ " values('" + status + "' ,'" + copyright + "' ," + has_more + " ,'" + num_results + "' ,'"
					+ display_title + "' ,'" + mpaa_rating + "')";

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
