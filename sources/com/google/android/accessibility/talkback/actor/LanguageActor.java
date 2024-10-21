package com.google.android.accessibility.talkback.actor;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.monitor.ScreenMonitor;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.grpc.internal.RetryingNameResolver;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LanguageActor {
    public ActorState actorState;
    public final Context context;
    private Set installLanguages;
    public Pipeline.FeedbackReturner pipeline;
    public final RetryingNameResolver.ResolutionResultListener speechLanguage$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl state$ar$class_merging$2e40e015_0$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);

    public LanguageActor(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.context = context;
        this.speechLanguage$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    public static String getLocaleString(Context context, Locale locale) {
        if (locale == null) {
            return context.getString(R.string.reset_user_language_preference);
        }
        String displayCountry = locale.getDisplayCountry();
        if (TextUtils.isEmpty(displayCountry)) {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_22(locale.getDisplayLanguage());
        }
        return context.getString(R.string.template_language_options_menu_item, _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_22(locale.getDisplayLanguage()), displayCountry);
    }

    public final boolean allowSelectLanguage() {
        Set languages;
        if (!ScreenMonitor.isDeviceLocked(this.context) && (languages = this.actorState.getSpeechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().getLanguages()) != null) {
            languages.add(null);
            LogUtils.v("LanguageActor", "Installed languages: ".concat(languages.toString()), new Object[0]);
            if (languages.size() >= 3) {
                this.installLanguages = languages;
                return true;
            }
        }
        return false;
    }

    public final Set getInstalledLanguages() {
        if (allowSelectLanguage()) {
            return this.installLanguages;
        }
        return null;
    }

    public final void selectPreviousOrNextLanguage(boolean z) {
        int i;
        Set installedLanguages = getInstalledLanguages();
        if (installedLanguages == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(installedLanguages);
        int size = arrayList.size();
        int indexOf = arrayList.indexOf(this.speechLanguage$ar$class_merging$ar$class_merging.getCurrentLanguage());
        if (z) {
            i = indexOf + 1;
            if (i >= size) {
                i = 0;
            }
        } else {
            i = indexOf - 1;
            if (i < 0) {
                i = size - 1;
            }
        }
        setLanguage((Locale) arrayList.get(i));
    }

    public final void setLanguage(Locale locale) {
        Object obj = this.speechLanguage$ar$class_merging$ar$class_merging.RetryingNameResolver$ResolutionResultListener$ar$this$0;
        if (locale == null) {
            TalkBackService talkBackService = (TalkBackService) obj;
            talkBackService.prefs.edit().remove(talkBackService.getString(R.string.pref_talkback_prefer_locale_key)).apply();
        } else {
            TalkBackService talkBackService2 = (TalkBackService) obj;
            talkBackService2.prefs.edit().putString(talkBackService2.getString(R.string.pref_talkback_prefer_locale_key), locale.getDisplayName()).apply();
        }
        ((TalkBackService) obj).compositor$ar$class_merging$e4d5cfcc_0.setUserPreferredLanguage(locale);
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Logger logger = Performance.DEFAULT_LOGGER;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(getLocaleString(this.context, locale)));
    }
}
