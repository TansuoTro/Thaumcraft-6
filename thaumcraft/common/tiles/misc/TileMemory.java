/*    */ package thaumcraft.common.tiles.misc;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ 
/*    */ public class TileMemory
/*    */   extends TileEntity
/*    */ {
/* 13 */   public IBlockState oldblock = Blocks.field_150350_a.func_176223_P();
/*    */ 
/*    */   
/*    */   public NBTTagCompound tileEntityCompound;
/*    */ 
/*    */ 
/*    */   
/*    */   public TileMemory() {}
/*    */ 
/*    */   
/* 23 */   public TileMemory(IBlockState bi) { this.oldblock = bi; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 29 */     super.func_145839_a(nbttagcompound);
/* 30 */     Block b = Block.func_149729_e(nbttagcompound.func_74762_e("oldblock"));
/* 31 */     int meta = nbttagcompound.func_74762_e("oldmeta");
/* 32 */     this.oldblock = b.func_176203_a(meta);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound func_189515_b(NBTTagCompound nbttagcompound) {
/* 38 */     super.func_189515_b(nbttagcompound);
/* 39 */     nbttagcompound.func_74768_a("oldblock", Block.func_149682_b(this.oldblock.func_177230_c()));
/* 40 */     nbttagcompound.func_74768_a("oldmeta", this.oldblock.func_177230_c().func_176201_c(this.oldblock));
/* 41 */     return nbttagcompound;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\misc\TileMemory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */