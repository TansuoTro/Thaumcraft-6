/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.aspects.IAspectContainer;
/*    */ 
/*    */ public class TileTubeFilter
/*    */   extends TileTube
/*    */   implements IAspectContainer {
/* 11 */   public Aspect aspectFilter = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/* 16 */     super.readSyncNBT(nbttagcompound);
/* 17 */     this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/* 23 */     nbttagcompound = super.writeSyncNBT(nbttagcompound);
/* 24 */     if (this.aspectFilter != null) nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag()); 
/* 25 */     return nbttagcompound;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 30 */   void calculateSuction(Aspect filter, boolean restrict, boolean dir) { super.calculateSuction(this.aspectFilter, restrict, dir); }
/*    */ 
/*    */ 
/*    */   
/*    */   public AspectList getAspects() {
/* 35 */     if (this.aspectFilter != null) return (new AspectList()).add(this.aspectFilter, -1); 
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAspects(AspectList aspects) {}
/*    */ 
/*    */   
/* 44 */   public boolean doesContainerAccept(Aspect tag) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int addToContainer(Aspect tag, int amount) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public boolean takeFromContainer(Aspect tag, int amount) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public boolean doesContainerContainAmount(Aspect tag, int amount) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public int containerContains(Aspect tag) { return 0; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileTubeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */