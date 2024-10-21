package com.google.android.accessibility.selecttospeak.tts;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import androidx.room.util.SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VoiceUtil {
    public static final VoiceUtil INSTANCE = new VoiceUtil();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VoiceWithName {
        public final String name;
        public final Voice voice;

        public VoiceWithName(Voice voice, String str) {
            voice.getClass();
            this.voice = voice;
            this.name = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VoiceWithName)) {
                return false;
            }
            VoiceWithName voiceWithName = (VoiceWithName) obj;
            if (Intrinsics.areEqual(this.voice, voiceWithName.voice) && Intrinsics.areEqual(this.name, voiceWithName.name)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return (this.voice.hashCode() * 31) + this.name.hashCode();
        }

        public final String toString() {
            return "VoiceWithName(voice=" + this.voice + ", name=" + this.name + ")";
        }
    }

    private VoiceUtil() {
    }

    public static final void loadUserSavedVoice(SpeechController speechController, Context context) {
        speechController.getClass();
        context.getClass();
        Voice savedVoice = INSTANCE.getSavedVoice(speechController, context);
        if (savedVoice != null) {
            setVoice$ar$ds(speechController, savedVoice);
        }
    }

    public static final List locallyAvailableVoices$ar$ds(SpeechController speechController) {
        Iterable iterable;
        TextToSpeech textToSpeech = speechController.getFailoverTts().mTts;
        if (textToSpeech != null) {
            iterable = textToSpeech.getVoices();
        } else {
            iterable = null;
        }
        if (iterable == null) {
            iterable = EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterable) {
            Voice voice = (Voice) obj;
            if (!voice.getFeatures().contains("notInstalled") && !voice.isNetworkConnectionRequired()) {
                arrayList.add(obj);
            }
        }
        return OnDeviceLanguageIdentificationLogEvent.toList(OnDeviceLanguageIdentificationLogEvent.sortedWith(OnDeviceLanguageIdentificationLogEvent.sortedWith(arrayList, new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(6)), new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(7)));
    }

    public static final void setVoice$ar$ds(SpeechController speechController, Voice voice) {
        TextToSpeech textToSpeech = speechController.getFailoverTts().mTts;
        if (textToSpeech != null) {
            textToSpeech.setVoice(voice);
        }
    }

    public final Voice getSavedVoice(SpeechController speechController, Context context) {
        String string = SpannableUtils$IdentifierSpan.getSharedPreferences(context).getString(context.getString(R.string.s2s_pref_user_selected_voice_key), null);
        if (string != null && string.length() != 0) {
            for (Voice voice : locallyAvailableVoices$ar$ds(speechController)) {
                if (Intrinsics.areEqual(voice.toString(), string)) {
                    return voice;
                }
            }
        }
        return null;
    }
}
