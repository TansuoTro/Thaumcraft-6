/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class PotionBlurredVision
/*    */   extends Potion {
/* 12 */   public static Potion instance = null;
/* 13 */   private int statusIconIndex = -1;
/*    */ 
/*    */   
/*    */   public PotionBlurredVision(boolean par2, int par3) {
/* 17 */     super(par2, par3);
/* 18 */     func_76399_b(0, 0);
/* 19 */     func_76390_b("potion.blurred");
/* 20 */     func_76399_b(5, 2);
/* 21 */     func_76404_a(0.25D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public boolean func_76398_f() { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e() {
/* 32 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(rl);
/* 33 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 36 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2) {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\potions\PotionBlurredVision.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */