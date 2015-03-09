package rouse.dynamicnewsapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ArticleActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private class FetchContent extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... parms) {
            Article article = new Article(parms[0]);
            String articleText = article.getContent();
            // parms[0] is first parm, etc.
            //NewsCategory current = new NewsCategory(mTitle.toString());
            //ArrayList<String> articles = current.getTitles();
            //articles.add(parms[0]);
            return articleText;
        }

        // Not sure what the three dots mean? See: http://stackoverflow.com/questions/3158730/java-3-dots-in-parameters?rq=1
        protected void onProgressUpdate(Void... values) {

        }

        protected void onPostExecute(String articleText) {
            TextView myAwesomeTextView = (TextView)findViewById(R.id.textView);
            myAwesomeTextView.setText(articleText);
        }
    }

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    MenuItem itemLater;
    MenuItem itemSave;
    MenuItem itemShare;

    Boolean Saved = false;
    Boolean Later = false;

    static final private int LATER = Menu.FIRST;
    static final private int SAVE = Menu.FIRST + 1;
    static final private int SHARE = Menu.FIRST + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        String value = new String();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("content");
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
                mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        new FetchContent().execute(value);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.Popular);
                break;
            case 2:
                mTitle = getString(R.string.Local);
                break;
            case 3:
                mTitle = getString(R.string.US);
                break;
            case 4:
                mTitle = getString(R.string.World);
                break;
            case 5:
                mTitle = getString(R.string.Sports);
                break;
            case 6:
                mTitle = getString(R.string.Finance);
                break;
            case 7:
                mTitle = getString(R.string.Pop);
                break;
            case 8:
                mTitle = getString(R.string.Saved);
                break;
            case 9:
                mTitle = getString(R.string.Later);
                break;
            case 10:
                mTitle = getString(R.string.Shared);
                break;
            case 11:
                mTitle = getString(R.string.Settings);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);


        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFFFF")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFFFF")));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        itemLater = menu.add(Menu.NONE, LATER, Menu.NONE, "");
        itemSave = menu.add(Menu.NONE, SAVE, Menu.NONE, "");
        itemShare = menu.add(Menu.NONE, SHARE, Menu.NONE, "");

        itemLater.setIcon(R.drawable.later);
        itemSave.setIcon(R.drawable.star);
        itemShare.setIcon(R.drawable.share);

        itemLater.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        itemSave.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        itemShare.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case (LATER): {
                if (Later) {
                    //Already in Read for Later
                    itemLater.setIcon(R.drawable.later);
                    Later = false;
                }
                else{
                    //Read for Later
                    itemLater.setIcon(R.drawable.later_sel);
                    Later = true;
                }
                break;
            }
            case (SAVE): {
                if (Saved){
                    //Already Saved
                    itemSave.setIcon(R.drawable.star);
                    Saved = false;
                }
                else {
                    //Save it
                    itemSave.setIcon(R.drawable.star_sel);
                    Saved = true;
                }
                break;
            }
            case (SHARE): {
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ArticleActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
