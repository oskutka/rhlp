package com.redhat.oskutka.rhlp.servlet;

import java.io.IOException;
import java.net.URLConnection;

@SuppressWarnings("serial")
public abstract class ZomatoRestaurantGetter extends ParsingRestaurantGetter {

	@Override
	protected String getUrl() {
        return "https://www.zomato.com/cs/widgets/daily_menu.php?entity_id=" + getZomatoId();
	}
	
	protected abstract String getZomatoId();

	@Override
	protected URLConnection getConnection() throws IOException {
		URLConnection connection = super.getConnection();
		connection.addRequestProperty("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.35 Safari/537.36 OPR/42.0.2393.27 (Edition beta)");
		connection.addRequestProperty("accept-language", "cs");
		connection.addRequestProperty("accept-encoding", "br");
		return connection;
	}
}
