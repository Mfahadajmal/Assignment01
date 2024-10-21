package com.google.android.accessibility.utils.input;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Consumer;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HeadsUpNotificationEventInterpreter implements AccessibilityEventListener {
    private final AccessibilityService service;
    public final List listeners = new ArrayList();
    private int headsUpNotificationWindowId = -1;
    private int headsUpNotificationDisplayId = -1;
    private boolean headsUpNotificationTracked = false;
    private long lastNotificationAppearanceTime = -1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Interpretation {
        public final AccessibilityEvent event;
        public final Performance.EventId eventId;
        public final boolean isHeadsUpAppearance;
        public final AccessibilityNodeInfoCompat notificationGuess;

        public Interpretation(AccessibilityEvent accessibilityEvent, Performance.EventId eventId, boolean z, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.event = accessibilityEvent;
            this.eventId = eventId;
            this.isHeadsUpAppearance = z;
            this.notificationGuess = accessibilityNodeInfoCompat;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(this.eventId.toString(), StringBuilderUtils.optionalTag("appeared", this.isHeadsUpAppearance));
        }
    }

    public HeadsUpNotificationEventInterpreter(AccessibilityService accessibilityService) {
        LogUtils.v("HeadsUpNotificationEventInterpreter", "HeadsUpNotificationEventInterpreter constructor", new Object[0]);
        this.service = accessibilityService;
    }

    private final AccessibilityNodeInfoCompat findHeadsUpNotification(int i, int i2) {
        AccessibilityNodeInfoCompat matchingDescendant;
        SparseArray windowsOnAllDisplays = SpannableUtils$IdentifierSpan.getWindowsOnAllDisplays(this.service);
        for (int i3 = 0; i3 < windowsOnAllDisplays.size(); i3++) {
            List<AccessibilityWindowInfo> list = (List) windowsOnAllDisplays.get(i3);
            if ((i2 == -1 || i2 == i3) && list != null) {
                for (AccessibilityWindowInfo accessibilityWindowInfo : list) {
                    if (accessibilityWindowInfo.getType() == 3 && (i == -1 || accessibilityWindowInfo.getId() == i)) {
                        AccessibilityNodeInfoCompat rootCompat = SpannableUtils$IdentifierSpan.getRootCompat(accessibilityWindowInfo);
                        if (rootCompat != null && (matchingDescendant = AccessibilityNodeInfoUtils.getMatchingDescendant(rootCompat, new Filter() { // from class: com.google.android.accessibility.utils.input.HeadsUpNotificationEventInterpreter.1
                            @Override // com.google.android.accessibility.utils.Filter
                            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                                boolean z;
                                boolean z2;
                                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
                                if (accessibilityNodeInfoCompat.getViewIdResourceName() != null) {
                                    z = accessibilityNodeInfoCompat.getViewIdResourceName().contains("expandableNotificationRow");
                                } else {
                                    z = false;
                                }
                                AccessibilityNodeInfoCompat parent = accessibilityNodeInfoCompat.getParent();
                                if (parent != null) {
                                    if (SpannableUtils$IdentifierSpan.getRole(parent) == 30) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    LogUtils.v("HeadsUpNotificationEventInterpreter", "node=%s hasScrollViewParent=%b hasResourceId=%b", accessibilityNodeInfoCompat, Boolean.valueOf(z2), Boolean.valueOf(z));
                                    if (z2 && z) {
                                        return true;
                                    }
                                }
                                return false;
                            }
                        })) != null) {
                            this.headsUpNotificationWindowId = accessibilityWindowInfo.getId();
                            this.headsUpNotificationDisplayId = SpannableUtils$IdentifierSpan.getDisplayId(accessibilityWindowInfo);
                            return matchingDescendant;
                        }
                    }
                }
            }
        }
        return null;
    }

    private final void notifyListeners(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        boolean z;
        if (accessibilityNodeInfoCompat != null) {
            z = true;
        } else {
            z = false;
        }
        LogUtils.v("HeadsUpNotificationEventInterpreter", "heads up notification %s appeared=%b", accessibilityNodeInfoCompat, Boolean.valueOf(z));
        Interpretation interpretation = new Interpretation(accessibilityEvent, eventId, z, accessibilityNodeInfoCompat);
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(interpretation);
        }
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 4194368;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        int windowChanges;
        int windowChanges2;
        AccessibilityNodeInfoCompat findHeadsUpNotification;
        if (SpannableUtils$IdentifierSpan.isAtLeastP()) {
            if (accessibilityEvent.getEventType() == 64) {
                if (!this.headsUpNotificationTracked) {
                    AccessibilityNodeInfoCompat findHeadsUpNotification2 = findHeadsUpNotification(-1, -1);
                    if (findHeadsUpNotification2 != null) {
                        this.headsUpNotificationTracked = true;
                        notifyListeners(findHeadsUpNotification2, accessibilityEvent, eventId);
                        return;
                    } else {
                        this.lastNotificationAppearanceTime = SystemClock.uptimeMillis();
                        return;
                    }
                }
                return;
            }
            if (accessibilityEvent.getEventType() == 4194304) {
                windowChanges = accessibilityEvent.getWindowChanges();
                if ((windowChanges & 2) == 0) {
                    windowChanges2 = accessibilityEvent.getWindowChanges();
                    if ((windowChanges2 & 1) != 0 && !this.headsUpNotificationTracked && SystemClock.uptimeMillis() - this.lastNotificationAppearanceTime < 1000 && (findHeadsUpNotification = findHeadsUpNotification(accessibilityEvent.getWindowId(), SpannableUtils$IdentifierSpan.getDisplayId(accessibilityEvent))) != null) {
                        this.headsUpNotificationTracked = true;
                        notifyListeners(findHeadsUpNotification, accessibilityEvent, eventId);
                        return;
                    }
                    return;
                }
                if (this.headsUpNotificationTracked && accessibilityEvent.getWindowId() == this.headsUpNotificationWindowId && SpannableUtils$IdentifierSpan.getDisplayId(accessibilityEvent) == this.headsUpNotificationDisplayId) {
                    this.headsUpNotificationWindowId = -1;
                    this.headsUpNotificationDisplayId = -1;
                    this.headsUpNotificationTracked = false;
                    notifyListeners(null, accessibilityEvent, eventId);
                }
            }
        }
    }
}
