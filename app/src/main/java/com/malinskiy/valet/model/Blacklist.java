package com.malinskiy.valet.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blacklist {

    private Set<Entry> packages;

    public Blacklist(Set<Entry> packages) {
        this.packages = packages;
    }

    public boolean contains(String packageName) {
        return packages.contains(new Entry(packageName, null));
    }

    public List<Entry> toList() {
        return Arrays.asList(packages.toArray(new Entry[]{}));
    }
}
