/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelZombieVillager;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderZombie;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderBrainyZombie extends RenderZombie {
/*    */   public RenderBrainyZombie(RenderManager rm) {
/* 20 */     super(rm);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     this.field_82431_q = 1;
/*    */   }
/*    */   
/*    */   private static final ResourceLocation field_110865_p = new ResourceLocation("thaumcraft", "textures/entity/bzombie.png");
/*    */   
/* 32 */   protected ResourceLocation func_110775_a(EntityZombie entity) { return field_110865_p; }
/*    */   private static final ResourceLocation field_110864_q = new ResourceLocation("thaumcraft", "textures/entity/bzombievil.png");
/*    */   private ModelBiped field_82434_o;
/*    */   
/*    */   protected void preRenderScale(EntityGiantBrainyZombie z, float par2) {
/* 37 */     GL11.glScalef(1.0F + z.getAnger(), 1.0F + z.getAnger(), 1.0F + z.getAnger());
/* 38 */     float q = Math.min(1.0F, z.getAnger()) / 2.0F;
/* 39 */     GL11.glColor3f(1.0F, 1.0F - q, 1.0F - q);
/*    */   }
/*    */   
/*    */   private ModelZombieVillager field_82432_p;
/*    */   private int field_82431_q;
/*    */   
/*    */   protected void preRenderCallback(EntityZombie par1EntityLiving, float par2) {
/* 46 */     if (par1EntityLiving instanceof EntityGiantBrainyZombie)
/* 47 */       preRenderScale((EntityGiantBrainyZombie)par1EntityLiving, par2); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderBrainyZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */