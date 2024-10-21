package com.google.android.accessibility.braille.brailledisplay.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.icu.text.NumberFormat;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoScrollManager {
    public boolean autoAdjustDurationEnabled;
    public final FloatingActionButton.ShadowDelegateImpl behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl behaviorNavigation$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Context context;
    public int duration;
    public final ApplicationModule feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl onDisplayContentChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    public final Runnable runnable = new WorkerKt$$ExternalSyntheticLambda0(this, 8, null);
    public final Handler handler = new Handler();

    public AutoScrollManager(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, ApplicationModule applicationModule, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2) {
        byte[] bArr = null;
        this.onDisplayContentChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this, bArr);
        this.onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 2, bArr);
        this.context = context;
        this.behaviorNavigation$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
        this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl2;
    }

    public final int getDuration() {
        if (this.autoAdjustDurationEnabled) {
            return Math.max(500, (this.duration * this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging.getCurrentShowContentLength()) / ((BdController) this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).displayer.displayProperties.numTextCells);
        }
        return this.duration;
    }

    public final String getSpeakDuration() {
        NumberFormat numberInstance;
        String format;
        int i;
        float f = this.duration;
        Resources resources = this.context.getResources();
        numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
        float f2 = f / 1000.0f;
        format = numberInstance.format(f2);
        Object[] objArr = {format};
        if (f2 == 1.0f) {
            i = 1;
        } else {
            i = 2;
        }
        String quantityString = resources.getQuantityString(R.plurals.bd_auto_scroll_duration, i, objArr);
        int i2 = this.duration;
        if (i2 == 500) {
            Context context = this.context;
            return context.getString(R.string.bd_auto_scroll_duration_prefix, context.getString(R.string.bd_auto_scroll_duration_minimum), quantityString);
        }
        if (i2 != 20000) {
            return quantityString;
        }
        Context context2 = this.context;
        return context2.getString(R.string.bd_auto_scroll_duration_prefix, context2.getString(R.string.bd_auto_scroll_duration_maximum), quantityString);
    }

    public final boolean isActive() {
        boolean hasCallbacks;
        hasCallbacks = this.handler.hasCallbacks(this.runnable);
        return hasCallbacks;
    }

    public final void stop() {
        AppCompatTextViewAutoSizeHelper.Api23Impl.i("AutoScrollManager", "Auto scroll stopped.");
        if (isActive()) {
            this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitFeedback(FeedbackManager$Type.AUTO_SCROLL_STOP);
        }
        this.handler.removeCallbacksAndMessages(null);
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging;
        ((BdController) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).cellsContentManager.onDisplayContentChangeListeners.remove(this.onDisplayContentChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        Context context = this.context;
        BrailleUserPreferences.getSharedPreferences$ar$ds(context).unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
    }
}
