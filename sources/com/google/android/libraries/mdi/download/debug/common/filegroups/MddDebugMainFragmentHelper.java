package com.google.android.libraries.mdi.download.debug.common.filegroups;

import android.os.Parcelable;
import com.google.mlkit.logging.schema.ImageInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MddDebugMainFragmentHelper implements MddDebugMainFragmentActionProvider, MddDebugMainFragmentUiProvider {
    public final MddDebugMainFragmentActionProvider actionProvider;
    public final MddDebugMainFragmentUiProvider uiProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class ActionInfo implements Parcelable {
        public final String action;
        public final String tag;

        public ActionInfo() {
        }

        public static ImageInfo.Builder newBuilder$ar$class_merging$ar$class_merging$ar$class_merging() {
            ImageInfo.Builder builder = new ImageInfo.Builder(null, null, null, null);
            builder.ImageInfo$Builder$ar$originalImageSize = "UNKNOWN";
            builder.ImageInfo$Builder$ar$imageFormat = null;
            return builder;
        }

        public final String action() {
            return this.action;
        }

        public final boolean equals(Object obj) {
            String str;
            if (obj == this) {
                return true;
            }
            if (obj instanceof ActionInfo) {
                ActionInfo actionInfo = (ActionInfo) obj;
                if (this.action.equals(actionInfo.action()) && ((str = this.tag) != null ? str.equals(actionInfo.tag()) : actionInfo.tag() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int hashCode2 = this.action.hashCode() ^ 1000003;
            String str = this.tag;
            if (str == null) {
                hashCode = 0;
            } else {
                hashCode = str.hashCode();
            }
            return (hashCode2 * 1000003) ^ hashCode;
        }

        public final String tag() {
            return this.tag;
        }

        public final String toString() {
            return "ActionInfo{action=" + this.action + ", tag=" + this.tag + "}";
        }

        public ActionInfo(String str, String str2) {
            this();
            if (str == null) {
                throw new NullPointerException("Null action");
            }
            this.action = str;
            this.tag = str2;
        }
    }

    public MddDebugMainFragmentHelper(MddDebugMainFragmentUiProvider mddDebugMainFragmentUiProvider, MddDebugMainFragmentActionProvider mddDebugMainFragmentActionProvider) {
        this.uiProvider = mddDebugMainFragmentUiProvider;
        this.actionProvider = mddDebugMainFragmentActionProvider;
    }
}
