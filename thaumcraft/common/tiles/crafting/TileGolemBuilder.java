/*     */ package thaumcraft.common.tiles.crafting;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagLong;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.container.ContainerGolemBuilder;
/*     */ import thaumcraft.common.golems.GolemProperties;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraftInventory;
/*     */ 
/*     */ 
/*     */ public class TileGolemBuilder
/*     */   extends TileThaumcraftInventory
/*     */   implements IEssentiaTransport
/*     */ {
/*  32 */   public long golem = -1L;
/*  33 */   public int cost = 0;
/*  34 */   public int maxCost = 0;
/*  35 */   public boolean[] hasStuff = null;
/*     */   boolean bufferedEssentia = false;
/*     */   int ticks; public int press; IGolemProperties props; ItemStack[] components; public void messageFromClient(NBTTagCompound nbt, EntityPlayerMP player) { super.messageFromClient(nbt, player); if (nbt.func_74764_b("check")) { this.hasStuff = checkCraft(nbt.func_74763_f("golem")); byte[] ba = new byte[this.hasStuff.length]; for (int a = 0; a < ba.length; ) { ba[a] = (byte)(this.hasStuff[a] ? 1 : 0); a++; }  NBTTagCompound nbt2 = new NBTTagCompound(); nbt2.func_74773_a("stuff", ba); sendMessageToClient(nbt2, player); } else if (nbt.func_74764_b("golem")) { startCraft(nbt.func_74763_f("golem"), player); }  } public void messageFromServer(NBTTagCompound nbt) { super.messageFromServer(nbt); if (nbt.func_74764_b("stuff")) { this.hasStuff = null; byte[] ba = nbt.func_74770_j("stuff"); if (ba != null && ba.length > 0) { this.hasStuff = new boolean[ba.length]; for (int a = 0; a < ba.length; ) { this.hasStuff[a] = (ba[a] == 1); a++; }  }
/*     */        ContainerGolemBuilder.redo = true; }
/*  39 */      } public TileGolemBuilder() { super(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     this.ticks = 0;
/* 107 */     this.press = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     this.props = null;
/* 186 */     this.components = null; }
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) { super.readSyncNBT(nbttagcompound); this.golem = nbttagcompound.func_74763_f("golem"); this.cost = nbttagcompound.func_74762_e("cost"); this.maxCost = nbttagcompound.func_74762_e("mcost"); if (this.golem >= 0L)
/*     */       try { this.props = GolemProperties.fromLong(this.golem); this.components = this.props.generateComponents(); } catch (Exception e) { this.props = null; this.components = null; this.cost = 0; this.golem = -1L; }
/* 189 */         } public boolean[] checkCraft(long id) { IGolemProperties props = GolemProperties.fromLong(id);
/* 190 */     ItemStack[] cc = props.generateComponents();
/* 191 */     boolean[] ret = new boolean[cc.length];
/* 192 */     int a = 0;
/* 193 */     for (ItemStack stack : props.generateComponents()) {
/* 194 */       ret[a] = InventoryUtils.checkAdjacentChests(this.field_145850_b, this.field_174879_c, stack);
/* 195 */       a++;
/*     */     } 
/* 197 */     return ret; }
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) { nbttagcompound.func_74772_a("golem", this.golem);
/*     */     nbttagcompound.func_74768_a("cost", this.cost);
/*     */     nbttagcompound.func_74768_a("mcost", this.maxCost);
/*     */     return super.writeSyncNBT(nbttagcompound); }
/* 202 */   public boolean startCraft(long id, EntityPlayer p) { ItemStack placer = new ItemStack(ItemsTC.golemPlacer);
/* 203 */     placer.func_77983_a("props", new NBTTagLong(id));
/*     */     
/* 205 */     if (func_70301_a(false) == null || func_70301_a(0).func_190926_b() || (
/* 206 */       func_70301_a(0).func_190916_E() < func_70301_a(0).func_77976_d() && 
/* 207 */       func_70301_a(0).func_77969_a(placer) && 
/* 208 */       ItemStack.func_77970_a(func_70301_a(0), placer))) {
/*     */       
/* 210 */       this.golem = id;
/* 211 */       this.props = GolemProperties.fromLong(this.golem);
/* 212 */       this.components = this.props.generateComponents();
/*     */       
/* 214 */       if (!InventoryUtils.consumeItemsFromAdjacentInventoryOrPlayer(func_145831_w(), func_174877_v(), p, true, this.components)) {
/* 215 */         this.cost = 0;
/* 216 */         this.props = null;
/* 217 */         this.components = null;
/* 218 */         this.golem = -1L;
/* 219 */         return false;
/*     */       } 
/*     */       
/* 222 */       this.cost = this.props.getTraits().size() * 2;
/* 223 */       for (ItemStack stack : this.components) {
/* 224 */         this.cost += stack.func_190916_E();
/*     */       }
/*     */       
/* 227 */       InventoryUtils.consumeItemsFromAdjacentInventoryOrPlayer(func_145831_w(), func_174877_v(), p, false, this.components);
/*     */       
/* 229 */       this.maxCost = this.cost;
/*     */       
/* 231 */       func_70296_d();
/* 232 */       syncTile(false);
/* 233 */       this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.wand, SoundCategory.BLOCKS, 0.25F, 1.0F);
/* 234 */       return true;
/*     */     } 
/* 236 */     this.cost = 0;
/* 237 */     this.props = null;
/* 238 */     this.components = null;
/* 239 */     this.golem = -1L;
/* 240 */     return false; }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() { return new AxisAlignedBB((this.field_174879_c.func_177958_n() - 1), this.field_174879_c.func_177956_o(), (this.field_174879_c.func_177952_p() - 1), (this.field_174879_c.func_177958_n() + 2), (this.field_174879_c.func_177956_o() + 2), (this.field_174879_c.func_177952_p() + 2)); }
/*     */   
/*     */   public boolean func_94041_b(int par1, ItemStack stack2) {
/* 247 */     if (stack2 != null && !stack2.func_190926_b() && stack2.func_77973_b() instanceof thaumcraft.common.golems.ItemGolemPlacer)
/* 248 */       return true; 
/* 249 */     return false;
/*     */   } public void func_73660_a() { super.func_73660_a(); boolean complete = false; if (!this.field_145850_b.field_72995_K) { this.ticks++; if (this.ticks % 5 == 0 && !complete && this.cost > 0 && this.golem >= 0L) { if (this.bufferedEssentia || drawEssentia()) { this.bufferedEssentia = false; this.cost--; func_70296_d(); }  if (this.cost <= 0) { ItemStack placer = new ItemStack(ItemsTC.golemPlacer); placer.func_77983_a("props", new NBTTagLong(this.golem)); if (func_70301_a(0).func_190926_b() || (func_70301_a(0).func_190916_E() < func_70301_a(0).func_77976_d() && func_70301_a(0).func_77969_a(placer) && ItemStack.func_77970_a(func_70301_a(0), placer))) { if (func_70301_a(false) == null || func_70301_a(0).func_190926_b()) { func_70299_a(0, placer.func_77946_l()); } else { func_70301_a(0).func_190917_f(1); }  complete = true; this.field_145850_b.func_184133_a(null, this.field_174879_c, SoundsTC.wand, SoundCategory.BLOCKS, 1.0F, 1.0F); }  }  }  } else { if (this.press < 90 && this.cost > 0 && this.golem > 0L) { this.press += 6; if (this.press >= 60) { this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.66F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, false); for (int a = 0; a < 16; a++)
/*     */             FXDispatcher.INSTANCE.drawVentParticles(this.field_174879_c.func_177958_n() + 0.5D, (this.field_174879_c.func_177956_o() + 1), this.field_174879_c.func_177952_p() + 0.5D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 0.0D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 11184810);  }  }  if (this.press >= 90 && this.field_145850_b.field_73012_v.nextInt(8) == 0) { FXDispatcher.INSTANCE.drawVentParticles(this.field_174879_c.func_177958_n() + 0.5D, (this.field_174879_c.func_177956_o() + 1), this.field_174879_c.func_177952_p() + 0.5D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 0.0D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 11184810); this.field_145850_b.func_184134_a(this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, SoundEvents.field_187659_cY, SoundCategory.BLOCKS, 0.1F, 1.0F + this.field_145850_b.field_73012_v.nextFloat() * 0.1F, false); }  if (this.press > 0 && (this.cost <= 0 || this.golem == -1L)) { if (this.press >= 90)
/*     */           for (int a = 0; a < 10; a++)
/*     */             FXDispatcher.INSTANCE.drawVentParticles(this.field_174879_c.func_177958_n() + 0.5D, (this.field_174879_c.func_177956_o() + 1), this.field_174879_c.func_177952_p() + 0.5D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 0.0D, this.field_145850_b.field_73012_v.nextGaussian() * 0.1D, 11184810);   this.press -= 3; }  }
/*     */      if (complete) { this.cost = 0; this.golem = -1L; syncTile(false); func_70296_d(); }
/* 255 */      } boolean drawEssentia() { for (EnumFacing face : EnumFacing.field_82609_l) {
/* 256 */       TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), face);
/* 257 */       if (te != null) {
/* 258 */         IEssentiaTransport ic = (IEssentiaTransport)te;
/* 259 */         if (!ic.canOutputTo(face.func_176734_d())) return false; 
/* 260 */         if (ic.getSuctionAmount(face.func_176734_d()) < getSuctionAmount(face) && ic
/* 261 */           .takeEssentia(Aspect.MECHANISM, 1, face.func_176734_d()) == 1) {
/* 262 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 266 */     return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public boolean isConnectable(EnumFacing face) { return (face.func_176736_b() >= 0 || face == EnumFacing.DOWN); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public boolean canInputFrom(EnumFacing face) { return isConnectable(face); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public boolean canOutputTo(EnumFacing face) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */   
/* 290 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public Aspect getSuctionType(EnumFacing face) { return Aspect.MECHANISM; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public int getSuctionAmount(EnumFacing face) { return (this.cost > 0 && this.golem >= 0L) ? 128 : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public Aspect getEssentiaType(EnumFacing loc) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 310 */   public int getEssentiaAmount(EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing facing) { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int addEssentia(Aspect aspect, int amount, EnumFacing facing) {
/* 320 */     if (!this.bufferedEssentia && this.cost > 0 && this.golem >= 0L && aspect == Aspect.MECHANISM) {
/* 321 */       this.bufferedEssentia = true;
/* 322 */       return 1;
/*     */     } 
/* 324 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 329 */   public boolean canRenderBreaking() { return true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\crafting\TileGolemBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */