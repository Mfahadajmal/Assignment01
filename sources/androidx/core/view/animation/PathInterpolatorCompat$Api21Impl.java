package androidx.core.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PathInterpolatorCompat$Api21Impl {
    public static final String createInsertQuery(String str) {
        return "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '86254750241babac4b8d52996a675549')";
    }

    static Interpolator createPathInterpolator(float f, float f2) {
        return new PathInterpolator(f, f2);
    }

    public static Interpolator createPathInterpolator(float f, float f2, float f3, float f4) {
        return new PathInterpolator(f, f2, f3, f4);
    }

    public static Interpolator createPathInterpolator(Path path) {
        return new PathInterpolator(path);
    }
}
