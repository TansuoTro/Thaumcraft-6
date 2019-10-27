/*    */ package thaumcraft.common.tiles.devices;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*    */ import thaumcraft.common.tiles.TileThaumcraft;
/*    */ import thaumcraft.common.world.aura.AuraChunk;
/*    */ import thaumcraft.common.world.aura.AuraHandler;
/*    */ 
/*    */ public class TileDioptra extends TileThaumcraft implements ITickable {
/*    */   public int counter;
/*    */   
/*    */   public TileDioptra() {
/* 18 */     this.counter = 0;
/*    */     
/* 20 */     this.grid_amt = new byte[169];
/* 21 */     this.grid_amt_p = new byte[169];
/*    */ 
/*    */ 
/*    */     
/* 25 */     Arrays.fill(this.grid_amt, (byte)0);
/* 26 */     Arrays.fill(this.grid_amt_p, (byte)0);
/*    */   }
/*    */   public byte[] grid_amt; private byte[] grid_amt_p;
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox() {
/* 32 */     return new AxisAlignedBB(
/* 33 */         func_174877_v().func_177958_n() - 0.3D, func_174877_v().func_177956_o() - 0.3D, func_174877_v().func_177952_p() - 0.3D, 
/* 34 */         func_174877_v().func_177958_n() + 1.3D, func_174877_v().func_177956_o() + 2.3D, func_174877_v().func_177952_p() + 1.3D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73660_a() {
/* 39 */     this.counter++;
/* 40 */     if (!this.field_145850_b.field_72995_K) {
/* 41 */       if (this.counter % 20 == 0) {
/* 42 */         Arrays.fill(this.grid_amt, (byte)0);
/* 43 */         for (int xx = 0; xx < 13; xx++) {
/* 44 */           for (int zz = 0; zz < 13; zz++) {
/* 45 */             AuraChunk ac = AuraHandler.getAuraChunk(this.field_145850_b.field_73011_w.getDimension(), (this.field_174879_c
/* 46 */                 .func_177958_n() >> 4) + xx - 6, (this.field_174879_c.func_177952_p() >> 4) + zz - 6);
/* 47 */             if (ac != null)
/* 48 */               if (BlockStateUtils.isEnabled(func_145832_p())) {
/* 49 */                 this.grid_amt[xx + zz * 13] = (byte)(int)Math.min(64.0F, ac.getVis() / 500.0F * 64.0F);
/*    */               } else {
/* 51 */                 this.grid_amt[xx + zz * 13] = (byte)(int)Math.min(64.0F, ac.getFlux() / 500.0F * 64.0F);
/*    */               }  
/*    */           } 
/*    */         } 
/* 55 */         func_70296_d();
/* 56 */         syncTile(false);
/*    */       } 
/*    */     } else {
/*    */       
/* 60 */       this.counter = 0;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void readSyncNBT(NBTTagCompound nbt) { if (nbt.func_74764_b("grid_a")) this.grid_amt = nbt.func_74770_j("grid_a");
/*    */      }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) {
/* 75 */     nbt.func_74773_a("grid_a", this.grid_amt);
/* 76 */     return nbt;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileDioptra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */