package de.crafty.toolupgrades.upgrade;

public enum ToolUpgrade {


    MAGNETISM(Type.TOOL_AND_WEAPON, "\u00a79Magnetism"),
    AUTO_SMELTING(Type.TOOL, "\u00a74Auto Smelting"),
    MULTI_MINER(Type.TOOL, "\u00a78Multi Miner"),
    TELEPORTATION(Type.WEAPON, "\u00a73Teleportation"),
    FADELESS(Type.ALL, "\u00a76Fadeless"),
    SOFT_FALL(Type.BOOTS, "\u00a7FSoft Fall"),
    ENDER_MASK(Type.HELMET, "\u00a71Ender Mask"),
    MOB_CAPTURE(Type.TOOL_AND_WEAPON, "\u00a75Mob Capture"),
    SILKY(Type.TOOL, "\u00a7FSilky"),
    LIFE_BONUS(Type.ARMOR, "\u00a7cLife Bonus");


    final Type type;
    final String displayName;
    ToolUpgrade(Type type, String displayName){
        this.type = type;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public Type getType() {
        return this.type;
    }

    public enum Type {

        ALL,
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        TOOL,
        WEAPON,
        ARMOR,
        TOOL_AND_WEAPON
    }

}
