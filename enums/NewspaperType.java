package enums;

public enum NewspaperType {
    BUSINESS_SMARTNESS("Деловая смекалка"),
    DAVILON_JOKES("Давилонские юморески"),
    FOR_FAT("Газета для толстеньких"),
    FOR_THIN("Газета для тоненьких"),
    FOR_SMART("Газета для умных"),
    FOR_FOOLS("Газета для дураков");

    private final String displayName;
    
    NewspaperType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
