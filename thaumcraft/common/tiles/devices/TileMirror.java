/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileMirror
/*     */   extends TileThaumcraft
/*     */   implements IInventory, ITickable
/*     */ {
/*     */   public boolean linked = false;
/*     */   public int linkX;
/*     */   public int linkY;
/*     */   public int linkZ;
/*     */   public int linkDim;
/*     */   public int instability;
/*     */   
/*     */   public void restoreLink() {
/*  40 */     if (isDestinationValid()) {
/*  41 */       WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(this.linkDim);
/*  42 */       if (worldServer == null)
/*  43 */         return;  TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  44 */       if (te != null && te instanceof TileMirror) {
/*  45 */         TileMirror tm = (TileMirror)te;
/*  46 */         tm.linked = true;
/*  47 */         tm.linkX = func_174877_v().func_177958_n();
/*  48 */         tm.linkY = func_174877_v().func_177956_o();
/*  49 */         tm.linkZ = func_174877_v().func_177952_p();
/*  50 */         tm.linkDim = this.field_145850_b.field_73011_w.getDimension();
/*  51 */         tm.syncTile(false);
/*  52 */         this.linked = true;
/*  53 */         func_70296_d();
/*  54 */         tm.func_70296_d();
/*  55 */         syncTile(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void invalidateLink() {
/*  61 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/*  62 */     if (worldServer == null)
/*  63 */       return;  if (!Utils.isChunkLoaded(worldServer, this.linkX, this.linkZ))
/*  64 */       return;  TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  65 */     if (te != null && te instanceof TileMirror) {
/*  66 */       TileMirror tm = (TileMirror)te;
/*  67 */       tm.linked = false;
/*  68 */       func_70296_d();
/*  69 */       tm.func_70296_d();
/*  70 */       tm.syncTile(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLinkValid() {
/*  76 */     if (!this.linked) return false; 
/*  77 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/*  78 */     if (worldServer == null) {
/*  79 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  84 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  85 */     if (te == null || !(te instanceof TileMirror)) {
/*  86 */       this.linked = false;
/*  87 */       func_70296_d();
/*  88 */       syncTile(false);
/*  89 */       return false;
/*     */     } 
/*  91 */     TileMirror tm = (TileMirror)te;
/*  92 */     if (!tm.linked) {
/*  93 */       this.linked = false;
/*  94 */       func_70296_d();
/*  95 */       syncTile(false);
/*  96 */       return false;
/*     */     } 
/*  98 */     if (tm.linkX != func_174877_v().func_177958_n() || tm.linkY != func_174877_v().func_177956_o() || tm.linkZ != func_174877_v().func_177952_p() || tm.linkDim != this.field_145850_b.field_73011_w
/*  99 */       .getDimension()) {
/*     */       
/* 101 */       this.linked = false;
/* 102 */       func_70296_d();
/* 103 */       syncTile(false);
/* 104 */       return false;
/*     */     } 
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isLinkValidSimple() {
/* 110 */     if (!this.linked) return false; 
/* 111 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 112 */     if (worldServer == null) {
/* 113 */       return false;
/*     */     }
/*     */     
/* 116 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 117 */     if (te == null || !(te instanceof TileMirror)) {
/* 118 */       return false;
/*     */     }
/* 120 */     TileMirror tm = (TileMirror)te;
/* 121 */     if (!tm.linked) {
/* 122 */       return false;
/*     */     }
/* 124 */     if (tm.linkX != func_174877_v().func_177958_n() || tm.linkY != func_174877_v().func_177956_o() || tm.linkZ != func_174877_v().func_177952_p() || tm.linkDim != this.field_145850_b.field_73011_w
/* 125 */       .getDimension())
/*     */     {
/* 127 */       return false;
/*     */     }
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isDestinationValid() {
/* 133 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 134 */     if (worldServer == null) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 139 */     if (te == null || !(te instanceof TileMirror)) {
/* 140 */       this.linked = false;
/* 141 */       func_70296_d();
/* 142 */       syncTile(false);
/* 143 */       return false;
/*     */     } 
/*     */     
/* 146 */     TileMirror tm = (TileMirror)te;
/* 147 */     if (tm.isLinkValid()) {
/* 148 */       return false;
/*     */     }
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public boolean transport(EntityItem ie) {
/* 154 */     ItemStack items = ie.func_92059_d();
/* 155 */     if (!this.linked || !isLinkValid()) return false; 
/* 156 */     WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(this.linkDim);
/* 157 */     TileEntity target = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 158 */     if (target != null && target instanceof TileMirror) {
/* 159 */       ((TileMirror)target).addStack(items);
/* 160 */       addInstability(null, items.func_190916_E());
/* 161 */       ie.func_70106_y();
/* 162 */       func_70296_d();
/* 163 */       target.func_70296_d();
/* 164 */       worldServer.func_175641_c(func_174877_v(), this.field_145854_h, 1, 0);
/* 165 */       return true;
/*     */     } 
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public boolean transportDirect(ItemStack items) {
/* 171 */     if (items == null || items.func_190926_b() || items.func_190916_E() <= 0) return false; 
/* 172 */     addStack(items.func_77946_l());
/* 173 */     func_70296_d();
/* 174 */     return true;
/*     */   }
/*     */   
/*     */   public void eject() {
/* 178 */     if (this.outputStacks.size() > 0 && this.count > 20) {
/* 179 */       int i = this.field_145850_b.field_73012_v.nextInt(this.outputStacks.size());
/* 180 */       if (this.outputStacks.get(i) != null && !((ItemStack)this.outputStacks.get(i)).func_190926_b()) {
/* 181 */         ItemStack outItem = ((ItemStack)this.outputStacks.get(i)).func_77946_l();
/* 182 */         outItem.func_190920_e(1);
/* 183 */         if (spawnItem(outItem)) {
/* 184 */           ((ItemStack)this.outputStacks.get(i)).func_190918_g(1);
/* 185 */           addInstability(null, 1);
/* 186 */           this.field_145850_b.func_175641_c(func_174877_v(), this.field_145854_h, 1, 0);
/* 187 */           if (((ItemStack)this.outputStacks.get(i)).func_190916_E() <= 0) {
/* 188 */             this.outputStacks.remove(i);
/*     */           }
/*     */         } 
/*     */       } else {
/* 192 */         this.outputStacks.remove(i);
/*     */       } 
/* 194 */       func_70296_d();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean spawnItem(ItemStack stack) {
/*     */     try {
/* 200 */       EnumFacing face = BlockStateUtils.getFacing(func_145832_p());
/*     */ 
/*     */ 
/*     */       
/* 204 */       EntityItem ie2 = new EntityItem(this.field_145850_b, func_174877_v().func_177958_n() + 0.5D, func_174877_v().func_177956_o() + 0.25D, func_174877_v().func_177952_p() + 0.5D, stack);
/* 205 */       ie2.field_70159_w = (face.func_82601_c() * 0.15F);
/* 206 */       ie2.field_70181_x = (face.func_96559_d() * 0.15F);
/* 207 */       ie2.field_70179_y = (face.func_82599_e() * 0.15F);
/* 208 */       ie2.field_71088_bW = 20;
/* 209 */       this.field_145850_b.func_72838_d(ie2);
/* 210 */       return true;
/* 211 */     } catch (Exception e) {
/* 212 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addInstability(World targetWorld, int amt) {
/* 217 */     this.instability += amt;
/* 218 */     func_70296_d();
/* 219 */     if (targetWorld != null) {
/* 220 */       TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 221 */       if (te != null && te instanceof TileMirror) {
/* 222 */         ((TileMirror)te).instability += amt;
/* 223 */         if (((TileMirror)te).instability < 0) ((TileMirror)te).instability = 0; 
/* 224 */         te.func_70296_d();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/* 233 */     super.readSyncNBT(nbttagcompound);
/* 234 */     this.linked = nbttagcompound.func_74767_n("linked");
/* 235 */     this.linkX = nbttagcompound.func_74762_e("linkX");
/* 236 */     this.linkY = nbttagcompound.func_74762_e("linkY");
/* 237 */     this.linkZ = nbttagcompound.func_74762_e("linkZ");
/* 238 */     this.linkDim = nbttagcompound.func_74762_e("linkDim");
/* 239 */     this.instability = nbttagcompound.func_74762_e("instability");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/* 245 */     super.writeSyncNBT(nbttagcompound);
/* 246 */     nbttagcompound.func_74757_a("linked", this.linked);
/* 247 */     nbttagcompound.func_74768_a("linkX", this.linkX);
/* 248 */     nbttagcompound.func_74768_a("linkY", this.linkY);
/* 249 */     nbttagcompound.func_74768_a("linkZ", this.linkZ);
/* 250 */     nbttagcompound.func_74768_a("linkDim", this.linkDim);
/* 251 */     nbttagcompound.func_74768_a("instability", this.instability);
/* 252 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145842_c(int i, int j) {
/* 259 */     if (i == 1) {
/*     */       
/* 261 */       if (this.field_145850_b.field_72995_K) {
/* 262 */         EnumFacing face = BlockStateUtils.getFacing(func_145832_p());
/* 263 */         double xx = func_174877_v().func_177958_n() + 0.33D + (this.field_145850_b.field_73012_v.nextFloat() * 0.33F) - face.func_82601_c() / 2.0D;
/* 264 */         double yy = func_174877_v().func_177956_o() + 0.33D + (this.field_145850_b.field_73012_v.nextFloat() * 0.33F) - face.func_96559_d() / 2.0D;
/* 265 */         double zz = func_174877_v().func_177952_p() + 0.33D + (this.field_145850_b.field_73012_v.nextFloat() * 0.33F) - face.func_82599_e() / 2.0D;
/* 266 */         FXDispatcher.INSTANCE.drawWispyMotes(xx, yy, zz, face
/* 267 */             .func_82601_c() / 50.0D + this.field_145850_b.field_73012_v.nextGaussian() * 0.01D, face.func_96559_d() / 50.0D + this.field_145850_b.field_73012_v.nextGaussian() * 0.01D, face.func_82599_e() / 50.0D + this.field_145850_b.field_73012_v.nextGaussian() * 0.01D, 
/* 268 */             MathHelper.func_76136_a(this.field_145850_b.field_73012_v, 10, 30), this.field_145850_b.field_73012_v.nextFloat() / 3.0F, 0.0F, this.field_145850_b.field_73012_v.nextFloat() / 2.0F, (float)(this.field_145850_b.field_73012_v.nextGaussian() * 0.01D));
/*     */       } 
/* 270 */       return true;
/*     */     } 
/* 272 */     return super.func_145842_c(i, j);
/*     */   }
/*     */   
/* 275 */   int count = 0;
/* 276 */   int inc = 40;
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/* 281 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 283 */       eject();
/*     */       
/* 285 */       checkInstability();
/*     */       
/* 287 */       if (this.count++ % this.inc == 0) {
/* 288 */         if (!isLinkValidSimple()) {
/* 289 */           if (this.inc < 600) this.inc += 20; 
/* 290 */           restoreLink();
/*     */         } else {
/* 292 */           this.inc = 40;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void checkInstability() {
/* 299 */     if (this.instability > 128) {
/* 300 */       AuraHelper.polluteAura(this.field_145850_b, this.field_174879_c, 1.0F, true);
/* 301 */       this.instability -= 128;
/* 302 */       func_70296_d();
/*     */     } 
/* 304 */     if (this.instability > 0 && this.count % 100 == 0) {
/* 305 */       this.instability--;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 310 */   private ArrayList<ItemStack> outputStacks = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbtCompound) {
/* 315 */     super.func_145839_a(nbtCompound);
/* 316 */     NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
/* 317 */     this.outputStacks = new ArrayList();
/*     */     
/* 319 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 321 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 322 */       byte b0 = nbttagcompound1.func_74771_c("Slot");
/*     */       
/* 324 */       this.outputStacks.add(new ItemStack(nbttagcompound1));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_189515_b(NBTTagCompound nbtCompound) {
/* 334 */     super.func_189515_b(nbtCompound);
/* 335 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 337 */     for (int i = 0; i < this.outputStacks.size(); i++) {
/*     */       
/* 339 */       if (this.outputStacks.get(i) != null && ((ItemStack)this.outputStacks.get(i)).func_190916_E() > 0) {
/* 340 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 341 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 342 */         ((ItemStack)this.outputStacks.get(i)).func_77955_b(nbttagcompound1);
/* 343 */         nbttaglist.func_74742_a(nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */     
/* 347 */     nbtCompound.func_74782_a("Items", nbttaglist);
/* 348 */     return nbtCompound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public int func_70302_i_() { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public ItemStack func_70301_a(int par1) { return ItemStack.field_190927_a; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public ItemStack func_70298_a(int par1, int par2) { return ItemStack.field_190927_a; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public ItemStack func_70304_b(int par1) { return ItemStack.field_190927_a; }
/*     */ 
/*     */   
/*     */   public void addStack(ItemStack stack) {
/* 376 */     this.outputStacks.add(stack);
/* 377 */     func_70296_d();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int par1, ItemStack stack2) {
/* 383 */     WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(this.linkDim);
/* 384 */     TileEntity target = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 385 */     if (target != null && target instanceof TileMirror) {
/* 386 */       ((TileMirror)target).addStack(stack2.func_77946_l());
/* 387 */       addInstability(null, stack2.func_190916_E());
/* 388 */       worldServer.func_175641_c(func_174877_v(), this.field_145854_h, 1, 0);
/*     */     } else {
/* 390 */       spawnItem(stack2.func_77946_l());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 397 */   public int func_70297_j_() { return 64; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   public boolean func_70300_a(EntityPlayer var1) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int var1, ItemStack var2) {
/* 407 */     WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(this.linkDim);
/* 408 */     TileEntity target = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 409 */     if (target != null && target instanceof TileMirror) {
/* 410 */       return true;
/*     */     }
/* 412 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 419 */   public String func_70005_c_() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 425 */   public boolean func_145818_k_() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 431 */   public ITextComponent func_145748_c_() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_174889_b(EntityPlayer player) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_174886_c(EntityPlayer player) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 449 */   public int func_174887_a_(int id) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_174885_b(int id, int value) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 461 */   public int func_174890_g() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_174888_l() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 473 */   public boolean func_191420_l() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */