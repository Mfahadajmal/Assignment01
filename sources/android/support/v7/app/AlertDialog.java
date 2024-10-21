package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertController;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AlertDialog extends AppCompatDialog implements DialogInterface {
    final AlertController mAlert;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        public final AlertController.AlertParams P;
        private final int mTheme;

        public Builder(Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public AlertDialog create() {
            int i;
            ListAdapter listAdapter;
            AlertDialog alertDialog = new AlertDialog(this.P.mContext, this.mTheme);
            final AlertController alertController = alertDialog.mAlert;
            final AlertController.AlertParams alertParams = this.P;
            View view = alertParams.mCustomTitleView;
            if (view != null) {
                alertController.mCustomTitleView = view;
            } else {
                CharSequence charSequence = alertParams.mTitle;
                if (charSequence != null) {
                    alertController.setTitle(charSequence);
                }
                Drawable drawable = alertParams.mIcon;
                if (drawable != null) {
                    alertController.mIcon = drawable;
                    alertController.mIconId = 0;
                    ImageView imageView = alertController.mIconView;
                    if (imageView != null) {
                        imageView.setVisibility(0);
                        alertController.mIconView.setImageDrawable(drawable);
                    }
                }
            }
            CharSequence charSequence2 = alertParams.mMessage;
            if (charSequence2 != null) {
                alertController.mMessage = charSequence2;
                TextView textView = alertController.mMessageView;
                if (textView != null) {
                    textView.setText(charSequence2);
                }
            }
            CharSequence charSequence3 = alertParams.mPositiveButtonText;
            if (charSequence3 != null) {
                alertController.setButton$ar$ds(-1, charSequence3, alertParams.mPositiveButtonListener);
            }
            CharSequence charSequence4 = alertParams.mNegativeButtonText;
            if (charSequence4 != null) {
                alertController.setButton$ar$ds(-2, charSequence4, alertParams.mNegativeButtonListener);
            }
            CharSequence charSequence5 = alertParams.mNeutralButtonText;
            if (charSequence5 != null) {
                alertController.setButton$ar$ds(-3, charSequence5, alertParams.mNeutralButtonListener);
            }
            if (alertParams.mItems != null || alertParams.mAdapter != null) {
                final AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) alertParams.mInflater.inflate(alertController.mListLayout, (ViewGroup) null);
                if (alertParams.mIsMultiChoice) {
                    listAdapter = new ArrayAdapter(alertParams.mContext, alertController.mMultiChoiceItemLayout, alertParams.mItems) { // from class: android.support.v7.app.AlertController.AlertParams.1
                        @Override // android.widget.ArrayAdapter, android.widget.Adapter
                        public final View getView(int i2, View view2, ViewGroup viewGroup) {
                            AlertParams alertParams2 = AlertParams.this;
                            View view3 = super.getView(i2, view2, viewGroup);
                            if (alertParams2.mCheckedItems != null && AlertParams.this.mCheckedItems[i2]) {
                                recycleListView.setItemChecked(i2, true);
                            }
                            return view3;
                        }
                    };
                } else {
                    if (alertParams.mIsSingleChoice) {
                        i = alertController.mSingleChoiceItemLayout;
                    } else {
                        i = alertController.mListItemLayout;
                    }
                    listAdapter = alertParams.mAdapter;
                    if (listAdapter == null) {
                        listAdapter = new AlertController.CheckedItemAdapter(alertParams.mContext, i, alertParams.mItems);
                    }
                }
                alertController.mAdapter = listAdapter;
                alertController.mCheckedItem = alertParams.mCheckedItem;
                if (alertParams.mOnClickListener != null) {
                    recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: android.support.v7.app.AlertController.AlertParams.3
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j) {
                            AlertParams.this.mOnClickListener.onClick(alertController.mDialog, i2);
                            if (!AlertParams.this.mIsSingleChoice) {
                                alertController.mDialog.dismiss();
                            }
                        }
                    });
                } else if (alertParams.mOnCheckboxClickListener != null) {
                    recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: android.support.v7.app.AlertController.AlertParams.4
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j) {
                            boolean[] zArr = AlertParams.this.mCheckedItems;
                            if (zArr != null) {
                                zArr[i2] = recycleListView.isItemChecked(i2);
                            }
                            AlertParams.this.mOnCheckboxClickListener.onClick(alertController.mDialog, i2, recycleListView.isItemChecked(i2));
                        }
                    });
                }
                if (alertParams.mIsSingleChoice) {
                    recycleListView.setChoiceMode(1);
                } else if (alertParams.mIsMultiChoice) {
                    recycleListView.setChoiceMode(2);
                }
                alertController.mListView = recycleListView;
            }
            View view2 = alertParams.mView;
            if (view2 != null) {
                alertController.mView = view2;
                alertController.mViewSpacingSpecified = false;
            }
            alertDialog.setCancelable(this.P.mCancelable);
            if (this.P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.P.mOnCancelListener);
            alertDialog.setOnDismissListener(this.P.mOnDismissListener);
            DialogInterface.OnKeyListener onKeyListener = this.P.mOnKeyListener;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        public final Context getContext() {
            return this.P.mContext;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.P.mCancelable = z;
            return this;
        }

        public final void setIcon$ar$ds(Drawable drawable) {
            this.P.mIcon = drawable;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.P.mMessage = charSequence;
            return this;
        }

        public void setMultiChoiceItems$ar$ds(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams.mCheckedItems = zArr;
            alertParams.mIsMultiChoice = true;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNegativeButtonText = charSequence;
            alertParams.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNeutralButtonText = charSequence;
            alertParams.mNeutralButtonListener = onClickListener;
            return this;
        }

        public void setNeutralButton$ar$ds(int i, DialogInterface.OnClickListener onClickListener) {
            Context context = this.P.mContext;
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNeutralButtonText = context.getText(i);
            alertParams.mNeutralButtonListener = onClickListener;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.P.mOnCancelListener = onCancelListener;
            return this;
        }

        public void setOnDismissListener$ar$ds(DialogInterface.OnDismissListener onDismissListener) {
            this.P.mOnDismissListener = onDismissListener;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mPositiveButtonText = charSequence;
            alertParams.mPositiveButtonListener = onClickListener;
            return this;
        }

        public void setSingleChoiceItems$ar$ds(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i;
            alertParams.mIsSingleChoice = true;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.P.mTitle = charSequence;
            return this;
        }

        public Builder setView(View view) {
            this.P.mView = view;
            return this;
        }

        public Builder(Context context, int i) {
            this.P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i)));
            this.mTheme = i;
        }

        public Builder setMessage(int i) {
            Context context = this.P.mContext;
            this.P.mMessage = context.getText(i);
            return this;
        }

        public Builder setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
            Context context = this.P.mContext;
            AlertController.AlertParams alertParams = this.P;
            alertParams.mNegativeButtonText = context.getText(i);
            alertParams.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
            Context context = this.P.mContext;
            AlertController.AlertParams alertParams = this.P;
            alertParams.mPositiveButtonText = context.getText(i);
            alertParams.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setTitle(int i) {
            Context context = this.P.mContext;
            this.P.mTitle = context.getText(i);
            return this;
        }
    }

    protected AlertDialog(Context context, int i) {
        super(context, resolveDialogTheme(context, i));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    static int resolveDialogTheme(Context context, int i) {
        if ((i >>> 24) > 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public final Button getButton(int i) {
        AlertController alertController = this.mAlert;
        if (i != -3) {
            if (i != -2) {
                return alertController.mButtonPositive;
            }
            return alertController.mButtonNegative;
        }
        return alertController.mButtonNeutral;
    }

    public final ListView getListView() {
        return this.mAlert.mListView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:106:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x025b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02fa  */
    @Override // android.support.v7.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCreate(android.os.Bundle r18) {
        /*
            Method dump skipped, instructions count: 781
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AlertDialog.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.support.v7.app.AppCompatDialog, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }
}
