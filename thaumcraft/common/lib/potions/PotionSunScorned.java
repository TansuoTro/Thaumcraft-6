/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class PotionSunScorned
/*    */   extends Potion {
/* 14 */   public static Potion instance = null;
/* 15 */   private int statusIconIndex = -1;
/*    */ 
/*    */   
/*    */   public PotionSunScorned(boolean par2, int par3) {
/* 19 */     super(par2, par3);
/* 20 */     func_76399_b(0, 0);
/* 21 */     func_76390_b("potion.sunscorned");
/* 22 */     func_76399_b(6, 2);
/* 23 */     func_76404_a(0.25D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public boolean func_76398_f() { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e() {
/* 34 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(rl);
/* 35 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 38 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */ 
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2) {
/* 42 */     if (!target.field_70170_p.field_72995_K) {
/*    */       
/* 44 */       float f = target.func_70013_c();
/* 45 */       if (f > 0.5F && target.field_70170_p.field_73012_v.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && target.field_70170_p
/* 46 */         .func_175710_j(new BlockPos(
/* 47 */             MathHelper.func_76128_c(target.field_70165_t), 
/* 48 */             MathHelper.func_76128_c(target.field_70163_u), 
/* 49 */             MathHelper.func_76128_c(target.field_70161_v)))) {
/*    */         
/* 51 */         target.func_70015_d(4);
/*    */       }
/* 53 */       else if (f < 0.25F && target.field_70170_p.field_73012_v.nextFloat() > f * 2.0F) {
/*    */         
/* 55 */         target.func_70691_i(1.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public boolean func_76397_a(int par1, int par2) { return (par1 % 40 == 0); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\potions\PotionSunScorned.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */