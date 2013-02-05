package adlm.t3k48.ChotChot;

import android.view.View;
import android.view.animation.Animation;

/**
 * Created with IntelliJ IDEA.
 * User: vanoseva
 * Date: 12/22/12
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnimationListenerExt implements Animation.AnimationListener {
    // Luu view object ma dang su dung animation nay.
    private View view;

    public AnimationListenerExt(View view) {
        this.view = view;
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
