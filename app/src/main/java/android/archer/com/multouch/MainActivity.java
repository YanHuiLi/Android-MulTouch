package android.archer.com.multouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 多点触摸的实现。
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout root = (FrameLayout) findViewById(R.id.container);
        final ImageView iv= (ImageView) findViewById(R.id.iv);
        root.setOnTouchListener(new View.OnTouchListener() {

            float currentDistence;
            float LastDistance= -1;


            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //采用switch来判断触摸的方式，一般有三种
                switch (event.getAction()) {
                    //按下
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("action down");

                        break;
                    //触摸移动
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("action move");


                        if (event.getPointerCount()>=2){
                            float offsetX=event.getX(0)-event.getX(1);
                            float offsetY=event.getY(0)-event.getY(1);


                            currentDistence= (float) Math.sqrt(offsetX*offsetX+offsetY*offsetY);

                        }

                        if (LastDistance<0){
                            LastDistance=currentDistence;
                        }else{
                            if (currentDistence-LastDistance>5){
                                System.out.println("放大");

                                FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) iv.getLayoutParams();
                                layoutParams.width= (int) (1.1f*iv.getWidth());
                                layoutParams.height= (int) (1.1f*iv.getHeight());

                                iv.setLayoutParams(layoutParams);


                                LastDistance=currentDistence;
                            }else if (LastDistance -currentDistence>5){
                                System.out.println("缩小");
                                LastDistance=currentDistence;
                                FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) iv.getLayoutParams();
                                layoutParams.width= (int) (0.8f*iv.getWidth());
                                layoutParams.height= (int) (0.8f*iv.getHeight());
                                iv.setLayoutParams(layoutParams);

                            }
                        }





/**
 获取多个触摸点。
 有bug
 System.out.println("pointer count : "+event.getPointerCount());
 System.out.println(String.format("x1:%f y1:%f x2:%f y2:%f", event.getX(0),event.getY(0),event.getX(1),event.getY(1)));

 //*

 //                        System.out.println(String.format("x:%f,y:%f", event.getX(), event.getY()))
 /**
 * 这段代码用于实现单个触摸点的拖动
 *
 把图片放进一个frameLayout里面
 FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) iv.getLayoutParams();
 layoutParams.leftMargin= (int) event.getX();
 layoutParams.topMargin= (int) event.getY();

 iv.setLayoutParams(layoutParams);
 */

                        break;

                    //触摸弹起
                    case MotionEvent.ACTION_UP:

                        System.out.println("action up");

                        break;

                }


                //通过返回true来通知系统监听move up事件
                return true;
            }
        });
    }

}
