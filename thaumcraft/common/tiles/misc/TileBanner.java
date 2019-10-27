/*    */ package thaumcraft.common.tiles.misc;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.common.blocks.basic.BlockBannerTC;
/*    */ import thaumcraft.common.tiles.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileBanner
/*    */   extends TileThaumcraft
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox() {
/* 18 */     return new AxisAlignedBB(
/* 19 */         func_174877_v().func_177958_n(), (func_174877_v().func_177956_o() - 1), func_174877_v().func_177952_p(), (
/* 20 */         func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 2), (func_174877_v().func_177952_p() + 1));
/*    */   }
/*    */   
/* 23 */   private byte facing = 0;
/* 24 */   private Aspect aspect = null;
/*    */   
/*    */   private boolean onWall = false;
/*    */   
/* 28 */   public byte getBannerFacing() { return this.facing; }
/*    */ 
/*    */   
/*    */   public void setBannerFacing(byte face) {
/* 32 */     this.facing = face;
/* 33 */     func_70296_d();
/*    */   }
/*    */ 
/*    */   
/* 37 */   public boolean getWall() { return this.onWall; }
/*    */ 
/*    */   
/*    */   public void setWall(boolean b) {
/* 41 */     this.onWall = b;
/* 42 */     func_70296_d();
/*    */   }
/*    */ 
/*    */   
/*    */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/* 47 */     this.facing = nbttagcompound.func_74771_c("facing");
/* 48 */     String as = nbttagcompound.func_74779_i("aspect");
/* 49 */     if (as != null && as.length() > 0) { setAspect(Aspect.getAspect(as)); }
/* 50 */     else { this.aspect = null; }
/* 51 */      this.onWall = nbttagcompound.func_74767_n("wall");
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/* 56 */     nbttagcompound.func_74774_a("facing", this.facing);
/* 57 */     nbttagcompound.func_74778_a("aspect", (getAspect() == null) ? "" : getAspect().getTag());
/* 58 */     nbttagcompound.func_74757_a("wall", this.onWall);
/* 59 */     return nbttagcompound;
/*    */   }
/*    */ 
/*    */   
/* 63 */   public Aspect getAspect() { return this.aspect; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void setAspect(Aspect aspect) { this.aspect = aspect; }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int getColor() {
/* 72 */     return (func_145838_q() == null || !(func_145838_q() instanceof BlockBannerTC) || ((BlockBannerTC)
/* 73 */       func_145838_q()).dye == null) ? -1 : ((BlockBannerTC)
/* 74 */       func_145838_q()).dye.func_193350_e();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\misc\TileBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */