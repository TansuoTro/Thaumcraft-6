/*    */ package thaumcraft.common.entities.ai.pech;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ 
/*    */ public class AIPechTradePlayer
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityPech villager;
/*    */   
/*    */   public AIPechTradePlayer(EntityPech par1EntityVillager) {
/* 12 */     this.villager = par1EntityVillager;
/* 13 */     func_75248_a(5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 21 */     if (!this.villager.func_70089_S())
/*    */     {
/* 23 */       return false;
/*    */     }
/* 25 */     if (this.villager.func_70090_H())
/*    */     {
/* 27 */       return false;
/*    */     }
/* 29 */     if (!this.villager.isTamed())
/*    */     {
/* 31 */       return false;
/*    */     }
/* 33 */     if (!this.villager.field_70122_E)
/*    */     {
/* 35 */       return false;
/*    */     }
/* 37 */     if (this.villager.field_70133_I)
/*    */     {
/* 39 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 43 */     return this.villager.trading;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public void func_75249_e() { this.villager.func_70661_as().func_75499_g(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void func_75251_c() { this.villager.trading = false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\ai\pech\AIPechTradePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */