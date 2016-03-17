package com.robinlee.xmlparser;

import android.app.Activity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.robinlee.androiddemo.R;

import java.io.IOException;

/**
 * Created by robinlee on 3/16/16.
 */
public class XmlParserActivity extends Activity{

    private static final String TAG = XmlParserActivity.class.getSimpleName();

    private StringBuffer mStrXmlParserRst = new StringBuffer();
    private TextView mTextViewParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);
        initView();
        xmlParserWithAttributeSet();
    }

    private void initView(){
        mTextViewParser = (TextView) this.findViewById(R.id.mTextViewXmlParser);
    }

    private void xmlParserWithAttributeSet( ){
        XmlPullParser xmlPullParser = getResources().getXml(R.xml.xml_parser_test);
        AttributeSet attributeSet = Xml.asAttributeSet(xmlPullParser);

        if(xmlPullParser != null){

            try{

                int eventType = xmlPullParser.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){

                    if(eventType == XmlPullParser.START_DOCUMENT){
                        mStrXmlParserRst.append("xmlPullParser state : [start_document] \n");
                    }else if(eventType == XmlPullParser.START_TAG){
                        mStrXmlParserRst.append("xmlPullParser state : [start_tag]; tag_key : " + xmlPullParser.getName() + " \n ");
                    }else if(eventType == XmlPullParser.TEXT){
                        mStrXmlParserRst.append("xmlPullParser state : [text]; text : " + xmlPullParser.getText() + " \n ");
                    }else if(eventType == XmlPullParser.END_TAG){
                        mStrXmlParserRst.append("xmlPullParser state : [end_tag]; tag_key : " + xmlPullParser.getName() + " \n\n\n ");
                    }else if(eventType == XmlPullParser.END_DOCUMENT){
                        mStrXmlParserRst.append("xmlPullParser state : [end_document]" + "\n");
                    }

                    if(attributeSet != null){
                        int count = attributeSet.getAttributeCount();
                        if(count > 0){
                            for(int i = 0; i < count; i++){
                                mStrXmlParserRst.append("attributeSet getAttributeName(i) : " +
                                                        attributeSet.getAttributeName(i) + " \n ");
                                mStrXmlParserRst.append("attributeSet getAttributeNameResource(i) : " +
                                                        attributeSet.getAttributeNameResource(i) + " \n ");
                                int resourceValue = attributeSet.getAttributeResourceValue(i, 0);
                                if(resourceValue == 0){
                                    try {
                                        mStrXmlParserRst.append("attributeSet getAttributeFloatValue(i, 0) : " +
                                                attributeSet.getAttributeFloatValue(i, 0) + " \n ");
                                    }catch (RuntimeException e){
                                        e.printStackTrace();
                                    }
                                }else{
                                    mStrXmlParserRst.append("attributeSet getAttributeResourceValue(i, 0) : " +
                                            resourceValue + " \n ");
                                }
                            }
                        }
                    }

                    eventType = xmlPullParser.next();
                }
                mTextViewParser.setText(mStrXmlParserRst);
            }catch (XmlPullParserException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
