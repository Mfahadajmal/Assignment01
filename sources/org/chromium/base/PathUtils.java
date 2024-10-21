package org.chromium.base;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PathUtils {
    static {
        new AtomicBoolean();
    }

    private PathUtils() {
    }

    public static String[] getAllPrivateDownloadsDirectories() {
        List arrayList = new ArrayList();
        StrictModeContext allowDiskWrites = StrictModeContext.allowDiskWrites();
        try {
            File[] externalFilesDirs = ContextUtils.sApplicationContext.getExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
            if (externalFilesDirs != null) {
                arrayList = Arrays.asList(externalFilesDirs);
            }
            allowDiskWrites.close();
            return toAbsolutePathStrings(arrayList);
        } catch (Throwable th) {
            try {
                allowDiskWrites.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String getCacheDirectory() {
        return getDirectoryPath$ar$ds();
    }

    public static String getDataDirectory() {
        return getDirectoryPath$ar$ds();
    }

    private static String getDirectoryPath$ar$ds() {
        throw null;
    }

    public static String getDownloadsDirectory() {
        String path;
        StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                String[] allPrivateDownloadsDirectories = getAllPrivateDownloadsDirectories();
                if (allPrivateDownloadsDirectories.length == 0) {
                    path = "";
                } else {
                    path = allPrivateDownloadsDirectories[0];
                }
            } else {
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            }
            allowDiskReads.close();
            return path;
        } catch (Throwable th) {
            try {
                allowDiskReads.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String[] getExternalDownloadVolumesNames() {
        Set<String> externalVolumeNames;
        StorageVolume storageVolume;
        File directory;
        ArrayList arrayList = new ArrayList();
        externalVolumeNames = MediaStore.getExternalVolumeNames(ContextUtils.sApplicationContext);
        for (String str : externalVolumeNames) {
            if (!TextUtils.isEmpty(str) && !str.contains("external_primary")) {
                storageVolume = ((StorageManager) ContextUtils.sApplicationContext.getSystemService(StorageManager.class)).getStorageVolume(MediaStore.Files.getContentUri(str));
                directory = storageVolume.getDirectory();
                File file = new File(directory, Environment.DIRECTORY_DOWNLOADS);
                if (!file.isDirectory()) {
                    Log.w("cr_".concat("PathUtils"), String.format(Locale.US, "Download dir missing: %s, parent dir:%s, isDirectory:%s", file.getAbsolutePath(), directory.getAbsolutePath(), Boolean.valueOf(directory.isDirectory())));
                }
                arrayList.add(file);
            }
        }
        return toAbsolutePathStrings(arrayList);
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    private static String getNativeLibraryDirectory() {
        ApplicationInfo applicationInfo = ContextUtils.sApplicationContext.getApplicationInfo();
        if ((applicationInfo.flags & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) == 0 && (applicationInfo.flags & 1) != 0) {
            return "/system/lib/";
        }
        return applicationInfo.nativeLibraryDir;
    }

    public static String getThumbnailCacheDirectory() {
        return getDirectoryPath$ar$ds();
    }

    private static String[] toAbsolutePathStrings(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            if (file != null && !TextUtils.isEmpty(file.getAbsolutePath())) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
