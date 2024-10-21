package com.google.android.accessibility.talkback.preference.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import androidx.preference.ListPreference;
import androidx.preference.ListPreferenceDialogFragmentCompat;
import com.google.android.accessibility.talkback.preference.base.PunctuationListPreference;
import com.google.android.marvin.talkback.R;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PunctuationListPreference extends ListPreference {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CustomListPreferenceDialogFragment extends ListPreferenceDialogFragmentCompat {
        private static String keyClickedEntryIndex;
        private int clickedDialogEntryIndex;

        private PunctuationListPreference getCustomizablePreference() {
            return (PunctuationListPreference) getPreference();
        }

        private DialogInterface.OnClickListener getOnItemClickListener() {
            return new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.PunctuationListPreference$CustomListPreferenceDialogFragment$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    PunctuationListPreference.CustomListPreferenceDialogFragment.this.m157xb4858a59(dialogInterface, i);
                }
            };
        }

        private String getValue() {
            PunctuationListPreference customizablePreference = getCustomizablePreference();
            if (this.clickedDialogEntryIndex >= 0 && customizablePreference.getEntryValues() != null) {
                return customizablePreference.getEntryValues()[this.clickedDialogEntryIndex].toString();
            }
            return null;
        }

        public static ListPreferenceDialogFragmentCompat newInstance(String str) {
            keyClickedEntryIndex = str;
            CustomListPreferenceDialogFragment customListPreferenceDialogFragment = new CustomListPreferenceDialogFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", str);
            customListPreferenceDialogFragment.setArguments(bundle);
            return customListPreferenceDialogFragment;
        }

        private void setClickedDialogEntryIndex(int i) {
            this.clickedDialogEntryIndex = i;
        }

        /* renamed from: lambda$getOnItemClickListener$0$com-google-android-accessibility-talkback-preference-base-PunctuationListPreference$CustomListPreferenceDialogFragment, reason: not valid java name */
        public /* synthetic */ void m157xb4858a59(DialogInterface dialogInterface, int i) {
            setClickedDialogEntryIndex(i);
            getCustomizablePreference().setValue(String.valueOf(getCustomizablePreference().getEntryValues()[this.clickedDialogEntryIndex]));
            dismiss();
        }

        @Override // androidx.preference.PreferenceDialogFragmentCompat, android.support.v4.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            Dialog onCreateDialog = super.onCreateDialog(bundle);
            if (bundle != null) {
                this.clickedDialogEntryIndex = bundle.getInt(keyClickedEntryIndex, this.clickedDialogEntryIndex);
            }
            return onCreateDialog;
        }

        @Override // androidx.preference.ListPreferenceDialogFragmentCompat, androidx.preference.PreferenceDialogFragmentCompat
        public void onDialogClosed(boolean z) {
            PunctuationListPreference customizablePreference = getCustomizablePreference();
            String value = getValue();
            if (z && value != null && customizablePreference.callChangeListener(value)) {
                customizablePreference.setValue(value);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.preference.ListPreferenceDialogFragmentCompat, androidx.preference.PreferenceDialogFragmentCompat
        public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
            super.onPrepareDialogBuilder(builder);
            this.clickedDialogEntryIndex = getCustomizablePreference().findIndexOfValue(getCustomizablePreference().getValue());
            getCustomizablePreference().onPrepareDialogBuilder(builder, getOnItemClickListener());
        }

        @Override // androidx.preference.ListPreferenceDialogFragmentCompat, androidx.preference.PreferenceDialogFragmentCompat, android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            bundle.putInt(keyClickedEntryIndex, this.clickedDialogEntryIndex);
        }
    }

    public PunctuationListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: lambda$onPrepareDialogBuilder$0$com-google-android-accessibility-talkback-preference-base-PunctuationListPreference, reason: not valid java name */
    public /* synthetic */ void m156xe8b20c04(DialogInterface dialogInterface, int i) {
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://support.google.com/accessibility/android/answer/6283655?hl=".concat(String.valueOf(Locale.getDefault().toLanguageTag())))));
    }

    protected void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        builder.setSingleChoiceItems$ar$ds(getEntries(), findIndexOfValue(getValue()), onClickListener);
        builder.setNegativeButton(R.string.learn_more, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.PunctuationListPreference$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PunctuationListPreference.this.m156xe8b20c04(dialogInterface, i);
            }
        });
    }
}
