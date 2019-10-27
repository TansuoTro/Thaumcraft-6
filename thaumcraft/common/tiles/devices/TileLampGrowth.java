/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SPacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockMist;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.lib.utils.CropUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileLampGrowth
/*     */   extends TileThaumcraft
/*     */   implements IEssentiaTransport, ITickable
/*     */ {
/*     */   private boolean reserve = false;
/*  37 */   public int charges = -1;
/*  38 */   public int maxCharges = 20;
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
/*  43 */     super.onDataPacket(net, pkt);
/*  44 */     if (this.field_145850_b != null && 
/*  45 */       this.field_145850_b.field_72995_K) {
/*  46 */       this.field_145850_b.func_180500_c(EnumSkyBlock.BLOCK, func_174877_v());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  53 */     if (!this.field_145850_b.field_72995_K) {
/*  54 */       if (this.charges <= 0) {
/*  55 */         if (this.reserve) {
/*  56 */           this.charges = this.maxCharges;
/*  57 */           this.reserve = false;
/*  58 */           func_70296_d();
/*  59 */           syncTile(true);
/*     */         }
/*  61 */         else if (drawEssentia()) {
/*  62 */           this.charges = this.maxCharges;
/*  63 */           func_70296_d();
/*  64 */           syncTile(true);
/*     */         } 
/*  66 */         if (this.charges <= 0) {
/*  67 */           if (BlockStateUtils.isEnabled(func_145832_p()))
/*     */           {
/*  69 */             this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(func_174877_v()).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false)), 3);
/*     */           }
/*     */         }
/*  72 */         else if (!gettingPower() && !BlockStateUtils.isEnabled(func_145832_p())) {
/*     */           
/*  74 */           this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(func_174877_v()).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)), 3);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  79 */       if (!this.reserve && 
/*  80 */         drawEssentia()) {
/*  81 */         this.reserve = true;
/*     */       }
/*     */ 
/*     */       
/*  85 */       if (this.charges == 0) {
/*  86 */         this.charges = -1;
/*  87 */         syncTile(true);
/*     */       } 
/*     */       
/*  90 */       if (!gettingPower() && this.charges > 0) {
/*  91 */         updatePlant();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isPlant(BlockPos bp) {
/*  99 */     IBlockState b = this.field_145850_b.func_180495_p(bp);
/* 100 */     boolean flag = b.func_177230_c() instanceof net.minecraft.block.IGrowable;
/* 101 */     Material mat = b.func_185904_a();
/* 102 */     return ((flag || mat == Material.field_151570_A || mat == Material.field_151585_k) && mat != Material.field_151577_b);
/*     */   }
/*     */   
/* 105 */   int lx = 0;
/* 106 */   int ly = 0;
/* 107 */   int lz = 0;
/* 108 */   Block lid = Blocks.field_150350_a;
/* 109 */   int lmd = 0;
/*     */   
/* 111 */   ArrayList<BlockPos> checklist = new ArrayList();
/*     */   
/*     */   private void updatePlant() {
/* 114 */     IBlockState bs = this.field_145850_b.func_180495_p(new BlockPos(this.lx, this.ly, this.lz));
/* 115 */     if (this.lid != bs.func_177230_c() || this.lmd != bs.func_177230_c().func_176201_c(bs)) {
/* 116 */       EntityPlayer p = this.field_145850_b.func_184137_a(this.lx, this.ly, this.lz, 32.0D, false);
/* 117 */       if (p != null) {
/* 118 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockMist(new BlockPos(this.lx, this.ly, this.lz), 4259648), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w
/*     */               
/* 120 */               .getDimension(), this.lx, this.ly, this.lz, 32.0D));
/*     */       }
/* 122 */       this.lid = bs.func_177230_c();
/* 123 */       this.lmd = bs.func_177230_c().func_176201_c(bs);
/*     */     } 
/*     */     
/* 126 */     int distance = 6;
/*     */     
/* 128 */     if (this.checklist.size() == 0) {
/* 129 */       for (int a = -distance; a <= distance; a++) {
/* 130 */         for (int b = -distance; b <= distance; b++)
/* 131 */           this.checklist.add(func_174877_v().func_177982_a(a, distance, b)); 
/*     */       } 
/* 133 */       Collections.shuffle(this.checklist, this.field_145850_b.field_73012_v);
/*     */     } 
/*     */     
/* 136 */     int x = ((BlockPos)this.checklist.get(0)).func_177958_n();
/* 137 */     int y = ((BlockPos)this.checklist.get(0)).func_177956_o();
/* 138 */     int z = ((BlockPos)this.checklist.get(0)).func_177952_p();
/* 139 */     this.checklist.remove(0);
/*     */     
/* 141 */     for (; y >= this.field_174879_c.func_177956_o() - distance; y--) {
/* 142 */       BlockPos bp = new BlockPos(x, y, z);
/* 143 */       if (!this.field_145850_b.func_175623_d(bp) && isPlant(bp) && 
/* 144 */         func_145835_a(x + 0.5D, y + 0.5D, z + 0.5D) < (distance * distance) && 
/* 145 */         !CropUtils.isGrownCrop(this.field_145850_b, bp) && 
/* 146 */         CropUtils.doesLampGrow(this.field_145850_b, bp)) {
/*     */         
/* 148 */         this.charges--;
/*     */         
/* 150 */         this.lx = x;
/* 151 */         this.ly = y;
/* 152 */         this.lz = z;
/* 153 */         IBlockState bs2 = this.field_145850_b.func_180495_p(bp);
/* 154 */         this.lid = bs2.func_177230_c();
/* 155 */         this.lmd = bs2.func_177230_c().func_176201_c(bs2);
/*     */         
/* 157 */         this.field_145850_b.func_175684_a(bp, this.lid, 1);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbttagcompound) {
/* 167 */     this.reserve = nbttagcompound.func_74767_n("reserve");
/* 168 */     this.charges = nbttagcompound.func_74762_e("charges");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/* 174 */     nbttagcompound.func_74757_a("reserve", this.reserve);
/* 175 */     nbttagcompound.func_74768_a("charges", this.charges);
/* 176 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 181 */   int drawDelay = 0;
/*     */   
/*     */   boolean drawEssentia() {
/* 184 */     if (++this.drawDelay % 5 != 0) return false; 
/* 185 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), BlockStateUtils.getFacing(func_145832_p()));
/* 186 */     if (te != null) {
/* 187 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 188 */       if (!ic.canOutputTo(BlockStateUtils.getFacing(func_145832_p()).func_176734_d())) return false; 
/* 189 */       if (ic.getSuctionAmount(BlockStateUtils.getFacing(func_145832_p()).func_176734_d()) < 
/* 190 */         getSuctionAmount(BlockStateUtils.getFacing(func_145832_p())) && ic
/* 191 */         .takeEssentia(Aspect.PLANT, 1, BlockStateUtils.getFacing(func_145832_p()).func_176734_d()) == 1) {
/* 192 */         return true;
/*     */       }
/*     */     } 
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 200 */   public boolean isConnectable(EnumFacing face) { return (face == BlockStateUtils.getFacing(func_145832_p())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   public boolean canInputFrom(EnumFacing face) { return (face == BlockStateUtils.getFacing(func_145832_p())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public boolean canOutputTo(EnumFacing face) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */   
/* 219 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   public Aspect getSuctionType(EnumFacing face) { return Aspect.PLANT; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public int getSuctionAmount(EnumFacing face) { return (face == BlockStateUtils.getFacing(func_145832_p()) && (!this.reserve || this.charges <= 0)) ? 128 : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public Aspect getEssentiaType(EnumFacing loc) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public int getEssentiaAmount(EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public int addEssentia(Aspect aspect, int amount, EnumFacing loc) { return 0; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileLampGrowth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */