package org.chromium.net;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface RequestContextConfigOptionsOrBuilder extends MessageLiteOrBuilder {
    boolean getBrotliEnabled();

    boolean getBypassPublicKeyPinningForLocalTrustAnchors();

    boolean getDisableCache();

    boolean getEnableNetworkQualityEstimator();

    String getExperimentalOptions();

    ByteString getExperimentalOptionsBytes();

    boolean getHttp2Enabled();

    long getHttpCacheMaxSize();

    int getHttpCacheMode();

    long getMockCertVerifier();

    int getNetworkThreadPriority();

    String getQuicDefaultUserAgentId();

    ByteString getQuicDefaultUserAgentIdBytes();

    boolean getQuicEnabled();

    String getStoragePath();

    ByteString getStoragePathBytes();

    String getUserAgent();

    ByteString getUserAgentBytes();

    boolean hasBrotliEnabled();

    boolean hasBypassPublicKeyPinningForLocalTrustAnchors();

    boolean hasDisableCache();

    boolean hasEnableNetworkQualityEstimator();

    boolean hasExperimentalOptions();

    boolean hasHttp2Enabled();

    boolean hasHttpCacheMaxSize();

    boolean hasHttpCacheMode();

    boolean hasMockCertVerifier();

    boolean hasNetworkThreadPriority();

    boolean hasQuicDefaultUserAgentId();

    boolean hasQuicEnabled();

    boolean hasStoragePath();

    boolean hasUserAgent();
}
