/*    */ package thaumcraft.common.entities.monster.tainted;
/*    */ 
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ 
/*    */ public class EntityTaintSeedPrime
/*    */   extends EntityTaintSeed
/*    */ {
/*    */   public EntityTaintSeedPrime(World par1World) {
/* 12 */     super(par1World);
/* 13 */     func_70105_a(2.0F, 2.0F);
/* 14 */     this.field_70728_aV = 12;
/*    */   }
/*    */ 
/*    */   
/* 18 */   protected int getArea() { return 2; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_110147_ax() {
/* 24 */     super.func_110147_ax();
/* 25 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0D);
/* 26 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
/* 27 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_70628_a(boolean flag, int i) {
/* 32 */     func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F);
/* 33 */     if (this.field_70146_Z.nextBoolean()) func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F); 
/* 34 */     if (this.field_70146_Z.nextBoolean()) func_70099_a(ConfigItems.FLUX_CRYSTAL.func_77946_l(), this.field_70131_O / 2.0F); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\monster\tainted\EntityTaintSeedPrime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */