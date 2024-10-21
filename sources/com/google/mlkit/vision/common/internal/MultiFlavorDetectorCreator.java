package com.google.mlkit.vision.common.internal;

import com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.vision.text.aidls.TextParcel;
import com.google.protobuf.ByteString;
import com.google.protobuf.RopeByteString;
import j$.util.DesugarCollections;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiFlavorDetectorCreator {
    private static MultiFlavorDetectorCreator instance$ar$class_merging$30544c60_0;
    public final Object MultiFlavorDetectorCreator$ar$registrations;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Registration {
    }

    public MultiFlavorDetectorCreator(Set set) {
        this.MultiFlavorDetectorCreator$ar$registrations = new HashMap();
        new HashMap();
        Iterator it = set.iterator();
        if (it.hasNext()) {
            throw null;
        }
    }

    public static synchronized MultiFlavorDetectorCreator getDefaultRegistry$ar$class_merging() {
        MultiFlavorDetectorCreator multiFlavorDetectorCreator;
        synchronized (MultiFlavorDetectorCreator.class) {
            if (instance$ar$class_merging$30544c60_0 == null) {
                instance$ar$class_merging$30544c60_0 = new MultiFlavorDetectorCreator();
            }
            multiFlavorDetectorCreator = instance$ar$class_merging$30544c60_0;
        }
        return multiFlavorDetectorCreator;
    }

    private static final int getDepthBinForLength$ar$ds(int i) {
        int binarySearch = Arrays.binarySearch(RopeByteString.minLengthByDepth, i);
        if (binarySearch < 0) {
            return (-(binarySearch + 1)) - 1;
        }
        return binarySearch;
    }

    public final void doBalance(ByteString byteString) {
        if (byteString.isBalanced()) {
            int depthBinForLength$ar$ds = getDepthBinForLength$ar$ds(byteString.size());
            Object obj = this.MultiFlavorDetectorCreator$ar$registrations;
            int minLength = RopeByteString.minLength(depthBinForLength$ar$ds + 1);
            if (!((ArrayDeque) obj).isEmpty() && ((ByteString) ((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).peek()).size() < minLength) {
                int minLength2 = RopeByteString.minLength(depthBinForLength$ar$ds);
                ByteString byteString2 = (ByteString) ((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).pop();
                while (!((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).isEmpty() && ((ByteString) ((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).peek()).size() < minLength2) {
                    byteString2 = new RopeByteString((ByteString) ((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).pop(), byteString2);
                }
                RopeByteString ropeByteString = new RopeByteString(byteString2, byteString);
                while (!((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).isEmpty()) {
                    int depthBinForLength$ar$ds2 = getDepthBinForLength$ar$ds(ropeByteString.totalLength) + 1;
                    Object obj2 = this.MultiFlavorDetectorCreator$ar$registrations;
                    if (((ByteString) ((ArrayDeque) obj2).peek()).size() >= RopeByteString.minLength(depthBinForLength$ar$ds2)) {
                        break;
                    } else {
                        ropeByteString = new RopeByteString((ByteString) ((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).pop(), ropeByteString);
                    }
                }
                ((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).push(ropeByteString);
                return;
            }
            ((ArrayDeque) this.MultiFlavorDetectorCreator$ar$registrations).push(byteString);
            return;
        }
        if (byteString instanceof RopeByteString) {
            RopeByteString ropeByteString2 = (RopeByteString) byteString;
            int[] iArr = RopeByteString.minLengthByDepth;
            doBalance(ropeByteString2.left);
            doBalance(ropeByteString2.right);
            return;
        }
        throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(String.valueOf(byteString.getClass()))));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    public final synchronized List getConfigurators() {
        return this.MultiFlavorDetectorCreator$ar$registrations;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
    public final List getTextBlocks() {
        return DesugarCollections.unmodifiableList(this.MultiFlavorDetectorCreator$ar$registrations);
    }

    public final synchronized void wasSetConfiguratorsCalled$ar$ds() {
    }

    public MultiFlavorDetectorCreator(TextParcel textParcel) {
        ArrayList arrayList = new ArrayList();
        this.MultiFlavorDetectorCreator$ar$registrations = arrayList;
        String str = textParcel.text;
        arrayList.addAll(ContextDataProvider.transform(textParcel.textBlocks, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(4)));
    }

    public MultiFlavorDetectorCreator(List list) {
        ArrayList arrayList = new ArrayList();
        this.MultiFlavorDetectorCreator$ar$registrations = arrayList;
        arrayList.addAll(list);
    }

    public MultiFlavorDetectorCreator(byte[] bArr) {
        this.MultiFlavorDetectorCreator$ar$registrations = new ArrayDeque();
    }

    public MultiFlavorDetectorCreator() {
        this.MultiFlavorDetectorCreator$ar$registrations = Collections.emptyList();
    }
}
