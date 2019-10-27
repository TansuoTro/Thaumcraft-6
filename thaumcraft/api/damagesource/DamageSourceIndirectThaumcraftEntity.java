/*    */ package thaumcraft.api.damagesource;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EntityDamageSourceIndirect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageSourceIndirectThaumcraftEntity
/*    */   extends EntityDamageSourceIndirect
/*    */ {
/*    */   private boolean fireDamage;
/*    */   private float hungerDamage;
/*    */   private boolean isUnblockable;
/*    */   
/* 16 */   public DamageSourceIndirectThaumcraftEntity(String par1Str, Entity par2Entity, Entity par3Entity) { super(par1Str, par2Entity, par3Entity); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DamageSource func_76361_j() {
/* 22 */     this.fireDamage = true;
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource func_76348_h() {
/* 28 */     this.isUnblockable = true;
/* 29 */     this.hungerDamage = 0.0F;
/* 30 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\damagesource\DamageSourceIndirectThaumcraftEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */