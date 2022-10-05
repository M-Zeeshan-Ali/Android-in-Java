package fahad.example.animation_fahadhussain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {
    private ViewAnimator simpleViewAnimator;
    Button btnNext;

    int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = (Button) findViewById(R.id.buttonNext);
        simpleViewAnimator = (ViewAnimator) findViewById(R.id.simpleViewAnimator);
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(images[i]);
            simpleViewAnimator.addView(imageView);
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // set the animation type to ViewAnimator
        simpleViewAnimator.setInAnimation(in);
        simpleViewAnimator.setOutAnimation(out);

        simpleViewAnimator.setAnimateFirstView(false); // set false value for setAnimateFirstView


        btnNext.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                simpleViewAnimator.showNext();
            }
        });

    }

}