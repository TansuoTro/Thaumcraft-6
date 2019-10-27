/*    */ package thaumcraft.common.entities.monster;
/*    */ 
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.network.datasync.DataParameter;
/*    */ import net.minecraft.network.datasync.DataSerializers;
/*    */ import net.minecraft.network.datasync.EntityDataManager;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityGiantBrainyZombie
/*    */   extends EntityBrainyZombie
/*    */ {
/*    */   public EntityGiantBrainyZombie(World world) {
/* 18 */     super(world);
/* 19 */     this.field_70728_aV = 15;
/*    */ 
/*    */     
/* 22 */     this.field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.4F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70636_d() {
/* 28 */     super.func_70636_d();
/* 29 */     if (getAnger() > 1.0F) {
/* 30 */       setAnger(getAnger() - 0.002F);
/* 31 */       func_70105_a(0.6F + getAnger() * 0.6F, 1.95F + getAnger() * 1.95F);
/* 32 */       func_146069_a(1.0F);
/*    */     } 
/*    */     
/* 35 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((7.0F + (getAnger() - 1.0F) * 5.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float func_70047_e() {
/* 41 */     float f = 1.74F + getAnger() * 1.74F;
/*    */     
/* 43 */     if (func_70631_g_())
/*    */     {
/* 45 */       f = (float)(f - 0.81D);
/*    */     }
/*    */     
/* 48 */     return f;
/*    */   }
/*    */   
/* 51 */   private static final DataParameter<Float> ANGER = EntityDataManager.func_187226_a(EntityGiantBrainyZombie.class, DataSerializers.field_187193_c);
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70088_a() {
/* 56 */     super.func_70088_a();
/* 57 */     func_184212_Q().func_187214_a(ANGER, Float.valueOf(0.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public float getAnger() { return ((Float)func_184212_Q().func_187225_a(ANGER)).floatValue(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void setAnger(float par1) { func_184212_Q().func_187227_b(ANGER, Float.valueOf(par1)); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/* 72 */     setAnger(Math.min(2.0F, getAnger() + 0.1F));
/* 73 */     return super.func_70097_a(par1DamageSource, par2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_110147_ax() {
/* 79 */     super.func_110147_ax();
/* 80 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(60.0D);
/* 81 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_70628_a(boolean flag, int i) {
/* 87 */     for (int a = 0; a < 6; ) { if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 2);  a++; }
/* 88 */      for (int a = 0; a < 6; ) { if (this.field_70170_p.field_73012_v.nextBoolean()) func_145779_a(Items.field_151078_bh, 2);  a++; }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\EntityGiantBrainyZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */