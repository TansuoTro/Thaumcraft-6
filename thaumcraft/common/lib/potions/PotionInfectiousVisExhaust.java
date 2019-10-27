/*    */ package thaumcraft.common.lib.potions;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.potions.PotionVisExhaust;
/*    */ 
/*    */ public class PotionInfectiousVisExhaust
/*    */   extends Potion {
/* 15 */   public static Potion instance = null;
/*    */ 
/*    */   
/* 18 */   private int statusIconIndex = -1;
/*    */   
/*    */   public PotionInfectiousVisExhaust(boolean par2, int par3) {
/* 21 */     super(par2, par3);
/* 22 */     func_76399_b(0, 0);
/* 23 */     func_76390_b("potion.infvisexhaust");
/* 24 */     func_76399_b(6, 1);
/* 25 */     func_76404_a(0.25D);
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
/*    */ 
/*    */   
/*    */   public void func_76394_a(EntityLivingBase target, int par2) {
/* 46 */     List<EntityLivingBase> targets = target.field_70170_p.func_72872_a(EntityLivingBase.class, target
/* 47 */         .func_174813_aQ().func_72314_b(4.0D, 4.0D, 4.0D));
/* 48 */     if (targets.size() > 0) {
/* 49 */       for (EntityLivingBase e : targets) {
/* 50 */         if (!e.func_70644_a(instance)) {
/* 51 */           if (par2 > 0) {
/* 52 */             e.func_70690_d(new PotionEffect(instance, 6000, par2 - 1, false, true));
/*    */             
/*    */             continue;
/*    */           } 
/* 56 */           e.func_70690_d(new PotionEffect(PotionVisExhaust.instance, 6000, 0, false, true));
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public boolean func_76397_a(int par1, int par2) { return (par1 % 40 == 0); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\potions\PotionInfectiousVisExhaust.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */