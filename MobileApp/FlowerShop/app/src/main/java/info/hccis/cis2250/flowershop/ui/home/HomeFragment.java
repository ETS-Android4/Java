package info.hccis.cis2250.flowershop.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import info.hccis.cis2250.flowershop.R;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import static info.hccis.cis2250.flowershop.R.id.share_button;

/**
 * Class to manage Home Fragment
 * CarouselView code sourced from //https://lobothijau.medium.com/create-carousel-easily-in-android-app-with-carouselview-6cbf5ef500a9
 *
 * @author Kendall Fowler
 * @since 27-Feb-2021
 */
public class HomeFragment extends Fragment {

    // Share button java
    // code sourced from https://www.youtube.com/watch?v=U39RsxrWhIA
    private Button shareButton;
    private Intent shareIntent;
    private String shareBody = "Kendall's app is the best, check it out!";

    //private HomeViewModel homeViewModel;
    private CarouselView carouselView;
    /*
    Images sourced from https://www.pexels.com/photo/selective-focus-photography-of-pink-and-red-flowers-931166/
    https://www.pexels.com/photo/pink-and-white-flowers-in-blue-glass-vase-3981634/
    https://www.pexels.com/photo/white-red-orange-and-brown-flowers-931168/
    https://www.pexels.com/photo/pink-rose-flower-bouquet-931167/
     */
    int[] sampleImages = {R.drawable.flowers, R.drawable.flowers_2, R.drawable.flowers_3, R.drawable.flowers_4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        carouselView = view.findViewById(R.id.carousel_View);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        //Toast Code
        Toast.makeText(getActivity(),"Welcome Home!", Toast.LENGTH_LONG).show();


        //Share Button Code
        shareButton = view.findViewById(share_button);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Kendall's App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });


        return view;
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }


    };





}


