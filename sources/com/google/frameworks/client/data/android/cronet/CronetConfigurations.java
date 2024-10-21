package com.google.frameworks.client.data.android.cronet;

import android.content.Context;
import com.google.common.collect.ImmutableSet;
import java.util.Date;
import org.chromium.net.CronetEngine;
import org.chromium.net.ExperimentalCronetEngine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetConfigurations {
    public static final ImmutableSet DEFAULT_QUIC_HINTS = ImmutableSet.of((Object) QuicHint.create("android.googleapis.com"), (Object) QuicHint.create("www.googleapis.com"), (Object) QuicHint.create("www.google.com"), (Object) QuicHint.create("www.gstatic.com"), (Object) QuicHint.create("lh1.googleusercontent.com"), (Object) QuicHint.create("lh2.googleusercontent.com"), (Object[]) new QuicHint[]{QuicHint.create("lh3.googleusercontent.com"), QuicHint.create("lh4.googleusercontent.com"), QuicHint.create("lh5.googleusercontent.com"), QuicHint.create("lh6.googleusercontent.com"), QuicHint.create("sp1.googleusercontent.com"), QuicHint.create("sp2.googleusercontent.com"), QuicHint.create("sp3.googleusercontent.com"), QuicHint.create("sp4.googleusercontent.com"), QuicHint.create("sp5.googleusercontent.com"), QuicHint.create("sp6.googleusercontent.com")});

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class CronetConfig {
        CronetConfig() {
        }

        public abstract Context context();

        public abstract CronetEngineBuilderFactory cronetEngineBuilderFactory();

        public abstract int diskCacheSizeBytes();

        public abstract boolean enableBrotli();

        public abstract boolean enableCertificateCache();

        public abstract boolean enableHttpCache();

        public final boolean enableInMemoryFallbackCache() {
            if (inMemoryFallbackCacheSizeBytes() > 0) {
                return true;
            }
            return false;
        }

        public abstract boolean enableNetworkQualityEstimator();

        public abstract boolean enableQuic();

        public abstract String experimentalOptions();

        public abstract int inMemoryFallbackCacheSizeBytes();

        public abstract CronetEngine.Builder.LibraryLoader libraryLoader();

        public abstract String storagePath();

        public abstract int threadPriority();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface CronetEngineBuilderFactory {
        ExperimentalCronetEngine.Builder createBuilder();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class PublicKeyPin {
        PublicKeyPin() {
        }

        public abstract Date expirationDate();

        public abstract String host();

        public abstract boolean includeSubdomains();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class QuicHint {
        private final String host;

        public QuicHint() {
        }

        public static QuicHint create(String str) {
            return new QuicHint(str);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof QuicHint) && this.host.equals(((QuicHint) obj).host())) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return ((((this.host.hashCode() ^ 1000003) * 1000003) ^ 443) * 1000003) ^ 443;
        }

        public final String host() {
            return this.host;
        }

        public final String toString() {
            return "QuicHint{host=" + this.host + ", port=443, alternatePort=443}";
        }

        public QuicHint(String str) {
            this();
            this.host = str;
        }
    }
}
