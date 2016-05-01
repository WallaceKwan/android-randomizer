// Wallace Kwan 

package com.wallacekwan.randomizer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ListRandomizerActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<ListItem> listArray;
    ListItemAdapter listAdapter;

    EditText textInput;
    Button clearAll, add, randomize;

    String textToAdd;

    RelativeLayout rl;
    View emptyView;

    throwEmptyInput throwEmptyInput = new throwEmptyInput();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_randomizer);

        // Data source
        listArray = new ArrayList<>();

        //ListItem listItem = new ListItem(1, "Option 1");
        //listArray.add(listItem);

        // Convert array to views
        listAdapter = new ListItemAdapter(this, listArray);

        // Attach adapter to listView
        ListView listResults = (ListView) findViewById(R.id.lvListView);
        listResults.setAdapter(listAdapter);

        // If there is nothing to show, display the empty view
        if (listArray.isEmpty()) {
            rl = (RelativeLayout) findViewById(R.id.listRandomizerLayout);
            emptyView = getLayoutInflater().inflate(R.layout.empty_view, rl, false);
            rl.addView(emptyView);
        }

        ///

        // Set "Text Input" list view
        textInput = (EditText) findViewById(R.id.etTextInput);

        // Set "Clear All" button
        clearAll = (Button) findViewById(R.id.btnClearAll);
        assert clearAll != null;
        clearAll.setOnClickListener(this);

        // Set "Add" button
        add = (Button) findViewById(R.id.btnAdd);
        assert add != null;
        add.setOnClickListener(this);

        // Set "Randomize" button
        randomize = (Button) findViewById(R.id.btnRandomize);
        assert randomize != null;
        randomize.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_randomizer, menu);
        return true;
    }

    public void onClick(View v) {

        if (v == clearAll) {

            AlertDialog.Builder confirmClearAll = new AlertDialog.Builder(this);
            confirmClearAll.setTitle("Confirm");
            confirmClearAll.setMessage("Are you sure?");

            confirmClearAll.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    try {
                        rl.addView(emptyView);
                    } catch (IllegalStateException e) {
                        rl.removeView(emptyView);
                        rl.addView(emptyView);
                    }

                    listArray.clear();
                    listAdapter.notifyDataSetChanged();

                    dialog.dismiss();
                }

            });

            confirmClearAll.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing
                    dialog.dismiss();
                }

            });

            AlertDialog alert = confirmClearAll.create();
            alert.show();

        }

        if (v == add) {

            textToAdd = textInput.getText().toString().trim();

            try {

                throwEmptyInput.getSize(textToAdd.length(), "No item added");
                rl.removeView(emptyView);

                ListItem listItem = new ListItem(listArray.size() + 1, textToAdd);
                listArray.add(listItem);
                listAdapter.notifyDataSetChanged();

            } catch (NullPointerException | emptyInput e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        if (v == randomize) {

            try {

                throwEmptyInput.getSize(listArray.size(), "Nothing to randomize");

                Random rand = new Random();
                int random = rand.nextInt(listArray.size());
                Toast.makeText(getApplicationContext(), Integer.toString(random) + " " + listArray.get(random),
                        Toast.LENGTH_SHORT).show();

            } catch (emptyInput e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }



    }


}


