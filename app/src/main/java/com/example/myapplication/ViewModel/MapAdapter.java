package com.example.myapplication.ViewModel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.myapplication.R;
/**
 * Responsible for serving as an adapter for displaying a map.
 * Connects the MapLayout and the GridView that displays the map.
 * */
public class  MapAdapter extends BaseAdapter {

    private Context context;
    private int[][] mapLayout;

    public MapAdapter(Context context, int[][] mapLayout) {
        this.context = context;
        this.mapLayout = mapLayout;
    }

    @Override
    public int getCount() {
        return mapLayout.length * mapLayout[0].length;
    }

    @Override
    public Object getItem(int position) {
        // Implement if needed
        return null;
    }

    @Override
    public long getItemId(int position) {
        // Implement if needed
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(
                    GridView.LayoutParams.MATCH_PARENT,
                    GridView.LayoutParams.MATCH_PARENT
            ));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        // Set the image resource based on the mapLayout data
        int row = position / mapLayout[0].length;
        int col = position % mapLayout[0].length;
        int tileResourceId = getTileResourceId(mapLayout[row][col]);
        imageView.setImageResource(tileResourceId);

        return imageView;
    }

    // Define a method to map your tile values to resource IDs
    private int getTileResourceId(int tileValue) {
        int resourceId;
        int floorTileResourceId = R.drawable.floor_tile;
        int wallTileResourceId = R.drawable.wall_tile;
        int exitTileResourceId = R.drawable.exit_tile;

        // Mapping tile values to resource IDs
        switch (tileValue) {
        case 0:
            resourceId = floorTileResourceId;
            break;
        case 1:
            resourceId = wallTileResourceId;
            break;
        case 2:
            resourceId = exitTileResourceId;
            break;
        default:
            resourceId = 0;
            break;
        }

        return resourceId;
    }
}


