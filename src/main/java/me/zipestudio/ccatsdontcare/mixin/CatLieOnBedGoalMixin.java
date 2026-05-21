package me.zipestudio.ccatsdontcare.mixin;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.AllBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.CatLieOnBedGoal;
import net.minecraft.world.level.LevelReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CatLieOnBedGoal.class)
public class CatLieOnBedGoalMixin {

    @ModifyReturnValue(
            method = "isValidTarget",
            at = @At("RETURN")
    )
    private boolean allowBelts(boolean original, LevelReader level, BlockPos pos) {

        if (original) {
            return true;
        }

        return AllBlocks.BELT.has(level.getBlockState(pos));
    }

}
