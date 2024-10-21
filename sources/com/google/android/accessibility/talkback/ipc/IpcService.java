package com.google.android.accessibility.talkback.ipc;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.google.android.accessibility.talkback.TalkBackExitController;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.CollectCollectors$$ExternalSyntheticLambda51;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import j$.util.Map;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class IpcService extends Service {
    public static TalkBackService.IpcClientCallbackImpl sClientCallback$ar$class_merging;
    private final Messenger messenger = new Messenger(new Handler(Looper.getMainLooper()) { // from class: com.google.android.accessibility.talkback.ipc.IpcService.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            boolean z;
            int i = 1;
            LogUtils.v("IpcService", "handleMessage(): %s", Integer.valueOf(message.what));
            switch (message.what) {
                case 0:
                    PageConfig.PageId pageId = (PageConfig.PageId) message.getData().getSerializable("training_page_id");
                    if (pageId != null) {
                        TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl = IpcService.sClientCallback$ar$class_merging;
                        if (ipcClientCallbackImpl != null) {
                            TalkBackService talkBackService = ipcClientCallbackImpl.talkBackService;
                            MetricRecorder metricRecorder = talkBackService.gestureController$ar$class_merging;
                            if (metricRecorder != null) {
                                PageConfig page = PageConfig.getPage(pageId, talkBackService, -1);
                                if (page == null) {
                                    ImmutableMap immutableMap = RegularImmutableMap.EMPTY;
                                    metricRecorder.setCaptureGestureIdToAnnouncements(immutableMap, immutableMap);
                                } else {
                                    metricRecorder.setCaptureGestureIdToAnnouncements(page.getCaptureGestureIdToAnnouncements, page.getCaptureFingerprintGestureIdToAnnouncements);
                                }
                            }
                            if (pageId != PageConfig.PageId.PAGE_ID_FINISHED) {
                                PageConfig.PageId pageId2 = ipcClientCallbackImpl.currentPageId;
                                if (pageId2 != PageConfig.PageId.PAGE_ID_UNKNOWN && pageId != pageId2) {
                                    ipcClientCallbackImpl.hasTrainingPageSwitched = true;
                                }
                                ipcClientCallbackImpl.currentPageId = pageId;
                            }
                            if (pageId != PageConfig.PageId.PAGE_ID_WELCOME_TO_TALKBACK && pageId != PageConfig.PageId.PAGE_ID_UPDATE_WELCOME) {
                                TalkBackService talkBackService2 = ipcClientCallbackImpl.talkBackService;
                                TalkBackExitController talkBackExitController = talkBackService2.talkBackExitController;
                                if (talkBackExitController != null) {
                                    talkBackService2.postRemoveEventListener(talkBackExitController);
                                    return;
                                }
                                return;
                            }
                            TalkBackService talkBackService3 = ipcClientCallbackImpl.talkBackService;
                            TalkBackExitController talkBackExitController2 = talkBackService3.talkBackExitController;
                            if (talkBackExitController2 != null) {
                                talkBackService3.addEventListener(talkBackExitController2);
                                return;
                            }
                            return;
                        }
                        LogUtils.w("IpcService", "clientCallback is null.", new Object[0]);
                        return;
                    }
                    return;
                case 1:
                    Messenger messenger = message.replyTo;
                    if (messenger != null && IpcService.sClientCallback$ar$class_merging != null) {
                        Context applicationContext = IpcService.this.getApplicationContext();
                        GestureShortcutMapping gestureShortcutMapping = new GestureShortcutMapping(applicationContext);
                        HashMap hashMap = new HashMap();
                        Map.EL.forEach((HashMap) gestureShortcutMapping.actionToGesture.get(gestureShortcutMapping.currentGestureSet), new CollectCollectors$$ExternalSyntheticLambda51(gestureShortcutMapping, hashMap, i));
                        Bundle bundle = new Bundle();
                        Map.EL.forEach(hashMap, new TalkBackService$IpcClientCallbackImpl$$ExternalSyntheticLambda0(bundle, 0));
                        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(applicationContext);
                        String[] stringArray = applicationContext.getResources().getStringArray(R.array.pref_shortcut_keys);
                        int length = stringArray.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 < length) {
                                if (sharedPreferences.contains(stringArray[i2])) {
                                    z = true;
                                } else {
                                    i2++;
                                }
                            } else {
                                z = false;
                            }
                        }
                        bundle.putBoolean("is_any_gesture_changed", z);
                        Message obtain = Message.obtain((Handler) null, 1);
                        obtain.setData(bundle);
                        try {
                            messenger.send(obtain);
                            return;
                        } catch (RemoteException unused) {
                            LogUtils.w("IpcService", "Fail to send gestures to client.", new Object[0]);
                            return;
                        }
                    }
                    LogUtils.e("IpcService", "No client messenger or clientCallback.", new Object[0]);
                    return;
                case 2:
                    TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl2 = IpcService.sClientCallback$ar$class_merging;
                    if (ipcClientCallbackImpl2 == null) {
                        LogUtils.w("IpcService", "clientCallback is null.", new Object[0]);
                        return;
                    } else {
                        ipcClientCallbackImpl2.onTrainingFinish();
                        return;
                    }
                case 3:
                    TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl3 = IpcService.sClientCallback$ar$class_merging;
                    if (ipcClientCallbackImpl3 == null) {
                        LogUtils.w("IpcService", "clientCallback is null and we tell the client that the server is destroyed.", new Object[0]);
                        IpcService.sendServerDestroyMsg(message.replyTo);
                        return;
                    } else {
                        ipcClientCallbackImpl3.serverOnDestroyListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new AppLifecycleMonitor(message.replyTo);
                        ipcClientCallbackImpl3.clientDisconnectedTimeStamp = 0L;
                        return;
                    }
                case 4:
                    TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl4 = IpcService.sClientCallback$ar$class_merging;
                    if (ipcClientCallbackImpl4 == null) {
                        LogUtils.w("IpcService", "clientCallback is null.", new Object[0]);
                        return;
                    } else {
                        ipcClientCallbackImpl4.clearServerOnDestroyListener();
                        ipcClientCallbackImpl4.clientDisconnectedTimeStamp = System.currentTimeMillis();
                        return;
                    }
                case 5:
                default:
                    return;
                case 6:
                    TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl5 = IpcService.sClientCallback$ar$class_merging;
                    if (ipcClientCallbackImpl5 == null) {
                        LogUtils.w("IpcService", "clientCallback is null.", new Object[0]);
                        return;
                    } else {
                        ipcClientCallbackImpl5.talkBackService.requestDisableTalkBack(1);
                        return;
                    }
                case 7:
                    Messenger messenger2 = message.replyTo;
                    if (messenger2 != null && IpcService.sClientCallback$ar$class_merging != null) {
                        Message obtain2 = Message.obtain((Handler) null, 7);
                        IpcService ipcService = IpcService.this;
                        TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl6 = IpcService.sClientCallback$ar$class_merging;
                        ipcService.getApplicationContext();
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("is_icon_detection_unavailable", ipcClientCallbackImpl6.talkBackService.getImageCaptioner().needDownloadDialog$ar$edu$8a2eb55_0(ImageCaptionUtils$CaptionType.ICON_LABEL, 3));
                        bundle2.putBoolean("is_image_description_unavailable", ipcClientCallbackImpl6.talkBackService.getImageCaptioner().needDownloadDialog$ar$edu$8a2eb55_0(ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION, 3));
                        obtain2.setData(bundle2);
                        try {
                            messenger2.send(obtain2);
                            return;
                        } catch (RemoteException unused2) {
                            LogUtils.w("IpcService", "Fail to send gestures to client.", new Object[0]);
                            return;
                        }
                    }
                    LogUtils.e("IpcService", "No client messenger or clientCallback.", new Object[0]);
                    return;
                case 8:
                    TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl7 = IpcService.sClientCallback$ar$class_merging;
                    if (ipcClientCallbackImpl7 == null) {
                        LogUtils.e("IpcService", "No client clientCallback.", new Object[0]);
                        return;
                    } else {
                        ipcClientCallbackImpl7.onRequestDownloadLibrary(ImageCaptionUtils$CaptionType.ICON_LABEL);
                        return;
                    }
                case 9:
                    TalkBackService.IpcClientCallbackImpl ipcClientCallbackImpl8 = IpcService.sClientCallback$ar$class_merging;
                    if (ipcClientCallbackImpl8 == null) {
                        LogUtils.e("IpcService", "No client clientCallback.", new Object[0]);
                        return;
                    } else {
                        ipcClientCallbackImpl8.onRequestDownloadLibrary(ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION);
                        return;
                    }
            }
        }
    });

    public static void sendServerDestroyMsg(Messenger messenger) {
        if (messenger != null) {
            try {
                messenger.send(Message.obtain((Handler) null, 5));
            } catch (RemoteException unused) {
                LogUtils.w("IpcService", "Fail to send server destroy state to client.", new Object[0]);
            }
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.messenger.getBinder();
    }
}
