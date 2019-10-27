/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.lib.events.EssentiaHandler;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ public class TileMirrorEssentia
/*     */   extends TileThaumcraft
/*     */   implements IAspectSource, ITickable
/*     */ {
/*     */   public boolean linked = false;
/*     */   public int linkX;
/*     */   public int linkY;
/*     */   public int linkZ;
/*     */   public int linkDim;
/*  30 */   public EnumFacing linkedFacing = EnumFacing.DOWN;
/*     */   
/*     */   public int instability;
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/*  36 */     this.linked = nbttagcompound.func_74767_n("linked");
/*  37 */     this.linkX = nbttagcompound.func_74762_e("linkX");
/*  38 */     this.linkY = nbttagcompound.func_74762_e("linkY");
/*  39 */     this.linkZ = nbttagcompound.func_74762_e("linkZ");
/*  40 */     this.linkDim = nbttagcompound.func_74762_e("linkDim");
/*  41 */     this.instability = nbttagcompound.func_74762_e("instability");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/*  47 */     nbttagcompound.func_74757_a("linked", this.linked);
/*  48 */     nbttagcompound.func_74768_a("linkX", this.linkX);
/*  49 */     nbttagcompound.func_74768_a("linkY", this.linkY);
/*  50 */     nbttagcompound.func_74768_a("linkZ", this.linkZ);
/*  51 */     nbttagcompound.func_74768_a("linkDim", this.linkDim);
/*  52 */     nbttagcompound.func_74768_a("instability", this.instability);
/*  53 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   protected void addInstability(World targetWorld, int amt) {
/*  57 */     this.instability += amt;
/*  58 */     func_70296_d();
/*  59 */     if (targetWorld != null) {
/*  60 */       TileEntity te = targetWorld.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  61 */       if (te != null && te instanceof TileMirrorEssentia) {
/*  62 */         ((TileMirrorEssentia)te).instability += amt;
/*  63 */         if (((TileMirrorEssentia)te).instability < 0) ((TileMirrorEssentia)te).instability = 0; 
/*  64 */         te.func_70296_d();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void restoreLink() {
/*  70 */     if (isDestinationValid()) {
/*  71 */       WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(this.linkDim);
/*  72 */       if (worldServer == null)
/*  73 */         return;  TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  74 */       if (te != null && te instanceof TileMirrorEssentia) {
/*  75 */         TileMirrorEssentia tm = (TileMirrorEssentia)te;
/*  76 */         tm.linked = true;
/*  77 */         tm.linkX = func_174877_v().func_177958_n();
/*  78 */         tm.linkY = func_174877_v().func_177956_o();
/*  79 */         tm.linkZ = func_174877_v().func_177952_p();
/*  80 */         tm.linkDim = this.field_145850_b.field_73011_w.getDimension();
/*  81 */         tm.syncTile(false);
/*  82 */         this.linkedFacing = BlockStateUtils.getFacing(worldServer.func_180495_p(new BlockPos(this.linkX, this.linkY, this.linkZ)));
/*  83 */         this.linked = true;
/*  84 */         func_70296_d();
/*  85 */         tm.func_70296_d();
/*  86 */         syncTile(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void invalidateLink() {
/*  92 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/*  93 */     if (worldServer == null)
/*  94 */       return;  if (!Utils.isChunkLoaded(worldServer, this.linkX, this.linkZ))
/*  95 */       return;  TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/*  96 */     if (te != null && te instanceof TileMirrorEssentia) {
/*  97 */       TileMirrorEssentia tm = (TileMirrorEssentia)te;
/*  98 */       tm.linked = false;
/*  99 */       tm.linkedFacing = EnumFacing.DOWN;
/* 100 */       func_70296_d();
/* 101 */       tm.func_70296_d();
/* 102 */       tm.syncTile(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLinkValid() {
/* 108 */     if (!this.linked) return false; 
/* 109 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 110 */     if (worldServer == null) {
/* 111 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 116 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 117 */     if (te == null || !(te instanceof TileMirrorEssentia)) {
/* 118 */       this.linked = false;
/* 119 */       func_70296_d();
/* 120 */       syncTile(false);
/* 121 */       return false;
/*     */     } 
/* 123 */     TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 124 */     if (!tm.linked) {
/* 125 */       this.linked = false;
/* 126 */       func_70296_d();
/* 127 */       syncTile(false);
/* 128 */       return false;
/*     */     } 
/* 130 */     if (tm.linkX != func_174877_v().func_177958_n() || tm.linkY != func_174877_v().func_177956_o() || tm.linkZ != func_174877_v().func_177952_p() || tm.linkDim != this.field_145850_b.field_73011_w
/* 131 */       .getDimension()) {
/*     */       
/* 133 */       this.linked = false;
/* 134 */       func_70296_d();
/* 135 */       syncTile(false);
/* 136 */       return false;
/*     */     } 
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isLinkValidSimple() {
/* 142 */     if (!this.linked) return false; 
/* 143 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 144 */     if (worldServer == null) {
/* 145 */       return false;
/*     */     }
/*     */     
/* 148 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 149 */     if (te == null || !(te instanceof TileMirrorEssentia)) {
/* 150 */       return false;
/*     */     }
/* 152 */     TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 153 */     if (!tm.linked) {
/* 154 */       return false;
/*     */     }
/* 156 */     if (tm.linkX != func_174877_v().func_177958_n() || tm.linkY != func_174877_v().func_177956_o() || tm.linkZ != func_174877_v().func_177952_p() || tm.linkDim != this.field_145850_b.field_73011_w
/* 157 */       .getDimension())
/*     */     {
/* 159 */       return false;
/*     */     }
/* 161 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isDestinationValid() {
/* 165 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 166 */     if (worldServer == null) {
/* 167 */       return false;
/*     */     }
/*     */     
/* 170 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 171 */     if (te == null || !(te instanceof TileMirrorEssentia)) {
/* 172 */       this.linked = false;
/* 173 */       func_70296_d();
/* 174 */       syncTile(false);
/* 175 */       return false;
/*     */     } 
/*     */     
/* 178 */     TileMirrorEssentia tm = (TileMirrorEssentia)te;
/* 179 */     if (tm.isLinkValid()) {
/* 180 */       return false;
/*     */     }
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public AspectList getAspects() { return null; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAspects(AspectList aspects) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesContainerAccept(Aspect tag) {
/* 200 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 201 */     if (this.linkedFacing == EnumFacing.DOWN && 
/* 202 */       worldServer != null) {
/* 203 */       this.linkedFacing = BlockStateUtils.getFacing(worldServer.func_180495_p(new BlockPos(this.linkX, this.linkY, this.linkZ)));
/*     */     }
/*     */ 
/*     */     
/* 207 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 208 */     if (te != null && te instanceof TileMirrorEssentia) {
/* 209 */       return EssentiaHandler.canAcceptEssentia(te, tag, this.linkedFacing, 8, true);
/*     */     }
/*     */     
/* 212 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addToContainer(Aspect tag, int amount) {
/* 217 */     if (!isLinkValid() || amount > 1) return amount;
/*     */     
/* 219 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 220 */     if (this.linkedFacing == EnumFacing.DOWN && 
/* 221 */       worldServer != null) {
/* 222 */       this.linkedFacing = BlockStateUtils.getFacing(worldServer.func_180495_p(new BlockPos(this.linkX, this.linkY, this.linkZ)));
/*     */     }
/*     */ 
/*     */     
/* 226 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 227 */     if (te != null && te instanceof TileMirrorEssentia) {
/* 228 */       boolean b = EssentiaHandler.addEssentia(te, tag, this.linkedFacing, 8, true, 5);
/* 229 */       if (b) addInstability(null, amount); 
/* 230 */       return b ? 0 : 1;
/*     */     } 
/*     */     
/* 233 */     return amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean takeFromContainer(Aspect tag, int amount) {
/* 238 */     if (!isLinkValid() || amount > 1) return false;
/*     */     
/* 240 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 241 */     if (this.linkedFacing == EnumFacing.DOWN && 
/* 242 */       worldServer != null) {
/* 243 */       this.linkedFacing = BlockStateUtils.getFacing(worldServer.func_180495_p(new BlockPos(this.linkX, this.linkY, this.linkZ)));
/*     */     }
/*     */ 
/*     */     
/* 247 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 248 */     if (te != null && te instanceof TileMirrorEssentia) {
/* 249 */       boolean b = EssentiaHandler.drainEssentia(te, tag, this.linkedFacing, 8, true, 5);
/* 250 */       if (b) addInstability(null, amount); 
/* 251 */       return b;
/*     */     } 
/*     */     
/* 254 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 259 */   public boolean takeFromContainer(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesContainerContainAmount(Aspect tag, int amount) {
/* 264 */     if (!isLinkValid() || amount > 1) return false;
/*     */     
/* 266 */     WorldServer worldServer = DimensionManager.getWorld(this.linkDim);
/* 267 */     if (this.linkedFacing == EnumFacing.DOWN && 
/* 268 */       worldServer != null) {
/* 269 */       this.linkedFacing = BlockStateUtils.getFacing(worldServer.func_180495_p(new BlockPos(this.linkX, this.linkY, this.linkZ)));
/*     */     }
/*     */ 
/*     */     
/* 273 */     TileEntity te = worldServer.func_175625_s(new BlockPos(this.linkX, this.linkY, this.linkZ));
/* 274 */     if (te != null && te instanceof TileMirrorEssentia) {
/* 275 */       return EssentiaHandler.findEssentia(te, tag, this.linkedFacing, 8, true);
/*     */     }
/* 277 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 282 */   public boolean doesContainerContain(AspectList ot) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public int containerContains(Aspect tag) { return 0; }
/*     */ 
/*     */ 
/*     */   
/* 291 */   int count = 0;
/* 292 */   int inc = 40;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/* 296 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/* 298 */       checkInstability();
/*     */       
/* 300 */       if (this.count++ % this.inc == 0) {
/* 301 */         if (!isLinkValidSimple()) {
/* 302 */           if (this.inc < 600) this.inc += 20; 
/* 303 */           restoreLink();
/*     */         } else {
/* 305 */           this.inc = 40;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void checkInstability() {
/* 312 */     if (this.instability > 64) {
/* 313 */       AuraHelper.polluteAura(this.field_145850_b, this.field_174879_c, 1.0F, true);
/* 314 */       this.instability -= 64;
/* 315 */       func_70296_d();
/*     */     } 
/* 317 */     if (this.instability > 0 && this.count % 100 == 0) {
/* 318 */       this.instability--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 324 */   public boolean isBlocked() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileMirrorEssentia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */