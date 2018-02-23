package org.sonatype.mavenbook.weather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class YahooRetriever {

	private static Logger log = Logger.getLogger(YahooRetriever.class);

	public InputStream retrieve(String zipcode) throws Exception {
		log.info( "Retrieving Weather Data" );
		// String url = "http://weather.yahooapis.com/forecastrss?p=" + zipcode;
		String url = "https://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20weather.forecast%20where%20woeid%20%3D%20%27" + zipcode + "%27%20and%20u%20%3D%20%27C%27%3B";

        // Add this if you need to connect via a corporate proxy
//      String proxyHost = "[proxy server]";
//      int proxyPort = [proxy server port];
//      SocketAddress addr = new InetSocketAddress(proxyHost, proxyPort);
//      Proxy httpProxy = new Proxy(Proxy.Type.HTTP, addr);
//      URLConnection conn = new URL(url).openConnection(httpProxy);
		URLConnection conn = new URL(url).openConnection();
		return conn.getInputStream();
	}

}
