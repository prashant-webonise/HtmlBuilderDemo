package com.exmaple.htmlbuilderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.prashant.java.htmlbuilder.HtmlBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private WebView webView;
    private HtmlBuilder htmlBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);


        htmlBuilder = new HtmlBuilder()
                .openHtml()
                .openHead()
                .addHtml("inside head")
                .closeHead()
                .newline()
                .openBody()
                .add("inside body")
                .newline()
                .addLink("This is Sparta!!!", "https://www.stackoverflow.com")
                .newline()
                .addNbsps(14)
                .add("nbsp example")
                .newline()
                .beginItalic()
                .addBold("bold and italic")
                .endItalic()
                .newline()
                .beginOrderedList()
                .listItem()
                .add("item 1")
                .listItem()
                .add("item 2")
                .listItem()
                .addLink("localhost", "http://127.0.0.1")
                .endOrderedList()
                .add("list end")
                .newline()
                .beginDivWithStyle("color : red")
                .addOnClick("Click me", "alert('I am alert');")
                .endDiv()
                .newline()
                .addImage("https://ih0.redbubble.net/image.128960625.4633/st%2Csmall%2C215x235" +
                        "-pad%2C210x230%2Cf8f8f8.lite-1u2.jpg", "suckerr")
                .newline()
                .addHeading("I AM HEADER", "#AAD")
                .newline()
                .beginTable("style=\"border: 1px solid black;\"")
                .addTableRow(true, "row 1", "row 2", "row 3", "row 4")
                .endTableRow()
                .addTableRow("row 5", "row 6", "row 7", "row 8")
                .endTableRow()
                .endTable()
                .add("end of table")
                .newline()
                .addIcon("https://images-cdn.9gag.com/photo/azL60YN_460s.jpg")
                .closeBody()
                .closeHtml();
        String htmlTableString = htmlBuilder.getHtml();
        Log.d(TAG, "onCreate: " + htmlTableString);
        webView.loadDataWithBaseURL(null, htmlTableString, "text/html", "charset=UTF-8", null);
    }
}
