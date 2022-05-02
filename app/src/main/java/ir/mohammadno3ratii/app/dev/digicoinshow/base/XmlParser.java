package ir.mohammadno3ratii.app.dev.digicoinshow.base;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * parse rss feed and return list form items and set A list for images
 * becose this feed hase proble in parse images
 */
public class XmlParser extends DefaultHandler  {

    private StringBuilder content;
    private boolean inMain;
    private boolean inSadana;
    private boolean inPrice;
    private boolean inItem;
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private Price price = new Price();
    private Item lastItem;


    public class Item {
        public String name;
        public String price;
        public String change;
        public String percent;
    }


    public class Price {
        public String status;
        public String time;
    }


    public XmlParser(String url) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            URL sourceUrl = new URL(url);
            xr.setContentHandler(this);
            xr.parse(new InputSource(sourceUrl.openStream()));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        content = new StringBuilder();

        if (localName.equalsIgnoreCase("main")) {
            inMain = true;
        }

        if (localName.equalsIgnoreCase("sadana-services")) {
            inSadana = true;
        }

        if (localName.equalsIgnoreCase("price-service")) {
            inPrice = true;
        }

        if (localName.equalsIgnoreCase("item")) {
            lastItem = new Item();
            itemList.add(lastItem);
            inItem = true;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (content != null) {
            content.append(ch, start, length);
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equalsIgnoreCase("main")) {
            inMain = false;
        } else if (localName.equalsIgnoreCase("sadana-services")) {
            inSadana = false;
        } else if (localName.equalsIgnoreCase("price-service")) {
            inPrice = false;
        } else if (localName.equalsIgnoreCase("item")) {
            inItem = false;
        }

        if (inItem) {
            if (localName.equalsIgnoreCase("name")) {
                lastItem.name = content.toString();
                // Log.i("LOG", "title In Item" + content.toString());
            }
            if (localName.equalsIgnoreCase("price")) {
                lastItem.price = content.toString();
                //Log.i("LOG", "description In Item" + content.toString());
            }
            if (localName.equalsIgnoreCase("change")) {
                lastItem.change = content.toString();
                // Log.i("LOG", "link In Item" + content.toString());
            }
            if (localName.equalsIgnoreCase("percent")) {
                lastItem.percent = content.toString();
            }
        } else if (inPrice) {
            if (localName.equalsIgnoreCase("status")) {
                price.status = content.toString();
            }
            if (localName.equalsIgnoreCase("time")) {
                price.time = content.toString();
            }
        }
        content = null;
    }


    public Item getItem(int index) {
        return itemList.get(index);
    }


    public ArrayList<Item> getItems() {
        return itemList;
    }

    public Price getPrice() {
        return price;
    }
}
