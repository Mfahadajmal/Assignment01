package com.google.android.accessibility.talkback;

import android.graphics.Rect;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class GranularityIterator$LineTextSegmentIterator extends GranularityIterator$AbstractTextSegmentIterator {
    public AccessibilityNodeInfoCompat node;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LazyHolder {
        static final GranularityIterator$LineTextSegmentIterator INSTANCE = new GranularityIterator$LineTextSegmentIterator();
    }

    private static int getTextLocationsInNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CharSequence charSequence, int i, List list) {
        List textLocations;
        List textLocations2;
        int length = charSequence.length();
        if (i > 0 && (textLocations2 = AccessibilityNodeInfoUtils.getTextLocations(accessibilityNodeInfoCompat, charSequence, 0, i)) != null) {
            list.addAll(textLocations2);
        }
        int size = list.size();
        if (i < length && (textLocations = AccessibilityNodeInfoUtils.getTextLocations(accessibilityNodeInfoCompat, charSequence, i, length)) != null) {
            list.addAll(textLocations);
        }
        if (list.isEmpty()) {
            return -1;
        }
        return size;
    }

    @Override // com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    public final int[] following(int i) {
        String str = this.iteratorText;
        if (!TextUtils.isEmpty(str) && this.node != null && i < str.length()) {
            if (i < 0) {
                i = 0;
            }
            ArrayList arrayList = new ArrayList();
            int textLocationsInNode = getTextLocationsInNode(this.node, str, i, arrayList);
            if (textLocationsInNode != -1) {
                int i2 = i;
                while (true) {
                    int i3 = textLocationsInNode + 1;
                    int size = arrayList.size();
                    i2++;
                    if (i3 >= 0 && ((i2 < str.length() && str.charAt(i2) == '\n') || i3 >= size || (i3 != size - 1 && ((Rect) arrayList.get(i3)).top < ((Rect) arrayList.get(textLocationsInNode + 2)).top))) {
                        break;
                    }
                    textLocationsInNode = i3;
                }
                return getRange(i, i2);
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    public final int[] preceding(int i) {
        String str = this.iteratorText;
        if (!TextUtils.isEmpty(str) && this.node != null) {
            int length = this.iteratorText.length();
            if (i > 0) {
                if (i > length) {
                    i = length;
                }
                ArrayList arrayList = new ArrayList();
                int textLocationsInNode = getTextLocationsInNode(this.node, str, i, arrayList);
                if (textLocationsInNode != -1) {
                    int i2 = i;
                    while (true) {
                        int i3 = textLocationsInNode - 1;
                        i2--;
                        if (i3 <= 0 || ((Rect) arrayList.get(textLocationsInNode - 2)).top < ((Rect) arrayList.get(i3)).top) {
                            break;
                        }
                        textLocationsInNode = i3;
                    }
                    return getRange(i2, i);
                }
                return null;
            }
            return null;
        }
        return null;
    }
}
