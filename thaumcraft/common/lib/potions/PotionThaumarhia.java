/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ 
/*    */ public class PotionThaumarhia
/*    */   extends Potion {
/* 14 */   public static Potion instance = null;
/* 15 */   private int statusIconIndex = -1;
/*    */ 
/*    */   
/*    */   public PotionThaumarhia(boolean par2, int par3) {
/* 19 */     super(par2, par3);
/* 20 */     func_76399_b(0, 0);
/* 21 */     func_76390_b("potion.thaumarhia");
/* 22 */     func_76399_b(7, 2);
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
/* 42 */     if (!target.field_70170_p.field_72995_K && target.field_70170_p.field_73012_v.nextInt(15) == 0)
/*    */     {
/* 44 */       if (target.field_70170_p.func_175623_d(new BlockPos(target))) {
/* 45 */         target.field_70170_p.func_175656_a(new BlockPos(target), BlocksTC.fluxGoo.func_176223_P());
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public boolean func_76397_a(int par1, int par2) { return (par1 % 20 == 0); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\potions\PotionThaumarhia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */