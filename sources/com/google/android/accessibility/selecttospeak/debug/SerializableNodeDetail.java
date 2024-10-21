package com.google.android.accessibility.selecttospeak.debug;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableNodeDetail {
    private final String className;
    private final String containerTitle;
    private final String contentDescription;
    private final String error;
    private final String hintText;
    private final String paneTitle;
    private final String stateDescription;
    private final String text;
    private final String toolTipText;

    public SerializableNodeDetail() {
        this(null, null, null, null, null, null, null, 511);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableNodeDetail)) {
            return false;
        }
        SerializableNodeDetail serializableNodeDetail = (SerializableNodeDetail) obj;
        if (Intrinsics.areEqual(this.className, serializableNodeDetail.className) && Intrinsics.areEqual(this.containerTitle, serializableNodeDetail.containerTitle) && Intrinsics.areEqual(this.text, serializableNodeDetail.text) && Intrinsics.areEqual(this.contentDescription, serializableNodeDetail.contentDescription) && Intrinsics.areEqual(this.stateDescription, serializableNodeDetail.stateDescription) && Intrinsics.areEqual(this.error, serializableNodeDetail.error) && Intrinsics.areEqual(this.hintText, serializableNodeDetail.hintText) && Intrinsics.areEqual(this.paneTitle, serializableNodeDetail.paneTitle) && Intrinsics.areEqual(this.toolTipText, serializableNodeDetail.toolTipText)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((((((((((((((this.className.hashCode() * 31) + this.containerTitle.hashCode()) * 31) + this.text.hashCode()) * 31) + this.contentDescription.hashCode()) * 31) + this.stateDescription.hashCode()) * 31) + this.error.hashCode()) * 31) + this.hintText.hashCode()) * 31) + this.paneTitle.hashCode()) * 31) + this.toolTipText.hashCode();
    }

    public final String toString() {
        return "SerializableNodeDetail(className=" + this.className + ", containerTitle=" + this.containerTitle + ", text=" + this.text + ", contentDescription=" + this.contentDescription + ", stateDescription=" + this.stateDescription + ", error=" + this.error + ", hintText=" + this.hintText + ", paneTitle=" + this.paneTitle + ", toolTipText=" + this.toolTipText + ")";
    }

    public /* synthetic */ SerializableNodeDetail(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        str = 1 == (i & 1) ? "" : str;
        str.getClass();
        String str8 = (i & 2) != 0 ? "" : null;
        str8.getClass();
        str2 = (i & 4) != 0 ? "" : str2;
        str2.getClass();
        str3 = (i & 8) != 0 ? "" : str3;
        str3.getClass();
        str4 = (i & 16) != 0 ? "" : str4;
        str4.getClass();
        str5 = (i & 32) != 0 ? "" : str5;
        str5.getClass();
        str6 = (i & 64) != 0 ? "" : str6;
        str6.getClass();
        String str9 = (i & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) != 0 ? "" : null;
        str9.getClass();
        str7 = (i & 256) != 0 ? "" : str7;
        str7.getClass();
        this.className = str;
        this.containerTitle = str8;
        this.text = str2;
        this.contentDescription = str3;
        this.stateDescription = str4;
        this.error = str5;
        this.hintText = str6;
        this.paneTitle = str9;
        this.toolTipText = str7;
    }
}
