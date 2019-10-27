/*      */ package thaumcraft.common.tiles.crafting;
/*      */ 
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.SoundEvents;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumHand;
/*      */ import net.minecraft.util.ITickable;
/*      */ import net.minecraft.util.SoundCategory;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.text.TextComponentString;
/*      */ import net.minecraft.util.text.TextFormatting;
/*      */ import net.minecraft.util.text.translation.I18n;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*      */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.ThaumcraftInvHelper;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.aspects.IAspectContainer;
/*      */ import thaumcraft.api.aura.AuraHelper;
/*      */ import thaumcraft.api.blocks.BlocksTC;
/*      */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*      */ import thaumcraft.api.capabilities.IPlayerWarp;
/*      */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*      */ import thaumcraft.api.casters.IInteractWithCaster;
/*      */ import thaumcraft.api.crafting.IInfusionStabiliser;
/*      */ import thaumcraft.api.crafting.IInfusionStabiliserExt;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.items.IGogglesDisplayExtended;
/*      */ import thaumcraft.api.potions.PotionFluxTaint;
/*      */ import thaumcraft.api.potions.PotionVisExhaust;
/*      */ import thaumcraft.client.fx.FXDispatcher;
/*      */ import thaumcraft.common.container.InventoryFake;
/*      */ import thaumcraft.common.lib.SoundsTC;
/*      */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*      */ import thaumcraft.common.lib.events.EssentiaHandler;
/*      */ import thaumcraft.common.lib.network.PacketHandler;
/*      */ import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
/*      */ import thaumcraft.common.lib.network.fx.PacketFXInfusionSource;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ import thaumcraft.common.tiles.TileThaumcraft;
/*      */ import thaumcraft.common.tiles.devices.TileStabilizer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TileInfusionMatrix
/*      */   extends TileThaumcraft
/*      */   implements IInteractWithCaster, IAspectContainer, ITickable, IGogglesDisplayExtended
/*      */ {
/*   81 */   private ArrayList<BlockPos> pedestals = new ArrayList();
/*   82 */   private int dangerCount = 0;
/*      */   public boolean active = false;
/*      */   public boolean crafting = false;
/*      */   public boolean checkSurroundings = true;
/*   86 */   public float costMult = 0.0F;
/*   87 */   private int cycleTime = 20;
/*      */   
/*   89 */   public int stabilityCap = 25;
/*   90 */   public float stability = 0.0F;
/*   91 */   public float stabilityReplenish = 0.0F;
/*      */ 
/*      */   
/*   94 */   private AspectList recipeEssentia = new AspectList();
/*   95 */   private ArrayList<ItemStack> recipeIngredients = null;
/*   96 */   private Object recipeOutput = null;
/*   97 */   private String recipePlayer = null;
/*   98 */   private String recipeOutputLabel = null;
/*   99 */   private ItemStack recipeInput = null;
/*  100 */   private int recipeInstability = 0;
/*  101 */   private int recipeXP = 0;
/*  102 */   private int recipeType = 0;
/*      */   public class SourceFX { public BlockPos loc;
/*      */     public int ticks;
/*      */     
/*      */     public SourceFX(BlockPos loc, int ticks, int color) {
/*  107 */       this.loc = loc;
/*  108 */       this.ticks = ticks;
/*  109 */       this.color = color;
/*      */     }
/*      */ 
/*      */     
/*      */     public int color;
/*      */     
/*      */     public int entity; }
/*      */ 
/*      */   
/*  118 */   public HashMap<String, SourceFX> sourceFX = new HashMap();
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public AxisAlignedBB getRenderBoundingBox() {
/*  123 */     return new AxisAlignedBB(
/*  124 */         func_174877_v().func_177958_n() - 0.1D, func_174877_v().func_177956_o() - 0.1D, func_174877_v().func_177952_p() - 0.1D, 
/*  125 */         func_174877_v().func_177958_n() + 1.1D, func_174877_v().func_177956_o() + 1.1D, func_174877_v().func_177952_p() + 1.1D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void readSyncNBT(NBTTagCompound nbtCompound) {
/*  131 */     this.active = nbtCompound.func_74767_n("active");
/*  132 */     this.crafting = nbtCompound.func_74767_n("crafting");
/*  133 */     this.stability = nbtCompound.func_74760_g("stability");
/*  134 */     this.recipeInstability = nbtCompound.func_74762_e("recipeinst");
/*  135 */     this.recipeEssentia.readFromNBT(nbtCompound);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbtCompound) {
/*  141 */     nbtCompound.func_74757_a("active", this.active);
/*  142 */     nbtCompound.func_74757_a("crafting", this.crafting);
/*  143 */     nbtCompound.func_74776_a("stability", this.stability);
/*  144 */     nbtCompound.func_74768_a("recipeinst", this.recipeInstability);
/*  145 */     this.recipeEssentia.writeToNBT(nbtCompound);
/*  146 */     return nbtCompound;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_145839_a(NBTTagCompound nbtCompound) {
/*  152 */     super.func_145839_a(nbtCompound);
/*      */     
/*  154 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("recipein", 10);
/*  155 */     this.recipeIngredients = new ArrayList();
/*  156 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */       
/*  158 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  159 */       this.recipeIngredients.add(new ItemStack(nbttagcompound1));
/*      */     } 
/*      */     
/*  162 */     String rot = nbtCompound.func_74779_i("rotype");
/*  163 */     if (rot != null && rot.equals("@")) {
/*  164 */       this.recipeOutput = new ItemStack(nbtCompound.func_74775_l("recipeout"));
/*      */     }
/*  166 */     else if (rot != null) {
/*  167 */       this.recipeOutputLabel = rot;
/*  168 */       this.recipeOutput = nbtCompound.func_74781_a("recipeout");
/*      */     } 
/*      */     
/*  171 */     this.recipeInput = new ItemStack(nbtCompound.func_74775_l("recipeinput"));
/*      */     
/*  173 */     this.recipeType = nbtCompound.func_74762_e("recipetype");
/*  174 */     this.recipeXP = nbtCompound.func_74762_e("recipexp");
/*  175 */     this.recipePlayer = nbtCompound.func_74779_i("recipeplayer");
/*  176 */     if (this.recipePlayer.isEmpty()) this.recipePlayer = null;
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   public NBTTagCompound func_189515_b(NBTTagCompound nbtCompound) {
/*  182 */     super.func_189515_b(nbtCompound);
/*      */     
/*  184 */     if (this.recipeIngredients != null && this.recipeIngredients.size() > 0) {
/*  185 */       NBTTagList nbttaglist = new NBTTagList();
/*  186 */       for (ItemStack stack : this.recipeIngredients) {
/*      */         
/*  188 */         if (!stack.func_190926_b()) {
/*      */           
/*  190 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  191 */           nbttagcompound1.func_74774_a("item", (byte)this.count);
/*  192 */           stack.func_77955_b(nbttagcompound1);
/*  193 */           nbttaglist.func_74742_a(nbttagcompound1);
/*  194 */           this.count++;
/*      */         } 
/*      */       } 
/*  197 */       nbtCompound.func_74782_a("recipein", nbttaglist);
/*      */     } 
/*  199 */     if (this.recipeOutput != null && this.recipeOutput instanceof ItemStack) nbtCompound.func_74778_a("rotype", "@"); 
/*  200 */     if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
/*  201 */       nbtCompound.func_74778_a("rotype", this.recipeOutputLabel);
/*      */     }
/*      */     
/*  204 */     if (this.recipeOutput != null && this.recipeOutput instanceof ItemStack) {
/*  205 */       nbtCompound.func_74782_a("recipeout", ((ItemStack)this.recipeOutput).func_77955_b(new NBTTagCompound()));
/*      */     }
/*  207 */     if (this.recipeOutput != null && this.recipeOutput instanceof NBTBase) {
/*  208 */       nbtCompound.func_74782_a("recipeout", (NBTBase)this.recipeOutput);
/*      */     }
/*      */     
/*  211 */     if (this.recipeInput != null) nbtCompound.func_74782_a("recipeinput", this.recipeInput.func_77955_b(new NBTTagCompound()));
/*      */     
/*  213 */     nbtCompound.func_74768_a("recipetype", this.recipeType);
/*  214 */     nbtCompound.func_74768_a("recipexp", this.recipeXP);
/*      */     
/*  216 */     if (this.recipePlayer == null) {
/*  217 */       nbtCompound.func_74778_a("recipeplayer", "");
/*      */     } else {
/*  219 */       nbtCompound.func_74778_a("recipeplayer", this.recipePlayer);
/*  220 */     }  return nbtCompound;
/*      */   }
/*      */   
/*      */   private enum EnumStability {
/*  224 */     VERY_STABLE, STABLE, UNSTABLE, VERY_UNSTABLE;
/*      */   }
/*      */ 
/*      */   
/*  228 */   private EnumStability getStability() { return (this.stability > (this.stabilityCap / 2)) ? EnumStability.VERY_STABLE : ((this.stability >= 0.0F) ? EnumStability.STABLE : ((this.stability > -25.0F) ? EnumStability.UNSTABLE : EnumStability.VERY_UNSTABLE)); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float getModFromCurrentStability() {
/*  234 */     switch (getStability()) { case VERY_STABLE:
/*  235 */         return 5.0F;
/*  236 */       case STABLE: return 6.0F;
/*  237 */       case UNSTABLE: return 7.0F;
/*  238 */       case VERY_UNSTABLE: return 8.0F; }
/*  239 */      return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  244 */   public int count = 0;
/*  245 */   public int craftCount = 0;
/*      */   public float startUp;
/*  247 */   private int countDelay = this.cycleTime / 2;
/*      */ 
/*      */   
/*      */   public void func_73660_a() {
/*  251 */     this.count++;
/*  252 */     if (this.checkSurroundings) {
/*  253 */       this.checkSurroundings = false;
/*  254 */       getSurroundings();
/*      */     } 
/*  256 */     if (this.field_145850_b.field_72995_K) {
/*  257 */       doEffects();
/*      */     } else {
/*      */       
/*  260 */       if (this.count % (this.crafting ? 20 : 100) == 0 && 
/*  261 */         !validLocation()) {
/*  262 */         this.active = false;
/*  263 */         func_70296_d();
/*  264 */         syncTile(false);
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  269 */       if (this.active && !this.crafting && this.stability < this.stabilityCap && this.count % Math.max(5, this.countDelay) == 0) {
/*  270 */         this.stability += Math.max(0.1F, this.stabilityReplenish);
/*  271 */         if (this.stability > this.stabilityCap) this.stability = this.stabilityCap; 
/*  272 */         func_70296_d();
/*  273 */         syncTile(false);
/*      */       } 
/*      */       
/*  276 */       if (this.active && this.crafting && this.count % this.countDelay == 0) {
/*  277 */         craftCycle();
/*  278 */         func_70296_d();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  285 */   ArrayList<ItemStack> ingredients = new ArrayList();
/*      */ 
/*      */   
/*      */   public boolean validLocation() {
/*  289 */     if (!(this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(0, -2, 0)).func_177230_c() instanceof thaumcraft.common.blocks.devices.BlockPedestal)) return false; 
/*  290 */     if (!(this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, 1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar)) return false; 
/*  291 */     if (!(this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, 1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar)) return false; 
/*  292 */     if (!(this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(1, -2, -1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar)) return false; 
/*  293 */     if (!(this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar)) return false;
/*      */     
/*  295 */     return true;
/*      */   }
/*      */   
/*      */   public void craftingStart(EntityPlayer player) {
/*  299 */     if (!validLocation()) {
/*  300 */       this.active = false;
/*  301 */       func_70296_d();
/*  302 */       syncTile(false);
/*      */       
/*      */       return;
/*      */     } 
/*  306 */     getSurroundings();
/*  307 */     TileEntity te = null;
/*  308 */     this.recipeInput = ItemStack.field_190927_a;
/*  309 */     te = this.field_145850_b.func_175625_s(this.field_174879_c.func_177979_c(2));
/*  310 */     if (te != null && te instanceof TilePedestal) {
/*  311 */       TilePedestal ped = (TilePedestal)te;
/*  312 */       if (!ped.func_70301_a(0).func_190926_b()) {
/*  313 */         this.recipeInput = ped.func_70301_a(0).func_77946_l();
/*      */       }
/*      */     } 
/*      */     
/*  317 */     if (this.recipeInput == null || this.recipeInput.func_190926_b())
/*      */       return; 
/*  319 */     ArrayList<ItemStack> components = new ArrayList<ItemStack>();
/*  320 */     for (BlockPos cc : this.pedestals) {
/*  321 */       te = this.field_145850_b.func_175625_s(cc);
/*  322 */       if (te != null && te instanceof TilePedestal) {
/*  323 */         TilePedestal ped = (TilePedestal)te;
/*  324 */         if (!ped.func_70301_a(0).func_190926_b()) {
/*  325 */           components.add(ped.func_70301_a(0).func_77946_l());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  330 */     if (components.size() == 0) {
/*      */       return;
/*      */     }
/*  333 */     InfusionRecipe recipe = ThaumcraftCraftingManager.findMatchingInfusionRecipe(components, this.recipeInput, player);
/*      */     
/*  335 */     if (this.costMult < 0.5D) this.costMult = 0.5F;
/*      */ 
/*      */     
/*  338 */     if (recipe != null) {
/*  339 */       this.recipeType = 0;
/*  340 */       this.recipeIngredients = components;
/*      */       
/*  342 */       if (recipe.getRecipeOutput(player, this.recipeInput, components) instanceof Object[]) {
/*  343 */         Object[] obj = (Object[])recipe.getRecipeOutput(player, this.recipeInput, components);
/*  344 */         this.recipeOutputLabel = (String)obj[0];
/*  345 */         this.recipeOutput = (NBTBase)obj[1];
/*      */       } else {
/*  347 */         this.recipeOutput = recipe.getRecipeOutput(player, this.recipeInput, components);
/*      */       } 
/*  349 */       this.recipeInstability = recipe.getInstability(player, this.recipeInput, components);
/*  350 */       AspectList al = recipe.getAspects(player, this.recipeInput, components);
/*  351 */       AspectList al2 = new AspectList();
/*  352 */       for (Aspect as : al.getAspects()) {
/*  353 */         if ((int)(al.getAmount(as) * this.costMult) > 0)
/*  354 */           al2.add(as, (int)(al.getAmount(as) * this.costMult)); 
/*      */       } 
/*  356 */       this.recipeEssentia = al2;
/*      */       
/*  358 */       this.recipePlayer = player.func_70005_c_();
/*  359 */       this.crafting = true;
/*  360 */       this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.craftstart, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*  361 */       syncTile(false);
/*  362 */       func_70296_d();
/*      */       return;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  368 */   private float getLossPerCycle() { return this.recipeInstability / getModFromCurrentStability(); }
/*      */ 
/*      */   
/*      */   public void craftCycle() {
/*  372 */     boolean valid = false;
/*  373 */     float ff = this.field_145850_b.field_73012_v.nextFloat() * getLossPerCycle();
/*  374 */     this.stability -= ff;
/*  375 */     this.stability += this.stabilityReplenish;
/*      */ 
/*      */ 
/*      */     
/*  379 */     if (this.stability < -100.0F) this.stability = -100.0F; 
/*  380 */     if (this.stability > this.stabilityCap) this.stability = this.stabilityCap;
/*      */     
/*  382 */     TileEntity te = this.field_145850_b.func_175625_s(this.field_174879_c.func_177979_c(2));
/*  383 */     if (te != null && te instanceof TilePedestal) {
/*  384 */       TilePedestal ped = (TilePedestal)te;
/*  385 */       if (!ped.func_70301_a(0).func_190926_b()) {
/*  386 */         ItemStack i2 = ped.func_70301_a(0).func_77946_l();
/*  387 */         if (this.recipeInput.func_77952_i() == 32767) {
/*  388 */           i2.func_77964_b(32767);
/*      */         }
/*  390 */         if (ThaumcraftInvHelper.areItemStacksEqualForCrafting(i2, this.recipeInput)) valid = true;
/*      */       
/*      */       } 
/*      */     } 
/*  394 */     if (!valid || (this.stability < 0.0F && this.field_145850_b.field_73012_v.nextInt(1500) <= Math.abs(this.stability))) {
/*      */       
/*  396 */       switch (this.field_145850_b.field_73012_v.nextInt(24)) { case 0: case 1: case 2:
/*      */         case 3:
/*  398 */           inEvEjectItem(0); break;
/*  399 */         case 4: case 5: case 6: inEvWarp(); break;
/*  400 */         case 7: case 8: case 9: inEvZap(false); break;
/*  401 */         case 10: case 11: inEvZap(true); break;
/*  402 */         case 12: case 13: inEvEjectItem(1); break;
/*  403 */         case 14: case 15: inEvEjectItem(2); break;
/*  404 */         case 16: inEvEjectItem(3); break;
/*  405 */         case 17: inEvEjectItem(4); break;
/*  406 */         case 18: case 19: inEvHarm(false); break;
/*  407 */         case 20: case 21: inEvEjectItem(5); break;
/*  408 */         case 22: inEvHarm(true); break;
/*  409 */         case 23: this.field_145850_b.func_72876_a(null, this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, 1.5F + this.field_145850_b.field_73012_v.nextFloat(), false);
/*      */           break; }
/*      */ 
/*      */       
/*  413 */       this.stability += 5.0F + this.field_145850_b.field_73012_v.nextFloat() * 5.0F;
/*      */       
/*  415 */       inResAdd();
/*      */       
/*  417 */       if (valid)
/*      */         return; 
/*      */     } 
/*  420 */     if (!valid) {
/*  421 */       this.crafting = false;
/*  422 */       this.recipeEssentia = new AspectList();
/*  423 */       this.recipeInstability = 0;
/*  424 */       syncTile(false);
/*  425 */       this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.craftfail, SoundCategory.BLOCKS, 1.0F, 0.6F);
/*  426 */       func_70296_d();
/*      */       
/*      */       return;
/*      */     } 
/*  430 */     if (this.recipeType == 1 && this.recipeXP > 0) {
/*  431 */       List<EntityPlayer> targets = this.field_145850_b.func_72872_a(EntityPlayer.class, (new AxisAlignedBB(
/*  432 */             func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/*  433 */             func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1))).func_72314_b(10.0D, 10.0D, 10.0D));
/*  434 */       if (targets != null && targets.size() > 0) {
/*  435 */         for (EntityPlayer target : targets) {
/*  436 */           if (target.field_71075_bZ.field_75098_d || target.field_71068_ca > 0) {
/*  437 */             if (!target.field_71075_bZ.field_75098_d) target.func_82242_a(-1); 
/*  438 */             this.recipeXP--;
/*  439 */             target.func_70097_a(DamageSource.field_76376_m, this.field_145850_b.field_73012_v.nextInt(2));
/*  440 */             PacketHandler.INSTANCE.sendToAllAround(new PacketFXInfusionSource(this.field_174879_c, this.field_174879_c, target
/*  441 */                   .func_145782_y()), new NetworkRegistry.TargetPoint(
/*  442 */                   (func_145831_w()).field_73011_w.getDimension(), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), 32.0D));
/*  443 */             target.func_184185_a(SoundEvents.field_187659_cY, 1.0F, 2.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.4F);
/*  444 */             this.countDelay = this.cycleTime;
/*      */             return;
/*      */           } 
/*      */         } 
/*  448 */         Aspect[] ingEss = this.recipeEssentia.getAspects();
/*  449 */         if (ingEss != null && ingEss.length > 0 && this.field_145850_b.field_73012_v.nextInt(3) == 0) {
/*  450 */           Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
/*  451 */           this.recipeEssentia.add(as, 1);
/*  452 */           this.stability -= 0.25F;
/*  453 */           syncTile(false);
/*  454 */           func_70296_d();
/*      */         } 
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*  460 */     if (this.recipeType == 1 && this.recipeXP == 0) this.countDelay = this.cycleTime / 2;
/*      */     
/*  462 */     if (this.countDelay < 1) this.countDelay = 1;
/*      */     
/*  464 */     if (this.recipeEssentia.visSize() > 0) {
/*  465 */       for (Aspect aspect : this.recipeEssentia.getAspects()) {
/*  466 */         int na = this.recipeEssentia.getAmount(aspect);
/*  467 */         if (na > 0) {
/*  468 */           if (EssentiaHandler.drainEssentia(this, aspect, null, 12, (na > 1) ? this.countDelay : 0)) {
/*  469 */             this.recipeEssentia.reduce(aspect, 1);
/*  470 */             syncTile(false);
/*  471 */             func_70296_d();
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/*  476 */           this.stability -= 0.25F;
/*  477 */           syncTile(false);
/*  478 */           func_70296_d();
/*      */         } 
/*      */       } 
/*  481 */       this.checkSurroundings = true;
/*      */       
/*      */       return;
/*      */     } 
/*  485 */     if (this.recipeIngredients.size() > 0) {
/*  486 */       for (int a = 0; a < this.recipeIngredients.size(); a++) {
/*  487 */         for (BlockPos cc : this.pedestals) {
/*  488 */           te = this.field_145850_b.func_175625_s(cc);
/*  489 */           if (te != null && te instanceof TilePedestal && ((TilePedestal)te).func_70301_a(false) != null && !((TilePedestal)te).func_70301_a(0).func_190926_b() && 
/*  490 */             ThaumcraftInvHelper.areItemStacksEqualForCrafting(((TilePedestal)te).func_70301_a(0), this.recipeIngredients.get(a))) {
/*      */             
/*  492 */             if (this.itemCount == 0) {
/*  493 */               this.itemCount = 5;
/*  494 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXInfusionSource(this.field_174879_c, cc, 0), new NetworkRegistry.TargetPoint(
/*      */                     
/*  496 */                     (func_145831_w()).field_73011_w.getDimension(), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), 32.0D));
/*  497 */             } else if (this.itemCount-- <= 1) {
/*  498 */               ItemStack is = ((TilePedestal)te).func_70301_a(0).func_77973_b().getContainerItem(((TilePedestal)te).func_70301_a(0));
/*  499 */               ((TilePedestal)te).func_70299_a(0, (is == null || is.func_190926_b()) ? ItemStack.field_190927_a : is.func_77946_l());
/*  500 */               ((TilePedestal)te).func_70296_d();
/*  501 */               ((TilePedestal)te).syncTile(false);
/*  502 */               this.recipeIngredients.remove(a);
/*  503 */               func_70296_d();
/*      */             } 
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */         
/*  510 */         Aspect[] ingEss = this.recipeEssentia.getAspects();
/*  511 */         if (ingEss != null && ingEss.length > 0 && this.field_145850_b.field_73012_v.nextInt(1 + a) == 0) {
/*  512 */           Aspect as = ingEss[this.field_145850_b.field_73012_v.nextInt(ingEss.length)];
/*  513 */           this.recipeEssentia.add(as, 1);
/*  514 */           this.stability -= 0.25F;
/*  515 */           syncTile(false);
/*  516 */           func_70296_d();
/*      */         } 
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*  522 */     this.crafting = false;
/*  523 */     craftingFinish(this.recipeOutput, this.recipeOutputLabel);
/*  524 */     this.recipeOutput = null;
/*  525 */     syncTile(false);
/*  526 */     func_70296_d();
/*      */   }
/*      */ 
/*      */   
/*      */   private void inEvZap(boolean all) {
/*  531 */     List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, (new AxisAlignedBB(
/*  532 */           func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/*  533 */           func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1))).func_72314_b(10.0D, 10.0D, 10.0D));
/*  534 */     if (targets != null && targets.size() > 0)
/*  535 */       for (EntityLivingBase target : targets) {
/*  536 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(this.field_174879_c, target, 0.3F - this.field_145850_b.field_73012_v
/*  537 */               .nextFloat() * 0.1F, 0.0F, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w
/*  538 */               .getDimension(), this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), 32.0D));
/*  539 */         target.func_70097_a(DamageSource.field_76376_m, (4 + this.field_145850_b.field_73012_v.nextInt(4)));
/*  540 */         if (!all)
/*      */           break; 
/*      */       }  
/*      */   }
/*      */   
/*      */   private void inEvHarm(boolean all) {
/*  546 */     List<EntityLivingBase> targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, (new AxisAlignedBB(
/*  547 */           func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/*  548 */           func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1))).func_72314_b(10.0D, 10.0D, 10.0D));
/*  549 */     if (targets != null && targets.size() > 0)
/*  550 */       for (EntityLivingBase target : targets) {
/*  551 */         if (this.field_145850_b.field_73012_v.nextBoolean()) {
/*  552 */           target.func_70690_d(new PotionEffect(PotionFluxTaint.instance, 120, 0, false, true));
/*      */         } else {
/*  554 */           PotionEffect pe = new PotionEffect(PotionVisExhaust.instance, 2400, 0, true, true);
/*  555 */           pe.getCurativeItems().clear();
/*  556 */           target.func_70690_d(pe);
/*      */         } 
/*  558 */         if (!all)
/*      */           break; 
/*      */       }  
/*      */   }
/*      */   
/*      */   private void inResAdd() {
/*  564 */     List<EntityPlayer> targets = this.field_145850_b.func_72872_a(EntityPlayer.class, (new AxisAlignedBB(
/*  565 */           func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/*  566 */           func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1))).func_186662_g(10.0D));
/*  567 */     if (targets != null && targets.size() > 0) {
/*  568 */       for (EntityPlayer player : targets) {
/*  569 */         IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/*  570 */         if (!knowledge.isResearchKnown("!INSTABILITY")) {
/*  571 */           knowledge.addResearch("!INSTABILITY");
/*  572 */           knowledge.sync((EntityPlayerMP)player);
/*  573 */           player.func_146105_b(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.instability")), true);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void inEvWarp() {
/*  581 */     List<EntityPlayer> targets = this.field_145850_b.func_72872_a(EntityPlayer.class, (new AxisAlignedBB(
/*  582 */           func_174877_v().func_177958_n(), func_174877_v().func_177956_o(), func_174877_v().func_177952_p(), (
/*  583 */           func_174877_v().func_177958_n() + 1), (func_174877_v().func_177956_o() + 1), (func_174877_v().func_177952_p() + 1))).func_186662_g(10.0D));
/*  584 */     if (targets != null && targets.size() > 0) {
/*  585 */       EntityPlayer target = (EntityPlayer)targets.get(this.field_145850_b.field_73012_v.nextInt(targets.size()));
/*  586 */       if (this.field_145850_b.field_73012_v.nextFloat() < 0.25F) {
/*  587 */         ThaumcraftApi.internalMethods.addWarpToPlayer(target, 1, IPlayerWarp.EnumWarpType.NORMAL);
/*      */       } else {
/*  589 */         ThaumcraftApi.internalMethods.addWarpToPlayer(target, 2 + this.field_145850_b.field_73012_v.nextInt(4), IPlayerWarp.EnumWarpType.TEMPORARY);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void inEvEjectItem(int type) {
/*  596 */     int retries = 0;
/*  597 */     while (retries < 25 && this.pedestals.size() > 0) {
/*  598 */       BlockPos cc = (BlockPos)this.pedestals.get(this.field_145850_b.field_73012_v.nextInt(this.pedestals.size()));
/*  599 */       TileEntity te = this.field_145850_b.func_175625_s(cc);
/*  600 */       if (te != null && te instanceof TilePedestal && ((TilePedestal)te)
/*  601 */         .func_70301_a(false) != null && !((TilePedestal)te).func_70301_a(0).func_190926_b()) {
/*      */         
/*  603 */         BlockPos stabPos = ((TilePedestal)te).findInstabilityMitigator();
/*  604 */         if (stabPos != null) {
/*      */           
/*  606 */           TileEntity ste = this.field_145850_b.func_175625_s(stabPos);
/*  607 */           if (ste != null && ste instanceof TileStabilizer) {
/*  608 */             TileStabilizer tste = (TileStabilizer)ste;
/*  609 */             if (tste.mitigate(MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 5, 10))) {
/*  610 */               this.field_145850_b.func_175641_c(cc, this.field_145850_b.func_180495_p(cc).func_177230_c(), 5, 0);
/*  611 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(this.field_174879_c, cc
/*  612 */                     .func_177984_a(), 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w
/*  613 */                     .getDimension(), cc.func_177958_n(), cc.func_177956_o(), cc.func_177952_p(), 32.0D));
/*  614 */               PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(cc
/*  615 */                     .func_177984_a(), stabPos, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w
/*  616 */                     .getDimension(), stabPos.func_177958_n(), stabPos.func_177956_o(), stabPos.func_177952_p(), 32.0D));
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } 
/*  622 */         if (type <= 3 || type == 5) {
/*  623 */           InventoryUtils.dropItems(this.field_145850_b, cc);
/*      */         } else {
/*  625 */           ((TilePedestal)te).func_70299_a(0, ItemStack.field_190927_a);
/*      */         } 
/*  627 */         ((TilePedestal)te).func_70296_d();
/*  628 */         ((TilePedestal)te).syncTile(false);
/*  629 */         if (type == 1 || type == 3) {
/*  630 */           this.field_145850_b.func_175656_a(cc.func_177984_a(), BlocksTC.fluxGoo.func_176223_P());
/*  631 */           this.field_145850_b.func_184133_a(null, cc, SoundEvents.field_187615_H, SoundCategory.BLOCKS, 0.3F, 1.0F);
/*      */         }
/*  633 */         else if (type == 2 || type == 4) {
/*  634 */           int a = 5 + this.field_145850_b.field_73012_v.nextInt(5);
/*  635 */           AuraHelper.polluteAura(this.field_145850_b, cc, a, true);
/*      */         }
/*  637 */         else if (type == 5) {
/*  638 */           this.field_145850_b.func_72876_a(null, (cc.func_177958_n() + 0.5F), (cc.func_177956_o() + 0.5F), (cc.func_177952_p() + 0.5F), 1.0F, false);
/*      */         } 
/*  640 */         this.field_145850_b.func_175641_c(cc, this.field_145850_b.func_180495_p(cc).func_177230_c(), 11, 0);
/*  641 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockArc(this.field_174879_c, cc
/*  642 */               .func_177984_a(), 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.0F, 0.3F - this.field_145850_b.field_73012_v.nextFloat() * 0.1F), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w
/*  643 */               .getDimension(), cc.func_177958_n(), cc.func_177956_o(), cc.func_177952_p(), 32.0D));
/*      */         return;
/*      */       } 
/*  646 */       retries++;
/*      */     } 
/*      */   }
/*      */   
/*  650 */   int itemCount = 0;
/*      */   
/*      */   public void craftingFinish(Object out, String label) {
/*  653 */     TileEntity te = this.field_145850_b.func_175625_s(this.field_174879_c.func_177979_c(2));
/*  654 */     if (te != null && te instanceof TilePedestal) {
/*  655 */       float dmg = 1.0F;
/*  656 */       if (out instanceof ItemStack) {
/*  657 */         ItemStack qs = ((ItemStack)out).func_77946_l();
/*  658 */         if (((TilePedestal)te).func_70301_a(0).func_77984_f() && ((TilePedestal)te).func_70301_a(0).func_77951_h()) {
/*  659 */           dmg = ((TilePedestal)te).func_70301_a(0).func_77952_i() / ((TilePedestal)te).func_70301_a(0).func_77958_k();
/*  660 */           if (qs.func_77984_f() && !qs.func_77951_h()) {
/*  661 */             qs.func_77964_b((int)(qs.func_77958_k() * dmg));
/*      */           }
/*      */         } 
/*  664 */         ((TilePedestal)te).setInventorySlotContentsFromInfusion(0, qs);
/*      */       }
/*  666 */       else if (out instanceof NBTBase) {
/*  667 */         ItemStack temp = ((TilePedestal)te).func_70301_a(0);
/*  668 */         NBTBase tag = (NBTBase)out;
/*  669 */         temp.func_77983_a(label, tag);
/*  670 */         syncTile(false);
/*  671 */         te.func_70296_d();
/*      */       }
/*  673 */       else if (out instanceof Enchantment) {
/*  674 */         ItemStack temp = ((TilePedestal)te).func_70301_a(0);
/*  675 */         Map enchantments = EnchantmentHelper.func_82781_a(temp);
/*  676 */         enchantments.put((Enchantment)out, Integer.valueOf(EnchantmentHelper.func_77506_a((Enchantment)out, temp) + 1));
/*  677 */         EnchantmentHelper.func_82782_a(enchantments, temp);
/*  678 */         syncTile(false);
/*  679 */         te.func_70296_d();
/*      */       } 
/*      */       
/*  682 */       if (this.recipePlayer != null) {
/*  683 */         EntityPlayer p = this.field_145850_b.func_72924_a(this.recipePlayer);
/*  684 */         if (p != null) {
/*  685 */           FMLCommonHandler.instance().firePlayerCraftingEvent(p, ((TilePedestal)te)
/*  686 */               .func_70301_a(0), new InventoryFake(this.recipeIngredients));
/*      */         }
/*      */       } 
/*  689 */       this.recipeEssentia = new AspectList();
/*  690 */       this.recipeInstability = 0;
/*  691 */       syncTile(false);
/*  692 */       func_70296_d();
/*  693 */       this.field_145850_b.func_175641_c(this.field_174879_c.func_177979_c(2), this.field_145850_b.func_180495_p(this.field_174879_c.func_177979_c(2)).func_177230_c(), 12, 0);
/*  694 */       this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.wand, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*      */     } 
/*      */   }
/*      */   
/*  698 */   private ArrayList<BlockPos> problemBlocks = new ArrayList();
/*      */   
/*      */   private void getSurroundings() {
/*  701 */     Set<Long> stuff = new HashSet<Long>();
/*  702 */     this.pedestals.clear();
/*  703 */     this.tempBlockCount.clear();
/*  704 */     this.problemBlocks.clear();
/*  705 */     this.cycleTime = 10;
/*  706 */     this.stabilityReplenish = 0.0F;
/*  707 */     this.costMult = 1.0F;
/*      */ 
/*      */     
/*      */     try {
/*  711 */       for (int xx = -8; xx <= 8; xx++) {
/*  712 */         for (int zz = -8; zz <= 8; zz++) {
/*  713 */           boolean skip = false;
/*  714 */           for (int yy = -3; yy <= 7; yy++) {
/*  715 */             if (xx != 0 || zz != 0) {
/*  716 */               int x = this.field_174879_c.func_177958_n() + xx;
/*  717 */               int y = this.field_174879_c.func_177956_o() - yy;
/*  718 */               int z = this.field_174879_c.func_177952_p() + zz;
/*  719 */               BlockPos bp = new BlockPos(x, y, z);
/*  720 */               Block bi = this.field_145850_b.func_180495_p(bp).func_177230_c();
/*  721 */               if (bi instanceof thaumcraft.common.blocks.devices.BlockPedestal) {
/*  722 */                 this.pedestals.add(bp);
/*      */               }
/*      */               try {
/*  725 */                 if (bi == Blocks.field_150465_bP || (bi instanceof IInfusionStabiliser && ((IInfusionStabiliser)bi).canStabaliseInfusion(func_145831_w(), bp))) {
/*  726 */                   stuff.add(Long.valueOf(bp.func_177986_g()));
/*      */                 }
/*  728 */               } catch (Exception exception) {}
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  734 */       while (!stuff.isEmpty()) {
/*  735 */         Long[] posArray = (Long[])stuff.toArray(new Long[stuff.size()]);
/*  736 */         if (posArray == null || posArray[false] == null)
/*  737 */           break;  long lp = posArray[0].longValue();
/*      */         try {
/*  739 */           BlockPos c1 = BlockPos.func_177969_a(lp);
/*  740 */           int x1 = this.field_174879_c.func_177958_n() - c1.func_177958_n();
/*  741 */           int z1 = this.field_174879_c.func_177952_p() - c1.func_177952_p();
/*  742 */           int x2 = this.field_174879_c.func_177958_n() + x1;
/*  743 */           int z2 = this.field_174879_c.func_177952_p() + z1;
/*  744 */           BlockPos c2 = new BlockPos(x2, c1.func_177956_o(), z2);
/*  745 */           Block sb1 = this.field_145850_b.func_180495_p(c1).func_177230_c();
/*  746 */           Block sb2 = this.field_145850_b.func_180495_p(c2).func_177230_c();
/*  747 */           float amt1 = 0.1F;
/*  748 */           float amt2 = 0.1F;
/*  749 */           if (sb1 instanceof IInfusionStabiliserExt) {
/*  750 */             amt1 = ((IInfusionStabiliserExt)sb1).getStabilizationAmount(func_145831_w(), c1);
/*      */           }
/*  752 */           if (sb2 instanceof IInfusionStabiliserExt) {
/*  753 */             amt2 = ((IInfusionStabiliserExt)sb2).getStabilizationAmount(func_145831_w(), c2);
/*      */           }
/*  755 */           if (sb1 == sb2 && amt1 == amt2) {
/*  756 */             if (sb1 instanceof IInfusionStabiliserExt && ((IInfusionStabiliserExt)sb1).hasSymmetryPenalty(func_145831_w(), c1, c2)) {
/*  757 */               this.stabilityReplenish -= ((IInfusionStabiliserExt)sb1).getSymmetryPenalty(func_145831_w(), c1);
/*  758 */               this.problemBlocks.add(c1);
/*      */             } else {
/*  760 */               this.stabilityReplenish += calcDeminishingReturns(sb1, amt1);
/*      */             } 
/*      */           } else {
/*  763 */             this.stabilityReplenish -= Math.max(amt1, amt2);
/*  764 */             this.problemBlocks.add(c1);
/*      */           } 
/*  766 */           stuff.remove(Long.valueOf(c2.func_177986_g()));
/*  767 */         } catch (Exception exception) {}
/*  768 */         stuff.remove(Long.valueOf(lp));
/*      */       } 
/*      */       
/*  771 */       if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar && this.field_145850_b
/*  772 */         .func_180495_p(this.field_174879_c.func_177982_a(1, -2, -1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar && this.field_145850_b
/*  773 */         .func_180495_p(this.field_174879_c.func_177982_a(1, -2, 1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar && this.field_145850_b
/*  774 */         .func_180495_p(this.field_174879_c.func_177982_a(-1, -2, 1)).func_177230_c() instanceof thaumcraft.common.blocks.basic.BlockPillar) {
/*      */         
/*  776 */         if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177230_c() == BlocksTC.pillarAncient && this.field_145850_b
/*  777 */           .func_180495_p(this.field_174879_c.func_177982_a(true, -2, -1)).func_177230_c() == BlocksTC.pillarAncient && this.field_145850_b
/*  778 */           .func_180495_p(this.field_174879_c.func_177982_a(true, -2, true)).func_177230_c() == BlocksTC.pillarAncient && this.field_145850_b
/*  779 */           .func_180495_p(this.field_174879_c.func_177982_a(-1, -2, true)).func_177230_c() == BlocksTC.pillarAncient) {
/*  780 */           this.cycleTime--;
/*  781 */           this.costMult -= 0.1F;
/*  782 */           this.stabilityReplenish -= 0.1F;
/*      */         } 
/*      */         
/*  785 */         if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(-1, -2, -1)).func_177230_c() == BlocksTC.pillarEldritch && this.field_145850_b
/*  786 */           .func_180495_p(this.field_174879_c.func_177982_a(true, -2, -1)).func_177230_c() == BlocksTC.pillarEldritch && this.field_145850_b
/*  787 */           .func_180495_p(this.field_174879_c.func_177982_a(true, -2, true)).func_177230_c() == BlocksTC.pillarEldritch && this.field_145850_b
/*  788 */           .func_180495_p(this.field_174879_c.func_177982_a(-1, -2, true)).func_177230_c() == BlocksTC.pillarEldritch) {
/*  789 */           this.cycleTime -= 3;
/*  790 */           this.costMult += 0.05F;
/*  791 */           this.stabilityReplenish += 0.2F;
/*      */         } 
/*      */       } 
/*      */       
/*  795 */       int[] xm = { -1, 1, 1, -1 }, zm = { -1, -1, 1, 1 };
/*  796 */       for (int a = 0; a < 4; a++) {
/*  797 */         Block b = this.field_145850_b.func_180495_p(this.field_174879_c.func_177982_a(xm[a], -3, zm[a])).func_177230_c();
/*  798 */         if (b == BlocksTC.matrixSpeed) {
/*  799 */           this.cycleTime--;
/*  800 */           this.costMult += 0.01F;
/*      */         } 
/*  802 */         if (b == BlocksTC.matrixCost) {
/*  803 */           this.cycleTime++;
/*  804 */           this.costMult -= 0.02F;
/*      */         } 
/*      */       } 
/*      */       
/*  808 */       this.countDelay = this.cycleTime / 2;
/*      */       
/*  810 */       int apc = 0;
/*  811 */       for (BlockPos cc : this.pedestals) {
/*  812 */         boolean items = false;
/*  813 */         int x = this.field_174879_c.func_177958_n() - cc.func_177958_n();
/*  814 */         int z = this.field_174879_c.func_177952_p() - cc.func_177952_p();
/*  815 */         Block bb = this.field_145850_b.func_180495_p(cc).func_177230_c();
/*  816 */         if (bb == BlocksTC.pedestalEldritch) {
/*  817 */           this.costMult += 0.0025F;
/*      */         }
/*  819 */         if (bb == BlocksTC.pedestalAncient) {
/*  820 */           this.costMult -= 0.01F;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  827 */     catch (Exception exception) {}
/*      */   }
/*      */   
/*  830 */   HashMap<Block, Integer> tempBlockCount = new HashMap();
/*      */   
/*      */   private float calcDeminishingReturns(Block b, float base) {
/*  833 */     float bb = base;
/*  834 */     int c = this.tempBlockCount.containsKey(b) ? ((Integer)this.tempBlockCount.get(b)).intValue() : 0;
/*  835 */     if (c > 0)
/*  836 */       bb = (float)(bb * Math.pow(0.75D, c)); 
/*  837 */     this.tempBlockCount.put(b, Integer.valueOf(c + 1));
/*  838 */     return bb;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean onCasterRightClick(World world, ItemStack wandstack, EntityPlayer player, BlockPos pos, EnumFacing side, EnumHand hand) {
/*  843 */     if (world.field_72995_K && this.active && !this.crafting) {
/*  844 */       this.checkSurroundings = true;
/*      */     }
/*  846 */     if (!world.field_72995_K && this.active && !this.crafting) {
/*  847 */       craftingStart(player);
/*  848 */       return false;
/*      */     } 
/*  850 */     if (!world.field_72995_K && !this.active && validLocation()) {
/*  851 */       world.func_184133_a(null, pos, SoundsTC.craftstart, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*  852 */       this.active = true;
/*  853 */       syncTile(false);
/*  854 */       func_70296_d();
/*  855 */       return false;
/*      */     } 
/*  857 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void doEffects() {
/*  862 */     if (this.crafting) {
/*  863 */       if (this.craftCount == 0) {
/*  864 */         this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), SoundsTC.infuserstart, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
/*  865 */       } else if (this.craftCount == 0 || this.craftCount % 65 == 0) {
/*  866 */         this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), SoundsTC.infuser, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
/*      */       } 
/*  868 */       this.craftCount++;
/*  869 */       FXDispatcher.INSTANCE.blockRunes(this.field_174879_c.func_177958_n(), (this.field_174879_c.func_177956_o() - 2), this.field_174879_c.func_177952_p(), 0.5F + this.field_145850_b.field_73012_v
/*  870 */           .nextFloat() * 0.2F, 0.1F, 0.7F + this.field_145850_b.field_73012_v.nextFloat() * 0.3F, 25, -0.03F);
/*  871 */     } else if (this.craftCount > 0) {
/*  872 */       this.craftCount -= 2;
/*  873 */       if (this.craftCount < 0) this.craftCount = 0; 
/*  874 */       if (this.craftCount > 50) this.craftCount = 50;
/*      */     
/*      */     } 
/*  877 */     if (this.active && this.startUp != 1.0F) {
/*  878 */       if (this.startUp < 1.0F) this.startUp += Math.max(this.startUp / 10.0F, 0.001F); 
/*  879 */       if (this.startUp > 0.999D) this.startUp = 1.0F;
/*      */     
/*      */     } 
/*  882 */     if (!this.active && this.startUp > 0.0F) {
/*  883 */       if (this.startUp > 0.0F) this.startUp -= this.startUp / 10.0F; 
/*  884 */       if (this.startUp < 0.001D) this.startUp = 0.0F; 
/*      */     } 
/*  886 */     for (String fxk : (String[])this.sourceFX.keySet().toArray(new String[0])) {
/*  887 */       SourceFX fx = (SourceFX)this.sourceFX.get(fxk);
/*  888 */       if (fx.ticks <= 0) {
/*  889 */         this.sourceFX.remove(fxk);
/*      */       } else {
/*      */         
/*  892 */         if (fx.loc.equals(this.field_174879_c)) {
/*  893 */           Entity player = this.field_145850_b.func_73045_a(fx.color);
/*  894 */           if (player != null) {
/*  895 */             for (int a = 0; a < 4; a++) {
/*  896 */               FXDispatcher.INSTANCE.drawInfusionParticles4(player.field_70165_t + ((this.field_145850_b.field_73012_v
/*  897 */                   .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N), 
/*  898 */                   (player.func_174813_aQ()).field_72338_b + (this.field_145850_b.field_73012_v.nextFloat() * player.field_70131_O), player.field_70161_v + ((this.field_145850_b.field_73012_v
/*  899 */                   .nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * player.field_70130_N), this.field_174879_c
/*  900 */                   .func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p());
/*      */             }
/*      */           }
/*      */         } else {
/*  904 */           TileEntity tile = this.field_145850_b.func_175625_s(fx.loc);
/*  905 */           if (tile instanceof TilePedestal) {
/*  906 */             ItemStack is = ((TilePedestal)tile).getSyncedStackInSlot(0);
/*  907 */             if (is != null && !is.func_190926_b()) {
/*  908 */               if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
/*  909 */                 FXDispatcher.INSTANCE.drawInfusionParticles3((fx.loc
/*  910 */                     .func_177958_n() + this.field_145850_b.field_73012_v.nextFloat()), (fx.loc.func_177956_o() + this.field_145850_b.field_73012_v.nextFloat() + 1.0F), (fx.loc.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat()), this.field_174879_c
/*  911 */                     .func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p());
/*      */               } else {
/*      */                 
/*  914 */                 Item bi = is.func_77973_b();
/*  915 */                 if (bi instanceof net.minecraft.item.ItemBlock) {
/*  916 */                   for (int a = 0; a < 4; a++) {
/*  917 */                     FXDispatcher.INSTANCE.drawInfusionParticles2((fx.loc
/*  918 */                         .func_177958_n() + this.field_145850_b.field_73012_v.nextFloat()), (fx.loc.func_177956_o() + this.field_145850_b.field_73012_v.nextFloat() + 1.0F), (fx.loc.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat()), this.field_174879_c, 
/*  919 */                         Block.func_149634_a(bi).func_176223_P(), is.func_77952_i());
/*      */                   }
/*      */                 } else {
/*      */                   
/*  923 */                   for (int a = 0; a < 4; a++) {
/*  924 */                     FXDispatcher.INSTANCE.drawInfusionParticles1((fx.loc
/*  925 */                         .func_177958_n() + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F), (fx.loc.func_177956_o() + 1.23F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F), (fx.loc.func_177952_p() + 0.4F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F), this.field_174879_c, is);
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } else {
/*      */             
/*  932 */             fx.ticks = 0;
/*      */           } 
/*  934 */         }  fx.ticks--;
/*  935 */         this.sourceFX.put(fxk, fx);
/*      */       } 
/*      */     } 
/*      */     
/*  939 */     if (this.crafting && this.stability < 0.0F && this.field_145850_b.field_73012_v.nextInt(250) <= Math.abs(this.stability)) {
/*  940 */       FXDispatcher.INSTANCE.spark((
/*  941 */           func_174877_v().func_177958_n() + this.field_145850_b.field_73012_v.nextFloat()), (func_174877_v().func_177956_o() + this.field_145850_b.field_73012_v.nextFloat()), (func_174877_v().func_177952_p() + this.field_145850_b.field_73012_v.nextFloat()), 3.0F + this.field_145850_b.field_73012_v
/*  942 */           .nextFloat() * 2.0F, 0.7F + this.field_145850_b.field_73012_v
/*  943 */           .nextFloat() * 0.1F, 0.1F, 0.65F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.8F);
/*      */     }
/*      */ 
/*      */     
/*  947 */     if (this.active && !this.problemBlocks.isEmpty() && this.field_145850_b.field_73012_v.nextInt(25) == 0) {
/*  948 */       BlockPos p = (BlockPos)this.problemBlocks.get(this.field_145850_b.field_73012_v.nextInt(this.problemBlocks.size()));
/*  949 */       FXDispatcher.INSTANCE.spark((p
/*  950 */           .func_177958_n() + this.field_145850_b.field_73012_v.nextFloat()), (p.func_177956_o() + this.field_145850_b.field_73012_v.nextFloat()), (p.func_177952_p() + this.field_145850_b.field_73012_v.nextFloat()), 2.0F + this.field_145850_b.field_73012_v
/*  951 */           .nextFloat(), 0.7F + this.field_145850_b.field_73012_v
/*  952 */           .nextFloat() * 0.1F, 0.1F, 0.65F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, 0.8F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  959 */   public AspectList getAspects() { return this.recipeEssentia; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAspects(AspectList aspects) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  969 */   public int addToContainer(Aspect tag, int amount) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  974 */   public boolean takeFromContainer(Aspect tag, int amount) { return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  979 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  984 */   public boolean doesContainerContainAmount(Aspect tag, int amount) { return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  989 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  994 */   public int containerContains(Aspect tag) { return 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  999 */   public boolean doesContainerAccept(Aspect tag) { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1004 */   public boolean canRenderBreaking() { return true; }
/*      */ 
/*      */   
/* 1007 */   static DecimalFormat myFormatter = new DecimalFormat("#######.##");
/*      */ 
/*      */   
/*      */   public String[] getIGogglesText() {
/* 1011 */     float lpc = getLossPerCycle();
/* 1012 */     if (lpc != 0.0F) {
/* 1013 */       return new String[] { TextFormatting.BOLD + 
/* 1014 */           I18n.func_74838_a("stability." + getStability().name()), TextFormatting.GOLD + "" + TextFormatting.ITALIC + myFormatter
/* 1015 */           .format(this.stabilityReplenish) + " " + I18n.func_74838_a("stability.gain"), TextFormatting.RED + "" + 
/* 1016 */           I18n.func_74838_a("stability.range") + TextFormatting.ITALIC + myFormatter.format(lpc) + " " + I18n.func_74838_a("stability.loss") };
/*      */     }
/*      */     
/* 1019 */     return new String[] { TextFormatting.BOLD + 
/* 1020 */         I18n.func_74838_a("stability." + getStability().name()), TextFormatting.GOLD + "" + TextFormatting.ITALIC + myFormatter
/* 1021 */         .format(this.stabilityReplenish) + " " + I18n.func_74838_a("stability.gain") };
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileInfusionMatrix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */