package org.chromium.net;

import J.N;
import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.X509TrustManagerExtensions;
import android.util.Log;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.chromium.base.ContextUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class X509Util {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String OID_ANY_EKU = "2.5.29.37.0";
    private static final String OID_SERVER_GATED_MICROSOFT = "1.3.6.1.4.1.311.10.3.3";
    private static final String OID_SERVER_GATED_NETSCAPE = "2.16.840.1.113730.4.1";
    private static final String OID_TLS_SERVER_AUTH = "1.3.6.1.5.5.7.3.1";
    private static final String TAG = "X509Util";
    private static CertificateFactory sCertificateFactory;
    private static X509TrustManagerExtensions sDefaultTrustManager;
    private static boolean sLoadedSystemKeyStore;
    private static File sSystemCertificateDirectory;
    private static KeyStore sSystemKeyStore;
    private static Set<Pair<X500Principal, PublicKey>> sSystemTrustAnchorCache;
    private static KeyStore sTestKeyStore;
    private static X509Certificate sTestRoot;
    private static X509TrustManagerExtensions sTestTrustManager;
    private static TrustStorageListener sTrustStorageListener;
    private static final Object sLock = new Object();
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TrustStorageListener extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("android.security.action.TRUST_STORE_CHANGED".equals(intent.getAction())) {
                try {
                    X509Util.reloadDefaultTrustManager();
                    return;
                } catch (KeyStoreException e) {
                    Log.e("cr_".concat(X509Util.TAG), "Unable to reload the default TrustManager", e);
                    return;
                } catch (NoSuchAlgorithmException e2) {
                    Log.e("cr_".concat(X509Util.TAG), "Unable to reload the default TrustManager", e2);
                    return;
                } catch (CertificateException e3) {
                    Log.e("cr_".concat(X509Util.TAG), "Unable to reload the default TrustManager", e3);
                    return;
                }
            }
            if ("android.security.action.KEYCHAIN_CHANGED".equals(intent.getAction())) {
                N.MJdorYDE();
            } else if ("android.security.action.KEY_ACCESS_CHANGED".equals(intent.getAction()) && !intent.getBooleanExtra("android.security.extra.KEY_ACCESSIBLE", false)) {
                N.MJdorYDE();
            }
        }
    }

    public static void addTestRootCertificate(byte[] bArr) {
        X509Certificate createCertificateFromBytes = createCertificateFromBytes(bArr);
        synchronized (sLock) {
            ensureTestInitializedLocked();
            KeyStore keyStore = sTestKeyStore;
            keyStore.setCertificateEntry("root_cert_" + Integer.toString(keyStore.size()), createCertificateFromBytes);
            reloadTestTrustManager();
        }
    }

    private static List<X509Certificate> checkServerTrustedIgnoringRuntimeException(X509TrustManagerExtensions x509TrustManagerExtensions, X509Certificate[] x509CertificateArr, String str, String str2) {
        try {
            return x509TrustManagerExtensions.checkServerTrusted(x509CertificateArr, str, str2);
        } catch (RuntimeException e) {
            Log.e("cr_".concat(TAG), "checkServerTrusted() unexpectedly threw: %s", e);
            throw new CertificateException(e);
        }
    }

    public static void clearTestRootCertificates() {
        synchronized (sLock) {
            ensureTestInitializedLocked();
            try {
                sTestKeyStore.load(null);
                reloadTestTrustManager();
            } catch (IOException unused) {
            }
        }
    }

    public static X509Certificate createCertificateFromBytes(byte[] bArr) {
        ensureInitialized();
        return (X509Certificate) sCertificateFactory.generateCertificate(new ByteArrayInputStream(bArr));
    }

    private static X509TrustManagerExtensions createTrustManager(KeyStore keyStore) {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        try {
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    try {
                        return new X509TrustManagerExtensions((X509TrustManager) trustManager);
                    } catch (IllegalArgumentException e) {
                        Log.e("cr_".concat(TAG), "Error creating trust manager (" + trustManager.getClass().getName() + "): " + e);
                    }
                }
            }
            Log.e("cr_".concat(TAG), "Could not find suitable trust manager");
            return null;
        } catch (RuntimeException e2) {
            Log.e("cr_".concat(TAG), "TrustManagerFactory.getTrustManagers() unexpectedly threw: %s", e2);
            throw new KeyStoreException(e2);
        }
    }

    private static void ensureInitialized() {
        synchronized (sLock) {
            ensureInitializedLocked();
        }
    }

    private static void ensureInitializedLocked() {
        if (sCertificateFactory == null) {
            sCertificateFactory = CertificateFactory.getInstance("X.509");
        }
        if (sDefaultTrustManager == null) {
            sDefaultTrustManager = createTrustManager(null);
        }
        if (!sLoadedSystemKeyStore) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidCAStore");
                sSystemKeyStore = keyStore;
                try {
                    keyStore.load(null);
                } catch (IOException unused) {
                }
                sSystemCertificateDirectory = new File(System.getenv("ANDROID_ROOT") + "/etc/security/cacerts");
            } catch (KeyStoreException unused2) {
            }
            sLoadedSystemKeyStore = true;
        }
        if (sSystemTrustAnchorCache == null) {
            sSystemTrustAnchorCache = new HashSet();
        }
        if (sTrustStorageListener == null) {
            sTrustStorageListener = new TrustStorageListener();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.security.action.KEYCHAIN_CHANGED");
            intentFilter.addAction("android.security.action.KEY_ACCESS_CHANGED");
            intentFilter.addAction("android.security.action.TRUST_STORE_CHANGED");
            ContextUtils.registerProtectedBroadcastReceiver(ContextUtils.sApplicationContext, sTrustStorageListener, intentFilter);
        }
    }

    private static void ensureTestInitializedLocked() {
        if (sTestKeyStore == null) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            sTestKeyStore = keyStore;
            try {
                keyStore.load(null);
            } catch (IOException unused) {
            }
        }
        if (sTestTrustManager == null) {
            sTestTrustManager = createTrustManager(sTestKeyStore);
        }
    }

    public static byte[][] getUserAddedRoots() {
        ArrayList arrayList = new ArrayList();
        synchronized (sLock) {
            try {
                try {
                    ensureInitialized();
                    KeyStore keyStore = sSystemKeyStore;
                    if (keyStore == null) {
                        return new byte[0];
                    }
                    try {
                        Enumeration<String> aliases = keyStore.aliases();
                        while (aliases.hasMoreElements()) {
                            String nextElement = aliases.nextElement();
                            if (nextElement.startsWith("user:")) {
                                try {
                                    Certificate certificate = sSystemKeyStore.getCertificate(nextElement);
                                    if (!(certificate instanceof X509Certificate)) {
                                        Log.w("cr_".concat(TAG), _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(nextElement, "alias: ", " is not a X509 Cert, skipping"));
                                    } else {
                                        arrayList.add(((X509Certificate) certificate).getEncoded());
                                    }
                                } catch (KeyStoreException e) {
                                    Log.e("cr_".concat(TAG), String.format(Locale.US, "Error reading cert with alias %s, error: %s", nextElement), e);
                                } catch (CertificateEncodingException e2) {
                                    Log.e("cr_".concat(TAG), String.format(Locale.US, "Error encoding cert with alias %s, error: %s", nextElement), e2);
                                }
                            }
                        }
                        X509Certificate x509Certificate = sTestRoot;
                        if (x509Certificate != null) {
                            try {
                                arrayList.add(x509Certificate.getEncoded());
                            } catch (CertificateEncodingException e3) {
                                Log.e("cr_".concat(TAG), "Error encoding test root cert, error %s", e3);
                            }
                        }
                        return (byte[][]) arrayList.toArray(new byte[0]);
                    } catch (KeyStoreException e4) {
                        Log.e("cr_".concat(TAG), "Error reading cert aliases: %s", e4);
                        return new byte[0];
                    }
                } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
                    return new byte[0];
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static String hashPrincipal(X500Principal x500Principal) {
        byte[] digest = MessageDigest.getInstance("MD5").digest(x500Principal.getEncoded());
        char[] cArr = new char[8];
        for (int i = 0; i < 4; i++) {
            char[] cArr2 = HEX_DIGITS;
            byte b = digest[3 - i];
            int i2 = i + i;
            cArr[i2] = cArr2[(b >> 4) & 15];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    private static boolean isKnownRoot(X509Certificate x509Certificate) {
        if (sSystemKeyStore == null) {
            return false;
        }
        Pair<X500Principal, PublicKey> pair = new Pair<>(x509Certificate.getSubjectX500Principal(), x509Certificate.getPublicKey());
        if (sSystemTrustAnchorCache.contains(pair)) {
            return true;
        }
        String hashPrincipal = hashPrincipal(x509Certificate.getSubjectX500Principal());
        int i = 0;
        while (true) {
            String str = hashPrincipal + "." + i;
            if (!new File(sSystemCertificateDirectory, str).exists()) {
                return false;
            }
            Certificate certificate = sSystemKeyStore.getCertificate("system:".concat(str));
            if (certificate != null) {
                if (!(certificate instanceof X509Certificate)) {
                    Log.e("cr_".concat(TAG), "Anchor " + str + " not an X509Certificate: " + certificate.getClass().getName());
                } else {
                    X509Certificate x509Certificate2 = (X509Certificate) certificate;
                    if (x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getSubjectX500Principal()) && x509Certificate.getPublicKey().equals(x509Certificate2.getPublicKey())) {
                        sSystemTrustAnchorCache.add(pair);
                        return true;
                    }
                }
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reloadDefaultTrustManager() {
        synchronized (sLock) {
            sDefaultTrustManager = null;
            sSystemTrustAnchorCache = null;
            ensureInitializedLocked();
        }
        N.M6C2IQIc();
    }

    private static void reloadTestTrustManager() {
        ensureTestInitializedLocked();
        sTestTrustManager = createTrustManager(sTestKeyStore);
    }

    public static void setTestRootCertificateForBuiltin(byte[] bArr) {
        X509Certificate createCertificateFromBytes = createCertificateFromBytes(bArr);
        synchronized (sLock) {
            sTestRoot = createCertificateFromBytes;
        }
    }

    static boolean verifyKeyUsage(X509Certificate x509Certificate) {
        List<String> extendedKeyUsage;
        try {
            extendedKeyUsage = x509Certificate.getExtendedKeyUsage();
        } catch (NullPointerException unused) {
        }
        if (extendedKeyUsage == null) {
            return true;
        }
        for (String str : extendedKeyUsage) {
            if (str.equals(OID_TLS_SERVER_AUTH) || str.equals(OID_ANY_EKU) || str.equals(OID_SERVER_GATED_NETSCAPE) || str.equals(OID_SERVER_GATED_MICROSOFT)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0092 A[Catch: all -> 0x00aa, TryCatch #5 {, blocks: (B:34:0x0063, B:36:0x0067, B:37:0x006c, B:40:0x006e, B:41:0x008c, B:43:0x0092, B:44:0x00a3, B:45:0x00a8, B:50:0x0074, B:59:0x0079, B:55:0x0081, B:56:0x008a), top: B:33:0x0063, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0081 A[Catch: all -> 0x00aa, TRY_ENTER, TryCatch #5 {, blocks: (B:34:0x0063, B:36:0x0067, B:37:0x006c, B:40:0x006e, B:41:0x008c, B:43:0x0092, B:44:0x00a3, B:45:0x00a8, B:50:0x0074, B:59:0x0079, B:55:0x0081, B:56:0x008a), top: B:33:0x0063, inners: #8 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.net.AndroidCertVerifyResult verifyServerCertificates(byte[][] r7, java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.X509Util.verifyServerCertificates(byte[][], java.lang.String, java.lang.String):org.chromium.net.AndroidCertVerifyResult");
    }
}
