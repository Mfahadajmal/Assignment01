package com.google.protobuf;

import androidx.preference.Preference;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RopeByteString extends ByteString {
    public static final int[] minLengthByDepth = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Preference.DEFAULT_ORDER};
    private static final long serialVersionUID = 1;
    public final ByteString left;
    private final int leftLength;
    public final ByteString right;
    public final int totalLength;
    public final int treeDepth;

    /* compiled from: PG */
    /* renamed from: com.google.protobuf.RopeByteString$1, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass1 extends ByteString.AbstractByteIterator {
        ByteString.ByteIterator current = nextPiece();
        final PieceIterator pieces;

        public AnonymousClass1() {
            this.pieces = new PieceIterator(RopeByteString.this);
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [com.google.protobuf.ByteString$ByteIterator] */
        private final ByteString.ByteIterator nextPiece() {
            PieceIterator pieceIterator = this.pieces;
            if (pieceIterator.hasNext()) {
                return pieceIterator.next().iterator2();
            }
            return null;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.current != null) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.ByteString.ByteIterator
        public final byte nextByte() {
            ByteString.ByteIterator byteIterator = this.current;
            if (byteIterator != null) {
                byte nextByte = byteIterator.nextByte();
                if (!this.current.hasNext()) {
                    this.current = nextPiece();
                }
                return nextByte;
            }
            throw new NoSuchElementException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PieceIterator implements Iterator {
        private final ArrayDeque breadCrumbs;
        private ByteString.LeafByteString next;

        public PieceIterator(ByteString byteString) {
            if (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                ArrayDeque arrayDeque = new ArrayDeque(ropeByteString.treeDepth);
                this.breadCrumbs = arrayDeque;
                arrayDeque.push(ropeByteString);
                this.next = getLeafByLeft(ropeByteString.left);
                return;
            }
            this.breadCrumbs = null;
            this.next = (ByteString.LeafByteString) byteString;
        }

        private final ByteString.LeafByteString getLeafByLeft(ByteString byteString) {
            while (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                this.breadCrumbs.push(ropeByteString);
                int[] iArr = RopeByteString.minLengthByDepth;
                byteString = ropeByteString.left;
            }
            return (ByteString.LeafByteString) byteString;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.next != null) {
                return true;
            }
            return false;
        }

        @Override // java.util.Iterator
        public final ByteString.LeafByteString next() {
            ByteString.LeafByteString leafByteString;
            ByteString.LeafByteString leafByteString2 = this.next;
            if (leafByteString2 == null) {
                throw new NoSuchElementException();
            }
            do {
                ArrayDeque arrayDeque = this.breadCrumbs;
                leafByteString = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    break;
                }
                RopeByteString ropeByteString = (RopeByteString) this.breadCrumbs.pop();
                int[] iArr = RopeByteString.minLengthByDepth;
                leafByteString = getLeafByLeft(ropeByteString.right);
            } while (leafByteString.isEmpty());
            this.next = leafByteString;
            return leafByteString2;
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public RopeByteString(ByteString byteString, ByteString byteString2) {
        this.left = byteString;
        this.right = byteString2;
        int size = byteString.size();
        this.leftLength = size;
        this.totalLength = size + byteString2.size();
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString.size() + byteString2.size();
        if (size < 128) {
            return concatenateBytes(byteString, byteString2);
        }
        if (byteString instanceof RopeByteString) {
            RopeByteString ropeByteString = (RopeByteString) byteString;
            if (ropeByteString.right.size() + byteString2.size() < 128) {
                return new RopeByteString(ropeByteString.left, concatenateBytes(ropeByteString.right, byteString2));
            }
            if (ropeByteString.left.getTreeDepth() > ropeByteString.right.getTreeDepth() && ropeByteString.treeDepth > byteString2.getTreeDepth()) {
                return new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
            }
        }
        if (size >= minLength(Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1)) {
            return new RopeByteString(byteString, byteString2);
        }
        MultiFlavorDetectorCreator multiFlavorDetectorCreator = new MultiFlavorDetectorCreator((byte[]) null);
        multiFlavorDetectorCreator.doBalance(byteString);
        multiFlavorDetectorCreator.doBalance(byteString2);
        ByteString byteString3 = (ByteString) ((ArrayDeque) multiFlavorDetectorCreator.MultiFlavorDetectorCreator$ar$registrations).pop();
        while (!((ArrayDeque) multiFlavorDetectorCreator.MultiFlavorDetectorCreator$ar$registrations).isEmpty()) {
            byteString3 = new RopeByteString((ByteString) ((ArrayDeque) multiFlavorDetectorCreator.MultiFlavorDetectorCreator$ar$registrations).pop(), byteString3);
        }
        return byteString3;
    }

    private static ByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[size + size2];
        byteString.copyTo$ar$ds(bArr, 0, size);
        byteString2.copyTo$ar$ds(bArr, size, size2);
        return new ByteString.LiteralByteString(bArr);
    }

    public static int minLength(int i) {
        int[] iArr = minLengthByDepth;
        int length = iArr.length;
        if (i >= 47) {
            return Preference.DEFAULT_ORDER;
        }
        return iArr[i];
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    @Override // com.google.protobuf.ByteString
    public final ByteBuffer asReadOnlyByteBuffer() {
        throw null;
    }

    @Override // com.google.protobuf.ByteString
    public final byte byteAt(int i) {
        checkIndex(i, this.totalLength);
        return internalByteAt(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public final void copyToInternal(byte[] bArr, int i, int i2, int i3) {
        int i4 = i + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            this.left.copyToInternal(bArr, i, i2, i3);
        } else {
            if (i >= i5) {
                this.right.copyToInternal(bArr, i - i5, i2, i3);
                return;
            }
            int i6 = i5 - i;
            this.left.copyToInternal(bArr, i, i2, i6);
            this.right.copyToInternal(bArr, 0, i2 + i6, i3 - i6);
        }
    }

    @Override // com.google.protobuf.ByteString
    public final boolean equals(Object obj) {
        boolean equalsRange;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (this.totalLength != byteString.size()) {
            return false;
        }
        if (this.totalLength == 0) {
            return true;
        }
        int i = this.hash;
        int i2 = byteString.hash;
        if (i != 0 && i2 != 0 && i != i2) {
            return false;
        }
        PieceIterator pieceIterator = new PieceIterator(this);
        ByteString.LeafByteString next = pieceIterator.next();
        PieceIterator pieceIterator2 = new PieceIterator(byteString);
        ByteString.LeafByteString next2 = pieceIterator2.next();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int size = next.size() - i3;
            int size2 = next2.size() - i4;
            int min = Math.min(size, size2);
            if (i3 == 0) {
                equalsRange = next.equalsRange(next2, i4, min);
            } else {
                equalsRange = next2.equalsRange(next, i3, min);
            }
            if (!equalsRange) {
                return false;
            }
            i5 += min;
            int i6 = this.totalLength;
            if (i5 >= i6) {
                if (i5 == i6) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (min == size) {
                i3 = 0;
                next = pieceIterator.next();
            } else {
                i3 += min;
                next = next;
            }
            if (min == size2) {
                next2 = pieceIterator2.next();
                i4 = 0;
            } else {
                i4 += min;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public final int getTreeDepth() {
        return this.treeDepth;
    }

    @Override // com.google.protobuf.ByteString
    public final byte internalByteAt(int i) {
        int i2 = this.leftLength;
        if (i < i2) {
            return this.left.internalByteAt(i);
        }
        return this.right.internalByteAt(i - i2);
    }

    @Override // com.google.protobuf.ByteString
    public final boolean isBalanced() {
        if (this.totalLength >= minLength(this.treeDepth)) {
            return true;
        }
        return false;
    }

    @Override // com.google.protobuf.ByteString
    public final boolean isValidUtf8() {
        ByteString byteString = this.left;
        ByteString byteString2 = this.right;
        if (byteString2.partialIsValidUtf8(byteString.partialIsValidUtf8(0, 0, this.leftLength), 0, byteString2.size()) != 0) {
            return false;
        }
        return true;
    }

    @Override // com.google.protobuf.ByteString, java.lang.Iterable
    /* renamed from: iterator */
    public final Iterator<Byte> iterator2() {
        return new AnonymousClass1();
    }

    @Override // com.google.protobuf.ByteString
    public final CodedInputStream newCodedInput() {
        ArrayList<ByteBuffer> arrayList = new ArrayList();
        PieceIterator pieceIterator = new PieceIterator(this);
        while (pieceIterator.hasNext()) {
            arrayList.add(pieceIterator.next().asReadOnlyByteBuffer());
        }
        int i = CodedInputStream.CodedInputStream$ar$NoOp;
        int i2 = 0;
        int i3 = 0;
        for (ByteBuffer byteBuffer : arrayList) {
            i3 += byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                i2 |= 1;
            } else if (byteBuffer.isDirect()) {
                i2 |= 2;
            } else {
                i2 |= 4;
            }
        }
        if (i2 == 2) {
            return new CodedInputStream.IterableDirectByteBufferDecoder(arrayList, i3);
        }
        return CodedInputStream.newInstance(new IterableByteBufferInputStream(arrayList));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public final int partialHash(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialHash(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialHash(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialHash(this.left.partialHash(i, i2, i6), 0, i3 - i6);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.ByteString
    public final int partialIsValidUtf8(int i, int i2, int i3) {
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            return this.left.partialIsValidUtf8(i, i2, i3);
        }
        if (i2 >= i5) {
            return this.right.partialIsValidUtf8(i, i2 - i5, i3);
        }
        int i6 = i5 - i2;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i, i2, i6), 0, i3 - i6);
    }

    @Override // com.google.protobuf.ByteString
    public final int size() {
        return this.totalLength;
    }

    @Override // com.google.protobuf.ByteString
    public final ByteString substring(int i, int i2) {
        int checkRange = checkRange(i, i2, this.totalLength);
        if (checkRange == 0) {
            return ByteString.EMPTY;
        }
        if (checkRange == this.totalLength) {
            return this;
        }
        int i3 = this.leftLength;
        if (i2 <= i3) {
            return this.left.substring(i, i2);
        }
        if (i >= i3) {
            return this.right.substring(i - i3, i2 - i3);
        }
        ByteString byteString = this.left;
        return new RopeByteString(byteString.substring(i, byteString.size()), this.right.substring(0, i2 - this.leftLength));
    }

    @Override // com.google.protobuf.ByteString
    protected final String toStringInternal(Charset charset) {
        return new String(toByteArray(), charset);
    }

    Object writeReplace() {
        return new ByteString.LiteralByteString(toByteArray());
    }

    @Override // com.google.protobuf.ByteString
    public final void writeTo(ByteOutput byteOutput) {
        this.left.writeTo(byteOutput);
        this.right.writeTo(byteOutput);
    }

    @Override // com.google.protobuf.ByteString, java.lang.Iterable
    /* renamed from: iterator, reason: avoid collision after fix types in other method */
    public final /* synthetic */ Iterator<Byte> iterator2() {
        return new AnonymousClass1();
    }
}
