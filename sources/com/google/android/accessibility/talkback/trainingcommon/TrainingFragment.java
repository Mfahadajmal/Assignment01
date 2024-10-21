package com.google.android.accessibility.talkback.trainingcommon;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.talkback.trainingcommon.content.ClickableChip;
import com.google.android.accessibility.talkback.trainingcommon.content.ExitBanner;
import com.google.android.accessibility.talkback.trainingcommon.content.PageButton;
import com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig;
import com.google.android.accessibility.talkback.trainingcommon.content.PageNumber;
import com.google.android.accessibility.talkback.trainingcommon.content.Title;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingFragment extends Fragment {
    public TrainingIpcClient.ServiceData data;
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    public HapticPatternParser$$ExternalSyntheticLambda1 linkHandler$ar$class_merging$ar$class_merging;
    public WindowTrackerFactory metricStore$ar$class_merging$ar$class_merging;
    NavigationButtonBar navigationButtonBar;
    public Function navigationButtonBarSupplier;
    private PageConfig page;
    private LinearLayout pageBannerLayout;
    private LinearLayout pageLayout;
    private RepeatedAnnouncingHandler repeatedAnnouncingHandler;

    private final void addView(View view) {
        if (this.page == null) {
            LogUtils.e("TrainingFragment", "Cannot add view to fragment because no page.", new Object[0]);
        }
        if (this.page.isOnlyOneFocus()) {
            view.setImportantForAccessibility(4);
        }
        this.pageLayout.addView(view);
    }

    private final void setTrainingPageInitialFocus(View view) {
        if (view != null) {
            ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: com.google.android.accessibility.talkback.trainingcommon.TrainingFragment.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public final void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                    if (Build.VERSION.SDK_INT >= 34) {
                        AccessibilityNodeInfoCompat.Api34Impl.setRequestInitialAccessibilityFocus(accessibilityNodeInfoCompat.mInfo, true);
                    } else {
                        accessibilityNodeInfoCompat.setBooleanProperty(32, true);
                    }
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout;
        PageConfig.PageAndContentPredicate pageAndContentPredicate;
        Function function;
        Object apply;
        View inflate = layoutInflater.inflate(R.layout.training_fragment_name, viewGroup, false);
        this.pageLayout = (LinearLayout) inflate.findViewById(R.id.training_page);
        this.pageBannerLayout = (LinearLayout) inflate.findViewById(R.id.training_page_banner);
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtils.e("TrainingFragment", "Cannot create view because fragment was created without arguments.", new Object[0]);
            return inflate;
        }
        PageConfig.PageId pageId = (PageConfig.PageId) arguments.get("page");
        if (pageId == null) {
            LogUtils.e("TrainingFragment", "Cannot create view because no page ID.", new Object[0]);
            return inflate;
        }
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtils.e("TrainingFragment", "Cannot create view because fragment is not attached to activity.", new Object[0]);
            return inflate;
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.id.nav_container);
        if (viewGroup2 != null && (function = this.navigationButtonBarSupplier) != null) {
            apply = function.apply(inflate.getContext());
            NavigationButtonBar navigationButtonBar = (NavigationButtonBar) apply;
            this.navigationButtonBar = navigationButtonBar;
            viewGroup2.addView(navigationButtonBar);
        }
        PageConfig page = PageConfig.getPage(pageId, activity, arguments.getInt("vendor_page_index", -1));
        this.page = page;
        if (page != null) {
            View createView = new Title(page).createView(layoutInflater, viewGroup, getContext(), this.data);
            addView(createView);
            if (this.page.getPageId() == PageConfig.PageId.PAGE_ID_UPDATE_WELCOME) {
                setTrainingPageInitialFocus(createView);
            }
            int i = getArguments().getInt("page_number");
            int i2 = getArguments().getInt("total_number");
            if (i > 0 && i2 > 0) {
                addView(new PageNumber(i, i2).createView(layoutInflater, viewGroup, getContext(), this.data));
            }
            ImmutableList contents = this.page.getContents();
            int size = contents.size();
            for (int i3 = 0; i3 < size; i3++) {
                PageContentConfig pageContentConfig = (PageContentConfig) contents.get(i3);
                TrainingIpcClient.ServiceData serviceData = this.data;
                if (serviceData != null && ((pageAndContentPredicate = pageContentConfig.predicate) == null || pageAndContentPredicate.test(serviceData))) {
                    if ((pageContentConfig instanceof ExitBanner) && this.pageBannerLayout != null) {
                        View createView2 = pageContentConfig.createView(layoutInflater, viewGroup, getContext(), this.data);
                        ExitBanner exitBanner = (ExitBanner) pageContentConfig;
                        exitBanner.requestDisableTalkBack$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this);
                        exitBanner.metricStore$ar$class_merging$ar$class_merging = this.metricStore$ar$class_merging$ar$class_merging;
                        this.pageBannerLayout.addView(createView2);
                    } else if (pageContentConfig instanceof PageButton) {
                        View createView3 = pageContentConfig.createView(layoutInflater, viewGroup, getContext(), this.data);
                        Button button = ((PageButton) pageContentConfig).button;
                        addView(createView3);
                    } else {
                        if (pageContentConfig instanceof ClickableChip) {
                            ((ClickableChip) pageContentConfig).linkHandler$ar$class_merging$ar$class_merging = this.linkHandler$ar$class_merging$ar$class_merging;
                        }
                        addView(pageContentConfig.createView(layoutInflater, viewGroup, getContext(), this.data));
                    }
                }
            }
            this.pageLayout.setImportantForAccessibility(0);
            if (this.page.isOnlyOneFocus() && (linearLayout = this.pageBannerLayout) != null && linearLayout.getChildCount() > 0) {
                setTrainingPageInitialFocus(inflate.findViewById(R.id.training_page_scroll));
            }
            if (this.page.getIdleAnnouncementConfig() != null) {
                this.page.getIdleAnnouncementConfig();
                this.repeatedAnnouncingHandler = new RepeatedAnnouncingHandler(getContext(), getContext().getString(R.string.welcome_to_talkback_page_idle_announcement));
                LogUtils.v("TrainingFragment", "Idle announcement prepared.", new Object[0]);
            }
            if (this.formFactorUtils.isAndroidWear) {
                ViewCompat.setAccessibilityPaneTitle(inflate, getString(this.page.getPageNameResId()));
                inflate.requestFocus();
                if (getActivity() instanceof TrainingActivity) {
                    boolean z = ((TrainingActivity) getActivity()).formFactorUtils.isAndroidWear;
                }
            }
            if (this.formFactorUtils.isAndroidTv) {
                inflate.findViewById(R.id.training_page_wrapper).setOnClickListener(new TrainingActivity$$ExternalSyntheticLambda0(this, 2));
                this.pageLayout.requestFocus();
            }
            return inflate;
        }
        LogUtils.e("TrainingFragment", "Cannot create view because unknown PageId. [%s]", pageId.name());
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public final void onPause() {
        super.onPause();
        if (this.repeatedAnnouncingHandler != null) {
            LogUtils.v("TrainingFragment", "Idle announcement unregistered.", new Object[0]);
            this.repeatedAnnouncingHandler.handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.support.v4.app.Fragment
    public final void onResume() {
        super.onResume();
        if (this.repeatedAnnouncingHandler != null) {
            LogUtils.v("TrainingFragment", "Idle announcement registered.", new Object[0]);
            RepeatedAnnouncingHandler repeatedAnnouncingHandler = this.repeatedAnnouncingHandler;
            repeatedAnnouncingHandler.handler.postDelayed(repeatedAnnouncingHandler.idleAnnouncementTask, repeatedAnnouncingHandler.initialDelayMs);
        }
    }
}
