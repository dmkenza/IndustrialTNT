package com.kenza.tnt

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

object Entities {


    val industrialTntEntity = register("butterfly",
        FabricEntityTypeBuilder.create(SpawnGroup.MISC) { type: EntityType<TntEntity>, world: World ->
            TntEntity(type, world)
        }.dimensions(EntityDimensions.fixed(0.25f, 0.25f)))



    fun <T : Entity?> register(name: String?, builder: FabricEntityTypeBuilder<T>): EntityType<T> {
        val type = builder.build()
        return Registry.register(Registry.ENTITY_TYPE, Identifier(MOD_ID, name), type)
    }

}