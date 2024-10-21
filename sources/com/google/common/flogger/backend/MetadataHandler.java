package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MetadataHandler {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public final ValueHandler defaultHandler;
        public static final ValueHandler IGNORE_VALUE = new ValueHandler() { // from class: com.google.common.flogger.backend.MetadataHandler.Builder.1
            @Override // com.google.common.flogger.backend.MetadataHandler.ValueHandler
            public final void handle(MetadataKey metadataKey, Object obj, Object obj2) {
            }
        };
        public static final RepeatedValueHandler IGNORE_REPEATED_VALUE = new RepeatedValueHandler() { // from class: com.google.common.flogger.backend.MetadataHandler.Builder.2
            @Override // com.google.common.flogger.backend.MetadataHandler.RepeatedValueHandler
            public final void handle(MetadataKey metadataKey, Iterator it, Object obj) {
            }
        };
        public final Map singleValueHandlers = new HashMap();
        public final Map repeatedValueHandlers = new HashMap();
        public RepeatedValueHandler defaultRepeatedHandler = null;

        public Builder(ValueHandler valueHandler) {
            this.defaultHandler = valueHandler;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MapBasedhandler extends MetadataHandler {
        private final ValueHandler defaultHandler;
        private final RepeatedValueHandler defaultRepeatedHandler;
        private final Map repeatedValueHandlers;
        private final Map singleValueHandlers;

        public MapBasedhandler(Builder builder) {
            HashMap hashMap = new HashMap();
            this.singleValueHandlers = hashMap;
            HashMap hashMap2 = new HashMap();
            this.repeatedValueHandlers = hashMap2;
            hashMap.putAll(builder.singleValueHandlers);
            hashMap2.putAll(builder.repeatedValueHandlers);
            this.defaultHandler = builder.defaultHandler;
            this.defaultRepeatedHandler = builder.defaultRepeatedHandler;
        }

        @Override // com.google.common.flogger.backend.MetadataHandler
        protected final void handle(MetadataKey metadataKey, Object obj, Object obj2) {
            ValueHandler valueHandler = (ValueHandler) this.singleValueHandlers.get(metadataKey);
            if (valueHandler != null) {
                valueHandler.handle(metadataKey, obj, obj2);
            } else {
                this.defaultHandler.handle(metadataKey, obj, obj2);
            }
        }

        @Override // com.google.common.flogger.backend.MetadataHandler
        protected final void handleRepeated(MetadataKey metadataKey, Iterator it, Object obj) {
            RepeatedValueHandler repeatedValueHandler = (RepeatedValueHandler) this.repeatedValueHandlers.get(metadataKey);
            if (repeatedValueHandler != null) {
                repeatedValueHandler.handle(metadataKey, it, obj);
            } else if (this.defaultRepeatedHandler != null && !this.singleValueHandlers.containsKey(metadataKey)) {
                this.defaultRepeatedHandler.handle(metadataKey, it, obj);
            } else {
                while (it.hasNext()) {
                    handle(metadataKey, it.next(), obj);
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface RepeatedValueHandler {
        void handle(MetadataKey metadataKey, Iterator it, Object obj);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ValueHandler {
        void handle(MetadataKey metadataKey, Object obj, Object obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void handle(MetadataKey metadataKey, Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleRepeated(MetadataKey metadataKey, Iterator it, Object obj) {
        throw null;
    }
}
