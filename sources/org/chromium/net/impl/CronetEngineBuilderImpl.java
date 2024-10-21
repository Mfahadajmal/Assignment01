package org.chromium.net.impl;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import android.util.Base64;
import java.io.File;
import java.net.IDN;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.chromium.net.ApiVersion;
import org.chromium.net.CronetEngine;
import org.chromium.net.ICronetEngineBuilder;
import org.chromium.net.impl.CronetLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CronetEngineBuilderImpl extends ICronetEngineBuilder {
    private static final Pattern INVALID_PKP_HOST_NAME = Pattern.compile("^[0-9\\.]*$");
    static final int sApiLevel;
    public final Context mApplicationContext;
    public boolean mBrotiEnabled;
    public String mExperimentalOptions;
    public boolean mHttp2Enabled;
    public long mHttpCacheMaxSize;
    public HttpCacheMode mHttpCacheMode;
    protected final CronetLogger mLogger;
    public boolean mNetworkQualityEstimatorEnabled;
    public boolean mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
    public boolean mQuicEnabled;
    public final CronetLogger.CronetSource mSource;
    public String mStoragePath;
    public String mUserAgent;
    public final List mQuicHints = new LinkedList();
    public final List mPkps = new LinkedList();
    private int mThreadPriority = 20;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum HttpCacheMode {
        DISABLED(0, false),
        DISK(1, true),
        DISK_NO_HTTP(1, false),
        MEMORY(2, true);

        public final boolean mContentCacheEnabled;
        public final int mType;

        HttpCacheMode(int i, boolean z) {
            this.mContentCacheEnabled = z;
            this.mType = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Pkp {
        public final Object CronetEngineBuilderImpl$Pkp$ar$mExpirationDate;
        public final Object CronetEngineBuilderImpl$Pkp$ar$mHashes;
        public final CharSequence CronetEngineBuilderImpl$Pkp$ar$mHost;
        public final boolean mIncludeSubdomains;

        public Pkp(CharSequence charSequence, CharSequence charSequence2, boolean z, Runnable runnable) {
            this.CronetEngineBuilderImpl$Pkp$ar$mExpirationDate = charSequence;
            this.CronetEngineBuilderImpl$Pkp$ar$mHost = charSequence2;
            this.mIncludeSubdomains = z;
            this.CronetEngineBuilderImpl$Pkp$ar$mHashes = runnable;
        }

        public Pkp(String str, byte[][] bArr, boolean z, Date date) {
            this.CronetEngineBuilderImpl$Pkp$ar$mHost = str;
            this.CronetEngineBuilderImpl$Pkp$ar$mHashes = bArr;
            this.mIncludeSubdomains = z;
            this.CronetEngineBuilderImpl$Pkp$ar$mExpirationDate = date;
        }

        public Pkp(CharSequence charSequence, boolean z, Runnable runnable) {
            this(charSequence, "", z, runnable);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class QuicHint {
        public final Object CronetEngineBuilderImpl$QuicHint$ar$mHost;
        public final int mAlternatePort;
        public final int mPort;

        public QuicHint() {
            this.CronetEngineBuilderImpl$QuicHint$ar$mHost = new QuicHint[256];
            this.mAlternatePort = 0;
            this.mPort = 0;
        }

        public QuicHint(int i, int i2) {
            this.CronetEngineBuilderImpl$QuicHint$ar$mHost = null;
            this.mAlternatePort = i;
            int i3 = i2 & 7;
            this.mPort = i3 == 0 ? 8 : i3;
        }

        public QuicHint(int i, String str, int i2) {
            this.mAlternatePort = i;
            this.CronetEngineBuilderImpl$QuicHint$ar$mHost = str;
            this.mPort = i2;
        }

        public QuicHint(String str, int i, int i2) {
            this.CronetEngineBuilderImpl$QuicHint$ar$mHost = str;
            this.mPort = i;
            this.mAlternatePort = i2;
        }
    }

    static {
        int i;
        if (Integer.parseInt(ApiVersion.getCronetVersion().split("\\.")[0]) < 59) {
            i = 3;
        } else {
            i = 31;
        }
        sApiLevel = i;
    }

    public CronetEngineBuilderImpl(Context context, CronetLogger.CronetSource cronetSource) {
        long uptimeMillis = SystemClock.uptimeMillis();
        Context applicationContext = context.getApplicationContext();
        this.mApplicationContext = applicationContext;
        this.mSource = cronetSource;
        this.mLogger = CronetLoggerFactory.createLogger(applicationContext, cronetSource);
        try {
            this.mQuicEnabled = true;
            this.mHttp2Enabled = true;
            this.mBrotiEnabled = false;
            enableHttpCache$ar$ds(0, 0L);
            this.mNetworkQualityEstimatorEnabled = false;
            this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = true;
            maybeLogCronetEngineBuilderInitializedInfo(uptimeMillis, true);
        } catch (Throwable th) {
            maybeLogCronetEngineBuilderInitializedInfo(uptimeMillis, false);
            throw th;
        }
    }

    private final void maybeLogCronetEngineBuilderInitializedInfo(long j, boolean z) {
        if (sApiLevel >= 30) {
            return;
        }
        CronetLogger.CronetEngineBuilderInitializedInfo cronetEngineBuilderInitializedInfo = new CronetLogger.CronetEngineBuilderInitializedInfo();
        cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$creationSuccessful = false;
        try {
            cronetEngineBuilderInitializedInfo.author$ar$edu = 2;
            cronetEngineBuilderInitializedInfo.uid = Process.myUid();
            cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$implVersion = new CronetLogger.CronetVersion(ImplVersion.getCronetVersion());
            cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$source = this.mSource;
            cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$apiVersion = new CronetLogger.CronetVersion(ApiVersion.getCronetVersion());
            cronetEngineBuilderInitializedInfo.cronetInitializationRef = getLogCronetInitializationRef();
            cronetEngineBuilderInitializedInfo.CronetLogger$CronetEngineBuilderInitializedInfo$ar$creationSuccessful = Boolean.valueOf(z);
        } finally {
            cronetEngineBuilderInitializedInfo.engineBuilderCreatedLatencyMillis = (int) (SystemClock.uptimeMillis() - j);
            this.mLogger.logCronetEngineBuilderInitializedInfo(cronetEngineBuilderInitializedInfo);
        }
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder addPublicKeyPins(String str, Set set, boolean z, Date date) {
        addPublicKeyPins$ar$ds(str, set, z, date);
        return this;
    }

    public final void addPublicKeyPins$ar$ds(String str, Set set, boolean z, Date date) {
        str.getClass();
        set.getClass();
        date.getClass();
        if (!INVALID_PKP_HOST_NAME.matcher(str).matches()) {
            if (str.length() <= 255) {
                try {
                    String ascii = IDN.toASCII(str, 2);
                    HashMap hashMap = new HashMap();
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        byte[] bArr = (byte[]) it.next();
                        if (bArr != null && bArr.length == 32) {
                            hashMap.put(Base64.encodeToString(bArr, 0), bArr);
                        } else {
                            throw new IllegalArgumentException("Public key pin is invalid");
                        }
                    }
                    this.mPkps.add(new Pkp(ascii, (byte[][]) hashMap.values().toArray(new byte[hashMap.size()]), z, date));
                    return;
                } catch (IllegalArgumentException unused) {
                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Hostname ", " is illegal. The name of the host does not comply with RFC 1122 and RFC 1123."));
                }
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Hostname ", " is too long. The name of the host does not comply with RFC 1122 and RFC 1123."));
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Hostname ", " is illegal. A hostname should not consist of digits and/or dots only."));
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder addQuicHint(String str, int i, int i2) {
        addQuicHint$ar$ds(str, i, i2);
        return this;
    }

    public final void addQuicHint$ar$ds(String str, int i, int i2) {
        if (!str.contains("/")) {
            this.mQuicHints.add(new QuicHint(str, i, i2));
            return;
        }
        throw new IllegalArgumentException("Illegal QUIC Hint Host: ".concat(String.valueOf(str)));
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* synthetic */ ICronetEngineBuilder enableBrotli(boolean z) {
        this.mBrotiEnabled = z;
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* synthetic */ ICronetEngineBuilder enableHttp2(boolean z) {
        this.mHttp2Enabled = z;
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder enableHttpCache(int i, long j) {
        enableHttpCache$ar$ds(i, j);
        return this;
    }

    public final void enableHttpCache$ar$ds(int i, long j) {
        HttpCacheMode httpCacheMode;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        httpCacheMode = HttpCacheMode.DISK;
                    } else {
                        throw new IllegalArgumentException("Unknown public builder cache mode");
                    }
                } else {
                    httpCacheMode = HttpCacheMode.DISK_NO_HTTP;
                }
            } else {
                httpCacheMode = HttpCacheMode.MEMORY;
            }
        } else {
            httpCacheMode = HttpCacheMode.DISABLED;
        }
        if (httpCacheMode.mType == 1 && this.mStoragePath == null) {
            throw new IllegalArgumentException("Storage path must be set");
        }
        this.mHttpCacheMode = httpCacheMode;
        this.mHttpCacheMaxSize = j;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* synthetic */ ICronetEngineBuilder enableNetworkQualityEstimator(boolean z) {
        this.mNetworkQualityEstimatorEnabled = z;
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* synthetic */ ICronetEngineBuilder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean z) {
        this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = z;
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* synthetic */ ICronetEngineBuilder enableQuic(boolean z) {
        this.mQuicEnabled = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getDefaultQuicUserAgentId() {
        if (this.mQuicEnabled) {
            Context context = this.mApplicationContext;
            StringBuilder sb = new StringBuilder();
            sb.append(context.getPackageName());
            UserAgent.appendCronetVersion(sb);
            return sb.toString();
        }
        return "";
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final String getDefaultUserAgent() {
        return UserAgent.from(this.mApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.chromium.net.ICronetEngineBuilder
    public long getLogCronetInitializationRef() {
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int httpCacheMode() {
        return this.mHttpCacheMode.mType;
    }

    public VersionSafeCallbacks$LibraryLoader libraryLoader() {
        return null;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* synthetic */ ICronetEngineBuilder setExperimentalOptions(String str) {
        this.mExperimentalOptions = str;
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder setLibraryLoader(CronetEngine.Builder.LibraryLoader libraryLoader) {
        setLibraryLoader$ar$ds(libraryLoader);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder setStoragePath(String str) {
        setStoragePath$ar$ds(str);
        return this;
    }

    public final void setStoragePath$ar$ds(String str) {
        if (new File(str).isDirectory()) {
            this.mStoragePath = str;
            return;
        }
        throw new IllegalArgumentException("Storage path must be set to existing directory");
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder setThreadPriority(int i) {
        setThreadPriority$ar$ds(i);
        return this;
    }

    public final void setThreadPriority$ar$ds(int i) {
        if (i <= 19 && i >= -20) {
            this.mThreadPriority = i;
            return;
        }
        throw new IllegalArgumentException("Thread priority invalid");
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* synthetic */ ICronetEngineBuilder setUserAgent(String str) {
        this.mUserAgent = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int threadPriority(int i) {
        int i2 = this.mThreadPriority;
        if (i2 == 20) {
            return i;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final CronetLogger.CronetEngineBuilderInfo toLoggerInfo() {
        int i;
        boolean z = this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
        boolean z2 = this.mQuicEnabled;
        boolean z3 = this.mHttp2Enabled;
        boolean z4 = this.mBrotiEnabled;
        HttpCacheMode httpCacheMode = this.mHttpCacheMode;
        HttpCacheMode httpCacheMode2 = HttpCacheMode.DISABLED;
        int ordinal = httpCacheMode.ordinal();
        if (ordinal != 0) {
            i = 3;
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal == 3) {
                        i = 1;
                    } else {
                        throw new IllegalArgumentException("Unknown internal builder cache mode");
                    }
                } else {
                    i = 2;
                }
            }
        } else {
            i = 0;
        }
        return new CronetLogger.CronetEngineBuilderInfo(z, z2, z3, z4, i, this.mExperimentalOptions, this.mNetworkQualityEstimatorEnabled, threadPriority(10), getLogCronetInitializationRef());
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder enableSdch(boolean z) {
        return this;
    }

    public void setLibraryLoader$ar$ds(CronetEngine.Builder.LibraryLoader libraryLoader) {
    }
}
