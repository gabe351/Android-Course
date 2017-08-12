package br.com.dfn.smartphonextablet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements MainActivityFragment.OnSelectedJogador {

    MainActivityFragment lstFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        if (isTablet()) {
            buildTablet();
        } else {
            buildPhone();
        }

    }

    public void buildTablet() {

    }

    public void buildPhone() {
        if (fragmentManager.findFragmentById(R.id.container) != null) {
            lstFragment =
                    (MainActivityFragment)
                            fragmentManager.findFragmentById(R.id.container);
        } else {
            lstFragment = new MainActivityFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.container, lstFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isTablet() {
        return findViewById(R.id.containerDetail) != null;
    }

    @Override
    public void onSelect(Jogador j) {
        if (isTablet()) {

        } else {
            Intent it = new Intent(getApplicationContext(), DetailActivity.class);
            it.putExtra(DetailActivity.PARAM_JOGADOR, j);
            startActivity(it);
        }
    }
}
