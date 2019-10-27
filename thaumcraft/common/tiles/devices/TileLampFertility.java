/*     */ package thaumcraft.common.tiles.devices;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SPacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.TileThaumcraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileLampFertility
/*     */   extends TileThaumcraft
/*     */   implements IEssentiaTransport, ITickable
/*     */ {
/*  29 */   public int charges = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
/*  34 */     super.onDataPacket(net, pkt);
/*  35 */     if (this.field_145850_b != null && 
/*  36 */       this.field_145850_b.field_72995_K) {
/*  37 */       this.field_145850_b.func_180500_c(EnumSkyBlock.BLOCK, func_174877_v());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  42 */   int count = 0;
/*     */ 
/*     */   
/*     */   public void func_73660_a() {
/*  46 */     if (!this.field_145850_b.field_72995_K) {
/*  47 */       if (this.charges < 10) {
/*  48 */         if (drawEssentia()) {
/*  49 */           this.charges++;
/*  50 */           func_70296_d();
/*  51 */           syncTile(true);
/*     */         } 
/*  53 */         if (this.charges <= 1) {
/*  54 */           if (BlockStateUtils.isEnabled(func_145832_p()))
/*     */           {
/*  56 */             this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(func_174877_v()).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(false)), 3);
/*     */           }
/*     */         }
/*  59 */         else if (!gettingPower() && !BlockStateUtils.isEnabled(func_145832_p())) {
/*     */           
/*  61 */           this.field_145850_b.func_180501_a(this.field_174879_c, this.field_145850_b.func_180495_p(func_174877_v()).func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)), 3);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  66 */       if (!gettingPower() && this.charges > 1 && this.count++ % 300 == 0) {
/*  67 */         updateAnimals();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateAnimals() {
/*  76 */     int distance = 7;
/*     */     
/*  78 */     List<EntityAnimal> var5 = this.field_145850_b.func_72872_a(EntityAnimal.class, (new AxisAlignedBB(this.field_174879_c
/*     */           
/*  80 */           .func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p(), (this.field_174879_c
/*  81 */           .func_177958_n() + 1), (this.field_174879_c.func_177956_o() + 1), (this.field_174879_c.func_177952_p() + 1)))
/*  82 */         .func_72314_b(distance, distance, distance));
/*     */     
/*  84 */     Iterator var2 = var5.iterator();
/*     */     
/*  86 */     label34: while (var2.hasNext()) {
/*     */       
/*  88 */       EntityAnimal var3 = (EntityAnimal)var2.next();
/*     */ 
/*     */       
/*  91 */       EntityAnimal entityAnimal = var3;
/*     */       
/*  93 */       if (var3.func_70874_b() != 0 || var3.func_70880_s())
/*     */         continue; 
/*  95 */       ArrayList<EntityAnimal> sa = new ArrayList<EntityAnimal>();
/*  96 */       Iterator varq = var5.iterator();
/*  97 */       while (varq.hasNext()) {
/*     */         
/*  99 */         EntityLivingBase var7 = (EntityLivingBase)varq.next();
/* 100 */         if (var7.getClass().equals(entityAnimal.getClass())) {
/* 101 */           sa.add((EntityAnimal)var7);
/*     */         }
/*     */       } 
/*     */       
/* 105 */       if (sa != null && sa.size() > 9)
/*     */         continue; 
/* 107 */       Iterator var22 = sa.iterator();
/* 108 */       EntityAnimal partner = null;
/* 109 */       while (var22.hasNext()) {
/*     */ 
/*     */         
/* 112 */         EntityAnimal var33 = (EntityAnimal)var22.next();
/* 113 */         if (var33.func_70874_b() != 0 || var33.func_70880_s())
/* 114 */           continue;  if (partner == null) {
/* 115 */           partner = var33; continue;
/*     */         } 
/* 117 */         this.charges -= 5;
/* 118 */         var33.func_146082_f(null);
/* 119 */         partner.func_146082_f(null);
/*     */         break label34;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void readSyncNBT(NBTTagCompound nbttagcompound) { this.charges = nbttagcompound.func_74762_e("charges"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbttagcompound) {
/* 136 */     nbttagcompound.func_74768_a("charges", this.charges);
/* 137 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 142 */   int drawDelay = 0;
/*     */   
/*     */   boolean drawEssentia() {
/* 145 */     if (++this.drawDelay % 5 != 0) return false; 
/* 146 */     TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, func_174877_v(), BlockStateUtils.getFacing(func_145832_p()));
/* 147 */     if (te != null) {
/* 148 */       IEssentiaTransport ic = (IEssentiaTransport)te;
/* 149 */       if (!ic.canOutputTo(BlockStateUtils.getFacing(func_145832_p()).func_176734_d())) return false; 
/* 150 */       if (ic.getSuctionAmount(BlockStateUtils.getFacing(func_145832_p()).func_176734_d()) < 
/* 151 */         getSuctionAmount(BlockStateUtils.getFacing(func_145832_p())) && ic
/* 152 */         .takeEssentia(Aspect.DESIRE, 1, BlockStateUtils.getFacing(func_145832_p()).func_176734_d()) == 1) {
/* 153 */         return true;
/*     */       }
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 161 */   public boolean isConnectable(EnumFacing face) { return (face == BlockStateUtils.getFacing(func_145832_p())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public boolean canInputFrom(EnumFacing face) { return (face == BlockStateUtils.getFacing(func_145832_p())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public boolean canOutputTo(EnumFacing face) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuction(Aspect aspect, int amount) {}
/*     */ 
/*     */ 
/*     */   
/* 180 */   public int getMinimumSuction() { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public Aspect getSuctionType(EnumFacing face) { return Aspect.DESIRE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public int getSuctionAmount(EnumFacing face) { return (face == BlockStateUtils.getFacing(func_145832_p())) ? (128 - this.charges * 10) : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public Aspect getEssentiaType(EnumFacing loc) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public int getEssentiaAmount(EnumFacing loc) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   public int takeEssentia(Aspect aspect, int amount, EnumFacing facing) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public int addEssentia(Aspect aspect, int amount, EnumFacing facing) { return 0; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\devices\TileLampFertility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */