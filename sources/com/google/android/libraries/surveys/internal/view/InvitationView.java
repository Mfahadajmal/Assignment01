package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InvitationView extends LinearLayout {
    public final Object InvitationView$ar$acceptButton;
    public final Object InvitationView$ar$declineButton;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.view.View$OnClickListener, java.lang.Object] */
    public InvitationView(Context context, List list) {
        super(context);
        this.InvitationView$ar$acceptButton = context;
        this.InvitationView$ar$declineButton = list;
        setOrientation(1);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.connection_device_action_button_view_padding_horizontal);
        int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.connection_device_action_button_view_padding_vertical);
        setPadding(dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset, dimensionPixelOffset2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WindowTrackerFactory windowTrackerFactory = (WindowTrackerFactory) it.next();
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from((Context) this.InvitationView$ar$acceptButton).inflate(R.layout.device_detail_button, (ViewGroup) null);
            Button button = (Button) viewGroup.findViewById(R.id.button);
            button.setText((CharSequence) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider);
            button.setOnClickListener(windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider);
            addView(viewGroup);
        }
    }

    private final void updateButtonSizeForAccessibility(Button button) {
        r0.post(new Runnable() { // from class: com.google.android.libraries.surveys.internal.resourceutils.LayoutUtils$$ExternalSyntheticLambda0
            public final /* synthetic */ int f$1 = R.dimen.survey_button_accessibility_padding;

            @Override // java.lang.Runnable
            public final void run() {
                Rect rect = new Rect();
                View view = button;
                view.getHitRect(rect);
                rect.top -= 2131166621;
                rect.left = rect.left;
                rect.right += r2;
                rect.bottom += r3;
                r4.setTouchDelegate(new TouchDelegate(rect, view));
            }
        });
    }

    public InvitationView(Context context) {
        super(context);
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.survey_invitation, (ViewGroup) this, true);
        Button button = (Button) findViewById(R.id.survey_prompt_take_survey_button);
        this.InvitationView$ar$acceptButton = button;
        Button button2 = (Button) findViewById(R.id.survey_prompt_no_thanks_button);
        this.InvitationView$ar$declineButton = button2;
        updateButtonSizeForAccessibility(button);
        updateButtonSizeForAccessibility(button2);
    }
}
