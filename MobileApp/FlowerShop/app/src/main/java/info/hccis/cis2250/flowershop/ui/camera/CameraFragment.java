package info.hccis.cis2250.flowershop.ui.camera;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import info.hccis.cis2250.flowershop.R;

/**
 * Fragment to control Camera
 * code sourced from https://www.youtube.com/watch?v=RaOyw84625w
 * https://www.toptip.ca/2019/03/android-programming-request-permission.html
 * https://stackoverflow.com/questions/36512241/how-to-implement-camera-functionality-in-fragment-in-android
 * @author Kendall Fowler
 * @since 29-MAR-2021
 */
public class CameraFragment extends Fragment {

   private CameraViewModel mViewModel;

   private ImageView photoView;
   private Button photoButton;

    public CameraFragment(){

    }
    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        photoView = view.findViewById(R.id.photo_image_view);
        photoButton = view.findViewById(R.id.photo_button);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);

        }

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }

        });

        return view;
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
       if (requestCode == 100){
           Bitmap captureImage = (Bitmap) data.getExtras().get("data");
           photoView.setImageBitmap(captureImage);
       }
    }



}