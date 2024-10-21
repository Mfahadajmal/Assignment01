package com.google.android.datatransport;

import _COROUTINE._BOUNDARY;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Event {
    public final Object payload;
    public final int priority$ar$edu;

    public Event() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Event) {
            Event event = (Event) obj;
            if (this.payload.equals(event.getPayload()) && this.priority$ar$edu == event.getPriority$ar$edu()) {
                return true;
            }
        }
        return false;
    }

    public final Object getPayload() {
        return this.payload;
    }

    public final int getPriority$ar$edu() {
        return this.priority$ar$edu;
    }

    public final int hashCode() {
        return (((this.payload.hashCode() ^ (-721379959)) * 1000003) ^ _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_0(this.priority$ar$edu)) * (-721379959);
    }

    public final String toString() {
        String str;
        int i = this.priority$ar$edu;
        String obj = this.payload.toString();
        if (i != 1) {
            str = "VERY_LOW";
        } else {
            str = "DEFAULT";
        }
        return "Event{code=null, payload=" + obj + ", priority=" + str + ", productData=null, eventContext=null}";
    }

    public Event(Object obj, int i) {
        this();
        if (obj == null) {
            throw new NullPointerException("Null payload");
        }
        this.payload = obj;
        this.priority$ar$edu = i;
    }
}
