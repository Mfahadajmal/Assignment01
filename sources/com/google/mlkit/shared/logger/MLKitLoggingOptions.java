package com.google.mlkit.shared.logger;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.talkback.Feedback;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MLKitLoggingOptions {
    private final boolean enableFirelog;
    private final int firelogEventType;
    private final String libraryName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object MLKitLoggingOptions$Builder$ar$libraryName;
        public boolean enableFirelog;
        public int firelogEventType;
        public byte set$0;

        public Builder() {
        }

        public final BrailleKeyBindingUtils.SupportedCommand.KeyDescriptor build() {
            Object obj;
            if (this.set$0 == 7 && (obj = this.MLKitLoggingOptions$Builder$ar$libraryName) != null) {
                return new BrailleKeyBindingUtils.SupportedCommand.KeyDescriptor(this.enableFirelog, (BrailleCharacter) obj, this.firelogEventType);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" space");
            }
            if (this.MLKitLoggingOptions$Builder$ar$libraryName == null) {
                sb.append(" dots");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" longPress");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" keyNameRes");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final void setAction$ar$edu$a06fe909_0$ar$ds(int i) {
            this.firelogEventType = i;
        }

        public final void setDots$ar$ds(BrailleCharacter brailleCharacter) {
            if (brailleCharacter != null) {
                this.MLKitLoggingOptions$Builder$ar$libraryName = brailleCharacter;
                return;
            }
            throw new NullPointerException("Null dots");
        }

        public final void setKeyNameRes$ar$ds(int i) {
            this.firelogEventType = i;
            this.set$0 = (byte) (this.set$0 | 4);
        }

        public final void setSpace$ar$ds(boolean z) {
            this.enableFirelog = z;
            this.set$0 = (byte) (this.set$0 | 1);
        }

        public final void setUserRequested$ar$ds(boolean z) {
            this.enableFirelog = z;
            this.set$0 = (byte) 1;
        }

        public Builder(byte[] bArr) {
            this();
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            this();
        }

        public Builder(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this();
        }

        /* renamed from: build, reason: collision with other method in class */
        public final Feedback.ImageCaption m225build() {
            int i;
            if (this.set$0 == 1 && (i = this.firelogEventType) != 0) {
                return new Feedback.ImageCaption(i, (AccessibilityNodeInfoCompat) this.MLKitLoggingOptions$Builder$ar$libraryName, this.enableFirelog);
            }
            StringBuilder sb = new StringBuilder();
            if (this.firelogEventType == 0) {
                sb.append(" action");
            }
            if (this.set$0 == 0) {
                sb.append(" userRequested");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
    }

    public MLKitLoggingOptions() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MLKitLoggingOptions) {
            MLKitLoggingOptions mLKitLoggingOptions = (MLKitLoggingOptions) obj;
            if (this.libraryName.equals(mLKitLoggingOptions.getLibraryName()) && this.enableFirelog == mLKitLoggingOptions.isEnableFirelog() && this.firelogEventType == mLKitLoggingOptions.getFirelogEventType()) {
                return true;
            }
        }
        return false;
    }

    public final int getFirelogEventType() {
        return this.firelogEventType;
    }

    public final String getLibraryName() {
        return this.libraryName;
    }

    public final int hashCode() {
        int i;
        int hashCode = this.libraryName.hashCode() ^ 1000003;
        if (true != this.enableFirelog) {
            i = 1237;
        } else {
            i = 1231;
        }
        return (((hashCode * 1000003) ^ i) * 1000003) ^ this.firelogEventType;
    }

    public final boolean isEnableFirelog() {
        return this.enableFirelog;
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.libraryName + ", enableFirelog=" + this.enableFirelog + ", firelogEventType=" + this.firelogEventType + "}";
    }

    public MLKitLoggingOptions(String str, boolean z, int i) {
        this();
        this.libraryName = str;
        this.enableFirelog = true;
        this.firelogEventType = 1;
    }
}
