package me.craziidust.craziilib.menu;

import com.google.common.annotations.Beta;
import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.createInventory;

public enum MenuType {
    CHEST_1_ROW {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder,9, title);
        }
    },
    CHEST_2_ROW {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder, 18, title);
        }
    },
    CHEST_3_ROW {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder,27,title);
        }
    },
    CHEST_4_ROW {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder,36,title);
        }
    },
    CHEST_5_ROW {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder,45,title);
        }
    },
    CHEST_6_ROW {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder,54,title);
        }
    },
    HOPPER {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder, InventoryType.HOPPER,title);
        }
    },
    @Beta
    DISPENSER {
        @Override
        public @NotNull Inventory create(Menu holder, Component title) {
            return createInventory(holder,InventoryType.DISPENSER,title);
        }
    };

    public abstract @NotNull Inventory create(Menu holder, Component title);
}