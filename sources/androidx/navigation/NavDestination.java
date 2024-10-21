package androidx.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.activity.OnBackPressedDispatcher;
import androidx.collection.SparseArrayCompat;
import androidx.collection.SparseArrayCompatKt;
import androidx.collection.SparseArrayKt$valueIterator$1;
import androidx.core.view.ViewConfigurationCompat$Api26Impl;
import androidx.core.view.ViewParentCompat$Api21Impl;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NavDestination {
    public static final ViewParentCompat$Api21Impl Companion$ar$class_merging$b389cfd1_0 = new ViewParentCompat$Api21Impl();
    public int id;
    public String idName;
    public CharSequence label;
    public final String navigatorName;
    public NavGraph parent;
    public String route;
    private final List deepLinks = new ArrayList();
    public final SparseArrayCompat actions = new SparseArrayCompat();
    public final Map _arguments = new LinkedHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DeepLinkMatch implements Comparable {
        public final NavDestination destination;
        private final boolean hasMatchingAction;
        private final boolean isExactDeepLink;
        public final Bundle matchingArgs;
        private final int matchingPathSegments;
        private final int mimeTypeMatchLevel;

        public DeepLinkMatch(NavDestination navDestination, Bundle bundle, boolean z, int i, boolean z2, int i2) {
            this.destination = navDestination;
            this.matchingArgs = bundle;
            this.isExactDeepLink = z;
            this.matchingPathSegments = i;
            this.hasMatchingAction = z2;
            this.mimeTypeMatchLevel = i2;
        }

        @Override // java.lang.Comparable
        public final int compareTo(DeepLinkMatch deepLinkMatch) {
            deepLinkMatch.getClass();
            if (this.isExactDeepLink) {
                if (!deepLinkMatch.isExactDeepLink) {
                    return 1;
                }
            } else if (deepLinkMatch.isExactDeepLink) {
                return -1;
            }
            int i = this.matchingPathSegments - deepLinkMatch.matchingPathSegments;
            if (i > 0) {
                return 1;
            }
            if (i < 0) {
                return -1;
            }
            Bundle bundle = this.matchingArgs;
            if (bundle != null && deepLinkMatch.matchingArgs == null) {
                return 1;
            }
            if (bundle == null && deepLinkMatch.matchingArgs != null) {
                return -1;
            }
            if (bundle != null) {
                Bundle bundle2 = deepLinkMatch.matchingArgs;
                int size = bundle.size();
                bundle2.getClass();
                int size2 = size - bundle2.size();
                if (size2 > 0) {
                    return 1;
                }
                if (size2 < 0) {
                    return -1;
                }
            }
            if (this.hasMatchingAction) {
                if (!deepLinkMatch.hasMatchingAction) {
                    return 1;
                }
            } else if (deepLinkMatch.hasMatchingAction) {
                return -1;
            }
            return this.mimeTypeMatchLevel - deepLinkMatch.mimeTypeMatchLevel;
        }
    }

    static {
        new LinkedHashMap();
    }

    public NavDestination(Navigator navigator) {
        this.navigatorName = NavigatorProvider.Companion$ar$class_merging$99173bec_0.getNameForNavigator$navigation_common_release(navigator.getClass());
    }

    public final void addDeepLink(NavDeepLink navDeepLink) {
        List missingRequiredArguments = ViewConfigurationCompat$Api26Impl.missingRequiredArguments(this._arguments, new OnBackPressedDispatcher.AnonymousClass1(navDeepLink, 8));
        if (missingRequiredArguments.isEmpty()) {
            this.deepLinks.add(navDeepLink);
            return;
        }
        throw new IllegalArgumentException("Deep link " + navDeepLink.uriPattern + " can't be used to open destination " + this + ".\nFollowing required arguments are missing: " + missingRequiredArguments);
    }

    public final Bundle addInDefaultArgs(Bundle bundle) {
        if (bundle == null && this._arguments.isEmpty()) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        for (Map.Entry entry : this._arguments.entrySet()) {
            ((NavArgument) entry.getValue()).putDefaultValue((String) entry.getKey(), bundle2);
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            for (Map.Entry entry2 : this._arguments.entrySet()) {
                String str = (String) entry2.getKey();
                NavArgument navArgument = (NavArgument) entry2.getValue();
                str.getClass();
                if (navArgument.isNullable || !bundle2.containsKey(str) || bundle2.get(str) != null) {
                    try {
                        navArgument.type.get(bundle2, str);
                    } catch (ClassCastException unused) {
                    }
                }
                throw new IllegalArgumentException("Wrong argument type for '" + str + "' in argument bundle. " + navArgument.type.getName() + " expected.");
            }
        }
        return bundle2;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 1
            if (r8 != r9) goto L4
            return r0
        L4:
            r1 = 0
            if (r9 == 0) goto Lbd
            boolean r2 = r9 instanceof androidx.navigation.NavDestination
            if (r2 != 0) goto Ld
            goto Lbd
        Ld:
            java.util.List r2 = r8.deepLinks
            androidx.navigation.NavDestination r9 = (androidx.navigation.NavDestination) r9
            java.util.List r3 = r9.deepLinks
            androidx.collection.SparseArrayCompat r4 = r8.actions
            androidx.collection.SparseArrayCompat r5 = r9.actions
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            int r3 = r4.size()
            int r4 = r5.size()
            if (r3 != r4) goto L59
            androidx.collection.SparseArrayCompat r3 = r8.actions
            androidx.collection.SparseArrayKt$keyIterator$1 r4 = new androidx.collection.SparseArrayKt$keyIterator$1
            r4.<init>()
            kotlin.sequences.Sequence r3 = com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent.asSequence(r4)
            java.util.Iterator r3 = r3.iterator()
        L34:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L57
            java.lang.Object r4 = r3.next()
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            androidx.collection.SparseArrayCompat r5 = r8.actions
            java.lang.Object r5 = androidx.collection.SparseArrayCompatKt.commonGet(r5, r4)
            androidx.collection.SparseArrayCompat r6 = r9.actions
            java.lang.Object r4 = androidx.collection.SparseArrayCompatKt.commonGet(r6, r4)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r4)
            if (r4 != 0) goto L34
            goto L59
        L57:
            r3 = r0
            goto L5a
        L59:
            r3 = r1
        L5a:
            java.util.Map r4 = r8._arguments
            int r4 = r4.size()
            java.util.Map r5 = r9._arguments
            int r5 = r5.size()
            if (r4 != r5) goto La5
            java.util.Map r4 = r8._arguments
            java.util.Set r4 = r4.entrySet()
            kotlin.sequences.Sequence r4 = com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.asSequence(r4)
            java.util.Iterator r4 = r4.iterator()
        L76:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto La3
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.util.Map r6 = r9._arguments
            java.lang.Object r7 = r5.getKey()
            boolean r6 = r6.containsKey(r7)
            if (r6 == 0) goto La5
            java.util.Map r6 = r9._arguments
            java.lang.Object r7 = r5.getKey()
            java.lang.Object r6 = r6.get(r7)
            java.lang.Object r5 = r5.getValue()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r5)
            if (r5 != 0) goto L76
            goto La5
        La3:
            r4 = r0
            goto La6
        La5:
            r4 = r1
        La6:
            int r5 = r8.id
            int r6 = r9.id
            if (r5 != r6) goto Lbd
            java.lang.String r5 = r8.route
            java.lang.String r9 = r9.route
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r9)
            if (r9 == 0) goto Lbd
            if (r2 == 0) goto Lbd
            if (r3 == 0) goto Lbd
            if (r4 == 0) goto Lbd
            return r0
        Lbd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavDestination.equals(java.lang.Object):boolean");
    }

    public final NavAction getAction(int i) {
        NavAction navAction;
        if (this.actions.size() == 0) {
            navAction = null;
        } else {
            navAction = (NavAction) SparseArrayCompatKt.commonGet(this.actions, i);
        }
        if (navAction == null) {
            NavGraph navGraph = this.parent;
            if (navGraph == null) {
                return null;
            }
            return navGraph.getAction(i);
        }
        return navAction;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        Set<String> keySet;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = this.id * 31;
        String str = this.route;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        int i9 = i8 + i;
        for (NavDeepLink navDeepLink : this.deepLinks) {
            int i10 = i9 * 31;
            String str2 = navDeepLink.uriPattern;
            if (str2 != null) {
                i5 = str2.hashCode();
            } else {
                i5 = 0;
            }
            int i11 = (i10 + i5) * 31;
            String str3 = navDeepLink.action;
            if (str3 != null) {
                i6 = str3.hashCode();
            } else {
                i6 = 0;
            }
            int i12 = (i11 + i6) * 31;
            String str4 = navDeepLink.mimeType;
            if (str4 != null) {
                i7 = str4.hashCode();
            } else {
                i7 = 0;
            }
            i9 = i12 + i7;
        }
        SparseArrayKt$valueIterator$1 sparseArrayKt$valueIterator$1 = new SparseArrayKt$valueIterator$1(this.actions);
        while (sparseArrayKt$valueIterator$1.hasNext()) {
            NavAction navAction = (NavAction) sparseArrayKt$valueIterator$1.next();
            int i13 = ((i9 * 31) + navAction.destinationId) * 31;
            NavOptions navOptions = navAction.navOptions;
            if (navOptions != null) {
                i3 = navOptions.hashCode();
            } else {
                i3 = 0;
            }
            i9 = i13 + i3;
            Bundle bundle = navAction.defaultArguments;
            if (bundle != null && (keySet = bundle.keySet()) != null) {
                for (String str5 : keySet) {
                    int i14 = i9 * 31;
                    Bundle bundle2 = navAction.defaultArguments;
                    bundle2.getClass();
                    Object obj = bundle2.get(str5);
                    if (obj != null) {
                        i4 = obj.hashCode();
                    } else {
                        i4 = 0;
                    }
                    i9 = i14 + i4;
                }
            }
        }
        for (String str6 : this._arguments.keySet()) {
            int hashCode = ((i9 * 31) + str6.hashCode()) * 31;
            Object obj2 = this._arguments.get(str6);
            if (obj2 != null) {
                i2 = obj2.hashCode();
            } else {
                i2 = 0;
            }
            i9 = hashCode + i2;
        }
        return i9;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0184 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.navigation.NavDestination.DeepLinkMatch matchDeepLink(androidx.navigation.NavDeepLinkRequest r18) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavDestination.matchDeepLink(androidx.navigation.NavDeepLinkRequest):androidx.navigation.NavDestination$DeepLinkMatch");
    }

    public void onInflate(Context context, AttributeSet attributeSet) {
        Object obj;
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, androidx.navigation.common.R$styleable.Navigator);
        obtainAttributes.getClass();
        String string = obtainAttributes.getString(2);
        if (string == null) {
            setId(0);
        } else if (!OnDeviceStainRemovalLogEvent.isBlank(string)) {
            String createRoute$ar$ds = ViewParentCompat$Api21Impl.createRoute$ar$ds(string);
            setId(createRoute$ar$ds.hashCode());
            AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent();
            aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = createRoute$ar$ds;
            addDeepLink(aggregatedOnDeviceTextDetectionLogEvent.build());
        } else {
            throw new IllegalArgumentException("Cannot have an empty route");
        }
        List list = this.deepLinks;
        Iterator it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((NavDeepLink) obj).uriPattern, ViewParentCompat$Api21Impl.createRoute$ar$ds(this.route))) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        TypeIntrinsics.asMutableCollection(list).remove(obj);
        this.route = string;
        if (obtainAttributes.hasValue(1)) {
            setId(obtainAttributes.getResourceId(1, 0));
            this.idName = ViewParentCompat$Api21Impl.getDisplayName$ar$ds(context, this.id);
        }
        this.label = obtainAttributes.getText(0);
        obtainAttributes.recycle();
    }

    public final void setId(int i) {
        this.id = i;
        this.idName = null;
    }

    public boolean supportsActions() {
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        String str = this.idName;
        if (str == null) {
            sb.append("0x");
            sb.append(Integer.toHexString(this.id));
        } else {
            sb.append(str);
        }
        sb.append(")");
        String str2 = this.route;
        if (str2 != null && !OnDeviceStainRemovalLogEvent.isBlank(str2)) {
            sb.append(" route=");
            sb.append(this.route);
        }
        if (this.label != null) {
            sb.append(" label=");
            sb.append(this.label);
        }
        return sb.toString();
    }
}
