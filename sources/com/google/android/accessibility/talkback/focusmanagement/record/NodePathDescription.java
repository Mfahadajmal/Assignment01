package com.google.android.accessibility.talkback.focusmanagement.record;

import android.graphics.Rect;
import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.utils.DiagnosticOverlayControllerImpl;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.DiagnosticOverlayController;
import com.google.android.accessibility.utils.DiagnosticOverlayUtils;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Comparator$CC;
import j$.util.Comparator$EL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodePathDescription {
    private static final Comparator NODE_MATCH_COMPARATOR = Comparator$EL.thenComparing(Comparator$EL.thenComparingDouble(Comparator$CC.comparingInt(new NodePathDescription$$ExternalSyntheticLambda1(0)), new NodePathDescription$$ExternalSyntheticLambda2(0)), new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(8), Comparator$CC.reverseOrder());
    public static final /* synthetic */ int NodePathDescription$ar$NoOp = 0;
    public final ArrayList nodeDescriptions = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Match {
        private final int depth;
        private final double distance;
        public final boolean isPathEnd;
        public final AccessibilityNode node;
        public final boolean prune;
        private final double score;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            private int depth;
            private double distance;
            private boolean isPathEnd;
            public AccessibilityNode node;
            private boolean prune;
            private double score;
            private byte set$0;

            public Builder() {
            }

            public final Match build() {
                if (this.set$0 != 31) {
                    StringBuilder sb = new StringBuilder();
                    if ((this.set$0 & 1) == 0) {
                        sb.append(" prune");
                    }
                    if ((this.set$0 & 2) == 0) {
                        sb.append(" isPathEnd");
                    }
                    if ((this.set$0 & 4) == 0) {
                        sb.append(" score");
                    }
                    if ((this.set$0 & 8) == 0) {
                        sb.append(" depth");
                    }
                    if ((this.set$0 & 16) == 0) {
                        sb.append(" distance");
                    }
                    throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                }
                return new Match(this.prune, this.isPathEnd, this.score, this.depth, this.distance, this.node);
            }

            public final void setDepth$ar$ds(int i) {
                this.depth = i;
                this.set$0 = (byte) (this.set$0 | 8);
            }

            public final void setDistance$ar$ds(double d) {
                this.distance = d;
                this.set$0 = (byte) (this.set$0 | 16);
            }

            public final void setIsPathEnd$ar$ds(boolean z) {
                this.isPathEnd = z;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public final void setPrune$ar$ds(boolean z) {
                this.prune = z;
                this.set$0 = (byte) (this.set$0 | 1);
            }

            public final void setScore$ar$ds(double d) {
                this.score = d;
                this.set$0 = (byte) (this.set$0 | 4);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public Match() {
        }

        public final int depth() {
            return this.depth;
        }

        public final double distance() {
            return this.distance;
        }

        public final boolean equals(Object obj) {
            AccessibilityNode accessibilityNode;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Match) {
                Match match = (Match) obj;
                if (this.prune == match.prune() && this.isPathEnd == match.isPathEnd() && Double.doubleToLongBits(this.score) == Double.doubleToLongBits(match.score()) && this.depth == match.depth() && Double.doubleToLongBits(this.distance) == Double.doubleToLongBits(match.distance()) && ((accessibilityNode = this.node) != null ? accessibilityNode.equals(match.node()) : match.node() == null)) {
                    return true;
                }
            }
            return false;
        }

        public final boolean hasNode() {
            if (node() != null) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            long doubleToLongBits = (Double.doubleToLongBits(this.score) >>> 32) ^ Double.doubleToLongBits(this.score);
            long doubleToLongBits2 = (Double.doubleToLongBits(this.distance) >>> 32) ^ Double.doubleToLongBits(this.distance);
            AccessibilityNode accessibilityNode = this.node;
            if (accessibilityNode == null) {
                hashCode = 0;
            } else {
                hashCode = accessibilityNode.hashCode();
            }
            int i2 = 1237;
            if (true != this.prune) {
                i = 1237;
            } else {
                i = 1231;
            }
            if (true == this.isPathEnd) {
                i2 = 1231;
            }
            return ((((((((int) doubleToLongBits) ^ ((((i ^ 1000003) * 1000003) ^ i2) * 1000003)) * 1000003) ^ this.depth) * 1000003) ^ ((int) doubleToLongBits2)) * 1000003) ^ hashCode;
        }

        public final boolean isPathEnd() {
            return this.isPathEnd;
        }

        public final AccessibilityNode node() {
            return this.node;
        }

        public final boolean prune() {
            return this.prune;
        }

        public final double score() {
            return this.score;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(StringBuilderUtils.optionalTag("prune", prune()), StringBuilderUtils.optionalTag("isPathEnd", isPathEnd()), StringBuilderUtils.optionalDouble("score", score(), 0.0d), StringBuilderUtils.optionalInt("depth", depth(), 0), StringBuilderUtils.optionalDouble("distance", distance(), 3.4028234663852886E38d), StringBuilderUtils.optionalSubObj("node", node()));
        }

        public Match(boolean z, boolean z2, double d, int i, double d2, AccessibilityNode accessibilityNode) {
            this();
            this.prune = z;
            this.isPathEnd = z2;
            this.score = d;
            this.depth = i;
            this.distance = d2;
            this.node = accessibilityNode;
        }
    }

    public NodePathDescription() {
    }

    public static boolean contentMatches(NodeDescription nodeDescription, AccessibilityNode accessibilityNode, boolean z) {
        if (accessibilityNode != null) {
            CharSequence text = NodeDescription.getText(accessibilityNode, z);
            if ((!z || !TextUtils.isEmpty(nodeDescription.text)) && TextUtils.equals(nodeDescription.className, accessibilityNode.getCompat().getClassName())) {
                return TextUtils.equals(nodeDescription.text, text);
            }
            return false;
        }
        return false;
    }

    private static boolean isTextMatchingNonEmptyText(CharSequence charSequence, CharSequence charSequence2) {
        if (!TextUtils.isEmpty(charSequence) && !TextUtils.equals(charSequence, "OUT_OF_RANGE")) {
            return TextUtils.equals(charSequence, charSequence2);
        }
        return false;
    }

    public static AccessibilityNode refreshOrNull(AccessibilityNode accessibilityNode) {
        if (accessibilityNode != null && accessibilityNode.refresh()) {
            return accessibilityNode;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.nodeDescriptions.equals(((NodePathDescription) obj).nodeDescriptions);
        }
        return false;
    }

    public final Match findDownwardMatch(AccessibilityNode accessibilityNode, int i, int i2, CharSequence charSequence, CharSequence charSequence2, HashSet hashSet) {
        boolean z;
        boolean z2;
        boolean z3;
        double d;
        double d2;
        Match build;
        boolean z4;
        AccessibilityNode accessibilityNode2;
        CharSequence text;
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo;
        int i3;
        AccessibilityNode accessibilityNode3;
        int i4;
        Integer num = 5;
        DiagnosticOverlayController diagnosticOverlayController = DiagnosticOverlayUtils.diagnosticOverlayController;
        if (diagnosticOverlayController != null && ((DiagnosticOverlayControllerImpl) diagnosticOverlayController).enabled) {
            num.getClass();
            DiagnosticOverlayControllerImpl.refocusNodePath.add(accessibilityNode);
        }
        if (hashSet.contains(accessibilityNode)) {
            return null;
        }
        hashSet.add(accessibilityNode);
        Match.Builder builder = new Match.Builder(null);
        builder.setPrune$ar$ds(false);
        builder.setIsPathEnd$ar$ds(false);
        double d3 = 0.0d;
        builder.setScore$ar$ds(0.0d);
        builder.setDepth$ar$ds(0);
        builder.setDistance$ar$ds(3.4028234663852886E38d);
        builder.setDepth$ar$ds(i2);
        if (i2 == 0) {
            builder.node = accessibilityNode;
            build = builder.build();
        } else {
            int size = (this.nodeDescriptions.size() - i2) - 1;
            if (size < 0) {
                builder.setPrune$ar$ds(true);
                build = builder.build();
            } else {
                if (size == 0) {
                    z = true;
                } else {
                    z = false;
                }
                builder.setIsPathEnd$ar$ds(z);
                NodeDescription nodeDescription = (NodeDescription) this.nodeDescriptions.get(size);
                if (!isTextMatchingNonEmptyText(charSequence, nodeDescription.previousSiblingText) && !isTextMatchingNonEmptyText(charSequence2, nodeDescription.nextSiblingText)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                boolean contentMatches = contentMatches(nodeDescription, accessibilityNode, z);
                int i5 = nodeDescription.rawIndexInParent;
                boolean hasCollectionIndex = nodeDescription.hasCollectionIndex();
                if (i5 == i) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (hasCollectionIndex) {
                    z3 = nodeDescription.matchesCollectionIndices(accessibilityNode);
                }
                if (true != contentMatches) {
                    d = 0.0d;
                } else {
                    d = 1.2d;
                }
                if (true != z3) {
                    d2 = 0.0d;
                } else {
                    d2 = 1.1d;
                }
                if (true == z2) {
                    d3 = 1.0d;
                }
                double d4 = d + d2 + d3;
                builder.setScore$ar$ds(d4);
                if (d4 <= 1.1d) {
                    builder.setPrune$ar$ds(true);
                    build = builder.build();
                } else {
                    builder.node = accessibilityNode;
                    AccessibilityNode accessibilityNode4 = nodeDescription.savedNode;
                    if (accessibilityNode4 != null) {
                        accessibilityNode4.getBoundsInScreen(new Rect());
                        accessibilityNode.getBoundsInScreen(new Rect());
                        builder.setDistance$ar$ds(Math.sqrt(((int) Math.pow(r3.centerX() - r4.centerX(), 2.0d)) + ((int) Math.pow(r3.centerY() - r4.centerY(), 2.0d))));
                    }
                    build = builder.build();
                }
            }
        }
        if (!build.hasNode() || build.prune) {
            return null;
        }
        if (this.nodeDescriptions.size() - 1 <= i2) {
            return build;
        }
        int i6 = i2 + 1;
        if (this.nodeDescriptions.size() - 1 <= i6) {
            z4 = true;
        } else {
            z4 = false;
        }
        int role = accessibilityNode.getRole();
        Match match = build;
        int i7 = 0;
        AccessibilityNode accessibilityNode5 = null;
        CharSequence charSequence3 = null;
        CharSequence charSequence4 = "OUT_OF_RANGE";
        while (i7 <= accessibilityNode.getChildCount()) {
            if (i7 < accessibilityNode.getChildCount()) {
                accessibilityNode2 = accessibilityNode.getChild(i7);
            } else {
                accessibilityNode2 = null;
            }
            if (accessibilityNode2 == null) {
                text = "OUT_OF_RANGE";
            } else {
                text = NodeDescription.getText(accessibilityNode2, z4);
            }
            if (accessibilityNode2 == null) {
                collectionItemInfo = null;
            } else {
                collectionItemInfo = accessibilityNode2.getCollectionItemInfo();
            }
            if (role == 16 && collectionItemInfo != null) {
                i3 = collectionItemInfo.getColumnIndex();
            } else {
                i3 = i7;
            }
            if (accessibilityNode5 != null) {
                accessibilityNode3 = accessibilityNode2;
                i4 = i7;
                Match findDownwardMatch = findDownwardMatch(accessibilityNode5, i3 - 1, i6, charSequence3, text, hashSet);
                if (findDownwardMatch != null && findDownwardMatch.hasNode() && NODE_MATCH_COMPARATOR.compare(findDownwardMatch, match) > 0 && findDownwardMatch.node.getCompat().isVisibleToUser()) {
                    match = findDownwardMatch;
                }
            } else {
                accessibilityNode3 = accessibilityNode2;
                i4 = i7;
            }
            i7 = i4 + 1;
            charSequence3 = charSequence4;
            charSequence4 = text;
            accessibilityNode5 = accessibilityNode3;
        }
        return match;
    }

    public final int hashCode() {
        return this.nodeDescriptions.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NodePathDescription:[");
        int size = this.nodeDescriptions.size();
        while (true) {
            size--;
            if (size >= 0) {
                sb.append("\n\t");
                sb.append(size);
                sb.append(": ");
                sb.append(this.nodeDescriptions.get(size));
            } else {
                sb.append("]");
                return sb.toString();
            }
        }
    }

    public NodePathDescription(NodePathDescription nodePathDescription) {
        ArrayList arrayList = nodePathDescription.nodeDescriptions;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.nodeDescriptions.add(new NodeDescription((NodeDescription) arrayList.get(i)));
        }
    }
}
