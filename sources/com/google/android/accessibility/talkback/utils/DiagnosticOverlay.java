package com.google.android.accessibility.talkback.utils;

import android.content.Context;
import android.os.Message;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat$Api23Impl;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.widget.SimpleOverlay;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DiagnosticOverlay extends SimpleOverlay {
    public final OverlayHandler mHandler;
    public final TextView mText;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OverlayHandler extends WeakReferenceHandler {
        public OverlayHandler(DiagnosticOverlay diagnosticOverlay) {
            super(diagnosticOverlay);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            DiagnosticOverlay diagnosticOverlay = (DiagnosticOverlay) obj;
            if (message.what != 1) {
                return;
            }
            diagnosticOverlay.mText.setText("");
            diagnosticOverlay.hide();
        }
    }

    public DiagnosticOverlay(Context context) {
        super(context);
        this.mHandler = new OverlayHandler(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.params);
        layoutParams.format = -2;
        layoutParams.flags |= 8;
        layoutParams.flags |= 16;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 49;
        setParams(layoutParams);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.diagnostic_overlay_text_padding);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.diagnostic_overlay_text_bottom_margin);
        TextView textView = new TextView(context);
        this.mText = textView;
        textView.setBackgroundColor(ContextCompat$Api23Impl.getColor(context, R.color.diagnostic_overlay_background));
        textView.setTextColor(-1);
        textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        textView.setGravity(3);
        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(0, 0, 0, dimensionPixelSize2);
        frameLayout.addView(textView, layoutParams2);
        setContentView(frameLayout);
    }
}
