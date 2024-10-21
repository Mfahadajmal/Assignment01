package com.google.android.accessibility.braille.brailledisplay;

import android.app.Activity;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.inputmethod.InputMethodManager;
import com.google.android.accessibility.braille.brailledisplay.controller.DefaultConsumer;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleDisplayImeUnavailableActivity extends Activity {
    public static FloatingActionButton.ShadowDelegateImpl dialogBuilderProvider$ar$class_merging$ar$class_merging$ar$class_merging;
    private AlertDialog alertDialog;
    private final ContentObserver contentObserver;
    public final Handler handler;
    public boolean ignoreNextFocusChanged;
    public InputMethodManager inputMethodManager;

    public BrailleDisplayImeUnavailableActivity() {
        Handler handler = new Handler();
        this.handler = handler;
        this.ignoreNextFocusChanged = false;
        this.contentObserver = new ContentObserver(handler) { // from class: com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                if (AppCompatDelegate.Api33Impl.isInputMethodDefault(BrailleDisplayImeUnavailableActivity.this, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD)) {
                    BrailleDisplayImeUnavailableActivity.this.finish();
                }
            }
        };
    }

    private final void checkStatus() {
        int i = 0;
        if (!AppCompatDelegate.Api33Impl.isInputMethodEnabled(this, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD)) {
            dismissDialog();
            AlertDialog create = getAlertDialogBuilder().setTitle(R.string.bd_ime_disabled_dialog_title).setMessage(R.string.bd_ime_disabled_dialog_message).setNegativeButton(android.R.string.cancel, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, i)).setPositiveButton(R.string.bd_ime_disabled_dialog_title_positive, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 2)).create();
            this.alertDialog = create;
            create.setCancelable(false);
            this.alertDialog.getWindow().setType(2032);
            this.alertDialog.show();
            return;
        }
        if (!AppCompatDelegate.Api33Impl.isInputMethodDefault(this, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD)) {
            dismissDialog();
            AlertDialog create2 = getAlertDialogBuilder().setTitle(R.string.bd_ime_not_default_dialog_title).setMessage(R.string.bd_ime_not_default_dialog_message).setNegativeButton(android.R.string.cancel, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 3)).setPositiveButton(R.string.bd_ime_not_default_dialog_positive, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 4)).create();
            this.alertDialog = create2;
            create2.setCancelable(false);
            this.alertDialog.getWindow().setType(2032);
            this.alertDialog.show();
            return;
        }
        finish();
    }

    private final void dismissDialog() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.alertDialog.dismiss();
        }
    }

    private final AlertDialog.Builder getAlertDialogBuilder() {
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = dialogBuilderProvider$ar$class_merging$ar$class_merging$ar$class_merging;
        if (shadowDelegateImpl == null) {
            return SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this);
        }
        return SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(((DefaultConsumer) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getAccessibilityService());
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.inputMethodManager = (InputMethodManager) getSystemService("input_method");
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("default_input_method"), false, this.contentObserver);
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(this.contentObserver);
    }

    @Override // android.app.Activity
    protected final void onPause() {
        super.onPause();
        dismissDialog();
    }

    @Override // android.app.Activity
    protected final void onResume() {
        super.onResume();
        checkStatus();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            return;
        }
        if (this.ignoreNextFocusChanged) {
            this.ignoreNextFocusChanged = false;
        } else {
            checkStatus();
        }
    }
}
