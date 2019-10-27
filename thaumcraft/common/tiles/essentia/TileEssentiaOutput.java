/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.ITickable;
/*    */ import thaumcraft.api.ThaumcraftApiHelper;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.IEssentiaTransport;
/*    */ import thaumcraft.common.lib.events.EssentiaHandler;
/*    */ import thaumcraft.common.tiles.TileThaumcraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEssentiaOutput
/*    */   extends TileThaumcraft
/*    */   implements IEssentiaTransport, ITickable
/*    */ {
/* 20 */   public boolean isConnectable(EnumFacing face) { return (face == getFacing().func_176734_d()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public boolean canInputFrom(EnumFacing face) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public boolean canOutputTo(EnumFacing face) { return (face == getFacing().func_176734_d()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSuction(Aspect aspect, int amount) {}
/*    */ 
/*    */ 
/*    */   
/* 39 */   public int getMinimumSuction() { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public Aspect getSuctionType(EnumFacing loc) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int getSuctionAmount(EnumFacing loc) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public Aspect getEssentiaType(EnumFacing loc) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public int getEssentiaAmount(EnumFacing loc) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) { return amount; }
/*    */ 
/*    */   
/* 72 */   int count = 0;
/*    */ 
/*    */   
/*    */   public void func_73660_a() {
/* 76 */     if (!this.field_145850_b.field_72995_K && 
/* 77 */       ++this.count % 5 == 0) {
/* 78 */       fillBuffer();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   void fillBuffer() {
/* 84 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), getFacing().func_176734_d());
/* 85 */     if (te != null) {
/* 86 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 87 */       if (!ic.canInputFrom(getFacing()))
/* 88 */         return;  if (ic.getSuctionAmount(getFacing()) > 0 && ic.getSuctionType(getFacing()) != null) {
/* 89 */         Aspect ta = ic.getSuctionType(getFacing());
/* 90 */         if (EssentiaHandler.drainEssentiaWithConfirmation(this, ta, getFacing(), 16, false, 5) && 
/* 91 */           ic.addEssentia(ta, 1, getFacing()) > 0)
/* 92 */           EssentiaHandler.confirmDrain(); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileEssentiaOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */