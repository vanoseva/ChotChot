package adlm.t3k48.ChotChot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.util.Log;
import android.content.Intent;

public class MyActivity extends Activity implements AdlmButtonListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        RelativeLayout tl = (RelativeLayout)this.findViewById(R.id.mailLayout);

        RelativeLayout.LayoutParams lpResume = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lpResume.setMargins(0, 100, 0, 0);
        lpResume.addRule(RelativeLayout.CENTER_HORIZONTAL);

        AdlmButton adlmResume = new AdlmButton(this);
        adlmResume.setResource(R.drawable.v1_help_rematch, R.drawable.v1_help_rematch_effect);
        adlmResume.setLayoutParams(lpResume);
        tl.addView(adlmResume);
        adlmResume.setAdlmButtonListener(this);
//        adlmResume.setAdlmButtonListener(new AdlmButtonListener() {
//            @Override
//            public void onClick(Object sender, AdlmButtonArguments args) {
//
//                //Log.v("AdlmButton", "Args: X = " + args.X + " - " + args.Y);
//
//                // Demo vao giao dien ingame.
//                //Intent intent = new Intent(this, IngameActivity.class);
//
//                setContentView(R.layout.ingame);
//            }
//        });

        RelativeLayout.LayoutParams lpOption = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lpOption.setMargins(0, 300, 0, 0);
        lpOption.addRule(RelativeLayout.CENTER_HORIZONTAL);

        AdlmButton adlmOption = new AdlmButton(this);
        adlmOption.setResource(R.drawable.v1_help_option, R.drawable.v1_help_option_effect);
        adlmOption.setLayoutParams(lpOption);
        tl.addView(adlmOption);
        adlmOption.setAdlmButtonListener(new AdlmButtonListener() {
            @Override
            public void onClick(Object sender, AdlmButtonArguments args) {
                //AdlmButton adlm = (AdlmButton)sender;

                // Chuyen sang giao dien Option.
                setContentView(R.layout.option);
            }
        });

        //Log.v("novastar", "Width = " + tl.getWidth() + " - Height = " + tl.getHeight());
    }

    public void onClick(Object sender, AdlmButtonArguments args) {
        Intent intent = new Intent(this, IngameActivity.class);
        intent.putExtra("mymsg", "hello :)");


        startActivity(intent);
        //setContentView(R.layout.ingame);
    }
}