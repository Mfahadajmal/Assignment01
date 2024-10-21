package com.google.android.gms.tasks;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.Configuration;
import com.google.android.gms.phenotype.Configurations;
import com.google.android.libraries.phenotype.client.api.Flag;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import java.util.concurrent.Executor;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnCanceledCompletionListener implements TaskCompletionListener {
    public final Object OnCanceledCompletionListener$ar$onCanceled$ar$class_merging;
    private final Executor executor;
    public final Object lock;
    private final /* synthetic */ int switching_field;

    public OnCanceledCompletionListener(Executor executor, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, TaskImpl taskImpl, int i) {
        this.switching_field = i;
        this.executor = executor;
        this.lock = shadowDelegateImpl;
        this.OnCanceledCompletionListener$ar$onCanceled$ar$class_merging = taskImpl;
    }

    @Override // com.google.android.gms.tasks.TaskCompletionListener
    public final void onComplete(final Task task) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (task.isSuccessful()) {
                            synchronized (this.lock) {
                            }
                            this.executor.execute(new ListMenuManager$$ExternalSyntheticLambda3(this, task, 18, (byte[]) null));
                            return;
                        }
                        return;
                    }
                    if (!task.isSuccessful() && !((TaskImpl) task).canceled) {
                        synchronized (this.lock) {
                        }
                        this.executor.execute(new ListMenuManager$$ExternalSyntheticLambda3(this, task, 17, (byte[]) null));
                        return;
                    }
                    return;
                }
                synchronized (this.lock) {
                }
                this.executor.execute(new ListMenuManager$$ExternalSyntheticLambda3(this, task, 16, (byte[]) null));
                return;
            }
            this.executor.execute(new Runnable(this) { // from class: com.google.android.gms.tasks.ContinueWithCompletionListener$1
                final /* synthetic */ OnCanceledCompletionListener this$0$ar$class_merging$ae3b6252_0;

                {
                    this.this$0$ar$class_merging$ae3b6252_0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i2;
                    Flag flag;
                    if (!((TaskImpl) task).canceled) {
                        try {
                            Configurations configurations = (Configurations) task.getResult();
                            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) com.google.android.libraries.phenotype.client.api.Configurations.DEFAULT_INSTANCE.createBuilder();
                            String str = configurations.snapshotToken;
                            builder.copyOnWrite();
                            com.google.android.libraries.phenotype.client.api.Configurations configurations2 = (com.google.android.libraries.phenotype.client.api.Configurations) builder.instance;
                            str.getClass();
                            configurations2.bitField0_ |= 1;
                            configurations2.snapshotToken_ = str;
                            String str2 = configurations.serverToken;
                            builder.copyOnWrite();
                            com.google.android.libraries.phenotype.client.api.Configurations configurations3 = (com.google.android.libraries.phenotype.client.api.Configurations) builder.instance;
                            str2.getClass();
                            int i3 = 4;
                            configurations3.bitField0_ |= 4;
                            configurations3.serverToken_ = str2;
                            boolean z = configurations.isDelta;
                            builder.copyOnWrite();
                            com.google.android.libraries.phenotype.client.api.Configurations configurations4 = (com.google.android.libraries.phenotype.client.api.Configurations) builder.instance;
                            configurations4.bitField0_ |= 8;
                            configurations4.isDelta_ = z;
                            long j = configurations.configurationVersion;
                            builder.copyOnWrite();
                            com.google.android.libraries.phenotype.client.api.Configurations configurations5 = (com.google.android.libraries.phenotype.client.api.Configurations) builder.instance;
                            configurations5.bitField0_ |= 16;
                            configurations5.configurationVersion_ = j;
                            byte[] bArr = configurations.experimentToken;
                            int i4 = 2;
                            if (bArr != null) {
                                ByteString copyFrom = ByteString.copyFrom(bArr);
                                builder.copyOnWrite();
                                com.google.android.libraries.phenotype.client.api.Configurations configurations6 = (com.google.android.libraries.phenotype.client.api.Configurations) builder.instance;
                                configurations6.bitField0_ |= 2;
                                configurations6.experimentToken_ = copyFrom;
                            }
                            Configuration[] configurationArr = configurations.configurations;
                            int length = configurationArr.length;
                            int i5 = 0;
                            while (i5 < length) {
                                Configuration configuration = configurationArr[i5];
                                com.google.android.gms.phenotype.Flag[] flagArr = configuration.flags;
                                int length2 = flagArr.length;
                                int i6 = 0;
                                while (i6 < length2) {
                                    com.google.android.gms.phenotype.Flag flag2 = flagArr[i6];
                                    int i7 = flag2.flagValueType;
                                    if (i7 != 1) {
                                        if (i7 != i4) {
                                            if (i7 != 3) {
                                                if (i7 != i3) {
                                                    if (i7 == 5) {
                                                        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) Flag.DEFAULT_INSTANCE.createBuilder();
                                                        String str3 = flag2.name;
                                                        builder2.copyOnWrite();
                                                        Flag flag3 = (Flag) builder2.instance;
                                                        str3.getClass();
                                                        flag3.bitField0_ |= 1;
                                                        flag3.name_ = str3;
                                                        if (flag2.flagValueType == 5) {
                                                            byte[] bArr2 = flag2.bytesValue;
                                                            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(bArr2);
                                                            ByteString copyFrom2 = ByteString.copyFrom(bArr2);
                                                            builder2.copyOnWrite();
                                                            Flag flag4 = (Flag) builder2.instance;
                                                            flag4.valueCase_ = 5;
                                                            flag4.value_ = copyFrom2;
                                                            flag = (Flag) builder2.build();
                                                        } else {
                                                            throw new IllegalArgumentException("Not a bytes type");
                                                        }
                                                    } else {
                                                        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i7, "Unrecognized flag type: "));
                                                    }
                                                } else {
                                                    SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) Flag.DEFAULT_INSTANCE.createBuilder();
                                                    String str4 = flag2.name;
                                                    builder3.copyOnWrite();
                                                    Flag flag5 = (Flag) builder3.instance;
                                                    str4.getClass();
                                                    flag5.bitField0_ |= 1;
                                                    flag5.name_ = str4;
                                                    if (flag2.flagValueType == 4) {
                                                        String str5 = flag2.stringValue;
                                                        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str5);
                                                        builder3.copyOnWrite();
                                                        Flag flag6 = (Flag) builder3.instance;
                                                        flag6.valueCase_ = 4;
                                                        flag6.value_ = str5;
                                                        flag = (Flag) builder3.build();
                                                    } else {
                                                        throw new IllegalArgumentException("Not a String type");
                                                    }
                                                }
                                            } else {
                                                SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) Flag.DEFAULT_INSTANCE.createBuilder();
                                                String str6 = flag2.name;
                                                builder4.copyOnWrite();
                                                Flag flag7 = (Flag) builder4.instance;
                                                str6.getClass();
                                                flag7.bitField0_ |= 1;
                                                flag7.name_ = str6;
                                                if (flag2.flagValueType == 3) {
                                                    double d = flag2.doubleValue;
                                                    builder4.copyOnWrite();
                                                    Flag flag8 = (Flag) builder4.instance;
                                                    flag8.valueCase_ = 3;
                                                    flag8.value_ = Double.valueOf(d);
                                                    flag = (Flag) builder4.build();
                                                } else {
                                                    throw new IllegalArgumentException("Not a double type");
                                                }
                                            }
                                            i2 = 2;
                                        } else {
                                            SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) Flag.DEFAULT_INSTANCE.createBuilder();
                                            String str7 = flag2.name;
                                            builder5.copyOnWrite();
                                            Flag flag9 = (Flag) builder5.instance;
                                            str7.getClass();
                                            flag9.bitField0_ |= 1;
                                            flag9.name_ = str7;
                                            i2 = 2;
                                            if (flag2.flagValueType == 2) {
                                                boolean z2 = flag2.booleanValue;
                                                builder5.copyOnWrite();
                                                Flag flag10 = (Flag) builder5.instance;
                                                flag10.valueCase_ = 2;
                                                flag10.value_ = Boolean.valueOf(z2);
                                                flag = (Flag) builder5.build();
                                            } else {
                                                throw new IllegalArgumentException("Not a boolean type");
                                            }
                                        }
                                    } else {
                                        i2 = i4;
                                        SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) Flag.DEFAULT_INSTANCE.createBuilder();
                                        String str8 = flag2.name;
                                        builder6.copyOnWrite();
                                        Flag flag11 = (Flag) builder6.instance;
                                        str8.getClass();
                                        flag11.bitField0_ |= 1;
                                        flag11.name_ = str8;
                                        if (flag2.flagValueType == 1) {
                                            long j2 = flag2.longValue;
                                            builder6.copyOnWrite();
                                            Flag flag12 = (Flag) builder6.instance;
                                            flag12.valueCase_ = 1;
                                            flag12.value_ = Long.valueOf(j2);
                                            flag = (Flag) builder6.build();
                                        } else {
                                            throw new IllegalArgumentException("Not a long type");
                                        }
                                    }
                                    builder.copyOnWrite();
                                    com.google.android.libraries.phenotype.client.api.Configurations configurations7 = (com.google.android.libraries.phenotype.client.api.Configurations) builder.instance;
                                    flag.getClass();
                                    Internal.ProtobufList protobufList = configurations7.flag_;
                                    if (!protobufList.isModifiable()) {
                                        configurations7.flag_ = GeneratedMessageLite.mutableCopy(protobufList);
                                    }
                                    configurations7.flag_.add(flag);
                                    i6++;
                                    i4 = i2;
                                    i3 = 4;
                                }
                                int i8 = i4;
                                String[] strArr = configuration.deleteFlags;
                                if (strArr != null) {
                                    for (String str9 : strArr) {
                                        builder.copyOnWrite();
                                        com.google.android.libraries.phenotype.client.api.Configurations configurations8 = (com.google.android.libraries.phenotype.client.api.Configurations) builder.instance;
                                        str9.getClass();
                                        Internal.ProtobufList protobufList2 = configurations8.deleteFlag_;
                                        if (!protobufList2.isModifiable()) {
                                            configurations8.deleteFlag_ = GeneratedMessageLite.mutableCopy(protobufList2);
                                        }
                                        configurations8.deleteFlag_.add(str9);
                                    }
                                }
                                i5++;
                                i4 = i8;
                                i3 = 4;
                            }
                            ((TaskImpl) this.this$0$ar$class_merging$ae3b6252_0.OnCanceledCompletionListener$ar$onCanceled$ar$class_merging).setResult((com.google.android.libraries.phenotype.client.api.Configurations) builder.build());
                            return;
                        } catch (RuntimeExecutionException e) {
                            if (e.getCause() instanceof Exception) {
                                ((TaskImpl) this.this$0$ar$class_merging$ae3b6252_0.OnCanceledCompletionListener$ar$onCanceled$ar$class_merging).setException((Exception) e.getCause());
                                return;
                            }
                            ((TaskImpl) this.this$0$ar$class_merging$ae3b6252_0.OnCanceledCompletionListener$ar$onCanceled$ar$class_merging).setException(e);
                            return;
                        } catch (Exception e2) {
                            ((TaskImpl) this.this$0$ar$class_merging$ae3b6252_0.OnCanceledCompletionListener$ar$onCanceled$ar$class_merging).setException(e2);
                            return;
                        }
                    }
                    ((TaskImpl) this.this$0$ar$class_merging$ae3b6252_0.OnCanceledCompletionListener$ar$onCanceled$ar$class_merging).trySetCanceled$ar$ds();
                }
            });
            return;
        }
        if (((TaskImpl) task).canceled) {
            synchronized (this.lock) {
            }
            this.executor.execute(new TrainingActivity$$ExternalSyntheticLambda1(this, 12, null));
        }
    }

    public OnCanceledCompletionListener(Executor executor, Object obj, int i) {
        this.switching_field = i;
        this.lock = new Object();
        this.executor = executor;
        this.OnCanceledCompletionListener$ar$onCanceled$ar$class_merging = obj;
    }
}
