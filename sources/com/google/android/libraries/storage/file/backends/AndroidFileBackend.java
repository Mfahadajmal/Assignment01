package com.google.android.libraries.storage.file.backends;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.android.libraries.storage.file.common.FileStorageUnavailableException;
import com.google.android.libraries.storage.file.common.MalformedUriException;
import com.google.android.libraries.storage.file.common.internal.LiteTransformFragments;
import com.google.android.libraries.storage.file.spi.Backend;
import com.google.android.libraries.storage.file.spi.ForwardingBackend;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidFileBackend extends ForwardingBackend {
    private final Context context;
    private String lazyDpsDataDirPath;
    private final Object lock = new Object();
    private final Backend backend = new JavaFileBackend(null);
    private final CurrentProcess directBootChecker$ar$class_merging$ar$class_merging$ar$class_merging = new CurrentProcess();

    public AndroidFileBackend(MetricsLogger metricsLogger) {
        this.context = (Context) metricsLogger.MetricsLogger$ar$logSessionId;
    }

    private final boolean isRemoteAuthority(Uri uri) {
        if (!TextUtils.isEmpty(uri.getAuthority()) && !this.context.getPackageName().equals(uri.getAuthority())) {
            return true;
        }
        return false;
    }

    private static final void throwIfRemoteBackendUnavailable$ar$ds() {
        throw new FileStorageUnavailableException("Android backend cannot perform remote operations without a remote backend");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend
    public final Backend delegate() {
        return this.backend;
    }

    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend, com.google.android.libraries.storage.file.spi.Backend
    public final boolean exists(Uri uri) {
        if (!isRemoteAuthority(uri)) {
            return delegate().exists(rewriteUri(uri));
        }
        throwIfRemoteBackendUnavailable$ar$ds();
        throw null;
    }

    @Override // com.google.android.libraries.storage.file.spi.Backend
    public final String name() {
        return "android";
    }

    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend, com.google.android.libraries.storage.file.spi.Backend
    public final InputStream openForRead(Uri uri) {
        if (!isRemoteAuthority(uri)) {
            return delegate().openForRead(rewriteUri(uri));
        }
        throwIfRemoteBackendUnavailable$ar$ds();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend
    public final Uri rewriteUri(Uri uri) {
        if (!isRemoteAuthority(uri)) {
            File file = toFile(uri);
            MetricsLogger metricsLogger = new MetricsLogger((byte[]) null);
            ((Uri.Builder) metricsLogger.MetricsLogger$ar$loggerProvider$ar$class_merging).path(file.getAbsolutePath());
            return ((Uri.Builder) metricsLogger.MetricsLogger$ar$loggerProvider$ar$class_merging).encodedFragment(LiteTransformFragments.joinTransformSpecs(((ImmutableList.Builder) metricsLogger.MetricsLogger$ar$logSessionId).build())).build();
        }
        throw new MalformedUriException("Operation across authorities is not allowed.");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.libraries.storage.file.spi.ForwardingBackend, com.google.android.libraries.storage.file.spi.Backend
    public final File toFile(Uri uri) {
        char c;
        Context createDeviceProtectedStorageContext;
        File filesDir;
        String str;
        Context createDeviceProtectedStorageContext2;
        Context createDeviceProtectedStorageContext3;
        boolean z;
        Account account;
        if (!isRemoteAuthority(uri)) {
            AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor(this.context, (byte[]) null);
            if (uri.getScheme().equals("android")) {
                if (!uri.getPathSegments().isEmpty()) {
                    if (TextUtils.isEmpty(uri.getQuery())) {
                        ArrayList arrayList = new ArrayList(uri.getPathSegments());
                        String str2 = (String) arrayList.get(0);
                        switch (str2.hashCode()) {
                            case -1820761141:
                                if (str2.equals("external")) {
                                    c = 5;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 94416770:
                                if (str2.equals("cache")) {
                                    c = 3;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 97434231:
                                if (str2.equals("files")) {
                                    c = 2;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 835260319:
                                if (str2.equals("managed")) {
                                    c = 4;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 988548496:
                                if (str2.equals("directboot-cache")) {
                                    c = 1;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 991565957:
                                if (str2.equals("directboot-files")) {
                                    c = 0;
                                    break;
                                }
                                c = 65535;
                                break;
                            default:
                                c = 65535;
                                break;
                        }
                        if (c == 0) {
                            createDeviceProtectedStorageContext = ((Context) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).createDeviceProtectedStorageContext();
                            filesDir = createDeviceProtectedStorageContext.getFilesDir();
                        } else if (c == 1) {
                            createDeviceProtectedStorageContext3 = ((Context) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).createDeviceProtectedStorageContext();
                            filesDir = createDeviceProtectedStorageContext3.getCacheDir();
                        } else if (c != 2) {
                            if (c != 3) {
                                if (c != 4) {
                                    if (c == 5) {
                                        filesDir = ((Context) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).getExternalFilesDir(null);
                                    } else {
                                        throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
                                    }
                                } else {
                                    File file = new File(CurrentProcess.getFilesDirWithPreNWorkaround((Context) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker), "managed");
                                    if (arrayList.size() >= 3) {
                                        try {
                                            String str3 = (String) arrayList.get(2);
                                            Account account2 = AccountSerialization.SHARED_ACCOUNT;
                                            if ("shared".equals(str3)) {
                                                account = AccountSerialization.SHARED_ACCOUNT;
                                            } else {
                                                int indexOf = str3.indexOf(58);
                                                if (indexOf >= 0) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                DisplayStats.checkArgument(z, "Malformed account", new Object[0]);
                                                account = new Account(str3.substring(indexOf + 1), str3.substring(0, indexOf));
                                            }
                                            if (!AccountSerialization.isSharedAccount(account)) {
                                                throw new MalformedUriException("AccountManager cannot be null");
                                            }
                                        } catch (IllegalArgumentException e) {
                                            throw new MalformedUriException(e);
                                        }
                                    }
                                    filesDir = file;
                                }
                            } else {
                                filesDir = ((Context) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).getCacheDir();
                            }
                        } else {
                            filesDir = CurrentProcess.getFilesDirWithPreNWorkaround((Context) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker);
                        }
                        File file2 = new File(filesDir, TextUtils.join(File.separator, arrayList.subList(1, arrayList.size())));
                        if (!DirectBootUtils.isUserUnlocked(this.context)) {
                            synchronized (this.lock) {
                                if (this.lazyDpsDataDirPath == null) {
                                    createDeviceProtectedStorageContext2 = this.context.createDeviceProtectedStorageContext();
                                    this.lazyDpsDataDirPath = CurrentProcess.getFilesDirWithPreNWorkaround(createDeviceProtectedStorageContext2).getParentFile().getAbsolutePath();
                                }
                                str = this.lazyDpsDataDirPath;
                            }
                            if (!file2.getAbsolutePath().startsWith(str)) {
                                throw new FileStorageUnavailableException("Cannot access credential-protected data from direct boot");
                            }
                        }
                        return file2;
                    }
                    throw new MalformedUriException("Did not expect uri to have query");
                }
                throw new MalformedUriException(String.format("Path must start with a valid logical location: %s", uri));
            }
            throw new MalformedUriException("Scheme must be 'android'");
        }
        throw new IOException("operation is not permitted in other authorities.");
    }
}
