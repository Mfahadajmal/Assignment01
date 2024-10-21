package com.google.android.accessibility.talkback.quickmenu;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.widget.SimpleOverlay;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class QuickMenuOverlay extends SimpleOverlay {
    private final Context context;
    private final Handler handler;
    private final Runnable hideOverlay;
    private final int layoutResId;
    private ImageView leftIcon;
    public CharSequence message;
    private LinearLayout overlay;
    private ImageView rightIcon;
    private TextView settingText;
    public boolean supported;

    public QuickMenuOverlay(Context context, int i) {
        super(context);
        this.handler = new Handler();
        this.hideOverlay = new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 20);
        this.supported = true;
        this.context = context;
        this.layoutResId = i;
    }

    @Override // com.google.android.accessibility.utils.widget.SimpleOverlay
    public final void hide() {
        if (this.overlay == null) {
            return;
        }
        this.handler.removeCallbacks(this.hideOverlay);
        super.hide();
    }

    public final boolean isShowing() {
        if (this.overlay != null && this.isVisible) {
            return true;
        }
        return false;
    }

    public final void show(boolean z) {
        AccessibilityManager accessibilityManager;
        if (this.supported && !TextUtils.isEmpty(this.message)) {
            if (this.overlay == null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.type = 2032;
                layoutParams.flags = 24;
                layoutParams.format = -3;
                layoutParams.width = -1;
                layoutParams.height = -2;
                layoutParams.gravity = 17;
                setParams(layoutParams);
                LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(this.layoutResId, (ViewGroup) null);
                this.overlay = linearLayout;
                this.settingText = (TextView) linearLayout.findViewById(R.id.quick_menu_text);
                this.leftIcon = (ImageView) this.overlay.findViewById(R.id.quick_menu_left_icon);
                this.rightIcon = (ImageView) this.overlay.findViewById(R.id.quick_menu_right_icon);
                setContentView(this.overlay);
            }
            this.settingText.setText(this.message);
            if (z) {
                this.leftIcon.setVisibility(0);
                this.rightIcon.setVisibility(0);
            } else {
                this.leftIcon.setVisibility(8);
                this.rightIcon.setVisibility(8);
            }
            if (isShowing()) {
                updateViewLayout();
            } else {
                super.show();
            }
            this.handler.removeCallbacks(this.hideOverlay);
            int i = 2750;
            if (SpannableUtils$IdentifierSpan.isAtLeastQ() && (accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility")) != null) {
                i = accessibilityManager.getRecommendedTimeoutMillis(2750, 2);
            }
            this.handler.postDelayed(this.hideOverlay, i);
        }
    }
}
