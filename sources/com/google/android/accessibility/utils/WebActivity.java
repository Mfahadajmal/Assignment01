package com.google.android.accessibility.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public class WebActivity extends Activity {
    WebView webView;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AllowlistWebViewClient extends WebViewClient {
        private static final ImmutableList GOOGLE_HOST_PATTERNS;

        static {
            Pattern compile = Pattern.compile("[a-z]+.google.com");
            Pattern compile2 = Pattern.compile("[a-z]+.google.[a-z][a-z]");
            Pattern compile3 = Pattern.compile("[a-z]+.google.co.[a-z][a-z]");
            Pattern compile4 = Pattern.compile("[a-z]+.google.com.[a-z][a-z]");
            Pattern compile5 = Pattern.compile("[a-z]+.gstatic.com");
            Pattern compile6 = Pattern.compile("fonts.googleapis.com");
            int i = ImmutableList.ImmutableList$ar$NoOp;
            GOOGLE_HOST_PATTERNS = ImmutableList.construct(compile, compile2, compile3, compile4, compile5, compile6);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.webkit.WebViewClient
        public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            String host = webResourceRequest.getUrl().getHost();
            if (host == null) {
                LogUtils.i("AllowlistWebViewClient", "Failed to load URL because host was null.", new Object[0]);
                return new WebResourceResponse("", "", 403, "Denied", null, null);
            }
            ImmutableList immutableList = GOOGLE_HOST_PATTERNS;
            int i = ((RegularImmutableList) immutableList).size;
            int i2 = 0;
            while (i2 < i) {
                boolean matches = ((Pattern) immutableList.get(i2)).matcher(host).matches();
                i2++;
                if (matches) {
                    return null;
                }
            }
            LogUtils.i("AllowlistWebViewClient", "Failed to load URL because it is not allow-listed. Host: %s", host);
            return new WebResourceResponse("", "", 403, "Denied", null, null);
        }
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            finish();
            return;
        }
        setContentView(R.layout.web_activity);
        WebView webView = (WebView) findViewById(R.id.web);
        this.webView = webView;
        webView.setWebViewClient(new AllowlistWebViewClient());
        this.webView.loadUrl(data.toString());
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        WebView webView;
        if (i == 4 && (webView = this.webView) != null && webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        finish();
        return true;
    }
}
