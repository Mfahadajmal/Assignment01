package com.google.android.play.core.splitcompat;

import android.os.Build;
import com.google.mlkit.common.model.RemoteModelManager;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NativeLibraryExtractor {
    private static final Pattern ABI_PATTERN = Pattern.compile("lib/([^/]+)/(.*\\.so)$");
    public static final /* synthetic */ int NativeLibraryExtractor$ar$NoOp = 0;
    public final FileStorage fileStorage;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface NativeLibrariesAction {
        void perform(ZipFile zipFile, Set set);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface NativeLibraryAction {
        void perform$ar$class_merging$ar$class_merging$ar$class_merging(RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration, File file, boolean z);
    }

    public NativeLibraryExtractor(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public static void forAllUsableNativeLibrariesInApk(SplitFileInfo splitFileInfo, NativeLibrariesAction nativeLibrariesAction) {
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(splitFileInfo.splitFile());
        } catch (IOException e) {
            e = e;
            zipFile = null;
        }
        try {
            String splitId = splitFileInfo.splitId();
            HashMap hashMap = new HashMap();
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                Matcher matcher = ABI_PATTERN.matcher(nextElement.getName());
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    String.format("NativeLibraryExtractor: split '%s' has native library '%s' for ABI '%s'", splitId, group2, group);
                    Set set = (Set) hashMap.get(group);
                    if (set == null) {
                        set = new HashSet();
                        hashMap.put(group, set);
                    }
                    set.add(new RemoteModelManager.RemoteModelManagerRegistration(nextElement, group2));
                }
            }
            HashMap hashMap2 = new HashMap();
            for (String str : Build.SUPPORTED_ABIS) {
                if (hashMap.containsKey(str)) {
                    String.format("NativeLibraryExtractor: there are native libraries for supported ABI %s; will use this ABI", str);
                    for (RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration : (Set) hashMap.get(str)) {
                        if (hashMap2.containsKey(remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider)) {
                            String.format("NativeLibraryExtractor: skipping library %s for ABI %s; already present for a better ABI", remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider, str);
                        } else {
                            hashMap2.put(remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider, remoteModelManagerRegistration);
                            String.format("NativeLibraryExtractor: using library %s for ABI %s", remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider, str);
                        }
                    }
                } else {
                    String.format("NativeLibraryExtractor: there are no native libraries for supported ABI %s", str);
                }
            }
            nativeLibrariesAction.perform(zipFile, new HashSet(hashMap2.values()));
            zipFile.close();
        } catch (IOException e2) {
            e = e2;
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e3) {
                    e.addSuppressed(e3);
                }
            }
            throw e;
        }
    }

    public final void forEachNativeLibraryInApk(SplitFileInfo splitFileInfo, Set set, NativeLibraryAction nativeLibraryAction) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration = (RemoteModelManager.RemoteModelManagerRegistration) it.next();
            File checkedFileInside = FileStorage.checkedFileInside(this.fileStorage.directoryForNativeLibrariesForSplit(splitFileInfo.splitId()), (String) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider);
            boolean z = false;
            if (checkedFileInside.exists() && checkedFileInside.length() == ((ZipEntry) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass).getSize() && FileStorage.isReadOnly(checkedFileInside)) {
                z = true;
            }
            nativeLibraryAction.perform$ar$class_merging$ar$class_merging$ar$class_merging(remoteModelManagerRegistration, checkedFileInside, z);
        }
    }
}
