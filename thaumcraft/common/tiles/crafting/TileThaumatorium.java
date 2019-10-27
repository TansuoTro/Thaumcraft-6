/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectContainer;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.crafting.CrucibleRecipe;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.blocks.IBlockFacing;
/*     */ import thaumcraft.common.container.InventoryFake;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileThaumatorium
/*     */   extends TileThaumcraftInventory
/*     */   implements IAspectContainer, IEssentiaTransport, ITickable
/*     */ {
/*  54 */   public AspectList essentia = new AspectList();
/*  55 */   public ArrayList<Integer> recipeHash = new ArrayList();
/*  56 */   public ArrayList<AspectList> recipeEssentia = new ArrayList();
/*  57 */   public ArrayList<String> recipePlayer = new ArrayList();
/*  58 */   public int currentCraft = -1;
/*  59 */   public int maxRecipes = 1;
/*  60 */   public Aspect currentSuction = null;
/*  61 */   int counter; boolean heated; CrucibleRecipe currentRecipe; public Container eventHandler; public ArrayList<CrucibleRecipe> recipes; @SideOnly(Side.CLIENT) public AxisAlignedBB getRenderBoundingBox() { return new AxisAlignedBB(func_174877_v().func_177958_n() - 0.1D, func_174877_v().func_177956_o() - 0.1D, func_174877_v().func_177952_p() - 0.1D, func_174877_v().func_177958_n() + 1.1D, func_174877_v().func_177956_o() + 2.1D, func_174877_v().func_177952_p() + 1.1D); } public void readSyncNBT(NBTTagCompound nbttagcompound) { this.essentia.readFromNBT(nbttagcompound); this.maxRecipes = nbttagcompound.func_74771_c("maxrec"); this.recipeEssentia = new ArrayList(); this.recipeHash = new ArrayList(); this.recipePlayer = new ArrayList(); int[] hashes = nbttagcompound.func_74759_k("recipes"); if (hashes != null) for (int hash : hashes) { CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(hash); if (recipe != null) { this.recipeEssentia.add(recipe.getAspects().copy()); this.recipePlayer.add(""); this.recipeHash.add(Integer.valueOf(hash)); }  }   } public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) { nbttagcompound.func_74774_a("maxrec", (byte)this.maxRecipes); this.essentia.writeToNBT(nbttagcompound); int[] hashes = new int[this.recipeHash.size()]; int a = 0; for (Integer i : this.recipeHash) { hashes[a] = i.intValue(); a++; }  nbttagcompound.func_74783_a("recipes", hashes); return nbttagcompound; } int venting = 0;
/*     */   public void func_145839_a(NBTTagCompound nbtCompound) { super.func_145839_a(nbtCompound); NBTTagList nbttaglist2 = nbtCompound.func_150295_c("OutputPlayer", 8); for (int a = 0; a < nbttaglist2.func_74745_c(); a++) { if (this.recipePlayer.size() > a) this.recipePlayer.set(a, nbttaglist2.func_150307_f(a));  }  }
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbtCompound) { super.func_189515_b(nbtCompound); NBTTagList nbttaglist2 = new NBTTagList(); if (this.recipePlayer.size() > 0) for (int a = 0; a < this.recipePlayer.size(); a++) { if (this.recipePlayer.get(a) != null) { NBTTagString nbttagcompound1 = new NBTTagString((String)this.recipePlayer.get(a)); nbttaglist2.func_74742_a(nbttagcompound1); }  }   nbtCompound.func_74782_a("OutputPlayer", nbttaglist2); return nbtCompound; }
/*  64 */   boolean checkHeat() { Material mat = this.field_145850_b.func_180495_p(this.field_174879_c.func_177979_c(2)).func_185904_a(); Block bi = this.field_145850_b.func_180495_p(this.field_174879_c.func_177979_c(2)).func_177230_c(); return (mat == Material.field_151587_i || mat == Material.field_151581_o || BlocksTC.nitor.containsValue(bi) || bi == Blocks.field_189877_df); } public ItemStack getCurrentOutputRecipe() { ItemStack out = ItemStack.field_190927_a; if (this.currentCraft >= 0 && this.recipeHash != null && this.recipeHash.size() > 0) { CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)this.recipeHash.get(this.currentCraft)).intValue()); if (recipe != null) out = recipe.getRecipeOutput().func_77946_l();  }  return out; } public void func_73660_a() { if (!this.field_145850_b.field_72995_K) { if (this.counter == 0 || this.counter % 40 == 0) { this.heated = checkHeat(); getUpgrades(); }  this.counter++; if (this.heated && !gettingPower() && this.counter % 5 == 0 && this.recipeHash != null && this.recipeHash.size() > 0) { if (func_70301_a(0).func_190926_b()) { this.currentSuction = null; return; }  if (this.currentCraft < 0 || this.currentCraft >= this.recipeHash.size() || this.currentRecipe == null || !this.currentRecipe.catalystMatches(func_70301_a(0))) for (int a = 0; a < this.recipeHash.size(); a++) { CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(((Integer)this.recipeHash.get(a)).intValue()); if (recipe.catalystMatches(func_70301_a(0))) { this.currentCraft = a; this.currentRecipe = recipe; break; }  }   if (this.currentCraft < 0 || this.currentCraft >= this.recipeHash.size()) return;  boolean done = true; this.currentSuction = null; for (Aspect aspect : ((AspectList)this.recipeEssentia.get(this.currentCraft)).getAspectsSortedByName()) { if (this.essentia.getAmount(aspect) < ((AspectList)this.recipeEssentia.get(this.currentCraft)).getAmount(aspect)) { this.currentSuction = aspect; done = false; break; }  }  if (done) { completeRecipe(); } else if (this.currentSuction != null) { fill(); }  }  } else if (this.venting > 0) { this.venting--; float fx = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fz = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fy = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fx2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fz2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; float fy2 = 0.1F - this.field_145850_b.field_73012_v.nextFloat() * 0.2F; int color = 16777215; EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()); FXDispatcher.INSTANCE.drawVentParticles((this.field_174879_c.func_177958_n() + 0.5F + fx + facing.func_82601_c() / 2.0F), (this.field_174879_c.func_177956_o() + 0.5F + fy), (this.field_174879_c.func_177952_p() + 0.5F + fz + facing.func_82599_e() / 2.0F), (facing.func_82601_c() / 4.0F + fx2), fy2, (facing.func_82599_e() / 4.0F + fz2), color); }  } private void completeRecipe() { if (this.currentRecipe != null && this.currentCraft < this.recipeHash.size() && this.currentRecipe.matches(this.essentia, func_70301_a(0)) && func_70298_a(false, true) != null) { this.essentia = new AspectList(); ItemStack dropped = getCurrentOutputRecipe(); EntityPlayer p = this.field_145850_b.func_72924_a((String)this.recipePlayer.get(this.currentCraft)); if (p != null) FMLCommonHandler.instance().firePlayerCraftingEvent(p, dropped, new InventoryFake(new ItemStack[] { func_70301_a(0) }));  EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()); InventoryUtils.ejectStackAt(func_145831_w(), func_174877_v(), facing, dropped); this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.25F, 2.6F + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8F); this.currentCraft = -1; syncTile(false); func_70296_d(); }  } void fill() { EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()); TileEntity te = null; IEssentiaTransport ic = null; for (int y = 0; y <= 1; y++) { for (EnumFacing dir : EnumFacing.field_82609_l) { if (dir != facing && dir != EnumFacing.DOWN && (y != 0 || dir != EnumFacing.UP)) { te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_174879_c.func_177981_b(y), dir); if (te != null) { ic = (IEssentiaTransport)te; if (ic.getEssentiaAmount(dir.func_176734_d()) > 0 && ic.getSuctionAmount(dir.func_176734_d()) < getSuctionAmount(null) && getSuctionAmount(null) >= ic.getMinimumSuction()) { int ess = ic.takeEssentia(this.currentSuction, 1, dir.func_176734_d()); if (ess > 0) { addToContainer(this.currentSuction, ess); return; }  }  }  }  }  }  } public TileThaumatorium() { super(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     this.counter = 0;
/* 152 */     this.heated = false;
/* 153 */     this.currentRecipe = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 486 */     this.recipes = new ArrayList(); } public int addToContainer(Aspect tt, int am) { int ce = this.currentRecipe.getAspects().getAmount(tt) - this.essentia.getAmount(tt); if (this.currentRecipe == null || ce <= 0) return am;  int add = Math.min(ce, am); this.essentia.add(tt, add); syncTile(false); func_70296_d(); return am - add; } public boolean takeFromContainer(Aspect tt, int am) { if (this.essentia.getAmount(tt) >= am) { this.essentia.remove(tt, am); syncTile(false); func_70296_d(); return true; }  return false; } public boolean takeFromContainer(AspectList ot) { return false; } public boolean doesContainerContain(AspectList ot) { return false; } public boolean doesContainerContainAmount(Aspect tt, int am) { return (this.essentia.getAmount(tt) >= am); } public int containerContains(Aspect tt) { return this.essentia.getAmount(tt); } public boolean doesContainerAccept(Aspect tag) { return true; }
/*     */   public boolean func_145842_c(int i, int j) { if (i >= 0) { if (this.field_145850_b.field_72995_K) this.venting = 7;  return true; }  return super.func_145842_c(i, j); }
/*     */   public boolean isConnectable(EnumFacing face) { return (face != BlockStateUtils.getFacing(func_145832_p())); }
/* 489 */   public void updateRecipes(EntityPlayer player) { this.recipes.clear();
/*     */     
/* 491 */     ArrayList<CrucibleRecipe> recipesTemp = new ArrayList<CrucibleRecipe>();
/* 492 */     if (func_70301_a(false) != null && !func_70301_a(0).func_190926_b() && this.recipeHash != null) {
/* 493 */       for (Object r : ThaumcraftApi.getCraftingRecipes().values()) {
/* 494 */         if (r instanceof CrucibleRecipe) {
/* 495 */           CrucibleRecipe creps = (CrucibleRecipe)r;
/* 496 */           if (ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { creps.getResearch() }) && creps
/* 497 */             .catalystMatches(func_70301_a(0))) {
/* 498 */             recipesTemp.add(creps);
/*     */             continue;
/*     */           } 
/* 501 */           if (this.recipeHash != null && this.recipeHash.size() > 0) {
/* 502 */             for (Integer hash : this.recipeHash) {
/* 503 */               if (creps.hash == hash.intValue()) {
/* 504 */                 recipesTemp.add(creps);
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 513 */     this.recipes = (ArrayList)recipesTemp.stream().sorted(new RecipeOutputComparator()).collect(Collectors.toList()); } public boolean canInputFrom(EnumFacing face) { return (face != BlockStateUtils.getFacing(func_145832_p())); } public boolean canOutputTo(EnumFacing face) { return false; } public void setSuction(Aspect aspect, int amount) { this.currentSuction = aspect; } public Aspect getSuctionType(EnumFacing loc) { return this.currentSuction; } public int getSuctionAmount(EnumFacing loc) { return (this.currentSuction != null) ? 128 : 0; } public Aspect getEssentiaType(EnumFacing loc) { return null; } public int getEssentiaAmount(EnumFacing loc) { return 0; } public int takeEssentia(Aspect aspect, int amount, EnumFacing face) { return (canOutputTo(face) && takeFromContainer(aspect, amount)) ? amount : 0; } public int addEssentia(Aspect aspect, int amount, EnumFacing face) { return canInputFrom(face) ? (amount - addToContainer(aspect, amount)) : 0; } public int getMinimumSuction() { return 0; } public AspectList getAspects() { return this.essentia; } public void setAspects(AspectList aspects) { this.essentia = aspects; } public void func_70296_d() { super.func_70296_d(); if (this.eventHandler != null)
/*     */       this.eventHandler.func_75130_a(this);  } public int[] func_180463_a(EnumFacing side) { return new int[] { 0 }; } public boolean gettingPower() { return (this.field_145850_b.func_175687_A(this.field_174879_c) > 0 || this.field_145850_b.func_175687_A(this.field_174879_c.func_177977_b()) > 0 || this.field_145850_b.func_175687_A(this.field_174879_c.func_177984_a()) > 0); } public void getUpgrades() { EnumFacing facing = BlockStateUtils.getFacing(func_145832_p()); int mr = 1; for (int yy = 0; yy <= 1; yy++) { for (EnumFacing dir : EnumFacing.field_82609_l) { if (dir != EnumFacing.DOWN && dir != facing) { int xx = this.field_174879_c.func_177958_n() + dir.func_82601_c(); int zz = this.field_174879_c.func_177952_p() + dir.func_82599_e(); BlockPos bp = new BlockPos(xx, this.field_174879_c.func_177956_o() + yy + dir.func_96559_d(), zz); IBlockState bs = this.field_145850_b.func_180495_p(bp); if (bs == BlocksTC.brainBox.func_176223_P().func_177226_a(IBlockFacing.FACING, dir.func_176734_d()))
/*     */             mr += 2;  }  }  }
/*     */      if (mr != this.maxRecipes) { this.maxRecipes = mr; while (this.recipeHash.size() > this.maxRecipes)
/*     */         this.recipeHash.remove(this.recipeHash.size() - 1);  syncTile(false); func_70296_d(); }
/*     */      } public String func_70005_c_() { return "container.alchemyfurnace"; }
/*     */   public boolean func_145818_k_() { return false; }
/*     */   public ITextComponent func_145748_c_() { return null; }
/* 521 */   private class RecipeOutputComparator extends Object implements Comparator<CrucibleRecipe> { public int compare(CrucibleRecipe a, CrucibleRecipe b) { if (a.equals(b)) return 0; 
/* 522 */       return a.getRecipeOutput().func_82833_r().compareTo(b.getRecipeOutput().func_82833_r()); }
/*     */      }
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> generateRecipeHashlist() {
/* 527 */     ArrayList<Integer> hashList = new ArrayList<Integer>();
/*     */     
/* 529 */     label19: for (null = this.recipeHash.iterator(); null.hasNext(); ) { int hash = ((Integer)null.next()).intValue();
/* 530 */       for (CrucibleRecipe cr : this.recipes)
/* 531 */       { if (cr.hash == hash)
/* 532 */           continue label19;  }  hashList.add(Integer.valueOf(hash)); }
/*     */ 
/*     */     
/* 535 */     for (CrucibleRecipe cr : this.recipes) {
/* 536 */       hashList.add(Integer.valueOf(cr.hash));
/*     */     }
/*     */     
/* 539 */     return hashList;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileThaumatorium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */