package model.items;

import enums.NewspaperType;

public record Newspaper(NewspaperType type, String title, boolean isIntelligible) {
    public Newspaper {
        if (title == null || title.isBlank()){
            throw new IllegalArgumentException("Название газеты походу написало ПСЖ...");
        }
    }

    public boolean isForFools() {
        return type == NewspaperType.FOR_FOOLS;
    }
}
