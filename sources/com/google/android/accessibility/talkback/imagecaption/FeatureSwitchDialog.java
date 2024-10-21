package com.google.android.accessibility.talkback.imagecaption;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.accessibility_suite.features.ImageCaption;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FeatureSwitchDialog extends BaseDialog {
    private final boolean isDeletable;
    private final SharedPreferences prefs;
    private final ImageCaptionConstants$FeatureSwitchDialogResources resources;
    private RadioGroup switches;

    public FeatureSwitchDialog(Context context, ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources, boolean z) {
        this(context, imageCaptionConstants$FeatureSwitchDialogResources, z, -1);
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public View getCustomizedView() {
        LayoutInflater from = LayoutInflater.from(this.context);
        if (this.resources.descriptionType == 1) {
            View inflate = from.inflate(R.layout.detailed_image_description_dialog, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.ai_descriptiog_switch_dialog_message);
            if (textView != null) {
                Context context = this.context;
                Context context2 = this.context;
                ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources = this.resources;
                String string = context.getString(R.string.dialog_message_gen_ai_tos_link);
                String string2 = context2.getString(imageCaptionConstants$FeatureSwitchDialogResources.messageRes, string);
                SpannableString spannableString = new SpannableString(string2);
                int indexOf = string2.indexOf(string);
                if (indexOf >= 0) {
                    Context context3 = this.context;
                    GeminiFunctionUtils.DescribeImageCandidate describeImageCandidate = GeminiFunctionUtils.confirmDownloadOrPerformImageCaptioning;
                    spannableString.setSpan(new GeminiFunctionUtils.AnonymousClass8(context3, this), indexOf, string.length() + indexOf, 0);
                }
                textView.setText(spannableString);
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
            return inflate;
        }
        ScrollView scrollView = (ScrollView) from.inflate(R.layout.automatic_image_caption_switch_dialog, (ViewGroup) null);
        TextView textView2 = (TextView) scrollView.findViewById(R.id.automatic_image_caption_switch_dialog_subtitle);
        if (!this.isDeletable) {
            textView2.setVisibility(8);
        }
        ((TextView) scrollView.findViewById(R.id.automatic_image_caption_switch_dialog_message)).setText(this.resources.messageRes);
        this.switches = (RadioGroup) scrollView.findViewById(R.id.automatic_image_caption_switch_dialog_radiogroup);
        if (!ImageCaption.enableAutomaticCaptioningForAllImages(this.context)) {
            this.switches.findViewById(R.id.automatic_image_caption_switch_dialog_radiobutton_enabled).setVisibility(8);
        }
        RadioButton radioButton = (RadioButton) this.switches.findViewById(R.id.automatic_image_caption_switch_dialog_radiobutton_enabled);
        RadioButton radioButton2 = (RadioButton) this.switches.findViewById(R.id.automatic_image_caption_switch_dialog_radiobutton_enabled_unlabelled_only);
        RadioButton radioButton3 = (RadioButton) this.switches.findViewById(R.id.automatic_image_caption_switch_dialog_radiobutton_disabled);
        ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources2 = this.resources;
        if (imageCaptionConstants$FeatureSwitchDialogResources2 == ImageCaptionConstants$FeatureSwitchDialogResources.ICON_DETECTION) {
            radioButton.setText(R.string.title_pref_enable_auto_icon_detection);
            radioButton2.setText(R.string.title_pref_enable_auto_icon_detection_unlabelled_only);
            radioButton3.setText(R.string.title_pref_disable_auto_icon_detection);
        } else if (imageCaptionConstants$FeatureSwitchDialogResources2 == ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION_AICORE_SCOPE) {
            Context context4 = this.context;
            Context context5 = this.context;
            String string3 = context4.getString(R.string.subtitle_pref_enable_auto_image_caption_with_aicore);
            String string4 = context5.getString(R.string.title_pref_enable_auto_image_caption_arg, string3);
            SpannableString spannableString2 = new SpannableString(string4);
            int indexOf2 = string4.indexOf(string3);
            if (indexOf2 >= 0) {
                spannableString2.setSpan(new TextAppearanceSpan(this.context, R.style.A11yAlertDialogSubtitleStyle), indexOf2, string4.length(), 0);
            }
            radioButton.setText(spannableString2);
        }
        int ordinal = SpannableUtils$IdentifierSpan.getAutomaticImageCaptioningState(this.context, this.prefs, this.resources).ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    radioButton2.setChecked(true);
                }
            } else {
                radioButton.setChecked(true);
            }
        } else {
            radioButton3.setChecked(true);
        }
        return scrollView;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public String getMessageString() {
        return null;
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public void handleDialogClick(int i) {
        RadioGroup radioGroup = this.switches;
        if (radioGroup != null && i == -1) {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            boolean z = false;
            if (checkedRadioButtonId == R.id.automatic_image_caption_switch_dialog_radiobutton_disabled) {
                this.prefs.edit().putBoolean(this.context.getString(this.resources.switchKey), false).remove(this.context.getString(this.resources.switchOnUnlabelledOnlyKey)).apply();
                return;
            }
            SharedPreferences.Editor putBoolean = this.prefs.edit().putBoolean(this.context.getString(this.resources.switchKey), true);
            String string = this.context.getString(this.resources.switchOnUnlabelledOnlyKey);
            if (checkedRadioButtonId == R.id.automatic_image_caption_switch_dialog_radiobutton_enabled_unlabelled_only) {
                z = true;
            }
            putBoolean.putBoolean(string, z).apply();
        }
    }

    public FeatureSwitchDialog(Context context, ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources, boolean z, int i) {
        super(context, imageCaptionConstants$FeatureSwitchDialogResources.titleRes, null);
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.resources = imageCaptionConstants$FeatureSwitchDialogResources;
        this.isDeletable = z;
        setPositiveButtonStringRes(i == -1 ? R.string.switch_auto_image_caption_dialog_positive_button_text : i);
        if (z) {
            setNegativeButtonStringRes(R.string.switch_auto_image_caption_dialog_negative_button_text);
        }
    }

    @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
    public void handleDialogDismiss() {
    }
}
