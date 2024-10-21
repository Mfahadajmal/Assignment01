package android.support.v7.widget;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.Spannable;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AppCompatReceiveContentHelper$OnDropApi24Impl {
    public AppCompatReceiveContentHelper$OnDropApi24Impl() {
    }

    public static boolean isFinished$ar$edu(int i) {
        if (i != 3 && i != 4 && i != 6) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean onDropForTextView(DragEvent dragEvent, TextView textView, Activity activity) {
        activity.requestDragAndDropPermissions(dragEvent);
        int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
        textView.beginBatchEdit();
        try {
            Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
            ViewCompat.performReceiveContent(textView, new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(dragEvent.getClipData(), 3).build());
            textView.endBatchEdit();
            return true;
        } catch (Throwable th) {
            textView.endBatchEdit();
            throw th;
        }
    }

    static boolean onDropForView(DragEvent dragEvent, View view, Activity activity) {
        activity.requestDragAndDropPermissions(dragEvent);
        ViewCompat.performReceiveContent(view, new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(dragEvent.getClipData(), 3).build());
        return true;
    }

    public static /* synthetic */ String toStringGeneratedffb196af7127d286(int i) {
        switch (i) {
            case 1:
                return "ENQUEUED";
            case 2:
                return "RUNNING";
            case 3:
                return "SUCCEEDED";
            case 4:
                return "FAILED";
            case 5:
                return "BLOCKED";
            case 6:
                return "CANCELLED";
            default:
                return "null";
        }
    }

    public void getItemOffsets$ar$ds(Rect rect, View view, RecyclerView recyclerView) {
        ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        rect.set(0, 0, 0, 0);
    }

    public AppCompatReceiveContentHelper$OnDropApi24Impl(byte[] bArr) {
    }

    public void onDraw$ar$ds(Canvas canvas, RecyclerView recyclerView) {
    }

    public void onDrawOver$ar$ds(Canvas canvas, RecyclerView recyclerView) {
    }
}
