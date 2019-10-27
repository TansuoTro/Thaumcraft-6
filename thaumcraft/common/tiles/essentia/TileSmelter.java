/*     */ package thaumcraft.common.tiles.essentia;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SPacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.essentia.BlockSmelter;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ import thaumcraft.common.tiles.devices.TileBellows;
/*     */ 
/*     */ public class TileSmelter
/*     */   extends TileThaumcraftInventory
/*     */ {
/*  32 */   private static final int[] slots_bottom = { 1 };
/*  33 */   private static final int[] slots_top = new int[0];
/*  34 */   private static final int[] slots_sides = { 0 };
/*     */   
/*  36 */   public AspectList aspects = new AspectList();
/*     */   public int vis;
/*  38 */   private int maxVis = 256;
/*  39 */   public int smeltTime = 100;
/*     */   boolean speedBoost = false;
/*     */   public int furnaceBurnTime;
/*     */   public int currentItemBurnTime;
/*     */   public int furnaceCookTime;
/*  44 */   int count = 0;
/*     */   int bellows;
/*     */   
/*  47 */   public TileSmelter() { super(2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     this.bellows = -1; } public void readSyncNBT(NBTTagCompound nbttagcompound) { this.furnaceBurnTime = nbttagcompound.func_74765_d("BurnTime"); } public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) { nbttagcompound.func_74777_a("BurnTime", (short)this.furnaceBurnTime); return nbttagcompound; }
/*     */   public void func_145839_a(NBTTagCompound nbtCompound) { super.func_145839_a(nbtCompound); this.speedBoost = nbtCompound.func_74767_n("speedBoost"); this.furnaceCookTime = nbtCompound.func_74765_d("CookTime"); this.currentItemBurnTime = TileEntityFurnace.func_145952_a(func_70301_a(1)); this.aspects.readFromNBT(nbtCompound);
/*     */     this.vis = this.aspects.visSize(); }
/* 218 */   public void checkNeighbours() { EnumFacing[] faces = EnumFacing.field_176754_o;
/*     */     try {
/* 220 */       if (BlockStateUtils.getFacing(func_145832_p()) == EnumFacing.NORTH) faces = new EnumFacing[] { EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST }; 
/* 221 */       if (BlockStateUtils.getFacing(func_145832_p()) == EnumFacing.SOUTH) faces = new EnumFacing[] { EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.WEST }; 
/* 222 */       if (BlockStateUtils.getFacing(func_145832_p()) == EnumFacing.EAST) faces = new EnumFacing[] { EnumFacing.SOUTH, EnumFacing.NORTH, EnumFacing.WEST }; 
/* 223 */       if (BlockStateUtils.getFacing(func_145832_p()) == EnumFacing.WEST) faces = new EnumFacing[] { EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.NORTH }; 
/* 224 */     } catch (Exception exception) {}
/* 225 */     this.bellows = TileBellows.getBellows(this.field_145850_b, this.field_174879_c, faces); }
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbtCompound) { nbtCompound = super.func_189515_b(nbtCompound); nbtCompound.func_74757_a("speedBoost", this.speedBoost); nbtCompound.func_74777_a("CookTime", (short)this.furnaceCookTime); this.aspects.writeToNBT(nbtCompound); return nbtCompound; }
/*     */   public void func_73660_a() { super.func_73660_a(); boolean flag = (this.furnaceBurnTime > 0); boolean flag1 = false; this.count++; if (this.furnaceBurnTime > 0) this.furnaceBurnTime--;  if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) { if (this.bellows < 0) checkNeighbours();  int speed = getSpeed(); if (this.speedBoost) speed = (int)(speed * 0.8D);  if (this.count % speed == 0 && this.aspects.size() > 0) { for (Aspect aspect : this.aspects.getAspects()) { if (this.aspects.getAmount(aspect) > 0 && TileAlembic.processAlembics(func_145831_w(), func_174877_v(), aspect)) { takeFromContainer(aspect, 1); break; }  }  for (EnumFacing face : EnumFacing.field_176754_o) { if (BlockStateUtils.getFacing(func_145832_p()) != face) { IBlockState aux = this.field_145850_b.func_180495_p(func_174877_v().func_177972_a(face)); if (aux.func_177230_c() == BlocksTC.smelterAux && BlockStateUtils.getFacing(aux) == face.func_176734_d()) for (Aspect aspect : this.aspects.getAspects()) { if (this.aspects.getAmount(aspect) > 0 && TileAlembic.processAlembics(func_145831_w(), func_174877_v().func_177972_a(face), aspect)) { takeFromContainer(aspect, 1); break; }  }   }  }  }  if (this.furnaceBurnTime == 0) if (canSmelt()) { this.currentItemBurnTime = this.furnaceBurnTime = TileEntityFurnace.func_145952_a(func_70301_a(1)); if (this.furnaceBurnTime > 0) { BlockSmelter.setFurnaceState(this.field_145850_b, func_174877_v(), true); flag1 = true; this.speedBoost = false; ItemStack itemstack = func_70301_a(1); if (!itemstack.func_190926_b()) { if (itemstack.func_77969_a(new ItemStack(ItemsTC.alumentum)))
/*     */                 this.speedBoost = true;  Item item = itemstack.func_77973_b(); itemstack.func_190918_g(1); if (itemstack.func_190926_b()) { ItemStack item1 = item.getContainerItem(itemstack); func_70299_a(1, item1); }  }  } else { BlockSmelter.setFurnaceState(this.field_145850_b, func_174877_v(), false); }  } else { BlockSmelter.setFurnaceState(this.field_145850_b, func_174877_v(), false); }   if (BlockStateUtils.isEnabled(func_145832_p()) && canSmelt()) { this.furnaceCookTime++; if (this.furnaceCookTime >= this.smeltTime) { this.furnaceCookTime = 0; smeltItem(); flag1 = true; }  } else { this.furnaceCookTime = 0; }  if (flag != ((this.furnaceBurnTime > 0)))
/*     */         flag1 = true;  }  if (flag1)
/* 230 */       func_70296_d();  } private int getType() { return (func_145838_q() == BlocksTC.smelterBasic) ? 0 : ((func_145838_q() == BlocksTC.smelterThaumium) ? 1 : 2); }
/*     */ 
/*     */   
/*     */   private float getEfficiency() {
/* 234 */     float efficiency = 0.8F;
/* 235 */     if (getType() == 1) efficiency = 0.9F; 
/* 236 */     if (getType() == 2) efficiency = 0.95F; 
/* 237 */     return efficiency; } private boolean canSmelt() { if (func_70301_a(0).func_190926_b())
/*     */       return false;  AspectList al = ThaumcraftCraftingManager.getObjectTags(func_70301_a(0)); if (al == null || al.size() == 0)
/*     */       return false;  int vs = al.visSize(); if (vs > this.maxVis - this.vis)
/*     */       return false;  this.smeltTime = (int)((vs * 2) * (1.0F - 0.125F * this.bellows)); return true; }
/* 241 */   private int getSpeed() { return 20 - ((getType() == 1) ? 10 : 5); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void smeltItem() {
/* 247 */     if (canSmelt()) {
/*     */       
/* 249 */       int flux = 0;
/* 250 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(func_70301_a(0));
/*     */       
/* 252 */       for (Aspect a : al.getAspects()) {
/* 253 */         if (getEfficiency() < 1.0F) {
/* 254 */           int qq = al.getAmount(a);
/* 255 */           for (int q = 0; q < qq; q++) {
/* 256 */             if (this.field_145850_b.field_73012_v.nextFloat() > ((a == Aspect.FLUX) ? (getEfficiency() * 0.66F) : getEfficiency())) {
/* 257 */               al.reduce(a, 1);
/* 258 */               flux++;
/*     */             } 
/*     */           } 
/*     */         } 
/* 262 */         this.aspects.add(a, al.getAmount(a));
/*     */       } 
/*     */ 
/*     */       
/* 266 */       if (flux > 0) {
/* 267 */         int pp = 0;
/*     */         
/* 269 */         for (int c = 0; c < flux; c++) {
/* 270 */           EnumFacing[] arrayOfEnumFacing = EnumFacing.field_176754_o; int i = arrayOfEnumFacing.length; byte b = 0; while (true) { if (b < i) { EnumFacing face = arrayOfEnumFacing[b];
/* 271 */               if (BlockStateUtils.getFacing(func_145832_p()) != face) {
/* 272 */                 IBlockState vent = this.field_145850_b.func_180495_p(func_174877_v().func_177972_a(face));
/* 273 */                 if (vent.func_177230_c() == BlocksTC.smelterVent && BlockStateUtils.getFacing(vent) == face.func_176734_d() && 
/* 274 */                   this.field_145850_b.field_73012_v.nextFloat() < 0.333D) {
/* 275 */                   this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 1, face.func_176734_d().ordinal()); break;
/*     */                 } 
/*     */               }  b++;
/*     */               continue; }
/*     */             
/* 280 */             pp++; break; }
/*     */         
/* 282 */         }  AuraHelper.polluteAura(func_145831_w(), func_174877_v(), pp, true);
/*     */       } 
/*     */       
/* 285 */       this.vis = this.aspects.visSize();
/*     */       
/* 287 */       func_70301_a(0).func_190918_g(1);
/*     */       
/* 289 */       if (func_70301_a(0).func_190916_E() <= 0)
/*     */       {
/* 291 */         func_70299_a(0, ItemStack.field_190927_a);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 298 */   public static boolean isItemFuel(ItemStack par0ItemStack) { return (TileEntityFurnace.func_145952_a(par0ItemStack) > 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int par1, ItemStack stack2) {
/* 304 */     if (par1 == 0) {
/* 305 */       AspectList al = ThaumcraftCraftingManager.getObjectTags(stack2);
/* 306 */       if (al != null && al.size() > 0)
/* 307 */         return true; 
/*     */     } 
/* 309 */     return (par1 == 1) ? isItemFuel(stack2) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public int[] func_180463_a(EnumFacing par1) { return (par1 == EnumFacing.DOWN) ? slots_bottom : ((par1 == EnumFacing.UP) ? slots_top : slots_sides); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public boolean func_180462_a(int par1, ItemStack stack2, EnumFacing par3) { return (par3 == EnumFacing.UP) ? false : func_94041_b(par1, stack2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public boolean func_180461_b(int par1, ItemStack stack2, EnumFacing par3) { return (par3 != EnumFacing.UP || par1 != 1 || stack2.func_77973_b() == Items.field_151133_ar); }
/*     */ 
/*     */   
/*     */   public boolean takeFromContainer(Aspect tag, int amount) {
/* 331 */     if (this.aspects != null && this.aspects.getAmount(tag) >= amount) {
/* 332 */       this.aspects.remove(tag, amount);
/* 333 */       this.vis = this.aspects.visSize();
/* 334 */       func_70296_d();
/* 335 */       return true;
/*     */     } 
/* 337 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getCookProgressScaled(int par1) {
/* 343 */     if (this.smeltTime <= 0) this.smeltTime = 1; 
/* 344 */     return this.furnaceCookTime * par1 / this.smeltTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 350 */   public int getVisScaled(int par1) { return this.vis * par1 / this.maxVis; }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getBurnTimeRemainingScaled(int par1) {
/* 356 */     if (this.currentItemBurnTime == 0)
/*     */     {
/* 358 */       this.currentItemBurnTime = 200;
/*     */     }
/* 360 */     return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
/* 365 */     super.onDataPacket(net, pkt);
/* 366 */     if (this.field_145850_b != null) this.field_145850_b.func_180500_c(EnumSkyBlock.BLOCK, this.field_174879_c);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 373 */     if (i == 1) {
/*     */       
/* 375 */       if (this.field_145850_b.field_72995_K) {
/* 376 */         EnumFacing d = EnumFacing.field_82609_l[j];
/* 377 */         this.field_145850_b.func_184134_a(func_174877_v().func_177958_n() + 0.5D + d.func_176734_d().func_82601_c(), func_174877_v().func_177956_o() + 0.5D, func_174877_v().func_177952_p() + 0.5D + d.func_176734_d().func_82599_e(), SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.25F, 2.6F + (this.field_145850_b.field_73012_v
/* 378 */             .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F, true);
/*     */         
/* 380 */         for (int a = 0; a < 4; a++) {
/* 381 */           float fx = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 382 */           float fz = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 383 */           float fy = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 384 */           float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 385 */           float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 386 */           float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F;
/* 387 */           int color = 11184810;
/* 388 */           FXDispatcher.INSTANCE.drawVentParticles((
/* 389 */               func_174877_v().func_177958_n() + 0.5F + fx + d.func_176734_d().func_82601_c()), (
/* 390 */               func_174877_v().func_177956_o() + 0.5F + fy), (
/* 391 */               func_174877_v().func_177952_p() + 0.5F + fz + d.func_176734_d().func_82599_e()), (d
/* 392 */               .func_176734_d().func_82601_c() / 4.0F + fx2), (d
/* 393 */               .func_176734_d().func_96559_d() / 4.0F + fy2), (d
/* 394 */               .func_176734_d().func_82599_e() / 4.0F + fz2), color);
/*     */         } 
/*     */       } 
/* 397 */       return true;
/*     */     } 
/*     */     
/* 400 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\essentia\TileSmelter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */