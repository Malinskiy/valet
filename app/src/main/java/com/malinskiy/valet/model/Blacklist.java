package com.malinskiy.valet.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Blacklist {

    private Set<String> packages;

    public Blacklist() {
        this(new HashSet<>(Arrays.asList(
                SAMSUNG
                                        )));
    }

    private Blacklist(Set<String> packages) {
        this.packages = packages;
    }

    public boolean contains(String entry) {
        return packages.contains(entry);
    }

    public static String[] SAMSUNG = new String[] {
            "com.wssyncmldm"
    };
}
