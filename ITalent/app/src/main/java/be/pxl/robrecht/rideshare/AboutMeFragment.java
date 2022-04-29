package be.pxl.robrecht.rideshare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutMeFragment extends Fragment {
    private ImageView profilePic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_me_fragment, container, false);
        int placeHolder = R.drawable.profile_pic;
        profilePic = view.findViewById(R.id.aboutMePhoto);
        profilePic.setImageResource(placeHolder);

        return view;
    }
}
