package com.google.android.libraries.surveys.internal.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.util.Pair;
import com.google.android.marvin.talkback.R;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemInfoItemsAdapter extends RecyclerView.Adapter {
    private List items;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        Pair pair = (Pair) this.items.get(i);
        ((TextView) viewHolder2.view.findViewById(R.id.survey_system_info_item_key)).setText((CharSequence) pair.first);
        ((TextView) viewHolder2.view.findViewById(R.id.survey_system_info_item_value)).setText((CharSequence) pair.second);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.survey_system_info_item, viewGroup, false));
    }

    public final void setItems(List list) {
        this.items = list;
        notifyDataSetChanged();
    }
}
