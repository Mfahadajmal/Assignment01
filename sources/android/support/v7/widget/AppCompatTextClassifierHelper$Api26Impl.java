package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.Collections;
import org.chromium.base.BundleUtils$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatTextClassifierHelper$Api26Impl {
    public AppCompatTextClassifierHelper$Api26Impl() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeScrollExtent(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.getChildCount() != 0 && state.getItemCount() != 0 && view != null && view2 != null) {
            if (!z) {
                return Math.abs(RecyclerView.LayoutManager.getPosition$ar$ds(view) - RecyclerView.LayoutManager.getPosition$ar$ds(view2)) + 1;
            }
            return Math.min(orientationHelper.getTotalSpace(), orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view));
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeScrollOffset(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z, boolean z2) {
        int max;
        if (layoutManager.getChildCount() == 0 || state.getItemCount() == 0 || view == null || view2 == null) {
            return 0;
        }
        int min = Math.min(RecyclerView.LayoutManager.getPosition$ar$ds(view), RecyclerView.LayoutManager.getPosition$ar$ds(view2));
        int max2 = Math.max(RecyclerView.LayoutManager.getPosition$ar$ds(view), RecyclerView.LayoutManager.getPosition$ar$ds(view2));
        if (z2) {
            max = Math.max(0, (state.getItemCount() - max2) - 1);
        } else {
            max = Math.max(0, min);
        }
        if (!z) {
            return max;
        }
        return Math.round((max * (Math.abs(orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(RecyclerView.LayoutManager.getPosition$ar$ds(view) - RecyclerView.LayoutManager.getPosition$ar$ds(view2)) + 1))) + (orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(view)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeScrollRange(RecyclerView.State state, OrientationHelper orientationHelper, View view, View view2, RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager.getChildCount() != 0 && state.getItemCount() != 0 && view != null && view2 != null) {
            if (!z) {
                return state.getItemCount();
            }
            return (int) (((orientationHelper.getDecoratedEnd(view2) - orientationHelper.getDecoratedStart(view)) / (Math.abs(RecyclerView.LayoutManager.getPosition$ar$ds(view) - RecyclerView.LayoutManager.getPosition$ar$ds(view2)) + 1)) * state.getItemCount());
        }
        return 0;
    }

    public static TextClassifier getTextClassifier(TextView textView) {
        TextClassifier textClassifier;
        TextClassifier textClassifier2;
        TextClassificationManager m269m = BundleUtils$$ExternalSyntheticApiModelOutline0.m269m(textView.getContext().getSystemService(BundleUtils$$ExternalSyntheticApiModelOutline0.m271m()));
        if (m269m == null) {
            textClassifier = TextClassifier.NO_OP;
            return textClassifier;
        }
        textClassifier2 = m269m.getTextClassifier();
        return textClassifier2;
    }

    public AppCompatTextClassifierHelper$Api26Impl(byte[] bArr) {
        Collections.emptyList();
        Collections.emptyList();
    }
}
