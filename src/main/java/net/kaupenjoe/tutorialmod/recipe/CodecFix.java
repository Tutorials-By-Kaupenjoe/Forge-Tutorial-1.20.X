package net.kaupenjoe.tutorialmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.ForgeRegistries;

import java.util.Optional;

public final class CodecFix {
    private CodecFix() {}

    public static final Codec<ItemStack> ITEM_STACK_CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(ItemStack::getItem),
                Codec.INT.optionalFieldOf("count", 1).forGetter(ItemStack::getCount),
                CompoundTag.CODEC.optionalFieldOf("tag").forGetter((stack) -> {
                    return Optional.ofNullable(stack.getTag());
                })).apply(instance, CodecFix::createItemStackFix);
    });

    private static ItemStack createItemStackFix(ItemLike item, int count, Optional<CompoundTag> nbt) {
        ItemStack itemStack = new ItemStack(item, count);
        nbt.ifPresent(itemStack::setTag);
        return itemStack;
    }
}