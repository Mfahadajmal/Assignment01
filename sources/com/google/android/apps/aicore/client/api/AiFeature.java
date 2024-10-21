package com.google.android.apps.aicore.client.api;

import com.google.android.apps.aicore.aidl.AIFeature;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AiFeature {
    private final int id;
    private final String modelName;
    private final String name;
    private final int type;
    private final int variant;

    public AiFeature() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AiFeature) {
            AiFeature aiFeature = (AiFeature) obj;
            if (this.name.equals(aiFeature.getName()) && this.modelName.equals(aiFeature.getModelName()) && this.type == aiFeature.getType() && this.variant == aiFeature.getVariant() && this.id == aiFeature.getId()) {
                return true;
            }
        }
        return false;
    }

    public final int getId() {
        return this.id;
    }

    public final String getModelName() {
        return this.modelName;
    }

    public final String getName() {
        return this.name;
    }

    public final int getType() {
        return this.type;
    }

    public final int getVariant() {
        return this.variant;
    }

    public final int hashCode() {
        return ((((((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.modelName.hashCode()) * 1000003) ^ this.type) * 1000003) ^ this.variant) * 1000003) ^ this.id;
    }

    public final AIFeature toParcelableAiFeature() {
        return new AIFeature(getName(), getModelName(), getType(), getVariant(), getId());
    }

    public final String toString() {
        return "AiFeature{name=" + this.name + ", modelName=" + this.modelName + ", type=" + this.type + ", variant=" + this.variant + ", id=" + this.id + "}";
    }

    public AiFeature(String str, String str2, int i, int i2, int i3) {
        this();
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.name = str;
        if (str2 != null) {
            this.modelName = str2;
            this.type = i;
            this.variant = i2;
            this.id = i3;
            return;
        }
        throw new NullPointerException("Null modelName");
    }
}
