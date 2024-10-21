package com.google.android.apps.aicore.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl$$ExternalSyntheticLambda1;
import com.google.android.apps.aicore.llm.InterfaceData$SpeculativeDecodeStatistics;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.Duration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import j$.util.Collection;
import j$.util.stream.Collectors;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InferenceEventTraceResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<InferenceEventTraceResult> CREATOR = new InferenceEventTraceResultParcelableCreator();
    public final float cannedResponsesRatio;
    public final int customerId;
    public final int featureId;
    public final int featureType;
    public final int featureVariant;
    public final long inferenceApiCallHandlingLatencyMillis;
    public final long inferenceInputEncodingLatencyMillis;
    public final long inferenceInputSafetyCheckLatencyMillis;
    public final long inferenceLatencyTotalMillis;
    public final long inferenceOutputSafetyCheckLatencyMillis;
    public final long inferenceOverallOutputLatencyMillis;
    public final long inferenceServiceStartLatencyMillis;
    public final long inferenceStatefulSuspensionLatencyMillis;
    public final long inferenceStatelessSuspensionLatencyMillis;
    public final int inputSafetyCheckPolicyViolationType;
    public final int inputSafetyCheckSuggestedActionType;
    public final double inputTokensPerSecond;
    public final boolean isModelLoaded;
    public final long modelInferenceLatencyMillis;
    public final int numDecodeSteps;
    public final int numInputTokens;
    public final int numOutputTokens;
    public final int numPostDeduped;
    public final int numSamples;
    public final int numSuffixScoreFiltered;
    public final int outputSafetyCheckPolicyViolationType;
    public final int outputSafetyCheckSuggestedActionType;
    public final double outputTokensPerSecond;
    public final byte[] speculativeDecodeStatisticsBytes;
    public final int status;

    public InferenceEventTraceResult(int i, int i2, int i3, int i4, long j, int i5, int i6, int i7, long j2, long j3, long j4, long j5, long j6, long j7, int i8, int i9, int i10, int i11, boolean z, int i12, long j8, double d, double d2, int i13, float f, byte[] bArr, int i14, int i15, long j9, long j10) {
        this.customerId = i;
        this.featureType = i2;
        this.featureVariant = i3;
        this.status = i4;
        this.inferenceLatencyTotalMillis = j;
        this.numInputTokens = i5;
        this.numOutputTokens = i6;
        this.numDecodeSteps = i7;
        this.inferenceServiceStartLatencyMillis = j2;
        this.inferenceApiCallHandlingLatencyMillis = j3;
        this.inferenceInputSafetyCheckLatencyMillis = j4;
        this.inferenceInputEncodingLatencyMillis = j5;
        this.inferenceOverallOutputLatencyMillis = j6;
        this.inferenceOutputSafetyCheckLatencyMillis = j7;
        this.inputSafetyCheckPolicyViolationType = i8;
        this.inputSafetyCheckSuggestedActionType = i9;
        this.outputSafetyCheckPolicyViolationType = i10;
        this.outputSafetyCheckSuggestedActionType = i11;
        this.isModelLoaded = z;
        this.featureId = i12;
        this.modelInferenceLatencyMillis = j8;
        this.outputTokensPerSecond = d;
        this.inputTokensPerSecond = d2;
        this.numSamples = i13;
        this.cannedResponsesRatio = f;
        this.speculativeDecodeStatisticsBytes = bArr;
        this.numSuffixScoreFiltered = i14;
        this.numPostDeduped = i15;
        this.inferenceStatefulSuspensionLatencyMillis = j9;
        this.inferenceStatelessSuspensionLatencyMillis = j10;
    }

    public final String toString() {
        InterfaceData$SpeculativeDecodeStatistics interfaceData$SpeculativeDecodeStatistics;
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0("customerId", Integer.valueOf(this.customerId));
        builder.put$ar$ds$de9b9d28_0("featureType", Integer.valueOf(this.featureType));
        builder.put$ar$ds$de9b9d28_0("featureVariant", Integer.valueOf(this.featureVariant));
        builder.put$ar$ds$de9b9d28_0("status", Integer.valueOf(this.status));
        builder.put$ar$ds$de9b9d28_0("inferenceLatencyTotalMillis", Long.valueOf(this.inferenceLatencyTotalMillis));
        builder.put$ar$ds$de9b9d28_0("numInputTokens", Integer.valueOf(this.numInputTokens));
        builder.put$ar$ds$de9b9d28_0("numOutputTokens", Integer.valueOf(this.numOutputTokens));
        builder.put$ar$ds$de9b9d28_0("numDecodeSteps", Integer.valueOf(this.numDecodeSteps));
        builder.put$ar$ds$de9b9d28_0("inferenceServiceStartLatencyMillis", Long.valueOf(this.inferenceServiceStartLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("inferenceApiCallHandlingLatencyMillis", Long.valueOf(this.inferenceApiCallHandlingLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("inferenceInputSafetyCheckLatencyMillis", Long.valueOf(this.inferenceInputSafetyCheckLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("inferenceInputEncodingLatencyMillis", Long.valueOf(this.inferenceInputEncodingLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("inferenceOverallOutputLatencyMillis", Long.valueOf(this.inferenceOverallOutputLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("inferenceOutputSafetyCheckLatencyMillis", Long.valueOf(this.inferenceOutputSafetyCheckLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("inputSafetyCheckPolicyViolationType", Integer.valueOf(this.inputSafetyCheckPolicyViolationType));
        builder.put$ar$ds$de9b9d28_0("inputSafetyCheckSuggestedActionType", Integer.valueOf(this.inputSafetyCheckSuggestedActionType));
        builder.put$ar$ds$de9b9d28_0("outputSafetyCheckPolicyViolationType", Integer.valueOf(this.outputSafetyCheckPolicyViolationType));
        builder.put$ar$ds$de9b9d28_0("outputSafetyCheckSuggestedActionType", Integer.valueOf(this.outputSafetyCheckSuggestedActionType));
        builder.put$ar$ds$de9b9d28_0("isModelLoaded", Boolean.valueOf(this.isModelLoaded));
        builder.put$ar$ds$de9b9d28_0("featureId", Integer.valueOf(this.featureId));
        builder.put$ar$ds$de9b9d28_0("modelInferenceLatencyMillis", Long.valueOf(this.modelInferenceLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("outputTokensPerSecond", Double.valueOf(this.outputTokensPerSecond));
        builder.put$ar$ds$de9b9d28_0("inputTokensPerSecond", Double.valueOf(this.inputTokensPerSecond));
        builder.put$ar$ds$de9b9d28_0("numSamples", Integer.valueOf(this.numSamples));
        builder.put$ar$ds$de9b9d28_0("cannedResponsesRatio", Float.valueOf(this.cannedResponsesRatio));
        try {
            interfaceData$SpeculativeDecodeStatistics = (InterfaceData$SpeculativeDecodeStatistics) GeneratedMessageLite.parseFrom(InterfaceData$SpeculativeDecodeStatistics.DEFAULT_INSTANCE, this.speculativeDecodeStatisticsBytes, ExtensionRegistryLite.getGeneratedRegistry());
        } catch (InvalidProtocolBufferException e) {
            Log.e("InferenceEventTraceResult", "Failed to parse SpeculativeDecodeStatistics proto", e);
            interfaceData$SpeculativeDecodeStatistics = InterfaceData$SpeculativeDecodeStatistics.DEFAULT_INSTANCE;
        }
        StringBuilder sb = new StringBuilder("{drafter_id: ");
        sb.append(interfaceData$SpeculativeDecodeStatistics.drafterId_);
        sb.append(", drafter_time: ");
        Duration duration = interfaceData$SpeculativeDecodeStatistics.drafterTime_;
        if (duration == null) {
            duration = Duration.DEFAULT_INSTANCE;
        }
        sb.append(duration.seconds_);
        sb.append(", acceptance_rate: ");
        sb.append(interfaceData$SpeculativeDecodeStatistics.acceptanceRate_);
        sb.append(", drafter_guesses_per_position: ");
        sb.append((String) Collection.EL.stream(interfaceData$SpeculativeDecodeStatistics.drafterGuessesPerPosition_).map(new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(10)).collect(Collectors.joining(", ")));
        sb.append("}");
        builder.put$ar$ds$de9b9d28_0("speculativeDecodeStatistics", sb.toString());
        builder.put$ar$ds$de9b9d28_0("numSuffixScoreFiltered", Integer.valueOf(this.numSuffixScoreFiltered));
        builder.put$ar$ds$de9b9d28_0("numPostDeduped", Integer.valueOf(this.numPostDeduped));
        builder.put$ar$ds$de9b9d28_0("inferenceStatefulSuspensionLatencyMillis", Long.valueOf(this.inferenceStatefulSuspensionLatencyMillis));
        builder.put$ar$ds$de9b9d28_0("inferenceStatelessSuspensionLatencyMillis", Long.valueOf(this.inferenceStatelessSuspensionLatencyMillis));
        return "InferenceEventTraceResult\n".concat(ContextDataProvider.toStringImpl(builder.buildOrThrow()));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.customerId;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, i2);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.featureType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.featureVariant);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.status);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 5, this.inferenceLatencyTotalMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 6, this.numInputTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 7, this.numOutputTokens);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 8, this.numDecodeSteps);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 9, this.inferenceServiceStartLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 10, this.inferenceApiCallHandlingLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 11, this.inferenceInputSafetyCheckLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 12, this.inferenceInputEncodingLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 13, this.inferenceOverallOutputLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 14, this.inferenceOutputSafetyCheckLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 15, this.inputSafetyCheckPolicyViolationType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 16, this.inputSafetyCheckSuggestedActionType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 17, this.outputSafetyCheckPolicyViolationType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 18, this.outputSafetyCheckSuggestedActionType);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 19, this.isModelLoaded);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 20, this.featureId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 21, this.modelInferenceLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeDouble(parcel, 22, this.outputTokensPerSecond);
        StrictModeUtils$VmPolicyBuilderCompatS.writeDouble(parcel, 23, this.inputTokensPerSecond);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 24, this.numSamples);
        StrictModeUtils$VmPolicyBuilderCompatS.writeFloat(parcel, 25, this.cannedResponsesRatio);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 26, this.speculativeDecodeStatisticsBytes);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 27, this.numSuffixScoreFiltered);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 28, this.numPostDeduped);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 29, this.inferenceStatefulSuspensionLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 30, this.inferenceStatelessSuspensionLatencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
