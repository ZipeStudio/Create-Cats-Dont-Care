package me.zipestudio.ccatsdontcare.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MoveToBlockGoal.class)
public class MoveToBlockGoalMixin {

    @Shadow
    public PathfinderMob mob;

    @Shadow
    public BlockPos blockPos;

    @ModifyReturnValue(method = "getMoveToTarget", at = @At("RETURN"))
    private BlockPos redirectTargetForCatsOnBelts(BlockPos original) {

        if (!(this.mob instanceof Cat)) return original;

        BlockState state = mob.level().getBlockState(this.blockPos);
        if (AllBlocks.BELT.has(state)) {
            return blockPos;
        }

        return original;
    }

}