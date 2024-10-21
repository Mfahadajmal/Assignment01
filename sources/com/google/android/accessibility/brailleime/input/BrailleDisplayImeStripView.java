package com.google.android.accessibility.brailleime.input;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.ActionBarContextView;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableMap;
import io.grpc.internal.RetryingNameResolver;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleDisplayImeStripView extends RelativeLayout {
    private RetryingNameResolver.ResolutionResultListener callBack$ar$class_merging$40960935_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public ImageView dotsBackground;
    public final ImmutableMap dotsResMap;
    private Locale locale;
    private View switchToNextKeyboard;
    private View switchToTouchScreenKeyboard;

    public BrailleDisplayImeStripView(Context context) {
        super(context, null, 0);
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0(1, Integer.valueOf(R.drawable.dots_tapped_1));
        builder.put$ar$ds$de9b9d28_0(2, Integer.valueOf(R.drawable.dots_tapped_2));
        builder.put$ar$ds$de9b9d28_0(3, Integer.valueOf(R.drawable.dots_tapped_3));
        builder.put$ar$ds$de9b9d28_0(4, Integer.valueOf(R.drawable.dots_tapped_4));
        builder.put$ar$ds$de9b9d28_0(5, Integer.valueOf(R.drawable.dots_tapped_5));
        builder.put$ar$ds$de9b9d28_0(6, Integer.valueOf(R.drawable.dots_tapped_6));
        builder.put$ar$ds$de9b9d28_0(7, Integer.valueOf(R.drawable.dots_tapped_7));
        builder.put$ar$ds$de9b9d28_0(8, Integer.valueOf(R.drawable.dots_tapped_8));
        this.dotsResMap = builder.buildOrThrow();
        this.locale = Locale.getDefault();
        addView();
    }

    private final void addView() {
        View inflate = View.inflate(new ContextThemeWrapper(getContext(), R.style.BrailleDisplayKeyboardTheme), R.layout.brailledisplay_ime, this);
        this.switchToTouchScreenKeyboard = inflate.findViewById(R.id.switch_to_touch_screen_keyboard);
        this.switchToNextKeyboard = inflate.findViewById(R.id.switch_to_next_keyboard);
        this.dotsBackground = (ImageView) inflate.findViewById(R.id.dots_background);
        setCallBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.callBack$ar$class_merging$40960935_0$ar$class_merging$ar$class_merging$ar$class_merging);
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Locale locale = Locale.getDefault();
        if (!this.locale.equals(locale)) {
            refreshViews();
            this.locale = locale;
        }
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        Display display;
        float f;
        int makeMeasureSpec;
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        display = getContext().getDisplay();
        float height = display.getHeight();
        f = getContext().getResources().getFloat(R.dimen.braille_display_keyboard_height_fraction);
        int i3 = (int) (height * f);
        if (mode == 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), mode);
        }
        super.onMeasure(i, makeMeasureSpec);
    }

    public final void refreshViews() {
        removeAllViews();
        addView();
    }

    public final void setCallBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        ActionBarContextView.AnonymousClass1 anonymousClass1;
        this.callBack$ar$class_merging$40960935_0$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        View view = this.switchToTouchScreenKeyboard;
        ActionBarContextView.AnonymousClass1 anonymousClass12 = null;
        if (resolutionResultListener == null) {
            anonymousClass1 = null;
        } else {
            anonymousClass1 = new ActionBarContextView.AnonymousClass1(resolutionResultListener, 18, null);
        }
        view.setOnClickListener(anonymousClass1);
        View view2 = this.switchToNextKeyboard;
        if (resolutionResultListener != null) {
            anonymousClass12 = new ActionBarContextView.AnonymousClass1(resolutionResultListener, 19, null);
        }
        view2.setOnClickListener(anonymousClass12);
    }
}
