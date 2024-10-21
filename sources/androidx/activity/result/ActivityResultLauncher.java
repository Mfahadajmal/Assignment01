package androidx.activity.result;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ActivityResultLauncher<I> {
    public final void launch(Object obj) {
        launch$ar$ds(obj);
    }

    public abstract void launch$ar$ds(Object obj);

    public abstract void unregister();
}
