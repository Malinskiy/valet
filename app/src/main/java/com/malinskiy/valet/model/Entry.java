package com.malinskiy.valet.model;

public class Entry {

    public String packageName;
    public String description;

    public Entry(String packageName, String description) {
        this.packageName = packageName;
        this.description = description;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return packageName != null ? packageName.equals(entry.packageName) : entry.packageName == null;

    }

    @Override public int hashCode() {
        return packageName != null ? packageName.hashCode() : 0;
    }
}
