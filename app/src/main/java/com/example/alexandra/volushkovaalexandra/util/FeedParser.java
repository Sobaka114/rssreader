package com.example.alexandra.volushkovaalexandra.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * simple xml parser
 * Created by Alexandra on 09.06.2017.
 */

public class FeedParser implements IParser {

    DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();


    public FeedParser() throws ParserConfigurationException {
    }

    public List<FeedItem> parse(InputStream feed, String sourceLabel) throws IOException, SAXException {
        List<FeedItem> result = new ArrayList<>();

        Document doc = builder.parse(feed);
        NodeList itemList = doc.getElementsByTagName("item");
        String title = null;
        String link = null;
        String imageLink = null;
        String description = null;
        Bitmap bmp = null;

        for (int i = 0; i < itemList.getLength(); i++) {
            Node item = itemList.item(i);
            NodeList childNodes = item.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node child = childNodes.item(j);
                if (!(child instanceof Element)) {
                    continue;
                }
                String name = ((Element)child).getTagName();

                if(name == null) {
                    continue;
                }
                switch (name) {
                    case "title":
                        title = child.getTextContent();
                        break;
                    case "link":
                        link = child.getTextContent();
                        break;
                    case "description":
                        description = child.getTextContent();
                        break;
                    case "enclosure":
                        NamedNodeMap attributes = child.getAttributes();
                        Node url = attributes.getNamedItem("url");
                        imageLink = url.getTextContent();
                        InputStream im = new URL(imageLink).openStream();
                        bmp = BitmapFactory.decodeStream(im);
                        break;
                }
            }
            FeedItem feedItem = new FeedItem(title, link, sourceLabel, description, imageLink, bmp);
            result.add(feedItem);
        }
        return result;
    }
}
