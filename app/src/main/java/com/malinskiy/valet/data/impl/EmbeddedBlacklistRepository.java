package com.malinskiy.valet.data.impl;

import com.malinskiy.valet.data.IBlacklistRepository;
import com.malinskiy.valet.model.Blacklist;
import com.malinskiy.valet.model.Entry;

import java.util.Arrays;
import java.util.HashSet;

public class EmbeddedBlacklistRepository implements IBlacklistRepository {
    @Override public Blacklist load() {
        return new Blacklist(new HashSet<>(Arrays.asList(
                SAMSUNG
                                                        )));
    }

    public static Entry[] SAMSUNG = new Entry[] {
            new Entry("com.wssyncmldm", "Samsung OTA")
    };
}
