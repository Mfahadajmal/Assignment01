package androidx.media;

import android.media.AudioAttributes;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    public AudioAttributes mAudioAttributes;
    public int mLegacyStreamType = -1;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
    }

    public final int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public final String toString() {
        AudioAttributes audioAttributes = this.mAudioAttributes;
        Objects.toString(audioAttributes);
        return "AudioAttributesCompat: audioattributes=".concat(String.valueOf(audioAttributes));
    }
}
