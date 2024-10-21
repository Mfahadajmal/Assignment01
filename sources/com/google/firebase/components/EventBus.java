package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
final class EventBus implements Subscriber, Publisher {
    private final Map handlerMap = new HashMap();
    public Queue pendingEvents = new ArrayDeque();

    public final synchronized Set getHandlers$ar$ds() {
        throw null;
    }
}
