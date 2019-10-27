/*    */ package thaumcraft.api.golems.parts;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ 
/*    */ public class GolemMaterial
/*    */ {
/* 10 */   protected static GolemMaterial[] materials = new GolemMaterial[1];
/*    */   
/*    */   public byte id;
/*    */   
/*    */   public String key;
/*    */   
/*    */   public String[] research;
/*    */   
/*    */   public ResourceLocation texture;
/*    */   
/*    */   public int itemColor;
/*    */   
/*    */   public int healthMod;
/*    */   
/*    */   public int armor;
/*    */   
/*    */   public int damage;
/*    */   
/*    */   public ItemStack componentBase;
/*    */   public ItemStack componentMechanism;
/*    */   public EnumGolemTrait[] traits;
/*    */   
/*    */   public GolemMaterial(String key, String[] research, ResourceLocation texture, int itemColor, int hp, int armor, int damage, ItemStack compb, ItemStack compm, EnumGolemTrait[] tags) {
/* 33 */     this.key = key;
/* 34 */     this.research = research;
/* 35 */     this.texture = texture;
/* 36 */     this.itemColor = itemColor;
/* 37 */     this.componentBase = compb;
/* 38 */     this.componentMechanism = compm;
/* 39 */     this.healthMod = hp;
/* 40 */     this.armor = armor;
/* 41 */     this.traits = tags;
/* 42 */     this.damage = damage;
/*    */   }
/*    */   
/* 45 */   private static byte lastID = 0;
/*    */   public static void register(GolemMaterial thing) {
/* 47 */     thing.id = lastID;
/* 48 */     lastID = (byte)(lastID + 1);
/*    */     
/* 50 */     if (thing.id >= materials.length) {
/* 51 */       GolemMaterial[] temp = new GolemMaterial[thing.id + 1];
/* 52 */       System.arraycopy(materials, 0, temp, 0, materials.length);
/* 53 */       materials = temp;
/*    */     } 
/* 55 */     materials[thing.id] = thing;
/*    */   }
/*    */ 
/*    */   
/* 59 */   public String getLocalizedName() { return I18n.func_74838_a("golem.material." + this.key.toLowerCase()); }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public String getLocalizedDescription() { return I18n.func_74838_a("golem.material.text." + this.key.toLowerCase()); }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public static GolemMaterial[] getMaterials() { return materials; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\parts\GolemMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */