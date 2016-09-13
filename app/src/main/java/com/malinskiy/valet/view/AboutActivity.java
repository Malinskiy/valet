package com.malinskiy.valet.view;

import com.malinskiy.valet.R;
import com.malinskiy.valet.data.impl.EmbeddedBlacklistRepository;
import com.malinskiy.valet.model.Blacklist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.list) RecyclerView recyclerView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);
        ButterKnife.bind(this);

        Blacklist blacklist = new EmbeddedBlacklistRepository().load();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BlacklistAdapter(blacklist));
    }
}
