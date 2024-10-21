package androidx.media;

import android.util.SparseIntArray;
import androidx.versionedparcelable.VersionedParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AudioAttributesCompat implements VersionedParcelable {
    public static final /* synthetic */ int AudioAttributesCompat$ar$NoOp = 0;
    private static final SparseIntArray SUPPRESSIBLE_USAGES;
    public AudioAttributesImpl mImpl;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        SUPPRESSIBLE_USAGES = sparseIntArray;
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat audioAttributesCompat = (AudioAttributesCompat) obj;
        AudioAttributesImpl audioAttributesImpl = this.mImpl;
        if (audioAttributesImpl == null) {
            if (audioAttributesCompat.mImpl != null) {
                return false;
            }
            return true;
        }
        return audioAttributesImpl.equals(audioAttributesCompat.mImpl);
    }

    public final int hashCode() {
        return this.mImpl.hashCode();
    }

    public final String toString() {
        return this.mImpl.toString();
    }
}
