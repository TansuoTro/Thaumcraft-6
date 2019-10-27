/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidTank;
/*     */ import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidHandler;
/*     */ import net.minecraftforge.fluids.capability.IFluidTankProperties;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileCrucible
/*     */   extends TileThaumcraft
/*     */   implements ITickable, IFluidHandler, IAspectContainer
/*     */ {
/*  46 */   public AspectList aspects = new AspectList();
/*  47 */   public final int maxTags = 500;
/*  48 */   int bellows = -1;
/*  49 */   private int delay = 0;
/*     */ 
/*     */ 
/*     */   
/*  53 */   public short heat = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  59 */     this.heat = nbttagcompound.func_74765_d("Heat");
/*  60 */     this.tank.readFromNBT(nbttagcompound);
/*  61 */     if (nbttagcompound.func_74764_b("Empty")) this.tank.setFluid(null); 
/*  62 */     this.aspects.readFromNBT(nbttagcompound);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  68 */     nbttagcompound.func_74777_a("Heat", this.heat);
/*  69 */     this.tank.writeToNBT(nbttagcompound);
/*  70 */     this.aspects.writeToNBT(nbttagcompound);
/*  71 */     return nbttagcompound;
/*     */   }
/*     */   
/*  74 */   private long counter = -100L;
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  79 */     this.counter++;
/*  80 */     int prevheat = this.heat;
/*  81 */     if (!this.field_145850_b.field_72995_K) {
/*     */ 
/*     */ 
/*     */       
/*  85 */       if (this.tank.getFluidAmount() > 0) {
/*  86 */         IBlockState block = this.field_145850_b.func_180495_p(func_174877_v().func_177977_b());
/*  87 */         if (block.func_185904_a() == Material.field_151587_i || block
/*  88 */           .func_185904_a() == Material.field_151581_o || BlocksTC.nitor
/*  89 */           .containsValue(block.func_177230_c()) || block
/*  90 */           .func_177230_c() == Blocks.field_189877_df) {
/*  91 */           if (this.heat < 200) {
/*  92 */             this.heat = (short)(this.heat + 1);
/*  93 */             if (prevheat < 151 && this.heat >= 151) {
/*  94 */               func_70296_d();
/*  95 */               syncTile(false);
/*     */             } 
/*     */           } 
/*  98 */         } else if (this.heat > 0) {
/*  99 */           this.heat = (short)(this.heat - 1);
/* 100 */           if (this.heat == 149) {
/* 101 */             func_70296_d();
/* 102 */             syncTile(false);
/*     */           } 
/*     */         } 
/* 105 */       } else if (this.heat > 0) {
/* 106 */         this.heat = (short)(this.heat - 1);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 111 */       if (this.aspects.visSize() > 500) {
/* 112 */         spillRandom();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 120 */       if (this.counter >= 100L) {
/* 121 */         spillRandom();
/* 122 */         this.counter = 0L;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 127 */     else if (this.tank.getFluidAmount() > 0) {
/* 128 */       drawEffects();
/*     */     } 
/*     */ 
/*     */     
/* 132 */     if (this.field_145850_b.field_72995_K && prevheat < 151 && this.heat >= 151) {
/* 133 */       this.heat = (short)(this.heat + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 138 */   int prevcolor = 0;
/* 139 */   int prevx = 0;
/* 140 */   int prevy = 0;
/*     */ 
/*     */   
/*     */   private void drawEffects() {
/* 144 */     if (this.heat > 150) {
/* 145 */       FXDispatcher.INSTANCE.crucibleFroth(this.field_174879_c
/* 146 */           .func_177958_n() + 0.2F + this.field_145850_b.field_73012_v.nextFloat() * 0.6F, this.field_174879_c
/* 147 */           .func_177956_o() + getFluidHeight(), this.field_174879_c
/* 148 */           .func_177952_p() + 0.2F + this.field_145850_b.field_73012_v.nextFloat() * 0.6F);
/*     */       
/* 150 */       if (this.aspects.visSize() > 500) {
/* 151 */         for (int a = 0; a < 2; a++) {
/* 152 */           FXDispatcher.INSTANCE.crucibleFrothDown(this.field_174879_c.func_177958_n(), (this.field_174879_c.func_177956_o() + 1), this.field_174879_c.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat());
/* 153 */           FXDispatcher.INSTANCE.crucibleFrothDown((this.field_174879_c.func_177958_n() + 1), (this.field_174879_c.func_177956_o() + 1), this.field_174879_c.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat());
/* 154 */           FXDispatcher.INSTANCE.crucibleFrothDown(this.field_174879_c.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), (this.field_174879_c.func_177956_o() + 1), this.field_174879_c.func_177952_p());
/* 155 */           FXDispatcher.INSTANCE.crucibleFrothDown(this.field_174879_c.func_177958_n() + this.field_145850_b.field_73012_v.nextFloat(), (this.field_174879_c.func_177956_o() + 1), (this.field_174879_c.func_177952_p() + 1));
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     if (this.field_145850_b.field_73012_v.nextInt(6) == 0 && this.aspects.size() > 0) {
/*     */       
/* 165 */       int color = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.size())].getColor() + -16777216;
/* 166 */       int x = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 167 */       int y = 5 + this.field_145850_b.field_73012_v.nextInt(22);
/* 168 */       this.delay = this.field_145850_b.field_73012_v.nextInt(10);
/* 169 */       this.prevcolor = color;
/* 170 */       this.prevx = x;
/* 171 */       this.prevy = y;
/*     */       
/* 173 */       Color c = new Color(color);
/* 174 */       float r = c.getRed() / 255.0F;
/* 175 */       float g = c.getGreen() / 255.0F;
/* 176 */       float b = c.getBlue() / 255.0F;
/*     */       
/* 178 */       FXDispatcher.INSTANCE.crucibleBubble(this.field_174879_c
/* 179 */           .func_177958_n() + x / 32.0F + 0.015625F, this.field_174879_c
/* 180 */           .func_177956_o() + 0.05F + getFluidHeight(), this.field_174879_c
/* 181 */           .func_177952_p() + y / 32.0F + 0.015625F, r, g, b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectItem(ItemStack items) {
/* 191 */     boolean first = true;
/*     */     do {
/* 193 */       ItemStack spitout = items.func_77946_l();
/* 194 */       if (spitout.func_190916_E() > spitout.func_77976_d()) {
/* 195 */         spitout.func_190920_e(spitout.func_77976_d());
/*     */       }
/* 197 */       items.func_190918_g(spitout.func_190916_E());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       EntitySpecialItem entityitem = new EntitySpecialItem(this.field_145850_b, (this.field_174879_c.func_177958_n() + 0.5F), (this.field_174879_c.func_177956_o() + 0.71F), (this.field_174879_c.func_177952_p() + 0.5F), spitout);
/*     */       
/* 204 */       entityitem.field_70181_x = 0.07500000298023224D;
/* 205 */       entityitem.field_70159_w = first ? 0.0D : ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01F);
/* 206 */       entityitem.field_70179_y = first ? 0.0D : ((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01F);
/* 207 */       this.field_145850_b.func_72838_d(entityitem);
/*     */       
/* 209 */       first = false;
/* 210 */     } while (items.func_190916_E() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack attemptSmelt(ItemStack item, String username) {
/* 216 */     boolean bubble = false;
/* 217 */     boolean craftDone = false;
/* 218 */     int stacksize = item.func_190916_E();
/* 219 */     EntityPlayer player = this.field_145850_b.func_72924_a(username);
/*     */     
/* 221 */     for (int a = 0; a < stacksize; a++) {
/*     */ 
/*     */ 
/*     */       
/* 225 */       CrucibleRecipe rc = ThaumcraftCraftingManager.findMatchingCrucibleRecipe(player, this.aspects, item);
/*     */       
/* 227 */       if (rc != null && this.tank.getFluidAmount() > 0) {
/* 228 */         ItemStack out = rc.getRecipeOutput().func_77946_l();
/*     */         
/* 230 */         if (player != null) {
/* 231 */           FMLCommonHandler.instance().firePlayerCraftingEvent(player, out, new InventoryFake(new ItemStack[] { item }));
/*     */         }
/* 233 */         this.aspects = rc.removeMatching(this.aspects);
/* 234 */         this.tank.drain(50, true);
/* 235 */         ejectItem(out);
/* 236 */         craftDone = true;
/* 237 */         stacksize--;
/* 238 */         this.counter = -250L;
/*     */       } else {
/*     */         
/* 241 */         AspectList ot = ThaumcraftCraftingManager.getObjectTags(item);
/* 242 */         if (ot != null && ot.size() != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 251 */           for (Aspect tag : ot.getAspects()) {
/* 252 */             this.aspects.add(tag, ot.getAmount(tag));
/*     */           }
/*     */           
/* 255 */           bubble = true;
/* 256 */           stacksize--;
/* 257 */           this.counter = -150L;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 263 */     if (bubble) {
/* 264 */       this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.bubble, SoundCategory.BLOCKS, 0.2F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F);
/* 265 */       syncTile(false);
/* 266 */       this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.crucible, 2, 1);
/*     */     } 
/* 268 */     if (craftDone) {
/* 269 */       syncTile(false);
/* 270 */       this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.crucible, 99, 0);
/*     */     } 
/*     */     
/* 273 */     func_70296_d();
/*     */     
/* 275 */     if (stacksize <= 0) {
/* 276 */       return null;
/*     */     }
/* 278 */     item.func_190920_e(stacksize);
/* 279 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public void attemptSmelt(EntityItem entity) {
/* 284 */     ItemStack item = entity.func_92059_d();
/* 285 */     NBTTagCompound itemData = entity.getEntityData();
/* 286 */     String username = itemData.func_74779_i("thrower");
/* 287 */     ItemStack res = attemptSmelt(item, username);
/* 288 */     if (res == null || res.func_190916_E() <= 0) {
/* 289 */       entity.func_70106_y();
/*     */     } else {
/* 291 */       item.func_190920_e(res.func_190916_E());
/* 292 */       entity.func_92058_a(item);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFluidHeight() {
/* 298 */     float base = 0.3F + 0.5F * this.tank.getFluidAmount() / this.tank.getCapacity();
/* 299 */     float out = base + this.aspects.visSize() / 500.0F * (1.0F - base);
/* 300 */     if (out > 1.0F) out = 1.001F; 
/* 301 */     if (out == 1.0F) out = 0.9999F; 
/* 302 */     return out;
/*     */   }
/*     */   
/*     */   public void spillRandom() {
/* 306 */     if (this.aspects.size() > 0) {
/* 307 */       Aspect tag = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)];
/* 308 */       this.aspects.remove(tag, 1);
/* 309 */       AuraHelper.polluteAura(this.field_145850_b, func_174877_v(), (tag == Aspect.FLUX) ? 1.0F : 0.25F, true);
/*     */     } 
/* 311 */     func_70296_d();
/* 312 */     syncTile(false);
/*     */   }
/*     */   
/*     */   public void spillRemnants() {
/* 316 */     int vs = this.aspects.visSize();
/* 317 */     if (this.tank.getFluidAmount() > 0 || vs > 0) {
/* 318 */       this.tank.setFluid(null);
/* 319 */       AuraHelper.polluteAura(this.field_145850_b, func_174877_v(), vs * 0.25F, true);
/* 320 */       int f = this.aspects.getAmount(Aspect.FLUX);
/* 321 */       if (f > 0) AuraHelper.polluteAura(this.field_145850_b, func_174877_v(), f * 0.75F, false); 
/* 322 */       this.aspects = new AspectList();
/* 323 */       this.field_145850_b.func_175641_c(this.field_174879_c, BlocksTC.crucible, 2, 5);
/* 324 */       func_70296_d();
/* 325 */       syncTile(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 333 */     if (i == 99) {
/*     */       
/* 335 */       if (this.field_145850_b.field_72995_K) {
/* 336 */         FXDispatcher.INSTANCE.drawBamf(this.field_174879_c.func_177958_n() + 0.5D, (this.field_174879_c.func_177956_o() + 1.25F), this.field_174879_c.func_177952_p() + 0.5D, true, true, EnumFacing.UP);
/* 337 */         this.field_145850_b.func_184134_a((this.field_174879_c.func_177958_n() + 0.5F), (this.field_174879_c.func_177956_o() + 0.5F), (this.field_174879_c.func_177952_p() + 0.5F), SoundsTC.spill, SoundCategory.BLOCKS, 0.2F, 1.0F, false);
/*     */       } 
/*     */       
/* 340 */       return true;
/*     */     } 
/*     */     
/* 343 */     if (i == 1) {
/*     */       
/* 345 */       if (this.field_145850_b.field_72995_K) {
/* 346 */         FXDispatcher.INSTANCE.drawBamf(this.field_174879_c.func_177984_a(), true, true, EnumFacing.UP);
/*     */       }
/* 348 */       return true;
/*     */     } 
/*     */     
/* 351 */     if (i == 2) {
/*     */       
/* 353 */       this.field_145850_b.func_184134_a((this.field_174879_c.func_177958_n() + 0.5F), (this.field_174879_c.func_177956_o() + 0.5F), (this.field_174879_c.func_177952_p() + 0.5F), SoundsTC.spill, SoundCategory.BLOCKS, 0.2F, 1.0F, false);
/*     */       
/* 355 */       if (this.field_145850_b.field_72995_K) {
/* 356 */         for (int q = 0; q < 10; q++) {
/* 357 */           FXDispatcher.INSTANCE.crucibleBoil(this.field_174879_c, this, j);
/*     */         }
/*     */       }
/*     */       
/* 361 */       return true;
/*     */     } 
/* 363 */     return super.func_145842_c(i, j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 370 */   public AxisAlignedBB getRenderBoundingBox() { return new AxisAlignedBB(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), (this.field_174879_c.func_177958_n() + 1), (this.field_174879_c.func_177956_o() + 1), (this.field_174879_c.func_177952_p() + 1)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   public AspectList getAspects() { return this.aspects; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */ 
/*     */ 
/*     */   
/* 384 */   public int addToContainer(Aspect tag, int amount) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public boolean takeFromContainer(Aspect tag, int amount) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   public boolean doesContainerContainAmount(Aspect tag, int amount) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 408 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 414 */   public int containerContains(Aspect tag) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 419 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */ 
/*     */ 
/*     */   
/* 423 */   public FluidTank tank = new FluidTank(FluidRegistry.WATER, 0, 1000);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) { return (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
/* 436 */     if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
/* 437 */       return (T)this.tank; 
/* 438 */     return (T)super.getCapability(capability, facing);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 443 */   public IFluidTankProperties[] getTankProperties() { return this.tank.getTankProperties(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int fill(FluidStack resource, boolean doFill) {
/* 448 */     func_70296_d();
/* 449 */     syncTile(false);
/* 450 */     return this.tank.fill(resource, doFill);
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack drain(FluidStack resource, boolean doDrain) {
/* 455 */     FluidStack fs = this.tank.drain(resource, doDrain);
/* 456 */     func_70296_d();
/* 457 */     syncTile(false);
/* 458 */     return fs;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack drain(int maxDrain, boolean doDrain) {
/* 463 */     FluidStack fs = this.tank.drain(maxDrain, doDrain);
/* 464 */     func_70296_d();
/* 465 */     syncTile(false);
/* 466 */     return fs;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */