package androidx.work.impl.utils;

import _COROUTINE._BOUNDARY;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.android.p2putils.ApkSignatureSchemeV2Verifier$DataSource;
import com.android.p2putils.ApkSignatureSchemeV2Verifier$SignatureInfo;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkApi21 {
    private static byte[][] computeContentDigests(int[] iArr, ApkSignatureSchemeV2Verifier$DataSource[] apkSignatureSchemeV2Verifier$DataSourceArr) {
        long j;
        int i;
        int length;
        long j2 = 0;
        int i2 = 0;
        long j3 = 0;
        while (true) {
            j = 1048576;
            if (i2 >= 3) {
                break;
            }
            j3 += (apkSignatureSchemeV2Verifier$DataSourceArr[i2].size() + 1048575) / 1048576;
            i2++;
        }
        if (j3 < 2097151) {
            byte[][] bArr = new byte[iArr.length];
            int i3 = 0;
            while (true) {
                length = iArr.length;
                if (i3 >= length) {
                    break;
                }
                int i4 = (int) j3;
                byte[] bArr2 = new byte[(getContentDigestAlgorithmOutputSizeBytes(iArr[i3]) * i4) + 5];
                bArr2[0] = 90;
                setUnsignedInt32LittleEndian$ar$ds(i4, bArr2);
                bArr[i3] = bArr2;
                i3++;
            }
            byte[] bArr3 = new byte[5];
            bArr3[0] = -91;
            MessageDigest[] messageDigestArr = new MessageDigest[length];
            for (int i5 = 0; i5 < iArr.length; i5++) {
                String contentDigestAlgorithmJcaDigestAlgorithm = getContentDigestAlgorithmJcaDigestAlgorithm(iArr[i5]);
                try {
                    messageDigestArr[i5] = MessageDigest.getInstance(contentDigestAlgorithmJcaDigestAlgorithm);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(contentDigestAlgorithmJcaDigestAlgorithm.concat(" digest not supported"), e);
                }
            }
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            for (i = 3; i6 < i; i = 3) {
                ApkSignatureSchemeV2Verifier$DataSource apkSignatureSchemeV2Verifier$DataSource = apkSignatureSchemeV2Verifier$DataSourceArr[i6];
                int i9 = i7;
                long j4 = j2;
                int i10 = i6;
                long size = apkSignatureSchemeV2Verifier$DataSource.size();
                while (size > j2) {
                    int min = (int) Math.min(size, j);
                    setUnsignedInt32LittleEndian$ar$ds(min, bArr3);
                    for (int i11 = 0; i11 < length; i11++) {
                        messageDigestArr[i11].update(bArr3);
                    }
                    try {
                        apkSignatureSchemeV2Verifier$DataSource.feedIntoMessageDigests(messageDigestArr, j4, min);
                        int i12 = 0;
                        while (i12 < iArr.length) {
                            int i13 = iArr[i12];
                            byte[] bArr4 = bArr[i12];
                            int contentDigestAlgorithmOutputSizeBytes = getContentDigestAlgorithmOutputSizeBytes(i13);
                            ApkSignatureSchemeV2Verifier$DataSource apkSignatureSchemeV2Verifier$DataSource2 = apkSignatureSchemeV2Verifier$DataSource;
                            MessageDigest messageDigest = messageDigestArr[i12];
                            byte[] bArr5 = bArr3;
                            int digest = messageDigest.digest(bArr4, (i9 * contentDigestAlgorithmOutputSizeBytes) + 5, contentDigestAlgorithmOutputSizeBytes);
                            if (digest == contentDigestAlgorithmOutputSizeBytes) {
                                i12++;
                                apkSignatureSchemeV2Verifier$DataSource = apkSignatureSchemeV2Verifier$DataSource2;
                                bArr3 = bArr5;
                            } else {
                                throw new RuntimeException("Unexpected output size of " + messageDigest.getAlgorithm() + " digest: " + digest);
                            }
                        }
                        ApkSignatureSchemeV2Verifier$DataSource apkSignatureSchemeV2Verifier$DataSource3 = apkSignatureSchemeV2Verifier$DataSource;
                        long j5 = min;
                        j4 += j5;
                        size -= j5;
                        i9++;
                        apkSignatureSchemeV2Verifier$DataSource = apkSignatureSchemeV2Verifier$DataSource3;
                        j2 = 0;
                        j = 1048576;
                    } catch (IOException e2) {
                        throw new DigestException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i8, i9, "Failed to digest chunk #", " of section #"), e2);
                    }
                }
                i7 = i9;
                i8++;
                i6 = i10 + 1;
                j2 = 0;
                j = 1048576;
            }
            byte[][] bArr6 = new byte[iArr.length];
            for (int i14 = 0; i14 < iArr.length; i14++) {
                int i15 = iArr[i14];
                byte[] bArr7 = bArr[i14];
                String contentDigestAlgorithmJcaDigestAlgorithm2 = getContentDigestAlgorithmJcaDigestAlgorithm(i15);
                try {
                    bArr6[i14] = MessageDigest.getInstance(contentDigestAlgorithmJcaDigestAlgorithm2).digest(bArr7);
                } catch (NoSuchAlgorithmException e3) {
                    throw new RuntimeException(contentDigestAlgorithmJcaDigestAlgorithm2.concat(" digest not supported"), e3);
                }
            }
            return bArr6;
        }
        throw new DigestException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j3, "Too many chunks: "));
    }

    public static ByteBuffer getByteBuffer(ByteBuffer byteBuffer, int i) {
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i2 = i + position;
        if (i2 >= position && i2 <= limit) {
            byteBuffer.limit(i2);
            try {
                ByteBuffer slice = byteBuffer.slice();
                slice.order(byteBuffer.order());
                byteBuffer.position(i2);
                return slice;
            } finally {
                byteBuffer.limit(limit);
            }
        }
        throw new BufferUnderflowException();
    }

    private static String getContentDigestAlgorithmJcaDigestAlgorithm(int i) {
        if (i != 1) {
            if (i == 2) {
                return "SHA-512";
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unknown content digest algorthm: "));
        }
        return "SHA-256";
    }

    private static int getContentDigestAlgorithmOutputSizeBytes(int i) {
        if (i != 1) {
            if (i == 2) {
                return 64;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unknown content digest algorthm: "));
        }
        return 32;
    }

    private static ByteBuffer getLengthPrefixedSlice(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() >= 4) {
            int i = byteBuffer.getInt();
            if (i >= 0) {
                if (i <= byteBuffer.remaining()) {
                    return getByteBuffer(byteBuffer, i);
                }
                throw new IOException("Length-prefixed field longer than remaining buffer. Field length: " + i + ", remaining: " + byteBuffer.remaining());
            }
            throw new IllegalArgumentException("Negative length");
        }
        throw new IOException("Remaining buffer too short to contain length of length-prefixed field. Remaining: " + byteBuffer.remaining());
    }

    public static final NetworkCapabilities getNetworkCapabilitiesCompat(ConnectivityManager connectivityManager, Network network) {
        connectivityManager.getClass();
        return connectivityManager.getNetworkCapabilities(network);
    }

    private static int getSignatureAlgorithmContentDigestAlgorithm(int i) {
        if (i != 513) {
            if (i != 514) {
                if (i != 769) {
                    switch (i) {
                        case 257:
                        case 259:
                            return 1;
                        case 258:
                        case 260:
                            return 2;
                        default:
                            throw new IllegalArgumentException("Unknown signature algorithm: 0x".concat(String.valueOf(Long.toHexString(i))));
                    }
                }
                return 1;
            }
            return 2;
        }
        return 1;
    }

    public static final boolean hasCapabilityCompat(NetworkCapabilities networkCapabilities, int i) {
        networkCapabilities.getClass();
        return networkCapabilities.hasCapability(i);
    }

    private static byte[] readLengthPrefixedByteArray(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        if (i >= 0) {
            if (i <= byteBuffer.remaining()) {
                byte[] bArr = new byte[i];
                byteBuffer.get(bArr);
                return bArr;
            }
            throw new IOException("Underflow while reading length-prefixed value. Length: " + i + ", available: " + byteBuffer.remaining());
        }
        throw new IOException("Negative length");
    }

    private static void setUnsignedInt32LittleEndian$ar$ds(int i, byte[] bArr) {
        bArr[1] = (byte) (i & PrivateKeyType.INVALID);
        bArr[2] = (byte) ((i >>> 8) & PrivateKeyType.INVALID);
        bArr[3] = (byte) ((i >>> 16) & PrivateKeyType.INVALID);
        bArr[4] = (byte) (i >> 24);
    }

    public static final void unregisterNetworkCallbackCompat(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        connectivityManager.getClass();
        networkCallback.getClass();
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }

    public static X509Certificate[][] verify(FileChannel fileChannel, ApkSignatureSchemeV2Verifier$SignatureInfo apkSignatureSchemeV2Verifier$SignatureInfo) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try {
                ByteBuffer lengthPrefixedSlice = getLengthPrefixedSlice(apkSignatureSchemeV2Verifier$SignatureInfo.signatureBlock);
                int i = 0;
                while (lengthPrefixedSlice.hasRemaining()) {
                    i++;
                    try {
                        arrayList.add(verifySigner(getLengthPrefixedSlice(lengthPrefixedSlice), hashMap, certificateFactory));
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        throw new SecurityException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Failed to parse/verify signer #", " block"), e);
                    }
                }
                if (i > 0) {
                    if (!hashMap.isEmpty()) {
                        verifyIntegrity(hashMap, fileChannel, apkSignatureSchemeV2Verifier$SignatureInfo.apkSigningBlockOffset, apkSignatureSchemeV2Verifier$SignatureInfo.centralDirOffset, apkSignatureSchemeV2Verifier$SignatureInfo.eocdOffset, apkSignatureSchemeV2Verifier$SignatureInfo.eocd);
                        return (X509Certificate[][]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                    }
                    throw new SecurityException("No content digests found");
                }
                throw new SecurityException("No signers found");
            } catch (IOException e2) {
                throw new SecurityException("Failed to read list of signers", e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }

    private static void verifyIntegrity(Map map, final FileChannel fileChannel, final long j, final long j2, long j3, ByteBuffer byteBuffer) {
        if (!map.isEmpty()) {
            final long j4 = 0;
            ApkSignatureSchemeV2Verifier$DataSource apkSignatureSchemeV2Verifier$DataSource = new ApkSignatureSchemeV2Verifier$DataSource(fileChannel, j4, j) { // from class: com.android.p2putils.ApkSignatureSchemeV2Verifier$MemoryMappedFileDataSource
                private final FileChannel mFileChannel;
                private final long mFilePosition;
                private final long mSize;

                {
                    this.mFileChannel = fileChannel;
                    this.mFilePosition = j4;
                    this.mSize = j;
                }

                @Override // com.android.p2putils.ApkSignatureSchemeV2Verifier$DataSource
                public final void feedIntoMessageDigests(MessageDigest[] messageDigestArr, long j5, int i) {
                    MappedByteBuffer map2 = this.mFileChannel.map(FileChannel.MapMode.READ_ONLY, this.mFilePosition + j5, i);
                    map2.load();
                    for (MessageDigest messageDigest : messageDigestArr) {
                        map2.position(0);
                        messageDigest.update(map2);
                    }
                }

                @Override // com.android.p2putils.ApkSignatureSchemeV2Verifier$DataSource
                public final long size() {
                    return this.mSize;
                }
            };
            final long j5 = j3 - j2;
            ApkSignatureSchemeV2Verifier$DataSource apkSignatureSchemeV2Verifier$DataSource2 = new ApkSignatureSchemeV2Verifier$DataSource(fileChannel, j2, j5) { // from class: com.android.p2putils.ApkSignatureSchemeV2Verifier$MemoryMappedFileDataSource
                private final FileChannel mFileChannel;
                private final long mFilePosition;
                private final long mSize;

                {
                    this.mFileChannel = fileChannel;
                    this.mFilePosition = j2;
                    this.mSize = j5;
                }

                @Override // com.android.p2putils.ApkSignatureSchemeV2Verifier$DataSource
                public final void feedIntoMessageDigests(MessageDigest[] messageDigestArr, long j52, int i) {
                    MappedByteBuffer map2 = this.mFileChannel.map(FileChannel.MapMode.READ_ONLY, this.mFilePosition + j52, i);
                    map2.load();
                    for (MessageDigest messageDigest : messageDigestArr) {
                        map2.position(0);
                        messageDigest.update(map2);
                    }
                }

                @Override // com.android.p2putils.ApkSignatureSchemeV2Verifier$DataSource
                public final long size() {
                    return this.mSize;
                }
            };
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.order(ByteOrder.LITTLE_ENDIAN);
            NetworkApi23.assertByteOrderLittleEndian(duplicate);
            int position = duplicate.position() + 16;
            if (j >= 0 && j <= 4294967295L) {
                duplicate.putInt(duplicate.position() + position, (int) j);
                ApkSignatureSchemeV2Verifier$DataSource apkSignatureSchemeV2Verifier$DataSource3 = new ApkSignatureSchemeV2Verifier$DataSource(duplicate) { // from class: com.android.p2putils.ApkSignatureSchemeV2Verifier$ByteBufferDataSource
                    private final ByteBuffer mBuf;

                    {
                        this.mBuf = duplicate.slice();
                    }

                    @Override // com.android.p2putils.ApkSignatureSchemeV2Verifier$DataSource
                    public final void feedIntoMessageDigests(MessageDigest[] messageDigestArr, long j6, int i) {
                        ByteBuffer slice;
                        synchronized (this.mBuf) {
                            int i2 = (int) j6;
                            this.mBuf.position(i2);
                            this.mBuf.limit(i2 + i);
                            slice = this.mBuf.slice();
                        }
                        for (MessageDigest messageDigest : messageDigestArr) {
                            slice.position(0);
                            messageDigest.update(slice);
                        }
                    }

                    @Override // com.android.p2putils.ApkSignatureSchemeV2Verifier$DataSource
                    public final long size() {
                        return this.mBuf.capacity();
                    }
                };
                int size = map.size();
                int[] iArr = new int[size];
                Iterator it = map.keySet().iterator();
                int i = 0;
                while (it.hasNext()) {
                    iArr[i] = ((Integer) it.next()).intValue();
                    i++;
                }
                try {
                    byte[][] computeContentDigests = computeContentDigests(iArr, new ApkSignatureSchemeV2Verifier$DataSource[]{apkSignatureSchemeV2Verifier$DataSource, apkSignatureSchemeV2Verifier$DataSource2, apkSignatureSchemeV2Verifier$DataSource3});
                    for (int i2 = 0; i2 < size; i2++) {
                        int i3 = iArr[i2];
                        if (!MessageDigest.isEqual((byte[]) map.get(Integer.valueOf(i3)), computeContentDigests[i2])) {
                            throw new SecurityException(getContentDigestAlgorithmJcaDigestAlgorithm(i3).concat(" digest of contents did not verify"));
                        }
                    }
                    return;
                } catch (DigestException e) {
                    throw new SecurityException("Failed to compute digest(s) of contents", e);
                }
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "uint32 value of out range: "));
        }
        throw new SecurityException("No digests provided");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
    
        r11 = getSignatureAlgorithmContentDigestAlgorithm(r6);
        r12 = getSignatureAlgorithmContentDigestAlgorithm(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
    
        if (r11 == 1) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0052, code lost:
    
        if (r12 == 1) goto L138;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.security.cert.X509Certificate[] verifySigner(java.nio.ByteBuffer r22, java.util.Map r23, java.security.cert.CertificateFactory r24) {
        /*
            Method dump skipped, instructions count: 638
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.NetworkApi21.verifySigner(java.nio.ByteBuffer, java.util.Map, java.security.cert.CertificateFactory):java.security.cert.X509Certificate[]");
    }
}
