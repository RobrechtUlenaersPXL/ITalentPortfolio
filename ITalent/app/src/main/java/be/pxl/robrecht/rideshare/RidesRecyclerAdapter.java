package be.pxl.robrecht.rideshare;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import be.pxl.robrecht.rideshare.data.model.ITalentActivity;
import be.pxl.robrecht.rideshare.data.model.Ride;

public class RidesRecyclerAdapter extends RecyclerView.Adapter<RidesRecyclerAdapter.RidesViewHolder> {
    private ArrayList<ITalentActivity> activities;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseDataReference;
    private ChildEventListener mChildListener;
    private Context context;
    private String imageUrl;

    public RidesRecyclerAdapter(Context ct){
        context = ct;
        FireBaseUtil.openFireBaseReference("Activities",(Activity) context);
        mFirebaseDatabase = FireBaseUtil.mFireBaseDatabase;
        mFirebaseDataReference = FireBaseUtil.mDatabaseReference;
        activities = FireBaseUtil.activities;

        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ITalentActivity newActivity = snapshot.getValue(ITalentActivity.class);
                newActivity.setId(snapshot.getKey());
                activities.add(newActivity);
                notifyItemInserted(activities.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mFirebaseDataReference.addChildEventListener(mChildListener);


    }
    @NonNull
    @Override
    public RidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.rides_recycler_view_list_item,parent,false);
        return new RidesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RidesViewHolder holder, int position) {
        ITalentActivity tempActivity = activities.get(position);

        holder.activityTitle.setText(tempActivity.getTitle());
        holder.location.setText(tempActivity.getLocation());
        holder.duration.setText(tempActivity.getDuration());
        holder.date.setText(tempActivity.getDate());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view , int pos){
                ITalentActivity clickActivity = activities.get(pos);
                FragmentManager fragmentManager = ((Activity)context ).getFragmentManager();


                RideDetailFragment detailFragment = (RideDetailFragment) fragmentManager.findFragmentById(R.id.fragmentRideDetail);
                if (detailFragment != null && detailFragment.isVisible()){
                    RideDetailFragment newRideDetailFragment = new RideDetailFragment();
                    ((FragmentActivity)context).getIntent().putExtra("clicked_activity",clickActivity);
                    FragmentTransaction transaction = ((Activity)context ).getFragmentManager().beginTransaction();
                    transaction.replace(detailFragment.getId(), newRideDetailFragment,null);
                    transaction.commit();
                }else {
                    Intent intent = new Intent(context , RideDetailActivity.class);
                    intent.putExtra("clicked_activity",clickActivity);
                    context.startActivity(intent);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class RidesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        ItemClickListener itemClickListener;
        TextView activityTitle,location,duration,date;
        public RidesViewHolder(@NonNull View itemView) {
            super(itemView);
            activityTitle = itemView.findViewById(R.id.activityTitle);
            location = itemView.findViewById(R.id.activityLocation);
            duration = itemView.findViewById(R.id.activityDuration);
            date = itemView.findViewById(R.id.activityDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view,getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener itemclicker){
            this.itemClickListener = itemclicker;
        }
    }
}
