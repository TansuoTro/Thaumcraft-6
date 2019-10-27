/*    */ package thaumcraft.common.tiles.essentia;
/*    */ 
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aura.AuraHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileJarFillableVoid
/*    */   extends TileJarFillable
/*    */ {
/*    */   public int addToContainer(Aspect tt, int am) {
/* 14 */     boolean up = (this.amount < 250);
/* 15 */     if (am == 0) return am; 
/* 16 */     if (tt == this.aspect || this.amount == 0) {
/* 17 */       this.aspect = tt;
/* 18 */       this.amount += am;
/* 19 */       am = 0;
/* 20 */       if (this.amount > 250) {
/* 21 */         this; if (this.field_145850_b.field_73012_v.nextInt(250) == 0)
/* 22 */           AuraHelper.polluteAura(func_145831_w(), func_174877_v(), 1.0F, true); 
/* 23 */         this.amount = 250;
/*    */       } 
/*    */     } 
/* 26 */     if (up) {
/* 27 */       syncTile(false);
/* 28 */       func_70296_d();
/*    */     } 
/* 30 */     return am;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int getMinimumSuction() { return (this.aspectFilter != null) ? 48 : 32; }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSuctionAmount(EnumFacing loc) {
/* 40 */     if (this.aspectFilter != null && this.amount < 250) {
/* 41 */       return 48;
/*    */     }
/* 43 */     return 32;
/*    */   }
/*    */ 
/*    */   
/* 47 */   int count = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_73660_a() {
/* 52 */     if (!this.field_145850_b.field_72995_K && ++this.count % 5 == 0)
/* 53 */       fillJar(); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileJarFillableVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */