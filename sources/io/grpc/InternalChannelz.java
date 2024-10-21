package io.grpc;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalChannelz {
    public final ConcurrentMap otherSockets;
    public final ConcurrentNavigableMap rootChannels;
    public final ConcurrentMap subchannels;
    public static final Logger log = Logger.getLogger(InternalChannelz.class.getName());
    public static final InternalChannelz INSTANCE = new InternalChannelz();

    public InternalChannelz() {
        new ConcurrentSkipListMap();
        this.rootChannels = new ConcurrentSkipListMap();
        this.subchannels = new ConcurrentHashMap();
        this.otherSockets = new ConcurrentHashMap();
        new ConcurrentHashMap();
    }

    public static void add(Map map, InternalInstrumented internalInstrumented) {
    }

    public static void remove(Map map, InternalInstrumented internalInstrumented) {
    }
}
