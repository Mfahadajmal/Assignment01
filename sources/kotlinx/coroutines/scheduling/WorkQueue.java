package kotlinx.coroutines.scheduling;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Region;
import android.os.SystemClock;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.util.Base64;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.work.impl.constraints.trackers.BatteryNotLowTracker;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.brailleime.analytics.BrailleImeAnalyticsHelper$SessionCache;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$BrailleImeExtension;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$BrailleType;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationRecord;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationType;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$LayoutCode;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.imagecaption.IconDetectionRequest;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.storage.file.MonitorOutputStream;
import com.google.android.libraries.storage.file.spi.Monitor;
import com.google.android.libraries.storage.file.spi.Transform;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.play.core.splitcompat.FileStorage;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import com.google.android.play.core.splitinstall.DownloadedStateInterceptor$Callback;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.auth.Credentials;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ExecutionList;
import com.google.mlkit.logging.schema.DurationStats;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import io.grpc.util.MultiChildLoadBalancer;
import j$.time.Duration;
import j$.time.Instant;
import j$.util.DesugarCollections;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.inject.Provider;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.DebugKt;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkQueue {
    public final Object WorkQueue$ar$blockingTasksInBuffer;
    public final Object WorkQueue$ar$buffer;
    public final Object WorkQueue$ar$consumerIndex;
    public final Object WorkQueue$ar$lastScheduledTask;
    public final Object WorkQueue$ar$producerIndex;

    public WorkQueue(Context context, WorkManagerTaskExecutor workManagerTaskExecutor, ConstraintTracker constraintTracker, BatteryNotLowTracker batteryNotLowTracker, ConstraintTracker constraintTracker2, ConstraintTracker constraintTracker3) {
        this.WorkQueue$ar$producerIndex = context;
        this.WorkQueue$ar$buffer = constraintTracker;
        this.WorkQueue$ar$blockingTasksInBuffer = batteryNotLowTracker;
        this.WorkQueue$ar$consumerIndex = constraintTracker2;
        this.WorkQueue$ar$lastScheduledTask = constraintTracker3;
    }

    public static final AppLifecycleMonitor createFocusFinder$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return new AppLifecycleMonitor(TalkBackService.instance);
    }

    public static final AccessibilityNodeInfoCompat getAccessibilityFocusNode$ar$ds(boolean z) {
        return AppLifecycleMonitor.getAccessibilityFocusNode(TalkBackService.instance, z);
    }

    /* JADX WARN: Type inference failed for: r9v8, types: [java.util.List, java.lang.Object] */
    public final void addCalibration$ar$edu$ar$edu(int i, boolean z, int i2, boolean z2) {
        int i3;
        BrailleImeLogProto$LayoutCode brailleImeLogProto$LayoutCode;
        int i4;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BrailleImeLogProto$CalibrationRecord.DEFAULT_INSTANCE.createBuilder();
        int i5 = i - 1;
        if (i5 != 1) {
            if (i5 != 2) {
                if (i5 != 3) {
                    if (i5 != 4) {
                        if (i5 != 5) {
                            i3 = BrailleImeLogProto$CalibrationType.CALIBRATION_TYPE_INVALID$ar$edu;
                        } else {
                            i3 = BrailleImeLogProto$CalibrationType.CALIBRATION_TYPE_MANUAL$ar$edu;
                        }
                    } else {
                        i3 = BrailleImeLogProto$CalibrationType.CALIBRATION_TYPE_EIGHT_FINGER$ar$edu;
                    }
                } else {
                    i3 = BrailleImeLogProto$CalibrationType.CALIBRATION_TYPE_SEVEN_FINGER$ar$edu;
                }
            } else {
                i3 = BrailleImeLogProto$CalibrationType.CALIBRATION_TYPE_SIX_FINGER$ar$edu;
            }
        } else {
            i3 = BrailleImeLogProto$CalibrationType.CALIBRATION_TYPE_FIVE_FINGER$ar$edu;
        }
        builder.copyOnWrite();
        BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord = (BrailleImeLogProto$CalibrationRecord) builder.instance;
        int i6 = i3 - 1;
        if (i3 != 0) {
            brailleImeLogProto$CalibrationRecord.calibrationType_ = i6;
            brailleImeLogProto$CalibrationRecord.bitField0_ |= 4;
            if (z) {
                brailleImeLogProto$LayoutCode = BrailleImeLogProto$LayoutCode.TABLETOP;
            } else {
                brailleImeLogProto$LayoutCode = BrailleImeLogProto$LayoutCode.SCREEN_AWAY;
            }
            builder.copyOnWrite();
            BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord2 = (BrailleImeLogProto$CalibrationRecord) builder.instance;
            brailleImeLogProto$CalibrationRecord2.layout_ = brailleImeLogProto$LayoutCode.value;
            brailleImeLogProto$CalibrationRecord2.bitField0_ |= 1;
            builder.copyOnWrite();
            BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord3 = (BrailleImeLogProto$CalibrationRecord) builder.instance;
            int i7 = i2 - 1;
            if (i2 != 0) {
                brailleImeLogProto$CalibrationRecord3.calibrationState_ = i7;
                brailleImeLogProto$CalibrationRecord3.bitField0_ |= 8;
                if (z2) {
                    i4 = BrailleImeLogProto$BrailleType.BRAILLE_TYPE_EIGHT_DOT$ar$edu;
                } else {
                    i4 = BrailleImeLogProto$BrailleType.BRAILLE_TYPE_SIX_DOT$ar$edu;
                }
                Object obj = this.WorkQueue$ar$blockingTasksInBuffer;
                builder.copyOnWrite();
                BrailleImeLogProto$CalibrationRecord brailleImeLogProto$CalibrationRecord4 = (BrailleImeLogProto$CalibrationRecord) builder.instance;
                int i8 = i4 - 1;
                if (i4 != 0) {
                    brailleImeLogProto$CalibrationRecord4.brailleType_ = i8;
                    brailleImeLogProto$CalibrationRecord4.bitField0_ |= 2;
                    ((ApplicationModule) obj).ApplicationModule$ar$application.add((BrailleImeLogProto$CalibrationRecord) builder.build());
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [android.content.SharedPreferences, java.lang.Object] */
    public final void addExtensionToSharedPreference(BrailleImeLogProto$BrailleImeExtension brailleImeLogProto$BrailleImeExtension) {
        String encodeToString = Base64.encodeToString(brailleImeLogProto$BrailleImeExtension.toByteArray(), 0);
        ?? r0 = this.WorkQueue$ar$producerIndex;
        SharedPreferences.Editor edit = r0.edit();
        edit.putString(String.valueOf(r0.getAll().size() + 1), encodeToString);
        edit.apply();
    }

    public final Task addLast(Task task) {
        if (getBufferSize() == 127) {
            return task;
        }
        if (task.taskContext$ar$class_merging.getTaskMode() == 1) {
            ((AtomicInt) this.WorkQueue$ar$blockingTasksInBuffer).incrementAndGet();
        }
        int i = ((AtomicInt) this.WorkQueue$ar$producerIndex).value & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE;
        while (((AtomicReferenceArray) this.WorkQueue$ar$buffer).get(i) != null) {
            Thread.yield();
        }
        ((AtomicReferenceArray) this.WorkQueue$ar$buffer).lazySet(i, task);
        ((AtomicInt) this.WorkQueue$ar$producerIndex).incrementAndGet();
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List, java.lang.Object] */
    public final List chainTransformsForWrite(OutputStream outputStream) {
        MonitorOutputStream monitorOutputStream;
        ArrayList arrayList = new ArrayList();
        arrayList.add(outputStream);
        if (!this.WorkQueue$ar$lastScheduledTask.isEmpty()) {
            ?? r1 = this.WorkQueue$ar$lastScheduledTask;
            ArrayList arrayList2 = new ArrayList();
            Iterator it = r1.iterator();
            while (it.hasNext()) {
                Monitor.OutputMonitor monitorWrite$ar$ds = ((Monitor) it.next()).monitorWrite$ar$ds();
                if (monitorWrite$ar$ds != null) {
                    arrayList2.add(monitorWrite$ar$ds);
                }
            }
            if (!arrayList2.isEmpty()) {
                monitorOutputStream = new MonitorOutputStream(outputStream, arrayList2);
            } else {
                monitorOutputStream = null;
            }
            if (monitorOutputStream != null) {
                arrayList.add(monitorOutputStream);
            }
        }
        for (Transform transform : this.WorkQueue$ar$blockingTasksInBuffer) {
            arrayList.add(transform.wrapForWrite$ar$ds());
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:13|14|(3:15|16|(6:417|418|419|420|(2:422|(5:424|425|(9:427|428|429|430|431|432|(2:433|(1:435)(1:436))|437|438)|(3:463|464|465)(1:467)|466))|468)(1:18))|19|(2:21|22)|(3:398|399|(1:401)(8:402|(3:404|(2:406|407)(1:409)|408)|25|(20:30|31|(11:33|34|36|37|39|40|41|42|(1:44)(2:301|(1:303)(1:304))|45|(9:47|48|49|50|51|(3:284|285|(3:287|288|289))|53|54|(3:56|57|(2:59|(2:61|(2:268|269)(5:66|67|68|69|(2:261|262)(2:73|(9:75|76|77|78|79|80|81|82|(2:84|(2:86|(2:88|(10:90|91|92|93|94|95|(3:98|(3:100|101|(3:220|221|222)(3:105|106|(3:108|109|(10:144|145|146|147|(3:149|150|(2:152|(6:188|189|190|167|129|130)(5:154|(4:184|185|186|187)(5:156|157|(4:160|(2:162|(1:164))|166|158)|168|169)|167|129|130))(1:191))(1:198)|192|190|167|129|130)(4:111|112|113|114))(5:208|209|210|211|212)))(3:224|225|226)|96)|227|228|229)(3:236|237|238))(3:239|240|241))(3:242|243|244))(3:245|246|247))(3:257|258|259))))(3:271|272|273))(3:274|275|276))(4:280|281|282|283))(3:297|298|299))|320|321|322|(1:324)(1:393)|325|326|327|328|(1:330)|331|332|(2:334|(3:(2:336|(2:338|(1:340)(1:341))(1:344))|342|343)(3:373|374|375))|376|377|(2:378|(1:380)(1:381))|382|133)|395|131|132|133))|24|25|(21:27|30|31|(0)|320|321|322|(0)(0)|325|326|327|328|(0)|331|332|(0)|376|377|(3:378|(0)(0)|380)|382|133)|395|131|132|133) */
    /* JADX WARN: Code restructure failed: missing block: B:122:?, code lost:
    
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:346:0x067b, code lost:
    
        if (((com.google.firebase.encoders.FieldDescriptor.Builder) r9).FieldDescriptor$Builder$ar$properties.getName().equals("manifest") == false) goto L1045;
     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x067d, code lost:
    
        r12 = r34;
        r10 = ((com.google.firebase.encoders.FieldDescriptor.Builder) r9).FieldDescriptor$Builder$ar$properties.getAttributeValue(r12, "versionCode");
        r9 = ((com.google.firebase.encoders.FieldDescriptor.Builder) r9).FieldDescriptor$Builder$ar$properties.getAttributeValue(r12, "versionCodeMajor");
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x0694, code lost:
    
        if (r10 == null) goto L1043;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0696, code lost:
    
        r10 = java.lang.Integer.parseInt(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x069a, code lost:
    
        if (r9 != null) goto L966;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x069c, code lost:
    
        r9 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x06b1, code lost:
    
        if (r4 != r9) goto L1042;
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x06b3, code lost:
    
        r8 = r8 - 1;
        r34 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x06a2, code lost:
    
        r9 = (r10 & 4294967295L) | (java.lang.Integer.parseInt(r9) << 32);
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x06b8, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x06cf, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException(java.lang.String.format("Couldn't parse versionCodeMajor to int: %s", r0.getMessage()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x06d0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x06e7, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException(java.lang.String.format("Couldn't parse versionCode to int: %s", r0.getMessage()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x06ef, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("Manifest entry doesn't contain 'versionCode' attribute.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x0764, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x0765, code lost:
    
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:469:0x0088, code lost:
    
        if (r12.exists() == false) goto L551;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x07ba A[Catch: Exception -> 0x07b0, TRY_LEAVE, TryCatch #31 {Exception -> 0x07b0, blocks: (B:10:0x07ba, B:182:0x07af, B:181:0x07ac, B:176:0x07a6), top: B:2:0x000a, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x07a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:? A[Catch: Exception -> 0x07b0, SYNTHETIC, TryCatch #31 {Exception -> 0x07b0, blocks: (B:10:0x07ba, B:182:0x07af, B:181:0x07ac, B:176:0x07a6), top: B:2:0x000a, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0614 A[Catch: Exception -> 0x0764, all -> 0x07a2, TryCatch #1 {all -> 0x07a2, blocks: (B:131:0x075e, B:133:0x079a, B:143:0x076e, B:322:0x0605, B:324:0x0614, B:328:0x061f, B:330:0x0629, B:331:0x062d, B:332:0x0632, B:334:0x063b, B:336:0x065c, B:342:0x06f0, B:343:0x06f7, B:345:0x066c, B:347:0x067d, B:350:0x0696, B:358:0x069e, B:363:0x06ba, B:364:0x06cf, B:370:0x06e8, B:371:0x06ef, B:367:0x06d1, B:368:0x06e7, B:374:0x06f8, B:375:0x06ff, B:377:0x0700, B:378:0x0710, B:380:0x0714, B:386:0x0734, B:391:0x073d, B:392:0x0752, B:393:0x0619, B:395:0x0753, B:485:0x0793, B:416:0x0777, B:19:0x00f2), top: B:6:0x0036, inners: #2, #35 }] */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0629 A[Catch: Exception -> 0x073b, all -> 0x07a2, TryCatch #1 {all -> 0x07a2, blocks: (B:131:0x075e, B:133:0x079a, B:143:0x076e, B:322:0x0605, B:324:0x0614, B:328:0x061f, B:330:0x0629, B:331:0x062d, B:332:0x0632, B:334:0x063b, B:336:0x065c, B:342:0x06f0, B:343:0x06f7, B:345:0x066c, B:347:0x067d, B:350:0x0696, B:358:0x069e, B:363:0x06ba, B:364:0x06cf, B:370:0x06e8, B:371:0x06ef, B:367:0x06d1, B:368:0x06e7, B:374:0x06f8, B:375:0x06ff, B:377:0x0700, B:378:0x0710, B:380:0x0714, B:386:0x0734, B:391:0x073d, B:392:0x0752, B:393:0x0619, B:395:0x0753, B:485:0x0793, B:416:0x0777, B:19:0x00f2), top: B:6:0x0036, inners: #2, #35 }] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x063b A[Catch: Exception -> 0x0764, all -> 0x07a2, TryCatch #1 {all -> 0x07a2, blocks: (B:131:0x075e, B:133:0x079a, B:143:0x076e, B:322:0x0605, B:324:0x0614, B:328:0x061f, B:330:0x0629, B:331:0x062d, B:332:0x0632, B:334:0x063b, B:336:0x065c, B:342:0x06f0, B:343:0x06f7, B:345:0x066c, B:347:0x067d, B:350:0x0696, B:358:0x069e, B:363:0x06ba, B:364:0x06cf, B:370:0x06e8, B:371:0x06ef, B:367:0x06d1, B:368:0x06e7, B:374:0x06f8, B:375:0x06ff, B:377:0x0700, B:378:0x0710, B:380:0x0714, B:386:0x0734, B:391:0x073d, B:392:0x0752, B:393:0x0619, B:395:0x0753, B:485:0x0793, B:416:0x0777, B:19:0x00f2), top: B:6:0x0036, inners: #2, #35 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0140 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0714 A[Catch: IOException -> 0x0732, all -> 0x07a2, LOOP:7: B:378:0x0710->B:380:0x0714, LOOP_END, TRY_LEAVE, TryCatch #35 {IOException -> 0x0732, blocks: (B:377:0x0700, B:378:0x0710, B:380:0x0714), top: B:376:0x0700, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:381:0x072f A[EDGE_INSN: B:381:0x072f->B:382:0x072f BREAK  A[LOOP:7: B:378:0x0710->B:380:0x0714], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0619 A[Catch: Exception -> 0x0764, all -> 0x07a2, TryCatch #1 {all -> 0x07a2, blocks: (B:131:0x075e, B:133:0x079a, B:143:0x076e, B:322:0x0605, B:324:0x0614, B:328:0x061f, B:330:0x0629, B:331:0x062d, B:332:0x0632, B:334:0x063b, B:336:0x065c, B:342:0x06f0, B:343:0x06f7, B:345:0x066c, B:347:0x067d, B:350:0x0696, B:358:0x069e, B:363:0x06ba, B:364:0x06cf, B:370:0x06e8, B:371:0x06ef, B:367:0x06d1, B:368:0x06e7, B:374:0x06f8, B:375:0x06ff, B:377:0x0700, B:378:0x0710, B:380:0x0714, B:386:0x0734, B:391:0x073d, B:392:0x0752, B:393:0x0619, B:395:0x0753, B:485:0x0793, B:416:0x0777, B:19:0x00f2), top: B:6:0x0036, inners: #2, #35 }] */
    /* JADX WARN: Type inference failed for: r10v13, types: [java.lang.Object, org.xmlpull.v1.XmlPullParser] */
    /* JADX WARN: Type inference failed for: r10v17, types: [java.lang.Object, org.xmlpull.v1.XmlPullParser] */
    /* JADX WARN: Type inference failed for: r10v22, types: [java.lang.Object, org.xmlpull.v1.XmlPullParser] */
    /* JADX WARN: Type inference failed for: r10v31, types: [java.lang.Long] */
    /* JADX WARN: Type inference failed for: r10v32 */
    /* JADX WARN: Type inference failed for: r10v33 */
    /* JADX WARN: Type inference failed for: r10v37 */
    /* JADX WARN: Type inference failed for: r10v40 */
    /* JADX WARN: Type inference failed for: r10v41 */
    /* JADX WARN: Type inference failed for: r10v42 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v16, types: [java.nio.ByteBuffer] */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v24 */
    /* JADX WARN: Type inference failed for: r13v27 */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v29 */
    /* JADX WARN: Type inference failed for: r13v4, types: [int] */
    /* JADX WARN: Type inference failed for: r13v41 */
    /* JADX WARN: Type inference failed for: r13v43 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v20 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v28 */
    /* JADX WARN: Type inference failed for: r14v29 */
    /* JADX WARN: Type inference failed for: r14v30 */
    /* JADX WARN: Type inference failed for: r14v42 */
    /* JADX WARN: Type inference failed for: r14v44 */
    /* JADX WARN: Type inference failed for: r14v6, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r14v7, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v0, types: [kotlinx.coroutines.scheduling.WorkQueue] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v103 */
    /* JADX WARN: Type inference failed for: r1v19, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v82 */
    /* JADX WARN: Type inference failed for: r1v85 */
    /* JADX WARN: Type inference failed for: r1v89 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r1v92 */
    /* JADX WARN: Type inference failed for: r1v93 */
    /* JADX WARN: Type inference failed for: r1v97 */
    /* JADX WARN: Type inference failed for: r1v98 */
    /* JADX WARN: Type inference failed for: r4v28, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v41, types: [int] */
    /* JADX WARN: Type inference failed for: r4v45 */
    /* JADX WARN: Type inference failed for: r4v46 */
    /* JADX WARN: Type inference failed for: r4v47 */
    /* JADX WARN: Type inference failed for: r4v48 */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.lang.Object, org.xmlpull.v1.XmlPullParser] */
    /* JADX WARN: Type inference failed for: r9v21, types: [long] */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Integer copyAndVerifySync(java.util.List r40) {
        /*
            Method dump skipped, instructions count: 1998
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.WorkQueue.copyAndVerifySync(java.util.List):java.lang.Integer");
    }

    public final boolean finishCalculateSessionTime() {
        if (((BrailleImeAnalyticsHelper$SessionCache) this.WorkQueue$ar$consumerIndex).isSessionStartValid()) {
            Instant ofEpochMilli = Instant.ofEpochMilli(SystemClock.elapsedRealtime());
            BrailleImeAnalyticsHelper$SessionCache brailleImeAnalyticsHelper$SessionCache = (BrailleImeAnalyticsHelper$SessionCache) this.WorkQueue$ar$consumerIndex;
            brailleImeAnalyticsHelper$SessionCache.totalSessionDuration = Duration.between(brailleImeAnalyticsHelper$SessionCache.sessionStart, ofEpochMilli);
            return true;
        }
        return false;
    }

    public final int getBufferSize() {
        return ((AtomicInt) this.WorkQueue$ar$producerIndex).value - ((AtomicInt) this.WorkQueue$ar$consumerIndex).value;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map, java.lang.Object] */
    public final Map getContextMenuOptionMap() {
        return DesugarCollections.unmodifiableMap(((ApplicationModule) this.WorkQueue$ar$buffer).ApplicationModule$ar$application);
    }

    public final Duration getSessionDuration() {
        return ((BrailleImeAnalyticsHelper$SessionCache) this.WorkQueue$ar$consumerIndex).totalSessionDuration;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map, java.lang.Object] */
    public final Map getSettingsEventMap() {
        return DesugarCollections.unmodifiableMap(((ApplicationModule) this.WorkQueue$ar$lastScheduledTask).ApplicationModule$ar$application);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.concurrent.Executor, java.lang.Object] */
    public final void intercept(List list, DownloadedStateInterceptor$Callback downloadedStateInterceptor$Callback) {
        if (SplitCompat.installed.get() != null) {
            this.WorkQueue$ar$blockingTasksInBuffer.execute(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(this, list, downloadedStateInterceptor$Callback, 20));
            return;
        }
        throw new IllegalStateException("Ingestion should only be called in SplitCompat mode.");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    public final boolean isProcessingScreenshot() {
        boolean z;
        synchronized (this) {
            z = !this.WorkQueue$ar$consumerIndex.isEmpty();
        }
        return z;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List, java.lang.Object] */
    public final void onNewResults(ImmutableList immutableList) {
        Region dirtyRegion;
        if (!((UiChangesTracker) this.WorkQueue$ar$lastScheduledTask).isWholeScreenChangeObserved() && (dirtyRegion = ((UiChangesTracker) this.WorkQueue$ar$lastScheduledTask).getDirtyRegion()) != null) {
            Object obj = this.WorkQueue$ar$buffer;
            MetricsLogger metricsLogger = (MetricsLogger) obj;
            ((UiChangesTracker) metricsLogger.MetricsLogger$ar$logSessionId).reset();
            Object obj2 = metricsLogger.MetricsLogger$ar$logSessionId;
            synchronized (obj2) {
                ((Region) ((UiChangesTracker) obj2).UiChangesTracker$ar$dirtyRegion).set(dirtyRegion);
            }
            synchronized (obj) {
                ((MetricsLogger) obj).MetricsLogger$ar$loggerProvider$ar$class_merging = immutableList;
                ((MetricsLogger) obj).updateLatestResultsState(false, dirtyRegion);
            }
        }
        ((UiChangesTracker) this.WorkQueue$ar$lastScheduledTask).reset();
        ArrayList arrayList = new ArrayList(1);
        synchronized (this) {
            arrayList.addAll(this.WorkQueue$ar$consumerIndex);
            this.WorkQueue$ar$consumerIndex.clear();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((IconDetectionRequest) arrayList.get(i)).onDetectionFinished$ar$ds();
        }
    }

    public final boolean performAction$ar$edu$3bc9316c_0(int i, Object... objArr) {
        return ((NodeMenuRuleCreator) this.WorkQueue$ar$lastScheduledTask).performAction$ar$edu(i, 4, objArr);
    }

    public final Task pollBuffer() {
        Task task;
        while (true) {
            Object obj = this.WorkQueue$ar$consumerIndex;
            Object obj2 = this.WorkQueue$ar$producerIndex;
            int i = ((AtomicInt) obj).value;
            if (i - ((AtomicInt) obj2).value == 0) {
                return null;
            }
            int i2 = i & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE;
            if (((AtomicInt) this.WorkQueue$ar$consumerIndex).compareAndSet(i, i + 1) && (task = (Task) ((AtomicReferenceArray) this.WorkQueue$ar$buffer).getAndSet(i2, null)) != null) {
                if (task.taskContext$ar$class_merging.getTaskMode() == 1) {
                    ((AtomicInt) this.WorkQueue$ar$blockingTasksInBuffer).decrementAndGet();
                    boolean z = DebugKt.ASSERTIONS_ENABLED;
                }
                return task;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List, java.lang.Object] */
    public final void resetCalibrationRecords() {
        ((ApplicationModule) this.WorkQueue$ar$blockingTasksInBuffer).ApplicationModule$ar$application.clear();
    }

    public final void resetContextMenuRecords() {
        ((HashMap) ((ApplicationModule) this.WorkQueue$ar$buffer).ApplicationModule$ar$application).clear();
    }

    public final void resetSessionEvents() {
        BrailleImeAnalyticsHelper$SessionCache brailleImeAnalyticsHelper$SessionCache = (BrailleImeAnalyticsHelper$SessionCache) this.WorkQueue$ar$consumerIndex;
        brailleImeAnalyticsHelper$SessionCache.sessionStart = null;
        brailleImeAnalyticsHelper$SessionCache.totalBrailleCharCount = 0;
        brailleImeAnalyticsHelper$SessionCache.totalSessionDuration = Duration.ZERO;
        brailleImeAnalyticsHelper$SessionCache.gestureActMap.clear();
    }

    public final void resetSettingsEvents() {
        ((HashMap) ((ApplicationModule) this.WorkQueue$ar$lastScheduledTask).ApplicationModule$ar$application).clear();
    }

    public final void startCalculateSessionTime$ar$ds() {
        if (!((BrailleImeAnalyticsHelper$SessionCache) this.WorkQueue$ar$consumerIndex).isSessionStartValid()) {
            ((BrailleImeAnalyticsHelper$SessionCache) this.WorkQueue$ar$consumerIndex).sessionStart = Instant.ofEpochMilli(SystemClock.elapsedRealtime());
        }
    }

    public final boolean switchInputMethodToBrailleKeyboard() {
        boolean switchToInputMethod;
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            Object obj = this.WorkQueue$ar$buffer;
            switchToInputMethod = ((TalkBackService) obj).getSoftKeyboardController().switchToInputMethod(SpannableUtils$IdentifierSpan.getEnabledImeId(((TalkBackService) obj).getApplicationContext(), ((TalkBackService) obj).getPackageName()));
            return switchToInputMethod;
        }
        return false;
    }

    public final Task tryExtractFromTheMiddle(int i, boolean z) {
        AtomicReferenceArray atomicReferenceArray;
        AtomicReferenceArray atomicReferenceArray2 = (AtomicReferenceArray) this.WorkQueue$ar$buffer;
        int i2 = i & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE;
        Task task = (Task) atomicReferenceArray2.get(i2);
        if (task != null && task.taskContext$ar$class_merging.getTaskMode() == z) {
            Object obj = this.WorkQueue$ar$buffer;
            do {
                atomicReferenceArray = (AtomicReferenceArray) obj;
                if (atomicReferenceArray.compareAndSet(i2, task, null)) {
                    if (z) {
                        ((AtomicInt) this.WorkQueue$ar$blockingTasksInBuffer).decrementAndGet();
                    }
                    return task;
                }
            } while (atomicReferenceArray.get(i2) == task);
        }
        return null;
    }

    public WorkQueue(Context context, Executor executor, Verifier verifier, FileStorage fileStorage, NativeLibraryPathListMutex nativeLibraryPathListMutex) {
        this.WorkQueue$ar$buffer = context;
        this.WorkQueue$ar$consumerIndex = fileStorage;
        this.WorkQueue$ar$producerIndex = verifier;
        this.WorkQueue$ar$blockingTasksInBuffer = executor;
        this.WorkQueue$ar$lastScheduledTask = nativeLibraryPathListMutex;
    }

    public WorkQueue(DurationStats durationStats) {
        this.WorkQueue$ar$buffer = durationStats.DurationStats$ar$maxMs;
        this.WorkQueue$ar$blockingTasksInBuffer = durationStats.DurationStats$ar$thirdQuartileMs;
        this.WorkQueue$ar$lastScheduledTask = durationStats.DurationStats$ar$minMs;
        this.WorkQueue$ar$producerIndex = durationStats.DurationStats$ar$firstQuartileMs;
        this.WorkQueue$ar$consumerIndex = durationStats.DurationStats$ar$avgMs;
    }

    public WorkQueue(Context context, char[] cArr) {
        this.WorkQueue$ar$producerIndex = SpannableUtils$IdentifierSpan.getSharedPreferences(context, "braille_analytics_log_cache");
        this.WorkQueue$ar$consumerIndex = new BrailleImeAnalyticsHelper$SessionCache();
        this.WorkQueue$ar$lastScheduledTask = new ApplicationModule((byte[]) null, (byte[]) null);
        this.WorkQueue$ar$buffer = new ApplicationModule((char[]) null, (byte[]) null);
        this.WorkQueue$ar$blockingTasksInBuffer = new ApplicationModule(null, null, null);
    }

    public WorkQueue(Class cls, ClassLoader classLoader) {
        Class asSubclass = cls.asSubclass(Credentials.class);
        this.WorkQueue$ar$buffer = asSubclass;
        this.WorkQueue$ar$consumerIndex = asSubclass.getMethod("getScopes", null);
        Method declaredMethod = Class.forName("com.google.auth.oauth2.ServiceAccountJwtAccessCredentials", false, classLoader).asSubclass(Credentials.class).getDeclaredMethod("newBuilder", null);
        this.WorkQueue$ar$producerIndex = declaredMethod;
        Class<?> returnType = declaredMethod.getReturnType();
        this.WorkQueue$ar$blockingTasksInBuffer = returnType.getMethod("build", null);
        ArrayList arrayList = new ArrayList();
        this.WorkQueue$ar$lastScheduledTask = arrayList;
        Method method = asSubclass.getMethod("getClientId", null);
        arrayList.add(new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(method, returnType.getMethod("setClientId", method.getReturnType())));
        Method method2 = asSubclass.getMethod("getClientEmail", null);
        arrayList.add(new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(method2, returnType.getMethod("setClientEmail", method2.getReturnType())));
        Method method3 = asSubclass.getMethod("getPrivateKey", null);
        arrayList.add(new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(method3, returnType.getMethod("setPrivateKey", method3.getReturnType())));
        Method method4 = asSubclass.getMethod("getPrivateKeyId", null);
        arrayList.add(new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(method4, returnType.getMethod("setPrivateKeyId", method4.getReturnType())));
        Method method5 = asSubclass.getMethod("getQuotaProjectId", null);
        arrayList.add(new MultiChildLoadBalancer.AcceptResolvedAddrRetVal(method5, returnType.getMethod("setQuotaProjectId", method5.getReturnType())));
    }

    public WorkQueue(TalkBackService talkBackService, Pipeline.FeedbackReturner feedbackReturner, NodeMenuRuleCreator nodeMenuRuleCreator) {
        this.WorkQueue$ar$buffer = talkBackService;
        this.WorkQueue$ar$blockingTasksInBuffer = feedbackReturner;
        this.WorkQueue$ar$consumerIndex = talkBackService.getSpeechController();
        this.WorkQueue$ar$producerIndex = talkBackService.getLabelManager();
        this.WorkQueue$ar$lastScheduledTask = nodeMenuRuleCreator;
    }

    public WorkQueue(Context context, byte[] bArr) {
        this(context);
    }

    protected WorkQueue(Context context) {
        this.WorkQueue$ar$consumerIndex = new ArrayList();
        this.WorkQueue$ar$lastScheduledTask = new UiChangesTracker();
        this.WorkQueue$ar$buffer = new MetricsLogger((char[]) null);
        this.WorkQueue$ar$blockingTasksInBuffer = context;
        this.WorkQueue$ar$producerIndex = new ExecutionList.RunnableExecutorPair(context, (byte[]) null, (byte[]) null);
    }

    public WorkQueue(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        provider.getClass();
        this.WorkQueue$ar$consumerIndex = provider;
        provider2.getClass();
        this.WorkQueue$ar$lastScheduledTask = provider2;
        provider3.getClass();
        this.WorkQueue$ar$blockingTasksInBuffer = provider3;
        this.WorkQueue$ar$buffer = provider4;
        provider5.getClass();
        this.WorkQueue$ar$producerIndex = provider5;
    }

    public WorkQueue() {
        this.WorkQueue$ar$buffer = new AtomicReferenceArray(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        this.WorkQueue$ar$lastScheduledTask = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
        this.WorkQueue$ar$producerIndex = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
        this.WorkQueue$ar$consumerIndex = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
        this.WorkQueue$ar$blockingTasksInBuffer = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
    }
}
