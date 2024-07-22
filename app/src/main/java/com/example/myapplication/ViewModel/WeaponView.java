package com.example.myapplication.ViewModel;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class WeaponView extends RelativeLayout {
    private ImageView weaponImageView;

    public WeaponView(Context context, float x, float y, int weaponResourceId) {
        super(context);

        weaponImageView = new ImageView(context);

        LayoutParams params = new LayoutParams(48, 108);
        params.leftMargin = (int) x;
        params.topMargin = (int) y;
        weaponImageView.setLayoutParams(params);

        addView(weaponImageView);

        // Set the initial GIF based on the default direction (e.g., right)
        loadWeaponGif(R.drawable.arrow_right);

        weaponImageView.setVisibility(View.GONE);
    }

    public void updatePosition(float newX, float newY, int weaponResourceId) {
        int arrowWidth;
        int arrowHeight;

        // Adjust the arrow size based on the GIF
        if (weaponResourceId == R.drawable.arrow_up || weaponResourceId == R.drawable.arrow_down) {
            // Vertical arrow
            arrowWidth = 48;
            arrowHeight = 108;
        } else {
            // Horizontal arrow
            arrowWidth = 108;
            arrowHeight = 48;
        }

        LayoutParams params = new LayoutParams(arrowWidth, arrowHeight);
        params.leftMargin = (int) newX;
        params.topMargin = (int) newY;
        weaponImageView.setLayoutParams(params);

        loadWeaponGif(weaponResourceId);
    }

    public void startWeaponAnimation() {
        weaponImageView.setVisibility(View.VISIBLE);
    }

    public void stopWeaponAnimation() {
        // Hide the weapon view and stop the animation
        weaponImageView.setVisibility(View.GONE);
    }

    public void setDirection(int direction) {
        // Update the weapon GIF based on the player's direction
        switch (direction) {
            case 0: // Up
                loadWeaponGif(R.drawable.arrow_up);
                break;
            case 1: // Down
                loadWeaponGif(R.drawable.arrow_down);
                break;
            case 2: // Left
                loadWeaponGif(R.drawable.arrow_left);
                break;
            case 3: // Right
                loadWeaponGif(R.drawable.arrow_right);
                break;
        }
    }

    private void loadWeaponGif(int resourceId) {
        // Load the GIF using Glide
        Glide.with(getContext())
                .asGif()
                .load(resourceId)
                .into(weaponImageView);
    }
}