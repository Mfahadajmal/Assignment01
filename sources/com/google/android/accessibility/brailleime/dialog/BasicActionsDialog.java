package com.google.android.accessibility.brailleime.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import com.google.android.material.datepicker.YearGridAdapter;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BasicActionsDialog extends ViewAttachedDialog {
    private static AlertDialog basicActionsDialog;
    public final RetryingNameResolver.ResolutionResultListener callback$ar$class_merging$cf6af36f_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Context context;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    enum Gestures {
        SWIPE_RIGHT_ONE(R.drawable.ic_one_finger_right, R.string.gesture_add_space),
        SWIPE_LEFT_ONE(R.drawable.ic_one_finger_left, R.string.gesture_delete),
        SWIPE_RIGHT_TWO(R.drawable.ic_two_fingers_right, R.string.gesture_new_line),
        SWIPE_LEFT_TWO(R.drawable.ic_two_fingers_left, R.string.gesture_delete_word),
        SWIPE_UP_ONE(R.drawable.ic_one_finger_up, R.string.gesture_move_cursor_backward),
        SWIPE_DOWN_ONE(R.drawable.ic_one_finger_down, R.string.gesture_move_cursor_forward),
        SWIPE_DOWN_TWO(R.drawable.ic_two_fingers_down, R.string.gesture_hide_keyboard),
        SWIPE_DOWN_THREE(R.drawable.ic_three_fingers_down, R.string.gesture_next_keyboard),
        SWIPE_UP_TWO(R.drawable.ic_two_fingers_up, R.string.gesture_submit_text),
        SWIPE_UP_THREE(R.drawable.ic_three_fingers_up, R.string.gesture_open_context_menu);

        public final int iconDrawableRes;
        public final int titleStringRes;

        Gestures(int i, int i2) {
            this.iconDrawableRes = i;
            this.titleStringRes = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class GesturesAndActionsAdapter extends RecyclerView.Adapter {
        private final Context context;

        public GesturesAndActionsAdapter(Context context) {
            this.context = context;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final int getItemCount() {
            return Gestures.values().length;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            YearGridAdapter.ViewHolder viewHolder2 = (YearGridAdapter.ViewHolder) viewHolder;
            int i2 = YearGridAdapter.ViewHolder.YearGridAdapter$ViewHolder$ar$NoOp;
            ((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView).setTextSize(0, this.context.getResources().getDimension(R.dimen.actions_item_text_size));
            Drawable drawable = this.context.getDrawable(Gestures.values()[i].iconDrawableRes);
            int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.actions_drawable_size);
            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            ((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView).setCompoundDrawables(drawable, null, null, null);
            ((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView).setCompoundDrawablePadding(this.context.getResources().getDimensionPixelSize(R.dimen.padding_between_drawable_and_text));
            ((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView).setText(Gestures.values()[i].titleStringRes);
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new YearGridAdapter.ViewHolder(LayoutInflater.from(this.context).inflate(android.R.layout.select_dialog_item, viewGroup, false), (byte[]) null);
        }
    }

    public BasicActionsDialog(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.context = context;
        this.callback$ar$class_merging$cf6af36f_0$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    protected final AlertDialog.Builder dialogBuilder() {
        return SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this.context);
    }

    @Override // com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog
    protected final Dialog makeDialog() {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, R.style.DialogTheme);
        RecyclerView recyclerView = new RecyclerView(contextThemeWrapper);
        recyclerView.setLayoutManager(new LinearLayoutManager(contextThemeWrapper));
        recyclerView.setAdapter(new GesturesAndActionsAdapter(contextThemeWrapper));
        Context context = this.context;
        AlertDialog.Builder dialogBuilder = dialogBuilder();
        dialogBuilder.setTitle(context.getString(R.string.basic_gestures)).setView(recyclerView).setNegativeButton(this.context.getString(R.string.all_gestures), new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 8)).setPositiveButton(this.context.getString(android.R.string.cancel), (DialogInterface.OnClickListener) null);
        AlertDialog create = dialogBuilder.create();
        basicActionsDialog = create;
        create.setCanceledOnTouchOutside(false);
        return basicActionsDialog;
    }

    public BasicActionsDialog(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener, byte[] bArr) {
        this(context, resolutionResultListener);
    }
}
