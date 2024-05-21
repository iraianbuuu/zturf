package com.example.zturf.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class Helper {
    private List<Turf> turf_detail= new ArrayList<Turf>();
    private Turf turf;
    private String text;

    public List<Turf> getEmployees() {
        return turf_detail;
    }

    public List<Turf> parse(InputStream is) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser  parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("details")) {
                            // create a new instance of employee
                            turf = new Turf();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("details")) {
                            // add employee object to list
                            turf_detail.add(turf);
                        }else if (tagname.equalsIgnoreCase("name")) {
                            turf.setName(text);
                        }  else if (tagname.equalsIgnoreCase("date")) {
                            turf.setDate(text);
                        } else if (tagname.equalsIgnoreCase("time")) {
                            turf.setTime(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        return turf_detail;
    }
}