package com.android.settingslib.widget;

import _COROUTINE._BOUNDARY;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.preference.R$styleable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.brltty.BrailleKeyBinding;
import com.google.android.apps.aicore.aidl.AIFeature;
import com.google.android.apps.aicore.aidl.ImageDescriptionRequest;
import com.google.android.apps.aicore.aidl.ImageDescriptionResult;
import com.google.android.apps.aicore.aidl.InferenceEventTraceResult;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.gms.auth.TokenData;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.clearcut.internal.BatchedLogErrorParcelable;
import com.google.android.gms.clearcut.internal.DataCollectionIdentifierParcelable;
import com.google.android.gms.clearcut.internal.LogErrorParcelable;
import com.google.android.gms.clearcut.internal.LogVerifierResultParcelable;
import com.google.android.gms.clearcut.internal.PlayLoggerContext;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.phenotype.ExperimentTokens;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MainSwitchBar extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    private int mBackgroundActivatedColor;
    private int mBackgroundColor;
    private View mFrameView;
    protected Switch mSwitch;
    private final List mSwitchChangeListeners;
    protected TextView mTextView;

    public MainSwitchBar(Context context) {
        this(context, null);
    }

    private final void setBackground(boolean z) {
        int i;
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            if (z) {
                i = this.mBackgroundActivatedColor;
            } else {
                i = this.mBackgroundColor;
            }
            setBackgroundColor(i);
            return;
        }
        this.mFrameView.setActivated(z);
    }

    public final void addOnSwitchChangeListener(OnMainSwitchChangeListener onMainSwitchChangeListener) {
        if (!this.mSwitchChangeListeners.contains(onMainSwitchChangeListener)) {
            this.mSwitchChangeListeners.add(onMainSwitchChangeListener);
        }
    }

    public final boolean isChecked() {
        return this.mSwitch.isChecked();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        setBackground(z);
        int size = this.mSwitchChangeListeners.size();
        for (int i = 0; i < size; i++) {
            ((OnMainSwitchChangeListener) this.mSwitchChangeListeners.get(i)).onSwitchChanged$ar$ds(z);
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        MainSwitchBar mainSwitchBar;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mSwitch.setChecked(savedState.mChecked);
        setChecked(savedState.mChecked);
        setBackground(savedState.mChecked);
        if (true != savedState.mVisible) {
            i = 8;
        } else {
            i = 0;
        }
        setVisibility(i);
        Switch r0 = this.mSwitch;
        if (true != savedState.mVisible) {
            mainSwitchBar = null;
        } else {
            mainSwitchBar = this;
        }
        r0.setOnCheckedChangeListener(mainSwitchBar);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        boolean z;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.mChecked = this.mSwitch.isChecked();
        if (getVisibility() == 0) {
            z = true;
        } else {
            z = false;
        }
        savedState.mVisible = z;
        return savedState;
    }

    @Override // android.view.View
    public final boolean performClick() {
        this.mSwitch.performClick();
        return super.performClick();
    }

    public final void setChecked(boolean z) {
        Switch r0 = this.mSwitch;
        if (r0 != null) {
            r0.setChecked(z);
        }
        setBackground(z);
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mTextView.setEnabled(z);
        this.mSwitch.setEnabled(z);
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            this.mFrameView.setEnabled(z);
            this.mFrameView.setActivated(isChecked());
        }
    }

    public final void setTitle(CharSequence charSequence) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public MainSwitchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1(0);
        boolean mChecked;
        boolean mVisible;

        /* compiled from: PG */
        /* renamed from: com.android.settingslib.widget.MainSwitchBar$SavedState$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements Parcelable.Creator {
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(int i) {
                this.switching_field = i;
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                int i = 0;
                boolean z = false;
                int i2 = 0;
                ArrayList arrayList = null;
                String str = null;
                ArrayList arrayList2 = null;
                ArrayList arrayList3 = null;
                switch (this.switching_field) {
                    case 0:
                        return new SavedState(parcel);
                    case 1:
                        return new SwipeRefreshLayout.SavedState(parcel);
                    case 2:
                        return new BrailleDisplayProperties(parcel);
                    case 3:
                        return new BrailleInputEvent(parcel, null);
                    case 4:
                        return new BrailleKeyBinding(parcel);
                    case 5:
                        int validateObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        int i3 = 0;
                        int i4 = 0;
                        int i5 = 0;
                        String str2 = null;
                        String str3 = null;
                        while (parcel.dataPosition() < validateObjectHeader) {
                            int readInt = parcel.readInt();
                            int fieldId = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt);
                            if (fieldId != 1) {
                                if (fieldId != 2) {
                                    if (fieldId != 3) {
                                        if (fieldId != 4) {
                                            if (fieldId != 5) {
                                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt);
                                            } else {
                                                i5 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                                            }
                                        } else {
                                            i4 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                                        }
                                    } else {
                                        i3 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt);
                                    }
                                } else {
                                    str3 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                                }
                            } else {
                                str2 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader);
                        return new AIFeature(str2, str3, i3, i4, i5);
                    case 6:
                        int validateObjectHeader2 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        IBinder iBinder = null;
                        while (parcel.dataPosition() < validateObjectHeader2) {
                            int readInt2 = parcel.readInt();
                            int fieldId2 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt2);
                            if (fieldId2 != 1) {
                                if (fieldId2 != 2) {
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt2);
                                } else {
                                    iBinder = StrictModeUtils$VmPolicyBuilderCompatS.readIBinder(parcel, readInt2);
                                }
                            } else {
                                arrayList = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt2, Bitmap.CREATOR);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader2);
                        return new ImageDescriptionRequest(arrayList, iBinder);
                    case 7:
                        int validateObjectHeader3 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        InferenceEventTraceResult inferenceEventTraceResult = null;
                        while (parcel.dataPosition() < validateObjectHeader3) {
                            int readInt3 = parcel.readInt();
                            int fieldId3 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt3);
                            if (fieldId3 != 1) {
                                if (fieldId3 != 2) {
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt3);
                                } else {
                                    inferenceEventTraceResult = (InferenceEventTraceResult) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt3, InferenceEventTraceResult.CREATOR);
                                }
                            } else {
                                arrayList3 = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readInt3);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader3);
                        return new ImageDescriptionResult(arrayList3, inferenceEventTraceResult);
                    case 8:
                        return new FlexboxLayout.LayoutParams(parcel);
                    case 9:
                        return new FlexboxLayoutManager.LayoutParams(parcel);
                    case 10:
                        return new FlexboxLayoutManager.SavedState(parcel);
                    case 11:
                        int validateObjectHeader4 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        int i6 = 0;
                        boolean z2 = false;
                        boolean z3 = false;
                        String str4 = null;
                        Long l = null;
                        ArrayList arrayList4 = null;
                        String str5 = null;
                        while (parcel.dataPosition() < validateObjectHeader4) {
                            int readInt4 = parcel.readInt();
                            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt4)) {
                                case 1:
                                    i6 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt4);
                                    break;
                                case 2:
                                    str4 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt4);
                                    break;
                                case 3:
                                    int readSize = StrictModeUtils$VmPolicyBuilderCompatS.readSize(parcel, readInt4);
                                    if (readSize == 0) {
                                        l = null;
                                        break;
                                    } else {
                                        StrictModeUtils$VmPolicyBuilderCompatS.enforceSize$ar$ds(parcel, readSize, 8);
                                        l = Long.valueOf(parcel.readLong());
                                        break;
                                    }
                                case 4:
                                    z2 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt4);
                                    break;
                                case 5:
                                    z3 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt4);
                                    break;
                                case 6:
                                    arrayList4 = StrictModeUtils$VmPolicyBuilderCompatS.createStringList(parcel, readInt4);
                                    break;
                                case 7:
                                    str5 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt4);
                                    break;
                                default:
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt4);
                                    break;
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader4);
                        return new TokenData(i6, str4, l, z2, z3, arrayList4, str5);
                    case 12:
                        int validateObjectHeader5 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        long j = 0;
                        int i7 = 0;
                        int i8 = 0;
                        String str6 = null;
                        byte[] bArr = null;
                        Bundle bundle = null;
                        while (parcel.dataPosition() < validateObjectHeader5) {
                            int readInt5 = parcel.readInt();
                            int fieldId4 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt5);
                            if (fieldId4 != 1) {
                                if (fieldId4 != 2) {
                                    if (fieldId4 != 3) {
                                        if (fieldId4 != 4) {
                                            if (fieldId4 != 5) {
                                                if (fieldId4 != 1000) {
                                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt5);
                                                } else {
                                                    i7 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt5);
                                                }
                                            } else {
                                                bundle = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readInt5);
                                            }
                                        } else {
                                            bArr = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt5);
                                        }
                                    } else {
                                        j = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt5);
                                    }
                                } else {
                                    i8 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt5);
                                }
                            } else {
                                str6 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt5);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader5);
                        return new ProxyRequest(i7, str6, i8, j, bArr, bundle);
                    case 13:
                        int validateObjectHeader6 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        int i9 = 0;
                        int i10 = 0;
                        int i11 = 0;
                        PendingIntent pendingIntent = null;
                        Bundle bundle2 = null;
                        byte[] bArr2 = null;
                        while (parcel.dataPosition() < validateObjectHeader6) {
                            int readInt6 = parcel.readInt();
                            int fieldId5 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt6);
                            if (fieldId5 != 1) {
                                if (fieldId5 != 2) {
                                    if (fieldId5 != 3) {
                                        if (fieldId5 != 4) {
                                            if (fieldId5 != 5) {
                                                if (fieldId5 != 1000) {
                                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt6);
                                                } else {
                                                    i9 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt6);
                                                }
                                            } else {
                                                bArr2 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt6);
                                            }
                                        } else {
                                            bundle2 = StrictModeUtils$VmPolicyBuilderCompatS.createBundle(parcel, readInt6);
                                        }
                                    } else {
                                        i11 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt6);
                                    }
                                } else {
                                    pendingIntent = (PendingIntent) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt6, PendingIntent.CREATOR);
                                }
                            } else {
                                i10 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt6);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader6);
                        return new ProxyResponse(i9, i10, pendingIntent, i11, bundle2, bArr2);
                    case 14:
                        int validateObjectHeader7 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        long j2 = 0;
                        String str7 = null;
                        String str8 = null;
                        String str9 = null;
                        String str10 = null;
                        Uri uri = null;
                        String str11 = null;
                        String str12 = null;
                        ArrayList arrayList5 = null;
                        String str13 = null;
                        String str14 = null;
                        while (parcel.dataPosition() < validateObjectHeader7) {
                            int readInt7 = parcel.readInt();
                            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt7)) {
                                case 2:
                                    str7 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                case 3:
                                    str8 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                case 4:
                                    str9 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                case 5:
                                    str10 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                case 6:
                                    uri = (Uri) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt7, Uri.CREATOR);
                                    break;
                                case 7:
                                    str11 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                case 8:
                                    j2 = StrictModeUtils$VmPolicyBuilderCompatS.readLong(parcel, readInt7);
                                    break;
                                case 9:
                                    str12 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                case 10:
                                    arrayList5 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt7, Scope.CREATOR);
                                    break;
                                case 11:
                                    str13 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                case 12:
                                    str14 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt7);
                                    break;
                                default:
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt7);
                                    break;
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader7);
                        return new GoogleSignInAccount(str7, str8, str9, str10, uri, str11, j2, str12, arrayList5, str13, str14);
                    case 15:
                        int validateObjectHeader8 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        boolean z4 = true;
                        int i12 = 0;
                        PlayLoggerContext playLoggerContext = null;
                        byte[] bArr3 = null;
                        int[] iArr = null;
                        String[] strArr = null;
                        int[] iArr2 = null;
                        byte[][] bArr4 = null;
                        ExperimentTokens[] experimentTokensArr = null;
                        LogVerifierResultParcelable logVerifierResultParcelable = null;
                        String[] strArr2 = null;
                        DataCollectionIdentifierParcelable dataCollectionIdentifierParcelable = null;
                        while (parcel.dataPosition() < validateObjectHeader8) {
                            int readInt8 = parcel.readInt();
                            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt8)) {
                                case 2:
                                    playLoggerContext = (PlayLoggerContext) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt8, PlayLoggerContext.CREATOR);
                                    break;
                                case 3:
                                    bArr3 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArray(parcel, readInt8);
                                    break;
                                case 4:
                                    iArr = StrictModeUtils$VmPolicyBuilderCompatS.createIntArray(parcel, readInt8);
                                    break;
                                case 5:
                                    strArr = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readInt8);
                                    break;
                                case 6:
                                    iArr2 = StrictModeUtils$VmPolicyBuilderCompatS.createIntArray(parcel, readInt8);
                                    break;
                                case 7:
                                    bArr4 = StrictModeUtils$VmPolicyBuilderCompatS.createByteArrayArray(parcel, readInt8);
                                    break;
                                case 8:
                                    z4 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt8);
                                    break;
                                case 9:
                                    experimentTokensArr = (ExperimentTokens[]) StrictModeUtils$VmPolicyBuilderCompatS.createTypedArray(parcel, readInt8, ExperimentTokens.CREATOR);
                                    break;
                                case 10:
                                default:
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt8);
                                    break;
                                case 11:
                                    logVerifierResultParcelable = (LogVerifierResultParcelable) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt8, LogVerifierResultParcelable.CREATOR);
                                    break;
                                case 12:
                                    strArr2 = StrictModeUtils$VmPolicyBuilderCompatS.createStringArray(parcel, readInt8);
                                    break;
                                case 13:
                                    i12 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt8);
                                    break;
                                case 14:
                                    dataCollectionIdentifierParcelable = (DataCollectionIdentifierParcelable) StrictModeUtils$VmPolicyBuilderCompatS.createParcelable(parcel, readInt8, DataCollectionIdentifierParcelable.CREATOR);
                                    break;
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader8);
                        return new LogEventParcelable(playLoggerContext, bArr3, iArr, strArr, iArr2, bArr4, z4, experimentTokensArr, logVerifierResultParcelable, strArr2, i12, dataCollectionIdentifierParcelable);
                    case 16:
                        int validateObjectHeader9 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        while (parcel.dataPosition() < validateObjectHeader9) {
                            int readInt9 = parcel.readInt();
                            if (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt9) != 1) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt9);
                            } else {
                                arrayList2 = StrictModeUtils$VmPolicyBuilderCompatS.createTypedList(parcel, readInt9, LogErrorParcelable.CREATOR);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader9);
                        return new BatchedLogErrorParcelable(arrayList2);
                    case 17:
                        int validateObjectHeader10 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        int i13 = 0;
                        int i14 = 0;
                        while (parcel.dataPosition() < validateObjectHeader10) {
                            int readInt10 = parcel.readInt();
                            int fieldId6 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt10);
                            if (fieldId6 != 1) {
                                if (fieldId6 != 2) {
                                    if (fieldId6 != 3) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt10);
                                    } else {
                                        i14 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt10);
                                    }
                                } else {
                                    i13 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt10);
                                }
                            } else {
                                i = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt10);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader10);
                        return new DataCollectionIdentifierParcelable(i, i13, i14);
                    case 18:
                        int validateObjectHeader11 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        int i15 = 0;
                        while (parcel.dataPosition() < validateObjectHeader11) {
                            int readInt11 = parcel.readInt();
                            int fieldId7 = StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt11);
                            if (fieldId7 != 1) {
                                if (fieldId7 != 2) {
                                    if (fieldId7 != 3) {
                                        StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt11);
                                    } else {
                                        i15 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt11);
                                    }
                                } else {
                                    i2 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt11);
                                }
                            } else {
                                str = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt11);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader11);
                        return new LogErrorParcelable(str, i2, i15);
                    case 19:
                        int validateObjectHeader12 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        while (parcel.dataPosition() < validateObjectHeader12) {
                            int readInt12 = parcel.readInt();
                            if (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt12) != 1) {
                                StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt12);
                            } else {
                                z = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt12);
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader12);
                        return new LogVerifierResultParcelable(z);
                    default:
                        int validateObjectHeader13 = StrictModeUtils$VmPolicyBuilderCompatS.validateObjectHeader(parcel);
                        boolean z5 = true;
                        int i16 = 0;
                        int i17 = 0;
                        boolean z6 = false;
                        int i18 = 0;
                        boolean z7 = false;
                        int i19 = 0;
                        String str15 = null;
                        String str16 = null;
                        String str17 = null;
                        Integer num = null;
                        while (parcel.dataPosition() < validateObjectHeader13) {
                            int readInt13 = parcel.readInt();
                            switch (StrictModeUtils$VmPolicyBuilderCompatS.getFieldId(readInt13)) {
                                case 2:
                                    str15 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt13);
                                    break;
                                case 3:
                                    i16 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                                    break;
                                case 4:
                                    i17 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                                    break;
                                case 5:
                                    str16 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt13);
                                    break;
                                case 6:
                                default:
                                    StrictModeUtils$VmPolicyBuilderCompatS.skipUnknownField(parcel, readInt13);
                                    break;
                                case 7:
                                    z5 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt13);
                                    break;
                                case 8:
                                    str17 = StrictModeUtils$VmPolicyBuilderCompatS.createString(parcel, readInt13);
                                    break;
                                case 9:
                                    z6 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt13);
                                    break;
                                case 10:
                                    i18 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                                    break;
                                case 11:
                                    int readSize2 = StrictModeUtils$VmPolicyBuilderCompatS.readSize(parcel, readInt13);
                                    if (readSize2 == 0) {
                                        num = null;
                                        break;
                                    } else {
                                        StrictModeUtils$VmPolicyBuilderCompatS.enforceSize$ar$ds(parcel, readSize2, 4);
                                        num = Integer.valueOf(parcel.readInt());
                                        break;
                                    }
                                case 12:
                                    z7 = StrictModeUtils$VmPolicyBuilderCompatS.readBoolean(parcel, readInt13);
                                    break;
                                case 13:
                                    i19 = StrictModeUtils$VmPolicyBuilderCompatS.readInt(parcel, readInt13);
                                    break;
                            }
                        }
                        StrictModeUtils$VmPolicyBuilderCompatS.ensureAtEnd(parcel, validateObjectHeader13);
                        return new PlayLoggerContext(str15, i16, i17, str16, z5, str17, z6, i18, num, z7, i19);
                }
            }

            @Override // android.os.Parcelable.Creator
            public final /* synthetic */ Object[] newArray(int i) {
                switch (this.switching_field) {
                    case 0:
                        return new SavedState[i];
                    case 1:
                        return new SwipeRefreshLayout.SavedState[i];
                    case 2:
                        return new BrailleDisplayProperties[i];
                    case 3:
                        return new BrailleInputEvent[i];
                    case 4:
                        return new BrailleKeyBinding[i];
                    case 5:
                        return new AIFeature[i];
                    case 6:
                        return new ImageDescriptionRequest[i];
                    case 7:
                        return new ImageDescriptionResult[i];
                    case 8:
                        return new FlexboxLayout.LayoutParams[i];
                    case 9:
                        return new FlexboxLayoutManager.LayoutParams[i];
                    case 10:
                        return new FlexboxLayoutManager.SavedState[i];
                    case 11:
                        return new TokenData[i];
                    case 12:
                        return new ProxyRequest[i];
                    case 13:
                        return new ProxyResponse[i];
                    case 14:
                        return new GoogleSignInAccount[i];
                    case 15:
                        return new LogEventParcelable[i];
                    case 16:
                        return new BatchedLogErrorParcelable[i];
                    case 17:
                        return new DataCollectionIdentifierParcelable[i];
                    case 18:
                        return new LogErrorParcelable[i];
                    case 19:
                        return new LogVerifierResultParcelable[i];
                    default:
                        return new PlayLoggerContext[i];
                }
            }
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mChecked = ((Boolean) parcel.readValue(null)).booleanValue();
            this.mVisible = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        public final String toString() {
            return "MainSwitchBar.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + this.mChecked + " visible=" + this.mVisible + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.mChecked));
            parcel.writeValue(Boolean.valueOf(this.mVisible));
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public MainSwitchBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MainSwitchBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mSwitchChangeListeners = new ArrayList();
        LayoutInflater.from(context).inflate(R.layout.settingslib_main_switch_bar, this);
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
            this.mBackgroundActivatedColor = obtainStyledAttributes.getColor(0, 0);
            this.mBackgroundColor = context.getColor(R.color.material_grey_600);
            obtainStyledAttributes.recycle();
        }
        setFocusable(true);
        setClickable(true);
        this.mFrameView = findViewById(R.id.frame);
        this.mTextView = (TextView) findViewById(R.id.switch_text);
        this.mSwitch = (Switch) findViewById(android.R.id.switch_widget);
        addOnSwitchChangeListener(new BrailleDisplaySettingsFragment.AnonymousClass1(this, 1));
        if (this.mSwitch.getVisibility() == 0) {
            this.mSwitch.setOnCheckedChangeListener(this);
        }
        setChecked(this.mSwitch.isChecked());
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, 0, 0);
            setTitle(obtainStyledAttributes2.getText(4));
            obtainStyledAttributes2.recycle();
        }
        setBackground(this.mSwitch.isChecked());
    }
}
