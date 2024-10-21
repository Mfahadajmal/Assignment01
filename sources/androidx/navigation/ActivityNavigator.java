package androidx.navigation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import androidx.navigation.Navigator;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
@Navigator.Name("activity")
/* loaded from: classes.dex */
public class ActivityNavigator extends Navigator<Destination> {
    private final Context context;
    private final Activity hostActivity;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Destination extends NavDestination {
        public String dataPattern;
        public Intent intent;

        public Destination(Navigator navigator) {
            super(navigator);
        }

        private static final String parseApplicationId$ar$ds(Context context, String str) {
            if (str != null) {
                String packageName = context.getPackageName();
                packageName.getClass();
                return OnDeviceStainRemovalLogEvent.replace$default$ar$ds(str, "${applicationId}", packageName);
            }
            return null;
        }

        @Override // androidx.navigation.NavDestination
        public final boolean equals(Object obj) {
            Intent intent;
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof Destination) && super.equals(obj) && ((intent = this.intent) == null ? ((Destination) obj).intent == null : intent.filterEquals(((Destination) obj).intent)) && Intrinsics.areEqual(this.dataPattern, ((Destination) obj).dataPattern)) {
                return true;
            }
            return false;
        }

        @Override // androidx.navigation.NavDestination
        public final int hashCode() {
            int i;
            int hashCode = super.hashCode() * 31;
            Intent intent = this.intent;
            int i2 = 0;
            if (intent != null) {
                i = intent.filterHashCode();
            } else {
                i = 0;
            }
            int i3 = (hashCode + i) * 31;
            String str = this.dataPattern;
            if (str != null) {
                i2 = str.hashCode();
            }
            return i3 + i2;
        }

        @Override // androidx.navigation.NavDestination
        public final void onInflate(Context context, AttributeSet attributeSet) {
            super.onInflate(context, attributeSet);
            TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R$styleable.ActivityNavigator);
            obtainAttributes.getClass();
            String parseApplicationId$ar$ds = parseApplicationId$ar$ds(context, obtainAttributes.getString(4));
            if (this.intent == null) {
                this.intent = new Intent();
            }
            Intent intent = this.intent;
            intent.getClass();
            intent.setPackage(parseApplicationId$ar$ds);
            String string = obtainAttributes.getString(0);
            if (string != null) {
                if (string.charAt(0) == '.') {
                    string = String.valueOf(context.getPackageName()).concat(string);
                }
                ComponentName componentName = new ComponentName(context, string);
                if (this.intent == null) {
                    this.intent = new Intent();
                }
                Intent intent2 = this.intent;
                intent2.getClass();
                intent2.setComponent(componentName);
            }
            String string2 = obtainAttributes.getString(1);
            if (this.intent == null) {
                this.intent = new Intent();
            }
            Intent intent3 = this.intent;
            intent3.getClass();
            intent3.setAction(string2);
            String parseApplicationId$ar$ds2 = parseApplicationId$ar$ds(context, obtainAttributes.getString(2));
            if (parseApplicationId$ar$ds2 != null) {
                Uri parse = Uri.parse(parseApplicationId$ar$ds2);
                if (this.intent == null) {
                    this.intent = new Intent();
                }
                Intent intent4 = this.intent;
                intent4.getClass();
                intent4.setData(parse);
            }
            this.dataPattern = parseApplicationId$ar$ds(context, obtainAttributes.getString(3));
            obtainAttributes.recycle();
        }

        @Override // androidx.navigation.NavDestination
        public final boolean supportsActions() {
            return false;
        }

        @Override // androidx.navigation.NavDestination
        public final String toString() {
            ComponentName componentName;
            Intent intent = this.intent;
            String str = null;
            if (intent != null) {
                componentName = intent.getComponent();
            } else {
                componentName = null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            if (componentName != null) {
                sb.append(" class=");
                sb.append(componentName.getClassName());
            } else {
                Intent intent2 = this.intent;
                if (intent2 != null) {
                    str = intent2.getAction();
                }
                if (str != null) {
                    sb.append(" action=");
                    sb.append(str);
                }
            }
            return sb.toString();
        }
    }

    public ActivityNavigator(Context context) {
        Object obj;
        this.context = context;
        Iterator it = OnDeviceSmartReplyLogEvent.generateSequence(context, ActivityNavigator$hostActivity$1.INSTANCE).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (((Context) obj) instanceof Activity) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        this.hostActivity = (Activity) obj;
    }

    @Override // androidx.navigation.Navigator
    public final /* synthetic */ NavDestination createDestination() {
        return new Destination(this);
    }

    @Override // androidx.navigation.Navigator
    public final /* bridge */ /* synthetic */ NavDestination navigate$ar$ds(NavDestination navDestination, Bundle bundle, NavOptions navOptions) {
        Intent intent;
        int intExtra;
        Destination destination = (Destination) navDestination;
        Intent intent2 = destination.intent;
        if (intent2 != null) {
            Intent intent3 = new Intent(intent2);
            if (bundle != null) {
                intent3.putExtras(bundle);
                String str = destination.dataPattern;
                if (str != null && str.length() != 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(str);
                    while (matcher.find()) {
                        String group = matcher.group(1);
                        if (bundle.containsKey(group)) {
                            matcher.appendReplacement(stringBuffer, "");
                            stringBuffer.append(Uri.encode(String.valueOf(bundle.get(group))));
                        } else {
                            throw new IllegalArgumentException("Could not find " + group + " in " + bundle + " to fill data pattern " + str);
                        }
                    }
                    matcher.appendTail(stringBuffer);
                    intent3.setData(Uri.parse(stringBuffer.toString()));
                }
            }
            if (this.hostActivity == null) {
                intent3.addFlags(268435456);
            }
            if (navOptions != null && navOptions.singleTop) {
                intent3.addFlags(536870912);
            }
            Activity activity = this.hostActivity;
            if (activity != null && (intent = activity.getIntent()) != null && (intExtra = intent.getIntExtra("android-support-navigation:ActivityNavigator:current", 0)) != 0) {
                intent3.putExtra("android-support-navigation:ActivityNavigator:source", intExtra);
            }
            intent3.putExtra("android-support-navigation:ActivityNavigator:current", destination.id);
            Resources resources = this.context.getResources();
            if (navOptions != null) {
                int i = navOptions.popEnterAnim;
                int i2 = navOptions.popExitAnim;
                if ((i > 0 && Intrinsics.areEqual(resources.getResourceTypeName(i), "animator")) || (i2 > 0 && Intrinsics.areEqual(resources.getResourceTypeName(i2), "animator"))) {
                    Log.w("ActivityNavigator", "Activity destinations do not support Animator resource. Ignoring popEnter resource " + resources.getResourceName(i) + " and popExit resource " + resources.getResourceName(i2) + " when launching " + destination);
                } else {
                    intent3.putExtra("android-support-navigation:ActivityNavigator:popEnterAnim", i);
                    intent3.putExtra("android-support-navigation:ActivityNavigator:popExitAnim", i2);
                }
            }
            this.context.startActivity(intent3);
            if (navOptions != null && this.hostActivity != null) {
                int i3 = navOptions.enterAnim;
                int i4 = navOptions.exitAnim;
                if ((i3 > 0 && Intrinsics.areEqual(resources.getResourceTypeName(i3), "animator")) || (i4 > 0 && Intrinsics.areEqual(resources.getResourceTypeName(i4), "animator"))) {
                    Log.w("ActivityNavigator", "Activity destinations do not support Animator resource. Ignoring enter resource " + resources.getResourceName(i3) + " and exit resource " + resources.getResourceName(i4) + "when launching " + destination);
                    return null;
                }
                if (i3 >= 0 || i4 >= 0) {
                    this.hostActivity.overridePendingTransition(OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(i3, 0), OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(i4, 0));
                    return null;
                }
                return null;
            }
            return null;
        }
        throw new IllegalStateException("Destination " + destination.id + " does not have an Intent set.");
    }

    @Override // androidx.navigation.Navigator
    public final boolean popBackStack() {
        Activity activity = this.hostActivity;
        if (activity != null) {
            activity.finish();
            return true;
        }
        return false;
    }
}
