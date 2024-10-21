package com.google.android.accessibility.accessibilitymenu.model;

import com.google.android.accessibility.accessibilitymenu.utils.A11yMenuUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yMenuShortcut {
    public int imageColor;
    public int imageSrc;
    public int imgContentDescription;
    public int labelText;
    public int shortcutId;

    public A11yMenuShortcut(int i) {
        this.shortcutId = i;
        if (i < 0 || i > 12) {
            this.shortcutId = 0;
            LogUtils.w("A11yMenuShortcut", "setId to default UNSPECIFIED_ID as id is invalid. Max value is %d while id is %d", 12, Integer.valueOf(i));
        }
        int[] iArr = A11yMenuUtils.shortcutResource[this.shortcutId];
        this.imageSrc = iArr[0];
        this.imageColor = iArr[1];
        this.imgContentDescription = iArr[2];
        this.labelText = iArr[3];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof A11yMenuShortcut) && this.shortcutId == ((A11yMenuShortcut) obj).shortcutId) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.shortcutId;
    }
}
