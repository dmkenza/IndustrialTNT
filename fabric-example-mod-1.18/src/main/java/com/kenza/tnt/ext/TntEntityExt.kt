package com.kenza.tnt.ext

import com.kenza.tnt.domain.TNTAttribute
import com.kenza.tnt.domain.TntType
import drawer.getFrom
import drawer.put
import drawer.readFrom
import drawer.write
import kotlinx.serialization.Serializable
import net.minecraft.entity.Entity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedDataHandler
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf

@Serializable
data class TntEntityPropertyData(
    val tntType : TntType = TntType.Default
)



interface TntEntityExt : TNTAttribute {
}

class TntEntityExtImpl(val entity: Entity): TntEntityExt{

    private var propertyData: TntEntityPropertyData
        set(value) {
            entity.dataTracker.set(TNT_ENTITY_PROPERTY_TRACKER, value)
        }
        get() = entity.dataTracker.get(TNT_ENTITY_PROPERTY_TRACKER)


     override var tntType: TntType
        get() = propertyData.tntType
        set(value) {
            propertyData = propertyData.copy(tntType = value)
        }


    fun initDataTracker() {
        TrackedDataHandlerRegistry.register(TNT_ENTITY_EXTRA_DATA)
        entity.dataTracker.startTracking(TNT_ENTITY_PROPERTY_TRACKER, TntEntityPropertyData())
    }

    fun writeNbt(tag: NbtCompound): NbtCompound {
        val data = entity.dataTracker.get(TNT_ENTITY_PROPERTY_TRACKER)
        TntEntityPropertyData.serializer().put(data, inTag = tag, key = NBT_TNT_ENTITY_DATA_KEY)
        return tag
    }

    fun readNbt(tag: NbtCompound) {
        TntEntityPropertyData.serializer().getFrom(tag, key = NBT_TNT_ENTITY_DATA_KEY).let { data ->
            entity.dataTracker.set(TNT_ENTITY_PROPERTY_TRACKER, data)
            ckeckAndSetPropertyData()
        }
    }

    private fun ckeckAndSetPropertyData() {

    }


    companion object {

        val NBT_TNT_ENTITY_DATA_KEY = "NBT_TNT_ENTITY_DATA_KEY"


        val TNT_ENTITY_EXTRA_DATA: TrackedDataHandler<TntEntityPropertyData> =
            object : TrackedDataHandler<TntEntityPropertyData> {

                override fun write(buf: PacketByteBuf, value: TntEntityPropertyData) {
                    TntEntityPropertyData.serializer().write(value, toBuf = buf)
                }

                override fun read(buf: PacketByteBuf): TntEntityPropertyData {
                    return TntEntityPropertyData.serializer().readFrom(buf = buf)
                }

                override fun copy(value: TntEntityPropertyData): TntEntityPropertyData {
                    return value
                }

            }

        val TNT_ENTITY_PROPERTY_TRACKER = DataTracker.registerData(VillagerEntity::class.java, TNT_ENTITY_EXTRA_DATA)
    }
    
}