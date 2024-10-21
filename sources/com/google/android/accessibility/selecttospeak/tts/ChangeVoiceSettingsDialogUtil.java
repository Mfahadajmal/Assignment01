package com.google.android.accessibility.selecttospeak.tts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.selecttospeak.feedback.SpeechRateController;
import com.google.android.accessibility.selecttospeak.tts.VoiceUtil;
import com.google.android.accessibility.talkback.trainingcommon.content.LinkCondition$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChangeVoiceSettingsDialogUtil {
    public static final ChangeVoiceSettingsDialogUtil INSTANCE = new ChangeVoiceSettingsDialogUtil();
    public static Job dialogJob;

    private ChangeVoiceSettingsDialogUtil() {
    }

    public static final AlertDialog createFailedToLoadDialog$ar$ds(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.s2s_failed_to_load_voices);
        builder.setCancelable(true);
        builder.setOnCancelListener(new ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda1(1));
        AlertDialog create = builder.create();
        create.getClass();
        return create;
    }

    public static final void createVoicesDialog$lambda$9$lambda$8$lambda$7$ar$ds(Ref$IntRef ref$IntRef, List list, SpeechController speechController, Bundle bundle) {
        int i = ref$IntRef.element;
        if (i >= 0 && i < list.size()) {
            VoiceUtil.VoiceWithName voiceWithName = (VoiceUtil.VoiceWithName) list.get(ref$IntRef.element);
            voiceWithName.getClass();
            VoiceUtil.setVoice$ar$ds(speechController, voiceWithName.voice);
            speechController.speak$ar$class_merging$8236f667_0$ar$ds(voiceWithName.name, bundle, null);
            return;
        }
        LogUtils.e("ChangeVoiceSettingsDialogUtil", "Selected index out of range. Error index : " + ref$IntRef.element, new Object[0]);
    }

    public final AlertDialog createVoicesDialog(Activity activity, final SpeechController speechController) {
        final List list;
        int i;
        List<Voice> locallyAvailableVoices$ar$ds = VoiceUtil.locallyAvailableVoices$ar$ds(speechController);
        Resources resources = activity.getResources();
        resources.getClass();
        int i2 = 0;
        if (locallyAvailableVoices$ar$ds.isEmpty()) {
            list = EmptyList.INSTANCE;
        } else {
            List arrayList = new ArrayList();
            String str = "";
            int i3 = 0;
            String str2 = "";
            for (Voice voice : locallyAvailableVoices$ar$ds) {
                if (Intrinsics.areEqual(str, voice.getLocale().getCountry()) && Intrinsics.areEqual(str2, voice.getLocale().getLanguage())) {
                    i3++;
                } else {
                    i3 = 1;
                }
                String string = resources.getString(R.string.s2s_voice_name_template, voice.getLocale().getDisplayLanguage(new Locale(voice.getLocale().getLanguage())), voice.getLocale().getDisplayCountry(new Locale(voice.getLocale().getLanguage())), Integer.valueOf(i3));
                string.getClass();
                arrayList.add(new VoiceUtil.VoiceWithName(voice, string));
                str = voice.getLocale().getCountry();
                str.getClass();
                str2 = voice.getLocale().getLanguage();
                str2.getClass();
            }
            list = arrayList;
        }
        Voice savedVoice = VoiceUtil.INSTANCE.getSavedVoice(speechController, activity);
        if (savedVoice == null) {
            TextToSpeech textToSpeech = speechController.getFailoverTts().mTts;
            if (textToSpeech != null) {
                savedVoice = textToSpeech.getDefaultVoice();
            } else {
                savedVoice = null;
            }
        }
        if (savedVoice == null) {
            return createFailedToLoadDialog$ar$ds(activity);
        }
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                if (Intrinsics.areEqual(savedVoice, ((VoiceUtil.VoiceWithName) listIterator.previous()).voice)) {
                    i = listIterator.nextIndex();
                    break;
                }
            } else {
                i = -1;
                break;
            }
        }
        ref$IntRef.element = i;
        if (i >= 0 && i < list.size()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            AlertDialog.Builder neutralButton = builder.setPositiveButton(R.string.s2s_choose_voice, new ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3(list, ref$IntRef, activity, i2)).setNegativeButton(R.string.s2s_discard_voice, (DialogInterface.OnClickListener) null).setNeutralButton(R.string.s2s_test_voice, (DialogInterface.OnClickListener) null);
            ArrayList arrayList2 = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(list));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(((VoiceUtil.VoiceWithName) it.next()).name);
            }
            neutralButton.setSingleChoiceItems((CharSequence[]) arrayList2.toArray(new String[0]), ref$IntRef.element, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(ref$IntRef, 18));
            final AlertDialog create = builder.create();
            final Bundle bundle = new Bundle();
            float[] fArr = SpeechRateController.SPEECH_RATE_LIST;
            float f = SpannableUtils$NonCopyableTextSpan.createSpeechRateController$ar$ds(activity).speechRate;
            bundle.putFloat("pitch", 1.2f);
            bundle.putFloat("rate", f);
            create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.google.android.accessibility.selecttospeak.tts.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda5
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    create.getButton(-3).setOnClickListener(new LinkCondition$$ExternalSyntheticLambda0(ref$IntRef, list, speechController, bundle, 1));
                }
            });
            create.getClass();
            return create;
        }
        LogUtils.e("ChangeVoiceSettingsDialogUtil", "Default index not in range. Error index : " + ref$IntRef.element, new Object[0]);
        return createFailedToLoadDialog$ar$ds(activity);
    }
}
