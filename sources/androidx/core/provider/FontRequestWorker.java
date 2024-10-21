package androidx.core.provider;

import android.content.Context;
import android.os.Process;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Consumer;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FontRequestWorker {
    public static final ExecutorService DEFAULT_EXECUTOR_SERVICE;
    public static final Object LOCK;
    public static final SimpleArrayMap PENDING_REPLIES;
    public static final LruCache sTypefaceCache = new LruCache(16);

    /* compiled from: PG */
    /* renamed from: androidx.core.provider.FontRequestWorker$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Callable {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ FontRequest val$request;
        final /* synthetic */ int val$style;

        public AnonymousClass1(String str, Context context, FontRequest fontRequest, int i) {
            r1 = str;
            r2 = context;
            r3 = fontRequest;
            r4 = i;
        }

        @Override // java.util.concurrent.Callable
        public final /* bridge */ /* synthetic */ Object call() {
            return FontRequestWorker.getFontSync$ar$class_merging$ar$class_merging$ar$class_merging(r1, r2, r3, r4);
        }
    }

    /* compiled from: PG */
    /* renamed from: androidx.core.provider.FontRequestWorker$3 */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements Callable {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ FontRequest val$request;
        final /* synthetic */ int val$style;

        public AnonymousClass3(String str, Context context, FontRequest fontRequest, int i) {
            this.val$id = str;
            this.val$context = context;
            this.val$request = fontRequest;
            this.val$style = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call$ar$class_merging$ar$class_merging$ar$class_merging */
        public final OrderVerifyingClientCall.State call() {
            try {
                return FontRequestWorker.getFontSync$ar$class_merging$ar$class_merging$ar$class_merging(this.val$id, this.val$context, this.val$request, this.val$style);
            } catch (Throwable unused) {
                return new OrderVerifyingClientCall.State(-3, (byte[]) null);
            }
        }
    }

    /* compiled from: PG */
    /* renamed from: androidx.core.provider.FontRequestWorker$4 */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 implements Consumer {
        final /* synthetic */ Object FontRequestWorker$4$ar$val$id;
        private final /* synthetic */ int switching_field;

        public AnonymousClass4(Object obj, int i) {
            r2 = i;
            r1 = obj;
        }

        @Override // androidx.core.util.Consumer
        public final /* synthetic */ void accept(Object obj) {
            if (r2 != 0) {
                OrderVerifyingClientCall.State state = (OrderVerifyingClientCall.State) obj;
                if (state == null) {
                    state = new OrderVerifyingClientCall.State(-3, (byte[]) null);
                }
                ((NodeBrailler) r1).onTypefaceResult$ar$class_merging$ar$class_merging$ar$class_merging(state);
                return;
            }
            OrderVerifyingClientCall.State state2 = (OrderVerifyingClientCall.State) obj;
            synchronized (FontRequestWorker.LOCK) {
                ArrayList arrayList = (ArrayList) FontRequestWorker.PENDING_REPLIES.get(r1);
                if (arrayList == null) {
                    return;
                }
                FontRequestWorker.PENDING_REPLIES.remove(r1);
                for (int i = 0; i < arrayList.size(); i++) {
                    ((Consumer) arrayList.get(i)).accept(state2);
                }
            }
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new ThreadFactory() { // from class: androidx.core.provider.RequestExecutor$DefaultThreadFactory
            private final String mThreadName = "fonts-androidx";
            private final int mPriority = 10;

            /* compiled from: PG */
            /* loaded from: classes.dex */
            final class ProcessPriorityThread extends Thread {
                private final int mPriority;

                public ProcessPriorityThread(Runnable runnable, String str, int i) {
                    super(runnable, "fonts-androidx");
                    this.mPriority = 10;
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    Process.setThreadPriority(this.mPriority);
                    super.run();
                }
            }

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return new ProcessPriorityThread(runnable, this.mThreadName, 10);
            }
        });
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        DEFAULT_EXECUTOR_SERVICE = threadPoolExecutor;
        LOCK = new Object();
        PENDING_REPLIES = new SimpleArrayMap();
    }

    public static String createCacheId(FontRequest fontRequest, int i) {
        return fontRequest.mIdentifier + "-" + i;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x018c A[Catch: NameNotFoundException -> 0x0219, TRY_ENTER, TryCatch #0 {NameNotFoundException -> 0x0219, blocks: (B:5:0x0016, B:7:0x0026, B:9:0x0030, B:11:0x0043, B:13:0x004f, B:14:0x0057, B:16:0x005d, B:20:0x0094, B:22:0x0079, B:24:0x007f, B:26:0x0091, B:31:0x009b, B:59:0x00a3, B:65:0x018c, B:66:0x018f, B:101:0x01e6, B:102:0x01e9, B:103:0x01ec, B:109:0x01ed, B:110:0x0208, B:111:0x0209, B:112:0x0218), top: B:4:0x0016 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall.State getFontSync$ar$class_merging$ar$class_merging$ar$class_merging(java.lang.String r24, android.content.Context r25, androidx.core.provider.FontRequest r26, int r27) {
        /*
            Method dump skipped, instructions count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontRequestWorker.getFontSync$ar$class_merging$ar$class_merging$ar$class_merging(java.lang.String, android.content.Context, androidx.core.provider.FontRequest, int):com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall$State");
    }
}
