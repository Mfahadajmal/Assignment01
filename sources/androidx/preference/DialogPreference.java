package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import androidx.preference.PreferenceManager;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DialogPreference extends Preference {
    private Drawable mDialogIcon;
    private int mDialogLayoutResId;
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private CharSequence mNegativeButtonText;
    private CharSequence mPositiveButtonText;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TargetFragment {
        <T extends Preference> T findPreference(CharSequence charSequence);
    }

    public DialogPreference(Context context) {
        this(context, null);
    }

    public Drawable getDialogIcon() {
        return this.mDialogIcon;
    }

    public int getDialogLayoutResource() {
        return this.mDialogLayoutResId;
    }

    public CharSequence getDialogMessage() {
        return this.mDialogMessage;
    }

    public CharSequence getDialogTitle() {
        return this.mDialogTitle;
    }

    public CharSequence getNegativeButtonText() {
        return this.mNegativeButtonText;
    }

    public CharSequence getPositiveButtonText() {
        return this.mPositiveButtonText;
    }

    @Override // androidx.preference.Preference
    protected void onClick() {
        PreferenceManager.OnDisplayPreferenceDialogListener onDisplayPreferenceDialogListener = getPreferenceManager().mOnDisplayPreferenceDialogListener;
        if (onDisplayPreferenceDialogListener != null) {
            onDisplayPreferenceDialogListener.onDisplayPreferenceDialog(this);
        }
    }

    public void setDialogIcon(Drawable drawable) {
        this.mDialogIcon = drawable;
    }

    public void setDialogLayoutResource(int i) {
        this.mDialogLayoutResId = i;
    }

    public void setDialogMessage(CharSequence charSequence) {
        this.mDialogMessage = charSequence;
    }

    public void setDialogTitle(CharSequence charSequence) {
        this.mDialogTitle = charSequence;
    }

    public void setNegativeButtonText(CharSequence charSequence) {
        this.mNegativeButtonText = charSequence;
    }

    public void setPositiveButtonText(CharSequence charSequence) {
        this.mPositiveButtonText = charSequence;
    }

    public DialogPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, NotificationCompatBuilder$Api20Impl.getAttr(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle));
    }

    public void setDialogIcon(int i) {
        this.mDialogIcon = AppCompatDelegate.Api33Impl.getDrawable(getContext(), i);
    }

    public void setDialogMessage(int i) {
        setDialogMessage(getContext().getString(i));
    }

    public void setDialogTitle(int i) {
        setDialogTitle(getContext().getString(i));
    }

    public void setNegativeButtonText(int i) {
        setNegativeButtonText(getContext().getString(i));
    }

    public void setPositiveButtonText(int i) {
        setPositiveButtonText(getContext().getString(i));
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DialogPreference, i, i2);
        String string = NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 9, 0);
        this.mDialogTitle = string;
        if (string == null) {
            this.mDialogTitle = getTitle();
        }
        this.mDialogMessage = NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 8, 1);
        Drawable drawable = obtainStyledAttributes.getDrawable(6);
        this.mDialogIcon = drawable == null ? obtainStyledAttributes.getDrawable(2) : drawable;
        this.mPositiveButtonText = NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 11, 3);
        this.mNegativeButtonText = NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes, 10, 4);
        this.mDialogLayoutResId = NotificationCompatBuilder$Api20Impl.getResourceId(obtainStyledAttributes, 7, 5, 0);
        obtainStyledAttributes.recycle();
    }
}
