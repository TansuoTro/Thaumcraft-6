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
/*    */ 
/*    */ public class TileEssentiaInput
/*    */   extends TileThaumcraft
/*    */   implements IEssentiaTransport, ITickable
/*    */ {
/* 21 */   public boolean isConnectable(EnumFacing face) { return (face == getFacing().func_176734_d()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public boolean canInputFrom(EnumFacing face) { return (face == getFacing().func_176734_d()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public boolean canOutputTo(EnumFacing face) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSuction(Aspect aspect, int amount) {}
/*    */ 
/*    */ 
/*    */   
/* 40 */   public int getMinimumSuction() { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public Aspect getSuctionType(EnumFacing loc) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   public int getSuctionAmount(EnumFacing loc) { return 128; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public Aspect getEssentiaType(EnumFacing loc) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public int getEssentiaAmount(EnumFacing loc) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 70 */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) { return amount; }
/*    */ 
/*    */   
/* 73 */   int count = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_73660_a() {
/* 78 */     if (!this.field_145850_b.field_72995_K)
/*    */     {
/* 80 */       if (++this.count % 5 == 0) {
/* 81 */         fillJar();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   void fillJar() {
/* 88 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), getFacing().func_176734_d());
/* 89 */     if (te != null) {
/* 90 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 91 */       if (!ic.canOutputTo(getFacing()))
/* 92 */         return;  if (ic.getEssentiaAmount(getFacing()) > 0 && ic.getSuctionAmount(getFacing()) < getSuctionAmount(getFacing().func_176734_d()) && 
/* 93 */         getSuctionAmount(getFacing().func_176734_d()) >= ic.getMinimumSuction()) {
/* 94 */         Aspect ta = ic.getEssentiaType(getFacing());
/* 95 */         if (EssentiaHandler.addEssentia(this, ta, getFacing(), 16, false, 5))
/* 96 */           ic.takeEssentia(ta, 1, getFacing()); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileEssentiaInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */