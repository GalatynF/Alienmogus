package com.github.galatynf.alienmogus.mixin;

import com.github.galatynf.alienmogus.Alienmogus;
import com.github.galatynf.alienmogus.block.BadAirBlock;
import com.github.galatynf.alienmogus.block.RegisterBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ChokeInBadAirMixin extends Entity {

    @Shadow public abstract boolean canBreatheInWater();
    @Shadow protected abstract int getNextAirUnderwater(int air);

    public ChokeInBadAirMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getNextAirOnLand", at=@At("HEAD"), cancellable = true)
    private void chokeInBadAirWhenNotFull(int air, CallbackInfoReturnable<Integer> cir) {
        if(this.world.getBlockState(new BlockPos(this.getX(), this.getEyeY(), this.getZ())).isOf(RegisterBlocks.BAD_AIR)
                && !this.canBreatheInWater()
                && !StatusEffectUtil.hasWaterBreathing((LivingEntity)(Entity) this)
                && (!this.isPlayer() || !((PlayerEntity)(Entity)this).getAbilities().invulnerable)) {
            cir.setReturnValue(
                    this.world.getTime()%3 == 0?this.getNextAirUnderwater(air):this.getAir()
            );
        }
    }

    @Inject(method = "baseTick", at=@At("TAIL"))
    private void chokeInBadAirWhenFull(CallbackInfo ci) {
        if(this.world.getBlockState(new BlockPos(this.getX(), this.getEyeY(), this.getZ())).isOf(RegisterBlocks.BAD_AIR)
        && this.getAir() == 300) {
            this.setAir(this.getNextAirUnderwater(this.getAir()));
        }

        // If no air left, drown
        if (this.getAir() <= -20) {
            this.setAir(0);
            this.damage(DamageSource.CRAMMING, 1.0F);
        }
    }
}
