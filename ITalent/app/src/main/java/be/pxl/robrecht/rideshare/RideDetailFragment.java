package be.pxl.robrecht.rideshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Fragment;

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
        media1 = view.findViewById(R.id.detailMedia1);
        //media2 = view.findViewById(R.id.detailMedia2);
        //media3 = view.findViewById(R.id.detailMedia3);

        Bundle bundle = getArguments();
        TextView activityTitle = (TextView) view.findViewById(R.id.detailActivityTitle);
        TextView date = (TextView) view.findViewById(R.id.detailActivityDate);
        TextView location = (TextView) view.findViewById(R.id.detailActivityLocation);
        TextView duration = (TextView) view.findViewById(R.id.detailActivityDuration);
        TextView description = (TextView) view.findViewById(R.id.detailActivityDesciption);
        TextView domain = (TextView) view.findViewById(R.id.detailActivityDomain);
        //TextView longForm = (TextView) view.findViewById(R.id.longform);
        media1.setImageResource(placeHolder);
        Intent i = getActivity().getIntent();
        activity = (ITalentActivity) i.getSerializableExtra("clicked_activity");
        if(activity != null){
            activityTitle.setText(activity.getTitle());
            date.setText(activity.getDate());
            location.setText(activity.getLocation());
            duration.setText(activity.getDuration());
            description.setText(activity.getDescription());
            domain.setText(activity.getDomain());
            if (!activity.getLongForm().equals("None")){
                //longForm.setText(activity.getLongForm());
            }
            /*
            imageUrl1 = "";
            String mediaId = "1-1";
            FirebaseDatabase.getInstance().getReference().child("ITalentUrls").child(mediaId).get()
                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {

                            }
                            else {
                                if ( task.getResult().getValue() != null){
                                    imageUrl = String.valueOf(task.getResult().getValue());
                                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                    Picasso.with(getContext()).load(imageUrl).into(userAvatar);
                                }
                                else{
                                    userAvatar.setImageResource(placeHolder);
                                }

                            }
                        }
                    });
            */
        }
        return view;
    }
}
