package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Task {
    public void addOnCompleteListener$ar$ds(OnCompleteListener onCompleteListener) {
        throw null;
    }

    public void addOnCompleteListener$ar$ds$6dfdfa2c_0(Executor executor, OnCompleteListener onCompleteListener) {
        throw null;
    }

    public abstract void addOnFailureListener$ar$ds(OnFailureListener onFailureListener);

    public abstract void addOnFailureListener$ar$ds$7efc8a85_0(Executor executor, OnFailureListener onFailureListener);

    public abstract Task addOnSuccessListener(OnSuccessListener onSuccessListener);

    public abstract void addOnSuccessListener$ar$ds(Executor executor, OnSuccessListener onSuccessListener);

    public abstract Exception getException();

    public abstract Object getResult();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();
}
