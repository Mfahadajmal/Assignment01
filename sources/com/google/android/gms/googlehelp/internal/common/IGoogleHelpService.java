package com.google.android.gms.googlehelp.internal.common;

import android.graphics.Bitmap;
import android.os.IInterface;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.InProductHelp;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGoogleHelpService extends IInterface {
    void processGoogleHelpAndPip(GoogleHelp googleHelp, Bitmap bitmap, IGoogleHelpCallbacks iGoogleHelpCallbacks);

    void processInProductHelpAndPip(InProductHelp inProductHelp, Bitmap bitmap, IGoogleHelpCallbacks iGoogleHelpCallbacks);
}
