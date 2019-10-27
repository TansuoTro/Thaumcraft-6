/*    */ package thaumcraft.common.tiles.misc;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileNitor
/*    */   extends TileEntity
/*    */   implements ITickable
/*    */ {
/* 16 */   int count = 0;
/*    */ 
/*    */ 
/*    */   
/* 20 */   public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) { return (oldState.func_177230_c() != newState.func_177230_c()); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_73660_a() {
/* 25 */     if (this.field_145850_b.field_72995_K) {
/* 26 */       IBlockState state = this.field_145850_b.func_180495_p(func_174877_v());
/* 27 */       FXDispatcher.INSTANCE.drawNitorFlames((this.field_174879_c
/* 28 */           .func_177958_n() + 0.5F) + this.field_145850_b.field_73012_v.nextGaussian() * 0.025D, (this.field_174879_c.func_177956_o() + 0.45F) + this.field_145850_b.field_73012_v.nextGaussian() * 0.025D, (this.field_174879_c.func_177952_p() + 0.5F) + this.field_145850_b.field_73012_v.nextGaussian() * 0.025D, this.field_145850_b.field_73012_v
/* 29 */           .nextGaussian() * 0.0025D, this.field_145850_b.field_73012_v.nextFloat() * 0.06D, this.field_145850_b.field_73012_v.nextGaussian() * 0.0025D, 
/* 30 */           (state.func_177230_c().func_180659_g(state, this.field_145850_b, func_174877_v())).field_76291_p, 0);
/* 31 */       if (this.count++ % 10 == 0)
/* 32 */         FXDispatcher.INSTANCE.drawNitorCore((this.field_174879_c
/* 33 */             .func_177958_n() + 0.5F), (this.field_174879_c.func_177956_o() + 0.49F), (this.field_174879_c.func_177952_p() + 0.5F), 0.0D, 0.0D, 0.0D); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\misc\TileNitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */