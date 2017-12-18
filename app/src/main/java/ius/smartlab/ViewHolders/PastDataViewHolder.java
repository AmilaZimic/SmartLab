package ius.smartlab.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ius.smartlab.POJO.PastData;
import ius.smartlab.R;

/**
 * Created by Džana on 08/12/2017.
 */

public class PastDataViewHolder extends RecyclerView.ViewHolder {

    private TextView temp;
    private TextView light;
    private TextView doors;
    private TextView time;

    public PastDataViewHolder(View itemView) {
        super(itemView);
        temp = (TextView) itemView.findViewById(R.id.temp);
        light = (TextView) itemView.findViewById(R.id.light);
        doors=(TextView)itemView.findViewById(R.id.doors);
        time=(TextView)itemView.findViewById(R.id.time);
    }

    public void bindAd(PastData ad) {
        temp.setText(ad.getTemperature().toString());
        light.setText(ad.getLight().toString());
        doors.setText(ad.getDoors().toString());
        time.setText("Lab status at "+ad.getTime().toString()+" 'o clock");

    }

}
