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
import com.mobile.hcms.model.AssigmentModel;
import com.mobile.hcms.model.EmployeeModel;
import com.viethoa.RecyclerViewFastScroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import timber.log.Timber;

/**
 * Created by rioswarawan on 7/13/16.
 */
public abstract class AssignmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<AssigmentModel> data;
    private LayoutInflater inflater;

    public AssignmentAdapter(Context context, List<AssigmentModel> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_assignment, parent, false);
        return new BodyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AssigmentModel model = data.get(position);
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

    class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView startdate;
        TextView enddate;
        TextView organization;
        TextView location;
        LinearLayout layout;

        public BodyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            enddate = (TextView) itemView.findViewById(R.id.enddate);
            startdate = (TextView) itemView.findViewById(R.id.startdate);
            organization = (TextView) itemView.findViewById(R.id.organization);
            location = (TextView) itemView.findViewById(R.id.location);
        }

        public void populate(final AssigmentModel model, final int position) {
            name.setText(model.getName() + " - " + model.getType());
            startdate.setText(model.getStartdate());
            enddate.setText(model.getEnddate());
            organization.setText(model.getOrganization());
            location.setText(model.getLocation());

            layout.setOnClickListener((View view) -> onMessageSelected(model));
        }
    }

    public abstract void onMessageSelected(AssigmentModel position);
}
