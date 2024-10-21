package com.google.apps.tiktok.tracing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SuffixTree {
    public int activeEdge;
    public int activeLength;
    public Node activeNode;
    public final int[] input;
    public int remainingCharacters;
    public final Node root;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Candidate {
        public final Object SuffixTree$Candidate$ar$node;
        public final int begin;
        public final int end;
        public final int numSeen;

        public Candidate(Node node, int i, int i2, int i3) {
            this.numSeen = i;
            this.begin = i2;
            this.end = i3;
            this.SuffixTree$Candidate$ar$node = node;
        }

        public Candidate(Object obj, int i, int i2, int i3) {
            this.SuffixTree$Candidate$ar$node = obj;
            this.numSeen = i;
            this.begin = i2;
            this.end = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Node {
        int begin;
        final Map children = new HashMap(0);
        final int end;
        Node suffixLink;

        public Node(int i, int i2) {
            if (i <= i2) {
                this.begin = i;
                this.end = i2;
                this.suffixLink = null;
                return;
            }
            throw new IllegalArgumentException();
        }

        public final String toString() {
            return "Node" + System.identityHashCode(this);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TandemRepeatRegion {
        public final int begin;
        public final int end;
        public final int numSeen;

        public TandemRepeatRegion(int i, int i2, int i3) {
            this.begin = i;
            this.end = i2;
            this.numSeen = i3;
        }

        public final int endX() {
            return this.end + this.begin;
        }

        public final int endY() {
            return this.numSeen + this.begin;
        }

        public TandemRepeatRegion(int i, int i2, int i3, byte[] bArr) {
            this.end = i;
            this.numSeen = i2;
            this.begin = i3;
        }
    }

    public SuffixTree(int[] iArr) {
        this.input = iArr;
        Node node = new Node(-1, -1);
        this.root = node;
        this.activeNode = node;
    }

    private final void printChildren(Node node, StringBuilder sb) {
        for (Node node2 : node.children.values()) {
            sb.append("  ");
            sb.append(node);
            sb.append(" -> ");
            sb.append(node2);
            sb.append(" [label=\"");
            int[] iArr = this.input;
            sb.append(Arrays.toString(Arrays.copyOfRange(iArr, node2.begin, Math.min(iArr.length, node2.end + 1))));
            sb.append("\"]\n");
            printChildren(node2, sb);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void followSuffixLink() {
        Node node = this.activeNode.suffixLink;
        if (node != null) {
            this.activeNode = node;
        } else {
            this.activeNode = this.root;
            int i = this.activeLength;
            if (i > 0) {
                this.activeLength = i - 1;
            }
            if (this.remainingCharacters > 0) {
                this.activeEdge++;
            }
        }
        walkDown();
    }

    public final boolean regionEquals(int i, int i2, int i3, int i4) {
        if (i >= 0 && i3 >= 0) {
            int min = Math.min(this.input.length, i2);
            if (min - i == Math.min(this.input.length, i4) - i3) {
                for (int i5 = i; i5 <= min; i5++) {
                    int[] iArr = this.input;
                    if (iArr[i5] != iArr[(i3 + i5) - i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("digraph {\n");
        printChildren(this.root, sb);
        sb.append("}");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void walkDown() {
        if (this.activeLength != 0) {
            Node node = (Node) this.activeNode.children.get(Integer.valueOf(this.input[this.activeEdge]));
            while (true) {
                int i = (node.end - node.begin) + 1;
                int i2 = this.activeLength;
                if (i <= i2) {
                    int i3 = this.activeEdge + i;
                    this.activeEdge = i3;
                    this.activeNode = node;
                    int i4 = i2 - i;
                    this.activeLength = i4;
                    if (i4 > 0) {
                        node = (Node) node.children.get(Integer.valueOf(this.input[i3]));
                    }
                } else {
                    return;
                }
            }
        }
    }
}
