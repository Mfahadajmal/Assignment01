package com.google.android.accessibility.talkback.preference.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceDialogFragmentCompat;
import com.google.android.accessibility.talkback.preference.base.GestureListPreference;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GesturePreferenceFragmentCompat extends PreferenceDialogFragmentCompat {
    private static final String ARG_ACTIONS = "actions";
    private static final String TAG = "GesturePreferenceFragmentCompat";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ActionAdapter extends BaseAdapter {
        final Parcelable[] actionItems;
        final Context context;
        final String initialValue;

        public ActionAdapter(Context context, Parcelable[] parcelableArr, String str) {
            this.context = context;
            this.actionItems = parcelableArr;
            this.initialValue = str;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.actionItems.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.actionItems[i];
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            Parcelable parcelable = this.actionItems[i];
            if (!(parcelable instanceof GestureListPreference.ActionItem)) {
                return view;
            }
            GestureListPreference.ActionItem actionItem = (GestureListPreference.ActionItem) parcelable;
            if (actionItem.viewType != 1) {
                View inflate = LayoutInflater.from(this.context).inflate(R.layout.list_item_category, viewGroup, false);
                TextView textView = (TextView) inflate;
                ViewCompat.setAccessibilityHeading(textView, true);
                textView.setText(actionItem.text);
                return inflate;
            }
            View inflate2 = LayoutInflater.from(this.context).inflate(R.layout.list_item_radio, viewGroup, false);
            CheckedTextView checkedTextView = (CheckedTextView) inflate2.findViewById(R.id.actionItem);
            checkedTextView.setText(actionItem.text);
            if (TextUtils.equals(actionItem.value, this.initialValue)) {
                checkedTextView.setChecked(true);
            } else {
                checkedTextView.setChecked(false);
            }
            return inflate2;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            Parcelable parcelable = this.actionItems[i];
            if (!(parcelable instanceof GestureListPreference.ActionItem) || ((GestureListPreference.ActionItem) parcelable).viewType != 1) {
                return false;
            }
            return true;
        }
    }

    public static GesturePreferenceFragmentCompat create(GestureListPreference gestureListPreference) {
        GesturePreferenceFragmentCompat gesturePreferenceFragmentCompat = new GesturePreferenceFragmentCompat();
        Bundle bundle = new Bundle(2);
        bundle.putString("key", gestureListPreference.getKey());
        bundle.putParcelableArray(ARG_ACTIONS, gestureListPreference.getActionItems());
        gesturePreferenceFragmentCompat.setArguments(bundle);
        return gesturePreferenceFragmentCompat;
    }

    private String getPreferenceValue() {
        if (getPreference() instanceof GestureListPreference) {
            return ((GestureListPreference) getPreference()).getCurrentValue();
        }
        LogUtils.e(TAG, "Unexpected usage, the preference fragment should work with a GestureListPreference.", new Object[0]);
        return null;
    }

    private void setValue(String str) {
        if (getPreference() instanceof GestureListPreference) {
            ((GestureListPreference) getPreference()).setValue(str);
        } else {
            LogUtils.e(TAG, "Unexpected usage, the preference fragment should work with a GestureListPreference.", new Object[0]);
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, android.support.v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        final Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.google.android.accessibility.talkback.preference.base.GesturePreferenceFragmentCompat$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                ((AlertDialog) onCreateDialog).getButton(-1).setVisibility(8);
            }
        });
        return onCreateDialog;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected View onCreateDialogView(Context context) {
        final ListView listView = new ListView(getActivity());
        Parcelable[] parcelableArray = getArguments().getParcelableArray(ARG_ACTIONS);
        listView.setAdapter((ListAdapter) new ActionAdapter(getActivity(), parcelableArray, getPreferenceValue()));
        listView.setBackground(null);
        listView.setDivider(null);
        listView.setDividerHeight(context.getResources().getDimensionPixelSize(R.dimen.alertdialog_menuitem_divider_height));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.GesturePreferenceFragmentCompat$$ExternalSyntheticLambda1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                GesturePreferenceFragmentCompat.this.selectActionInList(adapterView, view, i, j);
            }
        });
        final int i = 0;
        listView.setPaddingRelative(context.getResources().getDimensionPixelSize(R.dimen.alertdialog_padding_start), 0, context.getResources().getDimensionPixelSize(R.dimen.alertdialog_padding_end), 0);
        String preferenceValue = getPreferenceValue();
        while (true) {
            if (i >= parcelableArray.length) {
                break;
            }
            Parcelable parcelable = parcelableArray[i];
            if ((parcelable instanceof GestureListPreference.ActionItem) && TextUtils.equals(((GestureListPreference.ActionItem) parcelable).value, preferenceValue)) {
                listView.post(new Runnable() { // from class: com.google.android.accessibility.talkback.preference.base.GesturePreferenceFragmentCompat$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        listView.setSelection(i);
                    }
                });
                break;
            }
            i++;
        }
        return listView;
    }

    public void selectActionInList(AdapterView<?> adapterView, View view, int i, long j) {
        CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.actionItem);
        if (checkedTextView.isChecked()) {
            dismiss();
            return;
        }
        checkedTextView.setChecked(true);
        GestureListPreference.ActionItem actionItem = (GestureListPreference.ActionItem) adapterView.getItemAtPosition(i);
        setValue(actionItem.value);
        getPreference().setSummary(actionItem.text);
        dismiss();
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public void onDialogClosed(boolean z) {
    }
}
