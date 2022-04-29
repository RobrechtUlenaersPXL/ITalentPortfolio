package be.pxl.robrecht.rideshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import be.pxl.robrecht.rideshare.data.model.Ride;

public class RidesListFragment extends Fragment {

    RecyclerView recyclerview;
    ArrayList<Ride> rides;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFireBaseDatabase.getReference().child("Activities");
        View view = inflater.inflate(R.layout.activity_list_view_rides_fragment,container,false);
        recyclerview = view.findViewById(R.id.rides_recycler_view);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RidesRecyclerAdapter ridesRecyclerAdapter = new RidesRecyclerAdapter(getContext());
        recyclerview.setAdapter(ridesRecyclerAdapter);
        return view;
    }

}
