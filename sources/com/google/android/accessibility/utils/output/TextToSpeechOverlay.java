package com.google.android.accessibility.utils.output;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.widgets.simple.SimpleOverlay;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextToSpeechOverlay extends SimpleOverlay {
    private final OverlayHandler handler;
    public final TextView text;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OverlayHandler extends WeakReferenceHandler {
        public OverlayHandler(TextToSpeechOverlay textToSpeechOverlay) {
            super(textToSpeechOverlay);
        }

        protected static final void handleMessage$ar$ds(Message message, TextToSpeechOverlay textToSpeechOverlay) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                textToSpeechOverlay.text.setText("");
                textToSpeechOverlay.hide();
                return;
            }
            try {
                textToSpeechOverlay.show();
            } catch (WindowManager.BadTokenException e) {
                LogUtils.e("TextToSpeechOverlay", e, "Caught WindowManager.BadTokenException while displaying text.", new Object[0]);
            }
            textToSpeechOverlay.text.setBackgroundColor(message.arg1);
            textToSpeechOverlay.text.setText((CharSequence) message.obj);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            handleMessage$ar$ds(message, (TextToSpeechOverlay) obj);
        }
    }

    public TextToSpeechOverlay(Context context) {
        super(context);
        this.handler = new OverlayHandler(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.params);
        layoutParams.type = 2032;
        layoutParams.format = -2;
        layoutParams.flags |= 8;
        layoutParams.flags |= 16;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 81;
        setParams(layoutParams);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.tts_overlay_text_padding);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.tts_overlay_text_bottom_margin);
        TextView textView = new TextView(context);
        this.text = textView;
        textView.setBackgroundColor(-1442840576);
        textView.setTextColor(-1);
        textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        textView.setGravity(17);
        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(0, 0, 0, dimensionPixelSize2);
        frameLayout.addView(textView, layoutParams2);
        this.contentView.removeAllViews();
        this.contentView.addView(frameLayout);
    }

    public final void displayText(CharSequence charSequence, int i) {
        int i2;
        if (TextUtils.isEmpty(charSequence)) {
            this.handler.sendEmptyMessage(2);
            return;
        }
        if (i == 16384) {
            i2 = -3602424;
        } else {
            i2 = -1442840576;
        }
        long max = Math.max(2000, charSequence.length() * 100);
        this.handler.removeMessages(2);
        OverlayHandler overlayHandler = this.handler;
        overlayHandler.sendMessage(Message.obtain(overlayHandler, 1, i2, -1, charSequence.toString().trim()));
        this.handler.sendEmptyMessageDelayed(2, max);
    }
}
