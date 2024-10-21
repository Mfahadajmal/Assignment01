package com.google.android.accessibility.talkback;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DimmingOverlayView extends LinearLayout {
    private View content;
    public ProgressBar progress;
    public int timerLimit;
    public TextView timerView;

    public DimmingOverlayView(Context context) {
        super(context);
        setOrientation(1);
        setGravity(17);
        setBackgroundColor(-16777216);
        LayoutInflater.from(getContext()).inflate(R.layout.dimming_overlay_exit_instruction, (ViewGroup) this, true);
        this.content = findViewById(R.id.content);
        this.timerView = (TextView) findViewById(R.id.timer);
        this.progress = (ProgressBar) findViewById(R.id.progress);
        setInstruction(context.getString(R.string.value_direction_down_and_right));
        setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.google.android.accessibility.talkback.DimmingOverlayView.1
            @Override // android.view.View.AccessibilityDelegate
            public final void sendAccessibilityEvent(View view, int i) {
                if (i == 32) {
                    return;
                }
                super.sendAccessibilityEvent(view, i);
            }
        });
    }

    private final void setContentVisibility(int i) {
        this.content.setVisibility(i);
    }

    public final void hideText() {
        setContentVisibility(8);
    }

    public final void setInstruction(String str) {
        String lowerCase;
        Context context = getContext();
        if (TextUtils.isEmpty(str)) {
            lowerCase = "";
        } else {
            lowerCase = ContextDataProvider.toLowerCase(str);
        }
        String string = context.getString(R.string.screen_dimming_exit_instruction_line2, lowerCase, context.getString(R.string.shortcut_disable_dimming));
        TextView textView = (TextView) findViewById(R.id.message_line_1);
        if (!FormFactorUtils.getInstance().isAndroidWear) {
            textView.setText(string);
        }
    }

    public final void showText() {
        setContentVisibility(0);
    }
}
