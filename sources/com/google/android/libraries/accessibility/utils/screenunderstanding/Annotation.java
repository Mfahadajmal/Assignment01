package com.google.android.libraries.accessibility.utils.screenunderstanding;

import android.graphics.Rect;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protos.research.socrates.Visual$Rectangular;
import com.google.protos.research.socrates.Visual$UIComponent;
import com.google.protos.research.socrates.VisualSelectionDescriptorOuterClass$Point2D;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Annotation {
    private final Rect bounds;
    private final float score;
    public final Visual$UIComponent.Type type;

    public Annotation(Annotation annotation) {
        this.bounds = annotation.getBounds();
        this.score = annotation.score;
        this.type = annotation.type;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Annotation)) {
            return false;
        }
        Annotation annotation = (Annotation) obj;
        if (Float.compare(annotation.score, this.score) == 0 && Objects.equals(this.bounds, annotation.bounds) && this.type == annotation.type) {
            return true;
        }
        return false;
    }

    public final Rect getBounds() {
        return new Rect(this.bounds);
    }

    public final int hashCode() {
        return Objects.hash(this.bounds, Float.valueOf(this.score), this.type);
    }

    public final String toString() {
        Rect rect = this.bounds;
        return "Annotation{type=" + this.type.name() + ", bounds=" + rect.toShortString() + ", score=" + this.score + "}";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Annotation(Visual$UIComponent visual$UIComponent) {
        Visual$UIComponent.Type type;
        ContextDataProvider.checkState(visual$UIComponent.predictedType_.size() > 0);
        Visual$Rectangular visual$Rectangular = visual$UIComponent.boundingBox_;
        VisualSelectionDescriptorOuterClass$Point2D visualSelectionDescriptorOuterClass$Point2D = (visual$Rectangular == null ? Visual$Rectangular.DEFAULT_INSTANCE : visual$Rectangular).point0_;
        int round = Math.round((visualSelectionDescriptorOuterClass$Point2D == null ? VisualSelectionDescriptorOuterClass$Point2D.DEFAULT_INSTANCE : visualSelectionDescriptorOuterClass$Point2D).x_);
        Visual$Rectangular visual$Rectangular2 = visual$UIComponent.boundingBox_;
        VisualSelectionDescriptorOuterClass$Point2D visualSelectionDescriptorOuterClass$Point2D2 = (visual$Rectangular2 == null ? Visual$Rectangular.DEFAULT_INSTANCE : visual$Rectangular2).point0_;
        int round2 = Math.round((visualSelectionDescriptorOuterClass$Point2D2 == null ? VisualSelectionDescriptorOuterClass$Point2D.DEFAULT_INSTANCE : visualSelectionDescriptorOuterClass$Point2D2).y_);
        Visual$Rectangular visual$Rectangular3 = visual$UIComponent.boundingBox_;
        VisualSelectionDescriptorOuterClass$Point2D visualSelectionDescriptorOuterClass$Point2D3 = (visual$Rectangular3 == null ? Visual$Rectangular.DEFAULT_INSTANCE : visual$Rectangular3).point1_;
        int round3 = Math.round((visualSelectionDescriptorOuterClass$Point2D3 == null ? VisualSelectionDescriptorOuterClass$Point2D.DEFAULT_INSTANCE : visualSelectionDescriptorOuterClass$Point2D3).x_);
        Visual$Rectangular visual$Rectangular4 = visual$UIComponent.boundingBox_;
        VisualSelectionDescriptorOuterClass$Point2D visualSelectionDescriptorOuterClass$Point2D4 = (visual$Rectangular4 == null ? Visual$Rectangular.DEFAULT_INSTANCE : visual$Rectangular4).point1_;
        Rect rect = new Rect(round, round2, round3, Math.round((visualSelectionDescriptorOuterClass$Point2D4 == null ? VisualSelectionDescriptorOuterClass$Point2D.DEFAULT_INSTANCE : visualSelectionDescriptorOuterClass$Point2D4).y_));
        this.bounds = rect;
        rect.sort();
        this.score = ((Visual$UIComponent.PredictedType) visual$UIComponent.predictedType_.get(0)).score_;
        Visual$UIComponent.PredictedType predictedType = (Visual$UIComponent.PredictedType) visual$UIComponent.predictedType_.get(0);
        if (predictedType.typeOfCase_ == 3) {
            type = Visual$UIComponent.Type.forNumber(((Integer) predictedType.typeOf_).intValue());
            if (type == null) {
                type = Visual$UIComponent.Type.UNRECOGNIZED;
            }
        } else {
            type = Visual$UIComponent.Type.UNKNOWN_COMPONENT_TYPE;
        }
        this.type = type;
    }
}
