package rouse.dynamicnewsapp;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private class checkUser extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... userInfo) {
            return UserDatabase.ValidLogIn(userInfo[0], userInfo[1]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            } else{
                TextView myAwesomeTextView = (TextView)findViewById(R.id.Invalid);
                myAwesomeTextView.setText("Invalid log in, try again.");
            }
        }
    }

    private class AddUser extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... userInfo) {
            return UserDatabase.makeUser(userInfo[0], userInfo[1]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            } else{
                TextView myAwesomeTextView = (TextView)findViewById(R.id.Invalid);
                myAwesomeTextView.setText("Network error, try again.");
            }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button LoginButton = (Button)findViewById(R.id.login);

        LoginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                            EditText mUser   = (EditText)findViewById(R.id.username);
                            EditText mPass   = (EditText)findViewById(R.id.password);
                            new checkUser().execute(mUser.getText().toString(), mPass.getText().toString());
                        }
                }
        );

        Button RegisterButton = (Button)findViewById(R.id.register);

        RegisterButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText mUser   = (EditText)findViewById(R.id.username);
                        EditText mPass   = (EditText)findViewById(R.id.password);
                        new AddUser().execute(mUser.getText().toString(), mPass.getText().toString());
                    }
                }
        );
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

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
            ((LoginActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
