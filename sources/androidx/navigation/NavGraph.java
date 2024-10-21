package androidx.navigation;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.collection.SparseArrayKt$valueIterator$1;
import androidx.core.view.ViewParentCompat$Api21Impl;
import androidx.core.view.WindowCompat$Api30Impl;
import androidx.navigation.NavDestination;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavGraph extends NavDestination implements Iterable<NavDestination>, KMappedMarker {
    public static final WindowCompat$Api30Impl Companion$ar$class_merging$ad045ee8_0 = new WindowCompat$Api30Impl();
    public final SparseArrayCompat nodes;
    public int startDestId;
    public String startDestIdName;

    public NavGraph(Navigator navigator) {
        super(navigator);
        this.nodes = new SparseArrayCompat();
    }

    public final void addDestination(NavDestination navDestination) {
        navDestination.getClass();
        int i = navDestination.id;
        String str = navDestination.route;
        if (i == 0) {
            if (str != null) {
                i = 0;
            } else {
                throw new IllegalArgumentException("Destinations must have an id or route. Call setId(), setRoute(), or include an android:id or app:route in your navigation XML.");
            }
        }
        String str2 = this.route;
        if (str2 != null && Intrinsics.areEqual(str, str2)) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, navDestination, "Destination ", " cannot have the same route as graph "));
        }
        if (i != this.id) {
            NavDestination navDestination2 = (NavDestination) SparseArrayCompatKt.commonGet(this.nodes, i);
            if (navDestination2 != navDestination) {
                if (navDestination.parent == null) {
                    if (navDestination2 != null) {
                        navDestination2.parent = null;
                    }
                    navDestination.parent = this;
                    this.nodes.put(navDestination.id, navDestination);
                    return;
                }
                throw new IllegalStateException("Destination already has a parent set. Call NavGraph.remove() to remove the previous parent.");
            }
            return;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(this, navDestination, "Destination ", " cannot have the same id as graph "));
    }

    @Override // androidx.navigation.NavDestination
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NavGraph)) {
            return false;
        }
        if (super.equals(obj)) {
            NavGraph navGraph = (NavGraph) obj;
            if (this.nodes.size() == navGraph.nodes.size() && this.startDestId == navGraph.startDestId) {
                for (NavDestination navDestination : OnDeviceSmartReplyLogEvent.asSequence(new SparseArrayKt$valueIterator$1(this.nodes))) {
                    if (!Intrinsics.areEqual(navDestination, SparseArrayCompatKt.commonGet(navGraph.nodes, navDestination.id))) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final NavDestination findNode(String str) {
        return null;
    }

    public final String getDisplayName() {
        int i = this.id;
        if (i != 0) {
            String str = this.idName;
            if (str == null) {
                return String.valueOf(i);
            }
            return str;
        }
        return "the root navigation";
    }

    @Override // androidx.navigation.NavDestination
    public final int hashCode() {
        int i = this.startDestId;
        SparseArrayCompat sparseArrayCompat = this.nodes;
        int size = sparseArrayCompat.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = (((i * 31) + sparseArrayCompat.keyAt(i2)) * 31) + ((NavDestination) sparseArrayCompat.valueAt(i2)).hashCode();
        }
        return i;
    }

    @Override // java.lang.Iterable
    public final Iterator<NavDestination> iterator() {
        return new NavGraph$iterator$1(this);
    }

    @Override // androidx.navigation.NavDestination
    public final NavDestination.DeepLinkMatch matchDeepLink(NavDeepLinkRequest navDeepLinkRequest) {
        NavDestination.DeepLinkMatch matchDeepLink = super.matchDeepLink(navDeepLinkRequest);
        ArrayList arrayList = new ArrayList();
        NavGraph$iterator$1 navGraph$iterator$1 = new NavGraph$iterator$1(this);
        while (navGraph$iterator$1.hasNext()) {
            NavDestination.DeepLinkMatch matchDeepLink2 = navGraph$iterator$1.next().matchDeepLink(navDeepLinkRequest);
            if (matchDeepLink2 != null) {
                arrayList.add(matchDeepLink2);
            }
        }
        return (NavDestination.DeepLinkMatch) OnDeviceLanguageIdentificationLogEvent.maxOrNull(OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.filterNotNull(new NavDestination.DeepLinkMatch[]{matchDeepLink, (NavDestination.DeepLinkMatch) OnDeviceLanguageIdentificationLogEvent.maxOrNull(arrayList)}));
    }

    @Override // androidx.navigation.NavDestination
    public final void onInflate(Context context, AttributeSet attributeSet) {
        super.onInflate(context, attributeSet);
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, androidx.navigation.common.R$styleable.NavGraphNavigator);
        obtainAttributes.getClass();
        int resourceId = obtainAttributes.getResourceId(0, 0);
        if (resourceId != this.id) {
            this.startDestId = resourceId;
            this.startDestIdName = null;
            this.startDestIdName = ViewParentCompat$Api21Impl.getDisplayName$ar$ds(context, resourceId);
            obtainAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("Start destination " + resourceId + " cannot use the same id as the graph " + this);
    }

    @Override // androidx.navigation.NavDestination
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        NavDestination findNode = findNode((String) null);
        if (findNode == null) {
            findNode = findNode(this.startDestId);
        }
        sb.append(" startDestination=");
        if (findNode == null) {
            String str = this.startDestIdName;
            if (str != null) {
                sb.append(str);
            } else {
                sb.append("0x".concat(String.valueOf(Integer.toHexString(this.startDestId))));
            }
        } else {
            sb.append("{");
            sb.append(findNode.toString());
            sb.append("}");
        }
        return sb.toString();
    }

    public final NavDestination findNode(int i) {
        return findNode(i, true);
    }

    public final NavDestination findNode(int i, boolean z) {
        NavGraph navGraph;
        NavDestination navDestination = (NavDestination) SparseArrayCompatKt.commonGet(this.nodes, i);
        if (navDestination == null) {
            navDestination = null;
            if (z && (navGraph = this.parent) != null) {
                return navGraph.findNode(i);
            }
        }
        return navDestination;
    }
}
