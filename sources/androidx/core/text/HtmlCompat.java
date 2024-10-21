package androidx.core.text;

import android.text.Html;
import android.text.Spanned;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HtmlCompat {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static Spanned fromHtml(String str, int i) {
            return Html.fromHtml(str, i);
        }

        public static float interpolate(float[] fArr, float f, float f2) {
            if (f2 >= 1.0f) {
                return 1.0f;
            }
            if (f2 <= 0.0f) {
                return 0.0f;
            }
            int min = Math.min((int) (200.0f * f2), 199);
            float f3 = f2 - (min * 0.005f);
            float f4 = fArr[min];
            return f4 + ((f3 / 0.005f) * (fArr[min + 1] - f4));
        }

        static String toHtml(Spanned spanned, int i) {
            return Html.toHtml(spanned, i);
        }

        static Spanned fromHtml(String str, int i, Html.ImageGetter imageGetter, Html.TagHandler tagHandler) {
            return Html.fromHtml(str, i, imageGetter, tagHandler);
        }
    }
}
