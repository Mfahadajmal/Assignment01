package androidx.media;

import androidx.versionedparcelable.VersionedParcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(VersionedParcel versionedParcel) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        audioAttributesCompat.mImpl = (AudioAttributesImpl) versionedParcel.readVersionedParcelable$ar$ds(audioAttributesCompat.mImpl);
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, VersionedParcel versionedParcel) {
        versionedParcel.writeVersionedParcelable$ar$ds(audioAttributesCompat.mImpl);
    }
}
