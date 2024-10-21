package androidx.media;

import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {
    public int mUsage = 0;
    public int mContentType = 0;
    public int mFlags = 0;
    public int mLegacyStream = -1;

    public final boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.mContentType == audioAttributesImplBase.mContentType) {
            int i = this.mFlags;
            int i2 = audioAttributesImplBase.mFlags;
            int i3 = audioAttributesImplBase.mLegacyStream;
            if (i3 == -1) {
                int i4 = audioAttributesImplBase.mUsage;
                int i5 = AudioAttributesCompat.AudioAttributesCompat$ar$NoOp;
                if ((i2 & 1) == 1) {
                    i3 = 7;
                } else if ((i2 & 4) == 4) {
                    i3 = 6;
                } else {
                    switch (i4) {
                        case 0:
                        case 1:
                        case 12:
                        case 14:
                        case 15:
                        case 16:
                        default:
                            i3 = 3;
                            break;
                        case 2:
                            i3 = 0;
                            break;
                        case 3:
                            i3 = 8;
                            break;
                        case 4:
                            i3 = 4;
                            break;
                        case 5:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                            i3 = 5;
                            break;
                        case 6:
                            i3 = 2;
                            break;
                        case 11:
                            i3 = 10;
                            break;
                        case 13:
                            i3 = 1;
                            break;
                    }
                }
            }
            if (i3 == 6) {
                i2 |= 4;
            } else if (i3 == 7) {
                i2 |= 1;
            }
            if (i == (i2 & 273) && this.mUsage == audioAttributesImplBase.mUsage && this.mLegacyStream == audioAttributesImplBase.mLegacyStream) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.mLegacyStream != -1) {
            sb.append(" stream=");
            sb.append(this.mLegacyStream);
            sb.append(" derived");
        }
        sb.append(" usage=");
        int i = this.mUsage;
        int i2 = AudioAttributesCompat.AudioAttributesCompat$ar$NoOp;
        switch (i) {
            case 0:
                str = "USAGE_UNKNOWN";
                break;
            case 1:
                str = "USAGE_MEDIA";
                break;
            case 2:
                str = "USAGE_VOICE_COMMUNICATION";
                break;
            case 3:
                str = "USAGE_VOICE_COMMUNICATION_SIGNALLING";
                break;
            case 4:
                str = "USAGE_ALARM";
                break;
            case 5:
                str = "USAGE_NOTIFICATION";
                break;
            case 6:
                str = "USAGE_NOTIFICATION_RINGTONE";
                break;
            case 7:
                str = "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
                break;
            case 8:
                str = "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
                break;
            case 9:
                str = "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
                break;
            case 10:
                str = "USAGE_NOTIFICATION_EVENT";
                break;
            case 11:
                str = "USAGE_ASSISTANCE_ACCESSIBILITY";
                break;
            case 12:
                str = "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
                break;
            case 13:
                str = "USAGE_ASSISTANCE_SONIFICATION";
                break;
            case 14:
                str = "USAGE_GAME";
                break;
            case 15:
            default:
                str = "unknown usage " + i;
                break;
            case 16:
                str = "USAGE_ASSISTANT";
                break;
        }
        sb.append(str);
        sb.append(" content=");
        sb.append(this.mContentType);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.mFlags).toUpperCase());
        return sb.toString();
    }
}
