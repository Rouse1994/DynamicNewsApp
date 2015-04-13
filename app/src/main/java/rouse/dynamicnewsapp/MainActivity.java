package rouse.dynamicnewsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, AdapterView.OnItemClickListener {

    private String current_user;

    private class FetchArticles extends AsyncTask<String, Void, ArrayList<String>> {

        protected ArrayList<String> doInBackground(String... parms) {

            // parms[0] is first parm, etc.
            ArrayList<String> articles = new ArrayList<String>();

            if (mTitle.toString().equals("Read Later")){
               articles = UserDatabase.getLater(current_user);
            } else if (mTitle.toString().equals("Saved")){
               articles = UserDatabase.getSaved(current_user);
            } else {
                current = new NewsCategory(mTitle.toString());
                articles = current.getTitles();
            } return articles;
        }

        // Not sure what the three dots mean? See: http://stackoverflow.com/questions/3158730/java-3-dots-in-parameters?rq=1
        protected void onProgressUpdate(Void... values) {

        }

        protected void onPostExecute(ArrayList<String> articles) {
            ListView listView = (ListView)findViewById(R.id.Items);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1 , articles);
            listView.setAdapter(adapter);
        }
    }

    // This is for selecting an item from the list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Use a toast message to show which item selected
        String text = "Opening article...";
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
        Intent i = new Intent(getApplicationContext(), ArticleActivity.class);
        i.putExtra("title", current.getArticleTitle(position));
        i.putExtra("content",current.getArticleFileName(position));
        i.putExtra("user", current_user);
        startActivity(i);
    }

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    NewsCategory current = new NewsCategory();
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

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
                mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        new FetchArticles().execute();

        ListView listView = (ListView)this.findViewById(R.id.Items);
        listView.setOnItemClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            current_user = extras.getString("user");
            onSectionAttached(extras.getInt("mTitle"));
        }

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
        new FetchArticles().execute();
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
        //itemLater = menu.add(Menu.NONE, LATER, Menu.NONE, "");
        //itemSave = menu.add(Menu.NONE, SAVE, Menu.NONE, "");
        //itemShare = menu.add(Menu.NONE, SHARE, Menu.NONE, "");

        //itemLater.setIcon(R.drawable.later);
        //itemSave.setIcon(R.drawable.star);
        //itemShare.setIcon(R.drawable.share);

        //itemLater.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        //itemSave.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        //itemShare.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

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
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
