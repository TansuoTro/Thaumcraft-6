/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderSlime;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.monster.EntitySlime;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderThaumicSlime
/*    */   extends RenderSlime {
/* 15 */   public RenderThaumicSlime(RenderManager p_i46141_1_, ModelBase p_i46141_2_, float p_i46141_3_) { super(p_i46141_1_); }
/*    */ 
/*    */   
/* 18 */   private static final ResourceLocation slimeTexture = new ResourceLocation("thaumcraft", "textures/entity/tslime.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   protected ResourceLocation func_110775_a(EntitySlime entity) { return slimeTexture; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderThaumicSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */