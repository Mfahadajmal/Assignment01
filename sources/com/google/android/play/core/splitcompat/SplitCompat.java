package com.google.android.play.core.splitcompat;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.play.core.splitcompat.NativeLibraryExtractor;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import com.google.android.play.core.splitcompat.loader.ClassLoaderInjectorV23;
import com.google.android.play.core.splitcompat.loader.SplitCompatLoadingException;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitCompatInterceptorProvider;
import com.google.android.play.core.splitinstall.SplitInstallEmulatedSplitsProvider;
import com.google.android.play.core.splitinstall.SplitInstallInfoProvider;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.internal.RetryingNameResolver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SplitCompat {
    public static final AtomicReference installed = new AtomicReference(null);
    private final SplitInstallModule assetPathAdder$ar$class_merging;
    private final Set emulatedSplits = new HashSet();
    private final SplitInstallSharedPreferences sharedPreferences;
    public final FileStorage storage;

    private SplitCompat(Context context) {
        try {
            FileStorage fileStorage = new FileStorage(context);
            this.storage = fileStorage;
            this.assetPathAdder$ar$class_merging = new SplitInstallModule(fileStorage);
            this.sharedPreferences = new SplitInstallSharedPreferences(context);
        } catch (PackageManager.NameNotFoundException e) {
            throw new SplitCompatLoadingException(e);
        }
    }

    private final synchronized void emulateInstallAll(Context context, boolean z) {
        List<String> asList;
        ClassLoaderInjectorV23 classLoaderInjectorV23;
        IOException iOException;
        ZipFile zipFile;
        ZipFile zipFile2;
        if (z) {
            this.storage.cleanup();
        } else {
            NativeLibraryPathListMutex.get().execute(new FlagStore$$ExternalSyntheticLambda3(this, 20, null));
        }
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo.splitNames == null) {
                asList = new ArrayList();
            } else {
                asList = Arrays.asList(packageInfo.splitNames);
            }
            FileStorage fileStorage = this.storage;
            SplitInstallSharedPreferences splitInstallSharedPreferences = this.sharedPreferences;
            Set<SplitFileInfo> verifiedSplits = fileStorage.verifiedSplits();
            Set modulesToUninstallIfEmulated = splitInstallSharedPreferences.getModulesToUninstallIfEmulated();
            HashSet hashSet = new HashSet();
            Iterator it = verifiedSplits.iterator();
            while (it.hasNext()) {
                String splitId = ((SplitFileInfo) it.next()).splitId();
                if (asList.contains(splitId) || modulesToUninstallIfEmulated.contains(SplitInstallInfoProvider.getModuleName(splitId))) {
                    hashSet.add(splitId);
                    it.remove();
                }
            }
            if (z) {
                deleteSplitsSync(hashSet);
            } else if (!hashSet.isEmpty()) {
                NativeLibraryPathListMutex.get().execute(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, hashSet, 14, (char[]) null));
            }
            HashSet hashSet2 = new HashSet();
            Iterator it2 = verifiedSplits.iterator();
            while (it2.hasNext()) {
                String splitId2 = ((SplitFileInfo) it2.next()).splitId();
                if (!SplitInstallInfoProvider.isConfigSplit(splitId2)) {
                    hashSet2.add(splitId2);
                }
            }
            for (String str : asList) {
                if (!SplitInstallInfoProvider.isConfigSplit(str)) {
                    hashSet2.add(str);
                }
            }
            HashSet<SplitFileInfo> hashSet3 = new HashSet(verifiedSplits.size());
            for (SplitFileInfo splitFileInfo : verifiedSplits) {
                if (SplitInstallInfoProvider.isBaseConfigSplit(splitFileInfo.splitId()) || hashSet2.contains(SplitInstallInfoProvider.getModuleName(splitFileInfo.splitId()))) {
                    hashSet3.add(splitFileInfo);
                }
            }
            final NativeLibraryExtractor nativeLibraryExtractor = new NativeLibraryExtractor(this.storage);
            int i = Build.VERSION.SDK_INT;
            if (i != 26) {
                if (i == 27 && Build.VERSION.PREVIEW_SDK_INT == 0) {
                    classLoaderInjectorV23 = new ClassLoaderInjectorV23(3);
                }
                classLoaderInjectorV23 = new ClassLoaderInjectorV23(4);
            } else {
                classLoaderInjectorV23 = new ClassLoaderInjectorV23(2);
            }
            ClassLoader classLoader = context.getClassLoader();
            if (z) {
                FileStorage fileStorage2 = nativeLibraryExtractor.fileStorage;
                Set<SplitFileInfo> verifiedSplits2 = fileStorage2.verifiedSplits();
                ArrayList<String> arrayList = new ArrayList();
                File[] listFiles = fileStorage2.directoryForNativeLibraries().listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (file.isDirectory()) {
                            arrayList.add(file.getName());
                        }
                    }
                }
                for (String str2 : arrayList) {
                    Iterator it3 = verifiedSplits2.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (((SplitFileInfo) it3.next()).splitId().equals(str2)) {
                                break;
                            }
                        } else {
                            String.format("NativeLibraryExtractor: extracted split '%s' has no corresponding split; deleting", str2);
                            FileStorage.deleteRecursively(nativeLibraryExtractor.fileStorage.directoryForNativeLibrariesForSplit(str2));
                            break;
                        }
                    }
                }
                HashSet hashSet4 = new HashSet();
                for (final SplitFileInfo splitFileInfo2 : verifiedSplits2) {
                    final HashSet hashSet5 = new HashSet();
                    NativeLibraryExtractor.forAllUsableNativeLibrariesInApk(splitFileInfo2, new NativeLibraryExtractor.NativeLibrariesAction() { // from class: com.google.android.play.core.splitcompat.NativeLibraryExtractor.2
                        @Override // com.google.android.play.core.splitcompat.NativeLibraryExtractor.NativeLibrariesAction
                        public final void perform(final ZipFile zipFile3, Set set) {
                            final HashSet hashSet6 = new HashSet();
                            final SplitFileInfo splitFileInfo3 = splitFileInfo2;
                            nativeLibraryExtractor.forEachNativeLibraryInApk(splitFileInfo3, set, new NativeLibraryAction() { // from class: com.google.android.play.core.splitcompat.NativeLibraryExtractor.3
                                @Override // com.google.android.play.core.splitcompat.NativeLibraryExtractor.NativeLibraryAction
                                public final void perform$ar$class_merging$ar$class_merging$ar$class_merging(RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration, File file2, boolean z2) {
                                    hashSet6.add(file2);
                                    if (!z2) {
                                        String.format("NativeLibraryExtractor: split '%s' has native library '%s' that does not exist; extracting from '%s!%s' to '%s'", splitFileInfo3.splitId(), remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$modelManagerInterfaceProvider, splitFileInfo3.splitFile().getAbsolutePath(), ((ZipEntry) remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass).getName(), file2.getAbsolutePath());
                                        ZipFile zipFile4 = zipFile3;
                                        Object obj = remoteModelManagerRegistration.RemoteModelManager$RemoteModelManagerRegistration$ar$remoteModelClass;
                                        int i2 = NativeLibraryExtractor.NativeLibraryExtractor$ar$NoOp;
                                        byte[] bArr = new byte[4096];
                                        if (file2.exists()) {
                                            file2.delete();
                                        }
                                        InputStream inputStream = zipFile4.getInputStream((ZipEntry) obj);
                                        try {
                                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                            try {
                                                FileStorage.markReadOnly(file2);
                                                while (true) {
                                                    int read = inputStream.read(bArr);
                                                    if (read <= 0) {
                                                        break;
                                                    } else {
                                                        fileOutputStream.write(bArr, 0, read);
                                                    }
                                                }
                                                fileOutputStream.close();
                                                if (inputStream != null) {
                                                    inputStream.close();
                                                }
                                            } finally {
                                            }
                                        } catch (Throwable th) {
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (Throwable th2) {
                                                    th.addSuppressed(th2);
                                                }
                                            }
                                            throw th;
                                        }
                                    }
                                }
                            });
                            hashSet5.addAll(hashSet6);
                        }
                    });
                    FileStorage fileStorage3 = nativeLibraryExtractor.fileStorage;
                    String splitId3 = splitFileInfo2.splitId();
                    HashSet hashSet6 = new HashSet();
                    File[] listFiles2 = fileStorage3.directoryForNativeLibrariesForSplit(splitId3).listFiles();
                    if (listFiles2 != null) {
                        for (File file2 : listFiles2) {
                            if (file2.isFile()) {
                                hashSet6.add(file2);
                            }
                        }
                    }
                    Iterator it4 = hashSet6.iterator();
                    while (it4.hasNext()) {
                        File file3 = (File) it4.next();
                        if (!hashSet5.contains(file3)) {
                            Iterator it5 = it4;
                            String.format("NativeLibraryExtractor: file '%s' found in split '%s' that is not in the split file '%s'; removing", file3.getAbsolutePath(), splitFileInfo2.splitId(), splitFileInfo2.splitFile().getAbsolutePath());
                            if (file3.getParentFile().getParentFile().equals(nativeLibraryExtractor.fileStorage.directoryForNativeLibraries())) {
                                FileStorage.deleteRecursively(file3);
                                it4 = it5;
                            } else {
                                throw new IllegalStateException("File to remove is not a native library");
                            }
                        }
                    }
                    hashSet4.addAll(hashSet5);
                }
                classLoaderInjectorV23.installNativeLibraries(classLoader, hashSet4);
            } else {
                Iterator it6 = hashSet3.iterator();
                while (it6.hasNext()) {
                    final SplitFileInfo splitFileInfo3 = (SplitFileInfo) it6.next();
                    final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                    final HashSet hashSet7 = new HashSet();
                    NativeLibraryExtractor.forAllUsableNativeLibrariesInApk(splitFileInfo3, new NativeLibraryExtractor.NativeLibrariesAction() { // from class: com.google.android.play.core.splitcompat.NativeLibraryExtractor.1
                        @Override // com.google.android.play.core.splitcompat.NativeLibraryExtractor.NativeLibrariesAction
                        public final void perform(ZipFile zipFile3, Set set) {
                            nativeLibraryExtractor.forEachNativeLibraryInApk(splitFileInfo3, set, new NativeLibraryAction() { // from class: com.google.android.play.core.splitcompat.NativeLibraryExtractor.1.1
                                @Override // com.google.android.play.core.splitcompat.NativeLibraryExtractor.NativeLibraryAction
                                public final void perform$ar$class_merging$ar$class_merging$ar$class_merging(RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration, File file4, boolean z2) {
                                    hashSet7.add(file4);
                                    if (!z2) {
                                        atomicBoolean.set(false);
                                    }
                                }
                            });
                        }
                    });
                    if (true != atomicBoolean.get()) {
                        hashSet7 = null;
                    }
                    if (hashSet7 == null) {
                        it6.remove();
                    } else {
                        classLoaderInjectorV23.installNativeLibraries(classLoader, hashSet7);
                    }
                }
            }
            HashSet hashSet8 = new HashSet();
            for (SplitFileInfo splitFileInfo4 : hashSet3) {
                try {
                    zipFile2 = new ZipFile(splitFileInfo4.splitFile());
                } catch (IOException e) {
                    iOException = e;
                    zipFile = null;
                }
                try {
                    ZipEntry entry = zipFile2.getEntry("classes.dex");
                    zipFile2.close();
                    if (entry != null) {
                        FileStorage fileStorage4 = this.storage;
                        String splitId4 = splitFileInfo4.splitId();
                        File file4 = new File(fileStorage4.versionCodeDirectory(), "dex");
                        FileStorage.makeDir$ar$ds(file4);
                        File checkedFileInside = FileStorage.checkedFileInside(file4, splitId4);
                        FileStorage.makeDir$ar$ds(checkedFileInside);
                        if (!classLoaderInjectorV23.installDexes(classLoader, checkedFileInside, splitFileInfo4.splitFile(), z)) {
                            Log.w("SplitCompat", "split was not installed ".concat(splitFileInfo4.splitFile().toString()));
                        }
                    }
                    hashSet8.add(splitFileInfo4.splitFile());
                } catch (IOException e2) {
                    iOException = e2;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                            throw iOException;
                        } catch (IOException e3) {
                            iOException.addSuppressed(e3);
                            throw iOException;
                        }
                    }
                    throw iOException;
                }
            }
            this.assetPathAdder$ar$class_merging.addResourcePathsForFiles(context, hashSet8);
            HashSet hashSet9 = new HashSet();
            for (SplitFileInfo splitFileInfo5 : hashSet3) {
                if (hashSet8.contains(splitFileInfo5.splitFile())) {
                    splitFileInfo5.splitId();
                    hashSet9.add(splitFileInfo5.splitId());
                } else {
                    splitFileInfo5.splitId();
                }
            }
            synchronized (this.emulatedSplits) {
                this.emulatedSplits.addAll(hashSet9);
            }
        } catch (PackageManager.NameNotFoundException e4) {
            throw new IOException(String.format("Cannot load data for application '%s'", packageName), e4);
        }
    }

    public static boolean install(Context context) {
        return internalInstall(context, false);
    }

    public static boolean installActivity(Context context) {
        SplitCompat splitCompat = (SplitCompat) installed.get();
        if (splitCompat == null) {
            if (context.getApplicationContext() != null) {
                install(context.getApplicationContext());
            }
            return install(context);
        }
        return splitCompat.assetPathAdder$ar$class_merging.addResourcePathsForSplitIdsDisablingStrictMode(context, splitCompat.getEmulatedSplits());
    }

    public static boolean internalInstall(Context context, boolean z) {
        AtomicReference atomicReference = installed;
        boolean ArtificialStackFrames$ar$MethodMerging$dc56d17a_18 = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(atomicReference, new SplitCompat(context));
        SplitCompat splitCompat = (SplitCompat) atomicReference.get();
        if (ArtificialStackFrames$ar$MethodMerging$dc56d17a_18) {
            SplitCompatInterceptorProvider splitCompatInterceptorProvider = SplitCompatInterceptorProvider.INSTANCE;
            SplitCompatInterceptorProvider.interceptor.set(new WorkQueue(context, NativeLibraryPathListMutex.get(), new Verifier(context, splitCompat.storage, new NativeLibraryPathListMutex()), splitCompat.storage, new NativeLibraryPathListMutex()));
            _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(SplitInstallEmulatedSplitsProvider.provider, new RetryingNameResolver.ResolutionResultListener(splitCompat));
            NativeLibraryPathListMutex.get().execute(new FlagStore$$ExternalSyntheticLambda3(context, 19));
        }
        try {
            splitCompat.emulateInstallAll(context, z);
            return true;
        } catch (Exception e) {
            Log.e("SplitCompat", "Error installing additional splits", e);
            return false;
        }
    }

    public final void deleteSplitsSync(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            FileStorage.deleteRecursively(this.storage.fileForVerifiedSplit((String) it.next()));
        }
        SplitInstallSharedPreferences splitInstallSharedPreferences = this.sharedPreferences;
        synchronized (SplitInstallSharedPreferences.class) {
            splitInstallSharedPreferences.getSharedPreferences().edit().putStringSet("modules_to_uninstall_if_emulated", new HashSet()).apply();
        }
    }

    public final Set getEmulatedSplits() {
        HashSet hashSet;
        synchronized (this.emulatedSplits) {
            hashSet = new HashSet(this.emulatedSplits);
        }
        return hashSet;
    }
}
