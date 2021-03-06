package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

/**
        * A main Activity that prompts the user for a URL to an image and
        * then uses Intents and other Activities to download the image and
        * view it.
        */

public class WebServiceActivity extends ActivityBase {

    /**
     * URL for the image that's downloaded by default if the user
     * doesn't specify otherwise.
     */
    private final static String DEFAULT_URL =
            "https://upload.wikimedia.org/wikipedia/commons/3/3e/Android_logo_2019.png";

    /**
     * A value that uniquely identifies the request to download an
     * image.
     */
    private static final int DOWNLOAD_IMAGE_REQUEST = 1;

    /**
     * EditText field for entering the desired URL to an image.
     */
    private EditText mUrlEditText;

    /**
     * Keeps track of whether a download button click from the user is
     * processed or not.  Only one download click is processed until a
     * requested image is downloaded and displayed.
     */
    private boolean mProcessButtonClick = true;

    /**
     * Reference to the "add" floating action button.
     */
    private FloatingActionButton mAddFab;

    /**
     * Reference to the "download" floating action button.
     */
    private FloatingActionButton mDownloadFab;

    /**
     * Keeps track of whether the edit text is visible for the user to
     * enter a URL.
     */
    private boolean mIsEditTextVisible = false;

    /**
     * Hook method called when a new instance of Activity is
     * created. One time initialization code goes here, e.g., UI
     * snackbar and some class scope variable initialization.
     *
     * @param savedInstanceState
     *            object that contains saved state information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        // Initialize the views.
        initializeViews();
    }

    /**
     * Initialize the views.
     */
    private void initializeViews() {
        // Cache the EditText that holds the urls entered by the
        // user (if any).
        mUrlEditText = findViewById(R.id.url);

        // Cache floating action button that adds a URL.
        mAddFab =
                findViewById(R.id.add_fab);

        // Cache floating action button that downloads an image.
        mDownloadFab =
                findViewById(R.id.download_fab);

        // Make the EditText invisible for animation purposes
        mUrlEditText.setVisibility(View.INVISIBLE);

        // Make the download button invisible for animation purposes
        mDownloadFab.setVisibility(View.INVISIBLE);

        // Register a listener to help display download FAB when the user
        // hits enter.
        mUrlEditText.setOnEditorActionListener
                (new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH
                                || actionId == EditorInfo.IME_ACTION_DONE
                                || event.getAction() == KeyEvent.ACTION_DOWN
                                && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            UiUtils.hideKeyboard(WebServiceActivity.this,
                                    mUrlEditText.getWindowToken());
                            // Insert default value if no input was specified.
                            if (TextUtils.isEmpty(
                                    mUrlEditText.getText().toString().trim())) {
                                mUrlEditText.setText(
                                        DEFAULT_URL);
                            }
                            UiUtils.showFab(mDownloadFab);
                            return true;
                        } else
                            return false;
                    }
                });
    }

    /**
     * Called by the Android Activity framework when the user clicks +
     * floating action button.
     * @param view The view
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addUrl(View view) {
        // Check whether the EditText is visible to determine
        // the kind of animations to use.
        if (mIsEditTextVisible) {
            // Hide the EditText using circular reveal animation
            // and set boolean to false.
            UiUtils.hideEditText(mUrlEditText);
            mIsEditTextVisible = false;

            // Rotate the FAB from 'X' to '+'.
            int animRedId = R.anim.fab_rotate_backward;

            // Load and start the animation.
            mAddFab.startAnimation
                    (AnimationUtils.loadAnimation(this,
                            animRedId));
            // Hides the download FAB.
            UiUtils.hideFab(mDownloadFab);
        } else {
            // Hide the EditText using circular reveal animation
            // and set boolean to true.
            UiUtils.revealEditText(mUrlEditText);
            mIsEditTextVisible = true;
            mUrlEditText.requestFocus();

            // Rotate the FAB from '+' to 'X'.
            int animRedId = R.anim.fab_rotate_forward;

            // Load and start the animation.
            mAddFab.startAnimation(AnimationUtils.loadAnimation(this,
                    animRedId));
        }
    }

    /**
     * Called by the Android Activity framework when the user clicks
     * the "Download Image" button.
     *
     * @param view
     *            The view.
     */
    public void downloadImage(View view) {
        try {
            // Hide the keyboard.
            UiUtils.hideKeyboard(this,
                    mUrlEditText.getWindowToken());

            // Call startDownloadImageActivity() to create a new
            // Intent and start an Activity that downloads an image
            // from the URL given by the user.
            startDownloadImageActivity(getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the URL to download based on user input.
     */
    protected Uri getUrl() {
        // Get the text the user typed in the edit text (if anything).
        String userInput = mUrlEditText.getText().toString();

        // If the user didn't provide a URL then use the default.
        if ("".equals(userInput))
            userInput = DEFAULT_URL;

        return Uri.parse(userInput);
    }

    /**
     * This method is used to create an Intent and then start an
     * Activity with it.
     *
     * @param url The URL for the image to download.
     */
    private void startDownloadImageActivity(Uri url) {
        // Make sure there's a non-null URL.
        if (url != null) {
            // Make sure that there's not already a download in progress.
            if (!mProcessButtonClick)
                UiUtils.showToast(this,
                        "Already downloading image "
                                + url);
                // Do a sanity check to ensure the URL is valid.
            else if (!URLUtil.isValidUrl(url.toString()))
                UiUtils.showToast(this,
                        "Invalid URL "
                                + url.toString());
            else {
                // Disable processing of a button click.
                mProcessButtonClick = false;

                // Make an intent to download the image.
                final Intent intent =
                        DownloadImageActivity.makeIntent(url);

                // Start the Activity associated with the Intent,
                // which will download the image and then return the
                // Uri for the downloaded image file via the
                // onActivityResult() hook method.
                startActivityForResult(intent,
                        DOWNLOAD_IMAGE_REQUEST);
            }
        }
    }

    /**
     * Hook method called back by the Android Activity framework when
     * an Activity that's been launched exits, giving the requestCode
     * it was started with, the resultCode it returned, and any
     * additional data from it.
     */
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        // Check if the started Activity completed successfully.
        if (resultCode == Activity.RESULT_OK) {
            // Check if the request code is what we're expecting.
            if (requestCode == DOWNLOAD_IMAGE_REQUEST) {
                // Call the makeGalleryIntent() factory method to
                // create an Intent that will launch the "Gallery" app
                // by passing in the path to the downloaded image
                // file.
                Intent intent =
                        makeGalleryIntent(data.getDataString());

                // Start the default Android Gallery app image viewer.
                startActivity(intent);
            }
        }
        // Check if the started Activity did not complete successfully
        // and inform the user a problem occurred when trying to
        // download contents at the given URL.
        else if (resultCode == Activity.RESULT_CANCELED)
            UiUtils.showToast(this,
                    "failed to download "
                            + getUrl().toString());

        // Allow user to click the download button again.
        mProcessButtonClick = true;
    }

    /**
     * Factory method that returns an implicit Intent for viewing the downloaded
     * image in the Gallery app.
     */
    private Intent makeGalleryIntent(String pathToImageFile) {
        // Create an intent that will start the Gallery app to view
        // the image.
        return UriUtils
                .buildFileProviderReadUriIntent(this,
                        Uri.fromFile(new File(pathToImageFile)),
                        Intent.ACTION_VIEW,
                        "image/*");
    }
}