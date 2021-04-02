package com.qtech.forgemods.core.modules.environment.ores;

import com.qtech.forgemods.core.modules.environment.ores.configs.ChancedOreConfig;
import com.qtech.forgemods.core.modules.environment.ores.configs.DefaultOreConfig;
import com.qtech.forgemods.core.modules.items.OreMaterial;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Ores {
    // Default ore.
    public static final DefaultOre COPPER = new DefaultOre("copper", () -> OreMaterial.COPPER, 3, 1, new DefaultOreConfig(8, 8, 40, 90));
    public static final DefaultOre TIN = new DefaultOre("tin", () -> OreMaterial.TIN, 3, 1, new DefaultOreConfig(8, 8, 20, 80));
    public static final DefaultOre SILVER = new DefaultOre("silver", () -> OreMaterial.SILVER, 4, 1, new DefaultOreConfig(4, 8, 0, 40));
    public static final DefaultOre LEAD = new DefaultOre("lead", () -> OreMaterial.LEAD, 4, 2, new DefaultOreConfig(4, 8, 0, 30));
    public static final DefaultOre NICKEL = new DefaultOre("nickel", () -> OreMaterial.NICKEL, 5, 1, new DefaultOreConfig(1, 6, 0, 24));
    public static final DefaultOre PLATINUM = new DefaultOre("platinum", () -> OreMaterial.PLATINUM, 5, 2, new DefaultOreConfig(1, 8, 5, 20));
    public static final DefaultOre ZINC = new DefaultOre("zinc", () -> OreMaterial.ZINC, 3, 1, new DefaultOreConfig(4, 8, 20, 60));
    public static final DefaultOre BISMUTH = new DefaultOre("bismuth", () -> OreMaterial.BISMUTH, 3, 1, new DefaultOreConfig(4, 8, 16, 64));
    public static final DefaultOre BAUXITE = new DefaultOre("bauxite", () -> OreMaterial.ALUMINUM, 4, 1, new DefaultOreConfig(6, 8, 15, 50));
    public static final DefaultOre URANIUM = new DefaultOre("uranium", () -> OreMaterial.URANIUM, 6, 2, new DefaultOreConfig(1, 4, 0, 18));

    // Gems
    public static final DefaultOre RUBY = new DefaultOre("ruby", () -> OreMaterial.RUBY, 6, 2, new DefaultOreConfig(1, 4, 0, 18));
    public static final DefaultOre AMETHYST = new DefaultOre("amethyst", () -> OreMaterial.AMETHYST, 6, 2, new DefaultOreConfig(1, 4, 15, 20));
    public static final DefaultOre SAPHIRE = new DefaultOre("amethyst", () -> OreMaterial.SAPHIRE, 6, 2, new DefaultOreConfig(1, 4, 10, 17));
    public static final DefaultOre AMBER = new DefaultOre("amber", () -> OreMaterial.AMBER, 6, 2, new DefaultOreConfig(1, 4, 2, 10));
    public static final DefaultOre PERIDOT = new DefaultOre("peridot", () -> OreMaterial.PERIDOT, 6, 2, new DefaultOreConfig(1, 4, 5, 15));
    public static final DefaultOre BERYL = new DefaultOre("beryl", () -> OreMaterial.BERYL, 6, 2, new DefaultOreConfig(1, 4, 8, 24));
    public static final DefaultOre TOPAZ = new DefaultOre("topaz", () -> OreMaterial.TOPAZ, 6, 2, new DefaultOreConfig(1, 4, 6, 17));
    public static final DefaultOre TANZANITE = new DefaultOre("tanzanite", () -> OreMaterial.TANZANITE, 6, 2, new DefaultOreConfig(1, 4, 0, 7));
    public static final DefaultOre MALACHITE = new DefaultOre("malachite", () -> OreMaterial.MALACHITE, 6, 2, new DefaultOreConfig(1, 4, 0, 7));

    // Nether ore.
    public static final NetherOre COBALT = new NetherOre("cobalt", () -> OreMaterial.COBALT, 20, 4, new DefaultOreConfig(128, 2, 0, 48));

    // Chanced ore.
    public static final ChancedOre ULTRINIUM = new ChancedOre("ultrinium", () -> OreMaterial.ULTRINIUM, 512, 5, new ChancedOreConfig(1, 1, 0, 64, 6), (b) -> b.getClimate().temperature >= 1.0);
    public static final ChancedOre INFINITY = new ChancedOre("infinity", () -> OreMaterial.INFINITY, 8192, 6, new ChancedOreConfig(1, 1, 0, 64, 128), (b) -> b.getClimate().temperature >= 2.0);
}
