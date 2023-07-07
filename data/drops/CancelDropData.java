package me.mati.skyblock.data.drops;

import me.mati.skyblock.data.DropData;
import me.mati.skyblock.data.DropType;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CancelDropData implements DropData {

    public void breakBlock( Block block, Player player, ItemStack item) {
        block.setType(Material.AIR);
        Utill.sendMsg(player, " &2\u0fc3 &4Blad: &cTego bloku nie mozesz wykopac!");
    }

    public DropType getDropType() {
        return DropType.CANCEL_DROP;
    }
}
