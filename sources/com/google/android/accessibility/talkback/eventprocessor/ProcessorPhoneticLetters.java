package com.google.android.accessibility.talkback.eventprocessor;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessorPhoneticLetters implements AccessibilityEventListener {
    public final GlobalVariables globalVariables;
    private final Map phoneticLetters = new HashMap();
    public Pipeline.FeedbackReturner pipeline;
    private final SharedPreferences prefs;
    private final TalkBackService service;

    public ProcessorPhoneticLetters(TalkBackService talkBackService, GlobalVariables globalVariables) {
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(talkBackService);
        this.service = talkBackService;
        this.globalVariables = globalVariables;
    }

    private final Map getPhoneticLetterMap(String str) {
        Map map = (Map) this.phoneticLetters.get(str);
        if (map == null) {
            map = new HashMap();
            this.phoneticLetters.put(str, map);
            InputStream openRawResource = this.service.getResources().openRawResource(R.raw.phonetic_letters);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource, StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                openRawResource.close();
                bufferedReader.close();
                JSONObject jSONObject = new JSONObject(sb.toString()).getJSONObject(str);
                if (jSONObject != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        map.put(next, jSONObject.getString(next));
                    }
                }
            } catch (IOException e) {
                LogUtils.e("ProcPhoneticLetters", e.toString(), new Object[0]);
            } catch (JSONException e2) {
                LogUtils.e("ProcPhoneticLetters", e2.toString(), new Object[0]);
            }
        }
        return map;
    }

    public final boolean arePhoneticLettersEnabled() {
        Resources resources = this.service.getResources();
        return SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_phonetic_letters_key), resources.getBoolean(R.bool.pref_phonetic_letters_default));
    }

    public final void cancelPhoneticLetter(Performance.EventId eventId) {
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.interrupt(0, 1));
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return -6310083;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0073 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:5:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.CharSequence getPhoneticLetter(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            r2 = 0
            if (r0 == 0) goto La
        L8:
            r3 = r1
            goto L29
        La:
            java.lang.String r0 = "-"
            r3 = 3
            java.lang.String[] r0 = r7.split(r0, r3)
            int r3 = r0.length
            r4 = 2
            r5 = 1
            if (r3 < r4) goto L20
            java.util.Locale r3 = new java.util.Locale
            r4 = r0[r2]
            r0 = r0[r5]
            r3.<init>(r4, r0)
            goto L29
        L20:
            if (r3 != r5) goto L8
            java.util.Locale r3 = new java.util.Locale
            r0 = r0[r2]
            r3.<init>(r0)
        L29:
            if (r3 != 0) goto L2f
            java.util.Locale r3 = java.util.Locale.getDefault()
        L2f:
            java.lang.String r8 = r8.toLowerCase(r3)
            java.util.Map r7 = r6.getPhoneticLetterMap(r7)
            java.lang.Object r7 = r7.get(r8)
            java.lang.String r7 = (java.lang.String) r7
            if (r7 != 0) goto L5f
            java.lang.String r7 = r3.getCountry()
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L56
            java.lang.String r7 = "en-US"
            java.util.Map r7 = r6.getPhoneticLetterMap(r7)
            java.lang.Object r7 = r7.get(r8)
            java.lang.String r7 = (java.lang.String) r7
            goto L5f
        L56:
            java.lang.String r7 = r3.getLanguage()
            java.lang.CharSequence r7 = r6.getPhoneticLetter(r7, r8)
            return r7
        L5f:
            if (r7 == 0) goto L73
            android.text.SpannableString r8 = new android.text.SpannableString
            r8.<init>(r7)
            android.text.style.LocaleSpan r7 = new android.text.style.LocaleSpan
            r7.<init>(r3)
            int r0 = r8.length()
            r8.setSpan(r7, r2, r0, r2)
            return r8
        L73:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters.getPhoneticLetter(java.lang.String, java.lang.String):java.lang.CharSequence");
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        CharSequence eventTextOrDescription;
        if ((accessibilityEvent.getEventType() & (-6310083)) != 0) {
            cancelPhoneticLetter(eventId);
        }
        if (arePhoneticLettersEnabled() && accessibilityEvent.getEventType() == 131072 && accessibilityEvent.getMovementGranularity() == 1) {
            if (SpannableUtils$IdentifierSpan.getSourceRole(accessibilityEvent) == 4) {
                eventTextOrDescription = SpannableUtils$IdentifierSpan.getEventAggregateText(accessibilityEvent);
            } else {
                eventTextOrDescription = SpannableUtils$IdentifierSpan.getEventTextOrDescription(accessibilityEvent);
            }
            if (!TextUtils.isEmpty(eventTextOrDescription)) {
                if ((accessibilityEvent.getAction() == 256 || accessibilityEvent.getAction() == 512) && accessibilityEvent.getFromIndex() >= 0 && accessibilityEvent.getFromIndex() < eventTextOrDescription.length()) {
                    speakPhoneticLetterForTraversedText(TextUtils.equals(accessibilityEvent.getPackageName(), "com.google.android.marvin.talkback"), String.valueOf(eventTextOrDescription.charAt(accessibilityEvent.getFromIndex())), eventId);
                }
            }
        }
    }

    public final void speakPhoneticLetterForTraversedText(boolean z, String str, Performance.EventId eventId) {
        String languageTag;
        if (!z && this.service.getUserPreferredLocale() != null) {
            languageTag = this.service.getUserPreferredLocale().toLanguageTag();
        } else {
            languageTag = Locale.getDefault().toLanguageTag();
        }
        CharSequence phoneticLetter = getPhoneticLetter(languageTag, str);
        if (phoneticLetter != null) {
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            speakOptions.mQueueMode = 0;
            speakOptions.mFlags = 30;
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Feedback.Part.Builder speech = Feedback.speech(phoneticLetter, speakOptions);
            speech.setDelayMs$ar$ds(250);
            speech.setInterruptGroup$ar$ds(0);
            speech.setInterruptLevel$ar$ds(1);
            speech.senderName = "ProcPhoneticLetters";
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, speech);
        }
    }
}
