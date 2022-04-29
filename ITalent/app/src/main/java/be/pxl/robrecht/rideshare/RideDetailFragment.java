package be.pxl.robrecht.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Fragment;

import com.squareup.picasso.Picasso;

import be.pxl.robrecht.rideshare.data.model.ITalentActivity;

public class RideDetailFragment extends Fragment {
    private ITalentActivity activity;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private ImageView media1;
    private ImageView media2;
    private ImageView media3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ride_detail_fragment, container, false);
        int placeHolder = R.drawable.space_invader;
        media1 = view.findViewById(R.id.aboutMePhoto);
        media2 = view.findViewById(R.id.detailMedia2);
        media3 = view.findViewById(R.id.detailMedia3);

        Bundle bundle = getArguments();
        TextView activityTitle = (TextView) view.findViewById(R.id.detailActivityTitle);
        TextView date = (TextView) view.findViewById(R.id.detailActivityDate);
        TextView location = (TextView) view.findViewById(R.id.detailActivityLocation);
        TextView duration = (TextView) view.findViewById(R.id.detailActivityDuration);
        TextView description = (TextView) view.findViewById(R.id.detailActivityDescription);
        TextView domain = (TextView) view.findViewById(R.id.detailActivityDomain);
        TextView longForm = (TextView) view.findViewById(R.id.detailActivityLongForm);
        Intent i = getActivity().getIntent();
        activity = (ITalentActivity) i.getSerializableExtra("clicked_activity");
        if(activity != null){
            activityTitle.setText(activity.getTitle());
            date.setText(activity.getDate());
            location.setText(activity.getLocation());
            duration.setText(activity.getDuration());
            description.setText(activity.getDescription());
            domain.setText(activity.getDomain());
            if (!activity.getLongForm().equals("none")){
                longForm.setText(activity.getLongForm());

            }else{
                longForm.setVisibility(View.GONE);
            }
            if(!activity.getMedia().get(0).equals("none")){
                imageUrl1 = activity.getMedia().get(0);
                Log.d("firebase",imageUrl1);
                Picasso.with(getContext()).load(imageUrl1).into(media1);

            }else{
                media1.setVisibility(View.GONE);
            }
            if(!activity.getMedia().get(1).equals("none")){
                imageUrl2 = activity.getMedia().get(1);
                Log.d("firebase",imageUrl2);
                Picasso.with(getContext()).load(imageUrl2).into(media2);

            }else{
                media2.setVisibility(View.GONE);
            }
            if(!activity.getMedia().get(2).equals("none")){
                imageUrl3 = activity.getMedia().get(2);
                Log.d("firebase",imageUrl3);
                Picasso.with(getContext()).load(imageUrl3).into(media3);

            }else{
                media3.setVisibility(View.GONE);
            }




        }
        return view;
    }
}
