/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import net.minecraft.inventory.ItemStackHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.crafting.ContainerDummy;
/*     */ import thaumcraft.common.container.InventoryArcaneWorkbench;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ import thaumcraft.common.world.aura.AuraChunk;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileArcaneWorkbench
/*     */   extends TileThaumcraft
/*     */ {
/*  20 */   public int auraVisServer = 0;
/*  21 */   public int auraVisClient = 0;
/*     */ 
/*     */ 
/*     */   
/*  25 */   public InventoryArcaneWorkbench inventoryCraft = new InventoryArcaneWorkbench(this, new ContainerDummy());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbtCompound) {
/*  33 */     super.func_145839_a(nbtCompound);
/*     */     
/*  35 */     NonNullList<ItemStack> stacks = NonNullList.func_191197_a(this.inventoryCraft.func_70302_i_(), ItemStack.field_190927_a);
/*  36 */     ItemStackHelper.func_191283_b(nbtCompound, stacks);
/*  37 */     for (int a = 0; a < stacks.size(); ) { this.inventoryCraft.func_70299_a(a, (ItemStack)stacks.get(a)); a++; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbtCompound) {
/*  43 */     super.func_189515_b(nbtCompound);
/*     */     
/*  45 */     NonNullList<ItemStack> stacks = NonNullList.func_191197_a(this.inventoryCraft.func_70302_i_(), ItemStack.field_190927_a);
/*  46 */     for (int a = 0; a < stacks.size(); ) { stacks.set(a, this.inventoryCraft.func_70301_a(a)); a++; }
/*  47 */      ItemStackHelper.func_191282_a(nbtCompound, stacks);
/*     */     
/*  49 */     return nbtCompound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbtCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbtCompound) { return nbtCompound; }
/*     */ 
/*     */   
/*     */   public void getAura() {
/*  69 */     if (!(func_145831_w()).field_72995_K) {
/*  70 */       int t = 0;
/*  71 */       if (this.field_145850_b.func_180495_p(func_174877_v().func_177984_a()).func_177230_c() == BlocksTC.arcaneWorkbenchCharger)
/*  72 */       { int sx = this.field_174879_c.func_177958_n() >> 4;
/*  73 */         int sz = this.field_174879_c.func_177952_p() >> 4;
/*  74 */         for (int xx = -1; xx <= 1; xx++) {
/*  75 */           for (int zz = -1; zz <= 1; zz++) {
/*  76 */             AuraChunk ac = AuraHandler.getAuraChunk(this.field_145850_b.field_73011_w.getDimension(), sx + xx, sz + zz);
/*  77 */             if (ac != null) t = (int)(t + ac.getVis()); 
/*     */           } 
/*     */         }  }
/*  80 */       else { t = (int)AuraHandler.getVis(func_145831_w(), func_174877_v()); }
/*     */       
/*  82 */       this.auraVisServer = t;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void spendAura(int vis) {
/*  87 */     if (!(func_145831_w()).field_72995_K)
/*  88 */       if (this.field_145850_b.func_180495_p(func_174877_v().func_177984_a()).func_177230_c() == BlocksTC.arcaneWorkbenchCharger)
/*  89 */       { int q = vis;
/*  90 */         int z = Math.max(1, vis / 9);
/*  91 */         int attempts = 0;
/*  92 */         while (q > 0) {
/*  93 */           attempts++;
/*  94 */           for (int xx = -1; xx <= 1; xx++) {
/*  95 */             for (int zz = -1; zz <= 1; )
/*  96 */             { if (z > q) z = q; 
/*  97 */               q = (int)(q - AuraHandler.drainVis(func_145831_w(), func_174877_v().func_177982_a(xx * 16, 0, zz * 16), z, false));
/*  98 */               if (q > 0) { if (attempts > 1000)
/*     */                   // Byte code: goto -> 162  zz++; }  // Byte code: goto -> 162 } 
/*     */           } 
/*     */         }  }
/* 102 */       else { AuraHandler.drainVis(func_145831_w(), func_174877_v(), vis, false); }
/*     */        
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileArcaneWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */