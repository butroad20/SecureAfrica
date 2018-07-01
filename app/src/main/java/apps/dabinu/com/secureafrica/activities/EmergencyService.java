package apps.dabinu.com.secureafrica.activities;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import apps.dabinu.com.secureafrica.R;

public class EmergencyService extends Service {
    private WindowManager mWindowManager;
    private View mChatHeadView;

    private Point szWindow = new Point();
    private Point pinnedPoint = new Point();

    public EmergencyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate() {
        super.onCreate();
        //Inflate the chat head layout we created
        mChatHeadView = LayoutInflater.from(this).inflate(R.layout.layout_chat_head, null);

        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }

        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //Specify the chat head position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mChatHeadView, params);

        mWindowManager.getDefaultDisplay(). getSize(szWindow);


//        //Set the close button.
//        ImageView closeButton = (ImageView) mChatHeadView.findViewById(R.id.close_btn);
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //close the service and remove the chat head from the window
//                stopSelf();
//            }
//        });

        //Drag and move chat head using user's touch action.

        final ImageView chatHeadImage = mChatHeadView.findViewById(R.id.chat_head_profile_iv);
        final CardView headHolder = mChatHeadView.findViewById(R.id.headHolder);


        chatHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open the chat conversation click.
                Intent intent = new Intent(EmergencyService.this, MainActivity.class);
//                intent.putExtra(SyncStateContract.Constants.CHAT_HEAD_EXTRA, true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                stopSelf();
            }
        });


        headHolder.setOnTouchListener(new View.OnTouchListener() {
            private int lastAction;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();

                        lastAction = event.getAction();
                        break;

                    case MotionEvent.ACTION_UP:
                        //As we implemented on touch listener with ACTION_MOVE,
                        //we have to check if the previous action was ACTION_DOWN
                        //to identify if the user clicked the view or not.


                        pinnedPoint.x = params.x < (szWindow.x / 2) ? 0 : szWindow.x;

                        pinnedPoint.y = params.y < (szWindow.y / 2) ? 0 : szWindow.y;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                animate(mChatHeadView, params.x, pinnedPoint.x, params.y, params.y);
                            }
                        }, 100);


//                        if (lastAction == MotionEvent.ACTION_DOWN) {
//
//
//                        }else if(lastAction == MotionEvent.ACTION_MOVE){
//
//                            //Checking for the drag position of the chat head for snapping
//
//
//
////                            if (params.x < ((szWindow.x / 2) - 100)){
////                                pinnedPoint.x = 0;
////                            }else{
////                                pinnedPoint.x = szWindow.x;
////                            }
////
////                            if (params.y < (szWindow.y / 2)){
////                                pinnedPoint.y = 0;
////                            }else{
////                                pinnedPoint.y = szWindow.y;
////                            }
//
//                            //Update the layout with new X & Y coordinate
//



                        // }

                        lastAction = event.getAction();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mChatHeadView, params);
                        lastAction = event.getAction();
                        break;
                }
                return true;
            }
        });
    }

    public void animate(final View v, int startX, int endX, int startY, int endY) {

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofInt("x", startX, endX);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofInt("y", startY, endY);

        ValueAnimator translator = ValueAnimator.ofPropertyValuesHolder(pvhX, pvhY);

        translator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) v.getLayoutParams();
                layoutParams.x = (Integer) valueAnimator.getAnimatedValue("x");
                layoutParams.y = (Integer) valueAnimator.getAnimatedValue("y");
                mWindowManager.updateViewLayout(v, layoutParams);
            }
        });

        translator.setDuration(100);
        translator.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatHeadView != null) mWindowManager.removeView(mChatHeadView);
    }
}
