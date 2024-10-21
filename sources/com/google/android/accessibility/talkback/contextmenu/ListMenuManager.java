package com.google.android.accessibility.talkback.contextmenu;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.accessibility.utils.widget.NonScrollableListView;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.mdi.download.debug.MddDebugListAdapter$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.android.material.datepicker.YearGridAdapter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import io.grpc.CallCredentials$RequestInfo;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListMenuManager implements WindowEventInterpreter.WindowEventHandler, AccessibilityEventListener {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public final ActorState actorState;
    public final TalkBackAnalytics analytics;
    private ContextMenu contextMenu;
    public A11yAlertDialogWrapper currentDialog;
    private AccessibilityNodeInfoCompat currentNode;
    public OrderVerifyingClientCall.State deferredAction$ar$class_merging$ar$class_merging;
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    public long lastMenuDismissUptimeMs;
    public final WindowTrackerFactory menuClickProcessor$ar$class_merging$ar$class_merging$ar$class_merging;
    public int menuShown;
    private final WindowTrackerFactory nodeMenuRuleProcessor$ar$class_merging$ar$class_merging;
    public final Pipeline.FeedbackReturner pipeline;
    public final TalkBackService service;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RecyclerViewAdapter extends RecyclerView.Adapter {
        private final ImmutableList items;
        public final CallCredentials$RequestInfo listener$ar$class_merging$19028214_0$ar$class_merging$ar$class_merging;

        public RecyclerViewAdapter(ImmutableList immutableList, CallCredentials$RequestInfo callCredentials$RequestInfo) {
            this.items = immutableList;
            this.listener$ar$class_merging$19028214_0$ar$class_merging$ar$class_merging = callCredentials$RequestInfo;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final int getItemCount() {
            return ((RegularImmutableList) this.items).size;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            YearGridAdapter.ViewHolder viewHolder2 = (YearGridAdapter.ViewHolder) viewHolder;
            ((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView).setText((CharSequence) this.items.get(i));
            ((ViewGroup) ((TextView) viewHolder2.YearGridAdapter$ViewHolder$ar$textView).getParent()).setOnClickListener(new MddDebugListAdapter$$ExternalSyntheticLambda0(this, i, 1));
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new YearGridAdapter.ViewHolder((ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_simple_framelayout, viewGroup, false));
        }
    }

    public ListMenuManager(TalkBackService talkBackService, Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, AccessibilityFocusMonitor accessibilityFocusMonitor, WindowTrackerFactory windowTrackerFactory, TalkBackAnalytics talkBackAnalytics) {
        this.service = talkBackService;
        this.pipeline = feedbackReturner;
        this.actorState = actorState;
        this.nodeMenuRuleProcessor$ar$class_merging$ar$class_merging = windowTrackerFactory;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.analytics = talkBackAnalytics;
        this.menuClickProcessor$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory(talkBackService, feedbackReturner);
    }

    private final void clearCurrentNode() {
        this.currentNode = null;
    }

    private final void executeDeferredActionByType$ar$edu(int i) {
        OrderVerifyingClientCall.State state = this.deferredAction$ar$class_merging$ar$class_merging;
        if (state != null && state.type$ar$edu$88c656f2_0 == i) {
            new Handler().post(new VoiceActionMonitor$$ExternalSyntheticLambda0(state, 17));
            this.deferredAction$ar$class_merging$ar$class_merging = null;
            if (i == 3) {
                this.service.postRemoveEventListener(this);
            }
        }
    }

    public static final CharSequence[] getItemsFromMenu$ar$ds(Menu menu) {
        int size = menu.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            MenuItem item = menu.getItem(i);
            if (item != null && item.isVisible()) {
                arrayList.add(item.getTitle());
            }
        }
        return (CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()]);
    }

    public final void attachContentDescriptionOnTitle(A11yAlertDialogWrapper a11yAlertDialogWrapper, String str) {
        int identifier = this.service.getResources().getIdentifier("alertTitle", "id", this.service.getPackageName());
        if (identifier > 0) {
            TextView textView = (TextView) a11yAlertDialogWrapper.getDialog().findViewById(identifier);
            if (textView != null) {
                textView.setContentDescription(str);
                return;
            }
            return;
        }
        LogUtils.w("ListMenuManager", "Cannot find the title TextView to add contentDescription.", new Object[0]);
    }

    public final void clearMenu() {
        ContextMenu contextMenu = this.contextMenu;
        if (contextMenu != null) {
            contextMenu.clear();
            this.contextMenu = null;
        }
        clearCurrentNode();
    }

    public final void dismissAll() {
        A11yAlertDialogWrapper a11yAlertDialogWrapper = this.currentDialog;
        if (a11yAlertDialogWrapper != null && a11yAlertDialogWrapper.isShowing()) {
            this.currentDialog.dismiss();
            this.currentDialog = null;
        }
        clearMenu();
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 32768;
    }

    @Override // com.google.android.accessibility.utils.input.WindowEventInterpreter.WindowEventHandler
    public final void handle(WindowEventInterpreter.EventInterpretation eventInterpretation, Performance.EventId eventId) {
        if (this.deferredAction$ar$class_merging$ar$class_merging != null && eventInterpretation.windowsStable) {
            executeDeferredActionByType$ar$edu(2);
        }
    }

    public final boolean isMenuShowing() {
        A11yAlertDialogWrapper a11yAlertDialogWrapper = this.currentDialog;
        if (a11yAlertDialogWrapper != null && a11yAlertDialogWrapper.isShowing()) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        if (accessibilityEvent.getEventType() == 32768 && this.deferredAction$ar$class_merging$ar$class_merging != null) {
            executeDeferredActionByType$ar$edu(3);
        }
    }

    public final void openAlert$ar$class_merging$ar$class_merging(ApplicationModule applicationModule) {
        A11yAlertDialogWrapper create = applicationModule.create();
        create.setOnDismissListener(new ListMenuManager$$ExternalSyntheticLambda4(this, 0));
        SpannableUtils$IdentifierSpan.setWindowTypeToDialog(create.getWindow());
        create.show();
        this.currentDialog = create;
        this.menuShown++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void showDialogMenu(String str, CharSequence[] charSequenceArr, ContextMenu contextMenu, Performance.EventId eventId) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        View listView;
        if (charSequenceArr != null && charSequenceArr.length != 0) {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this.service, 2);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$51f49cd0_0$ar$ds(str);
            CallCredentials$RequestInfo callCredentials$RequestInfo = new CallCredentials$RequestInfo(this, contextMenu, eventId);
            FormFactorUtils formFactorUtils = this.formFactorUtils;
            if (formFactorUtils.isAndroidWear) {
                listView = new NonScrollableListView(this.service);
            } else if (formFactorUtils.isAndroidTv) {
                listView = new RecyclerView(this.service);
            } else {
                listView = new ListView(this.service);
            }
            listView.setId(R.id.talkback_menu_listview);
            byte[] bArr = null;
            listView.setBackground(null);
            boolean z = listView instanceof ListView;
            if (z) {
                ListView listView2 = (ListView) listView;
                listView2.setDivider(null);
                listView2.setDividerHeight(this.service.getResources().getDimensionPixelSize(R.dimen.alertdialog_menuitem_divider_height));
            }
            listView.setPaddingRelative(this.service.getResources().getDimensionPixelSize(R.dimen.alertdialog_padding_start), this.service.getResources().getDimensionPixelSize(R.dimen.alertdialog_padding_top), this.service.getResources().getDimensionPixelSize(R.dimen.alertdialog_padding_end), this.service.getResources().getDimensionPixelSize(R.dimen.alertdialog_padding_bottom));
            int i = 2;
            if (z) {
                ListView listView3 = (ListView) listView;
                listView3.setAdapter((ListAdapter) new ArrayAdapter(new ContextThemeWrapper(this.service, R.style.A11yAlertDialogCustomViewTheme), R.layout.list_item_simple_framelayout, android.R.id.text1, charSequenceArr));
                listView3.setOnItemClickListener(new ContextMenuDialog.AnonymousClass1(callCredentials$RequestInfo, i, bArr));
            }
            if (listView instanceof RecyclerView) {
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(ImmutableList.copyOf(charSequenceArr), callCredentials$RequestInfo);
                RecyclerView recyclerView = (RecyclerView) listView;
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(listView.getContext()));
            }
            listView.getContext().setTheme(R.style.A11yAlertDialogCustomViewTheme);
            if (this.formFactorUtils.isAndroidWear) {
                listView.requestFocus();
            }
            if (this.formFactorUtils.isAndroidTv) {
                listView.requestFocus();
            }
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setView$ar$class_merging$ar$ds(listView);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 19));
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setCancelable$ar$class_merging$ar$ds();
            if (this.formFactorUtils.isAndroidTv) {
                new Handler(Looper.getMainLooper()).post(new ListMenuManager$$ExternalSyntheticLambda3(this, createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging, i));
            } else {
                openAlert$ar$class_merging$ar$class_merging(createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging);
            }
        }
    }

    public final boolean showMenu$ar$edu(int i, Performance.EventId eventId) {
        return showMenu$ar$edu$a8c95a97_0(i, eventId, -1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x013e, code lost:
    
        if (r4 == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x015d, code lost:
    
        if (r4 == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x017b, code lost:
    
        if (r4 == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x019f, code lost:
    
        if (r4 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01b7, code lost:
    
        if (r4 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00cb, code lost:
    
        if (r4 == false) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x034d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean showMenu$ar$edu$a8c95a97_0(int r20, final com.google.android.accessibility.utils.Performance.EventId r21, int r22) {
        /*
            Method dump skipped, instructions count: 1247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.contextmenu.ListMenuManager.showMenu$ar$edu$a8c95a97_0(int, com.google.android.accessibility.utils.Performance$EventId, int):boolean");
    }
}
