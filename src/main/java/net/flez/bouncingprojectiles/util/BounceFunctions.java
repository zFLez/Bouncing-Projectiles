package net.flez.bouncingprojectiles.util;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class BounceFunctions {
    public static void bounce(Entity projectile, BlockHitResult hitResult) {
        Vec3 v = projectile.getDeltaMovement();

        switch (hitResult.getDirection()) {
            case UP, DOWN -> v = new Vec3(v.x, -v.y, v.z);
            case EAST, WEST -> v = new Vec3(-v.x, v.y, v.z);
            case NORTH, SOUTH -> v = new Vec3(v.x, v.y, -v.z);
        }

        projectile.setDeltaMovement(v);
        projectile.hasImpulse = true;
        projectile.setOnGround(false);
    }
}
