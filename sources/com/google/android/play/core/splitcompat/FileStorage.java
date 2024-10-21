package com.google.android.play.core.splitcompat;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FileStorage {
    private final Context context;
    private File filesDir;
    private final long versionCode;

    public FileStorage(Context context) {
        this.context = context;
        this.versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    }

    public static File checkedFileInside(File file, String str) {
        File file2 = new File(file, str);
        if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
            return file2;
        }
        throw new IllegalArgumentException("split ID cannot be placed in target directory");
    }

    public static void deleteRecursively(File file) {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                deleteRecursively(file2);
            }
        }
        if (file.exists() && !file.delete()) {
            throw new IOException(String.format("Failed to delete '%s'", file.getAbsolutePath()));
        }
    }

    public static String filenameForSplitId(String str) {
        return String.valueOf(str).concat(".apk");
    }

    public static boolean isReadOnly(File file) {
        if (!file.canWrite()) {
            return true;
        }
        return false;
    }

    public static void makeDir$ar$ds(File file) {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("File input must be directory when it exists.");
            }
        } else {
            file.mkdirs();
            if (file.isDirectory()) {
            } else {
                throw new IOException("Unable to create directory: ".concat(String.valueOf(file.getAbsolutePath())));
            }
        }
    }

    public static void markReadOnly(File file) {
        file.setWritable(false, true);
        file.setWritable(false, false);
    }

    private final File topLevelDirectory() {
        if (this.filesDir == null) {
            Context context = this.context;
            if (context != null) {
                this.filesDir = context.getFilesDir();
            } else {
                throw new IllegalStateException("context must be non-null to populate null filesDir");
            }
        }
        File file = new File(this.filesDir, "splitcompat");
        makeDir$ar$ds(file);
        return file;
    }

    public final void cleanup() {
        File file = topLevelDirectory();
        String[] list = file.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(Long.toString(this.versionCode))) {
                    File file2 = new File(file, str);
                    file2.toString();
                    deleteRecursively(file2);
                }
            }
        }
    }

    public final File directoryForNativeLibraries() {
        File file = new File(versionCodeDirectory(), "native-libraries");
        makeDir$ar$ds(file);
        return file;
    }

    public final File directoryForNativeLibrariesForSplit(String str) {
        File checkedFileInside = checkedFileInside(directoryForNativeLibraries(), str);
        makeDir$ar$ds(checkedFileInside);
        return checkedFileInside;
    }

    public final File directoryForUnverifiedSplits() {
        File file = new File(versionCodeDirectory(), "unverified-splits");
        makeDir$ar$ds(file);
        return file;
    }

    public final File directoryForVerifiedSplits() {
        File file = new File(versionCodeDirectory(), "verified-splits");
        makeDir$ar$ds(file);
        return file;
    }

    public final File fileForVerifiedSplit(String str) {
        return checkedFileInside(directoryForVerifiedSplits(), filenameForSplitId(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set verifiedSplits() {
        File directoryForVerifiedSplits = directoryForVerifiedSplits();
        HashSet hashSet = new HashSet();
        File[] listFiles = directoryForVerifiedSplits.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.getName().endsWith(".apk") && isReadOnly(file)) {
                    hashSet.add(new SplitFileInfo(file, file.getName().substring(0, r5.length() - 4)));
                }
            }
        }
        return hashSet;
    }

    public final File versionCodeDirectory() {
        File file = new File(topLevelDirectory(), Long.toString(this.versionCode));
        makeDir$ar$ds(file);
        return file;
    }
}
