package com.example.alexandra.volushkovaalexandra;

import com.example.alexandra.volushkovaalexandra.data.model.FeedItem;
import com.example.alexandra.volushkovaalexandra.util.FeedParser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import static junit.framework.Assert.assertEquals;

/**
 * unit test for parser
 * Created by Alexandra on 09.06.2017.
 */

public class FeedParserUnitTest {
    @Test
    public void testParce() throws ParseException, XmlPullParserException, IOException, ParserConfigurationException, SAXException {

        String lentaRssExample = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<rss version=\"2.0\">\n" +
                "  <channel>\n" +
                "    <language>ru</language>\n" +
                "    <title>Lenta.ru : Новости</title>\n" +
                "    <description>Новости, статьи, фотографии, видео. Семь дней в неделю, 24 часа в сутки</description>\n" +
                "    <link>http://lenta.ru</link>\n" +
                "    <image>\n" +
                "      <url>http://assets.lenta.ru/small_logo.png</url>\n" +
                "      <title>Lenta.ru</title>\n" +
                "      <link>http://lenta.ru</link>\n" +
                "      <width>134</width>\n" +
                "      <height>22</height>\n" +
                "    </image>\n" +
                "    <atom:link rel=\"self\" type=\"application/rss+xml\" href=\"http://lenta.ru/rss\"/>\n" +
                "<item>\n" +
                "  <guid>https://lenta.ru/news/2017/06/09/zvaniya/</guid>\n" +
                "  <title>Test</title>\n" +
                "  <link>https://testLink</link>\n" +
                "  <description>testDescription</description>\n" +
                "  <pubDate>Fri, 09 Jun 2017 20:06:05 +0300</pubDate>\n" +
                "  <enclosure url=\"https://icdn.lenta.ru/images/2017/06/09/23/20170609232610104/pic_4a17a5f25a9382909a3166da3a4ff883.jpg\" length=\"62113\" type=\"image/jpeg\"/>\n" +
                "  <category>Бывший СССР</category>\n" +
                "</item>\n" +
                "<item>\n" +
                "  <guid>https://lenta.ru/news/2017/06/09/runpeperun/</guid>\n" +
                "  <title>test2</title>\n" +
                "  <link>test2_link</link>\n" +
                "  <description>test2_decription</description>\n" +
                "  <pubDate>Fri, 09 Jun 2017 20:01:00 +0300</pubDate>\n" +
                "  <enclosure url=\"https://icdn.lenta.ru/images/2017/06/09/23/20170609232610104/pic_4a17a5f25a9382909a3166da3a4ff883.jpg\" length=\"22967\" type=\"image/jpeg\"/>\n" +
                "  <category>Интернет и СМИ</category>\n" +
                "</item>" +
                "  </channel>\n" +
                "</rss>";

        FeedParser feedParser = new FeedParser();
        List<FeedItem> lenta = feedParser.parse(IOUtils.toInputStream(lentaRssExample), "Lenta");


        //imgurl = test_url
        //url = https://testLink
        //descripion testDescription
        //title Test
        assertEquals(lenta.size(),2);

        FeedItem feedItem1 = lenta.get(0);
        assertEquals(feedItem1.getTitle(), "Test");
        assertEquals(feedItem1.getDescription(), "testDescription");
        assertEquals(feedItem1.getImageUrl(), "https://icdn.lenta.ru/images/2017/06/09/23/20170609232610104/pic_4a17a5f25a9382909a3166da3a4ff883.jpg");
        assertEquals(feedItem1.getItemUrl(), "https://testLink");
        assertEquals(feedItem1.getSource(), "Lenta");

        //test2
        //test2_link
        //test2_decription
        //test2_img_url

        FeedItem feedItem2 = lenta.get(1);
        assertEquals(feedItem2.getTitle(), "test2");
        assertEquals(feedItem2.getDescription(), "test2_decription");
        assertEquals(feedItem2.getImageUrl(), "https://icdn.lenta.ru/images/2017/06/09/23/20170609232610104/pic_4a17a5f25a9382909a3166da3a4ff883.jpg");
        assertEquals(feedItem2.getItemUrl(), "test2_link");
        assertEquals(feedItem2.getSource(), "Lenta");


    }
}
