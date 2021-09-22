package pdl.tec.mygraphicstouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyGraphics extends View implements View.OnTouchListener {
    float circleX = 200, circleY = 200, radius = 75;
    boolean moving; // Default false
    float prevX, prevY;
    int screenWidth, screenHeight;


    public MyGraphics(Context context) {
        super(context);

        this.setOnTouchListener(this);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.RED);
        canvas.drawText("Hej", 400, 200, paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(circleX, circleY, radius, paint);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        float newX = event.getX();
        float newY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float xDelta = circleX - newX;
                float yDelta = circleY - newY;
                float delta = (float) Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2));
                if (delta < radius) {
                    moving = true;
                    prevX = newX;
                    prevY = newY;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving){
/*
                    circleX += newX - prevX;
                    circleY += newY - prevY;
                    if((newX <= screenWidth+radius) && (newY <= screenHeight+radius) && (newX <= 0 - radius) && (newY <= 0 - radius)){
                        prevX = newX;
                        prevY = newY;


                    }
                    invalidate(); // calder draw igen
*/
                    float xMoveTo = circleX + newX - prevX;
                    float yMoveTo = circleY + newY - prevY;
                    if (xMoveTo > 0 + radius && xMoveTo < screenWidth - radius
                            && yMoveTo > 0 + radius && yMoveTo < screenHeight - radius) {
                        circleX = xMoveTo;
                        circleY = yMoveTo;
                        prevX = newX;
                        prevY = newY;
                        invalidate(); // calder draw igen

                    }



                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }
}
