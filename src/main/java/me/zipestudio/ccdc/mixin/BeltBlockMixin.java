package me.zipestudio.ccdc.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltHelper;
import me.zipestudio.ccdc.config.LeafyConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeltBlock.class)
public class BeltBlockMixin {

    @ModifyReturnValue(method = "getBlockPathType", at = @At("RETURN"))
    private PathType changePathTypeForCats(PathType original) {
        return PathType.BLOCKED;
    }

    @Inject(
            method = "entityInside",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            ),
            cancellable = true
    )
    private void dontMoveChillingCats(BlockState state, Level worldIn, BlockPos pos, Entity entityIn, CallbackInfo ci) {

        if (!(entityIn instanceof Cat))
            return;

        BeltBlockEntity controller = BeltHelper.getControllerBE(worldIn, pos);
        if (controller == null)
            return;

        if (Math.abs(controller.getSpeed()) >= LeafyConfig.getInstance().getBeltMaxSpeed())
            return;

        ci.cancel();
    }

}
