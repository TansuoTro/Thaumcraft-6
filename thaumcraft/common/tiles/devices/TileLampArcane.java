/*    */ package thaumcraft.common.tiles.devices;
/*    */ 
/*    */ import net.minecraft.util.ITickable;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.EnumSkyBlock;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.common.lib.utils.BlockUtils;
/*    */ import thaumcraft.common.tiles.TileThaumcraft;
/*    */ 
/*    */ public class TileLampArcane
/*    */   extends TileThaumcraft
/*    */   implements ITickable {
/*    */   public int rad;
/* 14 */   public int rad1 = 0;
/*    */ 
/*    */   
/*    */   public void func_73660_a() {
/* 18 */     if (!this.field_145850_b.field_72995_K && this.field_145850_b.func_82737_E() % 5L == 0L && !gettingPower()) {
/* 19 */       int x = this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 20 */       int y = this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 21 */       int z = this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
/* 22 */       BlockPos bp = this.field_174879_c.func_177982_a(x, y, z);
/*    */       
/* 24 */       if (bp.func_177956_o() > this.field_145850_b.func_175725_q(bp).func_177956_o() + 4) {
/* 25 */         bp = this.field_145850_b.func_175725_q(bp).func_177981_b(4);
/*    */       }
/* 27 */       if (bp.func_177956_o() < 5) {
/* 28 */         bp = new BlockPos(bp.func_177958_n(), 5, bp.func_177952_p());
/*    */       }
/*    */       
/* 31 */       if (this.field_145850_b.func_175623_d(bp) && this.field_145850_b.func_180495_p(bp) != BlocksTC.effectGlimmer.func_176223_P() && this.field_145850_b
/* 32 */         .func_175642_b(EnumSkyBlock.BLOCK, bp) < 11 && BlockUtils.hasLOS(func_145831_w(), func_174877_v(), bp)) {
/* 33 */         this.field_145850_b.func_180501_a(bp, BlocksTC.effectGlimmer.func_176223_P(), 3);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public void removeLights() {
/* 39 */     for (int x = -15; x <= 15; x++) {
/* 40 */       for (int y = -15; y <= 15; y++) {
/* 41 */         for (int z = -15; z <= 15; z++) {
/* 42 */           BlockPos bp = this.field_174879_c.func_177982_a(x, y, z);
/* 43 */           if (this.field_145850_b.func_180495_p(bp) == BlocksTC.effectGlimmer.func_176223_P())
/* 44 */             this.field_145850_b.func_175698_g(bp); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileLampArcane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */