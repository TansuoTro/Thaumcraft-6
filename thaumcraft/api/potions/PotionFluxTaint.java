/*    */ package thaumcraft.api.potions;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.damagesource.DamageSourceThaumcraft;
/*    */ 
/*    */ 
/*    */ public class PotionFluxTaint
/*    */   extends Potion
/*    */ {
/* 17 */   public static Potion instance = null;
/* 18 */   private int statusIconIndex = -1;
/*    */ 
/*    */   
/*    */   public PotionFluxTaint(boolean par2, int par3) {
/* 22 */     super(par2, par3);
/* 23 */     func_76399_b(3, 1);
/* 24 */     func_76404_a(0.25D);
/* 25 */     func_76390_b("potion.flux_taint");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean func_76398_f() { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_76392_e() {
/* 36 */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(rl);
/* 37 */     return super.func_76392_e();
/*    */   }
/*    */   
/* 40 */   static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");
/*    */ 
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int strength) {
/* 44 */     IAttributeInstance cai = target.func_110148_a(ThaumcraftApiHelper.CHAMPION_MOD);
/* 45 */     if (target instanceof thaumcraft.api.entities.ITaintedMob || (cai != null && (int)cai.func_111126_e() == 13)) {
/* 46 */       target.func_70691_i(1.0F);
/*    */     }
/* 48 */     else if (!target.func_70662_br() && !(target instanceof net.minecraft.entity.player.EntityPlayer)) {
/*    */       
/* 50 */       target.func_70097_a(DamageSourceThaumcraft.taint, 1.0F);
/*    */     
/*    */     }
/* 53 */     else if (!target.func_70662_br() && (target.func_110138_aP() > 1.0F || target instanceof net.minecraft.entity.player.EntityPlayer)) {
/*    */       
/* 55 */       target.func_70097_a(DamageSourceThaumcraft.taint, 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_76397_a(int par1, int par2) {
/* 62 */     int k = 40 >> par2;
/* 63 */     return (k > 0) ? ((par1 % k == 0)) : true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\potions\PotionFluxTaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */