package androidx.core.view.accessibility;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ClipData;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.content.res.FontResourcesParserCompat$Api21Impl;
import androidx.core.graphics.Insets;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewParentCompat$Api21Impl;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat$Impl23;
import androidx.core.view.WindowInsetsControllerCompat$Impl30;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.navigation.NavDestination;
import androidx.navigation.NavType;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import j$.time.Duration;
import j$.time.TimeConversions;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AccessibilityNodeInfoCompat {
    public final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId = -1;
    public int mVirtualDescendantId = -1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLICK;
        public static final AccessibilityActionCompat ACTION_COLLAPSE;
        public static final AccessibilityActionCompat ACTION_COPY;
        public static final AccessibilityActionCompat ACTION_CUT;
        public static final AccessibilityActionCompat ACTION_DISMISS;
        public static final AccessibilityActionCompat ACTION_EXPAND;
        public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1);
        public static final AccessibilityActionCompat ACTION_LONG_CLICK;
        public static final AccessibilityActionCompat ACTION_PAGE_DOWN;
        public static final AccessibilityActionCompat ACTION_PAGE_LEFT;
        public static final AccessibilityActionCompat ACTION_PAGE_RIGHT;
        public static final AccessibilityActionCompat ACTION_PAGE_UP;
        public static final AccessibilityActionCompat ACTION_PASTE;
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_IN_DIRECTION;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        final Object mAction;
        public final AccessibilityViewCommand mCommand;
        public final int mId;
        public final Class mViewCommandArgumentClass;

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction9;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction10;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction11;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction12;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction13;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction14;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction15;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction16;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction17;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction18;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction19;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction20;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction21;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction22;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction23;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction24;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction25;
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction26;
            new AccessibilityActionCompat(2);
            new AccessibilityActionCompat(4);
            new AccessibilityActionCompat(8);
            ACTION_CLICK = new AccessibilityActionCompat(16);
            ACTION_LONG_CLICK = new AccessibilityActionCompat(32);
            new AccessibilityActionCompat(64);
            ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
            new AccessibilityActionCompat(256, AccessibilityViewCommand.MoveAtGranularityArguments.class);
            new AccessibilityActionCompat(512, AccessibilityViewCommand.MoveAtGranularityArguments.class);
            new AccessibilityActionCompat(1024, AccessibilityViewCommand.MoveHtmlArguments.class);
            new AccessibilityActionCompat(2048, AccessibilityViewCommand.MoveHtmlArguments.class);
            ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096);
            ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192);
            ACTION_COPY = new AccessibilityActionCompat(16384);
            ACTION_PASTE = new AccessibilityActionCompat(32768);
            ACTION_CUT = new AccessibilityActionCompat(65536);
            new AccessibilityActionCompat(131072, AccessibilityViewCommand.SetSelectionArguments.class);
            ACTION_EXPAND = new AccessibilityActionCompat(262144);
            ACTION_COLLAPSE = new AccessibilityActionCompat(524288);
            ACTION_DISMISS = new AccessibilityActionCompat(1048576);
            new AccessibilityActionCompat(2097152, AccessibilityViewCommand.SetTextArguments.class);
            ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
            ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, AccessibilityViewCommand.ScrollToPositionArguments.class);
            ACTION_SCROLL_UP = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
            ACTION_SCROLL_LEFT = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
            ACTION_SCROLL_DOWN = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
            ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
            AccessibilityNodeInfo.AccessibilityAction accessibilityAction27 = null;
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction26 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
                accessibilityAction = accessibilityAction26;
            } else {
                accessibilityAction = null;
            }
            ACTION_PAGE_UP = new AccessibilityActionCompat(accessibilityAction, R.id.accessibilityActionPageUp, null, null, null);
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction25 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
                accessibilityAction2 = accessibilityAction25;
            } else {
                accessibilityAction2 = null;
            }
            ACTION_PAGE_DOWN = new AccessibilityActionCompat(accessibilityAction2, R.id.accessibilityActionPageDown, null, null, null);
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction24 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
                accessibilityAction3 = accessibilityAction24;
            } else {
                accessibilityAction3 = null;
            }
            ACTION_PAGE_LEFT = new AccessibilityActionCompat(accessibilityAction3, R.id.accessibilityActionPageLeft, null, null, null);
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityAction23 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
                accessibilityAction4 = accessibilityAction23;
            } else {
                accessibilityAction4 = null;
            }
            ACTION_PAGE_RIGHT = new AccessibilityActionCompat(accessibilityAction4, R.id.accessibilityActionPageRight, null, null, null);
            new AccessibilityActionCompat(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
            accessibilityAction5 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
            ACTION_SET_PROGRESS = new AccessibilityActionCompat(accessibilityAction5, R.id.accessibilityActionSetProgress, null, null, AccessibilityViewCommand.SetProgressArguments.class);
            accessibilityAction6 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
            new AccessibilityActionCompat(accessibilityAction6, R.id.accessibilityActionMoveWindow, null, null, AccessibilityViewCommand.MoveWindowArguments.class);
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction22 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
                accessibilityAction7 = accessibilityAction22;
            } else {
                accessibilityAction7 = null;
            }
            new AccessibilityActionCompat(accessibilityAction7, R.id.accessibilityActionShowTooltip, null, null, null);
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityAction21 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
                accessibilityAction8 = accessibilityAction21;
            } else {
                accessibilityAction8 = null;
            }
            new AccessibilityActionCompat(accessibilityAction8, R.id.accessibilityActionHideTooltip, null, null, null);
            if (Build.VERSION.SDK_INT >= 30) {
                accessibilityAction20 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD;
                accessibilityAction9 = accessibilityAction20;
            } else {
                accessibilityAction9 = null;
            }
            new AccessibilityActionCompat(accessibilityAction9, R.id.accessibilityActionPressAndHold, null, null, null);
            if (Build.VERSION.SDK_INT >= 30) {
                accessibilityAction19 = AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER;
                accessibilityAction10 = accessibilityAction19;
            } else {
                accessibilityAction10 = null;
            }
            new AccessibilityActionCompat(accessibilityAction10, R.id.accessibilityActionImeEnter, null, null, null);
            if (Build.VERSION.SDK_INT >= 32) {
                accessibilityAction18 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START;
                accessibilityAction11 = accessibilityAction18;
            } else {
                accessibilityAction11 = null;
            }
            new AccessibilityActionCompat(accessibilityAction11, R.id.ALT, null, null, null);
            if (Build.VERSION.SDK_INT >= 32) {
                accessibilityAction17 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP;
                accessibilityAction12 = accessibilityAction17;
            } else {
                accessibilityAction12 = null;
            }
            new AccessibilityActionCompat(accessibilityAction12, R.id.CTRL, null, null, null);
            if (Build.VERSION.SDK_INT >= 32) {
                accessibilityAction16 = AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL;
                accessibilityAction13 = accessibilityAction16;
            } else {
                accessibilityAction13 = null;
            }
            new AccessibilityActionCompat(accessibilityAction13, R.id.FUNCTION, null, null, null);
            if (Build.VERSION.SDK_INT >= 33) {
                accessibilityAction15 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS;
                accessibilityAction14 = accessibilityAction15;
            } else {
                accessibilityAction14 = null;
            }
            new AccessibilityActionCompat(accessibilityAction14, R.id.KEYCODE_0, null, null, null);
            if (Build.VERSION.SDK_INT >= 34) {
                accessibilityAction27 = Api34Impl.getActionScrollInDirection();
            }
            ACTION_SCROLL_IN_DIRECTION = new AccessibilityActionCompat(accessibilityAction27, R.id.KEYCODE_3D_MODE, null, null, null);
        }

        public AccessibilityActionCompat(int i) {
            this(null, i, null, null, null);
        }

        public final boolean equals(Object obj) {
            if (obj == null || !(obj instanceof AccessibilityActionCompat) || !this.mAction.equals(((AccessibilityActionCompat) obj).mAction)) {
                return false;
            }
            return true;
        }

        public final int getId() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getId();
        }

        public final CharSequence getLabel() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.mAction).getLabel();
        }

        public final int hashCode() {
            return this.mAction.hashCode();
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("AccessibilityActionCompat: ");
            String actionSymbolicName = AccessibilityNodeInfoCompat.getActionSymbolicName(this.mId);
            if (actionSymbolicName.equals("ACTION_UNKNOWN") && getLabel() != null) {
                actionSymbolicName = getLabel().toString();
            }
            sb.append(actionSymbolicName);
            return sb.toString();
        }

        private AccessibilityActionCompat(int i, Class cls) {
            this(null, i, null, null, cls);
        }

        public AccessibilityActionCompat(Object obj, int i, CharSequence charSequence, AccessibilityViewCommand accessibilityViewCommand, Class cls) {
            this.mId = i;
            this.mCommand = accessibilityViewCommand;
            this.mAction = obj == null ? new AccessibilityNodeInfo.AccessibilityAction(i, charSequence) : obj;
            this.mViewCommandArgumentClass = cls;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api30Impl {
        public static Object createRangeInfo(int i, float f, float f2, float f3) {
            return new AccessibilityNodeInfo.RangeInfo(i, f, f2, f3);
        }

        public static final NavType fromArgType$ar$ds(String str, String str2) {
            String str3;
            boolean endsWith;
            if (Intrinsics.areEqual(NavType.IntType.getName(), str)) {
                return NavType.IntType;
            }
            if (Intrinsics.areEqual(NavType.IntArrayType.getName(), str)) {
                return NavType.IntArrayType;
            }
            if (Intrinsics.areEqual(NavType.LongType.getName(), str)) {
                return NavType.LongType;
            }
            if (Intrinsics.areEqual(NavType.LongArrayType.getName(), str)) {
                return NavType.LongArrayType;
            }
            if (Intrinsics.areEqual(NavType.BoolType.getName(), str)) {
                return NavType.BoolType;
            }
            if (Intrinsics.areEqual(NavType.BoolArrayType.getName(), str)) {
                return NavType.BoolArrayType;
            }
            if (!Intrinsics.areEqual(NavType.StringType.getName(), str)) {
                if (Intrinsics.areEqual(NavType.StringArrayType.getName(), str)) {
                    return NavType.StringArrayType;
                }
                if (Intrinsics.areEqual(NavType.FloatType.getName(), str)) {
                    return NavType.FloatType;
                }
                if (Intrinsics.areEqual(NavType.FloatArrayType.getName(), str)) {
                    return NavType.FloatArrayType;
                }
                if (Intrinsics.areEqual(NavType.ReferenceType.getName(), str)) {
                    return NavType.ReferenceType;
                }
                if (str.length() != 0) {
                    try {
                        if (OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(str, ".") && str2 != null) {
                            str3 = str2 + str;
                        } else {
                            str3 = str;
                        }
                        endsWith = str.endsWith("[]");
                        if (endsWith) {
                            str3 = str3.substring(0, str3.length() - 2);
                            str3.getClass();
                            Class<?> cls = Class.forName(str3);
                            if (Parcelable.class.isAssignableFrom(cls)) {
                                cls.getClass();
                                return new NavType.ParcelableArrayType(cls);
                            }
                            if (Serializable.class.isAssignableFrom(cls)) {
                                cls.getClass();
                                return new NavType.SerializableArrayType(cls);
                            }
                        } else {
                            Class<?> cls2 = Class.forName(str3);
                            if (Parcelable.class.isAssignableFrom(cls2)) {
                                cls2.getClass();
                                return new NavType.ParcelableType(cls2);
                            }
                            if (Enum.class.isAssignableFrom(cls2)) {
                                cls2.getClass();
                                return new NavType.EnumType(cls2);
                            }
                            if (Serializable.class.isAssignableFrom(cls2)) {
                                cls2.getClass();
                                return new NavType.SerializableType(cls2);
                            }
                        }
                        throw new IllegalArgumentException(str3 + " is not Serializable or Parcelable.");
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return NavType.StringType;
        }

        public static CharSequence getStateDescription(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getStateDescription();
        }

        public static void setStateDescription(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
            accessibilityNodeInfo.setStateDescription(charSequence);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api33Impl {
        public static CollectionItemInfoCompat buildCollectionItemInfoCompat(boolean z, int i, int i2, int i3, int i4, boolean z2, String str, String str2) {
            return new CollectionItemInfoCompat(new AccessibilityNodeInfo.CollectionItemInfo.Builder().setHeading(z).setColumnIndex(i).setRowIndex(i2).setColumnSpan(i3).setRowSpan(i4).setSelected(z2).setRowTitle(str).setColumnTitle(str2).build());
        }

        public static AccessibilityNodeInfoCompat getChild(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(accessibilityNodeInfo.getChild(i, i2));
        }

        public static String getCollectionItemColumnTitle(Object obj) {
            return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnTitle();
        }

        public static String getCollectionItemRowTitle(Object obj) {
            return ((AccessibilityNodeInfo.CollectionItemInfo) obj).getRowTitle();
        }

        public static AccessibilityNodeInfo.ExtraRenderingInfo getExtraRenderingInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getExtraRenderingInfo();
        }

        public static AccessibilityNodeInfoCompat getParent(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(accessibilityNodeInfo.getParent(i));
        }

        public static String getUniqueId(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getUniqueId();
        }

        public static boolean isTextSelectable(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.isTextSelectable();
        }

        public static void setTextSelectable(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            accessibilityNodeInfo.setTextSelectable(z);
        }

        public static void setUniqueId(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
            accessibilityNodeInfo.setUniqueId(str);
        }

        public static final boolean validateName$navigation_common_release$ar$ds(String str) {
            if (str != null && str.length() > 0) {
                return true;
            }
            return false;
        }

        public final String getNameForNavigator$navigation_common_release(Class cls) {
            cls.getClass();
            String str = (String) NavigatorProvider.annotationNames.get(cls);
            if (str == null) {
                Navigator.Name name = (Navigator.Name) cls.getAnnotation(Navigator.Name.class);
                if (name != null) {
                    str = name.value();
                } else {
                    str = null;
                }
                if (validateName$navigation_common_release$ar$ds(str)) {
                    NavigatorProvider.annotationNames.put(cls, str);
                } else {
                    throw new IllegalArgumentException("No @Navigator.Name annotation found for ".concat(String.valueOf(cls.getSimpleName())));
                }
            }
            str.getClass();
            return str;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api34Impl {
        public static AccessibilityNodeInfo.AccessibilityAction getActionScrollInDirection() {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_IN_DIRECTION;
        }

        public static void getBoundsInWindow(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect) {
            accessibilityNodeInfo.getBoundsInWindow(rect);
        }

        public static CharSequence getContainerTitle(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.getContainerTitle();
        }

        public static long getMinDurationBetweenContentChangeMillis(AccessibilityNodeInfo accessibilityNodeInfo) {
            return TimeConversions.convert(accessibilityNodeInfo.getMinDurationBetweenContentChanges()).toMillis();
        }

        public static boolean hasRequestInitialAccessibilityFocus(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.hasRequestInitialAccessibilityFocus();
        }

        public static boolean isAccessibilityDataSensitive(AccessibilityNodeInfo accessibilityNodeInfo) {
            return accessibilityNodeInfo.isAccessibilityDataSensitive();
        }

        public static final boolean matchDestination$navigation_ui_release(NavDestination navDestination, int i) {
            Iterator it = ViewParentCompat$Api21Impl.getHierarchy$ar$ds(navDestination).iterator();
            while (it.hasNext()) {
                if (((NavDestination) it.next()).id == i) {
                    return true;
                }
            }
            return false;
        }

        public static void setAccessibilityDataSensitive(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            accessibilityNodeInfo.setAccessibilityDataSensitive(z);
        }

        public static void setBoundsInWindow(AccessibilityNodeInfo accessibilityNodeInfo, Rect rect) {
            accessibilityNodeInfo.setBoundsInWindow(rect);
        }

        public static void setContainerTitle(AccessibilityNodeInfo accessibilityNodeInfo, CharSequence charSequence) {
            accessibilityNodeInfo.setContainerTitle(charSequence);
        }

        public static void setMinDurationBetweenContentChangeMillis(AccessibilityNodeInfo accessibilityNodeInfo, long j) {
            accessibilityNodeInfo.setMinDurationBetweenContentChanges(TimeConversions.convert(Duration.ofMillis(j)));
        }

        public static void setQueryFromAppProcessEnabled(AccessibilityNodeInfo accessibilityNodeInfo, View view, boolean z) {
            accessibilityNodeInfo.setQueryFromAppProcessEnabled(view, z);
        }

        public static void setRequestInitialAccessibilityFocus(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
            accessibilityNodeInfo.setRequestInitialAccessibilityFocus(z);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CollectionItemInfoCompat {
        public final Object mInfo;

        public CollectionItemInfoCompat(Object obj) {
            this.mInfo = obj;
        }

        public static CollectionItemInfoCompat obtain$ar$ds$ar$class_merging(int i, int i2, int i3) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, false, i3));
        }

        public static CollectionItemInfoCompat obtain$ar$ds$c443ecb5_0(int i, int i2, int i3, int i4, boolean z) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, false, z));
        }

        public final void alpha$ar$ds(float f) {
            View view = (View) ((WeakReference) this.mInfo).get();
            if (view != null) {
                view.animate().alpha(f);
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, androidx.core.view.ContentInfoCompat$BuilderCompat] */
        public final ContentInfoCompat build() {
            return this.mInfo.build();
        }

        public final void cancel() {
            View view = (View) ((WeakReference) this.mInfo).get();
            if (view != null) {
                view.animate().cancel();
            }
        }

        public final void close() {
            Object obj = this.mInfo;
            if (obj != null) {
                ((ContentProviderClient) obj).release();
            }
        }

        public final void execPendingActions$ar$ds() {
            ((FragmentHostCallback) this.mInfo).fragmentManager.execPendingActions(true);
        }

        public final int getColumnCount() {
            return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getColumnCount();
        }

        public final int getColumnIndex() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnIndex();
        }

        public final int getColumnSpan() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getColumnSpan();
        }

        public final float getCurrent() {
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getCurrent();
        }

        public final float getMax() {
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getMax();
        }

        public final int getMaxActionButtons() {
            Configuration configuration = ((Context) this.mInfo).getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (configuration.smallestScreenWidthDp <= 600 && i <= 600) {
                if (i < 500) {
                    if (i <= 480 || i2 <= 640) {
                        if (i >= 360) {
                            return 3;
                        }
                        return 2;
                    }
                    return 4;
                }
                return 4;
            }
            return 5;
        }

        public final float getMin() {
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getMin();
        }

        public final int getRowCount() {
            return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).getRowCount();
        }

        public final int getRowIndex() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowIndex();
        }

        public final int getRowSpan() {
            return ((AccessibilityNodeInfo.CollectionItemInfo) this.mInfo).getRowSpan();
        }

        public final FragmentManager getSupportFragmentManager() {
            return ((FragmentHostCallback) this.mInfo).fragmentManager;
        }

        public final int getType() {
            return ((AccessibilityNodeInfo.RangeInfo) this.mInfo).getType();
        }

        public final boolean hasEmbeddedTabs() {
            return ((Context) this.mInfo).getResources().getBoolean(com.google.android.marvin.talkback.R.bool.abc_action_bar_embed_tabs);
        }

        public final boolean isEmpty() {
            return ((LinkedHashMap) this.mInfo).isEmpty();
        }

        public final boolean isHierarchical() {
            return ((AccessibilityNodeInfo.CollectionInfo) this.mInfo).isHierarchical();
        }

        public final void noteStateNotSaved() {
            ((FragmentHostCallback) this.mInfo).fragmentManager.noteStateNotSaved();
        }

        public final Object put(Object obj, Object obj2) {
            return ((LinkedHashMap) this.mInfo).put(obj, obj2);
        }

        public final Cursor query$ar$ds(Uri uri, String[] strArr, String[] strArr2) {
            Object obj = this.mInfo;
            if (obj != null) {
                try {
                    return ((ContentProviderClient) obj).query(uri, strArr, "query = ?", strArr2, null, null);
                } catch (RemoteException e) {
                    Log.w("FontsProvider", "Unable to query the content provider", e);
                }
            }
            return null;
        }

        public final void setDuration$ar$ds$d0f32809_0(long j) {
            View view = (View) ((WeakReference) this.mInfo).get();
            if (view != null) {
                view.animate().setDuration(j);
            }
        }

        public final void setInsets$ar$ds(int i, Insets insets) {
            ((WindowInsetsCompat.BuilderImpl) this.mInfo).setInsets(i, insets);
        }

        public final void setListener$ar$ds$34caea9b_0(final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            final View view = (View) ((WeakReference) this.mInfo).get();
            if (view != null) {
                if (viewPropertyAnimatorListener != null) {
                    view.animate().setListener(new AnimatorListenerAdapter() { // from class: androidx.core.view.ViewPropertyAnimatorCompat$1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            ViewPropertyAnimatorListener.this.onAnimationCancel$ar$ds();
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            ViewPropertyAnimatorListener.this.onAnimationEnd$ar$ds();
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator) {
                            ViewPropertyAnimatorListener.this.onAnimationStart$ar$ds();
                        }
                    });
                } else {
                    view.animate().setListener(null);
                }
            }
        }

        @Deprecated
        public final void setStableInsets$ar$ds(Insets insets) {
            ((WindowInsetsCompat.BuilderImpl) this.mInfo).setStableInsets(insets);
        }

        @Deprecated
        public final void setSystemWindowInsets$ar$ds(Insets insets) {
            ((WindowInsetsCompat.BuilderImpl) this.mInfo).setSystemWindowInsets(insets);
        }

        public final void setUpdateListener$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
            CircularProgressDrawable.AnonymousClass1 anonymousClass1;
            View view = (View) ((WeakReference) this.mInfo).get();
            if (view != null) {
                if (shadowDelegateImpl != null) {
                    anonymousClass1 = new CircularProgressDrawable.AnonymousClass1(shadowDelegateImpl, view, 1);
                } else {
                    anonymousClass1 = null;
                }
                view.animate().setUpdateListener(anonymousClass1);
            }
        }

        public final Bundle toBundle() {
            Bundle bundle = new Bundle();
            Object obj = this.mInfo;
            if (obj != null) {
                bundle.putInt("android.support.customtabs.extra.TOOLBAR_COLOR", ((Integer) obj).intValue());
            }
            return bundle;
        }

        public final void translationY$ar$ds(float f) {
            View view = (View) ((WeakReference) this.mInfo).get();
            if (view != null) {
                view.animate().translationY(f);
            }
        }

        public CollectionItemInfoCompat(Object obj, byte[] bArr) {
            this.mInfo = obj;
        }

        /* renamed from: build */
        public final WindowInsetsCompat m31build() {
            return ((WindowInsetsCompat.BuilderImpl) this.mInfo).build();
        }

        public CollectionItemInfoCompat(int i) {
            this.mInfo = new LinkedHashMap(i, 0.75f, true);
        }

        public CollectionItemInfoCompat(Context context, Uri uri) {
            this.mInfo = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public CollectionItemInfoCompat(ClipData clipData, int i) {
            this.mInfo = Build.VERSION.SDK_INT >= 31 ? new ContentInfoCompat.BuilderCompat31Impl(clipData, i) : new ContentInfoCompat.BuilderCompatImpl(clipData, i);
        }

        public CollectionItemInfoCompat(View view) {
            this.mInfo = new WeakReference(view);
        }

        public CollectionItemInfoCompat() {
            if (Build.VERSION.SDK_INT >= 30) {
                this.mInfo = new WindowInsetsCompat.BuilderImpl30();
            } else if (Build.VERSION.SDK_INT >= 29) {
                this.mInfo = new WindowInsetsCompat.BuilderImpl29();
            } else {
                this.mInfo = new WindowInsetsCompat.BuilderImpl20();
            }
        }

        public CollectionItemInfoCompat(WindowInsetsCompat windowInsetsCompat) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.mInfo = new WindowInsetsCompat.BuilderImpl30(windowInsetsCompat);
            } else if (Build.VERSION.SDK_INT >= 29) {
                this.mInfo = new WindowInsetsCompat.BuilderImpl29(windowInsetsCompat);
            } else {
                this.mInfo = new WindowInsetsCompat.BuilderImpl20(windowInsetsCompat);
            }
        }

        public CollectionItemInfoCompat(Window window, View view) {
            FontResourcesParserCompat$Api21Impl fontResourcesParserCompat$Api21Impl = new FontResourcesParserCompat$Api21Impl();
            if (Build.VERSION.SDK_INT >= 35) {
                this.mInfo = new WindowInsetsControllerCompat$Impl30(window, this, fontResourcesParserCompat$Api21Impl);
            } else if (Build.VERSION.SDK_INT >= 30) {
                this.mInfo = new WindowInsetsControllerCompat$Impl30(window);
            } else {
                this.mInfo = new WindowInsetsControllerCompat$Impl23(window, fontResourcesParserCompat$Api21Impl) { // from class: androidx.core.view.WindowInsetsControllerCompat$Impl26
                    @Override // androidx.core.graphics.BlendModeUtils$Api29Impl
                    public final void setAppearanceLightNavigationBars(boolean z) {
                        if (z) {
                            unsetWindowFlag(134217728);
                            setWindowFlag$ar$ds();
                            setSystemUiFlag(16);
                            return;
                        }
                        unsetSystemUiFlag(16);
                    }
                };
            }
        }
    }

    public AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.mInfo = accessibilityNodeInfo;
    }

    private final List extrasIntList(String str) {
        ArrayList<Integer> integerArrayList = this.mInfo.getExtras().getIntegerArrayList(str);
        if (integerArrayList == null) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            this.mInfo.getExtras().putIntegerArrayList(str, arrayList);
            return arrayList;
        }
        return integerArrayList;
    }

    static String getActionSymbolicName(int i) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 4:
                        return "ACTION_SELECT";
                    case 8:
                        return "ACTION_CLEAR_SELECTION";
                    case 16:
                        return "ACTION_CLICK";
                    case 32:
                        return "ACTION_LONG_CLICK";
                    case 64:
                        return "ACTION_ACCESSIBILITY_FOCUS";
                    case BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE /* 128 */:
                        return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                    case 256:
                        return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                    case 512:
                        return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                    case 1024:
                        return "ACTION_NEXT_HTML_ELEMENT";
                    case 2048:
                        return "ACTION_PREVIOUS_HTML_ELEMENT";
                    case 4096:
                        return "ACTION_SCROLL_FORWARD";
                    case 8192:
                        return "ACTION_SCROLL_BACKWARD";
                    case 16384:
                        return "ACTION_COPY";
                    case 32768:
                        return "ACTION_PASTE";
                    case 65536:
                        return "ACTION_CUT";
                    case 131072:
                        return "ACTION_SET_SELECTION";
                    case 262144:
                        return "ACTION_EXPAND";
                    case 524288:
                        return "ACTION_COLLAPSE";
                    case 2097152:
                        return "ACTION_SET_TEXT";
                    case R.id.accessibilityActionMoveWindow:
                        return "ACTION_MOVE_WINDOW";
                    case R.id.KEYCODE_3D_MODE:
                        return "ACTION_SCROLL_IN_DIRECTION";
                    default:
                        switch (i) {
                            case R.id.accessibilityActionShowOnScreen:
                                return "ACTION_SHOW_ON_SCREEN";
                            case R.id.accessibilityActionScrollToPosition:
                                return "ACTION_SCROLL_TO_POSITION";
                            case R.id.accessibilityActionScrollUp:
                                return "ACTION_SCROLL_UP";
                            case R.id.accessibilityActionScrollLeft:
                                return "ACTION_SCROLL_LEFT";
                            case R.id.accessibilityActionScrollDown:
                                return "ACTION_SCROLL_DOWN";
                            case R.id.accessibilityActionScrollRight:
                                return "ACTION_SCROLL_RIGHT";
                            case R.id.accessibilityActionContextClick:
                                return "ACTION_CONTEXT_CLICK";
                            case R.id.accessibilityActionSetProgress:
                                return "ACTION_SET_PROGRESS";
                            default:
                                switch (i) {
                                    case R.id.accessibilityActionShowTooltip:
                                        return "ACTION_SHOW_TOOLTIP";
                                    case R.id.accessibilityActionHideTooltip:
                                        return "ACTION_HIDE_TOOLTIP";
                                    case R.id.accessibilityActionPageUp:
                                        return "ACTION_PAGE_UP";
                                    case R.id.accessibilityActionPageDown:
                                        return "ACTION_PAGE_DOWN";
                                    case R.id.accessibilityActionPageLeft:
                                        return "ACTION_PAGE_LEFT";
                                    case R.id.accessibilityActionPageRight:
                                        return "ACTION_PAGE_RIGHT";
                                    case R.id.accessibilityActionPressAndHold:
                                        return "ACTION_PRESS_AND_HOLD";
                                    default:
                                        switch (i) {
                                            case R.id.accessibilityActionImeEnter:
                                                return "ACTION_IME_ENTER";
                                            case R.id.ALT:
                                                return "ACTION_DRAG_START";
                                            case R.id.CTRL:
                                                return "ACTION_DRAG_DROP";
                                            case R.id.FUNCTION:
                                                return "ACTION_DRAG_CANCEL";
                                            default:
                                                return "ACTION_UNKNOWN";
                                        }
                                }
                        }
                }
            }
            return "ACTION_CLEAR_FOCUS";
        }
        return "ACTION_FOCUS";
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat wrapNonNullInstance(Object obj) {
        if (obj != null) {
            return new AccessibilityNodeInfoCompat(obj);
        }
        return null;
    }

    public final void addAction(int i) {
        this.mInfo.addAction(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityNodeInfoCompat)) {
            return false;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            if (accessibilityNodeInfoCompat.mInfo != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(accessibilityNodeInfoCompat.mInfo)) {
            return false;
        }
        if (this.mVirtualDescendantId == accessibilityNodeInfoCompat.mVirtualDescendantId && this.mParentVirtualDescendantId == accessibilityNodeInfoCompat.mParentVirtualDescendantId) {
            return true;
        }
        return false;
    }

    public final List getActionList() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.mInfo.getActionList();
        if (actionList != null) {
            ArrayList arrayList = new ArrayList();
            int size = actionList.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new AccessibilityActionCompat(actionList.get(i), 0, null, null, null));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    @Deprecated
    public final int getActions() {
        return this.mInfo.getActions();
    }

    public final boolean getBooleanProperty(int i) {
        Bundle extras = getExtras();
        if (extras == null || (extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i) != i) {
            return false;
        }
        return true;
    }

    @Deprecated
    public final void getBoundsInParent(Rect rect) {
        this.mInfo.getBoundsInParent(rect);
    }

    public final void getBoundsInScreen(Rect rect) {
        this.mInfo.getBoundsInScreen(rect);
    }

    public final void getBoundsInWindow(Rect rect) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api34Impl.getBoundsInWindow(this.mInfo, rect);
            return;
        }
        Rect rect2 = (Rect) this.mInfo.getExtras().getParcelable("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOUNDS_IN_WINDOW_KEY");
        if (rect2 != null) {
            rect.set(rect2.left, rect2.top, rect2.right, rect2.bottom);
        }
    }

    public AccessibilityNodeInfoCompat getChild(int i) {
        return wrapNonNullInstance(this.mInfo.getChild(i));
    }

    public final int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public final CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public final CollectionItemInfoCompat getCollectionInfo$ar$class_merging() {
        AccessibilityNodeInfo.CollectionInfo collectionInfo = this.mInfo.getCollectionInfo();
        if (collectionInfo != null) {
            return new CollectionItemInfoCompat(collectionInfo);
        }
        return null;
    }

    public final CollectionItemInfoCompat getCollectionItemInfo() {
        AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo = this.mInfo.getCollectionItemInfo();
        if (collectionItemInfo != null) {
            return new CollectionItemInfoCompat(collectionItemInfo);
        }
        return null;
    }

    public final CharSequence getContainerTitle() {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getContainerTitle(this.mInfo);
        }
        return this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.CONTAINER_TITLE_KEY");
    }

    public final CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public final int getDrawingOrder() {
        int drawingOrder;
        drawingOrder = this.mInfo.getDrawingOrder();
        return drawingOrder;
    }

    public final CharSequence getError() {
        return this.mInfo.getError();
    }

    public final Bundle getExtras() {
        return this.mInfo.getExtras();
    }

    public final CharSequence getHintText() {
        CharSequence hintText;
        hintText = this.mInfo.getHintText();
        return hintText;
    }

    public final int getInputType() {
        return this.mInfo.getInputType();
    }

    public final AccessibilityNodeInfoCompat getLabeledBy() {
        return wrapNonNullInstance(this.mInfo.getLabeledBy());
    }

    public final int getLiveRegion() {
        return this.mInfo.getLiveRegion();
    }

    public final int getMaxTextLength() {
        return this.mInfo.getMaxTextLength();
    }

    public final long getMinDurationBetweenContentChangesMillis() {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getMinDurationBetweenContentChangeMillis(this.mInfo);
        }
        return this.mInfo.getExtras().getLong("androidx.view.accessibility.AccessibilityNodeInfoCompat.MIN_DURATION_BETWEEN_CONTENT_CHANGES_KEY");
    }

    public final int getMovementGranularities() {
        return this.mInfo.getMovementGranularities();
    }

    public final CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public final CharSequence getPaneTitle() {
        CharSequence paneTitle;
        if (Build.VERSION.SDK_INT >= 28) {
            paneTitle = this.mInfo.getPaneTitle();
            return paneTitle;
        }
        return this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY");
    }

    public AccessibilityNodeInfoCompat getParent() {
        return wrapNonNullInstance(this.mInfo.getParent());
    }

    public final CollectionItemInfoCompat getRangeInfo$ar$class_merging() {
        AccessibilityNodeInfo.RangeInfo rangeInfo = this.mInfo.getRangeInfo();
        if (rangeInfo != null) {
            return new CollectionItemInfoCompat(rangeInfo);
        }
        return null;
    }

    public final CharSequence getRoleDescription() {
        return this.mInfo.getExtras().getCharSequence("AccessibilityNodeInfo.roleDescription");
    }

    public final CharSequence getStateDescription() {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getStateDescription(this.mInfo);
        }
        return this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY");
    }

    public final CharSequence getText() {
        if (!extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty()) {
            List extrasIntList = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            List extrasIntList2 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            List extrasIntList3 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            List extrasIntList4 = extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
            SpannableString spannableString = new SpannableString(TextUtils.substring(accessibilityNodeInfo.getText(), 0, accessibilityNodeInfo.getText().length()));
            for (int i = 0; i < extrasIntList.size(); i++) {
                spannableString.setSpan(new AccessibilityClickableSpanCompat(((Integer) extrasIntList4.get(i)).intValue(), this, getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), ((Integer) extrasIntList.get(i)).intValue(), ((Integer) extrasIntList2.get(i)).intValue(), ((Integer) extrasIntList3.get(i)).intValue());
            }
            return spannableString;
        }
        return this.mInfo.getText();
    }

    public final int getTextSelectionEnd() {
        return this.mInfo.getTextSelectionEnd();
    }

    public final int getTextSelectionStart() {
        return this.mInfo.getTextSelectionStart();
    }

    public final CharSequence getTooltipText() {
        CharSequence tooltipText;
        if (Build.VERSION.SDK_INT >= 28) {
            tooltipText = this.mInfo.getTooltipText();
            return tooltipText;
        }
        return this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY");
    }

    public final AccessibilityNodeInfoCompat getTraversalAfter() {
        return wrapNonNullInstance(this.mInfo.getTraversalAfter());
    }

    public final AccessibilityNodeInfoCompat getTraversalBefore() {
        return wrapNonNullInstance(this.mInfo.getTraversalBefore());
    }

    public final String getUniqueId() {
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getUniqueId(this.mInfo);
        }
        return this.mInfo.getExtras().getString("androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY");
    }

    public final String getViewIdResourceName() {
        return this.mInfo.getViewIdResourceName();
    }

    public final AccessibilityWindowInfoCompat getWindow() {
        return AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getWindow());
    }

    public final int getWindowId() {
        return this.mInfo.getWindowId();
    }

    public final int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public final boolean isAccessibilityFocused() {
        return this.mInfo.isAccessibilityFocused();
    }

    public final boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public final boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public final boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public final boolean isEditable() {
        return this.mInfo.isEditable();
    }

    public final boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public final boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public final boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public final boolean isHeading() {
        boolean isHeading;
        if (Build.VERSION.SDK_INT >= 28) {
            isHeading = this.mInfo.isHeading();
            return isHeading;
        }
        if (getBooleanProperty(2)) {
            return true;
        }
        CollectionItemInfoCompat collectionItemInfo = getCollectionItemInfo();
        if (collectionItemInfo != null && ((AccessibilityNodeInfo.CollectionItemInfo) collectionItemInfo.mInfo).isHeading()) {
            return true;
        }
        return false;
    }

    public final boolean isImportantForAccessibility() {
        boolean isImportantForAccessibility;
        isImportantForAccessibility = this.mInfo.isImportantForAccessibility();
        return isImportantForAccessibility;
    }

    public final boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public final boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public final boolean isScreenReaderFocusable() {
        boolean isScreenReaderFocusable;
        if (Build.VERSION.SDK_INT < 28) {
            return getBooleanProperty(1);
        }
        isScreenReaderFocusable = this.mInfo.isScreenReaderFocusable();
        return isScreenReaderFocusable;
    }

    public final boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public final boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public final boolean isShowingHintText() {
        boolean isShowingHintText;
        isShowingHintText = this.mInfo.isShowingHintText();
        return isShowingHintText;
    }

    public final boolean isTextEntryKey() {
        boolean isTextEntryKey;
        if (Build.VERSION.SDK_INT < 29) {
            return getBooleanProperty(8);
        }
        isTextEntryKey = this.mInfo.isTextEntryKey();
        return isTextEntryKey;
    }

    public final boolean isVisibleToUser() {
        return this.mInfo.isVisibleToUser();
    }

    public final boolean performAction(int i, Bundle bundle) {
        return this.mInfo.performAction(i, bundle);
    }

    public final boolean performAction$ar$ds() {
        return this.mInfo.performAction(64);
    }

    public final boolean refresh() {
        return this.mInfo.refresh();
    }

    public final void removeAction$ar$ds(AccessibilityActionCompat accessibilityActionCompat) {
        this.mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.mAction);
    }

    public final void setAccessibilityFocused(boolean z) {
        this.mInfo.setAccessibilityFocused(z);
    }

    public final void setBooleanProperty(int i, boolean z) {
        Bundle extras = getExtras();
        if (extras != null) {
            int i2 = extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i);
            if (true != z) {
                i = 0;
            }
            extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    @Deprecated
    public final void setBoundsInParent(Rect rect) {
        this.mInfo.setBoundsInParent(rect);
    }

    public final void setBoundsInScreen(Rect rect) {
        this.mInfo.setBoundsInScreen(rect);
    }

    public final void setCheckable(boolean z) {
        this.mInfo.setCheckable(z);
    }

    public final void setClassName(CharSequence charSequence) {
        this.mInfo.setClassName(charSequence);
    }

    public final void setClickable(boolean z) {
        this.mInfo.setClickable(z);
    }

    public final void setCollectionInfo(Object obj) {
        Object obj2;
        if (obj == null) {
            obj2 = null;
        } else {
            obj2 = ((CollectionItemInfoCompat) obj).mInfo;
        }
        this.mInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) obj2);
    }

    public final void setCollectionItemInfo(Object obj) {
        this.mInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) ((CollectionItemInfoCompat) obj).mInfo);
    }

    public final void setContentDescription(CharSequence charSequence) {
        this.mInfo.setContentDescription(charSequence);
    }

    public final void setEnabled(boolean z) {
        this.mInfo.setEnabled(z);
    }

    public final void setFocusable$ar$ds() {
        this.mInfo.setFocusable(true);
    }

    public final void setGranularScrollingSupported$ar$ds() {
        setBooleanProperty(67108864, true);
    }

    public final void setHintText(CharSequence charSequence) {
        this.mInfo.setHintText(charSequence);
    }

    public final void setLabelFor(View view) {
        this.mInfo.setLabelFor(view);
    }

    public final void setScrollable(boolean z) {
        this.mInfo.setScrollable(z);
    }

    public final void setShowingHintText(boolean z) {
        this.mInfo.setShowingHintText(z);
    }

    public final void setText(CharSequence charSequence) {
        this.mInfo.setText(charSequence);
    }

    public final void setTraversalAfter(View view) {
        this.mInfo.setTraversalAfter(view);
    }

    public final String toString() {
        boolean booleanProperty;
        boolean booleanProperty2;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        getBoundsInParent(rect);
        rect.toString();
        sb.append("; boundsInParent: ".concat(rect.toString()));
        getBoundsInScreen(rect);
        rect.toString();
        sb.append("; boundsInScreen: ".concat(rect.toString()));
        getBoundsInWindow(rect);
        rect.toString();
        sb.append("; boundsInWindow: ".concat(rect.toString()));
        sb.append("; packageName: ");
        sb.append(getPackageName());
        sb.append("; className: ");
        sb.append(getClassName());
        sb.append("; text: ");
        sb.append(getText());
        sb.append("; error: ");
        sb.append(getError());
        sb.append("; maxTextLength: ");
        sb.append(getMaxTextLength());
        sb.append("; stateDescription: ");
        sb.append(getStateDescription());
        sb.append("; contentDescription: ");
        sb.append(getContentDescription());
        sb.append("; tooltipText: ");
        sb.append(getTooltipText());
        sb.append("; viewIdResName: ");
        sb.append(getViewIdResourceName());
        sb.append("; uniqueId: ");
        sb.append(getUniqueId());
        sb.append("; checkable: ");
        sb.append(isCheckable());
        sb.append("; checked: ");
        sb.append(isChecked());
        sb.append("; focusable: ");
        sb.append(isFocusable());
        sb.append("; focused: ");
        sb.append(isFocused());
        sb.append("; selected: ");
        sb.append(isSelected());
        sb.append("; clickable: ");
        sb.append(isClickable());
        sb.append("; longClickable: ");
        sb.append(isLongClickable());
        sb.append("; contextClickable: ");
        sb.append(this.mInfo.isContextClickable());
        sb.append("; enabled: ");
        sb.append(isEnabled());
        sb.append("; password: ");
        sb.append(isPassword());
        sb.append("; scrollable: " + isScrollable());
        sb.append("; containerTitle: ");
        sb.append(getContainerTitle());
        sb.append("; granularScrollingSupported: ");
        sb.append(getBooleanProperty(67108864));
        sb.append("; importantForAccessibility: ");
        sb.append(isImportantForAccessibility());
        sb.append("; visible: ");
        sb.append(isVisibleToUser());
        sb.append("; isTextSelectable: ");
        if (Build.VERSION.SDK_INT >= 33) {
            booleanProperty = Api33Impl.isTextSelectable(this.mInfo);
        } else {
            booleanProperty = getBooleanProperty(8388608);
        }
        sb.append(booleanProperty);
        sb.append("; accessibilityDataSensitive: ");
        if (Build.VERSION.SDK_INT >= 34) {
            booleanProperty2 = Api34Impl.isAccessibilityDataSensitive(this.mInfo);
        } else {
            booleanProperty2 = getBooleanProperty(64);
        }
        sb.append(booleanProperty2);
        sb.append("; [");
        List actionList = getActionList();
        for (int i = 0; i < actionList.size(); i++) {
            AccessibilityActionCompat accessibilityActionCompat = (AccessibilityActionCompat) actionList.get(i);
            String actionSymbolicName = getActionSymbolicName(accessibilityActionCompat.getId());
            if (actionSymbolicName.equals("ACTION_UNKNOWN") && accessibilityActionCompat.getLabel() != null) {
                actionSymbolicName = accessibilityActionCompat.getLabel().toString();
            }
            sb.append(actionSymbolicName);
            if (i != actionList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Deprecated
    public AccessibilityNodeInfoCompat(Object obj) {
        this.mInfo = (AccessibilityNodeInfo) obj;
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(accessibilityNodeInfoCompat.mInfo));
    }

    public final void addAction(AccessibilityActionCompat accessibilityActionCompat) {
        this.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) accessibilityActionCompat.mAction);
    }
}
