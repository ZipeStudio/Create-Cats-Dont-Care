package me.zipestudio.ccdc.config;

import dev.isxander.yacl3.api.*;
import lombok.experimental.ExtensionMethod;
import me.zipestudio.ccdc.utils.ModMenuUtils;
import me.zipestudio.ccdc.utils.yacl.base.SimpleCategory;
import me.zipestudio.ccdc.utils.yacl.base.SimpleGroup;
import me.zipestudio.ccdc.utils.yacl.base.SimpleOption;
import me.zipestudio.ccdc.utils.yacl.extension.SimpleOptionExtension;
import me.zipestudio.ccdc.utils.yacl.screen.SimpleYACLScreen;
import me.zipestudio.ccdc.utils.yacl.utils.SimpleContent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

@ExtensionMethod(SimpleOptionExtension.class)
public class YACLConfigurationScreen {

    private static final Function<Boolean, Component> ENABLED_OR_DISABLE_FORMATTER = ModMenuUtils.getEnabledOrDisabledFormatter();

    private YACLConfigurationScreen() {
        throw new IllegalStateException("Screen class");
    }

    public static Screen createScreen(Screen parent) {
        LeafyConfig defConfig = LeafyConfig.getNewInstance();
        LeafyConfig config = LeafyConfig.getInstance();

        return SimpleYACLScreen.startBuilder(parent, config::saveAsync)
                .categories(
                        getGeneralCategory(defConfig, config)
                )
                .build();
    }

    private static ConfigCategory getGeneralCategory(LeafyConfig defConfig, LeafyConfig config) {
        return SimpleCategory.startBuilder("general")
                .groups(
                        getGeneralGroup(defConfig, config)
                )
                .build();
    }

    private static OptionGroup getGeneralGroup(LeafyConfig defConfig, LeafyConfig config) {
        return SimpleGroup.startBuilder("general")
                .options(

                        SimpleOption.<Boolean>startBuilder("enable_mod")
                                .withDescription(SimpleContent.NONE)
                                .withBinding(defConfig.isEnableMod(), config::isEnableMod, config::setEnableMod, true)
                                .withController(ENABLED_OR_DISABLE_FORMATTER)
                                .build(),

                        SimpleOption.<Integer>startBuilder("belt_max_speed")
                                .withDescription(SimpleContent.NONE)
                                .withBinding(defConfig.getBeltMaxSpeed(), config::getBeltMaxSpeed, config::setBeltMaxSpeed, true)
                                .withController(0, Integer.MAX_VALUE, 1, false)
                                .build()


                )
                .build();
    }

}


