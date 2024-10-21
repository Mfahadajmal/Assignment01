package android.support.v4.app;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.fragment.R$styleable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.fragment.app.strictmode.FragmentTagUsageViolation;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    final FragmentManager mFragmentManager;

    public FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        final FragmentStateManager createOrGetFragmentStateManager;
        if (FragmentContainerView.class.getName().equals(str)) {
            return new FragmentContainerView(context, attributeSet, this.mFragmentManager);
        }
        if ("fragment".equals(str)) {
            String attributeValue = attributeSet.getAttributeValue(null, "class");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Fragment);
            if (attributeValue == null) {
                attributeValue = obtainStyledAttributes.getString(0);
            }
            int resourceId = obtainStyledAttributes.getResourceId(1, -1);
            String string = obtainStyledAttributes.getString(2);
            obtainStyledAttributes.recycle();
            if (attributeValue != null) {
                ClassLoader classLoader = context.getClassLoader();
                int i = FragmentFactory.FragmentFactory$ar$NoOp;
                try {
                    if (Fragment.class.isAssignableFrom(FragmentFactory.loadClass(classLoader, attributeValue))) {
                        int id = view != null ? view.getId() : 0;
                        if (id == -1) {
                            if (resourceId != -1) {
                                id = -1;
                            } else {
                                if (string == null) {
                                    throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_5(attributeValue, attributeSet, ": Must specify unique android:id, android:tag, or have a parent with an id for "));
                                }
                                id = -1;
                                resourceId = -1;
                            }
                        }
                        Fragment findFragmentById = resourceId != -1 ? this.mFragmentManager.findFragmentById(resourceId) : null;
                        if (findFragmentById == null && string != null) {
                            findFragmentById = this.mFragmentManager.findFragmentByTag(string);
                        }
                        if (findFragmentById == null && id != -1) {
                            findFragmentById = this.mFragmentManager.findFragmentById(id);
                        }
                        if (findFragmentById == null) {
                            FragmentFactory fragmentFactory = this.mFragmentManager.getFragmentFactory();
                            context.getClassLoader();
                            findFragmentById = fragmentFactory.instantiate$ar$ds(attributeValue);
                            findFragmentById.mFromLayout = true;
                            findFragmentById.mFragmentId = resourceId != 0 ? resourceId : id;
                            findFragmentById.mContainerId = id;
                            findFragmentById.mTag = string;
                            findFragmentById.mInLayout = true;
                            findFragmentById.mFragmentManager = this.mFragmentManager;
                            FragmentManager fragmentManager = this.mFragmentManager;
                            findFragmentById.mHost = fragmentManager.mHost;
                            findFragmentById.onInflate(fragmentManager.mHost.context, attributeSet, findFragmentById.mSavedFragmentState);
                            createOrGetFragmentStateManager = this.mFragmentManager.addFragment(findFragmentById);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Objects.toString(findFragmentById);
                                Integer.toHexString(resourceId);
                            }
                        } else if (!findFragmentById.mInLayout) {
                            findFragmentById.mInLayout = true;
                            findFragmentById.mFragmentManager = this.mFragmentManager;
                            FragmentManager fragmentManager2 = this.mFragmentManager;
                            findFragmentById.mHost = fragmentManager2.mHost;
                            findFragmentById.onInflate(fragmentManager2.mHost.context, attributeSet, findFragmentById.mSavedFragmentState);
                            createOrGetFragmentStateManager = this.mFragmentManager.createOrGetFragmentStateManager(findFragmentById);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Objects.toString(findFragmentById);
                                Integer.toHexString(resourceId);
                            }
                        } else {
                            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
                        }
                        ViewGroup viewGroup = (ViewGroup) view;
                        FragmentStrictMode fragmentStrictMode = FragmentStrictMode.INSTANCE;
                        findFragmentById.getClass();
                        FragmentTagUsageViolation fragmentTagUsageViolation = new FragmentTagUsageViolation(findFragmentById, viewGroup);
                        FragmentStrictMode.logIfDebuggingEnabled$ar$ds(fragmentTagUsageViolation);
                        FragmentStrictMode.Policy nearestPolicy$ar$ds = FragmentStrictMode.getNearestPolicy$ar$ds(findFragmentById);
                        if (nearestPolicy$ar$ds.flags.contains(FragmentStrictMode.Flag.DETECT_FRAGMENT_TAG_USAGE) && FragmentStrictMode.shouldHandlePolicyViolation$ar$ds(nearestPolicy$ar$ds, findFragmentById.getClass(), fragmentTagUsageViolation.getClass())) {
                            FragmentStrictMode.handlePolicyViolation$ar$ds(nearestPolicy$ar$ds, fragmentTagUsageViolation);
                        }
                        findFragmentById.mContainer = viewGroup;
                        createOrGetFragmentStateManager.moveToExpectedState();
                        createOrGetFragmentStateManager.ensureInflatedView();
                        View view2 = findFragmentById.mView;
                        if (view2 != null) {
                            if (resourceId != 0) {
                                view2.setId(resourceId);
                            }
                            if (findFragmentById.mView.getTag() == null) {
                                findFragmentById.mView.setTag(string);
                            }
                            findFragmentById.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: android.support.v4.app.FragmentLayoutInflaterFactory.1
                                @Override // android.view.View.OnAttachStateChangeListener
                                public final void onViewAttachedToWindow(View view3) {
                                    FragmentStateManager fragmentStateManager = createOrGetFragmentStateManager;
                                    fragmentStateManager.moveToExpectedState();
                                    SpecialEffectsController.getOrCreateController((ViewGroup) fragmentStateManager.mFragment.mView.getParent(), FragmentLayoutInflaterFactory.this.mFragmentManager).forceCompleteAllOperations();
                                }

                                @Override // android.view.View.OnAttachStateChangeListener
                                public final void onViewDetachedFromWindow(View view3) {
                                }
                            });
                            return findFragmentById.mView;
                        }
                        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(attributeValue, "Fragment ", " did not create a view."));
                    }
                } catch (ClassNotFoundException unused) {
                }
            }
        }
        return null;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
