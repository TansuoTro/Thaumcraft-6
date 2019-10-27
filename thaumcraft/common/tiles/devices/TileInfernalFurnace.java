/*     */ package thaumcraft.common.tiles.devices;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftInvHelper;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.internal.CommonInternals;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ public class TileInfernalFurnace extends TileThaumcraftInventory {
/*     */   public int furnaceCookTime;
/*     */   public int furnaceMaxCookTime;
/*     */   public int speedyTime;
/*     */   public int facingX;
/*     */   public int facingZ;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  37 */     return new AxisAlignedBB(
/*  38 */         func_174877_v().func_177958_n() - 1.3D, func_174877_v().func_177956_o() - 1.3D, func_174877_v().func_177952_p() - 1.3D, 
/*  39 */         func_174877_v().func_177958_n() + 2.3D, func_174877_v().func_177956_o() + 2.3D, func_174877_v().func_177952_p() + 2.3D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileInfernalFurnace() {
/*  50 */     super(32);
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
/* 192 */     this.facingX = -5;
/* 193 */     this.facingZ = -5; this.furnaceCookTime = 0; this.furnaceMaxCookTime = 0; this.speedyTime = 0;
/*     */   }
/*     */   public int[] func_180463_a(EnumFacing par1) { return (par1 == EnumFacing.UP) ? super.func_180463_a(par1) : new int[0]; }
/*     */   public boolean func_180461_b(int par1, ItemStack stack2, EnumFacing par3) { return false; } public void func_145839_a(NBTTagCompound nbttagcompound) { super.func_145839_a(nbttagcompound); this.furnaceCookTime = nbttagcompound.func_74765_d("CookTime"); this.speedyTime = nbttagcompound.func_74765_d("SpeedyTime"); } public NBTTagCompound func_189515_b(NBTTagCompound nbttagcompound) { super.func_189515_b(nbttagcompound); nbttagcompound.func_74777_a("CookTime", (short)this.furnaceCookTime); nbttagcompound.func_74777_a("SpeedyTime", (short)this.speedyTime); return nbttagcompound; } public void func_73660_a() { super.func_73660_a(); if (this.facingX == -5) setFacing();  if (!this.field_145850_b.field_72995_K) { boolean cookedflag = false; if (this.furnaceCookTime > 0) { this.furnaceCookTime--; cookedflag = true; }  if (this.furnaceMaxCookTime <= 0) this.furnaceMaxCookTime = calcCookTime();  if (this.furnaceCookTime > this.furnaceMaxCookTime) this.furnaceCookTime = this.furnaceMaxCookTime;  if (this.furnaceCookTime <= 0 && cookedflag) for (int a = 0; a < func_70302_i_(); a++) { if (func_70301_a(a) != null && !func_70301_a(a).func_190926_b()) { ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(func_70301_a(a)); if (itemstack != null && !itemstack.func_190926_b()) { if (this.speedyTime > 0) this.speedyTime--;  ejectItem(itemstack.func_77946_l(), func_70301_a(a)); this.field_145850_b.func_175641_c(func_174877_v(), BlocksTC.infernalFurnace, 3, 0); if ((func_145831_w()).field_73012_v.nextInt(20) == 0) AuraHelper.polluteAura(func_145831_w(), func_174877_v().func_177972_a(getFacing().func_176734_d()), 1.0F, true);  func_70298_a(a, 1); break; }  func_70299_a(a, ItemStack.field_190927_a); }  }   if (this.speedyTime <= 0) this.speedyTime = (int)AuraHelper.drainVis(func_145831_w(), func_174877_v(), 20.0F, false);  if (this.furnaceCookTime == 0 && !cookedflag)
/* 197 */         for (int a = 0; a < func_70302_i_(); a++) { if (canSmelt(func_70301_a(a))) { this.furnaceMaxCookTime = calcCookTime(); this.furnaceCookTime = this.furnaceMaxCookTime; break; }  }   }  } public void ejectItem(ItemStack items, ItemStack furnaceItemStack) { if (items == null || items.func_190926_b())
/* 198 */       return;  ArrayList<ItemStack> ejecti = new ArrayList<ItemStack>();
/* 199 */     ejecti.add(items.func_77946_l());
/*     */     
/* 201 */     int bellows = getBellows() + 1;
/*     */     
/* 203 */     float lx = 0.5F; lx += this.facingX * 1.2F;
/* 204 */     float lz = 0.5F; lz += this.facingZ * 1.2F;
/* 205 */     float mx = 0.0F;
/* 206 */     float mz = 0.0F;
/*     */     
/* 208 */     for (int a = 0; a < bellows; a++) {
/* 209 */       ItemStack[] boni = getSmeltingBonus(furnaceItemStack);
/* 210 */       if (boni != null) {
/* 211 */         for (ItemStack bonus : boni) {
/* 212 */           if (!bonus.func_190926_b() && bonus.func_190916_E() > 0) {
/* 213 */             ejecti.add(bonus);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 219 */     for (ItemStack outItem : ejecti) {
/* 220 */       if (outItem.func_190926_b())
/* 221 */         continue;  EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()).func_176734_d();
/* 222 */       InventoryUtils.ejectStackAt(func_145831_w(), func_174877_v(), facing, outItem);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     int cnt = items.func_190916_E();
/* 229 */     float xpf = FurnaceRecipes.func_77602_a().func_151398_b(items);
/*     */ 
/*     */ 
/*     */     
/* 233 */     if (xpf == 0.0F) {
/*     */       
/* 235 */       cnt = 0;
/*     */     }
/* 237 */     else if (xpf < 1.0F) {
/*     */       
/* 239 */       int var4 = MathHelper.func_76141_d(cnt * xpf);
/*     */       
/* 241 */       if (var4 < MathHelper.func_76123_f(cnt * xpf) && (float)Math.random() < cnt * xpf - var4)
/*     */       {
/* 243 */         var4++;
/*     */       }
/*     */       
/* 246 */       cnt = var4;
/*     */     } 
/*     */     
/* 249 */     while (cnt > 0) {
/*     */       
/* 251 */       int var4 = EntityXPOrb.func_70527_a(cnt);
/* 252 */       cnt -= var4;
/*     */ 
/*     */ 
/*     */       
/* 256 */       EntityXPOrb xp = new EntityXPOrb(this.field_145850_b, (this.field_174879_c.func_177958_n() + lx), (this.field_174879_c.func_177956_o() + 0.4F), (this.field_174879_c.func_177952_p() + lz), var4);
/* 257 */       mx = (this.facingX == 0) ? ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025F) : (this.facingX * 0.13F);
/* 258 */       mz = (this.facingZ == 0) ? ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025F) : (this.facingZ * 0.13F);
/* 259 */       xp.field_70159_w = mx;
/* 260 */       xp.field_70179_y = mz;
/* 261 */       xp.field_70181_x = 0.0D;
/* 262 */       this.field_145850_b.func_72838_d(xp);
/*     */     }  }
/*     */   private int getBellows() { int bellows = 0; for (EnumFacing dir : EnumFacing.field_82609_l) {
/*     */       if (dir != EnumFacing.UP) {
/*     */         BlockPos p2 = this.field_174879_c.func_177967_a(dir, 2); TileEntity tile = this.field_145850_b.func_175625_s(p2); if (tile != null && tile instanceof TileBellows && BlockStateUtils.getFacing(this.field_145850_b.func_180495_p(p2)) == dir.func_176734_d() && this.field_145850_b.func_175687_A(p2) == 0)
/*     */           bellows++; 
/*     */       } 
/* 269 */     }  return Math.min(4, bellows); } private ItemStack[] getSmeltingBonus(ItemStack in) { ArrayList<ItemStack> out = new ArrayList<ItemStack>();
/*     */     
/* 271 */     for (ThaumcraftApi.SmeltBonus bonus : CommonInternals.smeltingBonus) {
/* 272 */       if (bonus.in instanceof ItemStack) {
/* 273 */         if (in.func_77973_b() == ((ItemStack)bonus.in).func_77973_b() && (in
/* 274 */           .func_77952_i() == ((ItemStack)bonus.in).func_77952_i() || ((ItemStack)bonus.in)
/* 275 */           .func_77952_i() == 32767) && 
/* 276 */           this.field_145850_b.field_73012_v.nextFloat() <= bonus.chance) {
/* 277 */           ItemStack is = bonus.out.func_77946_l();
/* 278 */           if (is.func_190916_E() < 1) is.func_190920_e(1); 
/* 279 */           out.add(is);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 283 */       for (int id : OreDictionary.getOreIDs(in)) {
/* 284 */         String od = OreDictionary.getOreName(id);
/* 285 */         if (((String)bonus.in).equals(od)) {
/* 286 */           if (this.field_145850_b.field_73012_v.nextFloat() <= bonus.chance) {
/* 287 */             ItemStack is = bonus.out.func_77946_l();
/* 288 */             if (is.func_190916_E() < 1) is.func_190920_e(1); 
/* 289 */             out.add(is);
/*     */           } 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 298 */     return (ItemStack[])out.toArray(new ItemStack[0]); } private int calcCookTime() { int b = getBellows(); if (b > 0)
/*     */       b = (20 - b - 1) * b;  return Math.max(10, ((this.speedyTime > 0) ? 80 : 140) - b); }
/*     */   public ItemStack addItemsToInventory(ItemStack items) { if (canSmelt(items)) { items = ThaumcraftInvHelper.insertStackAt(func_145831_w(), func_174877_v(), EnumFacing.UP, items, false); } else { destroyItem(); items = ItemStack.field_190927_a; }  return items; }
/*     */   private void destroyItem() { this.field_145850_b.func_184134_a((this.field_174879_c.func_177958_n() + 0.5F), (this.field_174879_c.func_177956_o() + 0.5F), (this.field_174879_c.func_177952_p() + 0.5F), SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.3F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F, false); double var21 = (this.field_174879_c.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat()); double var22 = (this.field_174879_c.func_177956_o() + 1); double var23 = (this.field_174879_c.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat()); this.field_145850_b.func_175688_a(EnumParticleTypes.LAVA, var21, var22, var23, 0.0D, 0.0D, 0.0D, new int[0]); }
/* 302 */   private boolean canSmelt(ItemStack stack) { return !FurnaceRecipes.func_77602_a().func_151395_a(stack).func_190926_b(); }
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
/*     */   private void setFacing() {
/* 323 */     this.facingX = 0;
/* 324 */     this.facingZ = 0;
/* 325 */     EnumFacing face = getFacing().func_176734_d();
/* 326 */     this.facingX = face.func_82601_c();
/* 327 */     this.facingZ = face.func_82599_e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 334 */     if (i == 3) {
/*     */       
/* 336 */       if (this.field_145850_b.field_72995_K) {
/* 337 */         for (int a = 0; a < 5; a++) {
/* 338 */           FXDispatcher.INSTANCE.furnaceLavaFx(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), this.facingX, this.facingZ);
/* 339 */           this.field_145850_b.func_184134_a((this.field_174879_c.func_177958_n() + 0.5F), (this.field_174879_c.func_177956_o() + 0.5F), (this.field_174879_c.func_177952_p() + 0.5F), SoundEvents.field_187662_cZ, SoundCategory.BLOCKS, 0.1F + this.field_145850_b.field_73012_v
/* 340 */               .nextFloat() * 0.1F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.15F, false);
/*     */         } 
/*     */       }
/* 343 */       return true;
/*     */     } 
/*     */     
/* 346 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileInfernalFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */