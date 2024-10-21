package androidx.transition;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Build;

/* compiled from: PG */
/* loaded from: classes.dex */
final class TransitionUtils {
    public static final boolean HAS_PICTURE_BITMAP;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api28Impl {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static Bitmap createBitmap(Picture picture) {
            return Bitmap.createBitmap(picture);
        }

        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] iArr) {
            return false;
        }
    }

    static {
        boolean z;
        if (Build.VERSION.SDK_INT >= 28) {
            z = true;
        } else {
            z = false;
        }
        HAS_PICTURE_BITMAP = z;
    }
}
