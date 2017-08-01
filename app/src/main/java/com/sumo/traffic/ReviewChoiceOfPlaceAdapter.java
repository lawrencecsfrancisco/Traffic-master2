package com.sumo.traffic;

/**
 * Created by lawre on 7/13/2017.
 */

/**
 * Created by william on 5/29/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BW-33105 on 5/15/2017.
 */


public class ReviewChoiceOfPlaceAdapter extends RecyclerView.Adapter<ReviewChoiceOfPlaceAdapter.ViewHolder> {
    int lastPosition = -1;
    private List<placeitem> listStaffs;
    private Context context;
    public static final String record = "record";
    String s;


    public ReviewChoiceOfPlaceAdapter(List<placeitem> listStaffs, Context context) {
        // getting all the staffs
        this.listStaffs = listStaffs;
        this.context = context;


    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public ReviewChoiceOfPlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.placeitem, parent, false);
        return new ReviewChoiceOfPlaceAdapter.ViewHolder(v);
    }
    @Override

    public void onBindViewHolder(ReviewChoiceOfPlaceAdapter.ViewHolder holder, final int position) {
        final placeitem listStaff = listStaffs.get(position);

        holder.name.setText(listStaff.getname());
        holder.type.setText(listStaff.gettype());




        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


/*
        if(listStaff.getPosition().matches("1")) {
            holder.textViewPosition.setText("Manager");
        }
        else if(listStaff.getPosition().matches("2")) {
            holder.textViewPosition.setText("Staff");
        }
        else if(listStaff.getPosition().matches("3")) {
            holder.textViewPosition.setText("Admin");
        }*/
        holder.linearLayoutStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         /*       //Toast.makeText(context, "you clicked"+listStaff.getUser_id(), Toast.LENGTH_LONG).show();
                context = v.getContext();
                Intent intent = new Intent(context, ViewManager.class );
                intent.putExtra(Config.EMP_ID, listStaff.getId());
                s = "Viewed Employee: " + listStaff.getLastname().toString();
                log();
                context.startActivity(intent);
            *//*    ((Activity)context).finish();*/
            }
        });

    }





    @Override
    public int getItemCount() {
        return listStaffs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView type;


        public LinearLayout linearLayoutStaff;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            type = (TextView)itemView.findViewById(R.id.type);


            linearLayoutStaff = (LinearLayout) itemView.findViewById(R.id.linearLayoutStaff);
        }
    }
}