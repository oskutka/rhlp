package com.redhat.oskutka.rhlp.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


@SuppressWarnings("serial")
public abstract class ZomatoRestaurantGetter extends RestaurantGetter {

	@Override
	protected String getUrl() {
		return "https://developers.zomato.com/api/v2.1/dailymenu?res_id=" + getZomatoId();
	}

	protected abstract String getZomatoId();

	@Override
	protected URLConnection getConnection() throws IOException {
		URLConnection connection = super.getConnection();
		connection.addRequestProperty("accept-language", "cs");
		connection.addRequestProperty("accept-encoding", "br");
		connection.addRequestProperty("user-key", getZomatoAPIKey());
		return connection;
	}

	protected String getZomatoAPIKey() {
		return System.getenv("ZOMATO_API_KEY");
	}

	@Override
	protected String getFreshMenuHTML() throws IOException, ParseException {
		StringBuffer sb = new StringBuffer();
		URLConnection connection = getConnection();
		JsonReader rdr = Json.createReader((InputStream) connection.getContent());
		JsonObject obj = rdr.readObject();
		List<JsonObject> menus = obj.getJsonArray("daily_menus").getValuesAs(JsonObject.class);
		if (!menus.isEmpty()) {
			JsonArray dishes = menus.get(0).getJsonObject("daily_menu").getJsonArray("dishes");
			if (dishes != null && !dishes.isEmpty()) {
				sb.append("<table>");
				for (JsonObject result : dishes.getValuesAs(JsonObject.class)) {
					JsonObject dish = result.getJsonObject("dish");
					sb.append("<tr><td>");
					sb.append(dish.getString("name"));
					sb.append("</td><td>");
					sb.append(dish.getString("price"));
					sb.append("</td></tr>");
				}
				sb.append("</table>");
			}
		}
		return stripImages() ? stripImages(sb.toString()) : sb.toString();
	}
}
