package com.google.android.accessibility.utils.input;

import android.accessibilityservice.AccessibilityService;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ReadOnly;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.Statistics;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.monitor.DisplayMonitor;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowEventInterpreter implements WindowsDelegate, DisplayMonitor.DisplayStateChangedListener {
    public Announcement announcement;
    public Boolean defaultDisplayOn;
    public final DisplayMonitor displayMonitor;
    public final boolean isSplitScreenModeAvailable;
    public WindowRoles pendingWindowRoles;
    public final AccessibilityService service;
    public final HashMap windowIdToData = new HashMap();
    public WindowRoles windowRoles = new WindowRoles();
    public int picInPicLastShownId = -1;
    public long picInPicDisappearTime = 0;
    public boolean reduceDelayPref = false;
    public long screenTransitionStartTime = 0;
    public boolean areWindowsChanging = false;
    public boolean recentKeyboardWindowChange = false;
    public final WindowEventDelayer windowEventDelayer = new WindowEventDelayer(this);
    private final List listeners = new ArrayList();
    public final List priorityListeners = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Announcement {
        public final boolean isFromInputMethodEditor;
        public final boolean isFromVolumeControlPanel;
        public final CharSequence packageName;
        public final CharSequence text;

        public Announcement() {
        }

        public final boolean equals(Object obj) {
            CharSequence charSequence;
            if (obj == this) {
                return true;
            }
            if (obj instanceof Announcement) {
                Announcement announcement = (Announcement) obj;
                if (this.text.equals(announcement.text()) && ((charSequence = this.packageName) != null ? charSequence.equals(announcement.packageName()) : announcement.packageName() == null) && this.isFromVolumeControlPanel == announcement.isFromVolumeControlPanel() && this.isFromInputMethodEditor == announcement.isFromInputMethodEditor()) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int hashCode2 = this.text.hashCode() ^ 1000003;
            CharSequence charSequence = this.packageName;
            if (charSequence == null) {
                hashCode = 0;
            } else {
                hashCode = charSequence.hashCode();
            }
            int i2 = ((hashCode2 * 1000003) ^ hashCode) * 1000003;
            int i3 = 1237;
            if (true != this.isFromVolumeControlPanel) {
                i = 1237;
            } else {
                i = 1231;
            }
            int i4 = (i2 ^ i) * 1000003;
            if (true == this.isFromInputMethodEditor) {
                i3 = 1231;
            }
            return i4 ^ i3;
        }

        public final boolean isFromInputMethodEditor() {
            return this.isFromInputMethodEditor;
        }

        public final boolean isFromVolumeControlPanel() {
            return this.isFromVolumeControlPanel;
        }

        public final CharSequence packageName() {
            return this.packageName;
        }

        public final CharSequence text() {
            return this.text;
        }

        public final String toString() {
            return "Announcement{text=" + this.text.toString() + ", packageName=" + String.valueOf(this.packageName) + ", isFromVolumeControlPanel=" + this.isFromVolumeControlPanel + ", isFromInputMethodEditor=" + this.isFromInputMethodEditor + "}";
        }

        public Announcement(CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
            this();
            if (charSequence == null) {
                throw new NullPointerException("Null text");
            }
            this.text = charSequence;
            this.packageName = charSequence2;
            this.isFromVolumeControlPanel = z;
            this.isFromInputMethodEditor = z2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EventInterpretation extends ReadOnly {
        public int anchorNodeRole;
        public Announcement announcement;
        public boolean horizontalPlacement;
        public Rect sourceBoundsInScreen;
        public int windowIdFromEvent = -1;
        public final WindowInterpretation windowA = new WindowInterpretation();
        public final WindowInterpretation windowB = new WindowInterpretation();
        public final WindowInterpretation accessibilityOverlay = new WindowInterpretation();
        public final WindowInterpretation picInPic = new WindowInterpretation();
        public final WindowInterpretation inputMethod = new WindowInterpretation();
        public boolean mainWindowsChanged = false;
        public boolean picInPicChanged = false;
        public boolean windowsStable = false;
        public boolean originalEvent = false;
        public boolean allowAnnounce = true;
        public boolean inputMethodChanged = false;
        public boolean shouldAnnounceInputMethodChange = false;
        public int displayId = 0;
        public int eventType = 0;
        public long eventStartTime = 0;
        public long maxDelayMs = 550;
        public long totalDelayMs = 0;
        public boolean interpretFirstTimeWhenWakeUp = false;
        public int changeTypes = 0;

        public final void setAnnouncement(Announcement announcement) {
            checkIsWritable();
            this.announcement = announcement;
        }

        public final void setChangeTypes(int i) {
            checkIsWritable();
            this.changeTypes = i;
        }

        public final void setMainWindowsChanged(boolean z) {
            checkIsWritable();
            this.mainWindowsChanged = z;
        }

        public final void setOriginalEvent(boolean z) {
            checkIsWritable();
            this.originalEvent = z;
        }

        public final void setShouldAnnounceInputMethodChange(boolean z) {
            checkIsWritable();
            this.shouldAnnounceInputMethodChange = z;
        }

        public final void setWindowsStable(boolean z) {
            checkIsWritable();
            this.windowsStable = z;
        }

        public final String toString() {
            String typeToString;
            String sb;
            String optionalInt = StringBuilderUtils.optionalInt("WindowIdFromEvent", this.windowIdFromEvent, -1);
            String optionalTag = StringBuilderUtils.optionalTag("MainWindowsChanged", this.mainWindowsChanged);
            String optionalTag2 = StringBuilderUtils.optionalTag("PicInPicChanged", this.picInPicChanged);
            String optionalTag3 = StringBuilderUtils.optionalTag("inputMethodChanged", this.shouldAnnounceInputMethodChange);
            String optionalTag4 = StringBuilderUtils.optionalTag("horizontalPlacement", this.horizontalPlacement);
            String optionalTag5 = StringBuilderUtils.optionalTag("WindowsStable", this.windowsStable);
            String optionalTag6 = StringBuilderUtils.optionalTag("OriginalEvent", this.originalEvent);
            String optionalTag7 = StringBuilderUtils.optionalTag("allowAnnounce", this.allowAnnounce);
            String optionalTag8 = StringBuilderUtils.optionalTag("interpretFirstTimeWhenWakeUp", this.interpretFirstTimeWhenWakeUp);
            String optionalInt2 = StringBuilderUtils.optionalInt("displayId", this.displayId, 0);
            int i = this.eventType;
            if (i == 0) {
                typeToString = null;
            } else {
                typeToString = SpannableUtils$IdentifierSpan.typeToString(i);
            }
            String optionalField = StringBuilderUtils.optionalField("EventType", typeToString);
            int i2 = this.eventType;
            if (i2 != 32) {
                if (i2 == 4194304) {
                    int i3 = this.changeTypes;
                    StringBuilder sb2 = new StringBuilder();
                    if ((i3 & 1) != 0) {
                        sb2.append("WINDOWS_CHANGE_ADDED");
                    }
                    if ((i3 & 2) != 0) {
                        sb2.append("WINDOWS_CHANGE_REMOVED");
                    }
                    if ((i3 & 4) != 0) {
                        sb2.append("WINDOWS_CHANGE_TITLE");
                    }
                    if (sb2.length() != 0) {
                        sb = sb2.toString();
                    }
                }
                sb = null;
            } else {
                int i4 = this.changeTypes;
                StringBuilder sb3 = new StringBuilder();
                if ((i4 & 8) != 0) {
                    sb3.append("CONTENT_CHANGE_TYPE_PANE_TITLE");
                }
                if ((i4 & 16) != 0) {
                    sb3.append("CONTENT_CHANGE_TYPE_PANE_APPEARED");
                }
                if ((i4 & 32) != 0) {
                    sb3.append("CONTENT_CHANGE_TYPE_PANE_DISAPPEARED");
                }
                if (sb3.length() != 0) {
                    sb = sb3.toString();
                }
                sb = null;
            }
            return StringBuilderUtils.joinStrings("\n  ", new String[]{StringBuilderUtils.joinFields(optionalInt, optionalTag, optionalTag2, optionalTag3, optionalTag4, optionalTag5, optionalTag6, optionalTag7, optionalTag8, optionalInt2, optionalField, StringBuilderUtils.optionalField("ChangeTypes", sb), StringBuilderUtils.optionalSubObj("Announcement", this.announcement), StringBuilderUtils.optionalInt$ar$ds("eventStartTime", this.eventStartTime), StringBuilderUtils.optionalInt$ar$ds("maxDelayMs", this.maxDelayMs), StringBuilderUtils.optionalInt$ar$ds("totalDelayMs", this.totalDelayMs), StringBuilderUtils.optionalSubObj("sourceBoundsInScreen", this.sourceBoundsInScreen)), StringBuilderUtils.optionalSubObj("WindowA", this.windowA), StringBuilderUtils.optionalSubObj("WindowB", this.windowB), StringBuilderUtils.optionalSubObj("A11yOverlay", this.accessibilityOverlay), StringBuilderUtils.optionalSubObj("PicInPic", this.picInPic), StringBuilderUtils.optionalSubObj("inputMethod", this.inputMethod), StringBuilderUtils.optionalSubObj("AnchorNodeRole", Integer.valueOf(this.anchorNodeRole))});
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Window {
        public CharSequence eventPackageName;
        public CharSequence title;
        public CharSequence titleFromStateChange;
        public CharSequence titleFromWindowChange;
        public boolean hasAccessibilityPane = false;
        public int eventSourceRole = 0;

        public final void setTitleFromWindowChange(CharSequence charSequence) {
            if (!TextUtils.equals(this.titleFromWindowChange, charSequence)) {
                this.titleFromStateChange = null;
            }
            this.titleFromWindowChange = charSequence;
        }

        public final String toString() {
            return "{ " + StringBuilderUtils.joinFields(StringBuilderUtils.optionalText("title", this.title), StringBuilderUtils.optionalText("titleFromWindowChange", this.titleFromWindowChange), StringBuilderUtils.optionalText("eventSourceRole", SpannableUtils$IdentifierSpan.roleToString(this.eventSourceRole)), StringBuilderUtils.optionalText("eventPackageName", this.eventPackageName)) + "}";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WindowEventDelayer extends WeakReferenceHandler {
        public WindowEventDelayer(WindowEventInterpreter windowEventInterpreter) {
            super(windowEventInterpreter, Looper.myLooper());
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            WindowEventInterpreter windowEventInterpreter = (WindowEventInterpreter) obj;
            if (message.what == 1) {
                WindowTrackerFactory windowTrackerFactory = (WindowTrackerFactory) message.obj;
                EventInterpretation eventInterpretation = (EventInterpretation) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
                Object obj2 = windowTrackerFactory.WindowTrackerFactory$ar$executorProvider;
                SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 0, "delayedInterpret()", new Object[0]);
                eventInterpretation.setOriginalEvent(false);
                WindowRoles windowRoles = windowEventInterpreter.pendingWindowRoles;
                if (windowRoles == null) {
                    windowRoles = windowEventInterpreter.windowRoles;
                }
                WindowRoles windowRoles2 = new WindowRoles(windowRoles);
                windowEventInterpreter.updateWindowRoles$ar$ds(eventInterpretation, windowEventInterpreter.service, windowRoles2);
                windowEventInterpreter.setWindowTitles(windowRoles2);
                windowEventInterpreter.detectWindowChanges$ar$ds(windowRoles2, eventInterpretation);
                windowEventInterpreter.detectInputMethodChanged$ar$ds(windowRoles2, eventInterpretation, true);
                LogUtils.v("WindowEventInterpreter", "END delayedInterpret() interpretation=%s", eventInterpretation);
                if ((eventInterpretation.windowA.hasTitleFromStateChange() && eventInterpretation.windowB.hasTitleFromStateChange() && eventInterpretation.accessibilityOverlay.hasTitleFromStateChange() && eventInterpretation.picInPic.hasTitleFromStateChange() && eventInterpretation.inputMethod.hasTitleFromStateChange()) || eventInterpretation.maxDelayMs <= eventInterpretation.totalDelayMs) {
                    eventInterpretation.setWindowsStable(true);
                    windowEventInterpreter.setRoles(windowRoles2);
                    windowEventInterpreter.notifyInterpretationListeners(eventInterpretation, (Performance.EventId) obj2);
                    return;
                }
                windowEventInterpreter.delayInterpret(eventInterpretation, (Performance.EventId) obj2);
                return;
            }
            if (message.what == 2) {
                windowEventInterpreter.recentKeyboardWindowChange = false;
                LogUtils.v("WindowEventInterpreter", "IME transition finished & start to support Announcement from IME.", new Object[0]);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface WindowEventHandler {
        void handle(EventInterpretation eventInterpretation, Performance.EventId eventId);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WindowInterpretation extends ReadOnly {
        public int id = -1;
        public int oldId = -1;
        public CharSequence oldTitle;
        public CharSequence title;
        public CharSequence titleForFeedback;
        public CharSequence titleFromStateChange;

        public final boolean hasTitleFromStateChange() {
            if (idOrTitleChanged() && this.titleFromStateChange == null) {
                return false;
            }
            return true;
        }

        public final boolean idOrTitleChanged() {
            if (this.oldId == this.id && TextUtils.equals(this.oldTitle, this.title)) {
                return false;
            }
            return true;
        }

        public final String toString() {
            return StringBuilderUtils.joinFields(StringBuilderUtils.optionalInt("ID", this.id, -1), StringBuilderUtils.optionalText("Title", this.title), StringBuilderUtils.optionalText("titleFromStateChange", this.titleFromStateChange), StringBuilderUtils.optionalText("TitleForFeedback", this.titleForFeedback), StringBuilderUtils.optionalInt("OldID", this.oldId, -1), StringBuilderUtils.optionalText("OldTitle", this.oldTitle));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WindowRoles {
        public int accessibilityOverlayWindowId;
        public CharSequence accessibilityOverlayWindowTitle;
        public int inputMethodWindowId;
        public CharSequence inputMethodWindowTitle;
        public int picInPicWindowId;
        public CharSequence picInPicWindowTitle;
        public int windowIdA;
        public int windowIdB;
        public CharSequence windowTitleA;
        public CharSequence windowTitleB;

        public WindowRoles() {
            this.windowIdA = -1;
            this.windowIdB = -1;
            this.accessibilityOverlayWindowId = -1;
            this.picInPicWindowId = -1;
            this.inputMethodWindowId = -1;
        }

        public final void clear() {
            this.windowIdA = -1;
            this.windowTitleA = null;
            this.windowIdB = -1;
            this.windowTitleB = null;
            this.accessibilityOverlayWindowId = -1;
            this.accessibilityOverlayWindowTitle = null;
            this.picInPicWindowId = -1;
            this.picInPicWindowTitle = null;
            this.inputMethodWindowId = -1;
            this.inputMethodWindowTitle = null;
        }

        public final String toString() {
            return String.format("a:%s:%s b:%s:%s accessOverlay:%s:%s picInPic:%s:%s inputMethod:%s:%s", Integer.valueOf(this.windowIdA), this.windowTitleA, Integer.valueOf(this.windowIdB), this.windowTitleB, Integer.valueOf(this.accessibilityOverlayWindowId), this.accessibilityOverlayWindowTitle, Integer.valueOf(this.picInPicWindowId), this.picInPicWindowTitle, Integer.valueOf(this.inputMethodWindowId), this.inputMethodWindowTitle);
        }

        public WindowRoles(WindowRoles windowRoles) {
            this.windowIdA = -1;
            this.windowIdB = -1;
            this.accessibilityOverlayWindowId = -1;
            this.picInPicWindowId = -1;
            this.inputMethodWindowId = -1;
            this.windowIdA = windowRoles.windowIdA;
            this.windowTitleA = windowRoles.windowTitleA;
            this.windowIdB = windowRoles.windowIdB;
            this.windowTitleB = windowRoles.windowTitleB;
            this.accessibilityOverlayWindowId = windowRoles.accessibilityOverlayWindowId;
            this.accessibilityOverlayWindowTitle = windowRoles.accessibilityOverlayWindowTitle;
            this.picInPicWindowId = windowRoles.picInPicWindowId;
            this.picInPicWindowTitle = windowRoles.picInPicWindowTitle;
            this.inputMethodWindowId = windowRoles.inputMethodWindowId;
            this.inputMethodWindowTitle = windowRoles.inputMethodWindowTitle;
        }
    }

    public WindowEventInterpreter(AccessibilityService accessibilityService, DisplayMonitor displayMonitor) {
        new Statistics();
        this.defaultDisplayOn = null;
        this.service = accessibilityService;
        this.displayMonitor = displayMonitor;
        this.isSplitScreenModeAvailable = !FeatureSupport.isTv(accessibilityService);
    }

    private static List getAllWindows(AccessibilityService accessibilityService) {
        ArrayList arrayList = new ArrayList();
        SparseArray windowsOnAllDisplays = SpannableUtils$IdentifierSpan.getWindowsOnAllDisplays(accessibilityService);
        int size = windowsOnAllDisplays.size();
        for (int i = 0; i < size; i++) {
            arrayList.addAll((Collection) windowsOnAllDisplays.valueAt(i));
        }
        return arrayList;
    }

    public static CharSequence getTextFromWindowStateChange(AccessibilityEvent accessibilityEvent, boolean z) {
        if (z && !TextUtils.isEmpty(accessibilityEvent.getContentDescription())) {
            return accessibilityEvent.getContentDescription();
        }
        List<CharSequence> text = accessibilityEvent.getText();
        if (!text.isEmpty()) {
            return text.get(0);
        }
        return null;
    }

    private static CharSequence getWindowPackageName(AccessibilityService accessibilityService, int i) {
        for (AccessibilityWindowInfo accessibilityWindowInfo : getAllWindows(accessibilityService)) {
            if (accessibilityWindowInfo.getId() == i) {
                AccessibilityNodeInfo root = SpannableUtils$IdentifierSpan.getRoot(accessibilityWindowInfo);
                if (root != null) {
                    return root.getPackageName();
                }
                return null;
            }
        }
        return null;
    }

    private final CharSequence getWindowTitleInternal(int i) {
        return getWindowTitleInternal(i, this.areWindowsChanging);
    }

    public static boolean isPaneContentChangeTypes(int i) {
        if ((i & 56) != 0) {
            return true;
        }
        return false;
    }

    private final void setNewWindowInterpretation(int i, WindowInterpretation windowInterpretation) {
        CharSequence charSequence;
        windowInterpretation.checkIsWritable();
        windowInterpretation.id = i;
        CharSequence windowTitleInternal = getWindowTitleInternal(i);
        windowInterpretation.checkIsWritable();
        windowInterpretation.title = windowTitleInternal;
        Window window = (Window) this.windowIdToData.get(Integer.valueOf(i));
        if (window == null) {
            charSequence = null;
        } else {
            charSequence = window.titleFromStateChange;
        }
        windowInterpretation.checkIsWritable();
        windowInterpretation.titleFromStateChange = charSequence;
        CharSequence windowTitleForFeedback = getWindowTitleForFeedback(i, windowTitleInternal);
        windowInterpretation.checkIsWritable();
        windowInterpretation.titleForFeedback = windowTitleForFeedback;
    }

    public static void setOldWindowInterpretation(int i, CharSequence charSequence, WindowInterpretation windowInterpretation) {
        windowInterpretation.checkIsWritable();
        windowInterpretation.oldId = i;
        windowInterpretation.checkIsWritable();
        windowInterpretation.oldTitle = charSequence;
    }

    public final void addListener(WindowEventHandler windowEventHandler) {
        this.listeners.add(windowEventHandler);
    }

    public final void clearWindowTransition() {
        this.announcement = null;
        this.pendingWindowRoles = null;
        this.screenTransitionStartTime = 0L;
        this.areWindowsChanging = false;
    }

    public final void delayInterpret(EventInterpretation eventInterpretation, Performance.EventId eventId) {
        eventInterpretation.checkIsWritable();
        eventInterpretation.totalDelayMs += 10;
        this.windowEventDelayer.sendMessageDelayed(this.windowEventDelayer.obtainMessage(1, new WindowTrackerFactory((Object) eventInterpretation, eventId)), 10L);
    }

    public final void detectInputMethodChanged$ar$ds(WindowRoles windowRoles, EventInterpretation eventInterpretation, boolean z) {
        setNewWindowInterpretation(windowRoles.inputMethodWindowId, eventInterpretation.inputMethod);
        boolean idOrTitleChanged = eventInterpretation.inputMethod.idOrTitleChanged();
        eventInterpretation.checkIsWritable();
        eventInterpretation.inputMethodChanged = idOrTitleChanged;
        eventInterpretation.setShouldAnnounceInputMethodChange(idOrTitleChanged);
        if (eventInterpretation.shouldAnnounceInputMethodChange && z) {
            Announcement announcement = eventInterpretation.announcement;
            if (announcement != null && announcement.isFromInputMethodEditor) {
                int i = eventInterpretation.inputMethod.id;
                CharSequence windowPackageName = getWindowPackageName(this.service, i);
                CharSequence charSequence = announcement.packageName;
                if (i == -1 || (windowPackageName != null && charSequence != null && windowPackageName.toString().contentEquals(charSequence))) {
                    eventInterpretation.setShouldAnnounceInputMethodChange(false);
                }
            }
            this.recentKeyboardWindowChange = true;
            this.windowEventDelayer.sendEmptyMessageDelayed(2, 1000L);
        }
        SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "detectInputMethodChanged()=%s", Boolean.valueOf(eventInterpretation.shouldAnnounceInputMethodChange));
    }

    public final void detectWindowChanges$ar$ds(WindowRoles windowRoles, EventInterpretation eventInterpretation) {
        boolean z;
        setNewWindowInterpretation(windowRoles.windowIdA, eventInterpretation.windowA);
        setNewWindowInterpretation(windowRoles.windowIdB, eventInterpretation.windowB);
        setNewWindowInterpretation(windowRoles.accessibilityOverlayWindowId, eventInterpretation.accessibilityOverlay);
        setNewWindowInterpretation(windowRoles.picInPicWindowId, eventInterpretation.picInPic);
        if (!eventInterpretation.windowA.idOrTitleChanged() && !eventInterpretation.windowB.idOrTitleChanged() && !eventInterpretation.accessibilityOverlay.idOrTitleChanged()) {
            z = false;
        } else {
            z = true;
        }
        SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "detectWindowChanges()=%s roles=%s", Boolean.valueOf(z), windowRoles);
        eventInterpretation.setMainWindowsChanged(z);
    }

    public final CharSequence getWindowTitleForFeedback(int i) {
        return getWindowTitleForFeedback(i, getWindowTitleInternal(i, !FeatureSupport.isWatch(this.service)));
    }

    public final void notifyInterpretationListeners(EventInterpretation eventInterpretation, Performance.EventId eventId) {
        Iterator it = this.priorityListeners.iterator();
        while (it.hasNext()) {
            ((WindowEventHandler) it.next()).handle(eventInterpretation, eventId);
        }
        Iterator it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            ((WindowEventHandler) it2.next()).handle(eventInterpretation, eventId);
        }
    }

    @Override // com.google.android.accessibility.utils.monitor.DisplayMonitor.DisplayStateChangedListener
    public final void onDisplayStateChanged(boolean z) {
        this.defaultDisplayOn = Boolean.valueOf(z);
        if (!z) {
            this.windowRoles.clear();
            this.picInPicLastShownId = -1;
            this.picInPicDisappearTime = 0L;
            clearWindowTransition();
        }
    }

    public final void setRoles(WindowRoles windowRoles) {
        this.windowRoles = windowRoles;
        clearWindowTransition();
    }

    public final void setWindowTitles(WindowRoles windowRoles) {
        windowRoles.windowTitleA = getWindowTitleInternal(windowRoles.windowIdA);
        windowRoles.windowTitleB = getWindowTitleInternal(windowRoles.windowIdB);
        windowRoles.accessibilityOverlayWindowTitle = getWindowTitleInternal(windowRoles.accessibilityOverlayWindowId);
        windowRoles.picInPicWindowTitle = getWindowTitleInternal(windowRoles.picInPicWindowId);
        windowRoles.inputMethodWindowTitle = getWindowTitleInternal(windowRoles.inputMethodWindowId);
    }

    public final void updateWindowRoles$ar$ds(EventInterpretation eventInterpretation, AccessibilityService accessibilityService, WindowRoles windowRoles) {
        int id;
        int id2;
        boolean z;
        int i;
        boolean z2 = true;
        int i2 = 0;
        SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() interpretation=%s", eventInterpretation);
        if (eventInterpretation.eventType != 32 || (i = eventInterpretation.changeTypes) == 0 || isPaneContentChangeTypes(i)) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            List list = (List) SpannableUtils$IdentifierSpan.getWindowsOnAllDisplays(accessibilityService).get(eventInterpretation.displayId);
            if (list != null && !list.isEmpty()) {
                AccessibilityWindowInfo accessibilityWindowInfo = null;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    AccessibilityWindowInfo accessibilityWindowInfo2 = (AccessibilityWindowInfo) list.get(i3);
                    if (SpannableUtils$IdentifierSpan.isPictureInPicture(accessibilityWindowInfo2)) {
                        arrayList4.add(accessibilityWindowInfo2);
                    } else {
                        int type = accessibilityWindowInfo2.getType();
                        if (type != 1) {
                            if (type != 2) {
                                if (type == 4) {
                                    arrayList3.add(accessibilityWindowInfo2);
                                    z = true;
                                }
                            } else {
                                Window window = (Window) this.windowIdToData.get(Integer.valueOf(accessibilityWindowInfo2.getId()));
                                if (window == null || window.eventSourceRole != 24) {
                                    z = true;
                                    accessibilityWindowInfo = accessibilityWindowInfo2;
                                }
                            }
                            z = false;
                        } else {
                            if (accessibilityWindowInfo2.getParent() == null) {
                                arrayList.add(accessibilityWindowInfo2);
                                z = true;
                            }
                            z = false;
                        }
                        if (!z) {
                            arrayList2.add(accessibilityWindowInfo2);
                        }
                    }
                }
                SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() accessibilityOverlayWindows.size()=%d", Integer.valueOf(arrayList3.size()));
                SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() applicationWindows.size()=%d", Integer.valueOf(arrayList.size()));
                windowRoles.accessibilityOverlayWindowId = -1;
                int size = arrayList3.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        break;
                    }
                    AccessibilityWindowInfo accessibilityWindowInfo3 = (AccessibilityWindowInfo) arrayList3.get(i4);
                    if (accessibilityWindowInfo3.isFocused() && accessibilityWindowInfo3.isActive()) {
                        windowRoles.accessibilityOverlayWindowId = accessibilityWindowInfo3.getId();
                        SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() Accessibility overlay case", new Object[0]);
                        break;
                    }
                    i4++;
                }
                if (arrayList4.isEmpty()) {
                    id = -1;
                } else {
                    id = ((AccessibilityWindowInfo) arrayList4.get(0)).getId();
                }
                windowRoles.picInPicWindowId = id;
                if (accessibilityWindowInfo == null) {
                    id2 = -1;
                } else {
                    id2 = accessibilityWindowInfo.getId();
                }
                windowRoles.inputMethodWindowId = id2;
                if (arrayList.isEmpty()) {
                    SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() Zero application windows case", new Object[0]);
                    windowRoles.windowIdA = -1;
                    windowRoles.windowIdB = -1;
                    int size2 = arrayList2.size();
                    while (i2 < size2) {
                        AccessibilityWindowInfo accessibilityWindowInfo4 = (AccessibilityWindowInfo) arrayList2.get(i2);
                        i2++;
                        if (accessibilityWindowInfo4.isActive()) {
                            windowRoles.windowIdA = accessibilityWindowInfo4.getId();
                            return;
                        }
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() One application window case", new Object[0]);
                    windowRoles.windowIdA = ((AccessibilityWindowInfo) arrayList.get(0)).getId();
                    windowRoles.windowIdB = -1;
                    return;
                }
                if (arrayList.size() == 2) {
                    AccessibilityWindowInfo accessibilityWindowInfo5 = (AccessibilityWindowInfo) arrayList.get(0);
                    AccessibilityWindowInfo accessibilityWindowInfo6 = (AccessibilityWindowInfo) arrayList.get(1);
                    Rect bounds = SpannableUtils$IdentifierSpan.getBounds(accessibilityWindowInfo5);
                    SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 2, "hasOverlap() windowA=%s rectA=%s", accessibilityWindowInfo5, bounds);
                    Rect bounds2 = SpannableUtils$IdentifierSpan.getBounds(accessibilityWindowInfo6);
                    SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 2, "hasOverlap() windowB=%s rectB=%s", accessibilityWindowInfo6, bounds2);
                    if (!Rect.intersects(bounds, bounds2)) {
                        SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() Two application windows case", new Object[0]);
                        final boolean isScreenLayoutRTL = SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(accessibilityService);
                        Collections.sort(arrayList, new Comparator(isScreenLayoutRTL) { // from class: com.google.android.accessibility.utils.AccessibilityWindowInfoUtils$WindowPositionComparator
                            private final boolean mIsInRTL;
                            private final Rect mRectA = new Rect();
                            private final Rect mRectB = new Rect();

                            {
                                this.mIsInRTL = isScreenLayoutRTL;
                            }

                            @Override // java.util.Comparator
                            public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                                int i5;
                                int i6;
                                ((AccessibilityWindowInfo) obj).getBoundsInScreen(this.mRectA);
                                ((AccessibilityWindowInfo) obj2).getBoundsInScreen(this.mRectB);
                                if (this.mRectA.top != this.mRectB.top) {
                                    Rect rect = this.mRectA;
                                    Rect rect2 = this.mRectB;
                                    i5 = rect.top;
                                    i6 = rect2.top;
                                } else if (this.mIsInRTL) {
                                    Rect rect3 = this.mRectB;
                                    Rect rect4 = this.mRectA;
                                    i5 = rect3.right;
                                    i6 = rect4.right;
                                } else {
                                    Rect rect5 = this.mRectA;
                                    Rect rect6 = this.mRectB;
                                    i5 = rect5.left;
                                    i6 = rect6.left;
                                }
                                return i5 - i6;
                            }
                        });
                        windowRoles.windowIdA = ((AccessibilityWindowInfo) arrayList.get(0)).getId();
                        windowRoles.windowIdB = ((AccessibilityWindowInfo) arrayList.get(1)).getId();
                        Rect bounds3 = SpannableUtils$IdentifierSpan.getBounds((AccessibilityWindowInfo) arrayList.get(0));
                        Rect bounds4 = SpannableUtils$IdentifierSpan.getBounds((AccessibilityWindowInfo) arrayList.get(1));
                        if (bounds3.left < bounds4.right && bounds3.right > bounds4.left) {
                            z2 = false;
                        }
                        eventInterpretation.checkIsWritable();
                        eventInterpretation.horizontalPlacement = z2;
                        return;
                    }
                }
                SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() Default number of application windows case", new Object[0]);
                int size3 = arrayList.size();
                while (i2 < size3) {
                    AccessibilityWindowInfo accessibilityWindowInfo7 = (AccessibilityWindowInfo) arrayList.get(i2);
                    i2++;
                    if (accessibilityWindowInfo7.isActive()) {
                        windowRoles.windowIdA = accessibilityWindowInfo7.getId();
                        windowRoles.windowIdB = -1;
                        return;
                    }
                }
                return;
            }
            SpannableUtils$IdentifierSpan.log("WindowEventInterpreter", 1, "updateWindowRoles() windows.isEmpty()=true returning", new Object[0]);
            windowRoles.clear();
        }
    }

    private final CharSequence getWindowTitleInternal(int i, boolean z) {
        CharSequence charSequence;
        Iterator it = getAllWindows(this.service).iterator();
        while (true) {
            if (!it.hasNext()) {
                charSequence = null;
                break;
            }
            AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) it.next();
            if (accessibilityWindowInfo.getId() == i) {
                charSequence = SpannableUtils$IdentifierSpan.getTitle(accessibilityWindowInfo);
                break;
            }
        }
        Window window = (Window) this.windowIdToData.get(Integer.valueOf(i));
        return ((z && !TextUtils.isEmpty(charSequence)) || window == null || TextUtils.isEmpty(window.title)) ? charSequence : window.title;
    }

    private final CharSequence getWindowTitleForFeedback(int i, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            Window window = (Window) this.windowIdToData.get(Integer.valueOf(i));
            CharSequence charSequence2 = window == null ? null : window.eventPackageName;
            if (charSequence2 == null) {
                charSequence2 = getWindowPackageName(this.service, i);
            }
            if (charSequence2 != null) {
                PackageManager packageManager = this.service.getPackageManager();
                if (packageManager != null) {
                    try {
                        charSequence = packageManager.getApplicationLabel(packageManager.getApplicationInfo(charSequence2.toString(), 0));
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                charSequence = null;
            }
        }
        return TextUtils.isEmpty(charSequence) ? this.service.getString(R.string.untitled_window) : charSequence;
    }
}
