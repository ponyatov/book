package io.github.ponyatov.metal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /// task list text panel
    public static TextView tasklist;

    /// command entry field
    public static TextView pad;

    /// run command button
    Button go;

    /// add new task floating button
    FloatingActionButton fab;

    private void msg(String what) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("msg").setMessage(what);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasklist = findViewById(R.id.tasklist);

        pad = findViewById(R.id.pad);

        go  = findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Task t = new Task(pad.getText().toString());
                tasklist.setText(t.head());
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pad.setText("'newTask' Task\n"+pad.getText());
            }
        });

        FORTH.parse("# put your commands here \n"
                         +" 1 2.3 4e-5 0xDeadBeef 0b1101");
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
}
