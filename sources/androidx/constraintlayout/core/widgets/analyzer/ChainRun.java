package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChainRun extends WidgetRun {
    private int mChainStyle;
    ArrayList mWidgets;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        ConstraintWidget constraintWidget2;
        int i2;
        this.mWidgets = new ArrayList();
        this.orientation = i;
        ConstraintWidget constraintWidget3 = this.mWidget;
        ConstraintWidget previousChainMember = constraintWidget3.getPreviousChainMember(i);
        while (true) {
            ConstraintWidget constraintWidget4 = previousChainMember;
            constraintWidget2 = constraintWidget3;
            constraintWidget3 = constraintWidget4;
            if (constraintWidget3 == null) {
                break;
            } else {
                previousChainMember = constraintWidget3.getPreviousChainMember(this.orientation);
            }
        }
        this.mWidget = constraintWidget2;
        this.mWidgets.add(constraintWidget2.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget2.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.mWidgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        ArrayList arrayList = this.mWidgets;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            WidgetRun widgetRun = (WidgetRun) arrayList.get(i3);
            int i4 = this.orientation;
            if (i4 == 0) {
                widgetRun.mWidget.horizontalChainRun = this;
            } else if (i4 == 1) {
                widgetRun.mWidget.verticalChainRun = this;
            }
        }
        if (this.orientation == 0 && ((ConstraintWidgetContainer) this.mWidget.mParent).mIsRtl && this.mWidgets.size() > 1) {
            this.mWidget = ((WidgetRun) this.mWidgets.get(r6.size() - 1)).mWidget;
        }
        if (this.orientation == 0) {
            i2 = this.mWidget.mHorizontalChainStyle;
        } else {
            i2 = this.mWidget.mVerticalChainStyle;
        }
        this.mChainStyle = i2;
    }

    private final ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.mWidgets.size(); i++) {
            ConstraintWidget constraintWidget = ((WidgetRun) this.mWidgets.get(i)).mWidget;
            if (constraintWidget.mVisibility != 8) {
                return constraintWidget;
            }
        }
        return null;
    }

    private final ConstraintWidget getLastVisibleWidget() {
        ConstraintWidget constraintWidget;
        int size = this.mWidgets.size();
        do {
            size--;
            if (size >= 0) {
                constraintWidget = ((WidgetRun) this.mWidgets.get(size)).mWidget;
            } else {
                return null;
            }
        } while (constraintWidget.mVisibility == 8);
        return constraintWidget;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void apply() {
        ArrayList arrayList = this.mWidgets;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((WidgetRun) arrayList.get(i)).apply();
        }
        int size2 = this.mWidgets.size();
        if (size2 <= 0) {
            return;
        }
        ConstraintWidget constraintWidget = ((WidgetRun) this.mWidgets.get(0)).mWidget;
        ConstraintWidget constraintWidget2 = ((WidgetRun) this.mWidgets.get(size2 - 1)).mWidget;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode target$ar$ds$554e726e_0 = getTarget$ar$ds$554e726e_0(constraintAnchor, 0);
            int margin = constraintAnchor.getMargin();
            ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
            if (firstVisibleWidget != null) {
                margin = firstVisibleWidget.mLeft.getMargin();
            }
            if (target$ar$ds$554e726e_0 != null) {
                addTarget$ar$ds(this.start, target$ar$ds$554e726e_0, margin);
            }
            DependencyNode target$ar$ds$554e726e_02 = getTarget$ar$ds$554e726e_0(constraintAnchor2, 0);
            int margin2 = constraintAnchor2.getMargin();
            ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
            if (lastVisibleWidget != null) {
                margin2 = lastVisibleWidget.mRight.getMargin();
            }
            if (target$ar$ds$554e726e_02 != null) {
                addTarget$ar$ds(this.end, target$ar$ds$554e726e_02, -margin2);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode target$ar$ds$554e726e_03 = getTarget$ar$ds$554e726e_0(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
            if (firstVisibleWidget2 != null) {
                margin3 = firstVisibleWidget2.mTop.getMargin();
            }
            if (target$ar$ds$554e726e_03 != null) {
                addTarget$ar$ds(this.start, target$ar$ds$554e726e_03, margin3);
            }
            DependencyNode target$ar$ds$554e726e_04 = getTarget$ar$ds$554e726e_0(constraintAnchor4, 1);
            int margin4 = constraintAnchor4.getMargin();
            ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
            if (lastVisibleWidget2 != null) {
                margin4 = lastVisibleWidget2.mBottom.getMargin();
            }
            if (target$ar$ds$554e726e_04 != null) {
                addTarget$ar$ds(this.end, target$ar$ds$554e726e_04, -margin4);
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void applyToWidget() {
        for (int i = 0; i < this.mWidgets.size(); i++) {
            ((WidgetRun) this.mWidgets.get(i)).applyToWidget();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void clear() {
        this.mRunGroup = null;
        ArrayList arrayList = this.mWidgets;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((WidgetRun) arrayList.get(i)).clear();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final long getWrapDimension() {
        int size = this.mWidgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            j = j + r4.start.mMargin + ((WidgetRun) this.mWidgets.get(i)).getWrapDimension() + r4.end.mMargin;
        }
        return j;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final boolean supportsWrapComputation() {
        int size = this.mWidgets.size();
        for (int i = 0; i < size; i++) {
            if (!((WidgetRun) this.mWidgets.get(i)).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ChainRun ");
        if (this.orientation == 0) {
            str = "horizontal : ";
        } else {
            str = "vertical : ";
        }
        sb.append(str);
        ArrayList arrayList = this.mWidgets;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = (WidgetRun) arrayList.get(i);
            sb.append("<");
            sb.append(widgetRun);
            sb.append("> ");
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0180, code lost:
    
        if (r12.matchConstraintsType != 1) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0182, code lost:
    
        r3 = java.lang.Math.min(r11, r6.wrapValue);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x018a, code lost:
    
        r3 = java.lang.Math.max(r15, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x018e, code lost:
    
        if (r13 <= 0) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0190, code lost:
    
        r3 = java.lang.Math.min(r13, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0194, code lost:
    
        if (r3 == r11) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0196, code lost:
    
        r17 = r17 + 1;
        r11 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0199, code lost:
    
        r12.mDimension.resolve(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01a9, code lost:
    
        r14 = r14 + 1;
        r6 = r20;
        r15 = r21;
        r13 = r22;
        r10 = r23;
        r3 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0189, code lost:
    
        r3 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x016c, code lost:
    
        r22 = r13;
        r13 = r12.mWidget;
        r15 = r13.mMatchConstraintMaxHeight;
        r23 = r10;
        r15 = r13.mMatchConstraintMinHeight;
        r13 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x015b, code lost:
    
        r21 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x019f, code lost:
    
        r24 = r3;
        r23 = r10;
        r22 = r13;
        r21 = r15;
        r19 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01bb, code lost:
    
        r24 = r3;
        r20 = r6;
        r23 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x01c1, code lost:
    
        if (r17 <= 0) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x01c3, code lost:
    
        r5 = r5 - r17;
        r3 = 0;
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x01c7, code lost:
    
        if (r3 >= r1) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x01c9, code lost:
    
        r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r26.mWidgets.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01d7, code lost:
    
        if (r10.mWidget.mVisibility != 8) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01da, code lost:
    
        if (r3 <= 0) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01dc, code lost:
    
        if (r3 < r4) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x01de, code lost:
    
        r6 = r6 + r10.start.mMargin;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x01e3, code lost:
    
        r6 = r6 + r10.mDimension.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01e8, code lost:
    
        if (r3 >= r8) goto L295;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x01ea, code lost:
    
        if (r3 >= r9) goto L296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x01ec, code lost:
    
        r6 = r6 + (-r10.end.mMargin);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x01f2, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x01f7, code lost:
    
        r10 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01fa, code lost:
    
        if (r26.mChainStyle != 2) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01fc, code lost:
    
        if (r17 != 0) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x01fe, code lost:
    
        r3 = 0;
        r26.mChainStyle = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x020c, code lost:
    
        if (r6 <= r2) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x020e, code lost:
    
        r26.mChainStyle = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0210, code lost:
    
        if (r7 <= 0) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0212, code lost:
    
        if (r5 != 0) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0214, code lost:
    
        if (r4 != r9) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0216, code lost:
    
        r26.mChainStyle = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0218, code lost:
    
        r5 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0219, code lost:
    
        r10 = r26.mChainStyle;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x021c, code lost:
    
        if (r10 != 1) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x021e, code lost:
    
        if (r7 <= 1) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0220, code lost:
    
        r2 = (r2 - r6) / (r7 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x022c, code lost:
    
        if (r5 <= 0) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x022e, code lost:
    
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x022f, code lost:
    
        r5 = r3;
        r10 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0232, code lost:
    
        if (r5 >= r1) goto L298;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0234, code lost:
    
        if (r24 == false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0236, code lost:
    
        r3 = r1 - (r5 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x023c, code lost:
    
        r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r26.mWidgets.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x024a, code lost:
    
        if (r3.mWidget.mVisibility != 8) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x024c, code lost:
    
        r3.start.resolve(r10);
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02b6, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0257, code lost:
    
        if (r5 <= 0) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0259, code lost:
    
        if (r24 == false) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x025b, code lost:
    
        r10 = r10 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x025d, code lost:
    
        r10 = r10 + r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x025e, code lost:
    
        if (r5 <= 0) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0260, code lost:
    
        if (r5 < r4) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0262, code lost:
    
        if (r24 == false) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0264, code lost:
    
        r10 = r10 - r3.start.mMargin;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x026a, code lost:
    
        r10 = r10 + r3.start.mMargin;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x026f, code lost:
    
        if (r24 == false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0271, code lost:
    
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x027c, code lost:
    
        r6 = r3.mDimension.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x0283, code lost:
    
        if (r3.mDimensionBehavior$ar$edu != 3) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0288, code lost:
    
        if (r3.matchConstraintsType != 1) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x028a, code lost:
    
        r6 = r3.mDimension.wrapValue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x028e, code lost:
    
        if (r24 == false) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0290, code lost:
    
        r10 = r10 - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x0293, code lost:
    
        if (r24 == false) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0295, code lost:
    
        r3.start.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x02a0, code lost:
    
        r3.mResolved = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x02a3, code lost:
    
        if (r5 >= r8) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x02a5, code lost:
    
        if (r5 >= r9) goto L302;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x02a7, code lost:
    
        if (r24 == false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x02a9, code lost:
    
        r10 = r10 - (-r3.end.mMargin);
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x02b0, code lost:
    
        r10 = r10 + (-r3.end.mMargin);
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x029b, code lost:
    
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x0292, code lost:
    
        r10 = r10 + r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0277, code lost:
    
        r3.start.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x023b, code lost:
    
        r3 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0225, code lost:
    
        if (r7 != 1) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0227, code lost:
    
        r2 = (r2 - r6) / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x022b, code lost:
    
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x02ba, code lost:
    
        if (r10 != 0) goto L225;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x02bc, code lost:
    
        r2 = (r2 - r6) / (r7 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x02c0, code lost:
    
        if (r5 <= 0) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x02c2, code lost:
    
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x02c3, code lost:
    
        r5 = r3;
        r10 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x02c6, code lost:
    
        if (r5 >= r1) goto L304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x02c8, code lost:
    
        if (r24 == false) goto L193;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x02ca, code lost:
    
        r3 = r1 - (r5 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x02d0, code lost:
    
        r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r26.mWidgets.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x02de, code lost:
    
        if (r3.mWidget.mVisibility != 8) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x02e0, code lost:
    
        r3.start.resolve(r10);
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0349, code lost:
    
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x02eb, code lost:
    
        if (r24 == false) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x02ed, code lost:
    
        r10 = r10 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x02f0, code lost:
    
        if (r5 <= 0) goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x02f2, code lost:
    
        if (r5 < r4) goto L205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x02f4, code lost:
    
        if (r24 == false) goto L204;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x02f6, code lost:
    
        r10 = r10 - r3.start.mMargin;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x02fc, code lost:
    
        r10 = r10 + r3.start.mMargin;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x0301, code lost:
    
        if (r24 == false) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x0303, code lost:
    
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x030e, code lost:
    
        r6 = r3.mDimension.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0315, code lost:
    
        if (r3.mDimensionBehavior$ar$edu != 3) goto L213;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x031a, code lost:
    
        if (r3.matchConstraintsType != 1) goto L213;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x031c, code lost:
    
        r6 = java.lang.Math.min(r6, r3.mDimension.wrapValue);
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0324, code lost:
    
        if (r24 == false) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x0326, code lost:
    
        r10 = r10 - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0329, code lost:
    
        if (r24 == false) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x032b, code lost:
    
        r3.start.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0336, code lost:
    
        if (r5 >= r8) goto L307;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0338, code lost:
    
        if (r5 >= r9) goto L308;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x033a, code lost:
    
        if (r24 == false) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x033c, code lost:
    
        r10 = r10 - (-r3.end.mMargin);
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0343, code lost:
    
        r10 = r10 + (-r3.end.mMargin);
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0331, code lost:
    
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x0328, code lost:
    
        r10 = r10 + r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0309, code lost:
    
        r3.start.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x02ef, code lost:
    
        r10 = r10 + r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:244:0x02cf, code lost:
    
        r3 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x034e, code lost:
    
        if (r10 != 2) goto L316;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x0350, code lost:
    
        r2 = r2 - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x0353, code lost:
    
        if (r26.orientation != 0) goto L230;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x0355, code lost:
    
        r6 = r26.mWidget.mHorizontalBiasPercent;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x035e, code lost:
    
        if (r24 == false) goto L233;
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x0360, code lost:
    
        r6 = 1.0f - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x0364, code lost:
    
        r2 = (int) ((r2 * r6) + 0.5f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x036a, code lost:
    
        if (r2 < 0) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x036c, code lost:
    
        if (r5 <= 0) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x036f, code lost:
    
        if (r24 == false) goto L239;
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0371, code lost:
    
        r10 = r23 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x0376, code lost:
    
        r5 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x0377, code lost:
    
        if (r5 >= r1) goto L310;
     */
    /* JADX WARN: Code restructure failed: missing block: B:261:0x0379, code lost:
    
        r2 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x037b, code lost:
    
        if (r24 == false) goto L245;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x037d, code lost:
    
        r3 = r1 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0381, code lost:
    
        r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r26.mWidgets.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x038f, code lost:
    
        if (r3.mWidget.mVisibility != 8) goto L249;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x0391, code lost:
    
        r3.start.resolve(r10);
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x03f5, code lost:
    
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x039e, code lost:
    
        if (r5 <= 0) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:270:0x03a0, code lost:
    
        if (r5 < r4) goto L254;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x03a2, code lost:
    
        if (r24 == false) goto L253;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x03a4, code lost:
    
        r10 = r10 - r3.start.mMargin;
     */
    /* JADX WARN: Code restructure failed: missing block: B:273:0x03aa, code lost:
    
        r10 = r10 + r3.start.mMargin;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x03af, code lost:
    
        if (r24 == false) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x03b1, code lost:
    
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x03bc, code lost:
    
        r6 = r3.mDimension.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x03c3, code lost:
    
        if (r3.mDimensionBehavior$ar$edu != 3) goto L262;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x03c8, code lost:
    
        if (r3.matchConstraintsType != 1) goto L263;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x03ca, code lost:
    
        r6 = r3.mDimension.wrapValue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x03d0, code lost:
    
        if (r24 == false) goto L265;
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x03d2, code lost:
    
        r10 = r10 - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:283:0x03d5, code lost:
    
        if (r24 == false) goto L268;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x03d7, code lost:
    
        r3.start.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x03e2, code lost:
    
        if (r5 >= r8) goto L313;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x03e4, code lost:
    
        if (r5 >= r9) goto L314;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x03e6, code lost:
    
        if (r24 == false) goto L273;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x03e8, code lost:
    
        r10 = r10 - (-r3.end.mMargin);
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x03ef, code lost:
    
        r10 = r10 + (-r3.end.mMargin);
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x03dd, code lost:
    
        r3.end.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x03d4, code lost:
    
        r10 = r10 + r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x03b7, code lost:
    
        r3.start.resolve(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x0380, code lost:
    
        r3 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x0374, code lost:
    
        r10 = r23 + r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x036e, code lost:
    
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x035a, code lost:
    
        r6 = r26.mWidget.mVerticalBiasPercent;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x0202, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x01f5, code lost:
    
        r6 = r20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:307:0x0204, code lost:
    
        r24 = r3;
        r23 = r10;
        r3 = 0;
        r10 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00bb, code lost:
    
        if (r5.mDimension.resolved != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00ee, code lost:
    
        r5 = r16;
        r7 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00f8, code lost:
    
        r10 = r26.start.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00fc, code lost:
    
        if (r3 == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00fe, code lost:
    
        r10 = r26.end.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0104, code lost:
    
        if (r6 <= r2) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0106, code lost:
    
        r13 = r6 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x010a, code lost:
    
        if (r3 == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x010c, code lost:
    
        r10 = r10 + ((int) ((r13 / 2.0f) + 0.5f));
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0112, code lost:
    
        r10 = r10 - ((int) ((r13 / 2.0f) + 0.5f));
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0117, code lost:
    
        if (r5 <= 0) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0119, code lost:
    
        r13 = r2 - r6;
        r15 = (r13 / r5) + 0.5f;
        r14 = 0;
        r17 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0123, code lost:
    
        if (r14 >= r1) goto L290;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0125, code lost:
    
        r12 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r26.mWidgets.get(r14);
        r20 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0135, code lost:
    
        if (r12.mWidget.mVisibility == 8) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x013a, code lost:
    
        if (r12.mDimensionBehavior$ar$edu != 3) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x013c, code lost:
    
        r6 = r12.mDimension;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0140, code lost:
    
        if (r6.resolved != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0142, code lost:
    
        r11 = (int) r15;
        r19 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0147, code lost:
    
        if (r18 <= 0.0f) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0149, code lost:
    
        r21 = r15;
        r11 = (int) (((r12.mWidget.mWeight[r26.orientation] * r13) / r18) + 0.5f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x015f, code lost:
    
        if (r26.orientation != 0) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0161, code lost:
    
        r15 = r12.mWidget;
        r22 = r13;
        r13 = r15.mMatchConstraintMaxWidth;
        r15 = r15.mMatchConstraintMinWidth;
        r23 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x017b, code lost:
    
        r24 = r3;
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r27) {
        /*
            Method dump skipped, instructions count: 1016
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }
}
