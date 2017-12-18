package ius.smartlab.Helpers;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ius.smartlab.POJO.PastData;
import ius.smartlab.R;
import ius.smartlab.ViewHolders.PastDataViewHolder;

/**
 * Created by Džana on 08/12/2017.
 */

public class PastDataAdapter extends RecyclerView.Adapter<PastDataViewHolder> {
    ArrayList<PastData> mPastData;



    public PastDataAdapter(ArrayList<PastData> mAds)
    {
        this.mPastData = mAds;
    }

    @Override
    public PastDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_data_item, parent, false);
        return new PastDataViewHolder(inflatedView);
    }

    public void onBindViewHolder(PastDataViewHolder holder, int position) {
        final PastData mData = mPastData.get(position);
        holder.bindAd(mData);
    }

    @Override
    public int getItemCount() {
        return mPastData.size();
    }
}

