package com.abdelhakimrafik.contactapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.abdelhakimrafik.contactapp.model.Contact;

import java.util.ArrayList;

public class SwipeListener implements View.OnTouchListener {

    ListView list;
    private GestureDetector gestureDetector;
    private Context context;
    private ArrayList<Contact> listContact;

    public SwipeListener(Context ctx, ListView list, ArrayList<Contact> listContact) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        context = ctx;
        this.list = list;
        this.listContact = listContact;
    }

    public SwipeListener() {
        super();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onSwipeRight(int pos) {
        //Do what you want after swiping left to right
        Log.e("tttt", "Mak call");
        Uri number = Uri.parse("tel:" + listContact.get(pos).getPhone());

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(number);
        context.startActivity(intent);
    }

    public void onSwipeLeft(int pos) {
        //Do what you want after swiping right to left
        Log.e("tttt", "Send message");
        Uri number = Uri.parse("sms:" + listContact.get(pos).getPhone());

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(number);
        context.startActivity(intent);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        private int getPostion(MotionEvent e1) {
            return list.pointToPosition((int) e1.getX(), (int) e1.getY());
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY)
                    && Math.abs(distanceX) > SWIPE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight(getPostion(e1));
                else
                    onSwipeLeft(getPostion(e1));
                return true;
            }
            return false;
        }

    }
}
