/*    */ package thaumcraft.api.golems.parts;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IRangedAttackMob;
/*    */ import net.minecraft.entity.ai.EntityAIAttackRanged;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import thaumcraft.api.golems.EnumGolemTrait;
/*    */ import thaumcraft.api.golems.IGolemAPI;
/*    */ 
/*    */ public class GolemArm
/*    */ {
/* 14 */   protected static GolemArm[] arms = new GolemArm[1];
/*    */   
/*    */   public byte id;
/*    */   
/*    */   public String key;
/*    */   
/*    */   public String[] research;
/*    */   
/*    */   public ResourceLocation icon;
/*    */   public Object[] components;
/*    */   public EnumGolemTrait[] traits;
/*    */   public IArmFunction function;
/*    */   public PartModel model;
/*    */   
/*    */   public GolemArm(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, EnumGolemTrait[] tags) {
/* 29 */     this.key = key;
/* 30 */     this.research = research;
/* 31 */     this.icon = icon;
/* 32 */     this.components = comp;
/* 33 */     this.traits = tags;
/* 34 */     this.model = model;
/* 35 */     this.function = null;
/*    */   }
/*    */   
/*    */   public GolemArm(String key, String[] research, ResourceLocation icon, PartModel model, Object[] comp, IArmFunction function, EnumGolemTrait[] tags) {
/* 39 */     this(key, research, icon, model, comp, tags);
/* 40 */     this.function = function;
/*    */   }
/*    */   
/* 43 */   private static byte lastID = 0;
/*    */   public static void register(GolemArm thing) {
/* 45 */     thing.id = lastID;
/* 46 */     lastID = (byte)(lastID + 1);
/*    */     
/* 48 */     if (thing.id >= arms.length) {
/* 49 */       GolemArm[] temp = new GolemArm[thing.id + 1];
/* 50 */       System.arraycopy(arms, 0, temp, 0, arms.length);
/* 51 */       arms = temp;
/*    */     } 
/* 53 */     arms[thing.id] = thing;
/*    */   }
/*    */ 
/*    */   
/* 57 */   public String getLocalizedName() { return I18n.func_74838_a("golem.arm." + this.key.toLowerCase()); }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public String getLocalizedDescription() { return I18n.func_74838_a("golem.arm.text." + this.key.toLowerCase()); }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public static GolemArm[] getArms() { return arms; }
/*    */   
/*    */   public static interface IArmFunction extends IGenericFunction {
/*    */     void onMeleeAttack(IGolemAPI param1IGolemAPI, Entity param1Entity);
/*    */     
/*    */     void onRangedAttack(IGolemAPI param1IGolemAPI, EntityLivingBase param1EntityLivingBase, float param1Float);
/*    */     
/*    */     EntityAIAttackRanged getRangedAttackAI(IRangedAttackMob param1IRangedAttackMob);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\parts\GolemArm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */