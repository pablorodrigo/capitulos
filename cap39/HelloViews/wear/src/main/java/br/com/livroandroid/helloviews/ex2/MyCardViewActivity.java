package br.com.livroandroid.helloviews.ex2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import br.com.livroandroid.helloviews.R;

public class MyCardViewActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyCardFragment cardFragment = new MyCardFragment();
        Bundle args = new Bundle();
        args.putString("title","CardFragment");
        args.putString("msg","Card customizado.");
        cardFragment.setArguments(args);
        fragmentTransaction.add(R.id.cardLayout, cardFragment);
        fragmentTransaction.commit();
    }
}
