/*    */ package thaumcraft.common.entities.monster.mods;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ 
/*    */ public class ChampionModifier {
/*    */   String name;
/*    */   public int id;
/*    */   
/*    */   public ChampionModifier(int id, String name, int type, IChampionModifierEffect effect, UUID iud) {
/* 10 */     this.name = "";
/* 11 */     this.id = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 16 */     this.type = 0;
/*    */     
/* 18 */     this.effect = null;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.name = name;
/* 24 */     this.id = id;
/* 25 */     this.type = type;
/* 26 */     this.effect = effect;
/* 27 */     this.attributeMod = new AttributeModifier(iud, name, (id + 2), 0);
/*    */   }
/*    */   public int type; public IChampionModifierEffect effect; public AttributeModifier attributeMod;
/*    */   
/* 31 */   public String getModNameLocalized() { return I18n.func_74838_a("champion.mod." + this.name); }
/*    */   
/*    */   public static ChampionModifier[] mods = { 
/* 34 */       new ChampionModifier(0, "bold", -1, new ChampionModBold(), 
/* 35 */         UUID.fromString("40289aa1-907f-4ac6-ad79-e6681efe2cbc")), new ChampionModifier(1, "spine", 2, new ChampionModSpined(), 
/* 36 */         UUID.fromString("365eead5-3f15-42a8-9e68-36100faef945")), new ChampionModifier(2, "armor", 2, new ChampionModArmored(), 
/* 37 */         UUID.fromString("4e23758d-348e-42a8-8de6-08ae0a59033c")), new ChampionModifier(3, "mighty", -1, new ChampionModMighty(), 
/* 38 */         UUID.fromString("6d2ffe79-f034-4a06-b288-e1916c21e385")), new ChampionModifier(4, "grim", 1, new ChampionModGrim(), 
/* 39 */         UUID.fromString("0f23321e-f921-4246-90b8-21ef202de224")), new ChampionModifier(5, "warded", 0, new ChampionModWarded(), 
/* 40 */         UUID.fromString("b622c4d8-abc6-4db7-b3ee-5cf71b8e5286")), new ChampionModifier(6, "warp", 1, new ChampionModWarp(), 
/* 41 */         UUID.fromString("107da049-af7a-4409-989a-6de23c8fe036")), new ChampionModifier(7, "undying", 0, new ChampionModUndying(), 
/* 42 */         UUID.fromString("cb9484d3-6255-4893-a4f2-3ecc375692ee")), new ChampionModifier(8, "fiery", 1, new ChampionModFire(), 
/* 43 */         UUID.fromString("6b567fdf-9245-48f5-8314-f93fe5db1427")), new ChampionModifier(9, "sickly", 1, new ChampionModSickly(), 
/* 44 */         UUID.fromString("b5718868-9ab0-424c-af1f-8b35e836b46e")), new ChampionModifier(10, "venomous", 1, new ChampionModPoison(), 
/* 45 */         UUID.fromString("ab9a132e-c619-4c0a-a103-10cbbcfba1a2")), new ChampionModifier(11, "vampiric", 1, new ChampionModVampire(), 
/* 46 */         UUID.fromString("3412251e-af81-4c3c-93ba-2e1c33b049ea")), new ChampionModifier(12, "infested", 2, new ChampionModInfested(), 
/* 47 */         UUID.fromString("9c577fbe-ddbc-4ea2-a661-770ea775f43b")), new ChampionModifier(13, "tainted", 0, new ChampionModTainted(), 
/* 48 */         UUID.fromString("a3bb2595-8221-4140-bc73-538abcd1bbd2")) };
/*    */ 
/*    */ 
/*    */   
/* 52 */   public static AttributeModifier ATTRIBUTE_MOD_NONE = new AttributeModifier(UUID.fromString("1e645a3d-9115-4807-a61c-705172839f87"), "normal", 1.0D, 0);
/* 53 */   public static AttributeModifier ATTRIBUTE_MINUS_ONE = new AttributeModifier(UUID.fromString("f48eb416-8321-46e6-8c07-a07bed729a0c"), "minus1", -1.0D, 0);
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\mods\ChampionModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */