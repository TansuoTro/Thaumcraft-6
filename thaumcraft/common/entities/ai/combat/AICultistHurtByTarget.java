/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.EntityAITarget;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ 
/*    */ 
/*    */ public class AICultistHurtByTarget
/*    */   extends EntityAITarget
/*    */ {
/*    */   boolean entityCallsForHelp;
/*    */   private int revengeTimerOld;
/*    */   
/*    */   public AICultistHurtByTarget(EntityCreature owner, boolean callsHelp) {
/* 17 */     super(owner, false);
/* 18 */     this.entityCallsForHelp = callsHelp;
/* 19 */     func_75248_a(1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 25 */     int i = this.field_75299_d.func_142015_aE();
/* 26 */     EntityLivingBase entitylivingbase = this.field_75299_d.func_70643_av();
/* 27 */     return (i != this.revengeTimerOld && entitylivingbase != null && func_75296_a(entitylivingbase, false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75249_e() {
/* 33 */     this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
/* 34 */     this.field_188509_g = this.field_75299_d.func_70638_az();
/* 35 */     this.revengeTimerOld = this.field_75299_d.func_142015_aE();
/* 36 */     this.field_188510_h = 300;
/*    */     
/* 38 */     if (this.entityCallsForHelp)
/*    */     {
/* 40 */       alertOthers();
/*    */     }
/*    */     
/* 43 */     super.func_75249_e();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void alertOthers() {
/* 48 */     double d0 = func_111175_f();
/*    */     
/* 50 */     for (EntityCreature entitycreature : this.field_75299_d.field_70170_p.func_72872_a(thaumcraft.common.entities.monster.cult.EntityCultist.class, (new AxisAlignedBB(this.field_75299_d.field_70165_t, this.field_75299_d.field_70163_u, this.field_75299_d.field_70161_v, this.field_75299_d.field_70165_t + 1.0D, this.field_75299_d.field_70163_u + 1.0D, this.field_75299_d.field_70161_v + 1.0D))
/*    */         
/* 52 */         .func_72314_b(d0, 10.0D, d0))) {
/*    */       
/* 54 */       if (this.field_75299_d != entitycreature && entitycreature.func_70638_az() == null && (!(this.field_75299_d instanceof EntityTameable) || ((EntityTameable)this.field_75299_d)
/* 55 */         .func_70902_q() == ((EntityTameable)entitycreature).func_70902_q()) && 
/* 56 */         !entitycreature.func_184191_r(this.field_75299_d.func_70643_av()))
/*    */       {
/* 58 */         setEntityAttackTarget(entitycreature, this.field_75299_d.func_70643_av());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 65 */   protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn) { creatureIn.func_70624_b(entityLivingBaseIn); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\ai\combat\AICultistHurtByTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */