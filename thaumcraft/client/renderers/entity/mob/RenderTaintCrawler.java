/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelSilverfish;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintCrawler
/*    */   extends RenderLiving
/*    */ {
/* 18 */   private static final ResourceLocation textures = new ResourceLocation("thaumcraft", "textures/entity/crawler.png");
/*    */ 
/*    */ 
/*    */   
/* 22 */   public RenderTaintCrawler(RenderManager p_i46144_1_) { super(p_i46144_1_, new ModelSilverfish(), 0.2F); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   protected float func_180584_a(EntityTaintCrawler p_180584_1_) { return 180.0F; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   protected ResourceLocation getEntityTexture(EntityTaintCrawler entity) { return textures; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   protected float func_77037_a(EntityLivingBase p_77037_1_) { return func_180584_a((EntityTaintCrawler)p_77037_1_); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   protected ResourceLocation func_110775_a(Entity entity) { return getEntityTexture((EntityTaintCrawler)entity); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) { GL11.glScalef(0.7F, 0.7F, 0.7F); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderTaintCrawler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */