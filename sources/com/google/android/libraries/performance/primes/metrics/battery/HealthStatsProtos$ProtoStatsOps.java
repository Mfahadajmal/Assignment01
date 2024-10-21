package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class HealthStatsProtos$ProtoStatsOps {
    public abstract MessageLite convert(String str, Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List convert(Map map) {
        MessageLite convert;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() != null && (convert = convert((String) entry.getKey(), entry.getValue())) != null) {
                arrayList.add(convert);
            }
        }
        return arrayList;
    }

    public abstract String nameOf(MessageLite messageLite);

    public abstract MessageLite subtract(MessageLite messageLite, MessageLite messageLite2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List subtract(List list, List list2) {
        MessageLite messageLite;
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MessageLite messageLite2 = (MessageLite) it.next();
            String nameOf = nameOf(messageLite2);
            Iterator it2 = list2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    messageLite = null;
                    break;
                }
                messageLite = (MessageLite) it2.next();
                if (nameOf.equals(nameOf(messageLite))) {
                    break;
                }
            }
            MessageLite subtract = subtract(messageLite2, messageLite);
            if (subtract != null) {
                arrayList.add(subtract);
            }
        }
        return arrayList;
    }
}
