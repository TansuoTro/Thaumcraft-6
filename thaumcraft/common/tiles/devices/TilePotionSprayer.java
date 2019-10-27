/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.potion.PotionType;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectHelper;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.container.slot.SlotPotion;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ public class TilePotionSprayer extends TileThaumcraftInventory implements IAspectContainer, IEssentiaTransport {
/*     */   public AspectList recipe;
/*     */   public AspectList recipeProgress;
/*     */   public int charges;
/*     */   public int color;
/*     */   int counter;
/*     */   boolean activated;
/*     */   int venting;
/*     */   Aspect currentSuction;
/*     */   
/*  37 */   public TilePotionSprayer() { super(1);
/*     */ 
/*     */     
/*  40 */     this.recipe = new AspectList();
/*  41 */     this.recipeProgress = new AspectList();
/*  42 */     this.charges = 0;
/*  43 */     this.color = 0;
/*  44 */     this.counter = 0;
/*  45 */     this.activated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.venting = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     this.currentSuction = null; }
/*     */   public void func_73660_a() { super.func_73660_a(); this.counter++; EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()); if (!this.field_145850_b.field_72995_K) { if (this.counter % 5 == 0) { this.currentSuction = null; if (func_70301_a(0).func_190926_b() || this.charges >= 8) return;  boolean done = true; for (Aspect aspect : this.recipe.getAspectsSortedByName()) { if (this.recipeProgress.getAmount(aspect) < this.recipe.getAmount(aspect)) { this.currentSuction = aspect; done = false; break; }  }  if (done) { this.recipeProgress = new AspectList(); this.charges++; syncTile(false); func_70296_d(); } else if (this.currentSuction != null) { fill(); }  }  if (!BlockStateUtils.isEnabled(func_145832_p())) { if (!this.activated && this.charges > 0) { this.charges--; List<PotionEffect> effects = PotionUtils.func_185189_a(func_70301_a(0)); if (effects != null && !effects.isEmpty()) { int area = 1; BlockPos p = this.field_174879_c.func_177967_a(facing, 2); List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, new AxisAlignedBB((p.func_177958_n() - area), (p.func_177956_o() - area), (p.func_177952_p() - area), (p.func_177958_n() + 1 + area), (p.func_177956_o() + 1 + area), (p.func_177952_p() + 1 + area))); boolean lifted = false; if (targets.size() > 0) for (EntityLivingBase e : targets) { if (e.field_70128_L || !e.func_184603_cC()) continue;  for (PotionEffect potioneffect1 : effects) { Potion potion = potioneffect1.func_188419_a(); if (potion.func_76403_b()) { potion.func_180793_a(null, null, e, potioneffect1.func_76458_c(), 1.0D); continue; }  e.func_70690_d(new PotionEffect(potion, potioneffect1.func_76459_b(), potioneffect1.func_76458_c())); }  }   }  this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.25F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F); this.field_145850_b.func_175641_c(func_174877_v(), func_145838_q(), 0, 0); syncTile(false); func_70296_d(); }  this.activated = true; } else if (this.activated) { this.activated = false; }  } else if (this.venting > 0) { this.venting--; for (int a = 0; a < this.venting / 2; a++) { float fx = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fz = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fy = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fx2 = (float)(this.field_145850_b.field_73012_v.nextGaussian() * 0.06D); float fz2 = (float)(this.field_145850_b.field_73012_v.nextGaussian() * 0.06D); float fy2 = (float)(this.field_145850_b.field_73012_v.nextGaussian() * 0.06D); FXDispatcher.INSTANCE.drawVentParticles2((this.field_174879_c.func_177958_n() + 0.5F + fx + facing.func_82601_c() / 2.0F), (this.field_174879_c.func_177956_o() + 0.5F + fy + facing.func_96559_d() / 2.0F), (this.field_174879_c.func_177952_p() + 0.5F + fz + facing.func_82599_e() / 2.0F), fx2 + facing.func_82601_c() * 0.25D, fy2 + facing.func_96559_d() * 0.25D, fz2 + facing.func_82599_e() * 0.25D, this.color, 4.0F); }  }  }
/*     */   private void drawFX(EnumFacing facing, double c) {}
/* 235 */   public boolean func_145842_c(int i, int j) { if (i >= 0) { if (this.field_145850_b.field_72995_K) this.venting = 15;  return true; }  return super.func_145842_c(i, j); } public void readSyncNBT(NBTTagCompound nbt) { this.recipe = new AspectList(); this.recipe.readFromNBT(nbt, "recipe"); this.recipeProgress = new AspectList(); this.recipeProgress.readFromNBT(nbt, "progress"); this.charges = nbt.func_74762_e("charges"); this.color = nbt.func_74762_e("color"); } public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) { this.recipe.writeToNBT(nbt, "recipe"); this.recipeProgress.writeToNBT(nbt, "progress"); nbt.func_74768_a("charges", this.charges); nbt.func_74768_a("color", this.color); return nbt; } void fill() { EnumFacing facing = BlockStateUtils.getFacing(func_145832_p());
/* 236 */     TileEntity te = null;
/* 237 */     IEssentiaTransport ic = null;
/* 238 */     for (int y = 0; y <= 1; y++) {
/* 239 */       for (EnumFacing dir : EnumFacing.field_82609_l) {
/* 240 */         if (dir != facing)
/* 241 */         { te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c.func_177981_b(y), dir);
/* 242 */           if (te != null)
/* 243 */           { ic = (IEssentiaTransport)te;
/* 244 */             if (ic.getEssentiaAmount(dir.func_176734_d()) > 0 && ic
/* 245 */               .getSuctionAmount(dir.func_176734_d()) < getSuctionAmount(null) && 
/* 246 */               getSuctionAmount(null) >= ic.getMinimumSuction())
/* 247 */             { int ess = ic.takeEssentia(this.currentSuction, 1, dir.func_176734_d());
/* 248 */               if (ess > 0)
/* 249 */               { addToContainer(this.currentSuction, ess); return; }  }  }  } 
/*     */       } 
/*     */     }  }
/*     */   public boolean func_94041_b(int par1, ItemStack stack) { return (stack != null && !stack.func_190926_b() && SlotPotion.isValidPotion(stack)); }
/*     */   public void func_70299_a(int par1, ItemStack stack) { super.func_70299_a(par1, stack); recalcAspects(); }
/*     */   public ItemStack func_70298_a(int par1, int par2) { ItemStack stack = super.func_70298_a(par1, par2); recalcAspects(); return stack; }
/*     */   private void recalcAspects() { if (this.field_145850_b.field_72995_K)
/*     */       return;  ItemStack stack = func_70301_a(0); this.color = 3355443; if (!this.field_145850_b.field_72995_K) { if (stack == null || stack.func_190926_b()) { this.recipe = new AspectList(); }
/*     */       else { this.recipe = ConfigAspects.getPotionAspects(stack); this.color = getPotionColor(stack); }
/*     */        this.charges = 0; this.recipe = AspectHelper.cullTags(this.recipe, 10); this.recipeProgress = new AspectList(); syncTile(false); func_70296_d(); }
/*     */      } public int getPotionColor(ItemStack itemstack) { PotionType potion = PotionUtils.func_185191_c(itemstack); if (potion != null)
/* 260 */       return PotionUtils.func_185183_a(potion);  return 3355443; } public int addToContainer(Aspect tt, int am) { int ce = this.recipe.getAmount(tt) - this.recipeProgress.getAmount(tt);
/* 261 */     if (ce <= 0) return am; 
/* 262 */     int add = Math.min(ce, am);
/* 263 */     this.recipeProgress.add(tt, add);
/* 264 */     syncTile(false);
/* 265 */     func_70296_d();
/* 266 */     return am - add; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public boolean takeFromContainer(Aspect tt, int am) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public boolean doesContainerContainAmount(Aspect tt, int am) { return (this.recipeProgress.getAmount(tt) >= am); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public int containerContains(Aspect tt) { return this.recipeProgress.getAmount(tt); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public boolean isConnectable(EnumFacing face) { return (face != BlockStateUtils.getFacing(func_145832_p())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public boolean canInputFrom(EnumFacing face) { return (face != BlockStateUtils.getFacing(func_145832_p())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public boolean canOutputTo(EnumFacing face) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public void setSuction(Aspect aspect, int amount) { this.currentSuction = aspect; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 326 */   public Aspect getSuctionType(EnumFacing loc) { return this.currentSuction; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public int getSuctionAmount(EnumFacing loc) { return (this.currentSuction != null) ? 128 : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 336 */   public Aspect getEssentiaType(EnumFacing loc) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public int getEssentiaAmount(EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing face) { return (canOutputTo(face) && takeFromContainer(aspect, amount)) ? amount : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public int addEssentia(Aspect aspect, int amount, EnumFacing face) { return canInputFrom(face) ? (amount - addToContainer(aspect, amount)) : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 362 */   public AspectList getAspects() { return this.recipeProgress; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public void setAspects(AspectList aspects) { this.recipeProgress = aspects; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TilePotionSprayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */