package com.mycompany.omgandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by davidyu on 2/11/15.
 */
public class DetailActivity extends ActionBarActivity {

    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/"; // 13
    String mImageURL; // 13
    ShareActionProvider mShareActionProvider; // 14

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_detail);

        // Enable the "Up" button for more navigation options
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Access the imageview from XML
        final ImageView imageView = (ImageView) findViewById(R.id.img_cover);

        // 13. unpack the coverID from its trip inside your Intent
        String coverID = this.getIntent().getExtras().getString("coverID");

        // See if there is a valid coverID
        if (coverID.length() > 0) {

            // Use the ID to construct an image URL
            mImageURL = IMAGE_URL_BASE + coverID + "-L.jpg";

            // Use Picasso to load the image
            Picasso.with(this).load(mImageURL).placeholder(R.drawable.img_books_loading).into(imageView);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*
                // open the image in browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mImageURL));
                DetailActivity.this.startActivity(intent);
                */

                // send the image via email
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/html");
                emailIntent.putExtra(Intent.EXTRA_TITLE, "my title");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "my subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, mImageURL);
                DetailActivity.this.startActivity(emailIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu
        // this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Access the Share Item defined in menu XML
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        // Access the object responsible for
        // putting together the sharing submenu
        if (shareItem != null) {
            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        }

        setShareIntent();

        return true;
    }

    private void setShareIntent() {

        if ( mShareActionProvider != null ) {
            // create an Intent with the contents of the TextView
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                    "Book Recommendation!");
            shareIntent.putExtra(Intent.EXTRA_TEXT, mImageURL);

            // Make sure the provider knows
            // it should work with that Intent
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
