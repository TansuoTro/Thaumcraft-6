/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.renderers.models.entity.ModelTaintacle;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderTaintacle
/*    */   extends RenderLiving
/*    */ {
/* 16 */   public RenderTaintacle(RenderManager rm, float shadow, int length) { super(rm, new ModelTaintacle(length, false), shadow); }
/*    */ 
/*    */   
/* 19 */   private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/entity/taintacle.png");
/*    */ 
/*    */   
/* 22 */   protected ResourceLocation func_110775_a(Entity entity) { return rl; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderTaintacle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */