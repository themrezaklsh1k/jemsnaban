package com.mobile.hcms.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.mobile.hcms.R;
import com.mobile.hcms.model.EmployeeModel;
import com.viethoa.RecyclerViewFastScroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import timber.log.Timber;

/**
 * Created by rioswarawan on 7/13/16.
 */
public abstract class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {//implements RecyclerViewFastScroller.BubbleTextGetter{

    private Context context;
    private List<EmployeeModel> data;
    private LayoutInflater inflater;

    public EmployeeAdapter(Context context, List<EmployeeModel> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        return new BodyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EmployeeModel model = data.get(position);
        BodyViewHolder body = (BodyViewHolder) holder;
        body.populate(model, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /*@Override
    public String getTextToShowInBubble(int pos) {
        if (pos < 0 || pos >= data.size())
            return null;

        String name = data.get(pos).getName();
        if (name == null || name.length() < 1)
            return null;

        Timber.e("getTextToShowInBubble %s", String.valueOf(pos));

        return data.get(pos).getName().substring(0, 1);
    }*/

    class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView total;
        LinearLayout layout;
        ImageView imageView;

        public BodyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nohp);
            total = (TextView) itemView.findViewById(R.id.pesan);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            imageView = (ImageView) itemView.findViewById(R.id.imagedrawable);
        }

        public void populate(final EmployeeModel model, final int position) {
            Random rand = new Random();

            int  n = rand.nextInt(5);

            ArrayList<String> colors = new ArrayList<>();
            colors.add("#E8AE68");
            colors.add("#A57F60");
            colors.add("#DB5A42");
            colors.add("#FFD275");
            colors.add("#E3A587");

            name.setText(model.getName());
            total.setText(model.getBranch() + " - " + model.getJob());

            TextDrawable drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(120)  // width in px
                    .height(120) // height in px
                    .endConfig()
                    .buildRound(String.valueOf(model.getName().charAt(0)), Color.parseColor(colors.get(n)));
            imageView.setImageDrawable(drawable);

            layout.setOnClickListener((View view) -> onMessageSelected(model));
        }
    }

    public abstract void onMessageSelected(EmployeeModel position);
}
