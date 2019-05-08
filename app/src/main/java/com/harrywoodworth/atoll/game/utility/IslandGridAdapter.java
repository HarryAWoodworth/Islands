package com.harrywoodworth.atoll.game.utility;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.harrywoodworth.atoll.game.island.Island;

public class IslandGridAdapter extends BaseAdapter {

    private final Context context;
    private final char[] islandCode;

    public IslandGridAdapter(Context _context, Island island) {
        context = _context;
        islandCode = island.toCharArray();
    }

    @Override
    public int getCount() {
        return islandCode.length;
    }

    @Override
    public Object getItem(int position) {
        return islandCode[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView spaceView = new TextView(context);
        String setText = "" +islandCode[position];
        spaceView.setText(setText);
        return spaceView;
    }
}
