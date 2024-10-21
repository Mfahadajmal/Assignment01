package com.google.android.gms.feedback;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.libraries.surveys.internal.model.QuestionMetrics;
import com.google.android.libraries.surveys.internal.model.SurveyDataImpl;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.common.android.concurrent.ParcelableFuture;
import com.google.mlkit.vision.common.aidls.ImageMetadataParcel;
import com.google.mlkit.vision.text.aidls.TextBlockParcel;
import com.google.mlkit.vision.text.aidls.TextElementParcel;
import com.google.mlkit.vision.text.aidls.TextLineParcel;
import com.google.mlkit.vision.text.aidls.TextParcel;
import com.google.mlkit.vision.text.aidls.TextRecognizerOptions;
import com.google.mlkit.vision.text.aidls.TextSymbolParcel;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.contrib.android.ProtoParsers$InternalDontUse;
import java.util.ArrayList;
import org.chromium.base.UnguessableToken;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServiceDumpRequestCreator implements Parcelable.Creator {
    private final /* synthetic */ int switching_field;

    public ServiceDumpRequestCreator(int i) {
        this.switching_field = i;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        switch (this.switching_field) {
            case 0:
                int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                boolean z = false;
                String[] strArr = null;
                while (parcel.dataPosition() < validateObjectHeader) {
                    int readInt = parcel.readInt();
                    int fieldId = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt);
                    if (fieldId != 2) {
                        if (fieldId != 3) {
                            if (fieldId != 4) {
                                if (fieldId != 5) {
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt);
                                } else {
                                    z = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt);
                                }
                            } else {
                                i = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                            }
                        } else {
                            strArr = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readInt);
                        }
                    } else {
                        str = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
                return new ServiceDumpRequest(str, strArr, i, z);
            case 1:
                return new QuestionMetrics(parcel);
            case 2:
                try {
                    return new SurveyDataImpl(parcel);
                } catch (InvalidProtocolBufferException e) {
                    throw new BadParcelableException(e);
                }
            case 3:
                return new MaterialCheckBox.SavedState(parcel);
            case 4:
                return new ParcelableFuture(parcel);
            case 5:
                int validateObjectHeader2 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                long j = 0;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (parcel.dataPosition() < validateObjectHeader2) {
                    int readInt2 = parcel.readInt();
                    int fieldId2 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt2);
                    if (fieldId2 != 1) {
                        if (fieldId2 != 2) {
                            if (fieldId2 != 3) {
                                if (fieldId2 != 4) {
                                    if (fieldId2 != 5) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt2);
                                    } else {
                                        j = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt2);
                                    }
                                } else {
                                    i5 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt2);
                                }
                            } else {
                                i4 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt2);
                            }
                        } else {
                            i3 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt2);
                        }
                    } else {
                        i2 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt2);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader2);
                return new ImageMetadataParcel(i2, i3, i4, i5, j);
            case 6:
                int validateObjectHeader3 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str2 = null;
                Rect rect = null;
                ArrayList arrayList = null;
                String str3 = null;
                ArrayList arrayList2 = null;
                while (parcel.dataPosition() < validateObjectHeader3) {
                    int readInt3 = parcel.readInt();
                    int fieldId3 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt3);
                    if (fieldId3 != 1) {
                        if (fieldId3 != 2) {
                            if (fieldId3 != 3) {
                                if (fieldId3 != 4) {
                                    if (fieldId3 != 5) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt3);
                                    } else {
                                        arrayList2 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt3, TextLineParcel.CREATOR);
                                    }
                                } else {
                                    str3 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt3);
                                }
                            } else {
                                arrayList = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt3, Point.CREATOR);
                            }
                        } else {
                            rect = (Rect) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt3, Rect.CREATOR);
                        }
                    } else {
                        str2 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt3);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader3);
                return new TextBlockParcel(str2, rect, arrayList, str3, arrayList2);
            case 7:
                int validateObjectHeader4 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                float f = 0.0f;
                float f2 = 0.0f;
                String str4 = null;
                Rect rect2 = null;
                ArrayList arrayList3 = null;
                String str5 = null;
                ArrayList arrayList4 = null;
                while (parcel.dataPosition() < validateObjectHeader4) {
                    int readInt4 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt4)) {
                        case 1:
                            str4 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt4);
                            break;
                        case 2:
                            rect2 = (Rect) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt4, Rect.CREATOR);
                            break;
                        case 3:
                            arrayList3 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt4, Point.CREATOR);
                            break;
                        case 4:
                            str5 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt4);
                            break;
                        case 5:
                            f = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt4);
                            break;
                        case 6:
                            f2 = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt4);
                            break;
                        case 7:
                            arrayList4 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt4, TextSymbolParcel.CREATOR);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt4);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader4);
                return new TextElementParcel(str4, rect2, arrayList3, str5, f, f2, arrayList4);
            case 8:
                int validateObjectHeader5 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                float f3 = 0.0f;
                float f4 = 0.0f;
                String str6 = null;
                Rect rect3 = null;
                ArrayList arrayList5 = null;
                String str7 = null;
                ArrayList arrayList6 = null;
                while (parcel.dataPosition() < validateObjectHeader5) {
                    int readInt5 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt5)) {
                        case 1:
                            str6 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt5);
                            break;
                        case 2:
                            rect3 = (Rect) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt5, Rect.CREATOR);
                            break;
                        case 3:
                            arrayList5 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt5, Point.CREATOR);
                            break;
                        case 4:
                            str7 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt5);
                            break;
                        case 5:
                            arrayList6 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt5, TextElementParcel.CREATOR);
                            break;
                        case 6:
                            f3 = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt5);
                            break;
                        case 7:
                            f4 = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt5);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt5);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader5);
                return new TextLineParcel(str6, rect3, arrayList5, str7, arrayList6, f3, f4);
            case 9:
                int validateObjectHeader6 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                ArrayList arrayList7 = null;
                while (parcel.dataPosition() < validateObjectHeader6) {
                    int readInt6 = parcel.readInt();
                    int fieldId4 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt6);
                    if (fieldId4 != 1) {
                        if (fieldId4 != 2) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt6);
                        } else {
                            arrayList7 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt6, TextBlockParcel.CREATOR);
                        }
                    } else {
                        str = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt6);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader6);
                return new TextParcel(str, arrayList7);
            case 10:
                int validateObjectHeader7 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                boolean z2 = false;
                int i6 = 0;
                boolean z3 = false;
                String str8 = null;
                String str9 = null;
                String str10 = null;
                String str11 = null;
                while (parcel.dataPosition() < validateObjectHeader7) {
                    int readInt7 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt7)) {
                        case 1:
                            str8 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                            break;
                        case 2:
                            str9 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                            break;
                        case 3:
                            str10 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                            break;
                        case 4:
                            z2 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt7);
                            break;
                        case 5:
                            i6 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt7);
                            break;
                        case 6:
                            str11 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                            break;
                        case 7:
                            z3 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt7);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt7);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader7);
                return new TextRecognizerOptions(str8, str9, str10, z2, i6, str11, z3);
            case 11:
                int validateObjectHeader8 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                float f5 = 0.0f;
                float f6 = 0.0f;
                String str12 = null;
                Rect rect4 = null;
                ArrayList arrayList8 = null;
                while (parcel.dataPosition() < validateObjectHeader8) {
                    int readInt8 = parcel.readInt();
                    int fieldId5 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt8);
                    if (fieldId5 != 1) {
                        if (fieldId5 != 2) {
                            if (fieldId5 != 3) {
                                if (fieldId5 != 4) {
                                    if (fieldId5 != 5) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt8);
                                    } else {
                                        f6 = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt8);
                                    }
                                } else {
                                    f5 = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt8);
                                }
                            } else {
                                arrayList8 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt8, Point.CREATOR);
                            }
                        } else {
                            rect4 = (Rect) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt8, Rect.CREATOR);
                        }
                    } else {
                        str12 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt8);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader8);
                return new TextSymbolParcel(str12, rect4, arrayList8, f5, f6);
            case 12:
                byte[] bArr = new byte[parcel.readInt()];
                parcel.readByteArray(bArr);
                return new ProtoParsers$InternalDontUse(bArr, null);
            default:
                long readLong = parcel.readLong();
                long readLong2 = parcel.readLong();
                if (readLong == 0 || readLong2 == 0) {
                    return null;
                }
                return new UnguessableToken(readLong, readLong2);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        switch (this.switching_field) {
            case 0:
                return new ServiceDumpRequest[i];
            case 1:
                return new QuestionMetrics[i];
            case 2:
                return new SurveyDataImpl[i];
            case 3:
                return new MaterialCheckBox.SavedState[i];
            case 4:
                return new ParcelableFuture[i];
            case 5:
                return new ImageMetadataParcel[i];
            case 6:
                return new TextBlockParcel[i];
            case 7:
                return new TextElementParcel[i];
            case 8:
                return new TextLineParcel[i];
            case 9:
                return new TextParcel[i];
            case 10:
                return new TextRecognizerOptions[i];
            case 11:
                return new TextSymbolParcel[i];
            case 12:
                return new ProtoParsers$InternalDontUse[i];
            default:
                return new UnguessableToken[i];
        }
    }
}
