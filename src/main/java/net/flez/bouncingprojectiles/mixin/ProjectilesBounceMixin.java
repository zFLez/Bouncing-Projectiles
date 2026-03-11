package net.flez.bouncingprojectiles.mixin;

import net.flez.bouncingprojectiles.util.BounceFunctions;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Projectile.class)
public class ProjectilesBounceMixin {
    @Inject(method = "onHit", at = @At("HEAD"), cancellable = true)
    private void bouncingprojectiles$bounce(HitResult hitResult, CallbackInfo ci) {
        if (!(hitResult instanceof BlockHitResult blockHit)) return;

        Projectile projectile = (Projectile)(Object)this;

        if (!projectile.level()
                .getBlockState(blockHit.getBlockPos())
                .is(Blocks.SLIME_BLOCK)) return;

        BounceFunctions.bounce(projectile, blockHit);

        ci.cancel(); // stop normal collision
    }

    @Inject(method = "hitTargetOrDeflectSelf", at = @At("HEAD"), cancellable = true)
    private void bounceOnSlime(HitResult hitResult, CallbackInfoReturnable<ProjectileDeflection> cir) {

        if (!(hitResult instanceof BlockHitResult blockHit)) return;

        Projectile projectile = (Projectile)(Object)this;

        if (!projectile.level()
                .getBlockState(blockHit.getBlockPos())
                .is(Blocks.SLIME_BLOCK)) return;

        BounceFunctions.bounce(projectile, blockHit);

        cir.cancel();
    }
}