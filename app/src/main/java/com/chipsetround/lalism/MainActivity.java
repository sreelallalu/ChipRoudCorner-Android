package com.chipsetround.lalism;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import com.chipsetround.lalism.data.Chip;
import com.chipsetround.lalism.l.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ItemChipAdapter.OnContactClickListener {

    RecyclerView _recycler;
    ItemChipAdapter contactAdapter;
    private ChipsInputLayout chipsInputLayout;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        _recycler = (RecyclerView) findViewById(R.id.recycler);
        this.contactAdapter = new ItemChipAdapter(this);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(contactAdapter);
        chipsInputLayout = (ChipsInputLayout) findViewById(R.id.chips_input);
        submit = (Button) findViewById(R.id.submit);

        setUp();
        setChipSet();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
                ChipsAdapter.allList(new ChipsAdapter.SelectedListItems() {
                    @Override
                    public void selected(List<Chip> selected) {
                        for (int i = 0; i < selected.size(); i++) {
                            Log.e("list array", selected.get(i).getTitle());

                        }
                    }
                });*/


            }
        });
    }

    private void setChipSet() {


        List<Chip> listchip = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listchip.add(new ChipItems(1 + i, "kidukki " + i, "366663" + i, "email.co" + i));

        }


        this.chipsInputLayout.setSelectedChipList(listchip);

    }

    private void setUp() {

        List<Chip> listchip = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listchip.add(new ChipItems(1 + i, "jins" + i, "366663" + i, "email.co" + i));

        }

        this.chipsInputLayout.setFilterableChipList(listchip);
    }

    @Override
    public void onContactClicked(ChipItems chip) {

    }
}

