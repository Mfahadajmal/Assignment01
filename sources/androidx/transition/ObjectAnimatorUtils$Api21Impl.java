package androidx.transition;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.util.Property;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ObjectAnimatorUtils$Api21Impl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, V> ObjectAnimator ofObject(T t, Property<T, V> property, Path path) {
        return ObjectAnimator.ofObject(t, property, (TypeConverter) null, path);
    }
}
