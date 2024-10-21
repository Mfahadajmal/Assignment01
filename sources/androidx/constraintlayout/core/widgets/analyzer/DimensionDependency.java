package androidx.constraintlayout.core.widgets.analyzer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DimensionDependency extends DependencyNode {
    public int wrapValue;

    public DimensionDependency(WidgetRun widgetRun, byte[] bArr) {
        this(widgetRun);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.DependencyNode
    public final void resolve(int i) {
        if (!this.resolved) {
            this.resolved = true;
            this.value = i;
            for (Dependency dependency : this.mDependencies) {
                dependency.update(dependency);
            }
        }
    }

    public DimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
        this.mType$ar$edu$7d38fd09_0 = widgetRun instanceof HorizontalWidgetRun ? 2 : 3;
    }
}
