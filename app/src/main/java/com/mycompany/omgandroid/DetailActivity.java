package com.mycompany.omgandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ShareActionProvider;

import com.squareup.picasso.Picasso;

/**
 * Created by davidyu on 2/11/15.
 */
public class DetailActivity extends ActionBarActivity {

    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";

    ShareActionProvider mShareActionProvider;

    String mImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_detail);

        // Enable the "up" button for more navigation options
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Access the ImageView from XML
        ImageView imageView = (ImageView) findViewById(R.id.img_cover);

        // 13. unpack the coverID from its trip inside your Intent
        String coverID = this.getIntent().getExtras().getString("coverID");

        Log.d("omg android", "cover id is:" + coverID);

        // See if there is a valid coverID
        if ( coverID.length() > 0 ) {
            // Use the ID to construct an Image URL
            mImageURL = IMAGE_URL_BASE + coverID + "-L.jpg";

            // use Picasso to load the image
            Picasso.with(this).load(mImageURL).placeholder(R.drawable.img_books_loading).into(imageView);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        /*MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        if ( shareItem != null ) {
            Log.d("omg android", "shareItem:" + shareItem.toString());
            mShareActionProvider = (ShareActionProvider)shareItem.getActionProvider();
            Log.d("omg android", "mShareActionProvider:" + mShareActionProvider.toString());
        }
        setShareIntent();*/
        return true;
    }

   /* private void setShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Book Recommendation!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mImageURL);

        // Make sure the provider knows
        // it should work with that Intent
        mShareActionProvider.setShareIntent(shareIntent);
    }*/
}
