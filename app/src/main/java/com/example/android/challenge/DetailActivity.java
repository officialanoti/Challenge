package com.example.android.challenge;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.challenge.Models.Developer;
import com.squareup.picasso.Picasso;

/**
 * Created by Magaji on 8/30/2017.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Developer developer = (Developer) getIntent().getSerializableExtra("developer_select");

        CollapsingToolbarLayout cToolBar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        cToolBar.setTitleEnabled(true);
        cToolBar.setTitle(developer.getLogin());
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        ImageView imageView = (ImageView) findViewById(R.id.image_id);
        TextView profileUrl = (TextView) findViewById(R.id.profile_url);
        Picasso.with(this)
                .load(developer.getAvatar_url())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView);

        profileUrl.setText(developer.getHtml_url());

        profileUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(Intent.ACTION_VIEW);
                pIntent.setData(Uri.parse(developer.getHtml_url()));
                startActivity(pIntent);
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = ShareCompat.IntentBuilder.from(DetailActivity.this)
                        .setType("text/plain")
                        .setText("Check out this awesome developer @\"" + developer.getLogin() + "," + developer.getHtml_url())
                        .getIntent();
                startActivity(shareIntent);
            }
        });
    }
}
