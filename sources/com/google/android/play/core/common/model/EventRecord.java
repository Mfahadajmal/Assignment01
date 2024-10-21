package com.google.android.play.core.common.model;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventRecord {
    private final long eventTimestamp;
    private final int eventType;

    public EventRecord() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EventRecord) {
            EventRecord eventRecord = (EventRecord) obj;
            if (this.eventType == eventRecord.eventType() && this.eventTimestamp == eventRecord.eventTimestamp()) {
                return true;
            }
        }
        return false;
    }

    public final long eventTimestamp() {
        return this.eventTimestamp;
    }

    public final int eventType() {
        return this.eventType;
    }

    public final int hashCode() {
        long j = this.eventTimestamp;
        return ((int) (j ^ (j >>> 32))) ^ ((this.eventType ^ 1000003) * 1000003);
    }

    public final String toString() {
        return "EventRecord{eventType=" + this.eventType + ", eventTimestamp=" + this.eventTimestamp + "}";
    }

    public EventRecord(int i, long j) {
        this();
        this.eventType = i;
        this.eventTimestamp = j;
    }
}
