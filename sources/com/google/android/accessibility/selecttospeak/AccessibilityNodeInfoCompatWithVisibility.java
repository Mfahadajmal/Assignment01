package com.google.android.accessibility.selecttospeak;

import android.graphics.Rect;
import android.graphics.Region;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.RectUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.ocr.OcrController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.mlkit.vision.text.Text$TextBase;
import com.google.mlkit.vision.text.Text$TextBlock;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityNodeInfoCompatWithVisibility extends AccessibilityNodeInfoCompat {
    public static final boolean shouldCalculateNonOverlapBounds = true;
    public boolean isImage;
    public Rect nonOverlapBoundInScreen;
    public List ocrTextBlocks;
    private Rect visibleBoundsInScreen;
    private List windowsAbove;
    private SparseArray wordBounds;

    public AccessibilityNodeInfoCompatWithVisibility(Object obj, List list) {
        super(obj);
        this.visibleBoundsInScreen = null;
        this.nonOverlapBoundInScreen = null;
        this.isImage = false;
        this.ocrTextBlocks = null;
        this.wordBounds = null;
        obj.getClass();
        if (list == null) {
            this.windowsAbove = Collections.emptyList();
        } else {
            this.windowsAbove = new ArrayList(list);
        }
    }

    public static boolean contains(Region region, Rect rect) {
        if (region.quickReject(rect)) {
            return false;
        }
        if (region.quickContains(rect)) {
            return true;
        }
        Region region2 = new Region(rect);
        region2.op(region, Region.Op.INTERSECT);
        return region2.equals(new Region(rect));
    }

    public static AccessibilityNodeInfoCompatWithVisibility obtain(AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility) {
        Rect rect;
        AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility2 = new AccessibilityNodeInfoCompatWithVisibility(accessibilityNodeInfoCompatWithVisibility.mInfo, DesugarCollections.unmodifiableList(accessibilityNodeInfoCompatWithVisibility.windowsAbove));
        Rect rect2 = accessibilityNodeInfoCompatWithVisibility.visibleBoundsInScreen;
        Rect rect3 = null;
        if (rect2 == null) {
            rect = null;
        } else {
            rect = new Rect(rect2);
        }
        accessibilityNodeInfoCompatWithVisibility2.visibleBoundsInScreen = rect;
        Rect rect4 = accessibilityNodeInfoCompatWithVisibility.nonOverlapBoundInScreen;
        if (rect4 != null) {
            rect3 = new Rect(rect4);
        }
        accessibilityNodeInfoCompatWithVisibility2.nonOverlapBoundInScreen = rect3;
        return accessibilityNodeInfoCompatWithVisibility2;
    }

    public static void reduceVisibleRectangleForDrawingOrderRecursive(Rect rect, AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility, HashSet hashSet) {
        if (accessibilityNodeInfoCompatWithVisibility != null && !hashSet.contains(accessibilityNodeInfoCompatWithVisibility)) {
            hashSet.add(accessibilityNodeInfoCompatWithVisibility);
            AccessibilityNodeInfoCompatWithVisibility parent = accessibilityNodeInfoCompatWithVisibility.getParent();
            if (parent != null) {
                Rect rect2 = new Rect();
                for (int i = 0; i < parent.getChildCount(); i++) {
                    AccessibilityNodeInfoCompatWithVisibility child = parent.getChild(i);
                    if (child != null && !accessibilityNodeInfoCompatWithVisibility.equals(child) && child.getDrawingOrder() >= accessibilityNodeInfoCompatWithVisibility.getDrawingOrder()) {
                        child.getVisibleBoundsInScreen(rect2);
                        if (!RectUtils.isEmpty(rect2) && Rect.intersects(rect, rect2)) {
                            RectUtils.adjustRectToAvoidIntersection(rect, rect2);
                            if (RectUtils.isEmpty(rect)) {
                                return;
                            }
                        }
                    }
                }
                reduceVisibleRectangleForDrawingOrderRecursive(rect, accessibilityNodeInfoCompatWithVisibility.getParent(), hashSet);
            }
        }
    }

    public final boolean getHasOcrBlocks() {
        List list = this.ocrTextBlocks;
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    public final CharSequence getNodeDescription() {
        List list = this.ocrTextBlocks;
        if (list != null) {
            String textFromBlocks = OcrController.getTextFromBlocks(list);
            if (!TextUtils.isEmpty(textFromBlocks)) {
                return textFromBlocks;
            }
        }
        CharSequence text = getText();
        if (!TextUtils.isEmpty(text)) {
            return text;
        }
        CharSequence contentDescription = getContentDescription();
        if (!TextUtils.isEmpty(contentDescription)) {
            return contentDescription;
        }
        return null;
    }

    public final Rect getSpecificWordBounds(int i) {
        if (this.ocrTextBlocks != null) {
            Rect rect = new Rect();
            getVisibleBoundsInScreen(rect);
            if (this.wordBounds == null) {
                List list = this.ocrTextBlocks;
                SparseArray sparseArray = new SparseArray();
                int i2 = 0;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    Text$TextBlock text$TextBlock = (Text$TextBlock) list.get(i3);
                    Iterator it = text$TextBlock.getLines().iterator();
                    while (it.hasNext()) {
                        for (Text$TextBase text$TextBase : ((Text$TextBlock) it.next()).getElements()) {
                            sparseArray.append(i2, text$TextBase.boundingBox);
                            i2 += text$TextBase.getTextInternal().trim().length() + 1;
                        }
                    }
                    text$TextBlock.getLines().isEmpty();
                }
                this.wordBounds = sparseArray;
            }
            Rect rect2 = (Rect) this.wordBounds.get(i);
            if (rect2 != null) {
                rect2.offset(rect.left, rect.top);
                return rect2;
            }
            return null;
        }
        return null;
    }

    public final void getVisibleBoundsInScreen(Rect rect) {
        if (this.visibleBoundsInScreen == null) {
            try {
                this.visibleBoundsInScreen = new Rect();
                if (!isVisibleToUser()) {
                    this.visibleBoundsInScreen.setEmpty();
                } else {
                    getBoundsInScreen(this.visibleBoundsInScreen);
                    Rect rect2 = this.visibleBoundsInScreen;
                    Rect rect3 = new Rect();
                    for (int i = 0; i < this.windowsAbove.size(); i++) {
                        ((AccessibilityWindowInfo) this.windowsAbove.get(i)).getBoundsInScreen(rect3);
                        rect2.sort();
                        rect3.sort();
                        if (Rect.intersects(rect2, rect3)) {
                            LogUtils.v("NodeInfoCompatWithVisib", "Reduce visible rectangle for windows above. Node: %s; Window: %s", this, this.windowsAbove.get(i));
                            RectUtils.adjustRectToAvoidIntersection(rect2, rect3);
                        }
                    }
                }
            } catch (Exception unused) {
                LogUtils.e("NodeInfoCompatWithVisib", "Fail to update bound underneath windows for node: %s", this);
            }
        }
        rect.set(this.visibleBoundsInScreen);
    }

    public final boolean intersect(Rect rect) {
        if (!isVisibleToUser()) {
            return false;
        }
        Rect rect2 = new Rect();
        getBoundsInScreen(rect2);
        if (!rect2.intersect(rect)) {
            return false;
        }
        for (int i = 0; i < this.windowsAbove.size(); i++) {
            AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) this.windowsAbove.get(i);
            Region regionInScreen = SpannableUtils$IdentifierSpan.getRegionInScreen(accessibilityWindowInfo);
            if (regionInScreen != null && contains(regionInScreen, rect)) {
                LogUtils.e("NodeInfoCompatWithVisib", "User selected area (%s) covered underneath window: %s for node: %s", rect, accessibilityWindowInfo, this);
                return false;
            }
        }
        return true;
    }

    public final boolean isVisibleToUserBeneathWindows() {
        if (!isVisibleToUser()) {
            return false;
        }
        Rect rect = new Rect();
        getVisibleBoundsInScreen(rect);
        if (RectUtils.isEmpty(rect)) {
            return false;
        }
        return true;
    }

    @Override // androidx.core.view.accessibility.AccessibilityNodeInfoCompat
    public final AccessibilityNodeInfoCompatWithVisibility getChild(int i) {
        AccessibilityNodeInfo child = this.mInfo.getChild(i);
        if (child == null) {
            return null;
        }
        return new AccessibilityNodeInfoCompatWithVisibility(child, this.windowsAbove);
    }

    @Override // androidx.core.view.accessibility.AccessibilityNodeInfoCompat
    public final AccessibilityNodeInfoCompatWithVisibility getParent() {
        AccessibilityNodeInfo parent = this.mInfo.getParent();
        if (parent == null) {
            return null;
        }
        return new AccessibilityNodeInfoCompatWithVisibility(parent, this.windowsAbove);
    }

    public final AccessibilityNodeInfoCompat compat() {
        return this;
    }
}
