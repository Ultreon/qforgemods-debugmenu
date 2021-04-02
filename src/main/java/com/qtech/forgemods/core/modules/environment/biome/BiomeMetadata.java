/*******************************************************************************
 * Copyright 2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package com.qtech.forgemods.core.modules.environment.biome;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * Biome metadata class.
 *
 * @author Biomes 'o Plenty mod.
 */
public class BiomeMetadata {
    private final ImmutableMap<BOPClimates, Integer> weightMap;

    @Nullable
    private final RegistryKey<Biome> beachBiome;

    @Nullable
    private final RegistryKey<Biome> riverBiome;

    @Nullable
    private final BiFunction<Double, Double, Integer> foliageColorFunction;

    @Nullable
    private final BiFunction<Double, Double, Integer> grassColorFunction;

    @Nullable
    private final BiFunction<Double, Double, Integer> waterColorFunction;

    protected BiomeMetadata(Map<BOPClimates, Integer> weights, @Nullable RegistryKey<Biome> beachBiome, @Nullable RegistryKey<Biome> riverBiome, @Nullable BiFunction<Double, Double, Integer> foliageColorFunction, @Nullable BiFunction<Double, Double, Integer> grassColorFunction, @Nullable BiFunction<Double, Double, Integer> waterColorFunction) {
        this.weightMap = ImmutableMap.copyOf(weights);
        this.beachBiome = beachBiome;
        this.riverBiome = riverBiome;
        this.foliageColorFunction = foliageColorFunction;
        this.grassColorFunction = grassColorFunction;
        this.waterColorFunction = waterColorFunction;
    }

    public Map<BOPClimates, Integer> getWeightMap() {
        return this.weightMap;
    }

    @Nullable
    public RegistryKey<Biome> getBeachBiome() {
        return this.beachBiome;
    }

    @Nullable
    public RegistryKey<Biome> getRiverBiome() {
        return this.riverBiome;
    }

    @Nullable
    public BiFunction<Double, Double, Integer> getFoliageColorFunction() {
        return this.foliageColorFunction;
    }

    @Nullable
    public BiFunction<Double, Double, Integer> getGrassColorFunction() {
        return this.grassColorFunction;
    }

    @Nullable
    public BiFunction<Double, Double, Integer> getWaterColorFunction() {
        return this.waterColorFunction;
    }

    public boolean hasWeights() {
        return !this.weightMap.isEmpty() && !this.weightMap.entrySet().stream().allMatch((entry) -> entry.getValue().equals(0));
    }
}