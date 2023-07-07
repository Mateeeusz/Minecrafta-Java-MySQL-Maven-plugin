package me.mati.skyblock.store;

public enum StoreMode {
    MYSQL("mysql");

    private String name;

    StoreMode(final String name) {
        this.name = name;
    }

    public static StoreMode getByName(final String name) {
        for (final StoreMode sm : values()) {
            if (sm.getName().equalsIgnoreCase(name)) {
                return sm;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }
}

