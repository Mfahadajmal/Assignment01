package androidx.localbroadcastmanager.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import androidx.work.impl.model.WorkName;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public final class LocalBroadcastManager {
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    public final Context mAppContext;
    private final Handler mHandler;
    public final HashMap mReceivers = new HashMap();
    private final HashMap mActions = new HashMap();
    public final ArrayList mPendingBroadcasts = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        public ReceiverRecord(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.filter = intentFilter;
            this.receiver = broadcastReceiver;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
            sb.append("Receiver{");
            sb.append(this.receiver);
            sb.append(" filter=");
            sb.append(this.filter);
            if (this.dead) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private LocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) { // from class: androidx.localbroadcastmanager.content.LocalBroadcastManager.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int size;
                WorkName[] workNameArr;
                if (message.what != 1) {
                    super.handleMessage(message);
                    return;
                }
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.this;
                while (true) {
                    synchronized (localBroadcastManager.mReceivers) {
                        size = localBroadcastManager.mPendingBroadcasts.size();
                        if (size <= 0) {
                            return;
                        }
                        workNameArr = new WorkName[size];
                        localBroadcastManager.mPendingBroadcasts.toArray(workNameArr);
                        localBroadcastManager.mPendingBroadcasts.clear();
                    }
                    for (int i = 0; i < size; i++) {
                        WorkName workName = workNameArr[i];
                        int size2 = ((ArrayList) workName.WorkName$ar$name).size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            ReceiverRecord receiverRecord = (ReceiverRecord) ((ArrayList) workName.WorkName$ar$name).get(i2);
                            if (!receiverRecord.dead) {
                                receiverRecord.receiver.onReceive(localBroadcastManager.mAppContext, (Intent) workName.WorkName$ar$workSpecId);
                            }
                        }
                    }
                }
            }
        };
    }

    public static LocalBroadcastManager getInstance(Context context) {
        LocalBroadcastManager localBroadcastManager;
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            localBroadcastManager = mInstance;
        }
        return localBroadcastManager;
    }

    public final void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver);
            ArrayList arrayList = (ArrayList) this.mReceivers.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList(1);
                this.mReceivers.put(broadcastReceiver, arrayList);
            }
            arrayList.add(receiverRecord);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList arrayList2 = (ArrayList) this.mActions.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(1);
                    this.mActions.put(action, arrayList2);
                }
                arrayList2.add(receiverRecord);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    public final void sendBroadcast$ar$ds(Intent intent) {
        boolean z;
        int i;
        String str;
        ArrayList arrayList;
        ArrayList arrayList2;
        String str2;
        ?? r11;
        synchronized (this.mReceivers) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            int i2 = 1;
            if ((intent.getFlags() & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                Objects.toString(intent);
            }
            ArrayList arrayList3 = (ArrayList) this.mActions.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Objects.toString(arrayList3);
                }
                ArrayList arrayList4 = null;
                int i3 = 0;
                while (i3 < arrayList3.size()) {
                    ReceiverRecord receiverRecord = (ReceiverRecord) arrayList3.get(i3);
                    if (z) {
                        Objects.toString(receiverRecord.filter);
                    }
                    if (receiverRecord.broadcasting) {
                        i = i3;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        arrayList = arrayList4;
                        r11 = i2;
                    } else {
                        IntentFilter intentFilter = receiverRecord.filter;
                        String str3 = action;
                        String str4 = resolveTypeIfNeeded;
                        i = i3;
                        str = action;
                        arrayList = arrayList4;
                        arrayList2 = arrayList3;
                        str2 = resolveTypeIfNeeded;
                        r11 = i2;
                        int match = intentFilter.match(str3, str4, scheme, data, categories, "LocalBroadcastManager");
                        r11 = r11;
                        if (match >= 0) {
                            if (z) {
                                Integer.toHexString(match);
                            }
                            if (arrayList == null) {
                                arrayList4 = new ArrayList();
                            } else {
                                arrayList4 = arrayList;
                            }
                            arrayList4.add(receiverRecord);
                            receiverRecord.broadcasting = r11;
                            i3 = i + 1;
                            i2 = r11;
                            action = str;
                            arrayList3 = arrayList2;
                            resolveTypeIfNeeded = str2;
                        }
                    }
                    arrayList4 = arrayList;
                    i3 = i + 1;
                    i2 = r11;
                    action = str;
                    arrayList3 = arrayList2;
                    resolveTypeIfNeeded = str2;
                }
                ArrayList arrayList5 = arrayList4;
                int i4 = i2;
                if (arrayList5 != null) {
                    for (int i5 = 0; i5 < arrayList5.size(); i5++) {
                        ((ReceiverRecord) arrayList5.get(i5)).broadcasting = false;
                    }
                    this.mPendingBroadcasts.add(new WorkName(intent, arrayList5));
                    if (!this.mHandler.hasMessages(i4)) {
                        this.mHandler.sendEmptyMessage(i4);
                    }
                }
            }
        }
    }

    public final void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        synchronized (this.mReceivers) {
            ArrayList arrayList = (ArrayList) this.mReceivers.remove(broadcastReceiver);
            if (arrayList == null) {
                return;
            }
            int size = arrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ReceiverRecord receiverRecord = (ReceiverRecord) arrayList.get(size);
                    receiverRecord.dead = true;
                    for (int i = 0; i < receiverRecord.filter.countActions(); i++) {
                        String action = receiverRecord.filter.getAction(i);
                        ArrayList arrayList2 = (ArrayList) this.mActions.get(action);
                        if (arrayList2 != null) {
                            int size2 = arrayList2.size();
                            while (true) {
                                size2--;
                                if (size2 < 0) {
                                    break;
                                }
                                ReceiverRecord receiverRecord2 = (ReceiverRecord) arrayList2.get(size2);
                                if (receiverRecord2.receiver == broadcastReceiver) {
                                    receiverRecord2.dead = true;
                                    arrayList2.remove(size2);
                                }
                            }
                            if (arrayList2.size() <= 0) {
                                this.mActions.remove(action);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }
}
