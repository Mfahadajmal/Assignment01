package com.google.apps.tiktok.tracing;

import android.os.StrictMode;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
final class InsecureUuidGenerator {
    public static final InsecureUuidGenerator instance;
    private final AtomicLong state;
    private final UUID universallyUniqueXorSeed;

    static {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            instance = new InsecureUuidGenerator(UUID.randomUUID(), new SecureRandom().nextLong());
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public InsecureUuidGenerator(UUID uuid, long j) {
        this.universallyUniqueXorSeed = uuid;
        this.state = new AtomicLong((j ^ 25214903917L) & 281474976710655L);
    }

    final long nextLong() {
        long j;
        do {
            j = this.state.get();
        } while (!this.state.compareAndSet(j, ((25214903917L * (((j * 25214903917L) + 11) & 281474976710655L)) + 11) & 281474976710655L));
        return (((int) (r4 >>> 16)) << 32) + ((int) (r2 >>> 16));
    }

    public final UUID nextUuid() {
        return new UUID((nextLong() & (-61441)) ^ this.universallyUniqueXorSeed.getMostSignificantBits(), (nextLong() >>> 2) ^ this.universallyUniqueXorSeed.getLeastSignificantBits());
    }
}
