package com.google.android.accessibility.selecttospeak.debug;

import android.content.Context;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Set;
import kotlin.collections.EmptyList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableSnapshotHelper {
    public static final SerializableSnapshotHelper INSTANCE = new SerializableSnapshotHelper();
    private static final FilenameFilter SNAPSHOT_FILTER = new FilenameFilter() { // from class: com.google.android.accessibility.selecttospeak.debug.SerializableSnapshotHelper$$ExternalSyntheticLambda0
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            SerializableSnapshotHelper serializableSnapshotHelper = SerializableSnapshotHelper.INSTANCE;
            file.getClass();
            str.getClass();
            return OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(str, "s2s-snapshot");
        }
    };

    private SerializableSnapshotHelper() {
    }

    public static final File getSnapshotDir$ar$ds(Context context) {
        File file = new File(context.getCacheDir(), "snapshot");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static final SerializableRect serialize$ar$ds(Rect rect) {
        return new SerializableRect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final List getSnapshotFiles(Context context) {
        File[] listFiles = getSnapshotDir$ar$ds(context).listFiles(SNAPSHOT_FILTER);
        if (listFiles != null) {
            return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.toList(listFiles);
        }
        return EmptyList.INSTANCE;
    }

    public final boolean hasLoop(AccessibilityNodeInfo accessibilityNodeInfo, List list, Set set) {
        boolean z;
        if (set.contains(accessibilityNodeInfo)) {
            return true;
        }
        list.add(accessibilityNodeInfo);
        int childCount = accessibilityNodeInfo.getChildCount();
        boolean z2 = false;
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i);
            if (child != null) {
                z = hasLoop(child, list, set);
            } else {
                z = false;
            }
            z2 |= z;
        }
        return z2;
    }
}
