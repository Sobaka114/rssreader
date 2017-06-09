package com.example.alexandra.volushkovaalexandra.util;

import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * interface for parce
 * get item list from stream
 * Created by Alexandra on 10.06.2017.
 */

public interface IParser {
    /**
     *
     * @param feed input stream
     * @param sourceLabel source label (use only as label)
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws SAXException
     */
    List<FeedItem> parse(InputStream feed, String sourceLabel) throws XmlPullParserException, IOException, SAXException;
}
