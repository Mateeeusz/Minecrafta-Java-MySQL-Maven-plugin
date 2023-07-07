package me.mati.skyblock.data;

public enum DropType {
    CANCEL_DROP,
    NORMAL_DROP,
    RANDOM_DROP;

    public static DropType getFromName( String s) {
        for ( DropType dt : values()) {
            if (dt.name().replace("_", "").equalsIgnoreCase(s) || dt.name().equalsIgnoreCase(s)) {
                return dt;
            }
        }
        return null;
    }
}
