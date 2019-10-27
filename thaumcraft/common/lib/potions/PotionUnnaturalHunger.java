/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class PotionUnnaturalHunger
/*    */   extends Potion {
/* 13 */   public static Potion instance = null;
/* 14 */   private int statusIconIndex = -1;
/*    */ 
/*    */   
/*    */   public PotionUnnaturalHunger(boolean par2, int par3) {
/* 18 */     super(par2, par3);
/* 19 */     func_76399_b(0, 0);
/* 20 */     func_76390_b("potion.unhunger");
/* 21 */     func_76399_b(7, 1);
/* 22 */     func_76404_a(0.25D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public boolean func_76398_f() { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e() {
/* 33 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(rl);
/* 34 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 37 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */ 
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2) {
/* 41 */     if (!target.field_70170_p.field_72995_K && target instanceof EntityPlayer)
/*    */     {
/* 43 */       ((EntityPlayer)target).func_71020_j(0.025F * (par2 + 1));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   public boolean func_76397_a(int par1, int par2) { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\potions\PotionUnnaturalHunger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */