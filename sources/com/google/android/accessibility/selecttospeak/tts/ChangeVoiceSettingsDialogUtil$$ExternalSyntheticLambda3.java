package com.google.android.accessibility.selecttospeak.tts;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.CheckBox;
import com.google.android.accessibility.selecttospeak.tts.VoiceUtil;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.List;
import java.util.function.BiConsumer;
import kotlin.jvm.internal.Ref$IntRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Object ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$0;
    public final /* synthetic */ Object ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$1;
    public final /* synthetic */ Context ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3(List list, Ref$IntRef ref$IntRef, Activity activity, int i) {
        this.switching_field = i;
        this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$0 = list;
        this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$1 = ref$IntRef;
        this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$2 = activity;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.function.BiConsumer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List, java.lang.Object] */
    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.switching_field != 0) {
            this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$1.accept(this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$2, Boolean.valueOf(!((CheckBox) this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$0).isChecked()));
            return;
        }
        VoiceUtil.VoiceWithName voiceWithName = (VoiceUtil.VoiceWithName) OnDeviceLanguageIdentificationLogEvent.getOrNull(this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$0, ((Ref$IntRef) this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$1).element);
        if (voiceWithName != null) {
            Context context = this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$2;
            SpannableUtils$IdentifierSpan.getSharedPreferences(context).edit().putString(context.getString(R.string.s2s_pref_user_selected_voice_key), voiceWithName.voice.toString()).commit();
        }
    }

    public /* synthetic */ ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3(BiConsumer biConsumer, Context context, CheckBox checkBox, int i) {
        this.switching_field = i;
        this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$1 = biConsumer;
        this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$2 = context;
        this.ChangeVoiceSettingsDialogUtil$$ExternalSyntheticLambda3$ar$f$0 = checkBox;
    }
}
