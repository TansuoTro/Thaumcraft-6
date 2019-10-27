/*    */ package thaumcraft.client.renderers.entity;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.BufferBuilder;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityFallingBlock;
/*    */ import net.minecraft.util.EnumBlockRenderType;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.entities.EntityFallingTaint;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderFallingTaint
/*    */   extends Render
/*    */ {
/*    */   public RenderFallingTaint(RenderManager p_i46177_1_) {
/* 30 */     super(p_i46177_1_);
/* 31 */     this.field_76989_e = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(EntityFallingTaint p_180557_1_, double p_180557_2_, double p_180557_4_, double p_180557_6_, float p_180557_8_, float p_180557_9_) {
/* 36 */     if (p_180557_1_.getBlock() != null) {
/*    */       
/* 38 */       func_110776_a(TextureMap.field_110575_b);
/* 39 */       IBlockState iblockstate = p_180557_1_.getBlock();
/* 40 */       Block block = iblockstate.func_177230_c();
/* 41 */       BlockPos blockpos = new BlockPos(p_180557_1_);
/* 42 */       World world = p_180557_1_.getWorld();
/*    */       
/* 44 */       if (iblockstate != world.func_180495_p(blockpos) && block.func_149645_b(iblockstate) != EnumBlockRenderType.INVISIBLE)
/*    */       {
/* 46 */         if (block.func_149645_b(iblockstate) == EnumBlockRenderType.MODEL) {
/*    */           
/* 48 */           GlStateManager.func_179094_E();
/* 49 */           GlStateManager.func_179109_b((float)p_180557_2_, (float)p_180557_4_, (float)p_180557_6_);
/* 50 */           GlStateManager.func_179140_f();
/* 51 */           Tessellator tessellator = Tessellator.func_178181_a();
/* 52 */           BufferBuilder BufferBuilder = tessellator.func_178180_c();
/* 53 */           BufferBuilder.func_181668_a(7, DefaultVertexFormats.field_176600_a);
/*    */           
/* 55 */           int i = blockpos.func_177958_n();
/* 56 */           int j = blockpos.func_177956_o();
/* 57 */           int k = blockpos.func_177952_p();
/* 58 */           BufferBuilder.func_178969_c((-i - 0.5F), -j, (-k - 0.5F));
/* 59 */           BlockRendererDispatcher blockrendererdispatcher = Minecraft.func_71410_x().func_175602_ab();
/* 60 */           IBakedModel ibakedmodel = blockrendererdispatcher.func_184389_a(iblockstate);
/* 61 */           blockrendererdispatcher.func_175019_b().func_178267_a(world, ibakedmodel, iblockstate, blockpos, BufferBuilder, false);
/* 62 */           BufferBuilder.func_178969_c(0.0D, 0.0D, 0.0D);
/* 63 */           tessellator.func_78381_a();
/* 64 */           GlStateManager.func_179145_e();
/* 65 */           GlStateManager.func_179121_F();
/* 66 */           super.func_76986_a(p_180557_1_, p_180557_2_, p_180557_4_, p_180557_6_, p_180557_8_, p_180557_9_);
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 74 */   protected ResourceLocation getEntityTexture(EntityFallingBlock entity) { return TextureMap.field_110575_b; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 80 */   protected ResourceLocation func_110775_a(Entity entity) { return getEntityTexture((EntityFallingBlock)entity); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 86 */   public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) { doRender((EntityFallingTaint)entity, x, y, z, p_76986_8_, partialTicks); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\RenderFallingTaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */