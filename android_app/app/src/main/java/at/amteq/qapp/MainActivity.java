package at.amteq.qapp;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.graphics.Color;
import android.net.Uri;
import android.content.Intent;
import android.webkit.ValueCallback;

public class MainActivity extends Activity {
    private static final String DEFAULT_URL = "http://10.0.0.74:8756";
    private static final int RETRY_MS = 3000;
    private WebView web;
    private ValueCallback<Uri[]> filePathCallback;
    private Handler handler = new Handler(Looper.getMainLooper());
    private String serverUrl;
    private boolean errorScreenVisible = false;

    @Override public void onCreate(Bundle b){
        super.onCreate(b);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 7);
        }
        SharedPreferences sp = getSharedPreferences("qapp", MODE_PRIVATE);
        serverUrl = sp.getString("url", DEFAULT_URL);
        setupWebView();
        showLoadingScreen("Verbinde automatisch mit AMTEQ Q-App...", serverUrl);
        handler.postDelayed(() -> loadServer(), 350);
    }

    private void setupWebView(){
        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setCacheMode(WebSettings.LOAD_NO_CACHE);
        web.clearCache(true);
        web.setWebViewClient(new WebViewClient(){
            @Override public void onPageFinished(WebView v, String url){
                errorScreenVisible = false;
            }
            @Override public void onReceivedError(WebView v, int code, String desc, String failingUrl){
                showAutoRetryScreen("Server nicht erreichbar", serverUrl);
            }
        });
        web.setWebChromeClient(new WebChromeClient(){
            @Override public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> cb, FileChooserParams params){
                filePathCallback = cb;
                Intent intent = params.createIntent();
                try { startActivityForResult(intent, 99); } catch(Exception e){ filePathCallback=null; return false; }
                return true;
            }
        });
    }

    private void loadServer(){
        errorScreenVisible = false;
        setContentView(web);
        web.loadUrl(serverUrl + (serverUrl.contains("?") ? "&" : "?") + "t=" + System.currentTimeMillis());
    }

    private TextView makeText(String text, int size, int padBottom){
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(size);
        tv.setPadding(0,0,0,padBottom);
        return tv;
    }

    private void showLoadingScreen(String title, String url){
        LinearLayout l = baseLayout();
        TextView t = makeText("AMTEQ Q-App", 28, 18); l.addView(t);
        TextView msg = makeText(title + "\n" + url, 17, 26); l.addView(msg);
        ProgressBar pb = new ProgressBar(this); l.addView(pb);
        setContentView(l);
    }

    private LinearLayout baseLayout(){
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(36,90,36,36);
        l.setBackgroundColor(Color.rgb(2,44,34));
        return l;
    }

    private void showAutoRetryScreen(String title, String current){
        if(errorScreenVisible) return;
        errorScreenVisible = true;
        LinearLayout l = baseLayout();
        l.addView(makeText("AMTEQ Q-App", 28, 18));
        l.addView(makeText(title + "\n\nDie App verbindet sich automatisch erneut.\nServer: " + current, 17, 26));
        ProgressBar pb = new ProgressBar(this); l.addView(pb);
        Button retry = new Button(this); retry.setText("Jetzt erneut verbinden"); l.addView(retry);
        retry.setOnClickListener(v -> loadServer());
        Button settings = new Button(this); settings.setText("Serveradresse ändern"); l.addView(settings);
        settings.setOnClickListener(v -> showSettingsScreen(current));
        setContentView(l);
        handler.postDelayed(() -> { if(errorScreenVisible) loadServer(); }, RETRY_MS);
    }

    private void showSettingsScreen(String current){
        LinearLayout l = baseLayout();
        l.addView(makeText("Serveradresse", 25, 20));
        EditText e = new EditText(this);
        e.setText(current);
        e.setTextColor(Color.WHITE);
        e.setHintTextColor(Color.LTGRAY);
        e.setSingleLine(true);
        l.addView(e);
        Button save = new Button(this); save.setText("Speichern und verbinden"); l.addView(save);
        Button reset = new Button(this); reset.setText("Standard verwenden: " + DEFAULT_URL); l.addView(reset);
        save.setOnClickListener(v -> {
            String u = e.getText().toString().trim();
            if(!u.startsWith("http://") && !u.startsWith("https://")) u = "http://" + u;
            serverUrl = u;
            getSharedPreferences("qapp",MODE_PRIVATE).edit().putString("url",u).apply();
            showLoadingScreen("Verbinde automatisch mit AMTEQ Q-App...", serverUrl);
            handler.postDelayed(() -> loadServer(), 300);
        });
        reset.setOnClickListener(v -> {
            serverUrl = DEFAULT_URL;
            getSharedPreferences("qapp",MODE_PRIVATE).edit().putString("url",DEFAULT_URL).apply();
            showLoadingScreen("Verbinde automatisch mit AMTEQ Q-App...", serverUrl);
            handler.postDelayed(() -> loadServer(), 300);
        });
        setContentView(l);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==99 && filePathCallback!=null){
            Uri[] res = WebChromeClient.FileChooserParams.parseResult(resultCode,data);
            filePathCallback.onReceiveValue(res); filePathCallback=null;
        }
    }
    @Override public void onBackPressed(){ if(web!=null && web.canGoBack()) web.goBack(); else super.onBackPressed(); }
}
