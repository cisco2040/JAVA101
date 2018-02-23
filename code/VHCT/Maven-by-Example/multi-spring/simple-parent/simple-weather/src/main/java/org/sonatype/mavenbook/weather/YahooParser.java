package org.sonatype.mavenbook.weather;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

import org.sonatype.mavenbook.weather.model.Location;
import org.sonatype.mavenbook.weather.model.Condition;
import org.sonatype.mavenbook.weather.model.Atmosphere;
import org.sonatype.mavenbook.weather.model.Wind;
import org.sonatype.mavenbook.weather.model.Weather;

public class YahooParser {

    private static Logger log = Logger.getLogger(YahooParser.class);
    
    public Weather parse(String zip, InputStream inputStream) throws Exception {
	Weather weather = new Weather();
	
	log.info( "Creating XML Reader" );
	SAXReader xmlReader = createXmlReader();
	Document doc = xmlReader.read( inputStream );
	
	log.info( "Parsing XML Response" );
	Location location = new Location();
	location.setCity( doc.valueOf("/query/results/channel/y:location/@city") );
	location.setRegion( doc.valueOf("/query/results/channel/y:location/@region") );
	location.setCountry( doc.valueOf("/query/results/channel/y:location/@country") );
	location.setZip( zip );
	weather.setLocation( location );

	Condition condition = new Condition();
	condition.setText( doc.valueOf("/query/results/channel/item/y:condition/@text") );
	condition.setTemp( doc.valueOf("/query/results/channel/item/y:condition/@temp") );
	condition.setCode( doc.valueOf("/query/results/channel/item/y:condition/@code") );
	condition.setDate( doc.valueOf("/query/results/channel/item/y:condition/@date") );
	condition.setWeather( weather );
	weather.setCondition( condition );

	Atmosphere atmosphere = new Atmosphere();
	atmosphere.setHumidity( doc.valueOf("/query/results/channel/y:atmosphere/@humidity") );
	atmosphere.setVisibility( doc.valueOf("/query/results/channel/y:atmosphere/@visibility") );
	atmosphere.setPressure( doc.valueOf("/query/results/channel/y:atmosphere/@pressure") );
	atmosphere.setRising( doc.valueOf("/query/results/channel/y:atmosphere/@rising") );
	atmosphere.setWeather( weather );
	weather.setAtmosphere( atmosphere );

	Wind wind = new Wind();
	wind.setChill( doc.valueOf("/query/results/channel/y:wind/@chill") );
	wind.setDirection( doc.valueOf("/query/results/channel/y:wind/@direction") );
	wind.setSpeed( doc.valueOf("/query/results/channel/y:wind/@speed") );
	wind.setWeather( weather );
	weather.setWind( wind );

	weather.setDate( new Date() );
	
	return weather;
    }
    
    private SAXReader createXmlReader() {
	Map<String,String> uris = new HashMap<String,String>();
        uris.put( "y", "http://xml.weather.yahoo.com/ns/rss/1.0" );
        
        DocumentFactory factory = new DocumentFactory();
        factory.setXPathNamespaceURIs( uris );
        
	SAXReader xmlReader = new SAXReader();
	xmlReader.setDocumentFactory( factory );
	return xmlReader;
    }
}
