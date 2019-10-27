/*    */ package thaumcraft.common.entities.ai.combat;
/*    */ 
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ 
/*    */ public class EntityCritterAIAttackMelee
/*    */   extends EntityAIAttackMelee
/*    */ {
/* 19 */   public EntityCritterAIAttackMelee(EntityCreature creature, double speedIn, boolean useLongMemory) { super(creature, speedIn, useLongMemory); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_190102_a(EntityLivingBase target, double range) {
/* 26 */     double d0 = func_179512_a(target);
/*    */     
/* 28 */     if (range <= d0 && this.field_75439_d <= 0) {
/*    */       
/* 30 */       this.field_75439_d = 20;
/* 31 */       this.field_75441_b.func_184609_a(EnumHand.MAIN_HAND);
/* 32 */       attackEntityAsMob(this.field_75441_b, target);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean attackEntityAsMob(EntityLiving attacker, Entity target) {
/* 38 */     float f = Math.max(2.0F, (attacker.field_70131_O + attacker.field_70130_N) * 2.0F);
/* 39 */     if (attacker.func_110148_a(SharedMonsterAttributes.field_111264_e) != null) {
/* 40 */       f = (float)attacker.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*    */     }
/* 42 */     int i = 0;
/*    */     
/* 44 */     if (target instanceof EntityLivingBase) {
/*    */       
/* 46 */       f += EnchantmentHelper.func_152377_a(attacker.func_184614_ca(), ((EntityLivingBase)target).func_70668_bt());
/* 47 */       i += EnchantmentHelper.func_77501_a(attacker);
/*    */     } 
/*    */     
/* 50 */     boolean flag = target.func_70097_a(DamageSource.func_76358_a(attacker), f);
/*    */     
/* 52 */     if (flag) {
/*    */       
/* 54 */       if (i > 0 && target instanceof EntityLivingBase) {
/*    */         
/* 56 */         ((EntityLivingBase)target).func_70653_a(attacker, i * 0.5F, MathHelper.func_76126_a(attacker.field_70177_z * 0.017453292F), -MathHelper.func_76134_b(attacker.field_70177_z * 0.017453292F));
/* 57 */         attacker.field_70159_w *= 0.6D;
/* 58 */         attacker.field_70179_y *= 0.6D;
/*    */       } 
/*    */       
/* 61 */       int j = EnchantmentHelper.func_90036_a(attacker);
/*    */       
/* 63 */       if (j > 0)
/*    */       {
/* 65 */         target.func_70015_d(j * 4);
/*    */       }
/*    */       
/* 68 */       if (target instanceof EntityPlayer) {
/*    */         
/* 70 */         EntityPlayer entityplayer = (EntityPlayer)target;
/* 71 */         ItemStack itemstack = attacker.func_184614_ca();
/* 72 */         ItemStack itemstack1 = entityplayer.func_184587_cr() ? entityplayer.func_184607_cu() : ItemStack.field_190927_a;
/*    */         
/* 74 */         if (!itemstack.func_190926_b() && !itemstack1.func_190926_b() && itemstack.func_77973_b().canDisableShield(itemstack, itemstack1, entityplayer, attacker) && itemstack1.func_77973_b().isShield(itemstack1, entityplayer)) {
/*    */           
/* 76 */           float f1 = 0.25F + EnchantmentHelper.func_185293_e(attacker) * 0.05F;
/*    */           
/* 78 */           if (attacker.func_70681_au().nextFloat() < f1) {
/*    */             
/* 80 */             entityplayer.func_184811_cZ().func_185145_a(itemstack1.func_77973_b(), 100);
/* 81 */             attacker.field_70170_p.func_72960_a(entityplayer, (byte)30);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 87 */       if (target instanceof EntityLivingBase)
/*    */       {
/* 89 */         EnchantmentHelper.func_151384_a((EntityLivingBase)target, attacker);
/*    */       }
/*    */       
/* 92 */       EnchantmentHelper.func_151385_b(attacker, target);
/*    */     } 
/*    */     
/* 95 */     return flag;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\ai\combat\EntityCritterAIAttackMelee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */