package com.google.android.accessibility.brailleime.dialog;

import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.ListPopupWindow;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13;
import com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda5;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.android.accessibility.brailleime.analytics.BrailleImeAnalytics;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$ContextMenuOption;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.social.licenses.License;
import com.google.android.libraries.social.licenses.LicenseMenuActivity;
import com.google.android.libraries.social.licenses.LicenseMenuFragment;
import com.google.android.marvin.talkback.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import io.grpc.CallCredentials$RequestInfo;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueue;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextMenuDialog extends ViewAttachedDialog {
    public static final ImmutableList ITEM_STRING_IDS = ImmutableList.of((Object) Integer.valueOf(R.string.context_menu_input_language_selection), (Object) Integer.valueOf(R.string.context_menu_switch_contracted_status_selection), (Object) Integer.valueOf(R.string.context_menu_layout_calibration), (Object) Integer.valueOf(R.string.context_menu_review_gestures_selection), (Object) Integer.valueOf(R.string.context_menu_tutorial_selection), (Object) Integer.valueOf(R.string.context_menu_tutorial_finish), (Object) Integer.valueOf(R.string.context_menu_settings_selection));
    public final BasicActionsDialog basicActionsDialog;
    public final Callback callback;
    public final Context context;
    public AlertDialog contextMenuDialog;
    public ContextMenuListAdapter contextMenuListAdapter;
    private final AdapterView.OnItemClickListener itemClickListener = new AnonymousClass1(this, 0);
    public final List itemsAndActions = new ArrayList();
    public boolean tutorialMode;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ Object ContextMenuDialog$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.ContextMenuDialog$1$ar$this$0 = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object, java.lang.Runnable] */
        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
            BrailleImeLogProto$ContextMenuOption brailleImeLogProto$ContextMenuOption;
            Object item;
            CharSequence convertSelectionToString;
            long selectedItemId;
            int i2;
            View view2;
            int i3 = this.switching_field;
            int i4 = 3;
            int i5 = 1;
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 != 3) {
                            View view3 = null;
                            if (i < 0) {
                                ListPopupWindow listPopupWindow = ((MaterialAutoCompleteTextView) this.ContextMenuDialog$1$ar$this$0).modalListPopup;
                                if (!listPopupWindow.isShowing()) {
                                    item = null;
                                } else {
                                    item = listPopupWindow.mDropDownList.getSelectedItem();
                                }
                            } else {
                                item = ((MaterialAutoCompleteTextView) this.ContextMenuDialog$1$ar$this$0).getAdapter().getItem(i);
                            }
                            MaterialAutoCompleteTextView materialAutoCompleteTextView = (MaterialAutoCompleteTextView) this.ContextMenuDialog$1$ar$this$0;
                            convertSelectionToString = materialAutoCompleteTextView.convertSelectionToString(item);
                            materialAutoCompleteTextView.setText(convertSelectionToString, false);
                            AdapterView.OnItemClickListener onItemClickListener = ((MaterialAutoCompleteTextView) this.ContextMenuDialog$1$ar$this$0).getOnItemClickListener();
                            if (onItemClickListener != null) {
                                if (view != null && i >= 0) {
                                    view2 = view;
                                    i2 = i;
                                } else {
                                    ListPopupWindow listPopupWindow2 = ((MaterialAutoCompleteTextView) this.ContextMenuDialog$1$ar$this$0).modalListPopup;
                                    if (listPopupWindow2.isShowing()) {
                                        view3 = listPopupWindow2.mDropDownList.getSelectedView();
                                    }
                                    View view4 = view3;
                                    Object obj = this.ContextMenuDialog$1$ar$this$0;
                                    ListPopupWindow listPopupWindow3 = ((MaterialAutoCompleteTextView) obj).modalListPopup;
                                    int selectedItemPosition = ((MaterialAutoCompleteTextView) obj).modalListPopup.getSelectedItemPosition();
                                    if (!listPopupWindow3.isShowing()) {
                                        selectedItemId = Long.MIN_VALUE;
                                    } else {
                                        selectedItemId = listPopupWindow3.mDropDownList.getSelectedItemId();
                                    }
                                    j = selectedItemId;
                                    i2 = selectedItemPosition;
                                    view2 = view4;
                                }
                                onItemClickListener.onItemClick(((MaterialAutoCompleteTextView) this.ContextMenuDialog$1$ar$this$0).modalListPopup.mDropDownList, view2, i2, j);
                            }
                            ((MaterialAutoCompleteTextView) this.ContextMenuDialog$1$ar$this$0).modalListPopup.dismiss();
                            return;
                        }
                        License license = (License) adapterView.getItemAtPosition(i);
                        LicenseMenuActivity licenseMenuActivity = ((LicenseMenuFragment) this.ContextMenuDialog$1$ar$this$0).licenseSelectionListener$ar$class_merging;
                        if (licenseMenuActivity != null) {
                            licenseMenuActivity.onLicenseSelected(license);
                            return;
                        }
                        return;
                    }
                    ((CallCredentials$RequestInfo) this.ContextMenuDialog$1$ar$this$0).onItemClick(i);
                    return;
                }
                AppCompatSpinner.this.setSelection(i);
                if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                    AppCompatSpinner.DropdownPopup dropdownPopup = (AppCompatSpinner.DropdownPopup) this.ContextMenuDialog$1$ar$this$0;
                    AppCompatSpinner.this.performItemClick(view, i, dropdownPopup.mAdapter.getItemId(i));
                }
                ((ListPopupWindow) this.ContextMenuDialog$1$ar$this$0).dismiss();
                return;
            }
            ((CronetEngineBuilderImpl.Pkp) ((ContextMenuDialog) this.ContextMenuDialog$1$ar$this$0).itemsAndActions.get(i)).CronetEngineBuilderImpl$Pkp$ar$mHashes.run();
            BrailleImeAnalytics brailleImeAnalytics = BrailleImeAnalytics.getInstance(((ContextMenuDialog) this.ContextMenuDialog$1$ar$this$0).context);
            int intValue = ((Integer) ContextMenuDialog.ITEM_STRING_IDS.get(i)).intValue();
            if (intValue == R.string.context_menu_input_language_selection) {
                i4 = 8;
            } else if (intValue == R.string.context_menu_switch_contracted_status_selection) {
                i4 = 2;
            } else if (intValue != R.string.context_menu_review_gestures_selection) {
                if (intValue == R.string.context_menu_tutorial_selection) {
                    i4 = 5;
                } else if (intValue == R.string.context_menu_tutorial_finish) {
                    i4 = 6;
                } else if (intValue == R.string.context_menu_settings_selection) {
                    i4 = 7;
                } else if (intValue == R.string.context_menu_layout_calibration) {
                    i4 = 4;
                } else {
                    i4 = 1;
                }
            }
            WorkQueue workQueue = brailleImeAnalytics.helper$ar$class_merging$ar$class_merging$ar$class_merging;
            switch (i4 - 1) {
                case 1:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.CONTRACTED_ON_OFF;
                    break;
                case 2:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.SEE_ALL_ACTIONS;
                    break;
                case 3:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.CALIBRATION;
                    break;
                case 4:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.TUTORIAL_OPEN;
                    break;
                case 5:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.TUTORIAL_FINISH;
                    break;
                case 6:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.GO_TO_SETTINGS;
                    break;
                case 7:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.TYPING_LANGUAGE;
                    break;
                default:
                    brailleImeLogProto$ContextMenuOption = BrailleImeLogProto$ContextMenuOption.UNSPECIFIED_OPTION;
                    break;
            }
            if (((HashMap) ((ApplicationModule) workQueue.WorkQueue$ar$buffer).ApplicationModule$ar$application).containsKey(brailleImeLogProto$ContextMenuOption)) {
                i5 = 1 + ((Integer) ((HashMap) ((ApplicationModule) workQueue.WorkQueue$ar$buffer).ApplicationModule$ar$application).get(brailleImeLogProto$ContextMenuOption)).intValue();
            }
            ((HashMap) ((ApplicationModule) workQueue.WorkQueue$ar$buffer).ApplicationModule$ar$application).put(brailleImeLogProto$ContextMenuOption, Integer.valueOf(i5));
            if (((CronetEngineBuilderImpl.Pkp) ((ContextMenuDialog) this.ContextMenuDialog$1$ar$this$0).itemsAndActions.get(i)).mIncludeSubdomains) {
                ((ContextMenuDialog) this.ContextMenuDialog$1$ar$this$0).contextMenuDialog.dismiss();
            } else {
                ((ContextMenuDialog) this.ContextMenuDialog$1$ar$this$0).updateItemsAndActionsList();
                ((ContextMenuDialog) this.ContextMenuDialog$1$ar$this$0).contextMenuListAdapter.notifyDataSetChanged();
            }
        }

        public /* synthetic */ AnonymousClass1(Object obj, int i, byte[] bArr) {
            this.switching_field = i;
            this.ContextMenuDialog$1$ar$this$0 = obj;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        void onCalibration();

        void onDialogHidden();

        void onDialogShown();

        void onLaunchSettings();

        void onTutorialClosed();

        void onTutorialOpen();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ContextMenuListAdapter extends ArrayAdapter {
        private final Context context;
        private final List menuItemList;

        public ContextMenuListAdapter(Context context, List list) {
            super(context, android.R.layout.simple_list_item_1, list);
            this.context = context;
            this.menuItemList = list;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            TextView textView = (TextView) view2.findViewById(android.R.id.text1);
            textView.setTextSize(0, this.context.getResources().getDimensionPixelSize(R.dimen.context_menu_item_text_size));
            int dimensionPixelOffset = this.context.getResources().getDimensionPixelOffset(R.dimen.context_menu_item_padding_horizontal);
            textView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
            textView.setText(((CharSequence[]) Collection.EL.stream(this.menuItemList).map(new BraillePreferenceUtils$$ExternalSyntheticLambda5(this.context, 3)).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(5)))[i]);
            return view2;
        }
    }

    public ContextMenuDialog(Context context, Callback callback) {
        this.context = context;
        this.callback = callback;
        this.basicActionsDialog = new BasicActionsDialog(context, new RetryingNameResolver.ResolutionResultListener(this, null), null);
    }

    private final Runnable generateItemAction(int i) {
        if (i == R.string.context_menu_input_language_selection) {
            return new WorkerKt$$ExternalSyntheticLambda0(this, 18);
        }
        if (i == R.string.context_menu_switch_contracted_status_selection) {
            return new WorkerKt$$ExternalSyntheticLambda0(this, 19);
        }
        if (i == R.string.context_menu_review_gestures_selection) {
            return new WorkerKt$$ExternalSyntheticLambda0(this, 20);
        }
        if (i == R.string.context_menu_tutorial_selection) {
            Callback callback = this.callback;
            callback.getClass();
            return new ContextMenuDialog$$ExternalSyntheticLambda5(callback, 1);
        }
        if (i == R.string.context_menu_tutorial_finish) {
            Callback callback2 = this.callback;
            callback2.getClass();
            return new ContextMenuDialog$$ExternalSyntheticLambda5(callback2, 0);
        }
        if (i == R.string.context_menu_settings_selection) {
            Callback callback3 = this.callback;
            callback3.getClass();
            return new ContextMenuDialog$$ExternalSyntheticLambda5(callback3, 2);
        }
        if (i == R.string.context_menu_layout_calibration) {
            Callback callback4 = this.callback;
            callback4.getClass();
            return new ContextMenuDialog$$ExternalSyntheticLambda5(callback4, 3);
        }
        return new BrailleIme$21$$ExternalSyntheticLambda1(2);
    }

    @Override // com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog
    public final void dismiss() {
        AlertDialog alertDialog = this.contextMenuDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.basicActionsDialog.dismiss();
    }

    @Override // com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog
    protected final Dialog makeDialog() {
        updateItemsAndActionsList();
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, R.style.DialogTheme);
        AlertDialog.Builder alertDialogBuilder = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(contextThemeWrapper);
        this.contextMenuListAdapter = new ContextMenuListAdapter(contextThemeWrapper, this.itemsAndActions);
        alertDialogBuilder.setTitle(this.context.getString(R.string.context_menu_title)).setAdapter(this.contextMenuListAdapter, null).setNegativeButton(android.R.string.cancel, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 9)).setOnCancelListener(new TalkBackOffDialog$$ExternalSyntheticLambda1(this, 1));
        AlertDialog create = alertDialogBuilder.create();
        this.contextMenuDialog = create;
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda11
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                ContextMenuDialog.this.callback.onDialogShown();
            }
        });
        this.contextMenuDialog.setCanceledOnTouchOutside(false);
        this.contextMenuDialog.getListView().setOnItemClickListener(this.itemClickListener);
        return this.contextMenuDialog;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateItemsAndActionsList() {
        int i;
        this.itemsAndActions.clear();
        ImmutableList immutableList = ITEM_STRING_IDS;
        int i2 = ((RegularImmutableList) immutableList).size;
        for (int i3 = 0; i3 < i2; i3++) {
            int intValue = ((Integer) immutableList.get(i3)).intValue();
            if (intValue == R.string.context_menu_switch_contracted_status_selection) {
                Context context = this.context;
                if (BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(context).isSupportsContracted(context)) {
                    if (true != BrailleUserPreferences.readContractedMode(this.context)) {
                        i = R.string.contracted;
                    } else {
                        i = R.string.uncontracted;
                    }
                    List list = this.itemsAndActions;
                    Context context2 = this.context;
                    list.add(new CronetEngineBuilderImpl.Pkp(context2.getString(R.string.context_menu_switch_contracted_status_selection, context2.getString(i)), false, generateItemAction(R.string.context_menu_switch_contracted_status_selection)));
                }
            } else if (intValue == R.string.context_menu_tutorial_selection) {
                if (!this.tutorialMode) {
                    this.itemsAndActions.add(new CronetEngineBuilderImpl.Pkp(this.context.getString(R.string.context_menu_tutorial_selection), true, generateItemAction(R.string.context_menu_tutorial_selection)));
                }
            } else if (intValue == R.string.context_menu_tutorial_finish) {
                if (this.tutorialMode) {
                    this.itemsAndActions.add(new CronetEngineBuilderImpl.Pkp(this.context.getString(R.string.context_menu_tutorial_finish), true, generateItemAction(R.string.context_menu_tutorial_finish)));
                }
            } else if (intValue == R.string.context_menu_input_language_selection) {
                if (BrailleUserPreferences.readAvailablePreferredCodes(this.context).size() > 1) {
                    Context context3 = this.context;
                    String userFacingName = BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(context3).getUserFacingName(context3);
                    Context context4 = this.context;
                    this.itemsAndActions.add(new CronetEngineBuilderImpl.Pkp((CharSequence) this.context.getString(R.string.context_menu_input_language_selection, BrailleUserPreferences.getNextInputCode(context4).getUserFacingName(context4)), (CharSequence) this.context.getString(R.string.context_menu_input_language_selection_summary, userFacingName), false, generateItemAction(R.string.context_menu_input_language_selection)));
                }
            } else if (intValue == R.string.context_menu_settings_selection) {
                if (!((KeyguardManager) this.context.getSystemService("keyguard")).isKeyguardLocked()) {
                    this.itemsAndActions.add(new CronetEngineBuilderImpl.Pkp(this.context.getString(R.string.context_menu_settings_selection), true, generateItemAction(R.string.context_menu_settings_selection)));
                }
            } else if (intValue == R.string.context_menu_layout_calibration) {
                if (!this.tutorialMode) {
                    this.itemsAndActions.add(new CronetEngineBuilderImpl.Pkp(this.context.getString(R.string.context_menu_layout_calibration), true, generateItemAction(R.string.context_menu_layout_calibration)));
                }
            } else {
                this.itemsAndActions.add(new CronetEngineBuilderImpl.Pkp(this.context.getString(intValue), false, generateItemAction(intValue)));
            }
        }
    }
}
