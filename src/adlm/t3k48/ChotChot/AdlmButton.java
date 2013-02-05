package adlm.t3k48.ChotChot;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import  android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: vanoseva
 * Date: 12/15/12
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdlmButton extends RelativeLayout implements View.OnTouchListener {
    private Context context;

    private ImageView ivButton;
    private ImageView ivEffect;
    private AlphaAnimation alphaAnim;

    private boolean flagTouchDown;

    // Tao listener de khi button nay duoc click/touch (up), thi se fire hanh dong
    // tuong ung.
    private AdlmButtonListener listener;

    public AdlmButton(Context context) {
        super(context);

        this.context = context;

        this.init();
    }

    public AdlmButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.init();
    }

    /**
     * Khoi tao gia tri ban dau cho cac bien cua object nay.
     */
    private void init() {
        this.listener = null;
        this.flagTouchDown = false;
    }

    /**
     * Image cho button va effect cua no.
     * @param interfaceButtonID ID cua image button.
     * @param interfaceEffectID ID cua image effect.
     */
    public void setResource(int interfaceButtonID, int interfaceEffectID) {

        //this.setBackgroundColor(Color.BLUE);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.ivButton = new ImageView(this.context);
        this.ivButton.setImageResource(interfaceButtonID);
        this.ivButton.setLayoutParams(lp);
        this.ivButton.setOnTouchListener(this);

        this.ivEffect = new ImageView(this.context);
        this.ivEffect.setImageResource(interfaceEffectID);
        this.ivEffect.setLayoutParams(lp);
        this.ivEffect.setVisibility(View.INVISIBLE);

        this.addView(this.ivButton);
        this.addView(this.ivEffect);

        // Khoi tao alpha animation.
        this.alphaAnim = new AlphaAnimation(0.00f, 1.00f);
        this.alphaAnim.setDuration(500);
        this.alphaAnim.setRepeatMode(Animation.REVERSE);
        //this.alphaAnim.setRepeatCount(Animation.INFINITE);
        this.alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // To change body of implemented methods use File | Settings | File Templates.

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Dua button ve trang thai ban dau (khong co effect).
                // Kich hoat effect cho button tai day.
                if (!flagTouchDown) {
                    ivEffect.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


    }

    /**
     * Khi extend ViewGroup, can phai override ham nay, neu khong cac child view se khong hien
     * thi.
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        //Log.v("novastar", "onLayout: " + this + " - " + changed + " - " + l + " - " + t + " - " + r + " - " + b);
        //Log.v("novastar", "Width = " + this.getWidth() + " - Height = " + this.getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Log.v("novastar", "On my touch down");
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    /**
     * Xu ly touch event cho cac child view.
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // Chi xu ly touch cho image view (button).
        if (v != this.ivButton) {
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.flagTouchDown = true;

                this.ivEffect.setVisibility(View.VISIBLE);
                this.ivEffect.startAnimation(this.alphaAnim);

                break;
            case MotionEvent.ACTION_UP:

                // Reset ve trang thai het touch.
                this.flagTouchDown = false;

                // Chi invisible effect khi anim da ket thuc.
                if (this.alphaAnim.hasEnded()) {
                    this.ivEffect.setVisibility(View.INVISIBLE);
                    this.alphaAnim.reset();
                }

                // Raise event khi con nam trong button.
                // TODO: Co nen toi uu de khong phai lay rect moi lan touch up hay khong?
                Rect r = new Rect();
                v.getHitRect(r);
                if (r.contains((int)event.getX(), (int)event.getY())) {
                    // Fire event.
                    if (this.listener != null) {
                        this.listener.onClick(this, new AdlmButtonArguments());
                    }
                }

                break;
        }

        return true;
    }

    public void setAdlmButtonListener(AdlmButtonListener listener) {
        this.listener = listener;
    }
}
