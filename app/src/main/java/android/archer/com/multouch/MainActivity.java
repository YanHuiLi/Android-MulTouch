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
//                        System.out.println(String.format("x:%f,y:%f", event.getX(), event.getY()))

                        //把图片放进一个frameLayout里面
                        FrameLayout.LayoutParams layoutParams= (FrameLayout.LayoutParams) iv.getLayoutParams();
                         layoutParams.leftMargin= (int) event.getX();
                        layoutParams.topMargin= (int) event.getY();

                        iv.setLayoutParams(layoutParams);

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
