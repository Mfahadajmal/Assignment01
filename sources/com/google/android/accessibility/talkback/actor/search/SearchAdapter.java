package com.google.android.accessibility.talkback.actor.search;

import android.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.datepicker.YearGridAdapter;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SearchAdapter extends RecyclerView.Adapter {
    public List searchResult = Collections.emptyList();
    public View.OnClickListener viewHolderClickListener;

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.searchResult.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((TextView) ((YearGridAdapter.ViewHolder) viewHolder).YearGridAdapter$ViewHolder$ar$textView).setText((CharSequence) this.searchResult.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_list_item_1, viewGroup, false);
        textView.setTextColor(-1);
        textView.setOnClickListener(this.viewHolderClickListener);
        return new YearGridAdapter.ViewHolder(textView, (byte[]) null);
    }
}
