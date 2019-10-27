/*    */ package thaumcraft.common.tiles.devices;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntityChest;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileHungryChest
/*    */   extends TileEntityChest
/*    */ {
/*    */   public void func_145979_i() {
/* 19 */     if (!this.field_145984_a)
/*    */     {
/* 21 */       this.field_145984_a = true;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public boolean canRenderBreaking() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_174886_c(EntityPlayer player) {
/* 33 */     if (!player.func_175149_v() && func_145838_q() instanceof thaumcraft.common.blocks.devices.BlockHungryChest) {
/*    */       
/* 35 */       this.field_145987_o--;
/* 36 */       this.field_145850_b.func_175641_c(this.field_174879_c, func_145838_q(), 1, this.field_145987_o);
/* 37 */       this.field_145850_b.func_175685_c(this.field_174879_c, func_145838_q(), true);
/* 38 */       this.field_145850_b.func_175685_c(this.field_174879_c.func_177977_b(), func_145838_q(), true);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) { return (oldState.func_177230_c() != newState.func_177230_c()); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileHungryChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */