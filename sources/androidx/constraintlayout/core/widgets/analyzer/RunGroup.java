package androidx.constraintlayout.core.widgets.analyzer;

import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RunGroup {
    public static int index;
    public Object RunGroup$ar$mFirstRun;
    final Object RunGroup$ar$mRuns;

    public RunGroup(TextView textView) {
        this.RunGroup$ar$mRuns = textView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.view.textclassifier.TextClassifier, java.lang.Object] */
    public final TextClassifier getTextClassifier() {
        ?? r0 = this.RunGroup$ar$mFirstRun;
        if (r0 == 0) {
            return AppCompatTextClassifierHelper$Api26Impl.getTextClassifier((TextView) this.RunGroup$ar$mRuns);
        }
        return r0;
    }

    public final long traverseEnd(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.mDependencies.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = (Dependency) dependencyNode.mDependencies.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.mRun != widgetRun) {
                    j2 = Math.min(j2, traverseEnd(dependencyNode2, dependencyNode2.mMargin + j));
                }
            }
        }
        if (dependencyNode == widgetRun.end) {
            long wrapDimension = j - widgetRun.getWrapDimension();
            return Math.min(Math.min(j2, traverseEnd(widgetRun.start, wrapDimension)), wrapDimension - widgetRun.start.mMargin);
        }
        return j2;
    }

    public final long traverseStart(DependencyNode dependencyNode, long j) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun instanceof HelperReferences) {
            return j;
        }
        int size = dependencyNode.mDependencies.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            Dependency dependency = (Dependency) dependencyNode.mDependencies.get(i);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.mRun != widgetRun) {
                    j2 = Math.max(j2, traverseStart(dependencyNode2, dependencyNode2.mMargin + j));
                }
            }
        }
        if (dependencyNode == widgetRun.start) {
            long wrapDimension = j + widgetRun.getWrapDimension();
            return Math.max(Math.max(j2, traverseStart(widgetRun.end, wrapDimension)), wrapDimension - widgetRun.end.mMargin);
        }
        return j2;
    }

    public RunGroup(WidgetRun widgetRun) {
        this.RunGroup$ar$mFirstRun = null;
        this.RunGroup$ar$mRuns = new ArrayList();
        index++;
        this.RunGroup$ar$mFirstRun = widgetRun;
    }
}
