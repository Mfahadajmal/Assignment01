package com.google.android.gms.feedback;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.pseudonymous.SessionZwiebackToken;
import com.google.android.gms.signin.internal.AuthAccountResult;
import com.google.android.gms.signin.internal.RecordConsentByConsentResultResponse;
import com.google.android.gms.signin.internal.SignInRequest;
import com.google.android.gms.signin.internal.SignInResponse;
import com.google.android.gms.usagereporting.ConsentInformation;
import com.google.android.gms.usagereporting.ElCapitanOptions;
import com.google.android.gms.usagereporting.SafetyOptions;
import com.google.android.gms.usagereporting.UsageReportingOptInOptions;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;
import com.google.android.gms.vision.text.internal.client.BoundingBoxParcel;
import com.google.android.gms.vision.text.internal.client.LineBoxParcel;
import com.google.android.gms.vision.text.internal.client.SymbolBoxParcel;
import com.google.android.gms.vision.text.internal.client.TextRecognizerOptions;
import com.google.android.gms.vision.text.internal.client.WordBoxParcel;
import com.google.android.libraries.mdi.download.debug.common.filegroups.AutoValue_MddDebugMainFragmentHelper_ActionInfo;
import com.google.android.libraries.social.licenses.License;
import com.google.android.libraries.surveys.SurveyMetadata;
import com.google.android.libraries.surveys.internal.model.Answer;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServiceDumpCreator implements Parcelable.Creator {
    private final /* synthetic */ int switching_field;

    public ServiceDumpCreator(int i) {
        this.switching_field = i;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ServiceDumpRequest serviceDumpRequest = null;
        String str = null;
        String str2 = null;
        ArrayList arrayList = null;
        ConnectionResult connectionResult = null;
        ResolveAccountRequest resolveAccountRequest = null;
        ArrayList arrayList2 = null;
        Intent intent = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        int i3 = 0;
        switch (this.switching_field) {
            case 0:
                int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                byte[] bArr = null;
                while (parcel.dataPosition() < validateObjectHeader) {
                    int readInt = parcel.readInt();
                    int fieldId = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt);
                    if (fieldId != 2) {
                        if (fieldId != 3) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt);
                        } else {
                            bArr = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt);
                        }
                    } else {
                        serviceDumpRequest = (ServiceDumpRequest) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt, ServiceDumpRequest.CREATOR);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
                return new ServiceDump(serviceDumpRequest, bArr);
            case 1:
                return new SessionZwiebackToken(parcel);
            case 2:
                int validateObjectHeader2 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                int i4 = 0;
                while (parcel.dataPosition() < validateObjectHeader2) {
                    int readInt2 = parcel.readInt();
                    int fieldId2 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt2);
                    if (fieldId2 != 1) {
                        if (fieldId2 != 2) {
                            if (fieldId2 != 3) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt2);
                            } else {
                                intent = (Intent) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt2, Intent.CREATOR);
                            }
                        } else {
                            i4 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt2);
                        }
                    } else {
                        i = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt2);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader2);
                return new AuthAccountResult(i, i4, intent);
            case 3:
                int validateObjectHeader3 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str3 = null;
                while (parcel.dataPosition() < validateObjectHeader3) {
                    int readInt3 = parcel.readInt();
                    int fieldId3 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt3);
                    if (fieldId3 != 1) {
                        if (fieldId3 != 2) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt3);
                        } else {
                            str3 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt3);
                        }
                    } else {
                        arrayList2 = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readInt3);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader3);
                return new RecordConsentByConsentResultResponse(arrayList2, str3);
            case 4:
                int validateObjectHeader4 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                while (parcel.dataPosition() < validateObjectHeader4) {
                    int readInt4 = parcel.readInt();
                    int fieldId4 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt4);
                    if (fieldId4 != 1) {
                        if (fieldId4 != 2) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt4);
                        } else {
                            resolveAccountRequest = (ResolveAccountRequest) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt4, ResolveAccountRequest.CREATOR);
                        }
                    } else {
                        i3 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt4);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader4);
                return new SignInRequest(i3, resolveAccountRequest);
            case 5:
                int validateObjectHeader5 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                ResolveAccountResponse resolveAccountResponse = null;
                while (parcel.dataPosition() < validateObjectHeader5) {
                    int readInt5 = parcel.readInt();
                    int fieldId5 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt5);
                    if (fieldId5 != 1) {
                        if (fieldId5 != 2) {
                            if (fieldId5 != 3) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt5);
                            } else {
                                resolveAccountResponse = (ResolveAccountResponse) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt5, ResolveAccountResponse.CREATOR);
                            }
                        } else {
                            connectionResult = (ConnectionResult) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt5, ConnectionResult.CREATOR);
                        }
                    } else {
                        i2 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt5);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader5);
                return new SignInResponse(i2, connectionResult, resolveAccountResponse);
            case 6:
                int validateObjectHeader6 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                String str4 = null;
                byte[] bArr2 = null;
                ArrayList arrayList3 = null;
                while (parcel.dataPosition() < validateObjectHeader6) {
                    int readInt6 = parcel.readInt();
                    int fieldId6 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt6);
                    if (fieldId6 != 1) {
                        if (fieldId6 != 2) {
                            if (fieldId6 != 3) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt6);
                            } else {
                                int readSize = StrictModeUtils$VmPolicyBuilderCompatS.readSize(parcel, readInt6);
                                int dataPosition = parcel.dataPosition();
                                if (readSize == 0) {
                                    arrayList3 = null;
                                } else {
                                    ArrayList arrayList4 = new ArrayList();
                                    int readInt7 = parcel.readInt();
                                    for (int i5 = 0; i5 < readInt7; i5++) {
                                        arrayList4.add(Integer.valueOf(parcel.readInt()));
                                    }
                                    parcel.setDataPosition(dataPosition + readSize);
                                    arrayList3 = arrayList4;
                                }
                            }
                        } else {
                            bArr2 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt6);
                        }
                    } else {
                        str4 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt6);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader6);
                return new ConsentInformation.AccountConsentInformation(str4, bArr2, arrayList3);
            case 7:
                int validateObjectHeader7 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                boolean z4 = false;
                while (parcel.dataPosition() < validateObjectHeader7) {
                    int readInt8 = parcel.readInt();
                    int fieldId7 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt8);
                    if (fieldId7 != 1) {
                        if (fieldId7 != 2) {
                            if (fieldId7 != 3) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt8);
                            } else {
                                z4 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt8);
                            }
                        } else {
                            z3 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt8);
                        }
                    } else {
                        arrayList = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt8, ConsentInformation.AccountConsentInformation.CREATOR);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader7);
                return new ConsentInformation(arrayList, z3, z4);
            case 8:
                int validateObjectHeader8 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                int i6 = 0;
                while (parcel.dataPosition() < validateObjectHeader8) {
                    int readInt9 = parcel.readInt();
                    int fieldId8 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt9);
                    if (fieldId8 != 2) {
                        if (fieldId8 != 3) {
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt9);
                        } else {
                            i6 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt9);
                        }
                    } else {
                        z2 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt9);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader8);
                return new ElCapitanOptions(z2, i6);
            case 9:
                int validateObjectHeader9 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                boolean z5 = false;
                int i7 = 0;
                while (parcel.dataPosition() < validateObjectHeader9) {
                    int readInt10 = parcel.readInt();
                    int fieldId9 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt10);
                    if (fieldId9 != 2) {
                        if (fieldId9 != 3) {
                            if (fieldId9 != 4) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt10);
                            } else {
                                i7 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt10);
                            }
                        } else {
                            z5 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt10);
                        }
                    } else {
                        z = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt10);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader9);
                return new SafetyOptions(z, z5, i7);
            case 10:
                int validateObjectHeader10 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                ArrayList arrayList5 = null;
                String str5 = null;
                int i8 = 0;
                boolean z6 = false;
                int i9 = 0;
                boolean z7 = false;
                while (parcel.dataPosition() < validateObjectHeader10) {
                    int readInt11 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt11)) {
                        case 2:
                            i8 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt11);
                            break;
                        case 3:
                            z6 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt11);
                            break;
                        case 4:
                            arrayList5 = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readInt11);
                            break;
                        case 5:
                            i9 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt11);
                            break;
                        case 6:
                            str5 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt11);
                            break;
                        case 7:
                            z7 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt11);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt11);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader10);
                return new UsageReportingOptInOptions(i8, z6, arrayList5, i9, str5, z7);
            case 11:
                int validateObjectHeader11 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                long j = 0;
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                int i13 = 0;
                while (parcel.dataPosition() < validateObjectHeader11) {
                    int readInt12 = parcel.readInt();
                    int fieldId10 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt12);
                    if (fieldId10 != 2) {
                        if (fieldId10 != 3) {
                            if (fieldId10 != 4) {
                                if (fieldId10 != 5) {
                                    if (fieldId10 != 6) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt12);
                                    } else {
                                        i13 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt12);
                                    }
                                } else {
                                    j = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt12);
                                }
                            } else {
                                i12 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt12);
                            }
                        } else {
                            i11 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt12);
                        }
                    } else {
                        i10 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt12);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader11);
                return new FrameMetadataParcel(i10, i11, i12, j, i13);
            case 12:
                int validateObjectHeader12 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                float f = 0.0f;
                int i14 = 0;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                while (parcel.dataPosition() < validateObjectHeader12) {
                    int readInt13 = parcel.readInt();
                    int fieldId11 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt13);
                    if (fieldId11 != 2) {
                        if (fieldId11 != 3) {
                            if (fieldId11 != 4) {
                                if (fieldId11 != 5) {
                                    if (fieldId11 != 6) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt13);
                                    } else {
                                        f = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt13);
                                    }
                                } else {
                                    i17 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                                }
                            } else {
                                i16 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                            }
                        } else {
                            i15 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                        }
                    } else {
                        i14 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader12);
                return new BoundingBoxParcel(i14, i15, i16, i17, f);
            case 13:
                int validateObjectHeader13 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                float f2 = 0.0f;
                WordBoxParcel[] wordBoxParcelArr = null;
                BoundingBoxParcel boundingBoxParcel = null;
                BoundingBoxParcel boundingBoxParcel2 = null;
                BoundingBoxParcel boundingBoxParcel3 = null;
                String str6 = null;
                String str7 = null;
                int i18 = 0;
                boolean z8 = false;
                int i19 = 0;
                int i20 = 0;
                while (parcel.dataPosition() < validateObjectHeader13) {
                    int readInt14 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt14)) {
                        case 2:
                            wordBoxParcelArr = (WordBoxParcel[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readInt14, WordBoxParcel.CREATOR);
                            break;
                        case 3:
                            boundingBoxParcel = (BoundingBoxParcel) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt14, BoundingBoxParcel.CREATOR);
                            break;
                        case 4:
                            boundingBoxParcel2 = (BoundingBoxParcel) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt14, BoundingBoxParcel.CREATOR);
                            break;
                        case 5:
                            boundingBoxParcel3 = (BoundingBoxParcel) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt14, BoundingBoxParcel.CREATOR);
                            break;
                        case 6:
                            str6 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt14);
                            break;
                        case 7:
                            f2 = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt14);
                            break;
                        case 8:
                            str7 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt14);
                            break;
                        case 9:
                            i18 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt14);
                            break;
                        case 10:
                            z8 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt14);
                            break;
                        case 11:
                            i19 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt14);
                            break;
                        case 12:
                            i20 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt14);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt14);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader13);
                return new LineBoxParcel(wordBoxParcelArr, boundingBoxParcel, boundingBoxParcel2, boundingBoxParcel3, str6, f2, str7, i18, z8, i19, i20);
            case 14:
                int validateObjectHeader14 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                while (parcel.dataPosition() < validateObjectHeader14) {
                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, parcel.readInt());
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader14);
                return new SymbolBoxParcel();
            case 15:
                int validateObjectHeader15 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                while (parcel.dataPosition() < validateObjectHeader15) {
                    int readInt15 = parcel.readInt();
                    if (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt15) != 2) {
                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt15);
                    } else {
                        str2 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt15);
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader15);
                return new TextRecognizerOptions(str2);
            case 16:
                int validateObjectHeader16 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                float f3 = 0.0f;
                SymbolBoxParcel[] symbolBoxParcelArr = null;
                BoundingBoxParcel boundingBoxParcel4 = null;
                BoundingBoxParcel boundingBoxParcel5 = null;
                String str8 = null;
                String str9 = null;
                boolean z9 = false;
                while (parcel.dataPosition() < validateObjectHeader16) {
                    int readInt16 = parcel.readInt();
                    switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt16)) {
                        case 2:
                            symbolBoxParcelArr = (SymbolBoxParcel[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readInt16, SymbolBoxParcel.CREATOR);
                            break;
                        case 3:
                            boundingBoxParcel4 = (BoundingBoxParcel) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt16, BoundingBoxParcel.CREATOR);
                            break;
                        case 4:
                            boundingBoxParcel5 = (BoundingBoxParcel) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt16, BoundingBoxParcel.CREATOR);
                            break;
                        case 5:
                            str8 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt16);
                            break;
                        case 6:
                            f3 = StrictModeUtils$VmPolicyBuilderCompatS.readFloat(parcel, readInt16);
                            break;
                        case 7:
                            str9 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt16);
                            break;
                        case 8:
                            z9 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt16);
                            break;
                        default:
                            StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt16);
                            break;
                    }
                }
                StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader16);
                return new WordBoxParcel(symbolBoxParcelArr, boundingBoxParcel4, boundingBoxParcel5, str8, f3, str9, z9);
            case 17:
                String readString = parcel.readString();
                if (parcel.readInt() == 0) {
                    str = parcel.readString();
                }
                return new AutoValue_MddDebugMainFragmentHelper_ActionInfo(readString, str);
            case 18:
                return new License(parcel);
            case 19:
                return new SurveyMetadata(parcel);
            default:
                return new Answer(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        switch (this.switching_field) {
            case 0:
                return new ServiceDump[i];
            case 1:
                return new SessionZwiebackToken[i];
            case 2:
                return new AuthAccountResult[i];
            case 3:
                return new RecordConsentByConsentResultResponse[i];
            case 4:
                return new SignInRequest[i];
            case 5:
                return new SignInResponse[i];
            case 6:
                return new ConsentInformation.AccountConsentInformation[i];
            case 7:
                return new ConsentInformation[i];
            case 8:
                return new ElCapitanOptions[i];
            case 9:
                return new SafetyOptions[i];
            case 10:
                return new UsageReportingOptInOptions[i];
            case 11:
                return new FrameMetadataParcel[i];
            case 12:
                return new BoundingBoxParcel[i];
            case 13:
                return new LineBoxParcel[i];
            case 14:
                return new SymbolBoxParcel[i];
            case 15:
                return new TextRecognizerOptions[i];
            case 16:
                return new WordBoxParcel[i];
            case 17:
                return new AutoValue_MddDebugMainFragmentHelper_ActionInfo[i];
            case 18:
                return new License[i];
            case 19:
                return new SurveyMetadata[i];
            default:
                return new Answer[i];
        }
    }
}
