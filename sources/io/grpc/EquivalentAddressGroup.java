package io.grpc;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.Attributes;
import j$.util.DesugarCollections;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EquivalentAddressGroup {
    public static final Attributes.Key ATTR_AUTHORITY_OVERRIDE = new Attributes.Key("io.grpc.EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE");
    public final List addrs;
    public final Attributes attrs;
    private final int hashCode;

    public EquivalentAddressGroup(SocketAddress socketAddress) {
        this(socketAddress, Attributes.EMPTY);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EquivalentAddressGroup)) {
            return false;
        }
        EquivalentAddressGroup equivalentAddressGroup = (EquivalentAddressGroup) obj;
        if (this.addrs.size() != equivalentAddressGroup.addrs.size()) {
            return false;
        }
        for (int i = 0; i < this.addrs.size(); i++) {
            if (!((SocketAddress) this.addrs.get(i)).equals(equivalentAddressGroup.addrs.get(i))) {
                return false;
            }
        }
        if (this.attrs.equals(equivalentAddressGroup.attrs)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final String toString() {
        Attributes attributes = this.attrs;
        return "[" + String.valueOf(this.addrs) + "/" + attributes.toString() + "]";
    }

    public EquivalentAddressGroup(SocketAddress socketAddress, Attributes attributes) {
        List singletonList = Collections.singletonList(socketAddress);
        ContextDataProvider.checkArgument(!singletonList.isEmpty(), (Object) "addrs is empty");
        List unmodifiableList = DesugarCollections.unmodifiableList(new ArrayList(singletonList));
        this.addrs = unmodifiableList;
        attributes.getClass();
        this.attrs = attributes;
        this.hashCode = unmodifiableList.hashCode();
    }
}
